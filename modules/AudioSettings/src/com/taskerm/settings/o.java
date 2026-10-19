package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
final class o extends k<Long> {
    @Override // com.joaomgcd.taskerm.settings.k
    public /* synthetic */ SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, Long l) {
        return a(editor, str, l.longValue());
    }

    @Override // com.joaomgcd.taskerm.settings.k
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Long a(String str) {
        c.f.b.k.b(str, "value");
        return c.l.n.d(str);
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<Long> a() {
        return Long.TYPE;
    }

    protected SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, long j) {
        c.f.b.k.b(editor, "receiver$0");
        c.f.b.k.b(str, "key");
        return editor.putLong(str, j);
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<?>[] b() {
        return new Class[]{Long.class};
    }
}
