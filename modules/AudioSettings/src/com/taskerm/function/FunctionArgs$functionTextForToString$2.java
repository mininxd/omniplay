package com.joaomgcd.taskerm.function;

import c.a.d;
import c.f.a.a;
import c.f.a.b;
import c.f.b.k;
import c.f.b.l;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class FunctionArgs$functionTextForToString$2 extends l implements a<String> {
    final /* synthetic */ FunctionArgs this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FunctionArgs$functionTextForToString$2(FunctionArgs functionArgs) {
        super(0);
        this.this$0 = functionArgs;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.function.FunctionArgs$functionTextForToString$2$1, reason: invalid class name */
    static final class AnonymousClass1 extends l implements b<String, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        public final String invoke(String str) {
            k.b(str, "it");
            ReplaceSpecialMode replaceSpecialMode = ReplaceSpecialMode.ToString;
            c.k[] specials = FunctionBaseKt.getSpecials();
            return FunctionBaseKt.replaceSpecialStuff(str, replaceSpecialMode, (c.k[]) Arrays.copyOf(specials, specials.length));
        }
    }

    @Override // c.f.a.a
    public final String invoke() {
        return d.a(this.this$0.getParameters(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, AnonymousClass1.INSTANCE, 30, (Object) null);
    }
}
