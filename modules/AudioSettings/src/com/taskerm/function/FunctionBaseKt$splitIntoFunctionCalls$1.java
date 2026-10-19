package com.joaomgcd.taskerm.function;

import c.f.a.a;
import c.f.b.l;
import c.l.n;
import c.s;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class FunctionBaseKt$splitIntoFunctionCalls$1 extends l implements a<s> {
    final /* synthetic */ ArrayList $result;
    final /* synthetic */ StringBuilder $sb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FunctionBaseKt$splitIntoFunctionCalls$1(ArrayList arrayList, StringBuilder sb) {
        super(0);
        this.$result = arrayList;
        this.$sb = sb;
    }

    @Override // c.f.a.a
    public /* bridge */ /* synthetic */ s invoke() {
        invoke2();
        return s.f2131a;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        this.$result.add(this.$sb.toString());
        n.a(this.$sb);
    }
}
