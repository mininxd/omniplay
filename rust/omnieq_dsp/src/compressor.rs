pub struct DynamicRangeCompressor {
    sample_rate: f64,
    drc_amount: f64, // 0.0 to 1.0
    envelope: f64,
    attack_coeff: f64,
    release_coeff: f64,
    is_active: bool,
}

const LINEAR_THRESHOLD: f64 = 0.25; // -12 dBFS
const ATTACK_TIME_SEC: f64 = 0.005;  // 5ms
const RELEASE_TIME_SEC: f64 = 0.100; // 100ms

impl DynamicRangeCompressor {
    pub fn new(sample_rate: f64) -> Self {
        let sr = sample_rate.max(8000.0);
        let attack_coeff = (-1.0 / (sr * ATTACK_TIME_SEC)).exp();
        let release_coeff = (-1.0 / (sr * RELEASE_TIME_SEC)).exp();
        Self {
            sample_rate: sr,
            drc_amount: 0.0,
            envelope: 1.0,
            attack_coeff,
            release_coeff,
            is_active: false,
        }
    }

    pub fn set_sample_rate(&mut self, sample_rate: f64) {
        let sr = sample_rate.max(8000.0);
        if (self.sample_rate - sr).abs() > 1.0 {
            self.sample_rate = sr;
            self.attack_coeff = (-1.0 / (sr * ATTACK_TIME_SEC)).exp();
            self.release_coeff = (-1.0 / (sr * RELEASE_TIME_SEC)).exp();
        }
    }

    pub fn set_amount(&mut self, amount_pct: i32) {
        let clamped = (amount_pct.clamp(0, 100) as f64) / 100.0;
        self.drc_amount = clamped;
        self.is_active = self.drc_amount > 0.01;
        if !self.is_active {
            self.envelope = 1.0;
        }
    }

    pub fn reset_state(&mut self) {
        self.envelope = 1.0;
    }

    #[inline(always)]
    pub fn process_stereo(&mut self, left: f64, right: f64) -> (f64, f64) {
        let peak = left.abs().max(right.abs());

        let target_gain = if self.is_active {
            let ratio = 1.0 + 3.0 * self.drc_amount;
            if peak > LINEAR_THRESHOLD {
                let excess = peak - LINEAR_THRESHOLD;
                let compressed_peak = LINEAR_THRESHOLD + (excess / ratio);
                (compressed_peak / peak).clamp(0.1, 1.0)
            } else {
                1.0
            }
        } else {
            1.0
        };

        // Attack/release smoothing
        let coeff = if target_gain < self.envelope {
            self.attack_coeff
        } else {
            self.release_coeff
        };
        self.envelope = target_gain + coeff * (self.envelope - target_gain);

        let mut out_l = left * self.envelope;
        let mut out_r = right * self.envelope;

        // Peak Limiter: prevent PCM16 clipping
        out_l = out_l.clamp(-0.9999, 0.9999);
        out_r = out_r.clamp(-0.9999, 0.9999);

        (out_l, out_r)
    }
}
