package com.joaomgcd.taskerm.keyboard;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append('(');
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(')');
        return sb.toString();
    }
}
