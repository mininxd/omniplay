package com.joaomgcd.taskerm.keyboard;

import android.content.ComponentName;
import android.content.Context;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.dm;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7574a = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f7575c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static d f7576d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.f.a.a<InputConnection> f7577b;

    static final class b extends c.f.b.l implements c.f.a.m<Integer, Integer, Boolean> {
        b() {
            super(2);
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(Integer num, Integer num2) {
            return Boolean.valueOf(a(num.intValue(), num2.intValue()));
        }

        public final boolean a(int i, int i2) {
            return d.this.j().setComposingRegion(i, i2);
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<Boolean> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            return d.this.j() != null;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.keyboard.d$d, reason: collision with other inner class name */
    static final class C0183d extends c.f.b.l implements c.f.a.m<Integer, Integer, Boolean> {
        C0183d() {
            super(2);
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(Integer num, Integer num2) {
            return Boolean.valueOf(a(num.intValue(), num2.intValue()));
        }

        public final boolean a(int i, int i2) {
            return d.this.a(i, i2);
        }
    }

    static final class e extends c.f.b.l implements c.f.a.m<Integer, Integer, Boolean> {
        e() {
            super(2);
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(Integer num, Integer num2) {
            return Boolean.valueOf(a(num.intValue(), num2.intValue()));
        }

        public final boolean a(int i, int i2) {
            return d.this.a(i, i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public d(c.f.a.a<? extends InputConnection> aVar) {
        c.f.b.k.b(aVar, "inputConnectionGetter");
        this.f7577b = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InputConnection j() {
        return this.f7577b.invoke();
    }

    public final boolean a() {
        Boolean bool = (Boolean) aj.a((c.f.a.b) null, new c(), 1, (Object) null);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private final String a(CharSequence charSequence) {
        String string;
        return (charSequence == null || (string = charSequence.toString()) == null) ? "" : string;
    }

    public final String b() {
        ExtractedText extractedText = j().getExtractedText(new ExtractedTextRequest(), 0);
        return a(extractedText != null ? extractedText.text : null);
    }

    public final String c() {
        return a(j().getSelectedText(0));
    }

    public final String d() {
        return a(j().getTextBeforeCursor(Integer.MAX_VALUE, 0));
    }

    public final String e() {
        return a(j().getTextAfterCursor(10000, 0));
    }

    public final boolean a(String str) {
        c.f.b.k.b(str, "text");
        return j().commitText(str, 1);
    }

    public static /* synthetic */ boolean a(d dVar, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        return dVar.a(str, str2);
    }

    public final boolean a(String str, String str2) {
        c.f.b.k.b(str, "newText");
        j().beginBatchEdit();
        if (str2 == null) {
            j().setComposingRegion(0, Integer.MAX_VALUE);
        } else {
            d(str2);
        }
        j().setComposingText(str, 1);
        j().finishComposingText();
        j().endBatchEdit();
        return true;
    }

    private final int d(int i) {
        if (i < 0) {
            return 0;
        }
        int length = b().length();
        return i > length ? length : i;
    }

    public final boolean a(int i, int i2) {
        return j().setSelection(d(i), d(i2));
    }

    public final boolean f() {
        return a(0, Integer.MAX_VALUE);
    }

    public final boolean b(String str) {
        c.f.b.k.b(str, "text");
        return a(str, new C0183d());
    }

    public final boolean c(String str) {
        c.f.b.k.b(str, "regex");
        return b(str, new e());
    }

    public final boolean d(String str) {
        c.f.b.k.b(str, "text");
        return a(str, new b());
    }

    private final boolean a(String str, c.f.a.m<? super Integer, ? super Integer, Boolean> mVar) {
        int iA = c.l.n.a((CharSequence) b(), str, 0, false, 6, (Object) null);
        if (iA < 0) {
            return false;
        }
        return mVar.invoke(Integer.valueOf(iA), Integer.valueOf(str.length() + iA)).booleanValue();
    }

    public static final class a {

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.keyboard.d$a$a, reason: collision with other inner class name */
        static final class C0181a extends c.f.b.l implements c.f.a.a<Boolean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f7578a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ long f7579b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ c.f.a.b f7580c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ boolean f7581d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0181a(Context context, long j, c.f.a.b bVar, boolean z) {
                super(0);
                this.f7578a = context;
                this.f7579b = j;
                this.f7580c = bVar;
                this.f7581d = z;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.keyboard.d$a$a$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<Boolean> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ c.f.b.w.b f7583b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ c.f.b.w.d f7584c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(c.f.b.w.b bVar, c.f.b.w.d dVar) {
                    super(0);
                    this.f7583b = bVar;
                    this.f7584c = dVar;
                }

                /* JADX INFO: renamed from: com.joaomgcd.taskerm.keyboard.d$a$a$1$a, reason: collision with other inner class name */
                public static final class C0182a<T> implements b.a.d.i<Object> {

                    /* JADX INFO: renamed from: a, reason: collision with root package name */
                    public static final C0182a f7585a = new C0182a();

                    @Override // b.a.d.i
                    public final boolean a(Object obj) {
                        c.f.b.k.b(obj, "it");
                        return obj instanceof d;
                    }
                }

                @Override // c.f.a.a
                public /* synthetic */ Boolean invoke() {
                    return Boolean.valueOf(a());
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.String] */
                /* JADX WARN: Type inference failed for: r3v9, types: [T, java.lang.String] */
                public final boolean a() {
                    d dVar;
                    while (true) {
                        if (this.f7583b.f2026a >= 5) {
                            return false;
                        }
                        try {
                            c.f.b.w.d dVar2 = this.f7584c;
                            ?? M = ah.m(C0181a.this.f7578a);
                            if (M != 0) {
                                dVar2.f2028a = M;
                                for (int i = 0; i < 5 && com.joaomgcd.taskerm.e.b.f6613b.a(); i++) {
                                    dm.b(1L);
                                }
                                String strA = d.f7574a.a(C0181a.this.f7578a);
                                boolean zA = c.f.b.k.a(this.f7584c.f2028a, (Object) strA);
                                d dVar3 = d.f7576d;
                                if (!(dVar3 != null ? dVar3.a() : false)) {
                                    d.f7575c = (String) this.f7584c.f2028a;
                                }
                                if (!zA) {
                                    d.f7575c = (String) this.f7584c.f2028a;
                                } else {
                                    c.f.b.w.d dVar4 = this.f7584c;
                                    ?? F = ah.f(C0181a.this.f7578a, d.f7575c);
                                    if (F == 0) {
                                        throw new com.joaomgcd.taskerm.keyboard.a("Couldn't find user keyboard settings. Please set your keyboard in Android Settings to something other than Tasker.");
                                    }
                                    dVar4.f2028a = F;
                                }
                                if (zA) {
                                    dVar = d.f7576d;
                                    if (dVar == null) {
                                        throw new com.joaomgcd.taskerm.keyboard.a("Couldn't connect with previous keyboard session");
                                    }
                                } else {
                                    ah.d(C0181a.this.f7578a, strA);
                                    long j = C0181a.this.f7579b;
                                    b.a.h<Object> hVarA = com.joaomgcd.taskerm.rx.b.f8378b.a().a((b.a.d.i<? super Object>) C0182a.f7585a);
                                    if (hVarA != null) {
                                        b.a.l<Object> lVarD = hVarA.d();
                                        if (lVarD == null) {
                                            throw new c.q("null cannot be cast to non-null type io.reactivex.Single<T>");
                                        }
                                        b.a.l<Object> lVarB = lVarD.b(j, TimeUnit.MILLISECONDS);
                                        c.f.b.k.a((Object) lVarB, "result.timeout(it, TimeUnit.MILLISECONDS)");
                                        Object objB = lVarB.b();
                                        d.f7576d = (d) objB;
                                        c.f.b.k.a(objB, "EventBusRx.waitFor<Input…reviousInputSender = it }");
                                        dVar = (d) objB;
                                    } else {
                                        throw new c.q("null cannot be cast to non-null type io.reactivex.Observable<T>");
                                    }
                                }
                                return ((Boolean) C0181a.this.f7580c.invoke(dVar)).booleanValue();
                            }
                            throw new com.joaomgcd.taskerm.keyboard.a("Couldn't get current Keyboard");
                        } catch (Throwable unused) {
                            bn.b("InputMethodServiceTasker", "Couldn't do keyboard action. Maybe another app stole keyboard? Current keyboard: " + ah.m(C0181a.this.f7578a) + ". Trying again");
                            ah.e(C0181a.this.f7578a, (String) this.f7584c.f2028a);
                            dm.a(1000L);
                            c.f.b.w.b bVar = this.f7583b;
                            bVar.f2026a = bVar.f2026a + 1;
                        }
                    }
                }
            }

            @Override // c.f.a.a
            public /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(a());
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
            public final boolean a() throws InterruptedException {
                c.f.b.w.d dVar = new c.f.b.w.d();
                dVar.f2028a = ah.m(this.f7578a);
                c.f.b.w.b bVar = new c.f.b.w.b();
                bVar.f2026a = 0;
                try {
                    if (new AnonymousClass1(bVar, dVar).a()) {
                    }
                    throw new com.joaomgcd.taskerm.keyboard.a("Couldn't perform keyboard action");
                } finally {
                    if (this.f7581d) {
                        ah.e(this.f7578a, (String) dVar.f2028a);
                        Thread.sleep(500L);
                    }
                }
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String a(Context context) {
            return new ComponentName(context, (Class<?>) InputMethodServiceTasker.class).flattenToString();
        }

        public final b.a.l<Boolean> a(Context context, long j, boolean z, c.f.a.b<? super d, Boolean> bVar) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(bVar, "block");
            return com.joaomgcd.taskerm.rx.i.b(new C0181a(context, j, bVar, z));
        }

        public final com.joaomgcd.taskerm.keyboard.e[] a(String str) {
            if (str == null) {
                return new com.joaomgcd.taskerm.keyboard.e[0];
            }
            try {
                return new com.joaomgcd.taskerm.keyboard.e[]{com.joaomgcd.taskerm.keyboard.e.valueOf(str)};
            } catch (Exception unused) {
                return new com.joaomgcd.taskerm.keyboard.e[0];
            }
        }
    }

    private final boolean b(String str, c.f.a.m<? super Integer, ? super Integer, Boolean> mVar) {
        c.i.c cVarA;
        c.l.i iVarF = ak.f(b(), str);
        if (iVarF == null || (cVarA = iVarF.a()) == null) {
            return false;
        }
        return mVar.invoke(Integer.valueOf(cVarA.a()), Integer.valueOf(cVarA.b() + 1)).booleanValue();
    }

    public final boolean e(String str) {
        c.f.b.k.b(str, "textToDelete");
        return a("", str);
    }

    public final boolean g() {
        return a(this, "", null, 2, null);
    }

    public final boolean a(int i) {
        return a(i, i);
    }

    public final boolean b(int i) {
        return j().performEditorAction(i);
    }

    public final boolean c(int i) {
        return j().performContextMenuAction(i);
    }

    private final KeyEvent a(int i, int i2, Integer num) {
        if (num == null) {
            return new KeyEvent(i, i2);
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        return new KeyEvent(jUptimeMillis, jUptimeMillis, i, i2, 0, num.intValue());
    }

    public final boolean a(int i, com.joaomgcd.taskerm.keyboard.e... eVarArr) {
        c.f.b.k.b(eVarArr, "modifiers");
        int iIntValue = 0;
        for (com.joaomgcd.taskerm.keyboard.e eVar : eVarArr) {
            Integer numA = eVar.a();
            if (numA != null) {
                iIntValue |= numA.intValue();
            }
        }
        if (!j().sendKeyEvent(a(0, i, Integer.valueOf(iIntValue)))) {
            return false;
        }
        if (c.a.d.b(eVarArr, com.joaomgcd.taskerm.keyboard.e.Long)) {
            dm.b(1L);
        }
        return j().sendKeyEvent(a(1, i, Integer.valueOf(iIntValue)));
    }
}
