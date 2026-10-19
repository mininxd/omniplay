package com.joaomgcd.taskerm.nfc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.q;
import c.s;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.helper.j;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cm;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityNFCTag extends Activity implements com.joaomgcd.taskerm.helper.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7973a = {x.a(new v(x.a(ActivityNFCTag.class), "helper", "getHelper()Lcom/joaomgcd/taskerm/helper/HelperActivity;")), x.a(new v(x.a(ActivityNFCTag.class), "nfcTasker", "getNfcTasker()Lcom/joaomgcd/taskerm/nfc/NFCTasker;")), x.a(new v(x.a(ActivityNFCTag.class), "startedForScanInForeground", "getStartedForScanInForeground()Z"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f7974b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7975c = c.f.a(new b());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7976d = c.f.a(new c());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f7977e = c.f.a(new h());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b.a.b.b f7978f;

    private final com.joaomgcd.taskerm.nfc.c b() {
        c.e eVar = this.f7976d;
        c.j.g gVar = f7973a[1];
        return (com.joaomgcd.taskerm.nfc.c) eVar.b();
    }

    private final boolean c() {
        c.e eVar = this.f7977e;
        c.j.g gVar = f7973a[2];
        return ((Boolean) eVar.b()).booleanValue();
    }

    public final com.joaomgcd.taskerm.helper.h<ActivityNFCTag> a() {
        c.e eVar = this.f7975c;
        c.j.g gVar = f7973a[0];
        return (com.joaomgcd.taskerm.helper.h) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.helper.b
    public void a(bt btVar, cm cmVar) {
        k.b(btVar, "permissions");
        k.b(cmVar, "result");
    }

    static final class d extends l implements c.f.a.b<com.joaomgcd.taskerm.nfc.b, b.a.b> {
        d() {
            super(1);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.nfc.ActivityNFCTag$d$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.nfc.b f7985b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(com.joaomgcd.taskerm.nfc.b bVar) {
                super(0);
                this.f7985b = bVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                NFCTagTasker nFCTagTaskerA = this.f7985b.a();
                if (nFCTagTaskerA == null || nFCTagTaskerA.getHasTextPayload() || !nFCTagTaskerA.isWritable()) {
                    return;
                }
                if (!((com.joaomgcd.taskerm.dialog.x) y.a(ActivityNFCTag.this, R.string.an_nfc_tag, R.string.dc_nfc_tag_not_formatted_offer_format, 0, 8, (Object) null).b()).d()) {
                    y.d(ActivityNFCTag.this, R.string.an_nfc_tag, R.string.dc_nfc_tag_not_formatted_with_test).b();
                    return;
                }
                String strA = aj.a(R.string.dc_nfc_tag_test_payload, ActivityNFCTag.this, new Object[0]);
                ac acVarA = ac.a.a(ac.f6217a, ActivityNFCTag.this, R.string.dc_formatting, false, 4, null);
                try {
                    try {
                        NFCTagTasker.write$default(nFCTagTaskerA, strA, null, 2, null).b();
                        ac.a(acVarA, null, 1, null);
                        y.c(ActivityNFCTag.this, R.string.an_nfc_tag, aj.a(R.string.dc_nfc_tag_formatted_with_test, ActivityNFCTag.this, strA)).b();
                    } catch (Exception unused) {
                        y.c(ActivityNFCTag.this, R.string.an_nfc_tag, aj.a(R.string.dc_nfc_tag_cant_format, ActivityNFCTag.this, strA)).b();
                    }
                } finally {
                    ac.a(acVarA, null, 1, null);
                }
            }
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.b invoke(com.joaomgcd.taskerm.nfc.b bVar) {
            k.b(bVar, "it");
            return i.d(new AnonymousClass1(bVar));
        }
    }

    static final class h extends l implements c.f.a.a<Boolean> {
        h() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            Intent intent = ActivityNFCTag.this.getIntent();
            return k.a((Object) (intent != null ? intent.getAction() : null), (Object) "ACTION_SCAN_IN_FOREGROUND");
        }
    }

    static final class b extends l implements c.f.a.a<com.joaomgcd.taskerm.helper.h<ActivityNFCTag>> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.helper.h<ActivityNFCTag> invoke() {
            return new com.joaomgcd.taskerm.helper.h<>(ActivityNFCTag.this);
        }
    }

    static final class c extends l implements c.f.a.a<com.joaomgcd.taskerm.nfc.c> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.nfc.c invoke() {
            return new com.joaomgcd.taskerm.nfc.c(ActivityNFCTag.this);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (c()) {
            return;
        }
        j.a(a(), com.joaomgcd.taskerm.nfc.c.a(b(), getIntent(), true, null, 4, null), (c.f.a.a) null, 2, (Object) null);
        if (ah.a(this, 2076, R.string.en_nfc_tag, R.string.dc_nfc_tag_explained, "https://youtu.be/t3cbS3aez6M")) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (c() && this.f7978f == null) {
            try {
                b().a(this);
                this.f7978f = y.a((Activity) this, R.string.dt_scan_nfc, R.string.dc_scan_nfc_tag, 0, false, 24, (Object) null).a(new f(), new g());
            } catch (Exception e2) {
                i.a(this, e2);
                d();
            }
        }
    }

    static final class f<T> implements b.a.d.f<com.joaomgcd.taskerm.dialog.x> {
        f() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(com.joaomgcd.taskerm.dialog.x xVar) {
            k.b(xVar, "it");
            ActivityNFCTag.this.d();
        }
    }

    static final class g<T> implements b.a.d.f<Throwable> {
        g() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            k.b(th, "it");
            ActivityNFCTag.this.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d() {
        b().e();
        finish();
    }

    @Override // android.app.Activity
    protected void onPause() {
        try {
            if (c()) {
                b().b(this);
            }
            b.a.b.b bVar = this.f7978f;
            if (bVar != null) {
                bVar.b();
            }
        } catch (Exception e2) {
            i.a(this, e2);
        }
        super.onPause();
    }

    public static final class a {

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.nfc.ActivityNFCTag$a$a, reason: collision with other inner class name */
        static final class C0202a extends l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.nfc.b>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f7979a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0202a(Context context) {
                super(0);
                this.f7979a = context;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.nfc.ActivityNFCTag$a$a$a, reason: collision with other inner class name */
            public static final class C0203a<T> implements b.a.d.i<Object> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final C0203a f7980a = new C0203a();

                @Override // b.a.d.i
                public final boolean a(Object obj) {
                    k.b(obj, "it");
                    return obj instanceof com.joaomgcd.taskerm.nfc.b;
                }
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.l<com.joaomgcd.taskerm.nfc.b> invoke() {
                com.joaomgcd.taskerm.nfc.a.a(this.f7979a, true).c();
                Intent intent = new Intent(this.f7979a, (Class<?>) ActivityNFCTag.class);
                intent.setAction("ACTION_SCAN_IN_FOREGROUND");
                intent.setFlags(268435456);
                this.f7979a.startActivity(intent);
                b.a.h<Object> hVarA = com.joaomgcd.taskerm.rx.b.f8378b.a().a((b.a.d.i<? super Object>) C0203a.f7980a);
                if (hVarA != null) {
                    b.a.l lVarD = hVarA.d();
                    if (lVarD != null) {
                        return lVarD;
                    }
                    throw new q("null cannot be cast to non-null type io.reactivex.Single<T>");
                }
                throw new q("null cannot be cast to non-null type io.reactivex.Observable<T>");
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final b.a.l<com.joaomgcd.taskerm.nfc.b> a(Context context) {
            k.b(context, "context");
            return i.a(new C0202a(context));
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (c()) {
            com.joaomgcd.taskerm.helper.h<ActivityNFCTag> hVarA = a();
            b.a.b bVarC = b().a(intent, false, new d()).c(new e());
            k.a((Object) bVarC, "nfcTasker.reportTagScann…       finish()\n        }");
            j.a(hVarA, bVarC, (c.f.a.a) null, 2, (Object) null);
        }
    }

    static final class e implements b.a.d.a {
        e() {
        }

        @Override // b.a.d.a
        public final void run() {
            ActivityNFCTag.this.finish();
        }
    }
}
