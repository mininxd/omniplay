package com.joaomgcd.taskerm.action.f;

/* JADX INFO: loaded from: classes.dex */
public final class i {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(String str, String str2) {
        String strA = com.joaomgcd.taskerm.util.ak.A(str2);
        return strA != null ? strA : a(str);
    }

    private static final String a(String str) {
        return (str == null || !c.l.n.b((CharSequence) str, (CharSequence) "\n", false, 2, (Object) null)) ? "," : "\n";
    }
}
