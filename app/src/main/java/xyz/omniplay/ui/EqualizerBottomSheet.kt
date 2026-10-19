package xyz.omniplay.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.color.DynamicColors
import com.google.android.material.slider.Slider
import xyz.omniplay.R
import xyz.omniplay.audio.EqualizerManager
import xyz.omniplay.databinding.BottomSheetEqualizerBinding
import xyz.omniplay.databinding.ItemEqualizerBandBinding
import xyz.omniplay.util.ThemeHelper

/**
 * Material You (Material 3) Equalizer Bottom Sheet.
 *
 * Provides real-time frequency band controls, audio presets,
 * ClearBass bass boost, and surround sound spatialization.
 */
class EqualizerBottomSheet : BottomSheetDialogFragment(), EqualizerManager.EqualizerListener {

    private var _binding: BottomSheetEqualizerBinding? = null
    private val binding get() = _binding!!

    private lateinit var eqManager: EqualizerManager
    private val bandSliders = mutableListOf<Slider>()
    private val bandGainTexts = mutableListOf<TextView>()

    private var isUpdatingUi = false

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (DynamicColors.isDynamicColorAvailable()) {
            val style = ThemeHelper.getThemeStyle(requireContext())
            if (style.themeOverlayRes != 0) {
                dialog.context.theme.applyStyle(style.themeOverlayRes, true)
            }
        }
        return dialog
    }

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
        setupPresetsChips()
        setupBandSliders()
        setupEnhancementSliders()
        setupResetButton()

        updateEnabledState(eqManager.isEnabled())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eqManager.removeListener(this)
        bandSliders.clear()
        bandGainTexts.clear()
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
            eqManager.setEnabled(isChecked)
            updateEnabledState(isChecked)
        }
    }

    private fun setupPresetsChips() {
        binding.chipGroupPresets.removeAllViews()

        for (preset in eqManager.presets) {
            val chip = Chip(requireContext()).apply {
                id = preset.id
                text = getString(preset.nameRes)
                isCheckable = true
                isChecked = (preset.id == eqManager.getCurrentPresetId())
            }
            binding.chipGroupPresets.addView(chip)
        }

        binding.chipGroupPresets.setOnCheckedStateChangeListener { _, checkedIds ->
            if (isUpdatingUi) return@setOnCheckedStateChangeListener
            if (checkedIds.isNotEmpty()) {
                val selectedId = checkedIds.first()
                if (selectedId != eqManager.getCurrentPresetId()) {
                    eqManager.applyPreset(selectedId)
                }
            }
        }
    }

    private fun setupBandSliders() {
        binding.layoutBandsContainer.removeAllViews()
        bandSliders.clear()
        bandGainTexts.clear()

        val bandCount = eqManager.getBandCount()
        val range = eqManager.getBandLevelRange()
        val minDb = (range.first / 100).toFloat()
        val maxDb = (range.second / 100).toFloat()

        for (i in 0 until bandCount) {
            val bandBinding = ItemEqualizerBandBinding.inflate(
                layoutInflater,
                binding.layoutBandsContainer,
                false
            )

            val freqHz = eqManager.getBandCenterFreqHz(i)
            bandBinding.tvBandFrequency.text = eqManager.formatFrequency(freqHz)

            val currentDb = (eqManager.getBandLevel(i) / 100).toFloat().coerceIn(minDb, maxDb)
            bandBinding.sliderBandGain.valueFrom = minDb
            bandBinding.sliderBandGain.valueTo = maxDb
            bandBinding.sliderBandGain.value = currentDb
            bandBinding.tvBandGain.text = formatDb(currentDb.toInt())

            val bandIndex = i
            bandBinding.sliderBandGain.addOnChangeListener { _, value, fromUser ->
                if (fromUser) {
                    val db = value.toInt()
                    bandBinding.tvBandGain.text = formatDb(db)
                    eqManager.setBandLevel(bandIndex, (db * 100).toShort(), fromUser = true)
                    selectPresetChipSilently(EqualizerManager.PRESET_CUSTOM)
                }
            }

            bandSliders.add(bandBinding.sliderBandGain)
            bandGainTexts.add(bandBinding.tvBandGain)
            binding.layoutBandsContainer.addView(bandBinding.root)
        }
    }

    private fun setupEnhancementSliders() {
        // Bass Boost
        val bassBoostPct = (eqManager.getBassBoostStrength() / 10).toFloat().coerceIn(0f, 100f)
        binding.sliderBassBoost.value = bassBoostPct
        binding.tvBassBoostValue.text = "${bassBoostPct.toInt()}%"
        binding.sliderBassBoost.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val pct = value.toInt()
                binding.tvBassBoostValue.text = "$pct%"
                eqManager.setBassBoostStrength((pct * 10).toShort())
            }
        }

        // Virtualizer
        val virtualizerPct = (eqManager.getVirtualizerStrength() / 10).toFloat().coerceIn(0f, 100f)
        binding.sliderVirtualizer.value = virtualizerPct
        binding.tvVirtualizerValue.text = "${virtualizerPct.toInt()}%"
        binding.sliderVirtualizer.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val pct = value.toInt()
                binding.tvVirtualizerValue.text = "$pct%"
                eqManager.setVirtualizerStrength((pct * 10).toShort())
            }
        }
    }

    private fun setupResetButton() {
        binding.btnResetEqualizer.setOnClickListener {
            eqManager.resetToFlat()
        }
    }

    private fun selectPresetChipSilently(presetId: Int) {
        isUpdatingUi = true
        binding.chipGroupPresets.check(presetId)
        isUpdatingUi = false
    }

    private fun refreshUI() {
        if (_binding == null) return
        isUpdatingUi = true

        binding.switchEqualizer.isChecked = eqManager.isEnabled()
        updateEnabledState(eqManager.isEnabled())

        binding.chipGroupPresets.check(eqManager.getCurrentPresetId())

        val range = eqManager.getBandLevelRange()
        val minDb = (range.first / 100).toFloat()
        val maxDb = (range.second / 100).toFloat()

        for (i in 0 until bandSliders.size) {
            val levelDb = (eqManager.getBandLevel(i) / 100).toFloat().coerceIn(minDb, maxDb)
            bandSliders[i].value = levelDb
            bandGainTexts[i].text = formatDb(levelDb.toInt())
        }

        val bassBoostPct = (eqManager.getBassBoostStrength() / 10).toFloat().coerceIn(0f, 100f)
        binding.sliderBassBoost.value = bassBoostPct
        binding.tvBassBoostValue.text = "${bassBoostPct.toInt()}%"

        val virtualizerPct = (eqManager.getVirtualizerStrength() / 10).toFloat().coerceIn(0f, 100f)
        binding.sliderVirtualizer.value = virtualizerPct
        binding.tvVirtualizerValue.text = "${virtualizerPct.toInt()}%"

        isUpdatingUi = false
    }

    private fun updateEnabledState(enabled: Boolean) {
        val alpha = if (enabled) 1.0f else 0.45f

        binding.cardEqualizerBands.alpha = alpha
        binding.cardEnhancements.alpha = alpha
        binding.btnResetEqualizer.isEnabled = enabled

        for (slider in bandSliders) {
            slider.isEnabled = enabled
        }
        binding.sliderBassBoost.isEnabled = enabled
        binding.sliderVirtualizer.isEnabled = enabled

        for (i in 0 until binding.chipGroupPresets.childCount) {
            binding.chipGroupPresets.getChildAt(i).isEnabled = enabled
        }
    }

    private fun formatDb(db: Int): String {
        return when {
            db > 0 -> "+$db dB"
            db < 0 -> "$db dB"
            else -> "0 dB"
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
