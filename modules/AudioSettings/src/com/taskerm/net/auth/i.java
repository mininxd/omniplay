package com.joaomgcd.taskerm.net.auth;

import c.f.b.k;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes.dex */
public enum i {
    json("application/json"),
    form(HttpConnection.FORM_URL_ENCODED);


    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f7877d;

    i(String str) {
        k.b(str, "dataType");
        this.f7877d = str;
    }

    public final String a() {
        return this.f7877d;
    }
}
