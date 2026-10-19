package com.joaomgcd.taskerm.google.language;

import androidx.annotation.Keep;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RequestClassifyText {
    private final Document document;

    /* JADX WARN: Multi-variable type inference failed */
    public RequestClassifyText() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public RequestClassifyText(Document document) {
        this.document = document;
    }

    public /* synthetic */ RequestClassifyText(Document document, int i, g gVar) {
        this((i & 1) != 0 ? (Document) null : document);
    }

    public final Document getDocument() {
        return this.document;
    }
}
