package com.joaomgcd.taskerm.google.speechtotext;

import androidx.annotation.Keep;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class ResponseRecognize {
    private final SpeechRecognitionResult[] results;

    /* JADX WARN: Multi-variable type inference failed */
    public ResponseRecognize() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ResponseRecognize(SpeechRecognitionResult[] speechRecognitionResultArr) {
        this.results = speechRecognitionResultArr;
    }

    public /* synthetic */ ResponseRecognize(SpeechRecognitionResult[] speechRecognitionResultArr, int i, g gVar) {
        this((i & 1) != 0 ? (SpeechRecognitionResult[]) null : speechRecognitionResultArr);
    }

    public final SpeechRecognitionResult[] getResults() {
        return this.results;
    }

    @Keep
    public static final class SpeechRecognitionResult {
        private final SpeechRecognitionAlternative[] alternatives;
        private final Integer channelTag;

        /* JADX WARN: Multi-variable type inference failed */
        public SpeechRecognitionResult() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public SpeechRecognitionResult(Integer num, SpeechRecognitionAlternative[] speechRecognitionAlternativeArr) {
            this.channelTag = num;
            this.alternatives = speechRecognitionAlternativeArr;
        }

        public /* synthetic */ SpeechRecognitionResult(Integer num, SpeechRecognitionAlternative[] speechRecognitionAlternativeArr, int i, g gVar) {
            this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (SpeechRecognitionAlternative[]) null : speechRecognitionAlternativeArr);
        }

        public final Integer getChannelTag() {
            return this.channelTag;
        }

        public final SpeechRecognitionAlternative[] getAlternatives() {
            return this.alternatives;
        }

        @Keep
        public static final class SpeechRecognitionAlternative {
            private final Float confidence;
            private final String transcript;
            private final WordInfo[] words;

            public SpeechRecognitionAlternative() {
                this(null, null, null, 7, null);
            }

            public SpeechRecognitionAlternative(Float f2, String str, WordInfo[] wordInfoArr) {
                this.confidence = f2;
                this.transcript = str;
                this.words = wordInfoArr;
            }

            public /* synthetic */ SpeechRecognitionAlternative(Float f2, String str, WordInfo[] wordInfoArr, int i, g gVar) {
                this((i & 1) != 0 ? (Float) null : f2, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (WordInfo[]) null : wordInfoArr);
            }

            public final Float getConfidence() {
                return this.confidence;
            }

            public final String getTranscript() {
                return this.transcript;
            }

            public final WordInfo[] getWords() {
                return this.words;
            }

            @Keep
            public static final class WordInfo {
                private final String endTime;
                private final String startTime;
                private final String word;

                public WordInfo() {
                    this(null, null, null, 7, null);
                }

                public WordInfo(String str, String str2, String str3) {
                    this.endTime = str;
                    this.startTime = str2;
                    this.word = str3;
                }

                public /* synthetic */ WordInfo(String str, String str2, String str3, int i, g gVar) {
                    this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3);
                }

                public final String getEndTime() {
                    return this.endTime;
                }

                public final String getStartTime() {
                    return this.startTime;
                }

                public final String getWord() {
                    return this.word;
                }
            }
        }
    }
}
