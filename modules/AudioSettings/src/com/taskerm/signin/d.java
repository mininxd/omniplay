package com.joaomgcd.taskerm.signin;

import c.e;
import c.f;
import c.f.b.l;
import c.f.b.t;
import c.f.b.x;
import c.j.g;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f8624a = {x.a(new t(x.a(d.class, "Tasker(5.15.14)_marketNoTrialRelease"), "signInOptionsBasic", "getSignInOptionsBasic()Lcom/google/android/gms/auth/api/signin/GoogleSignInOptions;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final e f8625b = f.a(a.f8626a);

    /* JADX INFO: Access modifiers changed from: private */
    public static final GoogleSignInOptions b() {
        e eVar = f8625b;
        g gVar = f8624a[0];
        return (GoogleSignInOptions) eVar.b();
    }

    static final class a extends l implements c.f.a.a<GoogleSignInOptions> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8626a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final GoogleSignInOptions invoke() {
            return new com.joaomgcd.taskerm.signin.a(null, null, false, null, null, false, 63, null).a();
        }
    }
}
