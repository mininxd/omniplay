package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
final class m extends k<Float> {
    @Override // com.joaomgcd.taskerm.settings.k
    public /* synthetic */ SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, Float f2) {
        return a(editor, str, f2.floatValue());
    }

    @Override // com.joaomgcd.taskerm.settings.k
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Float a(String str) {
        c.f.b.k.b(str, "value");
        return c.l.n.a(str);
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<Float> a() {
        return Float.TYPE;
    }

    protected SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, float f2) {
        c.f.b.k.b(editor, "receiver$0");
        c.f.b.k.b(str, "key");
        return editor.putFloat(str, f2);
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<?>[] b() {
        return new Class[]{Float.class};
    }
}
