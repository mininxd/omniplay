package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
final class p extends k<String> {
    @Override // com.joaomgcd.taskerm.settings.k
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public String a(String str) {
        c.f.b.k.b(str, "value");
        return str;
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<String> a() {
        return String.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.settings.k
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, String str2) {
        c.f.b.k.b(editor, "receiver$0");
        c.f.b.k.b(str, "key");
        c.f.b.k.b(str2, "value");
        return editor.putString(str, str2);
    }

    @Override // com.joaomgcd.taskerm.settings.k
    public Class<?>[] b() {
        return new Class[]{String.class};
    }
}
