package com.joaomgcd.taskerm.net.a;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public enum a {
    PlainText("text/plain; charset=UTF-8"),
    HTML("text/html; charset=UTF-8"),
    Binary("application/octet-stream"),
    JSON("application/json; charset=UTF-8"),
    XML("text/xml; charset=UTF-8");

    private final String g;

    a(String str) {
        k.b(str, "mimeType");
        this.g = str;
    }

    public final String a() {
        return this.g;
    }
}
