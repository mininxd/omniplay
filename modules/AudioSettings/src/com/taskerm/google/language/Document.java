package com.joaomgcd.taskerm.google.language;

import androidx.annotation.Keep;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class Document {
    private final String content;
    private final String gcsContentUri;
    private final String language;
    private final DocumentType type;

    public Document() {
        this(null, null, null, null, 15, null);
    }

    public Document(DocumentType documentType, String str, String str2, String str3) {
        this.type = documentType;
        this.content = str;
        this.gcsContentUri = str2;
        this.language = str3;
    }

    public /* synthetic */ Document(DocumentType documentType, String str, String str2, String str3, int i, g gVar) {
        this((i & 1) != 0 ? (DocumentType) null : documentType, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3);
    }

    public final DocumentType getType() {
        return this.type;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getGcsContentUri() {
        return this.gcsContentUri;
    }

    public final String getLanguage() {
        return this.language;
    }
}
