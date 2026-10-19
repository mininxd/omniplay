package com.joaomgcd.taskerm.net;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class j extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InputStream f7901a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(String str, InputStream inputStream) {
        super("Couldn't get Input stream from " + str);
        c.f.b.k.b(str, "url");
        c.f.b.k.b(inputStream, "inputStreamError");
        this.f7901a = inputStream;
    }
}
