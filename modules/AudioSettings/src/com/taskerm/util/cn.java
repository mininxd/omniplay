package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class cn {
    public static final cq<String, ac> a(Exception exc) {
        c.f.b.k.b(exc, "t");
        return new cq<>(false, null, new ac(exc));
    }

    public static final <TPayload> cq<TPayload, ad> a(TPayload tpayload) {
        return new cq<>(true, tpayload, null, 4, null);
    }

    public static final <TError extends ab> cq<?, TError> a(TError terror) {
        return new cq<>(false, null, terror);
    }

    public static /* synthetic */ cq a(String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        return a(str);
    }

    public static final cq<?, ad> a(String str) {
        return new cq<>(false, null, str == null ? null : new ad(str));
    }
}
