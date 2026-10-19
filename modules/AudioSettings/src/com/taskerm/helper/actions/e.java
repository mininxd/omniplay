package com.joaomgcd.taskerm.helper.actions;

import c.l.n;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (n.b(str2, "()", false, 2, (Object) null)) {
            str2 = n.c(str2, "()", null, 2, null);
        }
        return str2 + '(' + str + ')';
    }
}
