package com.joaomgcd.taskerm.google.language;

import androidx.annotation.Keep;
import c.f.b.g;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RequestAnalyzeSentiment {
    private final Document document;
    private final EncodingType encodingType;

    /* JADX WARN: Multi-variable type inference failed */
    public RequestAnalyzeSentiment() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public RequestAnalyzeSentiment(EncodingType encodingType, Document document) {
        this.encodingType = encodingType;
        this.document = document;
    }

    public /* synthetic */ RequestAnalyzeSentiment(EncodingType encodingType, Document document, int i, g gVar) {
        this((i & 1) != 0 ? (EncodingType) null : encodingType, (i & 2) != 0 ? (Document) null : document);
    }

    public final EncodingType getEncodingType() {
        return this.encodingType;
    }

    public final Document getDocument() {
        return this.document;
    }

    @Keep
    public enum EncodingType {
        NONE("If `EncodingType` is not specified, encoding-dependent information (such as `begin_offset`) will be set at `-1`."),
        UTF8("Encoding-dependent information (such as `begin_offset`) is calculated based on the UTF-8 encoding of the input. C++ and Go are examples of languages that use this encoding natively."),
        UTF16("Encoding-dependent information (such as `begin_offset`) is calculated based on the UTF-16 encoding of the input. Java and JavaScript are examples of languages that use this encoding natively."),
        UTF32("Encoding-dependent information (such as `begin_offset`) is calculated based on the UTF-32 encoding of the input. Python is an example of a language that uses this encoding natively.");

        private final String description;

        EncodingType(String str) {
            k.b(str, "description");
            this.description = str;
        }

        public final String getDescription() {
            return this.description;
        }
    }
}
