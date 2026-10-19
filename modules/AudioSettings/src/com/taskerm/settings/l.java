package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
final class l extends k<Boolean> {
    @Override // com.joaomgcd.taskerm.settings.k
    public /* synthetic */ SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, Boolean bool) {
        return a(editor, str, bool.booleanValue());
    }

    @Override // com.joaomgcd.taskerm.settings.k
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Boolean a(String str) {
        boolean z;
        c.f.b.k.b(str, "value");
        if (c.f.b.k.a((Object) str, (Object) "true")) {
            z = true;
        } else {
            if (!c.f.b.k.a((Object) str, (Object) "false")) {
                return null;
            }
            z = false;
        }
        return Boolean.valueOf(z);
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<Boolean> a() {
        return Boolean.TYPE;
    }

    protected SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, boolean z) {
        c.f.b.k.b(editor, "receiver$0");
        c.f.b.k.b(str, "key");
        return editor.putBoolean(str, z);
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<?>[] b() {
        return new Class[]{Boolean.class};
    }
}
