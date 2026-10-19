package com.joaomgcd.taskerm.util;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ao {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final HashMap<String, ap> f8983a = new HashMap<>();

    static /* synthetic */ void a(String str, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            th = (Throwable) null;
        }
        b(str, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(String str, Throwable th) {
        ak.a(str, "FFileObserver", false, th);
    }
}
