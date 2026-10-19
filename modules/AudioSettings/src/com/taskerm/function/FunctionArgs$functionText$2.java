package com.joaomgcd.taskerm.function;

import c.a.d;
import c.f.a.a;
import c.f.a.b;
import c.f.b.l;

/* JADX INFO: loaded from: classes.dex */
final class FunctionArgs$functionText$2 extends l implements a<String> {
    final /* synthetic */ FunctionArgs this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FunctionArgs$functionText$2(FunctionArgs functionArgs) {
        super(0);
        this.this$0 = functionArgs;
    }

    @Override // c.f.a.a
    public final String invoke() {
        return d.a(this.this$0.getParameters(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (b) null, 62, (Object) null);
    }
}
