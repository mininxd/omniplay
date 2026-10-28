package xyz.omniplay.equalizer.ui

import android.content.Intent
import android.media.audiofx.AudioEffect
import android.os.Bundle
import android.view.HapticFeedbackConstants
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import xyz.omniplay.equalizer.R
import xyz.omniplay.equalizer.audio.EqualizerManager
import xyz.omniplay.equalizer.databinding.ActivityQuickEqualizerBinding
import xyz.omniplay.equalizer.util.ThemeHelper

/**
 * Floating quick Equalizer panel activity.
 * Invoked by OmniPlay or external audio apps to adjust presets,
 * Dax 10-band curves, and ClearBass without navigating away.
 */
class QuickEqualizerActivity : AppCompatActivity(), EqualizerManager.EqualizerListener {

    private lateinit var binding: ActivityQuickEqualizerBinding
    private lateinit var eqManager: EqualizerManager
    private var isUpdatingUi = false

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)
        super.onCreate(savedInstanceState)
        window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.attributes = window.attributes.apply {
            dimAmount = 0.0f
        }
        window.setBackgroundDrawableResource(android.R.color.transparent)
        binding = ActivityQuickEqualizerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ThemeHelper.applySystemBars(this)

        eqManager = EqualizerManager.getInstance(this)
        eqManager.addListener(this)

        val sessionId = intent.getIntExtra(AudioEffect.EXTRA_AUDIO_SESSION, 0)
        val packageName = intent.getStringExtra(AudioEffect.EXTRA_PACKAGE_NAME)
        if (sessionId > 0) {
            eqManager.openAudioSession(sessionId, packageName)
        } else {
            try {
                sendBroadcast(Intent("xyz.omniplay.action.REQUEST_AUDIO_SESSION").apply {
                    setPackage("xyz.omniplay")
                })
            } catch (_: Exception) {}
        }

        binding.rootQuickPanel.setOnClickListener {
            finish()
        }

        setupMasterSwitch()
        setupPresets()
        setupDaxBands()
        setupClearBass()
        setupResetButton()

        refreshUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        eqManager.removeListener(this)
    }

    override fun onEqualizerConfigChanged() {
        runOnUiThread {
            refreshUI()
        }
    }

    private fun setupMasterSwitch() {
        binding.switchMaster.isChecked = eqManager.isEnabled()

        binding.switchMaster.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdatingUi) return@setOnCheckedChangeListener
            if (isChecked != eqManager.isEnabled()) {
                binding.switchMaster.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                eqManager.setEnabled(isChecked)
                updateEnabledState(isChecked)
            }
        }
    }

    private fun setupPresets() {
        binding.chipGroupPresets.removeAllViews()
        for ((index, preset) in eqManager.presets.withIndex()) {
            val chip = (layoutInflater.inflate(R.layout.item_preset_chip, binding.chipGroupPresets, false) as Chip).apply {
                id = index + 1000
                text = preset.name
                setOnClickListener {
                    if (isUpdatingUi) return@setOnClickListener
                    it.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                    eqManager.applyPreset(index)
                }
            }
            binding.chipGroupPresets.addView(chip)
        }
        updatePresetSelection(eqManager.getCurrentPresetIndex())
    }

    private fun updatePresetSelection(index: Int) {
        val chipId = index + 1000
        binding.chipGroupPresets.check(chipId)
        val selectedChip = binding.chipGroupPresets.findViewById<Chip>(chipId)
        if (selectedChip != null) {
            binding.scrollPresets.post {
                val scrollX = selectedChip.left - (binding.scrollPresets.width - selectedChip.width) / 2
                binding.scrollPresets.smoothScrollTo(scrollX.coerceAtLeast(0), 0)
            }
        }
    }

    private fun setupDaxBands() {
        binding.daxEqBarsView.setBandGains(eqManager.getAllDaxBandLevels())
        binding.daxEqBarsView.listener = object : DaxEqualizerBarsView.OnBandGainChangeListener {
            override fun onBandGainChanged(bandIndex: Int, gainDb: Int, fromUser: Boolean) {
                if (fromUser) {
                    eqManager.setDaxBandLevel(bandIndex, gainDb, fromUser = true)
                }
            }
        }
    }

    private fun setupClearBass() {
        binding.sliderClearBass.isTickVisible = false
        binding.sliderClearBass.valueFrom = -10f
        binding.sliderClearBass.valueTo = 10f
        binding.sliderClearBass.stepSize = 1f

        val currentCb = eqManager.getClearBassLevel().toFloat()
        binding.sliderClearBass.value = currentCb
        binding.tvClearBassValue.text = formatDb(currentCb.toInt())

        binding.sliderClearBass.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val db = value.toInt()
                binding.tvClearBassValue.text = formatDb(db)
                eqManager.setClearBassLevel(db)
            }
        }
    }

    private fun setupResetButton() {
        binding.btnResetDax.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
            eqManager.resetEqToFlat()
            refreshUI()
        }
    }

    private fun refreshUI() {
        isUpdatingUi = true

        val enabled = eqManager.isEnabled()
        binding.switchMaster.isChecked = enabled
        updateEnabledState(enabled)

        updatePresetSelection(eqManager.getCurrentPresetIndex())
        binding.daxEqBarsView.setBandGains(eqManager.getAllDaxBandLevels())

        val cb = eqManager.getClearBassLevel().toFloat()
        binding.sliderClearBass.value = cb
        binding.tvClearBassValue.text = formatDb(cb.toInt())

        isUpdatingUi = false
    }

    private fun updateEnabledState(enabled: Boolean) {
        val targetAlpha = if (enabled) 1.0f else 0.38f
        binding.daxEqBarsView.isEnabled = enabled
        binding.daxEqBarsView.alpha = targetAlpha
        binding.btnResetDax.isEnabled = enabled
        binding.scrollPresets.isEnabled = enabled
        binding.scrollPresets.alpha = targetAlpha
        binding.sliderClearBass.isEnabled = enabled
        binding.sliderClearBass.alpha = targetAlpha
        binding.tvClearBassValue.alpha = targetAlpha
    }

    private fun formatDb(db: Int): String {
        return when {
            db > 0 -> "+$db dB"
            else -> "$db dB"
        }
    }
}
