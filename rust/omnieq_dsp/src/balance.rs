/// Smooth Stereo Balance Panner with parameter smoothing to avoid clicks or channel swaps.
pub struct StereoBalance {
    target_left_gain: f64,
    target_right_gain: f64,
    current_left_gain: f64,
    current_right_gain: f64,
    smooth_coeff: f64,
    is_centered: bool,
}

impl StereoBalance {
    pub fn new(sample_rate: f64) -> Self {
        let sr = sample_rate.max(8000.0);
        // ~20ms smoothing time constant for slider dragging
        let smooth_coeff = 1.0 - (-1.0 / (sr * 0.020)).exp();
        Self {
            target_left_gain: 1.0,
            target_right_gain: 1.0,
            current_left_gain: 1.0,
            current_right_gain: 1.0,
            smooth_coeff,
            is_centered: true,
        }
    }

    pub fn set_sample_rate(&mut self, sample_rate: f64) {
        let sr = sample_rate.max(8000.0);
        self.smooth_coeff = 1.0 - (-1.0 / (sr * 0.020)).exp();
    }

    /// Sets stereo balance from -100 (Full Left) to 100 (Full Right).
    pub fn set_balance(&mut self, balance: i32) {
        let b = balance.clamp(-100, 100);
        self.is_centered = b == 0;
        if b == 0 {
            self.target_left_gain = 1.0;
            self.target_right_gain = 1.0;
        } else if b > 0 {
            // Pan right: left decreases smoothly, right remains full
            self.target_left_gain = (1.0 - (b as f64) / 100.0).clamp(0.0, 1.0);
            self.target_right_gain = 1.0;
        } else {
            // Pan left: right decreases smoothly, left remains full
            self.target_left_gain = 1.0;
            self.target_right_gain = (1.0 + (b as f64) / 100.0).clamp(0.0, 1.0);
        }
    }

    #[inline]
    pub fn is_active(&self) -> bool {
        !self.is_centered
            || (self.current_left_gain - 1.0).abs() >= 0.001
            || (self.current_right_gain - 1.0).abs() >= 0.001
    }

    pub fn reset_state(&mut self) {
        if self.is_centered {
            self.current_left_gain = 1.0;
            self.current_right_gain = 1.0;
        }
    }

    #[inline(always)]
    pub fn process_stereo(&mut self, left: f64, right: f64) -> (f64, f64) {
        if self.is_centered
            && (self.current_left_gain - 1.0).abs() < 0.001
            && (self.current_right_gain - 1.0).abs() < 0.001
        {
            self.current_left_gain = 1.0;
            self.current_right_gain = 1.0;
            return (left, right);
        }

        // Smoothly interpolate current gain towards target gain per sample
        self.current_left_gain += (self.target_left_gain - self.current_left_gain) * self.smooth_coeff;
        self.current_right_gain += (self.target_right_gain - self.current_right_gain) * self.smooth_coeff;

        (
            left * self.current_left_gain,
            right * self.current_right_gain,
        )
    }
}
