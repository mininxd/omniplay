package com.joaomgcd.taskerm.util;

import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public final class ba {
    public static final <T> ax<T> a(c.f.a.a<? extends T> aVar) {
        c.f.b.k.b(aVar, "initializer");
        return new ax<>(aVar, null, 2, null);
    }

    public static final <TMessage> ay<TMessage> a(String str, c.f.a.b<? super TMessage, c.s> bVar) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(bVar, "handleMessage");
        return new ay<>(str, bVar);
    }

    public static final az a(String str) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        return new az(str);
    }
}
