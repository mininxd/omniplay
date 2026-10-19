package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface ab {
    String getErrorMessage();

    public static final class a {
        public static void a(ab abVar, Context context) {
            c.f.b.k.b(context, "context");
            ak.c(abVar.getErrorMessage(), context);
        }
    }
}
