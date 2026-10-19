package com.joaomgcd.taskerm.tether;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8740a = {c.f.b.x.a(new c.f.b.t(c.f.b.x.a(ab.class, "Tasker(5.15.14)_marketNoTrialRelease"), "iConnectivityManager", "getIConnectivityManager()Ljava/lang/Class;")), c.f.b.x.a(new c.f.b.t(c.f.b.x.a(ab.class, "Tasker(5.15.14)_marketNoTrialRelease"), "classOnStartTetheringCallback", "getClassOnStartTetheringCallback()Ljava/lang/Class;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f8741b = c.f.a(c.f8745a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.e f8742c = c.f.a(a.f8743a);

    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(int i) {
        switch (i) {
            case 0:
                return "OK";
            case 1:
                return "Unkonwn Iface";
            case 2:
                return "Service Unavailable";
            case 3:
                return "Unsupported";
            case 4:
                return "Unavailable Iface";
            case 5:
                return "Master Error";
            case 6:
                return "Tether Iface Error";
            case 7:
                return "Untether Iface Rrror";
            case 8:
                return "Enable NAT Error";
            case 9:
                return "Disable NAT Error";
            case 10:
                return "Iface Configuration Error";
            case 11:
                return "Provision Failed";
            default:
                return "Unkown error";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Class<?> c() {
        c.e eVar = f8741b;
        c.j.g gVar = f8740a[0];
        return (Class) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Class<?> d() {
        c.e eVar = f8742c;
        c.j.g gVar = f8740a[1];
        return (Class) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object b(Context context, c.f.a.b<? super Integer, c.s> bVar) {
        return com.android.b.e.a.a(d()).a(context.getCacheDir()).a(new b(bVar)).a();
    }

    static final class b implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8744a;

        b(c.f.a.b bVar) {
            this.f8744a = bVar;
        }

        @Override // java.lang.reflect.InvocationHandler
        public /* synthetic */ Object invoke(Object obj, Method method, Object[] objArr) {
            a(obj, method, objArr);
            return c.s.f2131a;
        }

        public final void a(Object obj, Method method, Object[] objArr) {
            c.f.b.k.a((Object) method, "method");
            String name = method.getName();
            if (name != null) {
                int iHashCode = name.hashCode();
                if (iHashCode != -1785200688) {
                    if (iHashCode == 1908223758 && name.equals("onTetheringFailed")) {
                        this.f8744a.invoke(2);
                        return;
                    }
                } else if (name.equals("onTetheringStarted")) {
                    this.f8744a.invoke(0);
                    return;
                }
            }
            this.f8744a.invoke(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConnectivityManager c(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object d(Context context) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField;
        ConnectivityManager connectivityManagerC = c(context);
        if (connectivityManagerC == null) {
            throw new RuntimeException("couldn't get tether status");
        }
        try {
            declaredField = connectivityManagerC.getClass().getDeclaredField("mService");
        } catch (NoSuchFieldException unused) {
            declaredField = connectivityManagerC.getClass().getDeclaredField("mServiceEx");
        }
        declaredField.setAccessible(true);
        Object obj = declaredField.get(connectivityManagerC);
        c.f.b.k.a(obj, "get(connectivityManager)");
        c.f.b.k.a(obj, "serviceField.apply {\n   …et(connectivityManager) }");
        return obj;
    }

    static final class c extends c.f.b.l implements c.f.a.a<Class<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8745a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return Class.forName("android.net.IConnectivityManager");
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<Class<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8743a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return Class.forName("android.net.ConnectivityManager$OnStartTetheringCallback");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ResultReceiver b(final b.a.k.d<cl> dVar) {
        final Handler handler = com.joaomgcd.taskerm.rx.i.f8398c;
        return new ResultReceiver(handler) { // from class: com.joaomgcd.taskerm.tether.TetherKt$getResultReceiver$1
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                cl cmVar;
                super.onReceiveResult(i, bundle);
                b.a.k.d dVar2 = dVar;
                if (i == 0) {
                    cmVar = new co();
                } else {
                    cmVar = new cm("couldn't enable tether: \"" + ab.b(i) + '\"');
                }
                dVar2.d_(cmVar);
            }
        };
    }

    private static final cl a(Context context, boolean z, String str, c.f.a.b<? super String, c.s> bVar, ac... acVarArr) {
        return new af(bVar, (ac[]) Arrays.copyOf(acVarArr, acVarArr.length)).a();
    }

    public static final cl a(Context context, String str, c.f.a.b<? super String, c.s> bVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "action");
        c.f.b.k.b(bVar, "debugger");
        return a(context, true, str, bVar, new l(context), new j(context), new k(context));
    }

    public static final cl b(Context context, String str, c.f.a.b<? super String, c.s> bVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "action");
        c.f.b.k.b(bVar, "debugger");
        return a(context, false, str, bVar, new f(context), new d(context), new e(context));
    }

    public static final cl c(Context context, String str, c.f.a.b<? super String, c.s> bVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "action");
        c.f.b.k.b(bVar, "debugger");
        return a(context, true, str, bVar, new i(context), new g(context), new h(context));
    }

    public static final cl d(Context context, String str, c.f.a.b<? super String, c.s> bVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "action");
        c.f.b.k.b(bVar, "debugger");
        return a(context, false, str, bVar, new com.joaomgcd.taskerm.tether.c(context), new com.joaomgcd.taskerm.tether.a(context), new com.joaomgcd.taskerm.tether.b(context));
    }
}
