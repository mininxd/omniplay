use std::f64::consts::PI;

/// Direct Form II Transposed Biquad Filter.
/// Uses 64-bit float coefficients and state to prevent numerical drift and phase distortion.
#[derive(Debug, Clone)]
pub struct Biquad {
    b0: f64,
    b1: f64,
    b2: f64,
    a1: f64,
    a2: f64,
    s1: f64,
    s2: f64,
}

impl Biquad {
    pub fn new() -> Self {
        Self {
            b0: 1.0,
            b1: 0.0,
            b2: 0.0,
            a1: 0.0,
            a2: 0.0,
            s1: 0.0,
            s2: 0.0,
        }
    }

    pub fn reset(&mut self) {
        self.s1 = 0.0;
        self.s2 = 0.0;
    }

    /// Processes a single sample through the Direct Form II Transposed filter.
    #[inline(always)]
    pub fn process(&mut self, input: f64) -> f64 {
        let output = self.b0 * input + self.s1;
        self.s1 = self.b1 * input - self.a1 * output + self.s2;
        self.s2 = self.b2 * input - self.a2 * output;
        output
    }

    /// Configures a Peaking Equalizer filter (Robert Bristow-Johnson Cookbook).
    pub fn set_peaking_eq(&mut self, center_freq: f64, sample_rate: f64, q: f64, gain_db: f64) {
        if gain_db.abs() < 0.001 {
            self.set_passthrough();
            return;
        }

        let sr = sample_rate.max(8000.0);
        let f0 = center_freq.clamp(20.0, sr * 0.495);
        let q_clamped = q.max(0.1);
        let a = 10.0f64.powf(gain_db / 40.0);
        let w0 = 2.0 * PI * f0 / sr;
        let alpha = w0.sin() / (2.0 * q_clamped);
        let cos_w0 = w0.cos();

        let b0 = 1.0 + alpha * a;
        let b1 = -2.0 * cos_w0;
        let b2 = 1.0 - alpha * a;
        let a0 = 1.0 + alpha / a;
        let a1 = -2.0 * cos_w0;
        let a2 = 1.0 - alpha / a;

        self.normalize(b0, b1, b2, a0, a1, a2);
    }

    /// Configures a Low-Shelf filter.
    pub fn set_low_shelf(&mut self, cutoff_freq: f64, sample_rate: f64, gain_db: f64) {
        if gain_db.abs() < 0.001 {
            self.set_passthrough();
            return;
        }

        let sr = sample_rate.max(8000.0);
        let f0 = cutoff_freq.clamp(20.0, sr * 0.495);
        let a = 10.0f64.powf(gain_db / 40.0);
        let w0 = 2.0 * PI * f0 / sr;
        let cos_w0 = w0.cos();
        let sin_w0 = w0.sin();
        let alpha = sin_w0 / 2.0 * (2.0f64).sqrt();
        let two_sqrt_a_alpha = 2.0 * a.sqrt() * alpha;

        let b0 = a * ((a + 1.0) - (a - 1.0) * cos_w0 + two_sqrt_a_alpha);
        let b1 = 2.0 * a * ((a - 1.0) - (a + 1.0) * cos_w0);
        let b2 = a * ((a + 1.0) - (a - 1.0) * cos_w0 - two_sqrt_a_alpha);
        let a0 = (a + 1.0) + (a - 1.0) * cos_w0 + two_sqrt_a_alpha;
        let a1 = -2.0 * ((a - 1.0) + (a + 1.0) * cos_w0);
        let a2 = (a + 1.0) + (a - 1.0) * cos_w0 - two_sqrt_a_alpha;

        self.normalize(b0, b1, b2, a0, a1, a2);
    }

    /// Configures a 2nd-order Low-Pass filter.
    pub fn set_low_pass(&mut self, cutoff_freq: f64, sample_rate: f64, q: f64) {
        let sr = sample_rate.max(8000.0);
        let f0 = cutoff_freq.clamp(20.0, sr * 0.495);
        let q_clamped = q.max(0.1);
        let w0 = 2.0 * PI * f0 / sr;
        let cos_w0 = w0.cos();
        let alpha = w0.sin() / (2.0 * q_clamped);

        let b0 = (1.0 - cos_w0) / 2.0;
        let b1 = 1.0 - cos_w0;
        let b2 = (1.0 - cos_w0) / 2.0;
        let a0 = 1.0 + alpha;
        let a1 = -2.0 * cos_w0;
        let a2 = 1.0 - alpha;

        self.normalize(b0, b1, b2, a0, a1, a2);
    }

    pub fn set_passthrough(&mut self) {
        self.b0 = 1.0;
        self.b1 = 0.0;
        self.b2 = 0.0;
        self.a1 = 0.0;
        self.a2 = 0.0;
    }

    #[inline(always)]
    fn normalize(&mut self, b0: f64, b1: f64, b2: f64, a0: f64, a1: f64, a2: f64) {
        if a0.abs() > 1e-12 {
            let inv_a0 = 1.0 / a0;
            self.b0 = b0 * inv_a0;
            self.b1 = b1 * inv_a0;
            self.b2 = b2 * inv_a0;
            self.a1 = a1 * inv_a0;
            self.a2 = a2 * inv_a0;
        } else {
            self.set_passthrough();
        }
    }
}
