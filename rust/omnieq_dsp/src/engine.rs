use crate::equalizer::EQ_BAND_COUNT;
use crate::node::{NodeId, NodePipeline};
use dasp::Sample;

pub struct DspEngine {
    sample_rate: f64,
    channels: usize,
    enabled: bool,
    pipeline: NodePipeline,
}

impl DspEngine {
    pub fn new(sample_rate: f64, channels: usize) -> Self {
        let sr = sample_rate.max(8000.0);
        let ch = channels.max(1);
        Self {
            sample_rate: sr,
            channels: ch,
            enabled: true,
            pipeline: NodePipeline::new(sr),
        }
    }

    pub fn set_sample_rate(&mut self, sample_rate: f64) {
        let sr = sample_rate.max(8000.0);
        if (self.sample_rate - sr).abs() > 1.0 {
            self.sample_rate = sr;
            self.pipeline.set_sample_rate(sr);
        }
    }

    pub fn set_enabled(&mut self, enabled: bool) {
        self.enabled = enabled;
        self.pipeline.set_master_enabled(enabled);
    }

    pub fn is_enabled(&self) -> bool {
        self.enabled
    }

    pub fn set_mono(&mut self, mono: bool) {
        self.pipeline.mono.set_enabled(mono);
    }

    pub fn set_band_gain(&mut self, band: usize, gain_db: f64) {
        self.pipeline.equalizer.set_band_gain(band, gain_db);
    }

    pub fn set_all_bands(&mut self, gains: &[f64; EQ_BAND_COUNT]) {
        self.pipeline.equalizer.set_all_bands(gains);
    }

    pub fn set_clear_bass(&mut self, gain_db: f64) {
        self.pipeline.clear_bass.set_gain(gain_db);
    }

    pub fn set_balance(&mut self, balance: i32) {
        self.pipeline.balance.set_balance(balance);
    }

    pub fn set_crossfeed(&mut self, amount_pct: i32) {
        self.pipeline.crossfeed.set_amount(amount_pct);
    }

    pub fn set_drc(&mut self, amount_pct: i32) {
        self.pipeline.compressor.set_amount(amount_pct);
    }

    pub fn set_node_order(&mut self, order: &[NodeId]) {
        self.pipeline.set_order(order);
    }

    pub fn reset_state(&mut self) {
        self.pipeline.reset_state();
    }

    /// High-performance zero-copy processing of interleaved 16-bit signed PCM audio.
    /// If no effects or channel routings are active, returns immediately with 0% CPU overhead.
    #[inline]
    pub fn process_interleaved_pcm16(&mut self, buffer: &mut [i16]) {
        if !self.pipeline.is_any_active() {
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
        let (active_nodes, count) = self.pipeline.get_active_nodes();
        if count == 0 {
            return;
        }

        let active_slice = &active_nodes[..count];
        let frame_count = buffer.len() / 2;
        let mut idx = 0;

        for _ in 0..frame_count {
            // Convert PCM16 to float (-1.0 to 1.0)
            let left: f64 = buffer[idx].to_sample::<f64>();
            let right: f64 = buffer[idx + 1].to_sample::<f64>();

            // Process through active nodes in the chain
            let (out_l, out_r) = self.pipeline.process_stereo_fast(active_slice, left, right);

            // Convert back to PCM16
            buffer[idx] = out_l.to_sample::<i16>();
            buffer[idx + 1] = out_r.to_sample::<i16>();

            idx += 2;
        }
    }

    #[inline(always)]
    fn process_mono_pcm16(&mut self, buffer: &mut [i16]) {
        for sample in buffer.iter_mut() {
            let s: f64 = sample.to_sample::<f64>();
            let (out_s, _) = self.pipeline.process_stereo_fast(&[NodeId::Equalizer, NodeId::ClearBass, NodeId::CompressorLimiter], s, s);
            *sample = out_s.to_sample::<i16>();
        }
    }
}
