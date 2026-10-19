package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
final class n extends k<Integer> {
    @Override // com.joaomgcd.taskerm.settings.k
    public /* synthetic */ SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, Integer num) {
        return a(editor, str, num.intValue());
    }

    @Override // com.joaomgcd.taskerm.settings.k
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(String str) {
        c.f.b.k.b(str, "value");
        return c.l.n.c(str);
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<Integer> a() {
        return Integer.TYPE;
    }

    protected SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, int i) {
        c.f.b.k.b(editor, "receiver$0");
        c.f.b.k.b(str, "key");
        return editor.putInt(str, i);
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<?>[] b() {
        return new Class[]{Integer.class};
    }
}
