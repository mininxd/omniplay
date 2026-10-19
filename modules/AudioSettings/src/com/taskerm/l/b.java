package com.joaomgcd.taskerm.l;

import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.ak;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final boolean a(String str) {
        String strA;
        if (str != null) {
            if (str != null) {
                String string = n.b((CharSequence) str).toString();
                return string != null && (strA = ak.a(string, String.valueOf((char) 65279))) != null && n.a(strA, "<", false, 2, (Object) null) && n.b(strA, ">", false, 2, (Object) null);
            }
            throw new q("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return false;
    }
}
