package com.joaomgcd.taskerm.google.language;

import androidx.annotation.Keep;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class ResponseAnalyzeSentiment {
    private final Sentence.Sentiment documentSentiment;
    private final String language;
    private final Sentence[] sentences;

    public ResponseAnalyzeSentiment() {
        this(null, null, null, 7, null);
    }

    public ResponseAnalyzeSentiment(String str, Sentence[] sentenceArr, Sentence.Sentiment sentiment) {
        this.language = str;
        this.sentences = sentenceArr;
        this.documentSentiment = sentiment;
    }

    public /* synthetic */ ResponseAnalyzeSentiment(String str, Sentence[] sentenceArr, Sentence.Sentiment sentiment, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Sentence[]) null : sentenceArr, (i & 4) != 0 ? (Sentence.Sentiment) null : sentiment);
    }

    public final String getLanguage() {
        return this.language;
    }

    public final Sentence[] getSentences() {
        return this.sentences;
    }

    public final Sentence.Sentiment getDocumentSentiment() {
        return this.documentSentiment;
    }

    @Keep
    public static final class Sentence {
        private final Sentiment sentiment;
        private final TextSpan text;

        /* JADX WARN: Multi-variable type inference failed */
        public Sentence() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public Sentence(TextSpan textSpan, Sentiment sentiment) {
            this.text = textSpan;
            this.sentiment = sentiment;
        }

        public /* synthetic */ Sentence(TextSpan textSpan, Sentiment sentiment, int i, g gVar) {
            this((i & 1) != 0 ? (TextSpan) null : textSpan, (i & 2) != 0 ? (Sentiment) null : sentiment);
        }

        public final TextSpan getText() {
            return this.text;
        }

        public final Sentiment getSentiment() {
            return this.sentiment;
        }

        @Keep
        public static final class TextSpan {
            private final Integer beginOffset;
            private final String content;

            /* JADX WARN: Multi-variable type inference failed */
            public TextSpan() {
                this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
            }

            public TextSpan(Integer num, String str) {
                this.beginOffset = num;
                this.content = str;
            }

            public /* synthetic */ TextSpan(Integer num, String str, int i, g gVar) {
                this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (String) null : str);
            }

            public final Integer getBeginOffset() {
                return this.beginOffset;
            }

            public final String getContent() {
                return this.content;
            }
        }

        @Keep
        public static final class Sentiment {
            private final Float magnitude;
            private final Float score;

            /* JADX WARN: Multi-variable type inference failed */
            public Sentiment() {
                this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
            }

            public Sentiment(Float f2, Float f3) {
                this.score = f2;
                this.magnitude = f3;
            }

            public /* synthetic */ Sentiment(Float f2, Float f3, int i, g gVar) {
                this((i & 1) != 0 ? (Float) null : f2, (i & 2) != 0 ? (Float) null : f3);
            }

            public final Float getScore() {
                return this.score;
            }

            public final Float getMagnitude() {
                return this.magnitude;
            }
        }
    }
}
