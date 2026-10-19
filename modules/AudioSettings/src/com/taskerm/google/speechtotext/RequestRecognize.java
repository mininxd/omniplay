package com.joaomgcd.taskerm.google.speechtotext;

import androidx.annotation.Keep;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.app.NotificationCompat;
import c.f.b.g;
import c.f.b.k;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class RequestRecognize {

    @Keep
    public static final class RecognitionConfig {
        private final Integer audioChannelCount;
        private final Boolean enableAutomaticPunctuation;
        private final Boolean enableSeparateRecognitionPerChannel;
        private final Boolean enableWordTimeOffsets;
        private final Encoding encoding;
        private final String languageCode;
        private final Integer maxAlternatives;
        private final RecognitionMetadata metadata;
        private final String model;
        private final Boolean profanityFilter;
        private final Integer sampleRateHertz;
        private final SpeechContext[] speechContexts;
        private final Boolean useEnhanced;

        public RecognitionConfig() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
        }

        public RecognitionConfig(Boolean bool, Integer num, Encoding encoding, SpeechContext[] speechContextArr, String str, Integer num2, Boolean bool2, String str2, Boolean bool3, Boolean bool4, RecognitionMetadata recognitionMetadata, Integer num3, Boolean bool5) {
            this.enableAutomaticPunctuation = bool;
            this.maxAlternatives = num;
            this.encoding = encoding;
            this.speechContexts = speechContextArr;
            this.model = str;
            this.audioChannelCount = num2;
            this.enableWordTimeOffsets = bool2;
            this.languageCode = str2;
            this.profanityFilter = bool3;
            this.useEnhanced = bool4;
            this.metadata = recognitionMetadata;
            this.sampleRateHertz = num3;
            this.enableSeparateRecognitionPerChannel = bool5;
        }

        public /* synthetic */ RecognitionConfig(Boolean bool, Integer num, Encoding encoding, SpeechContext[] speechContextArr, String str, Integer num2, Boolean bool2, String str2, Boolean bool3, Boolean bool4, RecognitionMetadata recognitionMetadata, Integer num3, Boolean bool5, int i, g gVar) {
            this((i & 1) != 0 ? (Boolean) null : bool, (i & 2) != 0 ? (Integer) null : num, (i & 4) != 0 ? (Encoding) null : encoding, (i & 8) != 0 ? (SpeechContext[]) null : speechContextArr, (i & 16) != 0 ? (String) null : str, (i & 32) != 0 ? (Integer) null : num2, (i & 64) != 0 ? (Boolean) null : bool2, (i & 128) != 0 ? (String) null : str2, (i & 256) != 0 ? (Boolean) null : bool3, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Boolean) null : bool4, (i & 1024) != 0 ? (RecognitionMetadata) null : recognitionMetadata, (i & 2048) != 0 ? (Integer) null : num3, (i & NotificationCompat.FLAG_BUBBLE) != 0 ? (Boolean) null : bool5);
        }

        public final Boolean getEnableAutomaticPunctuation() {
            return this.enableAutomaticPunctuation;
        }

        public final Integer getMaxAlternatives() {
            return this.maxAlternatives;
        }

        public final Encoding getEncoding() {
            return this.encoding;
        }

        public final SpeechContext[] getSpeechContexts() {
            return this.speechContexts;
        }

        public final String getModel() {
            return this.model;
        }

        public final Integer getAudioChannelCount() {
            return this.audioChannelCount;
        }

        public final Boolean getEnableWordTimeOffsets() {
            return this.enableWordTimeOffsets;
        }

        public final String getLanguageCode() {
            return this.languageCode;
        }

        public final Boolean getProfanityFilter() {
            return this.profanityFilter;
        }

        public final Boolean getUseEnhanced() {
            return this.useEnhanced;
        }

        public final RecognitionMetadata getMetadata() {
            return this.metadata;
        }

        public final Integer getSampleRateHertz() {
            return this.sampleRateHertz;
        }

        public final Boolean getEnableSeparateRecognitionPerChannel() {
            return this.enableSeparateRecognitionPerChannel;
        }

        @Keep
        public static final class SpeechContext {
            private final HashMap<String, Object>[] phrases;

            /* JADX WARN: Multi-variable type inference failed */
            public SpeechContext() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            public SpeechContext(HashMap<String, Object>[] mapArr) {
                this.phrases = mapArr;
            }

            public /* synthetic */ SpeechContext(HashMap[] mapArr, int i, g gVar) {
                this((i & 1) != 0 ? (HashMap[]) null : mapArr);
            }

            public final HashMap<String, Object>[] getPhrases() {
                return this.phrases;
            }
        }

        @Keep
        public static final class RecognitionMetadata {
            private final String audioTopic;
            private final Integer industryNaicsCodeOfAudio;
            private final InteractionType interactionType;
            private final MicrophoneDistance microphoneDistance;
            private final String obfuscatedId;
            private final OriginalMediaType originalMediaType;
            private final String originalMimeType;
            private final String recordingDeviceName;
            private final RecordingDeviceType recordingDeviceType;

            public RecognitionMetadata() {
                this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
            }

            public RecognitionMetadata(InteractionType interactionType, String str, String str2, String str3, MicrophoneDistance microphoneDistance, Integer num, OriginalMediaType originalMediaType, RecordingDeviceType recordingDeviceType, String str4) {
                this.interactionType = interactionType;
                this.audioTopic = str;
                this.originalMimeType = str2;
                this.recordingDeviceName = str3;
                this.microphoneDistance = microphoneDistance;
                this.industryNaicsCodeOfAudio = num;
                this.originalMediaType = originalMediaType;
                this.recordingDeviceType = recordingDeviceType;
                this.obfuscatedId = str4;
            }

            public /* synthetic */ RecognitionMetadata(InteractionType interactionType, String str, String str2, String str3, MicrophoneDistance microphoneDistance, Integer num, OriginalMediaType originalMediaType, RecordingDeviceType recordingDeviceType, String str4, int i, g gVar) {
                this((i & 1) != 0 ? (InteractionType) null : interactionType, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (MicrophoneDistance) null : microphoneDistance, (i & 32) != 0 ? (Integer) null : num, (i & 64) != 0 ? (OriginalMediaType) null : originalMediaType, (i & 128) != 0 ? (RecordingDeviceType) null : recordingDeviceType, (i & 256) != 0 ? (String) null : str4);
            }

            public final InteractionType getInteractionType() {
                return this.interactionType;
            }

            public final String getAudioTopic() {
                return this.audioTopic;
            }

            public final String getOriginalMimeType() {
                return this.originalMimeType;
            }

            public final String getRecordingDeviceName() {
                return this.recordingDeviceName;
            }

            public final MicrophoneDistance getMicrophoneDistance() {
                return this.microphoneDistance;
            }

            public final Integer getIndustryNaicsCodeOfAudio() {
                return this.industryNaicsCodeOfAudio;
            }

            public final OriginalMediaType getOriginalMediaType() {
                return this.originalMediaType;
            }

            public final RecordingDeviceType getRecordingDeviceType() {
                return this.recordingDeviceType;
            }

            public final String getObfuscatedId() {
                return this.obfuscatedId;
            }
        }

        @Keep
        public enum InteractionType {
            INTERACTION_TYPE_UNSPECIFIED("Use case is either unknown or is something other than one of the othervalues below."),
            DISCUSSION("Multiple people in a conversation or discussion. For example in ameeting with two or more people actively participating. Typically all the primary people speaking would be in the same room (if not, see PHONE_CALL)"),
            PRESENTATION("One or more persons lecturing or presenting to others, mostly uninterrupted."),
            PHONE_CALL("A phone-call or video-conference in which two or more people, who are not in the same room, are actively participating."),
            VOICEMAIL("A recorded message intended for another person to listen to."),
            PROFESSIONALLY_PRODUCED("Professionally produced audio (eg. TV Show, Podcast)."),
            VOICE_SEARCH("Transcribe spoken questions and queries into text."),
            VOICE_COMMAND("Transcribe voice commands, such as for controlling a device."),
            DICTATION("Transcribe speech to text to create a written document, such as a text-message, email or report.");

            private final String description;

            InteractionType(String str) {
                k.b(str, "description");
                this.description = str;
            }

            public final String getDescription() {
                return this.description;
            }
        }

        @Keep
        public enum MicrophoneDistance {
            MICROPHONE_DISTANCE_UNSPECIFIED("Audio type is not known."),
            NEARFIELD("The audio was captured from a closely placed microphone. Eg. phone,dictaphone, or handheld microphone. Generally if there speaker is within1 meter of the microphone."),
            MIDFIELD("The speaker if within 3 meters of the microphone."),
            FARFIELD("The speaker is more than 3 meters away from the microphone.");

            private final String description;

            MicrophoneDistance(String str) {
                k.b(str, "description");
                this.description = str;
            }

            public final String getDescription() {
                return this.description;
            }
        }

        @Keep
        public enum OriginalMediaType {
            ORIGINAL_MEDIA_TYPE_UNSPECIFIED("Unknown original media type."),
            AUDIO("The speech data is an audio recording."),
            VIDEO("The speech data originally recorded on a video.");

            private final String description;

            OriginalMediaType(String str) {
                k.b(str, "description");
                this.description = str;
            }

            public final String getDescription() {
                return this.description;
            }
        }

        @Keep
        public enum RecordingDeviceType {
            RECORDING_DEVICE_TYPE_UNSPECIFIED("The recording device is unknown."),
            SMARTPHONE("Speech was recorded on a smartphone."),
            PC("Speech was recorded using a personal computer or tablet."),
            PHONE_LINE("Speech was recorded over a phone line."),
            VEHICLE("Speech was recorded in a vehicle."),
            OTHER_OUTDOOR_DEVICE("Speech was recorded outdoors."),
            OTHER_INDOOR_DEVICE("Speech was recorded indoors.");

            private final String description;

            RecordingDeviceType(String str) {
                k.b(str, "description");
                this.description = str;
            }

            public final String getDescription() {
                return this.description;
            }
        }

        @Keep
        public enum Encoding {
            ENCODING_UNSPECIFIED("Not specified."),
            LINEAR16("Uncompressed 16-bit signed little-endian samples (Linear PCM)."),
            FLAC("`FLAC` (Free Lossless AudioCodec) is the recommended encoding because it islossless--therefore recognition is not compromised--and requires only about half the bandwidth of `LINEAR16`. `FLAC` stream encoding supports 16-bit and 24-bit samples, however, not all fields in `STREAMINFO` are supported."),
            MULAW("8-bit samples that compand 14-bit audio samples using G.711 PCMU/mu-law."),
            AMR("Adaptive Multi-Rate Narrowband codec. `sample_rate_hertz` must be 8000."),
            AMR_WB("Adaptive Multi-Rate Wideband codec. `sample_rate_hertz` must be 16000."),
            OGG_OPUS("Opus encoded audio frames in Ogg container ([OggOpus](https://wiki.xiph.org/OggOpus)). `sample_rate_hertz` must be one of 8000, 12000, 16000, 24000, or 48000."),
            SPEEX_WITH_HEADER_BYTE("Although the use of lossy encodings is not recommended, if a very low bitrate encoding is required, `OGG_OPUS` is highly preferred over Speex encoding. The [Speex](https://speex.org/)  encoding supported by Cloud Speech API has a header byte in each block, as in MIME type `audio/x-speex-with-header-byte`. It is a variant of the RTP Speex encoding defined in [RFC 5574](https://tools.ietf.org/html/rfc5574). The stream is a sequence of blocks, one block per RTP packet. Each block starts with a byte containing the length of the block, in bytes, followed by one or more frames of Speex data, padded to an integral number of bytes (octets) as specified in RFC 5574. In other words, each RTP header is replaced with a single byte containing the block length. Only Speex wideband is supported. `sample_rate_hertz` must be 16000.");

            private final String description;

            Encoding(String str) {
                k.b(str, "description");
                this.description = str;
            }

            public final String getDescription() {
                return this.description;
            }
        }
    }

    @Keep
    public static final class RecognitionAudio {
        private final String content;
        private final String uri;

        /* JADX WARN: Multi-variable type inference failed */
        public RecognitionAudio() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public RecognitionAudio(String str, String str2) {
            this.content = str;
            this.uri = str2;
        }

        public /* synthetic */ RecognitionAudio(String str, String str2, int i, g gVar) {
            this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2);
        }

        public final String getContent() {
            return this.content;
        }

        public final String getUri() {
            return this.uri;
        }
    }
}
