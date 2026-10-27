use crate::balance::StereoBalance;
use crate::clear_bass::ClearBass;
use crate::compressor::DynamicRangeCompressor;
use crate::crossfeed::Crossfeed;
use crate::equalizer::GraphicEqualizer;

/// Identifiers for each modular audio DSP node in the pipeline.
#[repr(u8)]
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum NodeId {
    MonoDownmix = 0,
    Crossfeed = 1,
    Equalizer = 2,
    ClearBass = 3,
    StereoBalance = 4,
    CompressorLimiter = 5,
}

pub const DEFAULT_NODE_ORDER: [NodeId; 6] = [
    NodeId::MonoDownmix,
    NodeId::Crossfeed,
    NodeId::Equalizer,
    NodeId::ClearBass,
    NodeId::StereoBalance,
    NodeId::CompressorLimiter,
];

/// Mono downmix processor node.
#[derive(Debug, Clone, Copy)]
pub struct MonoDownmixNode {
    enabled: bool,
}

impl MonoDownmixNode {
    pub fn new() -> Self {
        Self { enabled: false }
    }

    #[inline]
    pub fn is_active(&self) -> bool {
        self.enabled
    }

    pub fn set_enabled(&mut self, enabled: bool) {
        self.enabled = enabled;
    }

    #[inline(always)]
    pub fn process_stereo(&self, left: f64, right: f64) -> (f64, f64) {
        let mono = (left + right) * 0.5;
        (mono, mono)
    }
}

/// Modular Graph Pipeline chaining together all DSP stages.
/// Inspired by rustaudio's dsp-chain, allowing dynamic bypass,
/// reordering, and zero-cost passthrough when inactive.
pub struct NodePipeline {
    sample_rate: f64,
    master_enabled: bool,
    order: [NodeId; 6],
    pub mono: MonoDownmixNode,
    pub crossfeed: Crossfeed,
    pub equalizer: GraphicEqualizer,
    pub clear_bass: ClearBass,
    pub balance: StereoBalance,
    pub compressor: DynamicRangeCompressor,
}

impl NodePipeline {
    pub fn new(sample_rate: f64) -> Self {
        let sr = sample_rate.max(8000.0);
        Self {
            sample_rate: sr,
            master_enabled: true,
            order: DEFAULT_NODE_ORDER,
            mono: MonoDownmixNode::new(),
            crossfeed: Crossfeed::new(sr),
            equalizer: GraphicEqualizer::new(sr),
            clear_bass: ClearBass::new(sr),
            balance: StereoBalance::new(sr),
            compressor: DynamicRangeCompressor::new(sr),
        }
    }

    pub fn set_master_enabled(&mut self, enabled: bool) {
        self.master_enabled = enabled;
    }

    pub fn is_master_enabled(&self) -> bool {
        self.master_enabled
    }

    pub fn set_sample_rate(&mut self, sample_rate: f64) {
        let sr = sample_rate.max(8000.0);
        if (self.sample_rate - sr).abs() > 1.0 {
            self.sample_rate = sr;
            self.crossfeed.set_sample_rate(sr);
            self.equalizer.set_sample_rate(sr);
            self.clear_bass.set_sample_rate(sr);
            self.balance.set_sample_rate(sr);
            self.compressor.set_sample_rate(sr);
        }
    }

    pub fn reset_state(&mut self) {
        self.crossfeed.reset_state();
        self.equalizer.reset_state();
        self.clear_bass.reset_state();
        self.balance.reset_state();
        self.compressor.reset_state();
    }

    #[inline]
    pub fn is_node_active(&self, id: NodeId) -> bool {
        match id {
            // Balance and Mono are fundamental channel routing controls;
            // they remain functional whenever configured by the user, even if EQ master switch is flat/off.
            NodeId::MonoDownmix => self.mono.is_active(),
            NodeId::StereoBalance => self.balance.is_active(),
            // Frequency shaping and dynamic compression nodes depend on master toggle:
            NodeId::Crossfeed => self.master_enabled && self.crossfeed.is_active(),
            NodeId::Equalizer => self.master_enabled && self.equalizer.is_active(),
            NodeId::ClearBass => self.master_enabled && self.clear_bass.is_active(),
            NodeId::CompressorLimiter => self.master_enabled && self.compressor.is_active(),
        }
    }

    #[inline]
    pub fn is_any_active(&self) -> bool {
        self.mono.is_active()
            || self.balance.is_active()
            || (self.master_enabled
                && (self.crossfeed.is_active()
                    || self.equalizer.is_active()
                    || self.clear_bass.is_active()
                    || self.compressor.is_active()))
    }

    /// Sets the execution sequence of DSP nodes.
    pub fn set_order(&mut self, new_order: &[NodeId]) {
        let mut idx = 0;
        for &id in new_order {
            if idx < self.order.len() {
                self.order[idx] = id;
                idx += 1;
            }
        }
    }

    /// Returns the active nodes for fast inner-loop dispatch without branch overhead.
    #[inline]
    pub fn get_active_nodes(&self) -> ([NodeId; 6], usize) {
        let mut active = [NodeId::MonoDownmix; 6];
        let mut count = 0;
        for &id in &self.order {
            if self.is_node_active(id) {
                active[count] = id;
                count += 1;
            }
        }
        (active, count)
    }

    #[inline(always)]
    pub fn process_node(&mut self, id: NodeId, left: f64, right: f64) -> (f64, f64) {
        match id {
            NodeId::MonoDownmix => self.mono.process_stereo(left, right),
            NodeId::Crossfeed => self.crossfeed.process_stereo(left, right),
            NodeId::Equalizer => self.equalizer.process_stereo(left, right),
            NodeId::ClearBass => self.clear_bass.process_stereo(left, right),
            NodeId::StereoBalance => self.balance.process_stereo(left, right),
            NodeId::CompressorLimiter => self.compressor.process_stereo(left, right),
        }
    }

    #[inline(always)]
    pub fn process_stereo_fast(&mut self, active_nodes: &[NodeId], mut left: f64, mut right: f64) -> (f64, f64) {
        for &id in active_nodes {
            let (l, r) = self.process_node(id, left, right);
            left = l;
            right = r;
        }
        (left, right)
    }
}
