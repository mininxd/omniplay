package com.joaomgcd.taskerm.google.language;

import androidx.annotation.Keep;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
@Keep
public enum DocumentType {
    TYPE_UNSPECIFIED("The content type is not specified."),
    PLAIN_TEXT("Plain text"),
    HTML("HTML");

    private final String description;

    DocumentType(String str) {
        k.b(str, "description");
        this.description = str;
    }

    public final String getDescription() {
        return this.description;
    }
}
