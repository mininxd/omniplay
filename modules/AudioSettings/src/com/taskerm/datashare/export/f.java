package com.joaomgcd.taskerm.datashare.export;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import b.a.p;
import c.f.a.m;
import c.f.b.k;
import c.f.b.l;
import c.f.b.w;
import c.q;
import c.s;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.h;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestMissingPermissions;
import com.joaomgcd.taskerm.retrofit.j;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dm;
import com.joaomgcd.taskerservercommon.response.ResponseAddDataShares;
import cyanogenmod.app.ProfileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.ax;
import net.dinglisch.android.taskerm.ay;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.dl;
import net.dinglisch.android.taskerm.ds;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.ga;
import net.dinglisch.android.taskerm.gn;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final String a() {
        return "d";
    }

    public static final String b() {
        return "b";
    }

    public static final String c() {
        return "fulldatasharelink";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String e() {
        return "ShareDataConfig";
    }

    public static final void a(Activity activity, ay ayVar, fx fxVar, int i, boolean z) {
        k.b(activity, "activity");
        k.b(ayVar, "importable");
        k.b(fxVar, "data");
        i.c(new e(fxVar, activity, ac.a.a(ac.f6217a, activity, R.string.dt_exporting_progress, false, 4, null), z, ayVar, i));
    }

    static final class e extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ fx f6182a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f6183b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ac f6184c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f6185d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ay f6186e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ int f6187f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(fx fxVar, Activity activity, ac acVar, boolean z, ay ayVar, int i) {
            super(0);
            this.f6182a = fxVar;
            this.f6183b = activity;
            this.f6184c = acVar;
            this.f6185d = z;
            this.f6186e = ayVar;
            this.f6187f = i;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            try {
                bn.b(f.e(), "Caching full backup for export to link");
                this.f6182a.a(0).a(this.f6183b, f.c(), 0);
                bn.b(f.e(), "Full backup for export to link cached");
                ac.a(this.f6184c, null, 1, null);
                if (!this.f6185d) {
                    bn.b(f.e(), "Starting share to link activity with " + this.f6186e.l());
                    this.f6183b.startActivityForResult(new Intent(this.f6183b, (Class<?>) ShareDataConfig.class).putExtra(f.a(), this.f6186e.r()), this.f6187f);
                } else {
                    String strA = this.f6182a.a(this.f6183b, this.f6186e, 2);
                    k.a((Object) strA, "data.getAsUrl(activity, …PackedObject.FLAG_EXPORT)");
                    Object objB = y.a(this.f6183b, R.string.ml_export_to_uri, R.string.tip_export_as_data_uri, 0, false, (String) null, 56, (Object) null).b();
                    k.a(objB, "dialogOkCancel(activity,…s_data_uri).blockingGet()");
                    if (((x) objB).d()) {
                        f.b(this.f6183b, strA, this.f6186e);
                    }
                }
            } catch (Throwable th) {
                bn.b(f.e(), "Couldn't start sharing to link", th);
                ai.a(th, this.f6183b);
            }
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$f, reason: collision with other inner class name */
    static final class C0124f extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.datashare.export.e f6188a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ay f6189b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f6190c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ fx f6191d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0124f(com.joaomgcd.taskerm.datashare.export.e eVar, ay ayVar, Activity activity, fx fxVar) {
            super(0);
            this.f6188a = eVar;
            this.f6189b = ayVar;
            this.f6190c = activity;
            this.f6191d = fxVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() throws Throwable {
            a();
            return s.f2131a;
        }

        public final void a() throws Throwable {
            ac acVarA;
            ac acVar = (ac) null;
            try {
                try {
                    com.joaomgcd.taskerm.datashare.export.e eVar = this.f6188a;
                    String strB = eVar != null ? eVar.b() : null;
                    if (this.f6189b != null && strB != null) {
                        acVarA = ac.a.a(ac.f6217a, this.f6190c, R.string.dt_exporting_progress, false, 4, null);
                        try {
                            String strA = this.f6191d.a(this.f6190c, this.f6189b, 2);
                            k.a((Object) strA, "taskerData.getAsUrl(acti…PackedObject.FLAG_EXPORT)");
                            String strL = this.f6189b.l();
                            com.joaomgcd.taskerm.datashare.export.a.C0120a c0120a = com.joaomgcd.taskerm.datashare.export.a.f6113a;
                            k.a((Object) strL, ProfileManager.EXTRA_PROFILE_NAME);
                            ResponseAddDataShares responseAddDataSharesB = com.joaomgcd.taskerm.datashare.export.a.f6113a.a(this.f6190c).a(c0120a.a(strL, strB, strA, this.f6188a.d(), this.f6188a.e())).b();
                            ac.a(acVarA, null, 1, null);
                            String strC = this.f6189b.c();
                            Activity activity = this.f6190c;
                            int i = R.string.button_label_done;
                            Activity activity2 = this.f6190c;
                            k.a((Object) strC, "typeName");
                            x xVarB = y.a(new h(activity, i, aj.a(R.string.dc_data_successfully_shared, activity2, strC, strL), R.string.plugin_open, R.string.ml_copy, R.string.word_share, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null)).b();
                            if (xVarB.f()) {
                                if (acVarA != null) {
                                    ac.a(acVarA, null, 1, null);
                                    return;
                                }
                                return;
                            }
                            String strA2 = j.a(this.f6190c, responseAddDataSharesB.getShare().getUrl());
                            if (xVarB.d()) {
                                ah.a((Context) this.f6190c, strA2, false, false, false, 14, (Object) null);
                            } else if (xVarB.e()) {
                                ah.m(this.f6190c, strA2);
                            } else if (xVarB.g()) {
                                f.b(this.f6190c, strA2, this.f6189b);
                            }
                            if (acVarA != null) {
                                ac.a(acVarA, null, 1, null);
                                return;
                            }
                            return;
                        } catch (b.a.c.a e2) {
                            e = e2;
                            acVar = acVarA;
                            Activity activity3 = this.f6190c;
                            String strA3 = aj.a(R.string.x_number_of_errors, this.f6190c, Integer.valueOf(e.a().size()));
                            List<Throwable> listA = e.a();
                            k.a((Object) listA, "ex.exceptions");
                            List<Throwable> list = listA;
                            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
                            Iterator<T> it = list.iterator();
                            while (it.hasNext()) {
                                arrayList.add(((Throwable) it.next()).getMessage());
                            }
                            y.a(activity3, strA3, c.a.j.a(arrayList, "\n\n", null, null, 0, null, null, 62, null), (com.joaomgcd.taskerm.util.h) null, 8, (Object) null).b();
                            if (acVar == null) {
                                return;
                            }
                            ac.a(acVar, null, 1, null);
                            return;
                        } catch (Exception e3) {
                            e = e3;
                            acVar = acVarA;
                            y.c(this.f6190c, R.string.word_error, e.getMessage()).b();
                            if (acVar == null) {
                                return;
                            }
                            ac.a(acVar, null, 1, null);
                            return;
                        } catch (Throwable th) {
                            th = th;
                            if (acVarA != null) {
                                ac.a(acVarA, null, 1, null);
                            }
                            throw th;
                        }
                    }
                    y.a(new h(this.f6190c, R.string.word_error, "No data to share", R.string.button_label_ok, 0, 0, false, 0, (String) null, 0, (Integer) null, 2032, (c.f.b.g) null)).b();
                } catch (Throwable th2) {
                    th = th2;
                    acVarA = acVar;
                }
            } catch (b.a.c.a e4) {
                e = e4;
            } catch (Exception e5) {
                e = e5;
            }
        }
    }

    public static final void a(Activity activity, fx fxVar, ay ayVar, com.joaomgcd.taskerm.datashare.export.e eVar) {
        k.b(activity, "activity");
        k.b(fxVar, "taskerData");
        i.c(new C0124f(eVar, ayVar, activity, fxVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(Activity activity, String str, ay ayVar) {
        ah.a(activity, new cj(str, "Check out my Tasker " + ayVar.c() + " \"" + ayVar.l() + "!\"", 0, 4, null));
    }

    public static final b.a.l<cl> a(Activity activity) {
        k.b(activity, "context");
        return i.b(new c(activity));
    }

    static final class c extends l implements c.f.a.a<cl> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6169a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Activity activity) {
            super(0);
            this.f6169a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() {
            Uri data;
            Intent intent = this.f6169a.getIntent();
            if (intent == null || (data = intent.getData()) == null) {
                Intent intent2 = this.f6169a.getIntent();
                data = intent2 != null ? (Uri) intent2.getParcelableExtra("android.intent.extra.STREAM") : null;
            }
            if (data == null) {
                Intent intent3 = this.f6169a.getIntent();
                ArrayList parcelableArrayListExtra = intent3 != null ? intent3.getParcelableArrayListExtra("android.intent.extra.STREAM") : null;
                if (parcelableArrayListExtra == null) {
                    throw new q("null cannot be cast to non-null type java.util.ArrayList<android.net.Uri>");
                }
                data = (Uri) c.a.j.f((List) parcelableArrayListExtra);
            }
            if (data == null) {
                return new cm("No data to import");
            }
            return f.a(this.f6169a, false, (m<? super fx, ? super Integer, ? extends ay>) new a(data)).b();
        }

        static final class a extends l implements m<fx, Integer, ay> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Uri f6171b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Uri uri) {
                super(2);
                this.f6171b = uri;
            }

            @Override // c.f.a.m
            public /* synthetic */ ay invoke(fx fxVar, Integer num) {
                return a(fxVar, num.intValue());
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$c$a$1, reason: invalid class name */
            static final class AnonymousClass1 extends l implements c.f.a.a<ax> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ fx f6173b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int f6174c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(fx fxVar, int i) {
                    super(0);
                    this.f6173b = fxVar;
                    this.f6174c = i;
                }

                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final ax invoke() throws IOException {
                    ax axVarA = this.f6173b.a(c.this.f6169a, a.this.f6171b, this.f6174c);
                    k.a((Object) axVarA, "importFromContentUri(context, uri, it)");
                    return axVarA;
                }
            }

            public final ay a(fx fxVar, int i) {
                k.b(fxVar, "receiver$0");
                return f.b(c.this.f6169a, new AnonymousClass1(fxVar, i));
            }
        }
    }

    static final class a extends l implements c.f.a.b<String, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6132a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(1);
            this.f6132a = activity;
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(String str) {
            a(str);
            return s.f2131a;
        }

        public final void a(String str) {
            k.b(str, "error");
            x xVarB = y.a(new h(this.f6132a, R.string.word_error, this.f6132a.getString(R.string.dc_import_failed_tasker_version, new Object[]{str}), R.string.button_label_ok, R.string.button_label_check_update, R.string.button_label_beta, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null)).b();
            if (xVarB.e()) {
                ah.w(this.f6132a);
            } else if (xVarB.g()) {
                ah.v(this.f6132a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ay b(Activity activity, c.f.a.a<? extends ax> aVar) {
        a aVar2 = new a(activity);
        try {
            ax axVarInvoke = aVar.invoke();
            if (axVarInvoke.a()) {
                String strA = axVarInvoke.a(activity);
                k.a((Object) strA, "result.getErr(context)");
                aVar2.a(strA);
                return null;
            }
            return axVarInvoke.b();
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = e2.toString();
            }
            aVar2.a(message);
            return null;
        }
    }

    public static final b.a.l<cl> a(Activity activity, boolean z) {
        k.b(activity, "context");
        return i.b(new d(activity, z));
    }

    static final class d extends l implements c.f.a.a<cl> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6175a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f6176b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Activity activity, boolean z) {
            super(0);
            this.f6175a = activity;
            this.f6176b = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v5, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r6v8, types: [T, java.lang.String] */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() {
            Uri data;
            ?? string;
            ac acVarA = ac.a.a(ac.f6217a, this.f6175a, R.string.dc_checking_tasker_link, false, 4, null);
            try {
                w.d dVar = new w.d();
                Intent intent = this.f6175a.getIntent();
                if (intent == null || (data = intent.getData()) == null || (string = data.toString()) == 0) {
                    cm cmVar = new cm("No data to import");
                    ac.a(acVarA, null, 1, null);
                    return cmVar;
                }
                k.a((Object) string, "context.intent?.data?.to…rror(\"No data to import\")");
                dVar.f2028a = string;
                com.joaomgcd.taskerm.datashare.export.a aVarA = com.joaomgcd.taskerm.datashare.export.a.f6113a.a(this.f6175a);
                if (!com.joaomgcd.taskerm.signin.c.f8605a.b(this.f6175a)) {
                    com.joaomgcd.taskerm.signin.c.a.a(com.joaomgcd.taskerm.signin.c.f8605a, this.f6175a, false, null, 4, null).b();
                }
                try {
                    g gVarA = com.joaomgcd.taskerm.datashare.export.a.f6113a.a(this.f6175a, (String) dVar.f2028a, false, aVarA);
                    if (gVarA != null) {
                        dVar.f2028a = gVarA.c();
                    }
                    ac.a(acVarA, null, 1, null);
                    cl clVarB = f.a(this.f6175a, this.f6176b, new a(dVar)).b();
                    if (this.f6176b) {
                        ac.a(acVarA, null, 1, null);
                        return clVarB;
                    }
                    if (clVarB.b() && gVarA != null) {
                        aVarA.a(gVarA.b(), gVarA.a(), false, true).b();
                    }
                    ac.a(acVarA, null, 1, null);
                    return clVarB;
                } catch (Exception e2) {
                    cq cqVarA = cn.a(new com.joaomgcd.taskerm.util.ac(e2));
                    ac.a(acVarA, null, 1, null);
                    return cqVarA;
                }
            } catch (Throwable th) {
                ac.a(acVarA, null, 1, null);
                throw th;
            }
        }

        static final class a extends l implements m<fx, Integer, ay> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ w.d f6178b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(w.d dVar) {
                super(2);
                this.f6178b = dVar;
            }

            @Override // c.f.a.m
            public /* synthetic */ ay invoke(fx fxVar, Integer num) {
                return a(fxVar, num.intValue());
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$d$a$1, reason: invalid class name */
            static final class AnonymousClass1 extends l implements c.f.a.a<ax> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ fx f6180b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int f6181c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(fx fxVar, int i) {
                    super(0);
                    this.f6180b = fxVar;
                    this.f6181c = i;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final ax invoke() {
                    ax axVarA = this.f6180b.a(d.this.f6175a, (String) a.this.f6178b.f2028a, this.f6181c);
                    k.a((Object) axVarA, "importFromUrl(context, data, it)");
                    return axVarA;
                }
            }

            public final ay a(fx fxVar, int i) {
                k.b(fxVar, "receiver$0");
                return f.b(d.this.f6175a, new AnonymousClass1(fxVar, i));
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.joaomgcd.taskerm.dialog.ac] */
    public static final b.a.l<cl> a(Activity activity, boolean z, m<? super fx, ? super Integer, ? extends ay> mVar) {
        k.b(activity, "context");
        k.b(mVar, "import");
        w.d dVar = new w.d();
        dVar.f2028a = (ac) 0;
        return i.b(new b(activity, dVar, mVar, z));
    }

    static final class b extends l implements c.f.a.a<cl> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6133a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ w.d f6134b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ m f6135c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f6136d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Activity activity, w.d dVar, m mVar, boolean z) {
            super(0);
            this.f6133a = activity;
            this.f6134b = dVar;
            this.f6135c = mVar;
            this.f6136d = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v28, types: [T, com.joaomgcd.taskerm.dialog.ac] */
        /* JADX WARN: Type inference failed for: r3v6, types: [T, com.joaomgcd.taskerm.dialog.ac] */
        /* JADX WARN: Type inference failed for: r5v1, types: [T, com.joaomgcd.taskerm.dialog.ac] */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() {
            ArrayList arrayList;
            String strC;
            c.f.a.a<s> aVarC;
            Object next;
            int iG;
            List<ay> listF;
            try {
                if (!gr.d(this.f6133a)) {
                    y.d(this.f6133a, R.string.an_import_data, R.string.dc_please_accept_disclaimer_first).b();
                    ah.S(this.f6133a);
                    cm cmVar = new cm("User didn't accept Tasker terms yet.");
                    ac acVar = (ac) this.f6134b.f2028a;
                    if (acVar != null) {
                        ac.a(acVar, null, 1, null);
                        s sVar = s.f2131a;
                    }
                    return cmVar;
                }
                this.f6134b.f2028a = ac.a.a(ac.f6217a, this.f6133a, R.string.an_import_data, false, 4, null);
                fx fxVarA = fx.a(this.f6133a);
                k.a((Object) fxVarA, "TaskerData.getNewWithEmptyProject(context)");
                ay ayVar = (ay) this.f6135c.invoke(fxVarA, 0);
                if (ayVar == null) {
                    cm cmVar2 = new cm("Couldn't try to import");
                    ac acVar2 = (ac) this.f6134b.f2028a;
                    if (acVar2 != null) {
                        ac.a(acVar2, null, 1, null);
                        s sVar2 = s.f2131a;
                    }
                    return cmVar2;
                }
                String strC2 = ayVar.c();
                String strL = ayVar.l();
                if (this.f6136d) {
                    cq cqVarA = cn.a(new c.k(fxVarA, ayVar));
                    ac acVar3 = (ac) this.f6134b.f2028a;
                    if (acVar3 != null) {
                        ac.a(acVar3, null, 1, null);
                        s sVar3 = s.f2131a;
                    }
                    return cqVarA;
                }
                Activity activity = this.f6133a;
                int i = 2;
                k.a((Object) strC2, "type");
                k.a((Object) strL, ProfileManager.EXTRA_PROFILE_NAME);
                String strA = aj.a(R.string.dc_sure_import, activity, strC2, strL);
                boolean z = false;
                while (!z) {
                    x xVarB = y.a(this.f6133a, R.string.an_import_data, strA, R.string.button_label_view_description).b();
                    if (xVarB.h()) {
                        cm cmVar3 = new cm("User cancelled");
                        ac acVar4 = (ac) this.f6134b.f2028a;
                        if (acVar4 != null) {
                            ac.a(acVar4, null, 1, null);
                            s sVar4 = s.f2131a;
                        }
                        return cmVar3;
                    }
                    if (xVarB.g()) {
                        String strA2 = ayVar.a(this.f6133a, fxVarA, new com.joaomgcd.taskerm.f.g(false));
                        if (y.a(new h(this.f6133a, R.string.pl_description, strA2, R.string.button_label_ok, -1, R.string.ml_copy_to_clipboard, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null)).b().g()) {
                            Activity activity2 = this.f6133a;
                            k.a((Object) strA2, "description");
                            ah.m(activity2, strA2);
                        }
                    } else {
                        z = true;
                    }
                }
                fx fxVarE = fx.e(this.f6133a);
                if (ayVar instanceof fs) {
                    arrayList = c.a.j.a(fxVarE.h(strL));
                } else if (ayVar instanceof ga) {
                    arrayList = c.a.j.a(fxVarE.k(strL));
                } else if (ayVar instanceof dl) {
                    arrayList = new ArrayList();
                    arrayList.add(fxVarE.o(strL));
                    C0123b c0123b = new C0123b(fxVarE, ayVar, arrayList);
                    List<ga> listB = ((dl) ayVar).b(fxVarA);
                    if (listB != null) {
                        for (ga gaVar : listB) {
                            k.a((Object) gaVar, "it");
                            c0123b.a(fxVarE.k(gaVar.l()));
                        }
                        s sVar5 = s.f2131a;
                    }
                    List<fs> listA = ((dl) ayVar).a(fxVarA);
                    if (listA != null) {
                        for (fs fsVar : listA) {
                            k.a((Object) fsVar, "it");
                            c0123b.a(fxVarE.h(fsVar.l()));
                        }
                        s sVar6 = s.f2131a;
                    }
                    ArrayList<ds> arrayListB = fxVarA.b(-2, false);
                    if (arrayListB != null) {
                        for (ds dsVar : arrayListB) {
                            k.a((Object) dsVar, "it");
                            c0123b.a(fxVarE.s(dsVar.l()));
                        }
                        s sVar7 = s.f2131a;
                    }
                } else {
                    arrayList = null;
                }
                ay ayVar2 = arrayList != null ? (ay) c.a.j.a(arrayList, 0) : null;
                if (arrayList != null && (listF = c.a.j.f((Iterable) arrayList)) != null) {
                    for (ay ayVar3 : listF) {
                        Activity activity3 = this.f6133a;
                        Activity activity4 = this.f6133a;
                        Object[] objArr = new Object[i];
                        String strC3 = ayVar3.c();
                        k.a((Object) strC3, "existing.typeName");
                        objArr[0] = strC3;
                        String strL2 = ayVar3.l();
                        k.a((Object) strL2, "existing.name");
                        objArr[1] = strL2;
                        x xVar = (x) y.a(activity3, R.string.ml_import, aj.a(R.string.dc_import_alread_exists_overwrite, activity4, objArr), 0, 8, (Object) null).b();
                        if (xVar.h()) {
                            cm cmVar4 = new cm("User didn't want to overwrite");
                            ac acVar5 = (ac) this.f6134b.f2028a;
                            if (acVar5 != null) {
                                ac.a(acVar5, null, 1, null);
                                s sVar8 = s.f2131a;
                            }
                            return cmVar4;
                        }
                        if (xVar.d()) {
                            if (ayVar3 instanceof ga) {
                                fxVarE.p(((ga) ayVar3).J());
                            } else if (ayVar3 instanceof dl) {
                                fxVarE.a(this.f6133a.getPackageManager(), strL, true);
                            } else if (ayVar3 instanceof ds) {
                                fxVarE.b(this.f6133a.getPackageManager(), ((ds) ayVar3).l());
                            }
                        }
                        i = 2;
                    }
                    s sVar9 = s.f2131a;
                }
                com.joaomgcd.taskerm.datashare.export.e eVarE = ayVar.e();
                if (!(eVarE != null ? eVarE.a() : false)) {
                    this.f6134b.f2028a = ac.a.a(ac.f6217a, this.f6133a, R.string.dc_getting_permissions, false, 4, null);
                    Set<String> setKeySet = fxVarA.a((Context) this.f6133a, (Set<String>) new HashSet(), true).keySet();
                    k.a((Object) setKeySet, "permissions.keys");
                    Object[] array = setKeySet.toArray(new String[0]);
                    if (array == null) {
                        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    cl clVarB = new GenericActionActivityRequestMissingPermissions((String[]) array, null, 2, null).run(this.f6133a).b();
                    if (!clVarB.b()) {
                        cm cmVar5 = new cm("Couldn't get all needed permissions\n\n" + clVarB.a());
                        ac acVar6 = (ac) this.f6134b.f2028a;
                        if (acVar6 != null) {
                            ac.a(acVar6, null, 1, null);
                            s sVar10 = s.f2131a;
                        }
                        return cmVar5;
                    }
                    ac acVar7 = (ac) this.f6134b.f2028a;
                    if (acVar7 != null) {
                        ac.a(acVar7, null, 1, null);
                        s sVar11 = s.f2131a;
                    }
                }
                Integer num = (Integer) aj.a((c.f.a.b) null, new c(ayVar, fxVarE), 1, (Object) null);
                if (num == null) {
                    cm cmVar6 = new cm("No project selected");
                    ac acVar8 = (ac) this.f6134b.f2028a;
                    if (acVar8 != null) {
                        ac.a(acVar8, null, 1, null);
                        s sVar12 = s.f2131a;
                    }
                    return cmVar6;
                }
                int iIntValue = num.intValue();
                this.f6134b.f2028a = ac.a.a(ac.f6217a, this.f6133a, R.string.dc_importing, false, 4, null);
                m mVar = this.f6135c;
                k.a((Object) fxVarE, "realTaskerData");
                ay ayVar4 = (ay) mVar.invoke(fxVarE, Integer.valueOf(iIntValue));
                if (ayVar4 == null) {
                    cm cmVar7 = new cm("Couldn't import into runnning config");
                    ac acVar9 = (ac) this.f6134b.f2028a;
                    if (acVar9 != null) {
                        ac.a(acVar9, null, 1, null);
                        s sVar13 = s.f2131a;
                    }
                    return cmVar7;
                }
                if (ayVar4 instanceof dl) {
                    iIntValue = fxVarE.q(((dl) ayVar4).l());
                }
                i.e(new AnonymousClass3(strC2, ayVar4));
                ac acVar10 = (ac) this.f6134b.f2028a;
                if (acVar10 != null) {
                    ac.a(acVar10, null, 1, null);
                    s sVar14 = s.f2131a;
                }
                AnonymousClass4 anonymousClass4 = new AnonymousClass4(ayVar2);
                anonymousClass4.a(ayVar4);
                if (ayVar4 instanceof dl) {
                    ArrayList arrayList2 = new ArrayList();
                    List<ga> listB2 = fxVarE.B(iIntValue);
                    k.a((Object) listB2, "realTaskerData.getProfilesInProject(projectIndex)");
                    for (ga gaVar2 : listB2) {
                        k.a((Object) gaVar2, "profile");
                        anonymousClass4.a(gaVar2);
                        List<fs> listA2 = aj.a(gaVar2, fxVarE);
                        for (fs fsVar2 : listA2) {
                            k.a((Object) fsVar2, "task");
                            anonymousClass4.a(fsVar2);
                        }
                        arrayList2.addAll(listA2);
                    }
                    ArrayList<fs> arrayListR = fxVarE.r(iIntValue);
                    k.a((Object) arrayListR, "tasksInProject");
                    for (fs fsVar3 : arrayListR) {
                        Iterator it = arrayList2.iterator();
                        do {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                            iG = ((fs) next).G();
                            k.a((Object) fsVar3, "task");
                        } while (!(iG == fsVar3.G()));
                        if (!(next != null)) {
                            k.a((Object) fsVar3, "task");
                            anonymousClass4.a(fsVar3);
                        }
                    }
                } else if (ayVar4 instanceof ga) {
                    for (fs fsVar4 : aj.a(fxVarE, (ga) ayVar4)) {
                        k.a((Object) fsVar4, "it");
                        anonymousClass4.a(fsVar4);
                    }
                }
                com.joaomgcd.taskerm.datashare.export.c cVar = new com.joaomgcd.taskerm.datashare.export.c();
                if (ayVar4 instanceof fs) {
                    Activity activity5 = this.f6133a;
                    String strL3 = ((fs) ayVar4).l();
                    k.a((Object) strL3, "importedIntoRealData.name");
                    cVar.add(new com.joaomgcd.taskerm.datashare.export.b(aj.a(R.string.dc_import_task_run_right_away, activity5, strL3), new AnonymousClass5(ayVar4), null, 4, null));
                } else if (ayVar4 instanceof ga) {
                    Activity activity6 = this.f6133a;
                    String strL4 = ((ga) ayVar4).l();
                    k.a((Object) strL4, "importedIntoRealData.name");
                    cVar.add(new com.joaomgcd.taskerm.datashare.export.b(aj.a(R.string.dc_import_profile_run_right_away, activity6, strL4), new AnonymousClass6(ayVar4), new AnonymousClass7(ayVar4)));
                } else if (ayVar4 instanceof dl) {
                    List<ga> listB3 = ((dl) ayVar4).b(fxVarE);
                    if (listB3.size() > 0) {
                        Activity activity7 = this.f6133a;
                        String strL5 = ((dl) ayVar4).l();
                        k.a((Object) strL5, "importedIntoRealData.name");
                        cVar.add(new com.joaomgcd.taskerm.datashare.export.b(aj.a(R.string.dc_import_project_run_right_away, activity7, strL5), new AnonymousClass1(listB3), new AnonymousClass2(listB3)));
                    }
                    com.joaomgcd.taskerm.datashare.export.e eVarE2 = ((dl) ayVar4).e();
                    if (eVarE2 != null && (strC = eVarE2.c()) != null) {
                        if (!(strC.length() == 0)) {
                            cVar.add(new com.joaomgcd.taskerm.datashare.export.b(aj.a(R.string.dc_import_project_run_launch_task, this.f6133a, strC), new a(strC, this, cVar, fxVarE), null, 4, null));
                        }
                        s sVar15 = s.f2131a;
                    }
                }
                for (com.joaomgcd.taskerm.datashare.export.b bVar : cVar) {
                    x xVar2 = (x) y.a(this.f6133a, R.string.ml_import, bVar.a(), 0, 8, (Object) null).b();
                    if (xVar2.d()) {
                        bVar.b().invoke();
                    } else if (xVar2.h() && (aVarC = bVar.c()) != null) {
                        aVarC.invoke();
                    }
                }
                fxVarE.g(this.f6133a).a(this.f6133a, gr.g(this.f6133a), this.f6133a.getResources(), new Date().getTime(), true, true);
                if (MonitorService.a((Context) this.f6133a)) {
                    MonitorService.a((Context) this.f6133a, true);
                }
                List listF2 = arrayList != null ? c.a.j.f((Iterable) arrayList) : null;
                com.joaomgcd.taskerm.rx.b.c(new com.joaomgcd.taskerm.datashare.export.d(ayVar4, listF2 == null || listF2.isEmpty()));
                co coVar = new co();
                ac acVar11 = (ac) this.f6134b.f2028a;
                if (acVar11 != null) {
                    ac.a(acVar11, null, 1, null);
                    s sVar16 = s.f2131a;
                }
                return coVar;
            } catch (Throwable th) {
                ac acVar12 = (ac) this.f6134b.f2028a;
                if (acVar12 != null) {
                    ac.a(acVar12, null, 1, null);
                    s sVar17 = s.f2131a;
                }
                throw th;
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$b, reason: collision with other inner class name */
        static final class C0123b extends l implements c.f.a.b<ay, s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ fx f6162a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ay f6163b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ List f6164c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0123b(fx fxVar, ay ayVar, List list) {
                super(1);
                this.f6162a = fxVar;
                this.f6163b = ayVar;
                this.f6164c = list;
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(ay ayVar) {
                a(ayVar);
                return s.f2131a;
            }

            public final void a(ay ayVar) {
                fx fxVar = this.f6162a;
                k.a((Object) fxVar, "realTaskerData");
                dl dlVarA = aj.a(fxVar, ayVar);
                if (k.a((Object) (dlVarA != null ? dlVarA.l() : null), (Object) ((dl) this.f6163b).l())) {
                    return;
                }
                this.f6164c.add(ayVar);
            }
        }

        static final class c extends l implements c.f.a.a<Integer> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ay f6166b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ fx f6167c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(ay ayVar, fx fxVar) {
                super(0);
                this.f6166b = ayVar;
                this.f6167c = fxVar;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Integer invoke() {
                if (!(this.f6166b instanceof dl)) {
                    fx fxVar = this.f6167c;
                    k.a((Object) fxVar, "realTaskerData");
                    List<dl> listB = fxVar.B();
                    k.a((Object) listB, "realTaskerData.projects");
                    Object[] array = listB.toArray(new dl[0]);
                    if (array != null) {
                        dl[] dlVarArr = (dl[]) array;
                        List listC = c.a.j.c((dl[]) Arrays.copyOf(dlVarArr, dlVarArr.length));
                        dl dlVar = new dl("+New Project");
                        listC.add(dlVar);
                        if (listC.size() == 1) {
                            return 0;
                        }
                        ae aeVar = (ae) com.joaomgcd.taskerm.dialog.k.a(new ad(b.this.f6133a, R.string.import_to_project, listC, false, a.f6168a, null, null, null, null, Integer.valueOf(gn.c(b.this.f6133a)), Integer.valueOf(com.joaomgcd.taskerm.settings.ad.e(b.this.f6133a)), null, null, null, null, null, 63968, null)).b();
                        if (k.a((dl) aeVar.b(), dlVar)) {
                            String strJ = y.a(new h(b.this.f6133a, R.string.pl_name, (String) null, 0, 0, 0, true, 0, (String) null, 0, (Integer) null, 1976, (c.f.b.g) null)).b().j();
                            if (strJ == null) {
                                strJ = dlVar.l();
                            }
                            dlVar.a(strJ);
                            this.f6167c.a(dlVar);
                            fx fxVar2 = this.f6167c;
                            k.a((Object) fxVar2, "realTaskerData");
                            return Integer.valueOf(fxVar2.B().size() - 1);
                        }
                        return aeVar.c();
                    }
                    throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                }
                return 0;
            }

            static final class a extends l implements c.f.a.b<dl, com.joaomgcd.taskerm.dialog.w> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final a f6168a = new a();

                a() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final com.joaomgcd.taskerm.dialog.w invoke(dl dlVar) {
                    k.a((Object) dlVar, "it");
                    String strL = dlVar.l();
                    k.a((Object) strL, "it.name");
                    return new com.joaomgcd.taskerm.dialog.w(strL, dlVar.n(), false, 4, null);
                }
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$3, reason: invalid class name */
        static final class AnonymousClass3 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f6140b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ ay f6141c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(String str, ay ayVar) {
                super(0);
                this.f6140b = str;
                this.f6141c = ayVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                gr.c(b.this.f6133a, "Imported " + this.f6140b + " '" + this.f6141c.l() + '\'', new Object[0]);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$4, reason: invalid class name */
        static final class AnonymousClass4 extends l implements c.f.a.b<ay, s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ay f6143b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass4(ay ayVar) {
                super(1);
                this.f6143b = ayVar;
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(ay ayVar) {
                a(ayVar);
                return s.f2131a;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$4$a */
            static final class a extends l implements c.f.a.a<com.joaomgcd.taskerm.profile.i> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ com.joaomgcd.taskerm.profile.c f6144a;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                a(com.joaomgcd.taskerm.profile.c cVar) {
                    super(0);
                    this.f6144a = cVar;
                }

                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final com.joaomgcd.taskerm.profile.i invoke() {
                    return com.joaomgcd.taskerm.profile.i.y.a(this.f6144a.k());
                }
            }

            /* JADX WARN: Code duplicated, block: B:26:0x0069  */
            public final void a(ay ayVar) {
                String strJ;
                com.joaomgcd.taskerm.profile.j jVarB;
                com.joaomgcd.taskerm.profile.c next;
                k.b(ayVar, "receiver$0");
                com.joaomgcd.taskerm.profile.j jVarB2 = ayVar.b();
                if (jVarB2 != null) {
                    for (com.joaomgcd.taskerm.profile.c cVar : jVarB2) {
                        boolean z = true;
                        com.joaomgcd.taskerm.profile.i iVar = (com.joaomgcd.taskerm.profile.i) aj.a((c.f.a.b) null, new a(cVar), 1, (Object) null);
                        if (iVar != null && cVar.l()) {
                            ay ayVar2 = this.f6143b;
                            if (ayVar2 == null || (jVarB = ayVar2.b()) == null) {
                                strJ = null;
                            } else {
                                Iterator<com.joaomgcd.taskerm.profile.c> it = jVarB.iterator();
                                do {
                                    if (!it.hasNext()) {
                                        next = null;
                                        break;
                                    }
                                    next = it.next();
                                } while (!k.a(next, cVar));
                                com.joaomgcd.taskerm.profile.c cVar2 = next;
                                if (cVar2 != null) {
                                    strJ = cVar2.d();
                                } else {
                                    strJ = null;
                                }
                            }
                            boolean z2 = strJ != null ? ak.z(strJ) : false;
                            if (!z2) {
                                m<Activity, com.joaomgcd.taskerm.profile.c, b.a.l<String>> mVarB = iVar.b();
                                String strA = cVar.a();
                                String strB = cVar.b();
                                if (!z2) {
                                    if (mVarB != null) {
                                        strJ = (String) aj.a((c.f.a.b) null, new C0122b(iVar, strA, strB, mVarB, cVar, this), 1, (Object) null);
                                    } else {
                                        strJ = y.a(new h(b.this.f6133a, strA, cVar.e(), 0, 0, 0, true, 16385, strB, iVar.i(), null, null, 3128, null)).b().j();
                                    }
                                }
                                String str = strJ;
                                if (str != null && str.length() != 0) {
                                    z = false;
                                }
                                if (!z) {
                                    cVar.a(strJ);
                                }
                            }
                        }
                    }
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$4$b, reason: collision with other inner class name */
            static final class C0122b extends l implements c.f.a.a<String> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ com.joaomgcd.taskerm.profile.i f6145a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ String f6146b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ String f6147c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ m f6148d;

                /* JADX INFO: renamed from: e, reason: collision with root package name */
                final /* synthetic */ com.joaomgcd.taskerm.profile.c f6149e;

                /* JADX INFO: renamed from: f, reason: collision with root package name */
                final /* synthetic */ AnonymousClass4 f6150f;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0122b(com.joaomgcd.taskerm.profile.i iVar, String str, String str2, m mVar, com.joaomgcd.taskerm.profile.c cVar, AnonymousClass4 anonymousClass4) {
                    super(0);
                    this.f6145a = iVar;
                    this.f6146b = str;
                    this.f6147c = str2;
                    this.f6148d = mVar;
                    this.f6149e = cVar;
                    this.f6150f = anonymousClass4;
                }

                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String invoke() {
                    b.a.l lVarA;
                    if (this.f6145a.g()) {
                        lVarA = b.a.l.a(true);
                    } else {
                        lVarA = y.a(b.this.f6133a, this.f6146b, this.f6147c, (com.joaomgcd.taskerm.util.h) null, 8, (Object) null);
                    }
                    return (String) lVarA.a(new b.a.d.g<T, p<? extends R>>() { // from class: com.joaomgcd.taskerm.datashare.export.f.b.4.b.1
                        @Override // b.a.d.g
                        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                        public final b.a.l<String> apply(Object obj) {
                            return (b.a.l) C0122b.this.f6148d.invoke(b.this.f6133a, C0122b.this.f6149e);
                        }
                    }).b();
                }
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$5, reason: invalid class name */
        static final class AnonymousClass5 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ay f6153b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass5(ay ayVar) {
                super(0);
                this.f6153b = ayVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                ((fs) this.f6153b).b(b.this.f6133a);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$6, reason: invalid class name */
        static final class AnonymousClass6 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ ay f6154a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass6(ay ayVar) {
                super(0);
                this.f6154a = ayVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                ((ga) this.f6154a).T();
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$7, reason: invalid class name */
        static final class AnonymousClass7 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ ay f6155a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass7(ay ayVar) {
                super(0);
                this.f6155a = ayVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                ((ga) this.f6155a).U();
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ List f6137a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(List list) {
                super(0);
                this.f6137a = list;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                List list = this.f6137a;
                k.a((Object) list, "profiles");
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((ga) it.next()).T();
                }
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$2, reason: invalid class name */
        static final class AnonymousClass2 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ List f6138a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(List list) {
                super(0);
                this.f6138a = list;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                List list = this.f6138a;
                k.a((Object) list, "profiles");
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((ga) it.next()).U();
                }
            }
        }

        static final class a extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f6156a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b f6157b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.datashare.export.c f6158c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ fx f6159d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(String str, b bVar, com.joaomgcd.taskerm.datashare.export.c cVar, fx fxVar) {
                super(0);
                this.f6156a = str;
                this.f6157b = bVar;
                this.f6158c = cVar;
                this.f6159d = fxVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                fs fsVarH = this.f6159d.h(this.f6156a);
                if (fsVarH != null) {
                    k.a((Object) fsVarH, "realTaskerData.getTaskBy…    ?: return@AfterImport");
                    i.c(new AnonymousClass1(fsVarH));
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.f$b$a$1, reason: invalid class name */
            static final class AnonymousClass1 extends l implements c.f.a.a<s> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ fs f6161b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(fs fsVar) {
                    super(0);
                    this.f6161b = fsVar;
                }

                @Override // c.f.a.a
                public /* synthetic */ s invoke() {
                    a();
                    return s.f2131a;
                }

                public final void a() {
                    while (!a.this.f6157b.f6133a.isDestroyed()) {
                        dm.a(200L);
                    }
                    dm.a(500L);
                    ExecuteService.a(a.this.f6157b.f6133a, this.f6161b);
                }
            }
        }
    }
}
