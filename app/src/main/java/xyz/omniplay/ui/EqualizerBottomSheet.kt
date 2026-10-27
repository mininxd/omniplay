package xyz.omniplay.ui

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
 * Provides real-time 10-band DaxUI graphic equalizer and Sub-Bass controls.
 * Non-dotted sliders for bands and Sub-Bass.
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
        setupButtons()

        refreshUI()
        eqManager.requestOmniQualizerSync()
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

    private fun setupButtons() {
        binding.btnResetEqualizer.setOnClickListener {
            eqManager.resetEqToFlat()
            refreshUI()
        }
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

        isUpdatingUi = false
    }

    private fun updateEnabledState(enabled: Boolean) {
        val alpha = if (enabled) 1.0f else 0.45f

        binding.cardEqualizerBands.alpha = alpha
        binding.cardSubBass.alpha = alpha
        binding.btnResetEqualizer.isEnabled = enabled

        binding.sheetEqBarsView.isEnabled = enabled
        binding.sliderBassBoost.isEnabled = enabled
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
