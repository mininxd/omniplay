use crate::biquad::Biquad;

pub struct ClearBass {
    sample_rate: f64,
    gain_level: f64, // -10.0 to 10.0 dB
    filter_left: Biquad,
    filter_right: Biquad,
    is_active: bool,
}

impl ClearBass {
    pub fn new(sample_rate: f64) -> Self {
        let mut cb = Self {
            sample_rate: sample_rate.max(8000.0),
            gain_level: 0.0,
            filter_left: Biquad::new(),
            filter_right: Biquad::new(),
            is_active: false,
        };
        cb.update_filter();
        cb
    }

    pub fn set_sample_rate(&mut self, sample_rate: f64) {
        let sr = sample_rate.max(8000.0);
        if (self.sample_rate - sr).abs() > 1.0 {
            self.sample_rate = sr;
            self.update_filter();
        }
    }

    pub fn set_gain(&mut self, gain_db: f64) {
        let clamped = gain_db.clamp(-10.0, 10.0);
        if (self.gain_level - clamped).abs() > 0.01 {
            self.gain_level = clamped;
            self.update_filter();
        }
    }

    pub fn get_gain(&self) -> f64 {
        self.gain_level
    }

    pub fn reset_state(&mut self) {
        self.filter_left.reset();
        self.filter_right.reset();
    }

    fn update_filter(&mut self) {
        if self.gain_level.abs() < 0.1 {
            self.is_active = false;
            self.filter_left.set_passthrough();
            self.filter_right.set_passthrough();
        } else {
            self.is_active = true;
            // ClearBass targets 70 Hz sub-bass band with wide shelf
            self.filter_left.set_low_shelf(75.0, self.sample_rate, self.gain_level);
            self.filter_right.set_low_shelf(75.0, self.sample_rate, self.gain_level);
        }
    }

    #[inline(always)]
    pub fn process_stereo(&mut self, left: f64, right: f64) -> (f64, f64) {
        if !self.is_active {
            return (left, right);
        }
        (
            self.filter_left.process(left),
            self.filter_right.process(right),
        )
    }
}
