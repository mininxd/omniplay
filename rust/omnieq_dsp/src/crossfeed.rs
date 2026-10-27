use crate::biquad::Biquad;

pub struct Crossfeed {
    sample_rate: f64,
    amount: f64, // 0.0 to 1.0 (0 to 100%)
    lowpass_left: Biquad,
    lowpass_right: Biquad,
    delay_buffer_l: Vec<f64>,
    delay_buffer_r: Vec<f64>,
    delay_idx: usize,
    delay_samples: usize,
    is_active: bool,
}

impl Crossfeed {
    pub fn new(sample_rate: f64) -> Self {
        let sr = sample_rate.max(8000.0);
        // ~300 microseconds delay
        let delay_samples = ((sr * 0.000300).round() as usize).max(1);
        let mut cf = Self {
            sample_rate: sr,
            amount: 0.0,
            lowpass_left: Biquad::new(),
            lowpass_right: Biquad::new(),
            delay_buffer_l: vec![0.0; delay_samples + 1],
            delay_buffer_r: vec![0.0; delay_samples + 1],
            delay_idx: 0,
            delay_samples,
            is_active: false,
        };
        cf.update_filters();
        cf
    }

    pub fn set_sample_rate(&mut self, sample_rate: f64) {
        let sr = sample_rate.max(8000.0);
        if (self.sample_rate - sr).abs() > 1.0 {
            self.sample_rate = sr;
            self.delay_samples = ((sr * 0.000300).round() as usize).max(1);
            self.delay_buffer_l = vec![0.0; self.delay_samples + 1];
            self.delay_buffer_r = vec![0.0; self.delay_samples + 1];
            self.delay_idx = 0;
            self.update_filters();
        }
    }

    pub fn set_amount(&mut self, amount_pct: i32) {
        let clamped = (amount_pct.clamp(0, 100) as f64) / 100.0;
        self.amount = clamped;
        self.is_active = self.amount > 0.01;
    }

    #[inline]
    pub fn is_active(&self) -> bool {
        self.is_active
    }

    fn update_filters(&mut self) {
        // 700 Hz cutoff for binaural head-shadowing simulation
        self.lowpass_left.set_low_pass(700.0, self.sample_rate, 0.707);
        self.lowpass_right.set_low_pass(700.0, self.sample_rate, 0.707);
    }

    pub fn reset_state(&mut self) {
        self.lowpass_left.reset();
        self.lowpass_right.reset();
        self.delay_buffer_l.fill(0.0);
        self.delay_buffer_r.fill(0.0);
        self.delay_idx = 0;
    }

    #[inline(always)]
    pub fn process_stereo(&mut self, left: f64, right: f64) -> (f64, f64) {
        if !self.is_active {
            return (left, right);
        }

        // Low-pass opposite channels
        let filtered_r = self.lowpass_right.process(right) * 0.35 * self.amount;
        let filtered_l = self.lowpass_left.process(left) * 0.35 * self.amount;

        // Read delayed sample
        let delayed_r = self.delay_buffer_r[self.delay_idx];
        let delayed_l = self.delay_buffer_l[self.delay_idx];

        // Store new filtered samples into delay buffer
        self.delay_buffer_r[self.delay_idx] = filtered_r;
        self.delay_buffer_l[self.delay_idx] = filtered_l;
        self.delay_idx = (self.delay_idx + 1) % self.delay_samples;

        // Mix crossfeed
        let direct_gain = 1.0 - (0.15 * self.amount);
        let out_l = left * direct_gain + delayed_r;
        let out_r = right * direct_gain + delayed_l;

        (out_l, out_r)
    }
}
