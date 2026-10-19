package com.joaomgcd.taskerm.function;

import c.f.a.a;
import c.f.b.l;

/* JADX INFO: loaded from: classes.dex */
final class FunctionBase$Companion$callsFunction$1$functionBase$1 extends l implements a<FunctionBase<?, ?>> {
    final /* synthetic */ FunctionArgs $it;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FunctionBase$Companion$callsFunction$1$functionBase$1(FunctionArgs functionArgs) {
        super(0);
        this.$it = functionArgs;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // c.f.a.a
    public final FunctionBase<?, ?> invoke() {
        return FunctionBase.Companion.get(this.$it);
    }
}
