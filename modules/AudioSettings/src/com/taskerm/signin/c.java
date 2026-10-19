package com.joaomgcd.taskerm.signin;

import android.content.Context;
import b.a.p;
import c.a.z;
import c.f.b.k;
import c.f.b.l;
import c.q;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.net.ab;
import com.joaomgcd.taskerm.notification.ak;
import com.joaomgcd.taskerm.notification.ap;
import com.joaomgcd.taskerm.notification.as;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cp;
import java.util.HashMap;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8605a = new a(null);

    public static final class a {

        static final class f<T, R> implements b.a.d.g<T, R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final f f8615a = new f();

            f() {
            }

            public final boolean a(String str) {
                k.b(str, "it");
                return true;
            }

            @Override // b.a.d.g
            public /* synthetic */ Object apply(Object obj) {
                return Boolean.valueOf(a((String) obj));
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final com.google.android.gms.auth.api.signin.c c(Context context, com.joaomgcd.taskerm.signin.a aVar) {
            return com.google.android.gms.auth.api.signin.a.a(context, aVar.a());
        }

        private final com.google.android.gms.auth.api.signin.c e(Context context) {
            return com.google.android.gms.auth.api.signin.a.a(context, com.joaomgcd.taskerm.signin.d.b());
        }

        static final class j extends l implements c.f.a.a<b.a.l<GoogleSignInAccount>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f8622a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.signin.a f8623b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            j(Context context, com.joaomgcd.taskerm.signin.a aVar) {
                super(0);
                this.f8622a = context;
                this.f8623b = aVar;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.l<GoogleSignInAccount> invoke() {
                com.google.android.gms.c.e<GoogleSignInAccount> eVarB = c.f8605a.c(this.f8622a, this.f8623b).b();
                k.a((Object) eVarB, "context.getGoogleAccount…ient(args).silentSignIn()");
                return aj.a((com.google.android.gms.c.e) eVarB);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final b.a.l<GoogleSignInAccount> d(Context context, com.joaomgcd.taskerm.signin.a aVar) {
            return com.joaomgcd.taskerm.rx.i.a(new j(context, aVar));
        }

        static final class h extends l implements c.f.a.a<GoogleSignInAccount> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f8617a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.signin.a f8618b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            h(Context context, com.joaomgcd.taskerm.signin.a aVar) {
                super(0);
                this.f8617a = context;
                this.f8618b = aVar;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final GoogleSignInAccount invoke() {
                c.f8605a.d(this.f8617a).b();
                cl clVarB = new ActionGoogleSignIn(this.f8618b).run(this.f8617a).b();
                if (clVarB instanceof cm) {
                    throw new com.joaomgcd.taskerm.signin.b(((cm) clVarB).c());
                }
                if (clVarB == null) {
                    throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.util.SimpleResultSuccessWithPayload<com.google.android.gms.auth.api.signin.GoogleSignInAccount>");
                }
                return (GoogleSignInAccount) ((cp) clVarB).c();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final b.a.l<GoogleSignInAccount> e(Context context, com.joaomgcd.taskerm.signin.a aVar) {
            return com.joaomgcd.taskerm.rx.i.b(new h(context, aVar));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final at f(Context context, com.joaomgcd.taskerm.signin.a aVar) {
            String strB = aVar.b();
            if (strB == null) {
                strB = ah.a(context);
            }
            String strA = aj.a(R.string.dc_authorization_needed, context, new Object[0]);
            String strA2 = aj.a(R.string.dc_click_here_to_authorize_app, context, strB);
            aVar.a(true);
            return new at(context, strA, strA2, null, null, null, false, com.joaomgcd.taskerm.util.f.f9323b.a() ? new as(R.drawable.mw_hardware_security) : new as(ah.K(context)), null, strA + strA2, null, 0, 0L, new com.joaomgcd.taskerm.notification.c(context, new ActionGoogleSignIn(aVar), (String) null, (ap) null, 12, (c.f.b.g) null), false, false, null, null, null, new ak("GA", strA, "Shown when further authorizations for the user's Google account are needed.", 0, null, null, false, null, false, null, null, 2040, null), null, false, false, 7855480, null);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.signin.c$a$a, reason: collision with other inner class name */
        static final class C0231a extends l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String[] f8606a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Context f8607b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f8608c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ boolean f8609d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0231a(String[] strArr, Context context, String str, boolean z) {
                super(0);
                this.f8606a = strArr;
                this.f8607b = context;
                this.f8608c = str;
                this.f8609d = z;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String strA = c.a.d.a(this.f8606a, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null);
                boolean z = false;
                if (strA.length() == 0) {
                    strA = "https://www.googleapis.com/auth/userinfo.email";
                }
                try {
                    return com.google.android.gms.auth.b.a(this.f8607b, this.f8608c, "oauth2:" + strA);
                } catch (Exception unused) {
                    if (this.f8609d && ah.e(this.f8607b)) {
                        z = true;
                    }
                    if (z) {
                        c.f8605a.f(this.f8607b, new com.joaomgcd.taskerm.signin.a(null, null, false, this.f8606a, null, false, 55, null)).c().b();
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Can't get token for ");
                    sb.append(this.f8608c);
                    sb.append('.');
                    sb.append(z ? " Notification posted asking for authorization" : "");
                    sb.append(' ');
                    throw new com.joaomgcd.taskerm.signin.b(sb.toString());
                }
            }
        }

        public final b.a.l<String> a(Context context, String str, boolean z, String[] strArr) {
            k.b(context, "context");
            k.b(str, "account");
            k.b(strArr, "scopes");
            return com.joaomgcd.taskerm.rx.i.b(new C0231a(strArr, context, str, z));
        }

        static final class b extends l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f8610a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.signin.a f8611b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(Context context, com.joaomgcd.taskerm.signin.a aVar) {
                super(0);
                this.f8610a = context;
                this.f8611b = aVar;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                GoogleSignInAccount googleSignInAccountA = c.f8605a.a(this.f8610a);
                String strC = googleSignInAccountA != null ? googleSignInAccountA.c() : null;
                if (strC == null) {
                    c.f8605a.f(this.f8610a, this.f8611b).c().b();
                    throw new com.joaomgcd.taskerm.signin.b("No account currently signed in");
                }
                return c.f8605a.a(this.f8610a, strC, true, this.f8611b.e()).b();
            }
        }

        public final b.a.l<String> a(Context context, com.joaomgcd.taskerm.signin.a aVar) {
            k.b(context, "context");
            k.b(aVar, "args");
            return com.joaomgcd.taskerm.rx.i.b(new b(context, aVar));
        }

        static final class d<T, R> implements b.a.d.g<T, R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final d f8613a = new d();

            d() {
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final HashMap<String, String> apply(String str) {
                k.b(str, "it");
                return z.b(ab.a(str));
            }
        }

        public final b.a.l<HashMap<String, String>> b(Context context, com.joaomgcd.taskerm.signin.a aVar) {
            k.b(context, "context");
            k.b(aVar, "args");
            return c.f8605a.a(context, aVar).c(d.f8613a);
        }

        public final b.a.l<Boolean> a(Context context, String str, String[] strArr) {
            k.b(context, "context");
            k.b(str, "account");
            k.b(strArr, "scopes");
            b.a.l<Boolean> lVarD = a(context, str, false, strArr).c(f.f8615a).d(g.f8616a);
            k.a((Object) lVarD, "getToken(context, accoun…just(false)\n            }");
            return lVarD;
        }

        static final class g<T, R> implements b.a.d.g<Throwable, p<? extends Boolean>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final g f8616a = new g();

            g() {
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.l<Boolean> apply(Throwable th) {
                k.b(th, "it");
                return b.a.l.a(false);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.signin.c$a$c, reason: collision with other inner class name */
        static final class C0232c<T, R> implements b.a.d.g<T, R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0232c f8612a = new C0232c();

            C0232c() {
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final HashMap<String, String> apply(String str) {
                k.b(str, "it");
                return z.b(ab.a(str));
            }
        }

        public final b.a.l<HashMap<String, String>> b(Context context, String str, boolean z, String[] strArr) {
            k.b(context, "context");
            k.b(str, "account");
            k.b(strArr, "scopes");
            return a(context, str, z, strArr).c(C0232c.f8612a);
        }

        public final GoogleSignInAccount a(Context context) {
            k.b(context, "context");
            return com.google.android.gms.auth.api.signin.a.a(context);
        }

        public final boolean b(Context context) {
            k.b(context, "context");
            return a(context) != null;
        }

        public final String c(Context context) {
            String strC;
            k.b(context, "context");
            GoogleSignInAccount googleSignInAccountA = com.google.android.gms.auth.api.signin.a.a(context);
            if (googleSignInAccountA == null || (strC = googleSignInAccountA.c()) == null) {
                throw new com.joaomgcd.taskerm.signin.b("No account currently signed in");
            }
            return strC;
        }

        public static /* synthetic */ b.a.l a(a aVar, Context context, boolean z, com.joaomgcd.taskerm.signin.a aVar2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z = true;
            }
            if ((i2 & 4) != 0) {
                aVar2 = new com.joaomgcd.taskerm.signin.a(null, null, false, null, null, false, 63, null);
            }
            return aVar.a(context, z, aVar2);
        }

        static final class i extends l implements c.f.a.a<GoogleSignInAccount> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ boolean f8619a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Context f8620b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.signin.a f8621c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            i(boolean z, Context context, com.joaomgcd.taskerm.signin.a aVar) {
                super(0);
                this.f8619a = z;
                this.f8620b = context;
                this.f8621c = aVar;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final GoogleSignInAccount invoke() {
                GoogleSignInAccount googleSignInAccountA = (GoogleSignInAccount) null;
                if (this.f8619a) {
                    googleSignInAccountA = c.f8605a.a(this.f8620b);
                    String strC = googleSignInAccountA != null ? googleSignInAccountA.c() : null;
                    if (strC != null) {
                        try {
                            c.f8605a.a(this.f8620b, strC, false, this.f8621c.e()).b();
                            if (googleSignInAccountA != null) {
                                return googleSignInAccountA;
                            }
                            throw new RuntimeException("This should never happen. Account has to exist because email exists");
                        } catch (Exception unused) {
                            bn.b("GA", "Signed in account " + strC + " needs extra permissions: " + c.a.d.a(this.f8621c.e(), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null));
                        }
                    }
                }
                if (this.f8619a) {
                    try {
                        googleSignInAccountA = (GoogleSignInAccount) c.f8605a.d(this.f8620b, this.f8621c).b();
                    } catch (Exception unused2) {
                        googleSignInAccountA = null;
                    }
                }
                return googleSignInAccountA != null ? googleSignInAccountA : (GoogleSignInAccount) c.f8605a.e(this.f8620b, this.f8621c).b();
            }
        }

        public final b.a.l<GoogleSignInAccount> a(Context context, boolean z, com.joaomgcd.taskerm.signin.a aVar) {
            k.b(context, "context");
            k.b(aVar, "args");
            return com.joaomgcd.taskerm.rx.i.b(new i(z, context, aVar));
        }

        static final class e<T, R> implements b.a.d.g<Throwable, p<? extends co>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final e f8614a = new e();

            e() {
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.l<co> apply(Throwable th) {
                k.b(th, "it");
                if (!(th instanceof com.google.android.gms.common.api.b)) {
                    return b.a.l.b(th);
                }
                if (((com.google.android.gms.common.api.b) th).b() != 4) {
                    return b.a.l.b(th);
                }
                return b.a.l.a(new co());
            }
        }

        private final b.a.l<co> a(com.google.android.gms.c.e<Void> eVar) {
            b.a.l<co> lVarD = aj.b(eVar).d(e.f8614a);
            k.a((Object) lVarD, "singleVoid.onErrorResume…uccess())\n\n\n            }");
            return lVarD;
        }

        public final b.a.l<co> d(Context context) {
            k.b(context, "context");
            a aVar = this;
            com.google.android.gms.c.e<Void> eVarC = aVar.e(context).c();
            k.a((Object) eVarC, "context.googleAccountClientBasic.signOut()");
            return aVar.a(eVarC);
        }
    }
}
