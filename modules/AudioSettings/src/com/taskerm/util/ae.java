package com.joaomgcd.taskerm.util;

import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public final class ae extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Uri f8883a;

    public ae(Uri uri) {
        c.f.b.k.b(uri, "uri");
        this.f8883a = uri;
    }
}
