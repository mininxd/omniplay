package xyz.omniplay.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.color.DynamicColors
import com.google.android.material.slider.Slider
import xyz.omniplay.R
import xyz.omniplay.audio.EqualizerManager
import xyz.omniplay.databinding.BottomSheetEqualizerBinding
import xyz.omniplay.databinding.ItemEqualizerBandBinding
import xyz.omniplay.util.ThemeHelper

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
        setupBandSliders()
        setupClearBass()
        setupVptSurround()
        setupButtons()

        refreshUI()
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

    private fun setupBandSliders() {
        binding.layoutBandsContainer.removeAllViews()
        bandSliders.clear()
        bandGainTexts.clear()

        for (i in 0 until 10) {
            val bandBinding = ItemEqualizerBandBinding.inflate(
                layoutInflater,
                binding.layoutBandsContainer,
                false
            )

            val freqHz = eqManager.daxFrequenciesHz[i]
            bandBinding.tvBandFrequency.text = eqManager.formatFrequency(freqHz)

            // Strictly non-dotted slider
            bandBinding.sliderBandGain.isTickVisible = false
            bandBinding.sliderBandGain.valueFrom = -12f
            bandBinding.sliderBandGain.valueTo = 12f
            bandBinding.sliderBandGain.stepSize = 1f

            val currentDb = eqManager.getDaxBandLevel(i).toFloat()
            bandBinding.sliderBandGain.value = currentDb
            bandBinding.tvBandGain.text = formatDb(currentDb.toInt())

            val bandIndex = i
            bandBinding.sliderBandGain.addOnChangeListener { _, value, fromUser ->
                if (fromUser) {
                    val db = value.toInt()
                    bandBinding.tvBandGain.text = formatDb(db)
                    eqManager.setDaxBandLevel(bandIndex, db, fromUser = true)
                }
            }

            bandSliders.add(bandBinding.sliderBandGain)
            bandGainTexts.add(bandBinding.tvBandGain)
            binding.layoutBandsContainer.addView(bandBinding.root)
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

    private fun setupVptSurround() {
        updateVptChipSelection(eqManager.getVptMode())

        binding.chipGroupVptSheet.setOnCheckedStateChangeListener { _, checkedIds ->
            if (isUpdatingUi) return@setOnCheckedStateChangeListener
            val mode = when (checkedIds.firstOrNull()) {
                R.id.sheet_vpt_studio -> EqualizerManager.VPT_STUDIO
                R.id.sheet_vpt_club -> EqualizerManager.VPT_CLUB
                R.id.sheet_vpt_concert -> EqualizerManager.VPT_CONCERT_HALL
                else -> EqualizerManager.VPT_OFF
            }
            if (mode != eqManager.getVptMode()) {
                eqManager.setVptMode(mode)
            }
        }
    }

    private fun setupButtons() {
        binding.btnResetEqualizer.setOnClickListener {
            eqManager.resetAllToFlat()
            refreshUI()
        }

        binding.btnOpenFullSuite.setOnClickListener {
            dismiss()
            val intent = Intent(requireContext(), SoundEnhancementActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateVptChipSelection(vptMode: Int) {
        val targetId = when (vptMode) {
            EqualizerManager.VPT_STUDIO -> R.id.sheet_vpt_studio
            EqualizerManager.VPT_CLUB -> R.id.sheet_vpt_club
            EqualizerManager.VPT_CONCERT_HALL -> R.id.sheet_vpt_concert
            else -> R.id.sheet_vpt_off
        }
        binding.chipGroupVptSheet.check(targetId)

        val labelRes = when (vptMode) {
            EqualizerManager.VPT_STUDIO -> R.string.vpt_studio
            EqualizerManager.VPT_CLUB -> R.string.vpt_club
            EqualizerManager.VPT_CONCERT_HALL -> R.string.vpt_concert_hall
            else -> R.string.vpt_off
        }
        binding.tvVirtualizerValue.setText(labelRes)
    }

    private fun refreshUI() {
        if (_binding == null) return
        isUpdatingUi = true

        val enabled = eqManager.isEnabled()
        binding.switchEqualizer.isChecked = enabled
        updateEnabledState(enabled)

        for (i in 0 until bandSliders.size) {
            val db = eqManager.getDaxBandLevel(i).toFloat()
            bandSliders[i].value = db
            bandGainTexts[i].text = formatDb(db.toInt())
        }

        val cb = eqManager.getClearBassLevel().toFloat()
        binding.sliderBassBoost.value = cb
        binding.tvBassBoostValue.text = formatDb(cb.toInt())

        updateVptChipSelection(eqManager.getVptMode())

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

        for (i in 0 until binding.chipGroupVptSheet.childCount) {
            binding.chipGroupVptSheet.getChildAt(i).isEnabled = enabled
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
