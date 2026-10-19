package com.joaomgcd.taskerm.securesettings;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ag extends ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8473a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final p f8474b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ag(Context context) {
        super(context);
        c.f.b.k.b(context, "context");
        this.f8474b = p.Secure;
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    public p b() {
        return this.f8474b;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }
}
