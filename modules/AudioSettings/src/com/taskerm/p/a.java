package com.joaomgcd.taskerm.p;

import android.annotation.TargetApi;
import c.a.j;
import c.e.i;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.w;
import c.f.b.x;
import c.l.n;
import c.q;
import c.s;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.dm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8124a = new a();

    static final class f extends l implements c.f.a.a<Process> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.b f8146a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f8147b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ e f8148c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ w.d f8149d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ C0210a f8150e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(b.a.k.b bVar, d dVar, e eVar, w.d dVar2, C0210a c0210a) {
            super(0);
            this.f8146a = bVar;
            this.f8147b = dVar;
            this.f8148c = eVar;
            this.f8149d = dVar2;
            this.f8150e = c0210a;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.p.a$f$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.b<String, s> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(String str) {
                a(str);
                return s.f2131a;
            }

            /* JADX WARN: Type inference failed for: r0v7, types: [T, java.lang.Process] */
            public final void a(String str) {
                k.b(str, "fromWhere");
                boolean z = (!f.this.f8146a.k() || f.this.f8146a.m() || f.this.f8146a.l()) ? false : true;
                f.this.f8147b.a(str + " Should re-observe: " + z + ": " + f.this.f8146a.k() + '-' + f.this.f8146a.m() + '-' + f.this.f8146a.l());
                f.this.f8148c.a();
                if (z) {
                    f.this.f8149d.f2028a = f.this.invoke();
                }
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.p.a$f$2, reason: invalid class name */
        static final class AnonymousClass2 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ BufferedReader f8153b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f8154c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(BufferedReader bufferedReader, AnonymousClass1 anonymousClass1) {
                super(0);
                this.f8153b = bufferedReader;
                this.f8154c = anonymousClass1;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                try {
                    Iterator<String> itA = i.a(this.f8153b).a();
                    while (itA.hasNext()) {
                        String next = itA.next();
                        if (f.this.f8150e.b() == null) {
                            String strC = f.this.f8150e.c();
                            if (strC == null || n.b((CharSequence) next, (CharSequence) strC, false, 2, (Object) null)) {
                                if (!f.this.f8150e.a(next)) {
                                }
                            }
                        }
                        f.this.f8146a.b_(next);
                    }
                    dm.a(5000L);
                    this.f8154c.a("Stream ended");
                } catch (IOException unused) {
                    this.f8154c.a("Stream Closed");
                } catch (Exception e2) {
                    f.this.f8147b.a("Error monitoring: " + e2.getMessage());
                    b.a.k.b bVar = f.this.f8146a;
                    k.a((Object) bVar, "subject");
                    com.joaomgcd.taskerm.rx.i.a(bVar, e2);
                }
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Process invoke() {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            try {
                Process processExec = Runtime.getRuntime().exec(new String[]{"logcat", "-c"});
                k.a((Object) processExec, "Runtime.getRuntime().exec(arrayOf(\"logcat\", \"-c\"))");
                InputStream inputStream = processExec.getInputStream();
                k.a((Object) inputStream, "Runtime.getRuntime().exe…gcat\", \"-c\")).inputStream");
                Reader inputStreamReader = new InputStreamReader(inputStream, c.l.d.f2083a);
                i.a((Reader) (inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192)));
                this.f8147b.a("Monitoring logcat with component " + this.f8150e.c() + " and filter " + this.f8150e.a());
                String str = "logcat -v epoch";
                if (this.f8150e.c() != null) {
                    str = "logcat -v epoch \"" + this.f8150e.c() + "\" *:S";
                }
                String strB = this.f8150e.b();
                if (strB != null) {
                    str = str + " | grep --line-buffered " + strB;
                }
                Process processExec2 = Runtime.getRuntime().exec("sh");
                if (processExec2 == null) {
                    b.a.k.b bVar = this.f8146a;
                    k.a((Object) bVar, "subject");
                    com.joaomgcd.taskerm.rx.i.a(bVar, "Couldn't start logcat process");
                    return null;
                }
                InputStream inputStream2 = processExec2.getInputStream();
                k.a((Object) inputStream2, "inputStream");
                Reader inputStreamReader2 = new InputStreamReader(inputStream2, c.l.d.f2083a);
                com.joaomgcd.taskerm.rx.i.c(new AnonymousClass2(inputStreamReader2 instanceof BufferedReader ? (BufferedReader) inputStreamReader2 : new BufferedReader(inputStreamReader2, 8192), anonymousClass1));
                OutputStream outputStream = processExec2.getOutputStream();
                k.a((Object) outputStream, "processInner.outputStream");
                Writer outputStreamWriter = new OutputStreamWriter(outputStream, c.l.d.f2083a);
                BufferedWriter bufferedWriter = outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
                bufferedWriter.flush();
                bufferedWriter.write(("stop_shell() { if [ ! -z $logcat_pid ]; then kill $logcat_pid 2>/dev/null ; fi ; exit 0 ; } ; trap 'stop_shell' SIGTERM ; trap 'stop_shell' SIGINT ; { " + str + " & } ; logcat_pid=$! ; wait $logcat_pid ;") + '\n');
                bufferedWriter.flush();
                bufferedWriter.close();
                return processExec2;
            } catch (Throwable th) {
                b.a.k.b bVar2 = this.f8146a;
                k.a((Object) bVar2, "subject");
                com.joaomgcd.taskerm.rx.i.a(bVar2, th);
                return null;
            }
        }
    }

    static final class d extends l implements c.f.a.b<String, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ C0210a f8143a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b.a.k.b f8144b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(C0210a c0210a, b.a.k.b bVar) {
            super(1);
            this.f8143a = c0210a;
            this.f8144b = bVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(String str) {
            a(str);
            return s.f2131a;
        }

        public final void a(String str) {
            k.b(str, "str");
            c.f.a.b<String, s> bVarD = this.f8143a.d();
            if (bVarD != null) {
                bVarD.invoke(this.f8144b + ": " + str);
            }
        }
    }

    static final class e extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ w.d f8145a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(w.d dVar) {
            super(0);
            this.f8145a = dVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void a() {
            if (com.joaomgcd.taskerm.util.f.f9323b.r()) {
                Process process = (Process) this.f8145a.f2028a;
                if (process != null) {
                    process.destroyForcibly();
                    return;
                }
                return;
            }
            Process process2 = (Process) this.f8145a.f2028a;
            if (process2 != null) {
                process2.destroy();
            }
        }
    }

    static final class g extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ w.d f8155a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ f f8156b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f8157c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ e f8158d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(w.d dVar, f fVar, d dVar2, e eVar) {
            super(0);
            this.f8155a = dVar;
            this.f8156b = fVar;
            this.f8157c = dVar2;
            this.f8158d = eVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Process] */
        public final void a() {
            this.f8155a.f2028a = this.f8156b.invoke();
            dm.a(15000L);
            this.f8157c.a("Reopening stream after 15 seconds");
            this.f8158d.a();
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.p.a$a, reason: collision with other inner class name */
    public static class C0210a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f8125a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Boolean f8126b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f8127c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private c.f.a.b<? super String, s> f8128d;

        public C0210a() {
            this(null, null, null, null, 15, null);
        }

        public C0210a(String str, String str2, Boolean bool, c.f.a.b<? super String, s> bVar) {
            this.f8127c = str;
            this.f8128d = bVar;
            this.f8125a = str2;
            this.f8126b = bool;
        }

        public /* synthetic */ C0210a(String str, String str2, Boolean bool, c.f.a.b bVar, int i, c.f.b.g gVar) {
            this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (Boolean) null : bool, (i & 8) != 0 ? (c.f.a.b) null : bVar);
        }

        public final String c() {
            return this.f8127c;
        }

        public final void c(String str) {
            this.f8127c = str;
        }

        public final c.f.a.b<String, s> d() {
            return this.f8128d;
        }

        public final String a() {
            return this.f8125a;
        }

        public final void b(String str) {
            this.f8125a = str;
        }

        public final void a(Boolean bool) {
            this.f8126b = bool;
        }

        public boolean a(String str) {
            String str2;
            k.b(str, "log");
            if (b() == null && (str2 = this.f8125a) != null) {
                return n.b((CharSequence) str, (CharSequence) str2, false, 2, (Object) null);
            }
            return true;
        }

        public final String b() {
            if (aj.c(this.f8126b)) {
                return this.f8125a;
            }
            return null;
        }
    }

    private a() {
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ c.j.g[] f8129a = {x.a(new v(x.a(b.class), "split", "getSplit()Ljava/util/List;")), x.a(new v(x.a(b.class), "date", "getDate()Ljava/util/Date;")), x.a(new v(x.a(b.class), "pid", "getPid()Ljava/lang/Integer;")), x.a(new v(x.a(b.class), "tid", "getTid()Ljava/lang/Integer;")), x.a(new v(x.a(b.class), "level", "getLevel()Lcom/joaomgcd/taskerm/logcat/LogcatLevel;")), x.a(new v(x.a(b.class), "component", "getComponent()Ljava/lang/String;")), x.a(new v(x.a(b.class), "log", "getLog()Ljava/lang/String;"))};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.e f8130b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c.e f8131c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final c.e f8132d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final c.e f8133e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final c.e f8134f;
        private final c.e g;
        private final c.e h;
        private final String i;

        /* JADX INFO: Access modifiers changed from: private */
        public final List<String> c() {
            c.e eVar = this.f8130b;
            c.j.g gVar = f8129a[0];
            return (List) eVar.b();
        }

        public final String a() {
            c.e eVar = this.g;
            c.j.g gVar = f8129a[5];
            return (String) eVar.b();
        }

        public final String b() {
            c.e eVar = this.h;
            c.j.g gVar = f8129a[6];
            return (String) eVar.b();
        }

        static final class f extends l implements c.f.a.a<List<? extends String>> {
            f() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final List<String> invoke() {
                String str = b.this.i;
                if (str != null) {
                    return ak.a((CharSequence) n.b((CharSequence) str).toString(), " ");
                }
                throw new q("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }

        public b(String str) {
            k.b(str, "raw");
            this.i = str;
            this.f8130b = c.f.a(new f());
            this.f8131c = c.f.a(new C0212b());
            this.f8132d = c.f.a(new e());
            this.f8133e = c.f.a(new g());
            this.f8134f = c.f.a(new c());
            this.g = c.f.a(new C0211a());
            this.h = c.f.a(new d());
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.p.a$b$b, reason: collision with other inner class name */
        static final class C0212b extends l implements c.f.a.a<Date> {
            C0212b() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Date invoke() {
                Float fA;
                String str = (String) j.a(b.this.c(), 0);
                if (str == null || (fA = n.a(str)) == null) {
                    return null;
                }
                return new Date((long) (fA.floatValue() * 1000.0f));
            }
        }

        static final class e extends l implements c.f.a.a<Integer> {
            e() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Integer invoke() {
                String str = (String) j.a(b.this.c(), 1);
                if (str != null) {
                    return n.c(str);
                }
                return null;
            }
        }

        static final class g extends l implements c.f.a.a<Integer> {
            g() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Integer invoke() {
                String str = (String) j.a(b.this.c(), 2);
                if (str != null) {
                    return n.c(str);
                }
                return null;
            }
        }

        static final class c extends l implements c.f.a.a<com.joaomgcd.taskerm.p.b> {
            c() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.p.b invoke() {
                String str = (String) j.a(b.this.c(), 3);
                if (str == null) {
                    return null;
                }
                for (com.joaomgcd.taskerm.p.b bVar : com.joaomgcd.taskerm.p.b.values()) {
                    if (k.a((Object) bVar.a(), (Object) str)) {
                        return bVar;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.p.a$b$a, reason: collision with other inner class name */
        static final class C0211a extends l implements c.f.a.a<String> {
            C0211a() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String strA;
                String str = (String) j.a(b.this.c(), 4);
                if (str == null || (strA = ak.a(str, ":")) == null) {
                    return null;
                }
                if (strA != null) {
                    return n.b((CharSequence) strA).toString();
                }
                throw new q("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }

        static final class d extends l implements c.f.a.a<String> {
            d() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String strB = n.b(b.this.i, ":", (String) null, 2, (Object) null);
                if (strB != null) {
                    return n.b((CharSequence) strB).toString();
                }
                throw new q("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!k.a(getClass(), obj != null ? obj.getClass() : null)) {
                return false;
            }
            if (obj != null) {
                b bVar = (b) obj;
                return ((k.a((Object) a(), (Object) bVar.a()) ^ true) || (k.a((Object) b(), (Object) bVar.b()) ^ true)) ? false : true;
            }
            throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.logcat.Logcat.Log");
        }

        public int hashCode() {
            String strA = a();
            return (strA != null ? strA.hashCode() : 0) ^ b().hashCode();
        }
    }

    static final class c<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8142a = new c();

        c() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b apply(String str) {
            k.b(str, "it");
            return new b(str);
        }
    }

    public final b.a.h<b> a(C0210a c0210a) {
        k.b(c0210a, "listener");
        return b(c0210a).d(c.f8142a);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Process] */
    @TargetApi(26)
    public final b.a.h<String> b(C0210a c0210a) {
        k.b(c0210a, "listener");
        b.a.k.b bVarJ = b.a.k.b.j();
        d dVar = new d(c0210a, bVarJ);
        w.d dVar2 = new w.d();
        dVar2.f2028a = (Process) 0;
        e eVar = new e(dVar2);
        com.joaomgcd.taskerm.rx.i.c(new g(dVar2, new f(bVarJ, dVar, eVar, dVar2, c0210a), dVar, eVar));
        b.a.h hVarB = bVarJ.b((b.a.d.a) new h(eVar));
        k.a((Object) hVarB, "subject.doOnDispose {\n  …estroyProcess()\n        }");
        return hVarB;
    }

    static final class h implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ e f8159a;

        h(e eVar) {
            this.f8159a = eVar;
        }

        @Override // b.a.d.a
        public final void run() {
            this.f8159a.a();
        }
    }
}
