package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class s {
    private static final bn a(TextView textView, bn bnVar) {
        if (textView == null) {
            return null;
        }
        textView.setVisibility(8);
        if (bnVar == null || !bnVar.a()) {
            return null;
        }
        return bnVar;
    }

    private static final bn a(TextView textView) {
        return a(textView, bn.f9052b.c());
    }

    private static final bn b(TextView textView) {
        return a(textView, bn.f9052b.a());
    }

    private static final bn c(TextView textView) {
        return a(textView, bn.f9052b.b());
    }

    private static final void a(TextView textView, int i, bn bnVar) {
        Integer numA;
        if (bnVar == null || (numA = bnVar.a(i)) == null) {
            return;
        }
        a(textView, new r(numA.intValue()));
    }

    @TargetApi(16)
    public static final void a(TextView textView, int i) {
        a(textView, i, a(textView));
    }

    @TargetApi(16)
    public static final void b(TextView textView, int i) {
        a(textView, i, b(textView));
    }

    @TargetApi(16)
    public static final void c(TextView textView, int i) {
        a(textView, i, c(textView));
    }

    @TargetApi(16)
    private static final void b(TextView textView, int i, bn bnVar) {
        if (bnVar == null) {
            return;
        }
        a(textView, new r(bnVar.b(i) ? 1 : 0));
    }

    @TargetApi(16)
    public static final void d(TextView textView, int i) {
        b(textView, i, a(textView));
    }

    @TargetApi(16)
    public static final void e(TextView textView, int i) {
        b(textView, i, b(textView));
    }

    @TargetApi(16)
    public static final void f(TextView textView, int i) {
        b(textView, i, c(textView));
    }

    @TargetApi(16)
    public static final void a(TextView textView, r rVar) {
        int i;
        if (textView == null) {
            return;
        }
        if (aj.c(rVar != null ? Boolean.valueOf(rVar.a()) : null)) {
            textView.setText(String.valueOf(rVar != null ? Integer.valueOf(rVar.b()) : null));
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
    }
}
