package xyz.omniplay.equalizer.ui

import android.app.Activity
import android.content.Intent
import android.media.audiofx.AudioEffect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import xyz.omniplay.equalizer.R
import xyz.omniplay.equalizer.audio.EqualizerManager
import xyz.omniplay.equalizer.databinding.ActivityMainBinding
import xyz.omniplay.equalizer.service.EqualizerService
import xyz.omniplay.equalizer.util.ThemeColors
import xyz.omniplay.equalizer.util.ThemeHelper
import java.util.Locale
import kotlin.math.roundToInt

/**
 * OmniQualizer: Material 3 Equalizer & Sound Enhancement Application.
 *
 * Audio DSP features:
 * - 10-band Graphic Equalizer, Intelligent Equalizer (IEQ).
 * - Sub-Bass, Loudness Enhancer, Volume Boost.
 * - Multi-session routing: Global session 0 and external player audio session binding.
 */
class MainActivity : AppCompatActivity(), EqualizerManager.EqualizerListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var eqManager: EqualizerManager

    private var isUpdatingUi = false

    private val settingsLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val themeChanged = result.data?.getBooleanExtra(SettingsActivity.EXTRA_THEME_CHANGED, false) == true
            if (themeChanged) {
                recreate()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ThemeHelper.applySystemBars(this)

        eqManager = EqualizerManager.getInstance(this)
        eqManager.addListener(this)

        if (eqManager.isEnabled()) {
            EqualizerService.start(this)
        }

        handleAudioSessionIntent(intent)

        setupToolbar()
        setupMasterSwitch()
        setupPresets()
        setupDaxBands()
        setupIeq()
        setupClearBass()
        setupLoudnessEnhancer()
        setupVolumeBoost()
        setupPlaybackEffects()
        setupResetButton()

        refreshUI()
        requestNotificationPermissionIfNeeded()
    }

    override fun onResume() {
        super.onResume()
        if (eqManager.isEnabled()) {
            EqualizerService.start(this)
        }
        try {
            sendBroadcast(Intent("xyz.omniplay.action.REQUEST_AUDIO_SESSION").apply {
                setPackage("xyz.omniplay")
            })
        } catch (_: Exception) {}
        refreshUI()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleAudioSessionIntent(intent)
        refreshUI()
    }

    private fun handleAudioSessionIntent(intent: Intent?) {
        if (intent == null) return
        val action = intent.action
        val sessionId = intent.getIntExtra(AudioEffect.EXTRA_AUDIO_SESSION, AudioEffect.ERROR)
        val packageName = intent.getStringExtra(AudioEffect.EXTRA_PACKAGE_NAME)
        val contentType = intent.getIntExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC)
        Log.i("MainActivity", "handleAudioSessionIntent action=$action, sessionId=$sessionId, package=$packageName, contentType=$contentType")

        if (sessionId != AudioEffect.ERROR && sessionId >= 0) {
            eqManager.openAudioSession(sessionId, packageName)
        }

        if (action == AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL) {
            setResult(RESULT_OK)
            if (eqManager.isEnabled()) {
                EqualizerService.start(this)
            }
        }
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
            }
        }
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

    private fun setupToolbar() {
        binding.toolbar.subtitle = eqManager.getHardwareDspStatus()
        binding.btnSettings.setOnClickListener {
            settingsLauncher.launch(Intent(this, SettingsActivity::class.java))
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

        binding.rowMasterToggle.setOnClickListener {
            binding.switchMaster.toggle()
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

    private fun setupIeq() {
        updateIeqChipSelection(eqManager.getIeqPreset())

        binding.chipGroupIeq.setOnCheckedStateChangeListener { _, checkedIds ->
            if (isUpdatingUi) return@setOnCheckedStateChangeListener
            val preset = when (checkedIds.firstOrNull()) {
                R.id.chip_ieq_balanced -> EqualizerManager.IEQ_BALANCED
                R.id.chip_ieq_warm -> EqualizerManager.IEQ_WARM
                R.id.chip_ieq_detailed -> EqualizerManager.IEQ_DETAILED
                else -> EqualizerManager.IEQ_OFF
            }
            if (preset != eqManager.getIeqPreset()) {
                binding.chipGroupIeq.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                eqManager.setIeqPreset(preset)
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

        var previousDb = currentCb.toInt()
        binding.sliderClearBass.addOnChangeListener { slider, value, fromUser ->
            if (fromUser) {
                val db = value.toInt()
                if (db != previousDb) {
                    if (db == 0) {
                        slider.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                    } else {
                        slider.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                    }
                    previousDb = db
                }
                binding.tvClearBassValue.text = formatDb(db)
                eqManager.setClearBassLevel(db)
            }
        }

        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean = true
            override fun onDoubleTapEvent(e: MotionEvent): Boolean {
                if (e.actionMasked == MotionEvent.ACTION_UP) {
                    binding.sliderClearBass.post {
                        binding.sliderClearBass.value = 0f
                        binding.tvClearBassValue.text = formatDb(0)
                        binding.sliderClearBass.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                        eqManager.setClearBassLevel(0)
                    }
                }
                return true
            }
        })
        binding.sliderClearBass.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            false
        }
    }

    private fun setupLoudnessEnhancer() {
        binding.sliderLoudnessEnhancer.isTickVisible = false
        binding.sliderLoudnessEnhancer.valueFrom = 0.0f
        binding.sliderLoudnessEnhancer.valueTo = 10.0f
        binding.sliderLoudnessEnhancer.stepSize = 0.1f
        binding.sliderLoudnessEnhancer.setLabelFormatter { value ->
            String.format(Locale.US, "%.1f", value)
        }

        val isLeEnabled = eqManager.isLoudnessEnhancerEnabled()
        val currentDb = (eqManager.getLoudnessEnhancerGainMb() / 100.0f).coerceIn(0.0f, 10.0f)

        binding.switchLoudnessEnhancer.isChecked = isLeEnabled
        binding.sliderLoudnessEnhancer.value = currentDb
        binding.sliderLoudnessEnhancer.isEnabled = isLeEnabled && eqManager.isEnabled()
        binding.tvLoudnessEnhancerValue.text = formatLoudnessGain(isLeEnabled, currentDb)

        binding.switchLoudnessEnhancer.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdatingUi) return@setOnCheckedChangeListener
            if (isChecked != eqManager.isLoudnessEnhancerEnabled()) {
                eqManager.setLoudnessEnhancerEnabled(isChecked)
                binding.sliderLoudnessEnhancer.isEnabled = isChecked && eqManager.isEnabled()
                binding.tvLoudnessEnhancerValue.text = formatLoudnessGain(isChecked, binding.sliderLoudnessEnhancer.value)
            }
        }

        binding.rowLoudnessEnhancer.setOnClickListener {
            binding.switchLoudnessEnhancer.toggle()
        }

        binding.sliderLoudnessEnhancer.addOnChangeListener { slider, value, fromUser ->
            if (fromUser) {
                slider.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                binding.tvLoudnessEnhancerValue.text = formatLoudnessGain(binding.switchLoudnessEnhancer.isChecked, value)
                val gainMb = (value * 100f).roundToInt().coerceIn(0, 1000)
                eqManager.setLoudnessEnhancerGainMb(gainMb)
            }
        }
    }

    private fun setupVolumeBoost() {
        binding.switchXloud.isChecked = eqManager.isXLoudEnabled()
        binding.switchXloud.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdatingUi) return@setOnCheckedChangeListener
            if (isChecked != eqManager.isXLoudEnabled()) {
                binding.switchXloud.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                eqManager.setXLoudEnabled(isChecked)
            }
        }
        binding.rowXloud.setOnClickListener {
            binding.switchXloud.toggle()
        }
    }

    private fun setupPlaybackEffects() {
        binding.sliderCrossfeed.apply {
            isTickVisible = false
            valueFrom = 0f
            valueTo = 100f
            stepSize = 5f
            value = eqManager.getCrossfeedAmount().toFloat()
            binding.tvCrossfeedValue.text = formatEffectAmount(value.toInt())
            addOnChangeListener { slider, value, fromUser ->
                if (fromUser) {
                    slider.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                    val amount = value.toInt()
                    binding.tvCrossfeedValue.text = formatEffectAmount(amount)
                    eqManager.setCrossfeedAmount(amount)
                }
            }
        }

        binding.sliderDynamicRange.apply {
            isTickVisible = false
            valueFrom = 0f
            valueTo = 100f
            stepSize = 5f
            value = eqManager.getDynamicRangeAmount().toFloat()
            binding.tvDynamicRangeValue.text = formatEffectAmount(value.toInt())
            addOnChangeListener { slider, value, fromUser ->
                if (fromUser) {
                    slider.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                    val amount = value.toInt()
                    binding.tvDynamicRangeValue.text = formatEffectAmount(amount)
                    eqManager.setDynamicRangeAmount(amount)
                }
            }
        }

        binding.switchMonoAudio.isChecked = eqManager.isMonoAudioEnabled()
        binding.switchMonoAudio.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdatingUi) return@setOnCheckedChangeListener
            if (isChecked != eqManager.isMonoAudioEnabled()) {
                binding.switchMonoAudio.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                eqManager.setMonoAudioEnabled(isChecked)
            }
        }
        binding.rowMonoAudio.setOnClickListener {
            binding.switchMonoAudio.toggle()
        }

        var prevBalance = eqManager.getStereoBalance()
        binding.sliderStereoBalance.apply {
            isTickVisible = false
            valueFrom = -100f
            valueTo = 100f
            stepSize = 1f
            value = prevBalance.toFloat()
            binding.tvStereoBalanceValue.text = formatBalance(prevBalance)
            addOnChangeListener { slider, value, fromUser ->
                if (fromUser) {
                    val balance = value.toInt()
                    if (balance != prevBalance) {
                        if (balance == 0) {
                            slider.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                        } else {
                            slider.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                        }
                        prevBalance = balance
                    }
                    binding.tvStereoBalanceValue.text = formatBalance(balance)
                    eqManager.setStereoBalance(balance)
                }
            }
        }
        setupBalanceSliderResetGesture()
    }

    private fun setupBalanceSliderResetGesture() {
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean = true

            override fun onDoubleTapEvent(e: MotionEvent): Boolean {
                if (e.actionMasked == MotionEvent.ACTION_UP) {
                    binding.sliderStereoBalance.post { resetBalanceToCenter() }
                }
                return true
            }
        })

        binding.sliderStereoBalance.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            false
        }
    }

    private fun resetBalanceToCenter() {
        binding.sliderStereoBalance.value = 0f
        binding.tvStereoBalanceValue.text = formatBalance(0)
        if (eqManager.getStereoBalance() != 0) {
            eqManager.setStereoBalance(0)
        }
    }

    private fun setupResetButton() {
        binding.btnResetDax.setOnClickListener {
            eqManager.resetEqToFlat()
            refreshUI()
        }
    }

    private fun updateIeqChipSelection(ieqPreset: Int) {
        val targetId = when (ieqPreset) {
            EqualizerManager.IEQ_BALANCED -> R.id.chip_ieq_balanced
            EqualizerManager.IEQ_WARM -> R.id.chip_ieq_warm
            EqualizerManager.IEQ_DETAILED -> R.id.chip_ieq_detailed
            else -> R.id.chip_ieq_off
        }
        binding.chipGroupIeq.check(targetId)
    }

    private fun refreshUI() {
        isUpdatingUi = true

        binding.toolbar.subtitle = eqManager.getHardwareDspStatus()

        val enabled = eqManager.isEnabled()
        binding.switchMaster.isChecked = enabled
        updateEnabledState(enabled)

        updatePresetSelection(eqManager.getCurrentPresetIndex())

        // Dax 10-Band Pillars Rack
        binding.daxEqBarsView.setBandGains(eqManager.getAllDaxBandLevels())

        // IEQ
        updateIeqChipSelection(eqManager.getIeqPreset())

        // ClearBass
        val cb = eqManager.getClearBassLevel().toFloat()
        binding.sliderClearBass.value = cb
        binding.tvClearBassValue.text = formatDb(cb.toInt())

        // Loudness Enhancer
        val isLeOn = eqManager.isLoudnessEnhancerEnabled()
        val leGainDb = (eqManager.getLoudnessEnhancerGainMb() / 100.0f).coerceIn(0.0f, 10.0f)
        binding.switchLoudnessEnhancer.isChecked = isLeOn
        binding.sliderLoudnessEnhancer.value = leGainDb
        binding.sliderLoudnessEnhancer.isEnabled = isLeOn && enabled
        binding.tvLoudnessEnhancerValue.text = formatLoudnessGain(isLeOn, leGainDb)

        // Volume Boost
        binding.switchXloud.isChecked = eqManager.isXLoudEnabled()

        val crossfeed = eqManager.getCrossfeedAmount()
        binding.sliderCrossfeed.value = crossfeed.toFloat()
        binding.tvCrossfeedValue.text = formatEffectAmount(crossfeed)

        val dynamicRange = eqManager.getDynamicRangeAmount()
        binding.sliderDynamicRange.value = dynamicRange.toFloat()
        binding.tvDynamicRangeValue.text = formatEffectAmount(dynamicRange)

        binding.switchMonoAudio.isChecked = eqManager.isMonoAudioEnabled()

        val balance = eqManager.getStereoBalance()
        binding.sliderStereoBalance.value = balance.toFloat()
        binding.tvStereoBalanceValue.text = formatBalance(balance)

        isUpdatingUi = false
    }

    private fun updateEnabledState(enabled: Boolean) {
        val targetAlpha = if (enabled) 1.0f else 0.40f

        val cards = listOf(
            binding.cardPresets,
            binding.cardDaxEqualizer,
            binding.cardIeq,
            binding.cardClearBass,
            binding.cardLoudnessEnhancer,
            binding.cardVolumeBoost,
            binding.cardPlaybackEffects
        )

        for (card in cards) {
            card.animate().alpha(targetAlpha).setDuration(200).start()
        }

        binding.chipGroupPresets.isEnabled = enabled
        for (i in 0 until binding.chipGroupPresets.childCount) {
            binding.chipGroupPresets.getChildAt(i).isEnabled = enabled
        }

        binding.daxEqBarsView.isEnabled = enabled
        binding.sliderClearBass.isEnabled = enabled
        binding.rowLoudnessEnhancer.isEnabled = enabled
        binding.switchLoudnessEnhancer.isEnabled = enabled
        binding.sliderLoudnessEnhancer.isEnabled = enabled && eqManager.isLoudnessEnhancerEnabled()
        binding.cardVolumeBoost.isEnabled = enabled
        binding.rowXloud.isEnabled = enabled
        binding.switchXloud.isEnabled = enabled
        binding.sliderCrossfeed.isEnabled = enabled
        binding.sliderDynamicRange.isEnabled = enabled
        binding.rowMonoAudio.isEnabled = enabled
        binding.switchMonoAudio.isEnabled = enabled
        binding.sliderStereoBalance.isEnabled = enabled
        binding.btnResetDax.isEnabled = enabled

        for (i in 0 until binding.chipGroupIeq.childCount) {
            binding.chipGroupIeq.getChildAt(i).isEnabled = enabled
        }
    }

    private fun formatDb(db: Int): String {
        return when {
            db > 0 -> "+$db dB"
            db < 0 -> "$db dB"
            else -> "0 dB"
        }
    }

    private fun formatLoudnessGain(enabled: Boolean, gainDb: Float): String {
        return if (!enabled) {
            getString(R.string.effect_off)
        } else {
            String.format(Locale.US, "%.1f dB", gainDb)
        }
    }

    private fun formatEffectAmount(amount: Int): String {
        return if (amount <= 0) getString(R.string.effect_off) else getString(R.string.effect_percent, amount)
    }

    private fun formatBalance(balance: Int): String {
        return when {
            balance < 0 -> getString(R.string.balance_left, -balance)
            balance > 0 -> getString(R.string.balance_right, balance)
            else -> getString(R.string.balance_center)
        }
    }
}
