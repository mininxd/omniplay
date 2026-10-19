package com.joaomgcd.taskerm.b;

import android.content.Context;
import com.joaomgcd.taskerm.function.FunctionArgs;
import com.joaomgcd.taskerm.function.FunctionBaseKt;
import com.joaomgcd.taskerm.settings.ad;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5955a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f5956b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f5957c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f5958d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Context f5959e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final m f5960f;
    private final o g;

    public static final b.a.l<q> a(Context context, String str, boolean z, boolean z2) {
        return f5955a.a(context, str, z, z2);
    }

    public p(Context context, m mVar, o oVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(mVar, "adbService");
        this.f5959e = context;
        this.f5960f = mVar;
        this.g = oVar;
        this.f5956b = this.f5960f.b();
        this.f5957c = this.f5960f.c();
        o oVar2 = this.g;
        this.f5958d = oVar2 != null ? oVar2.c() : null;
    }

    public final Context b() {
        return this.f5959e;
    }

    public final String a(Object... objArr) {
        c.f.b.k.b(objArr, "params");
        o oVar = this.g;
        if (oVar != null) {
            return oVar.a(Arrays.copyOf(objArr, objArr.length));
        }
        return null;
    }

    public static /* synthetic */ b.a.l a(p pVar, Object[] objArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: runCommand");
        }
        if ((i2 & 2) != 0) {
            i = 1000;
        }
        return pVar.a(objArr, i);
    }

    public final b.a.l<String> a(Object[] objArr, int i) {
        c.f.b.k.b(objArr, "params");
        String strA = a(Arrays.copyOf(objArr, objArr.length));
        if (strA == null) {
            b.a.l<String> lVarB = b.a.l.b(new RuntimeException("Couldn't get command for " + this));
            c.f.b.k.a((Object) lVarB, "Single.error(RuntimeExce… get command for $this\"))");
            return lVarB;
        }
        b.a.l<String> lVarC = com.joaomgcd.taskerm.b.a.b(new com.joaomgcd.taskerm.b.a(this.f5959e), strA, i, true, 0, null, true, 24, null).c(b.f5966a);
        c.f.b.k.a((Object) lVarC, "ADB(context).sendLocalCo…iceList.parseOutput(it) }");
        return lVarC;
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5966a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(String str) {
            c.f.b.k.b(str, "it");
            return n.f5931a.d(str);
        }
    }

    public String toString() {
        return this.f5960f + " - " + this.f5958d;
    }

    public final String b(Object... objArr) {
        String string;
        c.f.b.k.b(objArr, "params");
        if (this.f5958d == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            if (obj instanceof Boolean) {
                string = ((Boolean) obj).booleanValue() ? "true" : "false";
            } else if (obj instanceof String) {
                StringBuilder sb = new StringBuilder();
                sb.append('\"');
                sb.append(obj);
                sb.append('\"');
                string = sb.toString();
            } else {
                string = obj.toString();
            }
            arrayList.add(string);
        }
        String str = this.f5958d;
        String str2 = this.f5956b;
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return new FunctionArgs(str, str2, (String[]) array, false, 8, null).toString();
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public p(Context context, String str, String str2, String str3) {
        this(context, new m(0, str, str2), str3);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "component");
        c.f.b.k.b(str2, "interfaceName");
        c.f.b.k.b(str3, "methodName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public p(Context context, m mVar, String str) {
        this(context, mVar, mVar.a(str));
        c.f.b.k.b(context, "context");
        c.f.b.k.b(mVar, "adbService");
        c.f.b.k.b(str, "methodName");
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final b.a.l<q> a(Context context, String str, boolean z, boolean z2) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "command");
            return a(this, context, str, 0, null, z2, z, 12, null);
        }

        public static /* synthetic */ b.a.l a(a aVar, Context context, String str, int i, String str2, boolean z, boolean z2, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                i = ad.a(context);
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                str2 = "localhost";
            }
            return aVar.a(context, str, i3, str2, (i2 & 16) != 0 ? true : z, (i2 & 32) != 0 ? false : z2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r1v16, types: [java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Boolean] */
        /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v5 */
        public final b.a.l<q> a(Context context, String str, int i, String str2, boolean z, boolean z2) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "command");
            c.f.b.k.b(str2, "host");
            FunctionArgs functionArgs = (FunctionArgs) c.a.j.a((List) FunctionBaseKt.getFunctionArgs(str), 0);
            if (functionArgs == null) {
                throw new RuntimeException(str + " doesn't have a valid format");
            }
            String component = functionArgs.getComponent();
            if (component == null) {
                throw new RuntimeException(str + " doesn't have a valid component");
            }
            String name = functionArgs.getName();
            ?? trimmedParameters = functionArgs.getTrimmedParameters();
            ArrayList arrayList = new ArrayList(trimmedParameters.length);
            for (?? ValueOf : trimmedParameters) {
                if (c.f.b.k.a((Object) ValueOf, (Object) "true") || c.f.b.k.a((Object) ValueOf, (Object) "false")) {
                    ValueOf = Boolean.valueOf(Boolean.parseBoolean(ValueOf));
                } else if (c.l.n.a((String) ValueOf, "\"", false, 2, (Object) null) && c.l.n.b((String) ValueOf, "\"", false, 2, (Object) null)) {
                    String str3 = (String) aj.a((c.f.a.b) null, new b(ValueOf), 1, (Object) null);
                    if (str3 == null) {
                        throw new RuntimeException(ValueOf + " is not a valid string parameter");
                    }
                    ValueOf = str3;
                }
                arrayList.add(ValueOf);
            }
            b.a.l lVarC = n.f5931a.a(context, component, i, str2, z, z2).c(new C0113a(context, component, name, arrayList));
            c.f.b.k.a((Object) lVarC, "ADBServiceList.getServic…pedArray())\n            }");
            return lVarC;
        }

        static final class b extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f5965a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(String str) {
                super(0);
                this.f5965a = str;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String str = this.f5965a;
                int length = this.f5965a.length() - 1;
                if (str == null) {
                    throw new c.q("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring = str.substring(1, length);
                c.f.b.k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return strSubstring;
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.p$a$a, reason: collision with other inner class name */
        static final class C0113a<T, R> implements b.a.d.g<T, R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f5961a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f5962b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f5963c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ List f5964d;

            C0113a(Context context, String str, String str2, List list) {
                this.f5961a = context;
                this.f5962b = str;
                this.f5963c = str2;
                this.f5964d = list;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final q apply(String str) {
                c.f.b.k.b(str, "interfaceName");
                p pVar = new p(this.f5961a, this.f5962b, str, this.f5963c);
                Object[] array = this.f5964d.toArray(new Object[0]);
                if (array != null) {
                    return new q(pVar, array);
                }
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
    }
}
