package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public abstract class cl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f9223a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f9224b;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }

    private cl(boolean z) {
        this.f9224b = z;
    }

    public /* synthetic */ cl(boolean z, c.f.b.g gVar) {
        this(z);
    }

    public final boolean b() {
        return this.f9224b;
    }

    public final String a() {
        ab abVarE;
        String errorMessage;
        if (this instanceof cm) {
            String strC = ((cm) this).c();
            if (strC != null) {
                return strC;
            }
        } else if ((this instanceof cq) && (abVarE = ((cq) this).e()) != null && (errorMessage = abVarE.getErrorMessage()) != null) {
            return errorMessage;
        }
        return "Unkown error";
    }
}
