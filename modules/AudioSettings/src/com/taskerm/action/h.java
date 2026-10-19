package com.joaomgcd.taskerm.action;

import android.content.Context;
import com.joaomgcd.taskerm.util.bw;

/* JADX INFO: loaded from: classes.dex */
public interface h {
    String getApiKey(Context context);

    String getOverrideApiKey();

    public static final class a {
        public static String a(h hVar, Context context) {
            c.f.b.k.b(context, "context");
            String overrideApiKey = hVar.getOverrideApiKey();
            String str = overrideApiKey;
            return str == null || str.length() == 0 ? bw.c(context) : overrideApiKey;
        }
    }
}
