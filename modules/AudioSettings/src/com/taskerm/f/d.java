package com.joaomgcd.taskerm.f;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.helper.b;
import com.joaomgcd.taskerm.helper.l;
import com.joaomgcd.taskerm.inputoutput.Toggle;
import com.joaomgcd.taskerm.util.App;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cb;
import com.joaomgcd.taskerm.util.cc;
import cyanogenmod.app.ProfileManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import net.dinglisch.android.taskerm.HasArgsEdit;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.fj;
import net.dinglisch.android.taskerm.gw;
import net.dinglisch.android.taskerm.y;

/* JADX INFO: loaded from: classes.dex */
public abstract class d<TInput, THelperEdit extends com.joaomgcd.taskerm.helper.l<THasArguments, TInput, THasArgumentsEdit>, THasArgumentsEdit extends HasArgsEdit & com.joaomgcd.taskerm.helper.b, THasArguments extends au, TSpec extends fj> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6791b = {x.a(new v(x.a(d.class), "inputClass", "getInputClass()Ljava/lang/Class;")), x.a(new v(x.a(d.class), "helpResIds", "getHelpResIds()Ljava/util/HashMap;"))};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.e f6792a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6793c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Integer f6794d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Integer f6795e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f6796f;
    private final TSpec g;

    private final HashMap<Integer, a> b() {
        c.e eVar = this.f6793c;
        c.j.g gVar = f6791b[1];
        return (HashMap) eVar.b();
    }

    public abstract THelperEdit a(THasArgumentsEdit thasargumentsedit);

    public Integer a(Resources resources, int i, THasArguments thasarguments) {
        c.f.b.k.b(resources, "res");
        return null;
    }

    protected abstract String a();

    public String[] b(Context context, TInput tinput) {
        c.f.b.k.b(context, "context");
        return null;
    }

    public abstract TInput d();

    public boolean d(int i) {
        return true;
    }

    public com.joaomgcd.taskerm.v.d<TInput> g() {
        return null;
    }

    public final Class<? extends TInput> h() {
        c.e eVar = this.f6792a;
        c.j.g gVar = f6791b[0];
        return (Class) eVar.b();
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return true;
    }

    public d(TSpec tspec) {
        c.f.b.k.b(tspec, "spec");
        this.g = tspec;
        this.f6792a = c.f.a(new C0147d());
        this.f6793c = c.f.a(new c());
        this.f6796f = this.g.g();
    }

    public final TSpec m() {
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String[] a(d dVar, Context context, Object obj, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPermissions");
        }
        if ((i & 2) != 0) {
            obj = null;
        }
        return dVar.b(context, obj);
    }

    public final boolean a(Context context, String[] strArr, TInput tinput) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(strArr, "permissions");
        return aj.a((Object[]) b(context, tinput), (Object[]) strArr);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.f.d$d, reason: collision with other inner class name */
    static final class C0147d extends c.f.b.l implements c.f.a.a<Class<? extends TInput>> {
        C0147d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<? extends TInput> invoke() {
            return (Class<? extends TInput>) d.this.d().getClass();
        }
    }

    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f6797a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f6798b;

        public a(int i, String str) {
            this.f6797a = i;
            this.f6798b = str;
        }

        public final int a() {
            return this.f6797a;
        }

        public final String b() {
            return this.f6798b;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<HashMap<Integer, a>> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<Integer, a> invoke() {
            HashMap<Integer, a> map = new HashMap<>();
            Iterator it = Cdo.a(d.this.h(), com.joaomgcd.taskerm.inputoutput.b.class).iterator();
            while (it.hasNext()) {
                com.joaomgcd.taskerm.inputoutput.b bVar = (com.joaomgcd.taskerm.inputoutput.b) ((cb) it.next()).d();
                int iE = bVar.e();
                if (iE != 0) {
                    map.put(Integer.valueOf(bVar.a()), new a(iE, ak.A(bVar.g())));
                }
            }
            return map;
        }
    }

    public final Integer a(int i) {
        a aVar = b().get(Integer.valueOf(i));
        if (aVar != null) {
            return Integer.valueOf(aVar.a());
        }
        return null;
    }

    public final String b(int i) {
        a aVar = b().get(Integer.valueOf(i));
        if (aVar != null) {
            return aVar.b();
        }
        return null;
    }

    public final boolean c(int i) {
        return a(i) != null;
    }

    public final boolean c(Context context, TInput tinput) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(tinput, "input");
        String[] strArrB = b(context, tinput);
        if (strArrB != null) {
            return new bt(context, 0, (String[]) Arrays.copyOf(strArrB, strArrB.length), 2, (c.f.b.g) null).g();
        }
        return true;
    }

    public Integer e() {
        return this.f6794d;
    }

    public Integer f() {
        return this.f6795e;
    }

    public final int i() {
        return this.f6796f;
    }

    public final String j() {
        String strA = a();
        return strA != null ? strA : "???";
    }

    public final String a(String str) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        return '%' + str;
    }

    protected final TInput a(THasArguments thasarguments, Context context, Bundle bundle) {
        c.f.b.k.b(thasarguments, "receiver$0");
        c.f.b.k.b(context, "context");
        TInput tinputD = d();
        a(tinputD, context, thasarguments, bundle);
        return tinputD;
    }

    static final class e extends c.f.b.l implements c.f.a.b<cc<? extends TInput, ? extends com.joaomgcd.taskerm.inputoutput.b>, Object> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ au f6808b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Context f6809c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Bundle f6810d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(au auVar, Context context, Bundle bundle) {
            super(1);
            this.f6808b = auVar;
            this.f6809c = context;
            this.f6810d = bundle;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Object invoke(cc<? extends TInput, com.joaomgcd.taskerm.inputoutput.b> ccVar) {
            c.f.b.k.b(ccVar, "it");
            Class<?> clsA = ccVar.a();
            com.joaomgcd.taskerm.inputoutput.b bVarD = ccVar.d();
            int iA = bVarD.a();
            if (ccVar.e() instanceof Class) {
                return ccVar.e();
            }
            if (!c.f.b.k.a(clsA, Toggle.class)) {
                return d.this.a(this.f6809c, this.f6808b, iA, clsA, this.f6810d, bVarD.d(), bVarD.f());
            }
            net.dinglisch.android.taskerm.e eVarH = this.f6808b.h(iA);
            c.f.b.k.a((Object) eVarH, "hasArguments.getBoolArg(index)");
            return (Enum) aj.a(eVarH.h(), Toggle.class);
        }
    }

    protected final void a(TInput tinput, Context context, THasArguments thasarguments, Bundle bundle) {
        c.f.b.k.b(tinput, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(thasarguments, "hasArguments");
        com.joaomgcd.taskerm.inputoutput.c.a(tinput, new e(thasarguments, context, bundle));
    }

    public final <T> T a(Context context, THasArguments thasarguments, int i, Class<T> cls, Bundle bundle, boolean z, boolean z2) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(thasarguments, "hasArguments");
        c.f.b.k.b(cls, "clzz");
        if (cls.isEnum()) {
            net.dinglisch.android.taskerm.h hVarG = thasarguments.g(i);
            c.f.b.k.a((Object) hVarG, "hasArguments.getIntArg(index)");
            return (T) aj.a(hVarG.h(), cls);
        }
        b bVar = new b(thasarguments, i, z, z2, context, bundle);
        if (c.f.b.k.a(cls, String.class)) {
            return (T) bVar.invoke();
        }
        if (c.f.b.k.a(cls, AppBasic.class)) {
            String strInvoke = bVar.invoke();
            if (strInvoke != null) {
                return (T) App.Companion.a(context, strInvoke);
            }
            return null;
        }
        if (c.f.b.k.a(cls, Integer.TYPE) || c.f.b.k.a(cls, Integer.class)) {
            return (T) Integer.valueOf(thasarguments.g(i).a(context, bundle));
        }
        if (c.f.b.k.a(cls, Long.TYPE) || c.f.b.k.a(cls, Long.class)) {
            String strInvoke2 = bVar.invoke();
            return (T) (strInvoke2 != null ? c.l.n.d(strInvoke2) : null);
        }
        if (!c.f.b.k.a(cls, Boolean.class)) {
            return null;
        }
        net.dinglisch.android.taskerm.e eVarH = thasarguments.h(i);
        c.f.b.k.a((Object) eVarH, "hasArguments.getBoolArg(index)");
        return (T) Boolean.valueOf(eVarH.b());
    }

    static final class b extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ au f6799a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f6800b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f6801c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f6802d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Context f6803e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ Bundle f6804f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(au auVar, int i, boolean z, boolean z2, Context context, Bundle bundle) {
            super(0);
            this.f6799a = auVar;
            this.f6800b = i;
            this.f6801c = z;
            this.f6802d = z2;
            this.f6803e = context;
            this.f6804f = bundle;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            net.dinglisch.android.taskerm.j jVarJ = this.f6799a.j(this.f6800b);
            c.f.b.k.a((Object) jVarJ, "hasArguments.getStringArg(index)");
            String strC = jVarJ.c();
            if (!this.f6801c) {
                if (this.f6802d) {
                    Context context = this.f6803e;
                    String strM = this.f6799a.m();
                    c.f.b.k.a((Object) strM, "hasArguments.displayName");
                    strC = ak.a(strC, context, strM, 3, this.f6804f, (16 & 16) != 0 ? false : false);
                } else {
                    strC = gw.a(this.f6803e, strC, this.f6802d, this.f6804f);
                }
            }
            if (strC == null) {
                return strC;
            }
            if (strC.length() == 0) {
                return null;
            }
            return strC;
        }
    }

    public final boolean b(THasArgumentsEdit thasargumentsedit) {
        c.f.b.k.b(thasargumentsedit, "hasArgsEdit");
        return a(thasargumentsedit).m();
    }

    public final boolean b(Context context, THasArguments thasarguments, Bundle bundle) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(thasarguments, "hasArguments");
        if (!k()) {
            return true;
        }
        y yVarO = thasarguments.o();
        return yVarO != null ? yVarO.a(context, true, null, bundle, "stateVarValEasy") : l();
    }

    /* JADX WARN: Code duplicated, block: B:25:0x003a  */
    /* JADX WARN: Code duplicated, block: B:27:0x003d A[RETURN] */
    public final com.joaomgcd.taskerm.v.b a(Boolean bool, String str) {
        com.joaomgcd.taskerm.v.d<TInput> dVarG;
        if (str == null) {
            return null;
        }
        if ((!c.f.b.k.a((Object) bool, (Object) true)) || (dVarG = g()) == null) {
            return null;
        }
        String[] strArrB = dVarG.b();
        if (strArrB.length == 0) {
            return com.joaomgcd.taskerm.v.b.Auto;
        }
        for (String str2 : strArrB) {
            if (c.l.n.b(str, str2, false, 2, (Object) null)) {
                if (str2 != null) {
                    return com.joaomgcd.taskerm.v.b.Auto;
                }
                return null;
            }
        }
        str2 = null;
        if (str2 != null) {
            return com.joaomgcd.taskerm.v.b.Auto;
        }
        return null;
    }
}
