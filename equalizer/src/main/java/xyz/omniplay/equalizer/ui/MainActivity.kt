package xyz.omniplay.equalizer.ui

import android.content.Intent
import android.media.audiofx.AudioEffect
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import xyz.omniplay.equalizer.R
import xyz.omniplay.equalizer.audio.EqualizerManager
import xyz.omniplay.equalizer.databinding.ActivityMainBinding
import xyz.omniplay.equalizer.util.ThemeHelper
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

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eqManager = EqualizerManager.getInstance(this)
        eqManager.addListener(this)

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
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleAudioSessionIntent(intent)
    }

    private fun handleAudioSessionIntent(intent: Intent?) {
        val sessionId = intent?.getIntExtra(AudioEffect.EXTRA_AUDIO_SESSION, AudioEffect.ERROR) ?: AudioEffect.ERROR
        val packageName = intent?.getStringExtra(AudioEffect.EXTRA_PACKAGE_NAME)
        if (sessionId != AudioEffect.ERROR && sessionId > 0) {
            eqManager.openAudioSession(sessionId, packageName)
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
    }

    private fun setupMasterSwitch() {
        binding.switchMaster.isChecked = eqManager.isEnabled()

        binding.rowMasterToggle.setOnClickListener {
            val newState = !binding.switchMaster.isChecked
            binding.switchMaster.isChecked = newState
            eqManager.setEnabled(newState)
            updateEnabledState(newState)
        }
    }

    private fun setupPresets() {
        binding.chipGroupPresets.removeAllViews()
        for ((index, preset) in eqManager.presets.withIndex()) {
            val chip = Chip(this).apply {
                id = index + 1000
                text = preset.name
                isCheckable = true
                setOnClickListener {
                    if (isUpdatingUi) return@setOnClickListener
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

        binding.sliderClearBass.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val db = value.toInt()
                binding.tvClearBassValue.text = formatDb(db)
                eqManager.setClearBassLevel(db)
            }
        }
    }

    private fun setupLoudnessEnhancer() {
        binding.sliderLoudnessEnhancer.isTickVisible = false
        binding.sliderLoudnessEnhancer.valueFrom = 0f
        binding.sliderLoudnessEnhancer.valueTo = 1000f
        binding.sliderLoudnessEnhancer.stepSize = 10f
        binding.sliderLoudnessEnhancer.setLabelFormatter { value ->
            value.toInt().toString()
        }

        val isLeEnabled = eqManager.isLoudnessEnhancerEnabled()
        val currentMb = eqManager.getLoudnessEnhancerGainMb().toFloat()

        binding.switchLoudnessEnhancer.isChecked = isLeEnabled
        binding.sliderLoudnessEnhancer.value = currentMb
        binding.sliderLoudnessEnhancer.isEnabled = isLeEnabled && eqManager.isEnabled()
        binding.tvLoudnessEnhancerValue.text = formatLoudnessGain(isLeEnabled, currentMb.toInt())

        binding.rowLoudnessEnhancer.setOnClickListener {
            val newState = !binding.switchLoudnessEnhancer.isChecked
            binding.switchLoudnessEnhancer.isChecked = newState
            eqManager.setLoudnessEnhancerEnabled(newState)
            binding.sliderLoudnessEnhancer.isEnabled = newState && eqManager.isEnabled()
            binding.tvLoudnessEnhancerValue.text = formatLoudnessGain(newState, eqManager.getLoudnessEnhancerGainMb())
        }

        binding.sliderLoudnessEnhancer.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val mb = value.toInt()
                binding.tvLoudnessEnhancerValue.text = formatLoudnessGain(binding.switchLoudnessEnhancer.isChecked, mb)
                eqManager.setLoudnessEnhancerGainMb(mb)
            }
        }
    }

    private fun setupVolumeBoost() {
        binding.switchXloud.isChecked = eqManager.isXLoudEnabled()
        binding.rowXloud.setOnClickListener {
            val newState = !binding.switchXloud.isChecked
            binding.switchXloud.isChecked = newState
            eqManager.setXLoudEnabled(newState)
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
            addOnChangeListener { _, value, fromUser ->
                if (fromUser) {
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
            addOnChangeListener { _, value, fromUser ->
                if (fromUser) {
                    val amount = value.toInt()
                    binding.tvDynamicRangeValue.text = formatEffectAmount(amount)
                    eqManager.setDynamicRangeAmount(amount)
                }
            }
        }

        binding.switchMonoAudio.isChecked = eqManager.isMonoAudioEnabled()
        binding.rowMonoAudio.setOnClickListener {
            val enabled = !binding.switchMonoAudio.isChecked
            binding.switchMonoAudio.isChecked = enabled
            eqManager.setMonoAudioEnabled(enabled)
        }

        binding.sliderStereoBalance.apply {
            isTickVisible = false
            valueFrom = -100f
            valueTo = 100f
            stepSize = 1f
            value = eqManager.getStereoBalance().toFloat()
            binding.tvStereoBalanceValue.text = formatBalance(value.toInt())
            addOnChangeListener { _, value, fromUser ->
                if (fromUser) {
                    val balance = value.toInt()
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
        val leGain = eqManager.getLoudnessEnhancerGainMb().toFloat()
        binding.switchLoudnessEnhancer.isChecked = isLeOn
        binding.sliderLoudnessEnhancer.value = leGain
        binding.sliderLoudnessEnhancer.isEnabled = isLeOn && enabled
        binding.tvLoudnessEnhancerValue.text = formatLoudnessGain(isLeOn, leGain.toInt())

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
        val alpha = if (enabled) 1.0f else 0.45f

        binding.cardPresets.alpha = alpha
        binding.cardDaxEqualizer.alpha = alpha
        binding.cardIeq.alpha = alpha
        binding.cardClearBass.alpha = alpha
        binding.cardLoudnessEnhancer.alpha = alpha
        binding.cardVolumeBoost.alpha = alpha
        binding.cardPlaybackEffects.alpha = alpha

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

    private fun formatLoudnessGain(enabled: Boolean, gainMb: Int): String {
        return if (!enabled) {
            getString(R.string.effect_off)
        } else {
            gainMb.toString()
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
