package xyz.omniplay.ui

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import xyz.omniplay.R
import xyz.omniplay.audio.EqualizerManager
import xyz.omniplay.databinding.ActivitySoundEnhancementBinding
import xyz.omniplay.util.ThemeHelper

/**
 * Material 3 Expressive "Equalizer and Sound Enhancement" nested settings screen.
 *
 * Features:
 * - 10-band Graphic Equalizer, Intelligent Equalizer (IEQ), Dialogue Enhancer.
 * - Sub-Bass, VPT Surround Sound, Automatic Optimisation, Clear Phase, xLOUD, and Front Surround 3D.
 * - Non-dotted sliders for high steps (bands & Sub-Bass), dotted sliders for lower steps (Dialogue Enhancer).
 */
class SoundEnhancementActivity : AppCompatActivity(), EqualizerManager.EqualizerListener {

    private lateinit var binding: ActivitySoundEnhancementBinding
    private lateinit var eqManager: EqualizerManager

    private var isUpdatingUi = false

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySoundEnhancementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eqManager = EqualizerManager.getInstance(this)
        eqManager.addListener(this)

        setupToolbar()
        setupMasterSwitch()
        setupDaxBands()
        setupIeq()
        setupDialogueEnhancer()
        setupClearBass()
        setupPlaybackEffects()
        setupVptSurround()
        setupSonySuite()
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

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.toolbar.subtitle = eqManager.getHardwareDspStatus()
    }

    private fun setupMasterSwitch() {
        if (!eqManager.isEnabled()) {
            eqManager.setEnabled(true)
        }
        binding.switchMaster.isChecked = eqManager.isEnabled()

        binding.rowMasterToggle.setOnClickListener {
            val newState = !binding.switchMaster.isChecked
            binding.switchMaster.isChecked = newState
            eqManager.setEnabled(newState)
            updateEnabledState(newState)
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
                eqManager.setIeqPreset(preset)
            }
        }
    }

    private fun setupDialogueEnhancer() {
        // Dotted slider on lower steps (10 steps)
        binding.sliderDialogueEnhancer.isTickVisible = true
        binding.sliderDialogueEnhancer.valueFrom = 0f
        binding.sliderDialogueEnhancer.valueTo = 10f
        binding.sliderDialogueEnhancer.stepSize = 1f

        val currentDe = eqManager.getDialogEnhancerAmount().toFloat()
        binding.sliderDialogueEnhancer.value = currentDe
        binding.tvDialogueEnhancerValue.text = if (currentDe.toInt() == 0) "Off" else "Level ${currentDe.toInt()}"

        binding.sliderDialogueEnhancer.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val amount = value.toInt()
                binding.tvDialogueEnhancerValue.text = if (amount == 0) "Off" else "Level $amount"
                eqManager.setDialogEnhancerAmount(amount)
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

    private fun setupVptSurround() {
        updateVptChipSelection(eqManager.getVptMode())

        binding.chipGroupVpt.setOnCheckedStateChangeListener { _, checkedIds ->
            if (isUpdatingUi) return@setOnCheckedStateChangeListener
            val mode = when (checkedIds.firstOrNull()) {
                R.id.chip_vpt_studio -> EqualizerManager.VPT_STUDIO
                R.id.chip_vpt_club -> EqualizerManager.VPT_CLUB
                R.id.chip_vpt_concert_hall -> EqualizerManager.VPT_CONCERT_HALL
                else -> EqualizerManager.VPT_OFF
            }
            if (mode != eqManager.getVptMode()) {
                eqManager.setVptMode(mode)
            }
        }
    }

    private fun setupSonySuite() {
        // Automatic Optimisation
        binding.switchAutomaticOptimization.isChecked = eqManager.isAutomaticOptimizationEnabled()
        binding.rowAutomaticOptimization.setOnClickListener {
            val newState = !binding.switchAutomaticOptimization.isChecked
            binding.switchAutomaticOptimization.isChecked = newState
            eqManager.setAutomaticOptimizationEnabled(newState)
        }

        // Clear Phase
        binding.switchClearPhase.isChecked = eqManager.isClearPhaseEnabled()
        binding.rowClearPhase.setOnClickListener {
            val newState = !binding.switchClearPhase.isChecked
            binding.switchClearPhase.isChecked = newState
            eqManager.setClearPhaseEnabled(newState)
        }

        // xLOUD
        binding.switchXloud.isChecked = eqManager.isXLoudEnabled()
        binding.rowXloud.setOnClickListener {
            val newState = !binding.switchXloud.isChecked
            binding.switchXloud.isChecked = newState
            eqManager.setXLoudEnabled(newState)
        }

        // S-Force Front Surround
        binding.switchSforce.isChecked = eqManager.isSForceEnabled()
        binding.rowSforce.setOnClickListener {
            val newState = !binding.switchSforce.isChecked
            binding.switchSforce.isChecked = newState
            eqManager.setSForceEnabled(newState)
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

    private fun updateVptChipSelection(vptMode: Int) {
        val targetId = when (vptMode) {
            EqualizerManager.VPT_STUDIO -> R.id.chip_vpt_studio
            EqualizerManager.VPT_CLUB -> R.id.chip_vpt_club
            EqualizerManager.VPT_CONCERT_HALL -> R.id.chip_vpt_concert_hall
            else -> R.id.chip_vpt_off
        }
        binding.chipGroupVpt.check(targetId)
    }

    private fun refreshUI() {
        isUpdatingUi = true

        binding.toolbar.subtitle = eqManager.getHardwareDspStatus()

        val enabled = eqManager.isEnabled()
        binding.switchMaster.isChecked = enabled
        updateEnabledState(enabled)

        // Dax 10-Band Pillars Rack
        binding.daxEqBarsView.setBandGains(eqManager.getAllDaxBandLevels())

        // IEQ
        updateIeqChipSelection(eqManager.getIeqPreset())

        // Dialogue Enhancer
        val de = eqManager.getDialogEnhancerAmount().toFloat()
        binding.sliderDialogueEnhancer.value = de
        binding.tvDialogueEnhancerValue.text = if (de.toInt() == 0) "Off" else "Level ${de.toInt()}"

        // ClearBass
        val cb = eqManager.getClearBassLevel().toFloat()
        binding.sliderClearBass.value = cb
        binding.tvClearBassValue.text = formatDb(cb.toInt())

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

        // VPT
        updateVptChipSelection(eqManager.getVptMode())

        // Sony Suite
        binding.switchAutomaticOptimization.isChecked = eqManager.isAutomaticOptimizationEnabled()
        binding.switchClearPhase.isChecked = eqManager.isClearPhaseEnabled()
        binding.switchXloud.isChecked = eqManager.isXLoudEnabled()
        binding.switchSforce.isChecked = eqManager.isSForceEnabled()

        isUpdatingUi = false
    }

    private fun updateEnabledState(enabled: Boolean) {
        val alpha = if (enabled) 1.0f else 0.45f

        binding.cardDaxEqualizer.alpha = alpha
        binding.cardIeq.alpha = alpha
        binding.cardDialogueEnhancer.alpha = alpha
        binding.cardClearBass.alpha = alpha
        binding.cardPlaybackEffects.alpha = alpha
        binding.cardVpt.alpha = alpha
        binding.cardSonySuite.alpha = alpha

        binding.daxEqBarsView.isEnabled = enabled
        binding.sliderDialogueEnhancer.isEnabled = enabled
        binding.sliderClearBass.isEnabled = enabled
        binding.sliderCrossfeed.isEnabled = enabled
        binding.sliderDynamicRange.isEnabled = enabled
        binding.rowMonoAudio.isEnabled = enabled
        binding.switchMonoAudio.isEnabled = enabled
        binding.sliderStereoBalance.isEnabled = enabled
        binding.btnResetDax.isEnabled = enabled

        for (i in 0 until binding.chipGroupIeq.childCount) {
            binding.chipGroupIeq.getChildAt(i).isEnabled = enabled
        }
        for (i in 0 until binding.chipGroupVpt.childCount) {
            binding.chipGroupVpt.getChildAt(i).isEnabled = enabled
        }

        binding.rowAutomaticOptimization.isEnabled = enabled
        binding.switchAutomaticOptimization.isEnabled = enabled

        binding.rowClearPhase.isEnabled = enabled
        binding.switchClearPhase.isEnabled = enabled

        binding.rowXloud.isEnabled = enabled
        binding.switchXloud.isEnabled = enabled

        binding.rowSforce.isEnabled = enabled
        binding.switchSforce.isEnabled = enabled
    }

    private fun formatDb(db: Int): String {
        return when {
            db > 0 -> "+$db dB"
            db < 0 -> "$db dB"
            else -> "0 dB"
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
