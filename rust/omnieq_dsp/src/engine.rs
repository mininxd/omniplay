use crate::balance::StereoBalance;
use crate::clear_bass::ClearBass;
use crate::compressor::DynamicRangeCompressor;
use crate::crossfeed::Crossfeed;
use crate::equalizer::{GraphicEqualizer, EQ_BAND_COUNT};
use dasp::Sample;

pub struct DspEngine {
    sample_rate: f64,
    channels: usize,
    enabled: bool,
    mono_audio: bool,
    equalizer: GraphicEqualizer,
    clear_bass: ClearBass,
    balance: StereoBalance,
    crossfeed: Crossfeed,
    compressor: DynamicRangeCompressor,
}

impl DspEngine {
    pub fn new(sample_rate: f64, channels: usize) -> Self {
        let sr = sample_rate.max(8000.0);
        let ch = channels.max(1);
        Self {
            sample_rate: sr,
            channels: ch,
            enabled: true,
            mono_audio: false,
            equalizer: GraphicEqualizer::new(sr),
            clear_bass: ClearBass::new(sr),
            balance: StereoBalance::new(sr),
            crossfeed: Crossfeed::new(sr),
            compressor: DynamicRangeCompressor::new(sr),
        }
    }

    pub fn set_sample_rate(&mut self, sample_rate: f64) {
        let sr = sample_rate.max(8000.0);
        if (self.sample_rate - sr).abs() > 1.0 {
            self.sample_rate = sr;
            self.equalizer.set_sample_rate(sr);
            self.clear_bass.set_sample_rate(sr);
            self.balance.set_sample_rate(sr);
            self.crossfeed.set_sample_rate(sr);
            self.compressor.set_sample_rate(sr);
        }
    }

    pub fn set_enabled(&mut self, enabled: bool) {
        self.enabled = enabled;
    }

    pub fn is_enabled(&self) -> bool {
        self.enabled
    }

    pub fn set_mono(&mut self, mono: bool) {
        self.mono_audio = mono;
    }

    pub fn set_band_gain(&mut self, band: usize, gain_db: f64) {
        self.equalizer.set_band_gain(band, gain_db);
    }

    pub fn set_all_bands(&mut self, gains: &[f64; EQ_BAND_COUNT]) {
        self.equalizer.set_all_bands(gains);
    }

    pub fn set_clear_bass(&mut self, gain_db: f64) {
        self.clear_bass.set_gain(gain_db);
    }

    pub fn set_balance(&mut self, balance: i32) {
        self.balance.set_balance(balance);
    }

    pub fn set_crossfeed(&mut self, amount_pct: i32) {
        self.crossfeed.set_amount(amount_pct);
    }

    pub fn set_drc(&mut self, amount_pct: i32) {
        self.compressor.set_amount(amount_pct);
    }

    pub fn reset_state(&mut self) {
        self.equalizer.reset_state();
        self.clear_bass.reset_state();
        self.crossfeed.reset_state();
        self.compressor.reset_state();
    }

    /// High-performance zero-copy processing of interleaved 16-bit signed PCM audio.
    #[inline]
    pub fn process_interleaved_pcm16(&mut self, buffer: &mut [i16]) {
        if !self.enabled {
            return;
        }

        if self.channels == 2 {
            self.process_stereo_pcm16(buffer);
        } else if self.channels == 1 {
            self.process_mono_pcm16(buffer);
        }
    }

    #[inline(always)]
    fn process_stereo_pcm16(&mut self, buffer: &mut [i16]) {
        let frame_count = buffer.len() / 2;
        let mut idx = 0;

        for _ in 0..frame_count {
            // Convert PCM16 to float (-1.0 to 1.0)
            let mut left: f64 = buffer[idx].to_sample::<f64>();
            let mut right: f64 = buffer[idx + 1].to_sample::<f64>();

            // 1. Mono downmix
            if self.mono_audio {
                let mono = (left + right) * 0.5;
                left = mono;
                right = mono;
            }

            // 2. Binaural Crossfeed
            let (cf_l, cf_r) = self.crossfeed.process_stereo(left, right);
            left = cf_l;
            right = cf_r;

            // 3. 10-Band Graphic Equalizer
            let (eq_l, eq_r) = self.equalizer.process_stereo(left, right);
            left = eq_l;
            right = eq_r;

            // 4. Sub-Bass / ClearBass
            let (cb_l, cb_r) = self.clear_bass.process_stereo(left, right);
            left = cb_l;
            right = cb_r;

            // 5. Smooth Stereo Balance
            let (bal_l, bal_r) = self.balance.process_stereo(left, right);
            left = bal_l;
            right = bal_r;

            // 6. Dynamic Range Compressor & Anti-Clip Limiter
            let (out_l, out_r) = self.compressor.process_stereo(left, right);

            // Convert back to PCM16
            buffer[idx] = out_l.to_sample::<i16>();
            buffer[idx + 1] = out_r.to_sample::<i16>();

            idx += 2;
        }
    }

    #[inline(always)]
    fn process_mono_pcm16(&mut self, buffer: &mut [i16]) {
        for sample in buffer.iter_mut() {
            let mut s: f64 = sample.to_sample::<f64>();
            s = self.equalizer.process_sample_left(s);
            let (cb_s, _) = self.clear_bass.process_stereo(s, s);
            let (out_s, _) = self.compressor.process_stereo(cb_s, cb_s);
            *sample = out_s.to_sample::<i16>();
        }
    }
}
