package xyz.omniplay.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import xyz.omniplay.R
import xyz.omniplay.audio.EqualizerManager
import xyz.omniplay.databinding.BottomSheetEqualizerBinding

/**
 * Material You (Material 3 Expressive) Equalizer Bottom Sheet.
 *
 * Provides real-time 10-band DaxUI graphic equalizer controls,
 * Sony SoundEnhancement ClearBass™ slider, and VPT Surround Sound chip selector.
 * Non-dotted sliders for bands & ClearBass.
 */
class EqualizerBottomSheet : BottomSheetDialogFragment(), EqualizerManager.EqualizerListener {

    private var _binding: BottomSheetEqualizerBinding? = null
    private val binding get() = _binding!!

    private lateinit var eqManager: EqualizerManager

    private var isUpdatingUi = false

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetEqualizerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eqManager = EqualizerManager.getInstance(requireContext())
        eqManager.addListener(this)

        setupHeader()
        setupBandSliders()
        setupClearBass()
        setupPlaybackEffects()
        setupIeq()
        setupButtons()

        refreshUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eqManager.removeListener(this)
        _binding = null
    }

    override fun onEqualizerConfigChanged() {
        if (!isAdded || _binding == null) return
        activity?.runOnUiThread {
            refreshUI()
        }
    }

    private fun setupHeader() {
        binding.switchEqualizer.isChecked = eqManager.isEnabled()
        binding.switchEqualizer.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdatingUi) return@setOnCheckedChangeListener
            eqManager.setEnabled(isChecked)
            updateEnabledState(isChecked)
        }
    }

    private fun setupBandSliders() {
        binding.sheetEqBarsView.setBandGains(eqManager.getAllDaxBandLevels())
        binding.sheetEqBarsView.listener = object : DaxEqualizerBarsView.OnBandGainChangeListener {
            override fun onBandGainChanged(bandIndex: Int, gainDb: Int, fromUser: Boolean) {
                if (fromUser) {
                    eqManager.setDaxBandLevel(bandIndex, gainDb, fromUser = true)
                }
            }
        }
    }

    private fun setupClearBass() {
        binding.sliderBassBoost.isTickVisible = false
        binding.sliderBassBoost.valueFrom = -10f
        binding.sliderBassBoost.valueTo = 10f
        binding.sliderBassBoost.stepSize = 1f

        val currentCb = eqManager.getClearBassLevel().toFloat()
        binding.sliderBassBoost.value = currentCb
        binding.tvBassBoostValue.text = formatDb(currentCb.toInt())

        binding.sliderBassBoost.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val db = value.toInt()
                binding.tvBassBoostValue.text = formatDb(db)
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
    }

    private fun setupIeq() {
        updateIeqChipSelection(eqManager.getIeqPreset())

        binding.chipGroupIeqSheet.setOnCheckedStateChangeListener { _, checkedIds ->
            if (isUpdatingUi) return@setOnCheckedStateChangeListener
            val preset = when (checkedIds.firstOrNull()) {
                R.id.sheet_ieq_balanced -> EqualizerManager.IEQ_BALANCED
                R.id.sheet_ieq_warm -> EqualizerManager.IEQ_WARM
                R.id.sheet_ieq_detailed -> EqualizerManager.IEQ_DETAILED
                else -> EqualizerManager.IEQ_OFF
            }
            if (preset != eqManager.getIeqPreset()) {
                eqManager.setIeqPreset(preset)
            }
        }
    }

    private fun setupButtons() {
        binding.btnResetEqualizer.setOnClickListener {
            eqManager.resetEqToFlat()
            refreshUI()
        }
    }

    private fun updateIeqChipSelection(ieqPreset: Int) {
        val targetId = when (ieqPreset) {
            EqualizerManager.IEQ_BALANCED -> R.id.sheet_ieq_balanced
            EqualizerManager.IEQ_WARM -> R.id.sheet_ieq_warm
            EqualizerManager.IEQ_DETAILED -> R.id.sheet_ieq_detailed
            else -> R.id.sheet_ieq_off
        }
        binding.chipGroupIeqSheet.check(targetId)

        val labelRes = when (ieqPreset) {
            EqualizerManager.IEQ_BALANCED -> R.string.ieq_balanced
            EqualizerManager.IEQ_WARM -> R.string.ieq_warm
            EqualizerManager.IEQ_DETAILED -> R.string.ieq_detailed
            else -> R.string.ieq_off
        }
        binding.tvIeqValue.setText(labelRes)
    }

    private fun refreshUI() {
        if (_binding == null) return
        isUpdatingUi = true

        val enabled = eqManager.isEnabled()
        binding.switchEqualizer.isChecked = enabled
        updateEnabledState(enabled)

        binding.sheetEqBarsView.setBandGains(eqManager.getAllDaxBandLevels())

        val cb = eqManager.getClearBassLevel().toFloat()
        binding.sliderBassBoost.value = cb
        binding.tvBassBoostValue.text = formatDb(cb.toInt())

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

        updateIeqChipSelection(eqManager.getIeqPreset())

        isUpdatingUi = false
    }

    private fun updateEnabledState(enabled: Boolean) {
        val alpha = if (enabled) 1.0f else 0.45f

        binding.cardEqualizerBands.alpha = alpha
        binding.cardSubBass.alpha = alpha
        binding.cardEnhancements.alpha = alpha
        binding.btnResetEqualizer.isEnabled = enabled

        binding.sheetEqBarsView.isEnabled = enabled
        binding.sliderBassBoost.isEnabled = enabled
        binding.sliderCrossfeed.isEnabled = enabled
        binding.sliderDynamicRange.isEnabled = enabled
        binding.rowMonoAudio.isEnabled = enabled
        binding.switchMonoAudio.isEnabled = enabled
        binding.sliderStereoBalance.isEnabled = enabled

        for (i in 0 until binding.chipGroupIeqSheet.childCount) {
            binding.chipGroupIeqSheet.getChildAt(i).isEnabled = enabled
        }
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

    companion object {
        const val TAG = "EqualizerBottomSheet"

        fun show(fragmentManager: androidx.fragment.app.FragmentManager) {
            val existing = fragmentManager.findFragmentByTag(TAG)
            if (existing == null) {
                EqualizerBottomSheet().show(fragmentManager, TAG)
            }
        }
    }
}
