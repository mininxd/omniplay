use crate::biquad::Biquad;

pub const EQ_BAND_COUNT: usize = 10;
pub const EQ_FREQUENCIES: [f64; EQ_BAND_COUNT] = [
    32.0, 64.0, 125.0, 250.0, 500.0, 1000.0, 2000.0, 4000.0, 8000.0, 16000.0,
];
const EQ_Q_FACTORS: [f64; EQ_BAND_COUNT] = [
    1.414, 1.414, 1.414, 1.414, 1.414, 1.414, 1.414, 1.414, 1.414, 1.414,
];

pub struct GraphicEqualizer {
    sample_rate: f64,
    bands_db: [f64; EQ_BAND_COUNT],
    filters_left: [Biquad; EQ_BAND_COUNT],
    filters_right: [Biquad; EQ_BAND_COUNT],
    is_active: bool,
}

impl GraphicEqualizer {
    pub fn new(sample_rate: f64) -> Self {
        let mut eq = Self {
            sample_rate: sample_rate.max(8000.0),
            bands_db: [0.0; EQ_BAND_COUNT],
            filters_left: [
                Biquad::new(), Biquad::new(), Biquad::new(), Biquad::new(), Biquad::new(),
                Biquad::new(), Biquad::new(), Biquad::new(), Biquad::new(), Biquad::new(),
            ],
            filters_right: [
                Biquad::new(), Biquad::new(), Biquad::new(), Biquad::new(), Biquad::new(),
                Biquad::new(), Biquad::new(), Biquad::new(), Biquad::new(), Biquad::new(),
            ],
            is_active: false,
        };
        eq.recalculate_filters();
        eq
    }

    pub fn set_sample_rate(&mut self, sample_rate: f64) {
        let sr = sample_rate.max(8000.0);
        if (self.sample_rate - sr).abs() > 1.0 {
            self.sample_rate = sr;
            self.recalculate_filters();
        }
    }

    pub fn set_band_gain(&mut self, band_index: usize, gain_db: f64) {
        if band_index < EQ_BAND_COUNT {
            let clamped = gain_db.clamp(-12.0, 12.0);
            if (self.bands_db[band_index] - clamped).abs() > 0.01 {
                self.bands_db[band_index] = clamped;
                self.update_single_filter(band_index);
                self.check_active();
            }
        }
    }

    pub fn set_all_bands(&mut self, gains_db: &[f64; EQ_BAND_COUNT]) {
        for i in 0..EQ_BAND_COUNT {
            self.bands_db[i] = gains_db[i].clamp(-12.0, 12.0);
        }
        self.recalculate_filters();
        self.check_active();
    }

    pub fn get_band_gain(&self, band_index: usize) -> f64 {
        if band_index < EQ_BAND_COUNT {
            self.bands_db[band_index]
        } else {
            0.0
        }
    }

    pub fn reset_to_flat(&mut self) {
        self.bands_db = [0.0; EQ_BAND_COUNT];
        for i in 0..EQ_BAND_COUNT {
            self.filters_left[i].set_passthrough();
            self.filters_right[i].set_passthrough();
        }
        self.is_active = false;
    }

    pub fn reset_state(&mut self) {
        for i in 0..EQ_BAND_COUNT {
            self.filters_left[i].reset();
            self.filters_right[i].reset();
        }
    }

    fn check_active(&mut self) {
        self.is_active = self.bands_db.iter().any(|&g| g.abs() > 0.01);
    }

    fn update_single_filter(&mut self, i: usize) {
        let freq = EQ_FREQUENCIES[i];
        let q = EQ_Q_FACTORS[i];
        let gain = self.bands_db[i];
        self.filters_left[i].set_peaking_eq(freq, self.sample_rate, q, gain);
        self.filters_right[i].set_peaking_eq(freq, self.sample_rate, q, gain);
    }

    fn recalculate_filters(&mut self) {
        for i in 0..EQ_BAND_COUNT {
            self.update_single_filter(i);
        }
    }

    #[inline(always)]
    pub fn process_sample_left(&mut self, mut sample: f64) -> f64 {
        if !self.is_active {
            return sample;
        }
        for filter in &mut self.filters_left {
            sample = filter.process(sample);
        }
        sample
    }

    #[inline(always)]
    pub fn process_sample_right(&mut self, mut sample: f64) -> f64 {
        if !self.is_active {
            return sample;
        }
        for filter in &mut self.filters_right {
            sample = filter.process(sample);
        }
        sample
    }

    #[inline(always)]
    pub fn process_stereo(&mut self, left: f64, right: f64) -> (f64, f64) {
        if !self.is_active {
            return (left, right);
        }
        let mut out_l = left;
        let mut out_r = right;
        for i in 0..EQ_BAND_COUNT {
            out_l = self.filters_left[i].process(out_l);
            out_r = self.filters_right[i].process(out_r);
        }
        (out_l, out_r)
    }
}
