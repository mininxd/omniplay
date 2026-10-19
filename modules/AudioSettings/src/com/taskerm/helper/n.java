package com.joaomgcd.taskerm.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.notification.ah;
import com.joaomgcd.taskerm.notification.ak;
import com.joaomgcd.taskerm.notification.ao;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.securesettings.aa;
import com.joaomgcd.taskerm.settings.ad;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dm;
import cyanogenmod.app.ProfileManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;
import net.dinglisch.android.tasker.InputRunShell;
import net.dinglisch.android.tasker.OutputRunShell;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.Kid;
import net.dinglisch.android.taskerm.MyAccessibilityService;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.bp;
import net.dinglisch.android.taskerm.dl;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.fy;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public final class n extends u<ExecuteService> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7378b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(n.class), "backgroundThread", "getBackgroundThread()Lio/reactivex/Scheduler;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7379c;

    public static final class a {
    }

    public final boolean a(String str, boolean z, String str2) {
        return a(this, str, z, str2, (String) null, (String) null, (String) null, 56, (Object) null);
    }

    @TargetApi(26)
    public final boolean a(String[] strArr) {
        return a(this, strArr, (com.joaomgcd.taskerm.notification.b) null, 2, (Object) null);
    }

    public final int b(int i2) {
        switch (i2) {
            case 1:
            case 4:
                return 2;
            case 2:
                return 3;
            case 3:
            default:
                return 1;
        }
    }

    public final void d(fs fsVar) {
        c.f.b.k.b(fsVar, "task");
    }

    @Override // com.joaomgcd.taskerm.helper.j
    public b.a.k q() {
        c.e eVar = this.f7379c;
        c.j.g gVar = f7378b[0];
        return (b.a.k) eVar.b();
    }

    static final class e extends c.f.b.l implements c.f.a.b<Exception, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7384a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.b f7385b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str, c.f.b.w.b bVar) {
            super(1);
            this.f7384a = str;
            this.f7385b = bVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Exception exc) {
            a(exc);
            return c.s.f2131a;
        }

        public final void a(Exception exc) {
            c.f.b.k.b(exc, "receiver$0");
            try {
                bn.d("E", this.f7384a + ": " + exc.getMessage() + " : " + ai.a(exc));
            } catch (Throwable unused) {
                bn.d("E", this.f7384a + ": " + exc.getMessage());
            }
            this.f7385b.f2026a = -3;
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.r f7388b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f7389c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f7390d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(com.joaomgcd.taskerm.securesettings.r rVar, String str, String str2) {
            super(0);
            this.f7388b = rVar;
            this.f7389c = str;
            this.f7390d = str2;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            try {
                bn.d("E", this.f7389c + ": set " + this.f7390d + " in background result: " + aa.a((Context) n.this.u(), this.f7388b).b());
            } catch (Exception e2) {
                bn.b("E", "After retry", e2);
            }
        }
    }

    static final class j extends c.f.b.l implements c.f.a.b<at, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7398b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f7399c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f7400d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ fs f7401e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(String str, String str2, String str3, fs fsVar) {
            super(1);
            this.f7398b = str;
            this.f7399c = str2;
            this.f7400d = str3;
            this.f7401e = fsVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(at atVar) {
            a(atVar);
            return c.s.f2131a;
        }

        public final void a(at atVar) {
            c.f.b.k.b(atVar, "receiver$0");
            String string = atVar.e().getString(R.string.action_error_notifications);
            c.f.b.k.a((Object) string, "context.getString(R.stri…tion_error_notifications)");
            atVar.a(new ak("action_error_notifications", string, atVar.e().getString(R.string.action_error_notifications_category_explained), 4, null, new long[]{0, 100, 50, 100}, false, null, true, null, null, 1744, null));
            atVar.a(new ao("actionerror", false, 0, 6, null));
            atVar.a(new ah(n.this.u(), this.f7398b, c.l.n.a(c.l.n.a(this.f7399c, (CharSequence) this.f7400d), "\n", "\n\n", false, 4, (Object) null)));
            atVar.u().add(new com.joaomgcd.taskerm.notification.i(atVar.e(), "Open Task On Error", null, null, Integer.valueOf(this.f7401e.G())));
            atVar.u().add(new com.joaomgcd.taskerm.notification.f(atVar.e(), "action_error_notifications"));
            atVar.c(org.joda.time.format.a.a().a(System.currentTimeMillis()));
            atVar.b(true);
            atVar.a(System.currentTimeMillis());
        }
    }

    static final class k extends c.f.b.l implements c.f.a.b<at, c.s> {
        k() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(at atVar) {
            a(atVar);
            return c.s.f2131a;
        }

        public final void a(at atVar) {
            c.f.b.k.b(atVar, "receiver$0");
            atVar.a(new com.joaomgcd.taskerm.notification.p(n.this.u()));
        }
    }

    static final class l extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final l f7403a = new l();

        l() {
            super(0);
        }

        public final void a() {
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }
    }

    static final class m extends c.f.b.l implements c.f.a.b<at, c.s> {
        m() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(at atVar) {
            a(atVar);
            return c.s.f2131a;
        }

        public final void a(at atVar) {
            c.f.b.k.b(atVar, "receiver$0");
            atVar.a(new com.joaomgcd.taskerm.notification.j(n.this.u()));
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.n$n, reason: collision with other inner class name */
    static final class C0166n extends c.f.b.l implements c.f.a.b<at, c.s> {
        C0166n() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(at atVar) {
            a(atVar);
            return c.s.f2131a;
        }

        public final void a(at atVar) {
            c.f.b.k.b(atVar, "receiver$0");
            atVar.a(new com.joaomgcd.taskerm.notification.g(n.this.u(), com.joaomgcd.taskerm.util.ah.a(n.this.u(), au.a.Action, 523), false, null, 12, null));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(ExecuteService executeService) {
        super(executeService, "E");
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        this.f7379c = c.f.a(new c(executeService));
    }

    @Override // com.joaomgcd.taskerm.helper.u, com.joaomgcd.taskerm.helper.j
    public void b() {
        super.b();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r9v7 ??, still in use, count: 3, list:
          (r9v7 ?? I:com.joaomgcd.taskerm.securesettings.r) from 0x00ec: INVOKE 
          (r5v5 ?? I:com.joaomgcd.taskerm.helper.n$g)
          (r21v0 'this' ?? I:com.joaomgcd.taskerm.helper.n A[IMMUTABLE_TYPE, THIS])
          (r9v7 ?? I:com.joaomgcd.taskerm.securesettings.r)
          (r22v0 ?? I:java.lang.String)
          (r3v11 ?? I:java.lang.String)
         DIRECT call: com.joaomgcd.taskerm.helper.n.g.<init>(com.joaomgcd.taskerm.helper.n, com.joaomgcd.taskerm.securesettings.r, java.lang.String, java.lang.String):void A[Catch: Exception -> 0x0159, a -> 0x015f, MD:(com.joaomgcd.taskerm.helper.n, com.joaomgcd.taskerm.securesettings.r, java.lang.String, java.lang.String):void (m)]
          (r9v7 ?? I:com.joaomgcd.taskerm.securesettings.r) from 0x00ab: INVOKE (r5v12 ?? I:b.a.l) = (r5v11 ?? I:android.content.Context), (r9v7 ?? I:com.joaomgcd.taskerm.securesettings.r) STATIC call: com.joaomgcd.taskerm.securesettings.aa.a(android.content.Context, com.joaomgcd.taskerm.securesettings.r):b.a.l A[Catch: Exception -> 0x00cc, a -> 0x0150, MD:(android.content.Context, com.joaomgcd.taskerm.securesettings.r):b.a.l<java.lang.Boolean> (m)]
          (r9v7 ?? I:??[OBJECT, ARRAY]) from 0x0125: CHECK_CAST (r9v8 ?? I:com.joaomgcd.taskerm.securesettings.f) = (com.joaomgcd.taskerm.securesettings.f) (r9v7 ?? I:??[OBJECT, ARRAY])
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:75)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public final int a(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r9v7 ??, still in use, count: 3, list:
          (r9v7 ?? I:com.joaomgcd.taskerm.securesettings.r) from 0x00ec: INVOKE 
          (r5v5 ?? I:com.joaomgcd.taskerm.helper.n$g)
          (r21v0 'this' ?? I:com.joaomgcd.taskerm.helper.n A[IMMUTABLE_TYPE, THIS])
          (r9v7 ?? I:com.joaomgcd.taskerm.securesettings.r)
          (r22v0 ?? I:java.lang.String)
          (r3v11 ?? I:java.lang.String)
         DIRECT call: com.joaomgcd.taskerm.helper.n.g.<init>(com.joaomgcd.taskerm.helper.n, com.joaomgcd.taskerm.securesettings.r, java.lang.String, java.lang.String):void A[Catch: Exception -> 0x0159, a -> 0x015f, MD:(com.joaomgcd.taskerm.helper.n, com.joaomgcd.taskerm.securesettings.r, java.lang.String, java.lang.String):void (m)]
          (r9v7 ?? I:com.joaomgcd.taskerm.securesettings.r) from 0x00ab: INVOKE (r5v12 ?? I:b.a.l) = (r5v11 ?? I:android.content.Context), (r9v7 ?? I:com.joaomgcd.taskerm.securesettings.r) STATIC call: com.joaomgcd.taskerm.securesettings.aa.a(android.content.Context, com.joaomgcd.taskerm.securesettings.r):b.a.l A[Catch: Exception -> 0x00cc, a -> 0x0150, MD:(android.content.Context, com.joaomgcd.taskerm.securesettings.r):b.a.l<java.lang.Boolean> (m)]
          (r9v7 ?? I:??[OBJECT, ARRAY]) from 0x0125: CHECK_CAST (r9v8 ?? I:com.joaomgcd.taskerm.securesettings.f) = (com.joaomgcd.taskerm.securesettings.f) (r9v7 ?? I:??[OBJECT, ARRAY])
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:75)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r22v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:215)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:150)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:415)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */

    static final class f<T, R> implements b.a.d.g<Throwable, b.a.p<? extends Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f7386a = new f();

        f() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.p<? extends Boolean> apply(Throwable th) {
            c.f.b.k.b(th, "it");
            if (th instanceof SecurityException) {
                return ai.a((SecurityException) th);
            }
            return b.a.l.b(th);
        }
    }

    public static final class p<T> implements b.a.d.i<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final p f7413a = new p();

        @Override // b.a.d.i
        public final boolean a(Object obj) {
            c.f.b.k.b(obj, "it");
            return obj instanceof a;
        }
    }

    public static final class r<T> implements b.a.d.i<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final r f7415a = new r();

        @Override // b.a.d.i
        public final boolean a(Object obj) {
            c.f.b.k.b(obj, "it");
            return obj instanceof b;
        }
    }

    private final com.joaomgcd.taskerm.notification.b b(String[] strArr) {
        String str;
        bt.a aVar = bt.f9101c;
        Context contextT = t();
        try {
            str = strArr[0];
        } catch (Throwable unused) {
            str = null;
        }
        com.joaomgcd.taskerm.notification.b bVarA = aVar.a(contextT, str);
        return bVarA != null ? bVarA : p();
    }

    @TargetApi(26)
    public final boolean a(int i2, com.joaomgcd.taskerm.notification.b bVar) {
        c.f.b.k.b(bVar, "actionToGetPermission");
        return a(bt.a.b(bt.f9101c, t(), i2, 0, false, 12, null), bVar);
    }

    @TargetApi(26)
    public static /* synthetic */ boolean a(n nVar, String[] strArr, com.joaomgcd.taskerm.notification.b bVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bVar = nVar.b(strArr);
        }
        return nVar.a(strArr, bVar);
    }

    @TargetApi(26)
    public final boolean a(String[] strArr, com.joaomgcd.taskerm.notification.b bVar) {
        c.f.b.k.b(strArr, "permissionKeys");
        return a(new bt(t(), 0, (String[]) Arrays.copyOf(strArr, strArr.length), 2, (c.f.b.g) null), bVar);
    }

    public final void a() {
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            return;
        }
        at.a aVar = at.f8046b;
        Context applicationContext = u().getApplicationContext();
        c.f.b.k.a((Object) applicationContext, "service.applicationContext");
        com.joaomgcd.taskerm.helper.j.a(this, aVar.b(applicationContext, "urcaa", aj.a(R.string.an_notify_plain, u(), new Object[0]), aj.a(R.string.dc_notify_plain_without_category, t(), new Object[0]), new C0166n()), (c.f.a.a) null, 2, (Object) null);
    }

    public final void a(fs fsVar) {
        c.f.b.k.b(fsVar, "parentTask");
        if (Kid.a()) {
            return;
        }
        if (aj.b(fsVar.i()) > ((long) 60000)) {
            at.a aVar = at.f8046b;
            Context applicationContext = u().getApplicationContext();
            c.f.b.k.a((Object) applicationContext, "service.applicationContext");
            com.joaomgcd.taskerm.helper.j.a(this, at.a.a(aVar, applicationContext, "oldhttpp", aj.a(R.string.an_http_request, u(), new Object[0]), aj.a(R.string.dc_notify_old_http, t(), new Object[0]), null, 16, null), (c.f.a.a) null, 2, (Object) null);
        }
    }

    public final void c() {
        if (Kid.a()) {
            return;
        }
        at.a aVar = at.f8046b;
        Context applicationContext = u().getApplicationContext();
        c.f.b.k.a((Object) applicationContext, "service.applicationContext");
        com.joaomgcd.taskerm.helper.j.a(this, at.a.a(aVar, applicationContext, "clearallvar", aj.a(R.string.an_clear_variable, u(), new Object[0]), aj.a(R.string.dc_notify_clear_all_variables, t(), new Object[0]), null, 16, null), (c.f.a.a) null, 2, (Object) null);
    }

    public final void b(fs fsVar) {
        c.f.b.k.b(fsVar, "parentTask");
        if (com.joaomgcd.taskerm.util.ah.j(u())) {
            return;
        }
        if (aj.b(fsVar.i()) > ((long) 60000)) {
            String strA = aj.a(R.string.an_get_current_location, u(), new Object[0]);
            at.a aVar = at.f8046b;
            Context applicationContext = u().getApplicationContext();
            c.f.b.k.a((Object) applicationContext, "service.applicationContext");
            com.joaomgcd.taskerm.helper.j.a(this, at.a.a(aVar, applicationContext, "oldhttpp", strA, aj.a(R.string.dc_notify_get_location_2, t(), strA), null, 16, null), (c.f.a.a) null, 2, (Object) null);
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<b.a.k> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ExecuteService f7381a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(ExecuteService executeService) {
            super(0);
            this.f7381a = executeService;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k invoke() {
            return com.joaomgcd.taskerm.util.ah.c(this.f7381a).j();
        }
    }

    public final void d() {
        if (bp.f(u())) {
            return;
        }
        at.a aVar = at.f8046b;
        Context applicationContext = u().getApplicationContext();
        c.f.b.k.a((Object) applicationContext, "service.applicationContext");
        com.joaomgcd.taskerm.helper.j.a(this, aVar.b(applicationContext, "overan10", aj.a(R.string.ml_android_settings_overlays, u(), new Object[0]), aj.a(R.string.dc_android_10_needs_overlay, t(), com.joaomgcd.taskerm.util.ah.a(t())), new m()), (c.f.a.a) null, 2, (Object) null);
    }

    static final class q<T, R> implements b.a.d.g<Throwable, b.a.p<? extends a>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final q f7414a = new q();

        q() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<a> apply(Throwable th) {
            c.f.b.k.b(th, "it");
            return b.a.l.a(new a());
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f7380a;

        public b(int i) {
            this.f7380a = i;
        }

        public final boolean a() {
            return this.f7380a == -1;
        }

        public final String a(Context context) {
            int i;
            c.f.b.k.b(context, "context");
            switch (this.f7380a) {
                case -1:
                    i = R.string.button_label_ok;
                    break;
                case 0:
                case 6:
                default:
                    i = R.string.word_unknown;
                    break;
                case 1:
                    i = R.string.generic_failure;
                    break;
                case 2:
                    i = R.string.radio_off;
                    break;
                case 3:
                    i = R.string.null_PDU;
                    break;
                case 4:
                    i = R.string.service_currently_unavailable;
                    break;
                case 5:
                    i = R.string.sending_queue_limit_reached;
                    break;
                case 7:
                    i = R.string.short_code_not_allowed;
                    break;
                case 8:
                    i = R.string.short_code_never_allowed;
                    break;
            }
            return aj.a(i, context, new Object[0]);
        }
    }

    public final int a(net.dinglisch.android.taskerm.c cVar, Bundle bundle, fs fsVar, String str, c.f.a.b<? super Context, ? extends b.a.l<cl>> bVar) {
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(fsVar, "parentTask");
        c.f.b.k.b(str, "errorMessage");
        c.f.b.k.b(bVar, "doer");
        String strA = net.dinglisch.android.taskerm.n.a(u(), cVar, bundle, fsVar.am());
        u().c(strA);
        b.a.l<cl> lVarD = bVar.invoke(u()).d(h.f7391a);
        c.f.b.k.a((Object) lVarD, "doer(service).onErrorRes…(SimpleResultError(it)) }");
        a(lVarD, new i(str, cVar, fsVar, strA));
        return -1;
    }

    static final class h<T, R> implements b.a.d.g<Throwable, b.a.p<? extends cl>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f7391a = new h();

        h() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<cm> apply(Throwable th) {
            c.f.b.k.b(th, "it");
            return b.a.l.a(new cm(th));
        }
    }

    static final class i<T> implements b.a.d.f<cl> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7393b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ net.dinglisch.android.taskerm.c f7394c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ fs f7395d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7396e;

        i(String str, net.dinglisch.android.taskerm.c cVar, fs fsVar, String str2) {
            this.f7393b = str;
            this.f7394c = cVar;
            this.f7395d = fsVar;
            this.f7396e = str2;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(cl clVar) {
            c.f.b.k.b(clVar, "simpleResult");
            if (!clVar.b()) {
                String str = this.f7393b + ": " + clVar.a();
                bn.c("E", str);
                this.f7394c.a(net.dinglisch.android.taskerm.c.a.Err);
                this.f7395d.a(this.f7394c, str);
                if (this.f7394c.s()) {
                    this.f7395d.e(true);
                }
            }
            n.this.u().d(this.f7396e);
        }
    }

    public final void c(fs fsVar) {
        c.f.b.k.b(fsVar, "task");
        com.joaomgcd.taskerm.profile.j jVarB = fsVar.b();
        fx fxVarE = fx.e(u());
        c.f.b.k.a((Object) fxVarE, "taskerData");
        dl dlVarC = aj.c(fxVarE, fsVar);
        Collection collectionA = null;
        com.joaomgcd.taskerm.profile.j jVarB2 = dlVarC != null ? dlVarC.b() : null;
        if (fsVar.w() || dlVarC != null) {
            ga gaVarA = aj.a(fxVarE, fsVar);
            collectionA = aj.a(gaVarA != null ? gaVarA.b() : null, (Iterable) aj.c(fxVarE, gaVarA));
        } else {
            List<ga> listA = aj.a((fy) fxVarE, fsVar);
            if (listA != null) {
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = listA.iterator();
                while (it.hasNext()) {
                    dl dlVarB = aj.b(fxVarE, (ga) it.next());
                    com.joaomgcd.taskerm.profile.j jVarB3 = dlVarB != null ? dlVarB.b() : null;
                    if (jVarB3 != null) {
                        arrayList.add(jVarB3);
                    }
                }
                collectionA = c.a.j.a((Iterable) arrayList);
            }
        }
        List listA2 = aj.a((Collection) aj.a((Collection) jVarB, (Iterable) jVarB2), (Iterable) collectionA);
        List list = listA2;
        if (list == null || list.isEmpty()) {
            return;
        }
        com.joaomgcd.taskerm.profile.k.a((List<com.joaomgcd.taskerm.profile.c>) listA2).a(u(), fsVar.q());
    }

    public static /* synthetic */ boolean a(n nVar, String str, boolean z, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str3 = str + ": " + nVar.q(R.string.pl_show_on_lock_screen_interactive_everywhere);
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            str4 = aj.a(R.string.notification_accessibility_required_for_scene, nVar.u(), str2);
        }
        String str7 = str4;
        if ((i2 & 32) != 0) {
            str5 = "requiredaccessibilitynotrunning";
        }
        return nVar.a(str, z, str2, str6, str7, str5);
    }

    public final boolean a(String str, boolean z, String str2, String str3, String str4, String str5) {
        c.f.b.k.b(str, "actionName");
        c.f.b.k.b(str2, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(str3, "title");
        c.f.b.k.b(str4, "text");
        c.f.b.k.b(str5, "warningKey");
        if (!z || MyAccessibilityService.c()) {
            return true;
        }
        bn.d("E", "Action requires accessibility but the accessibility service is disabled. Notification posted.");
        com.joaomgcd.taskerm.rx.i.a(at.f8046b.b(u(), str5, str3, str4, new k()), u(), l.f7403a);
        return false;
    }

    public final int a(net.dinglisch.android.taskerm.c cVar, fs fsVar) {
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(fsVar, "parentTask");
        if (com.joaomgcd.taskerm.util.f.f9323b.k()) {
            u().sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            return -5;
        }
        return a(cVar, (Bundle) null, fsVar, "Can't dismiss notification shade with accessibility service", new d());
    }

    static final class d extends c.f.b.l implements c.f.a.b<Context, b.a.l<cl>> {
        d() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<cl> invoke(Context context) {
            c.f.b.k.b(context, "it");
            b.a.l lVarC = com.joaomgcd.taskerm.a.b.a(n.this.u(), 15).c((b.a.d.g<? super Boolean, ? extends R>) new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.helper.n.d.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final cl apply(Boolean bool) {
                    c.f.b.k.b(bool, "it");
                    if (bool.booleanValue()) {
                        return new co();
                    }
                    try {
                        n.this.u().sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                        return new co();
                    } catch (Throwable unused) {
                        return cn.a("Can't perform global action");
                    }
                }
            });
            c.f.b.k.a((Object) lVarC, "performGlobalAction(serv…          }\n            }");
            return lVarC;
        }
    }

    public final void a(fs fsVar, net.dinglisch.android.taskerm.c cVar, net.dinglisch.android.taskerm.c.a aVar, int i2, int i3, String str, String str2, CharSequence charSequence) {
        int iG;
        String strA;
        String str3;
        String string;
        ArrayList arrayList;
        String string2;
        List<c.l.i> listG;
        c.f.b.k.b(aVar, NotificationCompat.CATEGORY_STATUS);
        if (Kid.a()) {
            return;
        }
        if (cVar == null || fsVar == null) {
            return;
        }
        if ((aVar == net.dinglisch.android.taskerm.c.a.Err || aVar == net.dinglisch.android.taskerm.c.a.Exception) && i3 != 130 && cVar.s() && !dm.d() && !com.joaomgcd.taskerm.action.d.e().contains(Integer.valueOf(i3)) && (iG = fsVar.G()) >= 0) {
            if (str2 == null) {
                if (charSequence == null || (string2 = charSequence.toString()) == null || (listG = com.joaomgcd.taskerm.util.ak.g(string2, "/E (.+)")) == null) {
                    arrayList = null;
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<T> it = listG.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        c.l.i iVar = (c.l.i) next;
                        if (((iVar.b().length() == 0) || com.joaomgcd.taskerm.util.ak.a(iVar.b(), "E result", "E Error: ", "E exe:")) ? false : true) {
                            arrayList2.add(next);
                        }
                    }
                    ArrayList arrayList3 = new ArrayList();
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        c.l.f fVarA = ((c.l.i) it2.next()).c().a(1);
                        String strA2 = com.joaomgcd.taskerm.util.ak.A(fVarA != null ? fVarA.a() : null);
                        if (strA2 != null) {
                            arrayList3.add(strA2);
                        }
                    }
                    arrayList = arrayList3;
                }
                strA = com.joaomgcd.taskerm.util.ak.A(arrayList != null ? c.a.j.a(arrayList, "\n", null, null, 0, null, null, 62, null) : null);
            } else {
                strA = str2;
            }
            if (!(str == null && strA == null) && ad.i(u())) {
                String str4 = "Error Code: " + str;
                if (strA == null) {
                    string = str4 + ". Try running the task manually in Tasker to see what the error is.";
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(strA);
                    if (str == null) {
                        str3 = "";
                    } else {
                        str3 = " (" + str4 + ')';
                    }
                    sb.append(str3);
                    string = sb.toString();
                }
                StringBuilder sb2 = new StringBuilder("Click For more info.\nAction: '" + net.dinglisch.android.taskerm.n.t(i3) + "' (step " + (i2 + 1) + ')');
                if (fsVar.m()) {
                    sb2.append("\nTask: '" + fsVar.k() + '\'');
                }
                ga gaVarK = fx.c(u()).k(fsVar.v());
                if (gaVarK != null) {
                    String strA3 = gaVarK.a(u());
                    if (strA3.length() > 20) {
                        StringBuilder sb3 = new StringBuilder();
                        c.f.b.k.a((Object) strA3, "it");
                        if (strA3 == null) {
                            throw new c.q("null cannot be cast to non-null type java.lang.String");
                        }
                        String strSubstring = strA3.substring(0, 20);
                        c.f.b.k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        sb3.append(strSubstring);
                        sb3.append("...");
                        strA3 = sb3.toString();
                    }
                    sb2.append("\nProfile: '" + strA3 + '\'');
                }
                sb2.append('\n' + string);
                String string3 = sb2.toString();
                c.f.b.k.a((Object) string3, "sb.toString()");
                String strA4 = aj.a(R.string.tasker_action_error, t(), new Object[0]);
                com.joaomgcd.taskerm.helper.j.a(this, at.f8046b.b(u(), ad.a() + iG + '-' + i2 + '-' + i3, strA4, string3, new j(strA4, string3, "Click For more info.\n", fsVar)), (c.f.a.a) null, 2, (Object) null);
            }
        }
    }

    public final void a(Context context, int i2, net.dinglisch.android.taskerm.c cVar) {
        c.k<Integer, Integer> kVar;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(cVar, "toExecute");
        if (com.joaomgcd.taskerm.util.f.f9323b.d(context) && (kVar = com.joaomgcd.taskerm.action.d.d().get(Integer.valueOf(i2))) != null) {
            c.f.b.k.a((Object) kVar, "actionsWithRootAndGlobal…ons[actionCode] ?: return");
            net.dinglisch.android.taskerm.e eVarH = cVar.h(kVar.b().intValue());
            c.f.b.k.a((Object) eVarH, "toExecute.getBoolArg(argNumbers.second)");
            if (eVarH.b()) {
                net.dinglisch.android.taskerm.e eVarH2 = cVar.h(kVar.a().intValue());
                c.f.b.k.a((Object) eVarH2, "toExecute.getBoolArg(argNumbers.first)");
                if (eVarH2.b()) {
                    return;
                }
                com.joaomgcd.taskerm.helper.j.a(this, at.a.a(at.f8046b, u(), "rootbutnotglobal", aj.a(R.string.root_but_not_global_namespace, u(), new Object[0]), aj.a(R.string.root_but_not_global_namespace_explained, u(), new Object[0]), null, 16, null), (c.f.a.a) null, 2, (Object) null);
            }
        }
    }

    public final void a(TextToSpeech textToSpeech) {
        c.f.b.k.b(textToSpeech, "tts");
        try {
            textToSpeech.shutdown();
        } catch (Throwable th) {
            com.joaomgcd.taskerm.rx.i.a(u(), th);
        }
    }

    static final class o extends c.f.b.l implements c.f.a.b<Context, b.a.l<cl>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f7406a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7407b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f7408c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f7409d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7410e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ Bundle f7411f;
        final /* synthetic */ String g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        o(long j, String str, boolean z, boolean z2, String str2, Bundle bundle, String str3) {
            super(1);
            this.f7406a = j;
            this.f7407b = str;
            this.f7408c = z;
            this.f7409d = z2;
            this.f7410e = str2;
            this.f7411f = bundle;
            this.g = str3;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<cl> invoke(Context context) {
            c.f.b.k.b(context, "context");
            long j = this.f7406a == -1 ? 60000L : this.f7406a;
            b.a.l lVarC = net.dinglisch.android.tasker.d.f10445a.a(context, new InputRunShell(this.f7407b, j, this.f7408c, this.f7409d)).b(j, TimeUnit.MILLISECONDS).c((b.a.d.g<? super cl, ? extends R>) new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.helper.n.o.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final cl apply(cl clVar) {
                    String output;
                    String strA;
                    Bundle bundle;
                    Bundle bundle2;
                    c.f.b.k.b(clVar, "result");
                    if (!clVar.b() && o.this.f7410e != null && (bundle2 = o.this.f7411f) != null) {
                        bundle2.putString(o.this.f7410e, clVar.a());
                    }
                    if (!(clVar instanceof cq)) {
                        return clVar;
                    }
                    Object objD = ((cq) clVar).d();
                    if ((objD instanceof OutputRunShell) && (output = ((OutputRunShell) objD).getOutput()) != null && (strA = com.joaomgcd.taskerm.util.ak.A(o.this.g)) != null && (bundle = o.this.f7411f) != null) {
                        bundle.putString(strA, output);
                    }
                    return clVar;
                }
            });
            c.f.b.k.a((Object) lVarC, "runShell(context, InputR…ult\n                    }");
            return lVarC;
        }
    }

    public final int a(net.dinglisch.android.taskerm.c cVar, Bundle bundle, fs fsVar, String str, long j2, boolean z, boolean z2, String str2, String str3) {
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(fsVar, "parentTask");
        c.f.b.k.b(str, "command");
        return a(cVar, bundle, fsVar, "Couldn't run shell with Tasker Settings", new o(j2, str, z, z2, str3, bundle, str2));
    }

    public final void a(int i2) {
        b.a.h<Object> hVarA = com.joaomgcd.taskerm.rx.b.f8378b.a().a((b.a.d.i<? super Object>) p.f7413a);
        if (hVarA != null) {
            b.a.l<Object> lVarD = hVarA.d();
            if (lVarD != null) {
                lVarD.b(i2, TimeUnit.MILLISECONDS).d(q.f7414a).b();
                return;
            }
            throw new c.q("null cannot be cast to non-null type io.reactivex.Single<T>");
        }
        throw new c.q("null cannot be cast to non-null type io.reactivex.Observable<T>");
    }

    public final b e() {
        b.a.h<Object> hVarA = com.joaomgcd.taskerm.rx.b.f8378b.a().a((b.a.d.i<? super Object>) r.f7415a);
        if (hVarA != null) {
            b.a.l<Object> lVarD = hVarA.d();
            if (lVarD != null) {
                Object objB = lVarD.b(5L, TimeUnit.SECONDS).b();
                c.f.b.k.a(objB, "EventBusRx.waitFor<SMSSe…it.SECONDS).blockingGet()");
                return (b) objB;
            }
            throw new c.q("null cannot be cast to non-null type io.reactivex.Single<T>");
        }
        throw new c.q("null cannot be cast to non-null type io.reactivex.Observable<T>");
    }
}
