package xyz.omniplay.ui

import android.os.Handler
import android.os.Looper
import android.view.HapticFeedbackConstants
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.lyrics.Lyrics
import xyz.omniplay.lyrics.LyricsResult
import xyz.omniplay.lyrics.LrcLibClient
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService

class LyricsController(
    private val activity: AppCompatActivity,
    private val binding: ActivityMainBinding,
    private val getPlaybackService: () -> PlaybackService?
) {

    private var lyricAdapter: LyricAdapter? = null
    private var currentLyrics: Lyrics? = null
    private var lastOnlineLyricsSongId: Long? = null
    var isLyricsShowing: Boolean = false
        private set
    private var isUserScrollingLyrics = false
    private val lyricsScrollResetHandler = Handler(Looper.getMainLooper())
    private val resetUserScrollingRunnable = Runnable { isUserScrollingLyrics = false }
    private var lyricsFetchJob: Job? = null

    private fun dpToPx(dp: Int): Int = (dp * activity.resources.displayMetrics.density).toInt()

    fun setup(onSeek: (Long) -> Unit) {
        lyricAdapter = LyricAdapter { line ->
            val service = getPlaybackService()
            service?.seekTo(line.timeMs.toInt())
            binding.playbackSlider.setProgress(line.timeMs)
            binding.currentTimeText.text = Song.formatTime(line.timeMs)
            isUserScrollingLyrics = false
            try {
                binding.root.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
            } catch (ignored: Exception) {}
            onSeek(line.timeMs)
        }
        binding.lyricsRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.lyricsRecyclerView.adapter = lyricAdapter

        binding.lyricsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isUserScrollingLyrics = true
                    lyricsScrollResetHandler.removeCallbacks(resetUserScrollingRunnable)
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE && isUserScrollingLyrics) {
                    lyricsScrollResetHandler.removeCallbacks(resetUserScrollingRunnable)
                    lyricsScrollResetHandler.postDelayed(resetUserScrollingRunnable, 3500L)
                }
            }
        })

        binding.btnCloseLyrics.setOnClickListener {
            closeLyricsCard()
        }

        binding.lyricsHeaderBar.setOnClickListener {
            closeLyricsCard()
        }

        binding.btnLyricsRetry.setOnClickListener {
            loadLyricsForCurrentSong(forceRefresh = true)
        }
    }

    fun openLyricsCard() {
        if (isLyricsShowing) return
        val card = binding.albumArtCard
        val cardHeight = card.height.toFloat().coerceAtLeast(1f)

        binding.peekAlbumArtCard.visibility = View.INVISIBLE
        binding.lyricsCard.visibility = View.VISIBLE
        binding.lyricsCard.translationX = 0f
        binding.lyricsCard.translationY = 0f
        binding.lyricsCard.scaleX = 0.94f
        binding.lyricsCard.scaleY = 0.94f
        binding.lyricsCard.alpha = 0.6f

        prepareLyricsForDisplay()

        binding.lyricsCard.animate()
            .scaleX(1f).scaleY(1f).alpha(1f)
            .setDuration(220L).start()

        card.animate()
            .translationY(-cardHeight * 1.25f)
            .setDuration(220L)
            .setInterpolator(DecelerateInterpolator())
            .withEndAction {
                card.visibility = View.INVISIBLE
                card.translationY = 0f
                isLyricsShowing = true
                binding.lyricsCard.scaleX = 1f
                binding.lyricsCard.scaleY = 1f
                binding.lyricsCard.alpha = 1f
                getPlaybackService()?.let { service ->
                    updateLyricsProgress(service.getCurrentPosition().toLong())
                }
            }
            .start()
    }

    fun closeLyricsCard() {
        if (!isLyricsShowing) return
        val lyrics = binding.lyricsCard
        val album = binding.albumArtCard
        val cardHeight = album.height.toFloat().coerceAtLeast(1f)

        album.visibility = View.VISIBLE
        album.translationX = 0f
        album.translationY = -cardHeight * 1.15f
        album.scaleX = 1f
        album.scaleY = 1f
        album.alpha = 1f

        album.animate()
            .translationY(0f)
            .setDuration(200L)
            .setInterpolator(DecelerateInterpolator())
            .start()

        lyrics.animate()
            .scaleX(0.92f).scaleY(0.92f).alpha(0f)
            .setDuration(200L)
            .setInterpolator(DecelerateInterpolator())
            .withEndAction {
                lyrics.visibility = View.INVISIBLE
                lyrics.translationX = 0f
                lyrics.translationY = 0f
                lyrics.scaleX = 1f
                lyrics.scaleY = 1f
                lyrics.alpha = 1f
                isLyricsShowing = false
            }
            .start()
    }

    fun prepareLyricsForDisplay() {
        val song = getPlaybackService()?.currentSong ?: return
        val lyrics = currentLyrics ?: LrcLibClient.getCachedLyrics(activity, song)?.also {
            currentLyrics = it
        }

        if (lyrics != null && lyrics.songId == song.id) {
            renderLyricsResult(LyricsResult.Success(lyrics, isOffline = lyrics.isOffline))
            // If currently displayed lyrics are static, force an online search for synchronized lyrics
            if (!lyrics.hasSynced && !lyrics.isInstrumental && lastOnlineLyricsSongId != song.id) {
                loadLyricsForSong(song, forceRefresh = false)
            }
        } else {
            showLyricsLoading()
            loadLyricsForSong(song, forceRefresh = false)
        }
    }

    fun loadLyricsForCurrentSong(forceRefresh: Boolean = false) {
        val song = getPlaybackService()?.currentSong ?: return
        loadLyricsForSong(song, forceRefresh)
    }

    fun loadLyricsForSong(song: Song, forceRefresh: Boolean = false) {
        lyricsFetchJob?.cancel()

        val isSyncedOrInstrumental = currentLyrics?.let { it.hasSynced || it.isInstrumental } == true
        val alreadyFetchedOnline = (lastOnlineLyricsSongId == song.id)

        // If we already have synced lyrics or already queried online for this song (and not force refreshing), no need to re-query
        if (currentLyrics?.songId == song.id && !forceRefresh && (isSyncedOrInstrumental || alreadyFetchedOnline)) {
            if (isLyricsShowing) {
                renderLyricsResult(LyricsResult.Success(currentLyrics!!, isOffline = currentLyrics!!.isOffline))
            }
            return
        }

        // Only show full loading spinner if we don't have static lyrics already showing, or if user explicitly requested a refresh
        if (isLyricsShowing && (currentLyrics == null || forceRefresh)) {
            showLyricsLoading()
        }

        lastOnlineLyricsSongId = song.id
        lyricsFetchJob = activity.lifecycleScope.launch {
            val result = LrcLibClient.getLyrics(activity, song, forceRefresh)
            if (getPlaybackService()?.currentSong?.id == song.id) {
                when (result) {
                    is LyricsResult.Success -> {
                        currentLyrics = result.lyrics
                        if (isLyricsShowing) {
                            renderLyricsResult(result)
                        }
                    }
                    is LyricsResult.NotFound, is LyricsResult.Error -> {
                        if (currentLyrics == null || forceRefresh) {
                            currentLyrics = null
                            if (isLyricsShowing) {
                                renderLyricsResult(result)
                            }
                        }
                    }
                    is LyricsResult.Loading -> {}
                }
            }
        }
    }

    private fun showLyricsLoading() {
        binding.lyricsRecyclerView.visibility = View.GONE
        binding.lyricsPlainScroll.visibility = View.GONE
        binding.lyricsStatusLayout.visibility = View.VISIBLE
        binding.lyricsProgressBar.visibility = View.VISIBLE
        binding.lyricsStatusIcon.visibility = View.GONE
        binding.lyricsStatusText.text = "Loading lyrics..."
        binding.lyricsStatusSubtext.visibility = View.GONE
        binding.btnLyricsRetry.visibility = View.GONE
    }

    private fun renderLyricsResult(result: LyricsResult) {
        when (result) {
            is LyricsResult.Loading -> {
                showLyricsLoading()
            }
            is LyricsResult.Success -> {
                val lyrics = result.lyrics
                if (lyrics.isInstrumental) {
                    binding.lyricsRecyclerView.visibility = View.GONE
                    binding.lyricsPlainScroll.visibility = View.GONE
                    binding.lyricsStatusLayout.visibility = View.VISIBLE
                    binding.lyricsProgressBar.visibility = View.GONE
                    binding.lyricsStatusIcon.visibility = View.VISIBLE
                    binding.lyricsStatusIcon.setImageResource(R.drawable.ic_music_note)
                    binding.lyricsStatusText.text = "Instrumental Track"
                    binding.lyricsStatusSubtext.text = "This track contains no vocal lyrics"
                    binding.lyricsStatusSubtext.visibility = View.VISIBLE
                    binding.btnLyricsRetry.visibility = View.GONE
                    updateLyricsSourceBadge(lyrics.source, result.isOffline)
                    return
                }

                if (lyrics.hasSynced) {
                    binding.lyricsStatusLayout.visibility = View.GONE
                    binding.lyricsPlainScroll.visibility = View.GONE
                    binding.lyricsRecyclerView.visibility = View.VISIBLE
                    lyricAdapter?.submitLines(lyrics.syncedLyrics ?: emptyList())
                    getPlaybackService()?.let { service ->
                        updateLyricsProgress(service.getCurrentPosition().toLong())
                    }
                    updateLyricsSourceBadge(lyrics.source, result.isOffline)
                    return
                }

                if (lyrics.hasPlain) {
                    binding.lyricsStatusLayout.visibility = View.GONE
                    binding.lyricsRecyclerView.visibility = View.GONE
                    binding.lyricsPlainScroll.visibility = View.VISIBLE
                    binding.lyricsPlainText.text = lyrics.plainLyrics
                    updateLyricsSourceBadge(lyrics.source, result.isOffline)
                    return
                }

                binding.lyricsRecyclerView.visibility = View.GONE
                binding.lyricsPlainScroll.visibility = View.GONE
                binding.lyricsStatusLayout.visibility = View.VISIBLE
                binding.lyricsProgressBar.visibility = View.GONE
                binding.lyricsStatusIcon.visibility = View.VISIBLE
                binding.lyricsStatusIcon.setImageResource(R.drawable.ic_music_note)
                binding.lyricsStatusText.text = "No lyrics available"
                binding.lyricsStatusSubtext.text = "No lyric lines available for this song"
                binding.lyricsStatusSubtext.visibility = View.VISIBLE
                binding.btnLyricsRetry.visibility = View.VISIBLE
                binding.btnLyricsRetry.text = "Search Again"
            }
            is LyricsResult.NotFound -> {
                binding.lyricsRecyclerView.visibility = View.GONE
                binding.lyricsPlainScroll.visibility = View.GONE
                binding.lyricsStatusLayout.visibility = View.VISIBLE
                binding.lyricsProgressBar.visibility = View.GONE
                binding.lyricsStatusIcon.visibility = View.VISIBLE
                binding.lyricsStatusIcon.setImageResource(R.drawable.ic_music_note)
                binding.lyricsStatusText.text = "No lyrics found"
                binding.lyricsStatusSubtext.text = result.message
                binding.lyricsStatusSubtext.visibility = View.VISIBLE
                binding.btnLyricsRetry.visibility = View.VISIBLE
                binding.btnLyricsRetry.text = "Search Again"
            }
            is LyricsResult.Error -> {
                binding.lyricsRecyclerView.visibility = View.GONE
                binding.lyricsPlainScroll.visibility = View.GONE
                binding.lyricsStatusLayout.visibility = View.VISIBLE
                binding.lyricsProgressBar.visibility = View.GONE
                binding.lyricsStatusIcon.visibility = View.VISIBLE
                binding.lyricsStatusIcon.setImageResource(R.drawable.ic_info)
                if (result.isRateLimited) {
                    binding.lyricsStatusText.text = "Too many requests"
                } else {
                    binding.lyricsStatusText.text = "Could not load lyrics"
                }
                binding.lyricsStatusSubtext.text = result.message
                binding.lyricsStatusSubtext.visibility = View.VISIBLE
                binding.btnLyricsRetry.visibility = View.VISIBLE
                binding.btnLyricsRetry.text = "Retry"
            }
        }
    }

    private fun updateLyricsSourceBadge(source: String, isOffline: Boolean) {
        val badge = when {
            source.startsWith("EMBEDDED") -> source.removePrefix("EMBEDDED (").removeSuffix(")")
            source == "LOCAL FILE" -> "LOCAL"
            isOffline -> "OFFLINE"
            else -> "LRCLIB"
        }
        binding.lyricsSourceBadge.text = badge
    }

    fun updateLyricsProgress(currentPositionMs: Long) {
        if (!isLyricsShowing) return
        val lyrics = currentLyrics ?: return
        val synced = lyrics.syncedLyrics ?: return
        if (synced.isEmpty()) return

        val activeIndex = synced.indexOfLast { it.timeMs <= currentPositionMs }
        val adapter = lyricAdapter ?: return
        if (activeIndex != adapter.activeIndex) {
            val changed = adapter.setActiveIndex(activeIndex)
            if (changed && activeIndex >= 0 && !isUserScrollingLyrics) {
                val layoutManager = binding.lyricsRecyclerView.layoutManager as? LinearLayoutManager
                if (layoutManager != null && binding.lyricsRecyclerView.height > 0) {
                    val offset = (binding.lyricsRecyclerView.height / 2) - dpToPx(24)
                    layoutManager.scrollToPositionWithOffset(activeIndex, offset)
                }
            }
        }
    }

    fun onTrackChanged(newSong: Song?) {
        lyricsFetchJob?.cancel()
        currentLyrics = null
        lastOnlineLyricsSongId = null
        isUserScrollingLyrics = false
        lyricsScrollResetHandler.removeCallbacks(resetUserScrollingRunnable)

        if (lyricAdapter != null) {
            lyricAdapter?.submitLines(emptyList())
            lyricAdapter?.setActiveIndex(-1)
        }
        binding.lyricsPlainText.text = ""
        binding.lyricsSourceBadge.text = ""

        if (newSong == null) {
            renderLyricsResult(LyricsResult.NotFound("No song is currently playing"))
            return
        }

        if (isLyricsShowing) {
            val cached = LrcLibClient.getCachedLyrics(activity, newSong)
            if (cached != null) {
                currentLyrics = cached
                renderLyricsResult(LyricsResult.Success(cached, isOffline = cached.isOffline))
                if (!cached.hasSynced && !cached.isInstrumental) {
                    loadLyricsForSong(newSong, forceRefresh = false)
                }
            } else {
                showLyricsLoading()
                loadLyricsForSong(newSong, forceRefresh = false)
            }
        } else {
            LrcLibClient.getCachedLyrics(activity, newSong)?.let { cached ->
                currentLyrics = cached
            }
        }
    }

    fun handleBackPressed(): Boolean {
        if (isLyricsShowing) {
            closeLyricsCard()
            return true
        }
        return false
    }

    fun onDestroy() {
        lyricsFetchJob?.cancel()
        lyricsScrollResetHandler.removeCallbacks(resetUserScrollingRunnable)
    }
}
