package com.joaomgcd.taskerm.function;

import c.f.a.a;
import c.f.b.l;
import c.l.n;
import c.q;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class FunctionArgs$trimmedParameters$2 extends l implements a<String[]> {
    final /* synthetic */ FunctionArgs this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FunctionArgs$trimmedParameters$2(FunctionArgs functionArgs) {
        super(0);
        this.this$0 = functionArgs;
    }

    @Override // c.f.a.a
    public final String[] invoke() {
        String[] parameters = this.this$0.getParameters();
        ArrayList arrayList = new ArrayList(parameters.length);
        for (String str : parameters) {
            if (str == null) {
                throw new q("null cannot be cast to non-null type kotlin.CharSequence");
            }
            arrayList.add(n.b((CharSequence) str).toString());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
