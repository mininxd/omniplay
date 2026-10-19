package com.joaomgcd.taskerm.action.audio;

import com.joaomgcd.taskerm.util.AppBasic;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static AppBasic f4960a;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean e(String str) {
        return c.l.n.b((CharSequence) str, (CharSequence) ":", false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean f(String str) {
        return c.l.n.b((CharSequence) str, (CharSequence) "most(", false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean g(String str) {
        return c.l.n.b((CharSequence) str, (CharSequence) "last(", false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean h(String str) {
        return g(str) && c.l.n.b((CharSequence) str, (CharSequence) "s)", false, 2, (Object) null);
    }
}
