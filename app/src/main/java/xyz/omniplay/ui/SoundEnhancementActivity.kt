package xyz.omniplay.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.slider.Slider
import xyz.omniplay.R
import xyz.omniplay.audio.EqualizerManager
import xyz.omniplay.databinding.ActivitySoundEnhancementBinding
import xyz.omniplay.databinding.ItemEqualizerBandBinding
import xyz.omniplay.util.ThemeHelper

/**
 * Material 3 Expressive "Equalizer and Sound Enhancement" nested settings screen.
 *
 * Fully implements the reverse-engineered DSP features:
 * - Dolby Atmos (DaxUI): 10-band Graphic Equalizer & Profile Presets (Dynamic, Movie, Music, Voice, Flat, Custom).
 * - Sony Xperia (SoundEnhancement): ClearBass™, VPT Surround Sound (Studio, Club, Concert Hall),
 *   ClearAudio+, Clear Phase™, xLOUD™, and S-Force Front Surround 3D.
 * - All sliders are smooth and non-dotted (tickVisible = false).
 */
class SoundEnhancementActivity : AppCompatActivity(), EqualizerManager.EqualizerListener {

    private lateinit var binding: ActivitySoundEnhancementBinding
    private lateinit var eqManager: EqualizerManager

    private val bandSliders = mutableListOf<Slider>()
    private val bandGainTexts = mutableListOf<TextView>()

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
        setupDaxPresets()
        setupDaxBands()
        setupClearBass()
        setupVptSurround()
        setupSonySuite()
        setupResetButton()

        refreshUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        eqManager.removeListener(this)
        bandSliders.clear()
        bandGainTexts.clear()
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

    private fun setupDaxPresets() {
        binding.chipGroupDaxPresets.removeAllViews()

        for (preset in eqManager.daxPresets) {
            val chip = Chip(this).apply {
                id = preset.id
                text = getString(preset.nameRes)
                isCheckable = true
                isChecked = (preset.id == eqManager.getCurrentDaxPresetId())
            }
            binding.chipGroupDaxPresets.addView(chip)
        }

        binding.chipGroupDaxPresets.setOnCheckedStateChangeListener { _, checkedIds ->
            if (isUpdatingUi) return@setOnCheckedStateChangeListener
            if (checkedIds.isNotEmpty()) {
                val selectedId = checkedIds.first()
                if (selectedId != eqManager.getCurrentDaxPresetId()) {
                    eqManager.applyDaxPreset(selectedId)
                }
            }
        }
    }

    private fun setupDaxBands() {
        binding.layoutDaxBandsContainer.removeAllViews()
        bandSliders.clear()
        bandGainTexts.clear()

        for (i in 0 until 10) {
            val bandBinding = ItemEqualizerBandBinding.inflate(
                layoutInflater,
                binding.layoutDaxBandsContainer,
                false
            )

            val freqHz = eqManager.daxFrequenciesHz[i]
            bandBinding.tvBandFrequency.text = eqManager.formatFrequency(freqHz)

            // Ensure no tick dots are visible
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
                    selectPresetChipSilently(EqualizerManager.PRESET_CUSTOM)
                }
            }

            bandSliders.add(bandBinding.sliderBandGain)
            bandGainTexts.add(bandBinding.tvBandGain)
            binding.layoutDaxBandsContainer.addView(bandBinding.root)
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
        // ClearAudio+
        binding.switchClearAudioPlus.isChecked = eqManager.isClearAudioPlusEnabled()
        binding.rowClearAudioPlus.setOnClickListener {
            val newState = !binding.switchClearAudioPlus.isChecked
            binding.switchClearAudioPlus.isChecked = newState
            eqManager.setClearAudioPlusEnabled(newState)
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
            eqManager.resetAllToFlat()
            refreshUI()
        }
    }

    private fun selectPresetChipSilently(presetId: Int) {
        isUpdatingUi = true
        binding.chipGroupDaxPresets.check(presetId)
        isUpdatingUi = false
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

        val enabled = eqManager.isEnabled()
        binding.switchMaster.isChecked = enabled
        updateEnabledState(enabled)

        // Dax Presets
        binding.chipGroupDaxPresets.check(eqManager.getCurrentDaxPresetId())

        // Dax 10-Band Sliders
        for (i in 0 until bandSliders.size) {
            val db = eqManager.getDaxBandLevel(i).toFloat()
            bandSliders[i].value = db
            bandGainTexts[i].text = formatDb(db.toInt())
        }

        // ClearBass
        val cb = eqManager.getClearBassLevel().toFloat()
        binding.sliderClearBass.value = cb
        binding.tvClearBassValue.text = formatDb(cb.toInt())

        // VPT
        updateVptChipSelection(eqManager.getVptMode())

        // Sony Suite
        binding.switchClearAudioPlus.isChecked = eqManager.isClearAudioPlusEnabled()
        binding.switchClearPhase.isChecked = eqManager.isClearPhaseEnabled()
        binding.switchXloud.isChecked = eqManager.isXLoudEnabled()
        binding.switchSforce.isChecked = eqManager.isSForceEnabled()

        isUpdatingUi = false
    }

    private fun updateEnabledState(enabled: Boolean) {
        val alpha = if (enabled) 1.0f else 0.45f

        binding.cardDaxEqualizer.alpha = alpha
        binding.cardClearBass.alpha = alpha
        binding.cardVpt.alpha = alpha
        binding.cardSonySuite.alpha = alpha

        for (slider in bandSliders) {
            slider.isEnabled = enabled
        }
        binding.sliderClearBass.isEnabled = enabled
        binding.btnResetDax.isEnabled = enabled

        for (i in 0 until binding.chipGroupDaxPresets.childCount) {
            binding.chipGroupDaxPresets.getChildAt(i).isEnabled = enabled
        }
        for (i in 0 until binding.chipGroupVpt.childCount) {
            binding.chipGroupVpt.getChildAt(i).isEnabled = enabled
        }

        binding.rowClearAudioPlus.isEnabled = enabled
        binding.switchClearAudioPlus.isEnabled = enabled

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
}
