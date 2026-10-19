package com.joaomgcd.taskerm.function;

import android.content.Intent;
import c.f.a.b;
import c.f.b.k;
import c.f.b.l;
import c.s;

/* JADX INFO: loaded from: classes.dex */
final class TermuxCommand$doIt$intent$1$3 extends l implements b<String[], s> {
    final /* synthetic */ Intent $this_apply;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TermuxCommand$doIt$intent$1$3(Intent intent) {
        super(1);
        this.$this_apply = intent;
    }

    @Override // c.f.a.b
    public /* bridge */ /* synthetic */ s invoke(String[] strArr) {
        invoke2(strArr);
        return s.f2131a;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String[] strArr) {
        k.b(strArr, "it");
        this.$this_apply.putExtra("com.termux.RUN_COMMAND_ARGUMENTS", strArr);
    }
}
