package com.joaomgcd.taskerm.helper.actions.execute;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.helper.actions.execute.w;
import com.joaomgcd.taskerm.notification.af;
import com.joaomgcd.taskerm.notification.ag;
import com.joaomgcd.taskerm.securesettings.aa;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.az;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public abstract class q<TInput extends w> extends l<TInput> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f7199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.joaomgcd.taskerm.action.setting.a<TInput> f7200b;

    static final class a extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.securesettings.r, Integer> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f7202b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(boolean z) {
            super(1);
            this.f7202b = z;
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(com.joaomgcd.taskerm.securesettings.r rVar) {
            return Integer.valueOf(a(rVar));
        }

        public final int a(com.joaomgcd.taskerm.securesettings.r rVar) {
            c.f.b.k.b(rVar, "it");
            return q.a(q.this, rVar, this.f7202b, false, 4, (Object) null);
        }
    }

    static final class c extends c.f.b.l implements c.f.a.b<Integer, Integer> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(Integer num) {
            return Integer.valueOf(a(num.intValue()));
        }

        public final int a(int i) {
            return q.a(q.this, i, false, 2, (Object) null);
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<String, Integer> {
        d() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(String str) {
            return Integer.valueOf(a(str));
        }

        public final int a(String str) {
            c.f.b.k.b(str, "it");
            return q.a(q.this, str, false, false, 6, (Object) null);
        }
    }

    static final class f extends c.f.b.l implements c.f.a.b<Integer, Integer> {
        f() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(Integer num) {
            return Integer.valueOf(a(num.intValue()));
        }

        public final int a(int i) {
            return q.this.b(i, true);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, com.joaomgcd.taskerm.action.setting.a<TInput> aVar, Bundle bundle) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(aVar, "actionBaseCustomSetting");
        c.f.b.k.b(bundle, "taskVars");
        this.f7200b = aVar;
        this.f7199a = true;
    }

    public /* synthetic */ q(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, com.joaomgcd.taskerm.action.setting.a aVar, Bundle bundle, int i, c.f.b.g gVar) {
        this(executeService, cVar, aVar, (i & 8) != 0 ? new Bundle() : bundle);
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(TInput tinput) {
        c.f.b.k.b(tinput, "input");
        return new cm("Error with setting");
    }

    static final class e<T, R> implements b.a.d.g<Throwable, b.a.p<? extends Boolean>> {
        e() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> apply(Throwable th) {
            c.f.b.k.b(th, "it");
            bn.d("E", th.getMessage());
            if (th instanceof com.joaomgcd.taskerm.securesettings.a) {
                bn.c("E", "can't change setting: no write secure settings permission. Check notifications");
                q.this.h().f10621d.a(bt.f9101c.E(), new af(q.this.h()));
            }
            return b.a.l.a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Boolean a(b.a.l<Boolean> lVar) {
        return lVar.d(new e()).b();
    }

    public final b.a.l<Boolean> b(String str) {
        c.f.b.k.b(str, "value");
        return aa.a((Context) h(), (com.joaomgcd.taskerm.securesettings.f) this.f7200b.a(h(), str));
    }

    public static /* synthetic */ int a(q qVar, String str, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: apply");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return qVar.a(str, z, z2);
    }

    public final int a(String str, boolean z, boolean z2) {
        c.f.b.k.b(str, "value");
        if (!h().f10621d.a(bt.f9101c.K(), new ag(h()))) {
            bn.c("E", "can't change setting: no write system settings permission. Check notifications");
            return -3;
        }
        return a(this.f7200b.a(h(), str), z, z2);
    }

    static final class b extends c.f.b.l implements c.f.a.a<Boolean> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f7204b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.r f7205c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(boolean z, com.joaomgcd.taskerm.securesettings.r rVar) {
            super(0);
            this.f7204b = z;
            this.f7205c = rVar;
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0028  */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Boolean invoke() {
            b.a.l<Boolean> lVarA;
            q qVar = q.this;
            if (this.f7204b) {
                if (this.f7205c.h().length() == 0) {
                    lVarA = aa.b((Context) q.this.h(), this.f7205c);
                } else {
                    lVarA = aa.a((Context) q.this.h(), this.f7205c);
                }
            } else {
                lVarA = aa.a((Context) q.this.h(), this.f7205c);
            }
            return qVar.a(lVarA);
        }
    }

    public static /* synthetic */ int a(q qVar, com.joaomgcd.taskerm.securesettings.r rVar, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: apply");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return qVar.a(rVar, z, z2);
    }

    public final int a(com.joaomgcd.taskerm.securesettings.r rVar, boolean z, boolean z2) {
        c.f.b.k.b(rVar, "setting");
        b bVar = new b(z2, rVar);
        Boolean boolInvoke = bVar.invoke();
        if (!boolInvoke.booleanValue() && z) {
            Boolean boolB = az.c(h()).b();
            c.f.b.k.a((Object) boolB, "Init.canRoot(service).blockingGet()");
            if (boolB.booleanValue()) {
                rVar.a(true);
                boolInvoke = bVar.invoke();
            }
        }
        c.f.b.k.a((Object) boolInvoke, "success");
        return boolInvoke.booleanValue() ? -5 : -3;
    }

    public static /* synthetic */ cl a(q qVar, com.joaomgcd.taskerm.securesettings.r[] rVarArr, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: apply");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return qVar.a(rVarArr, z);
    }

    protected final <T> cl a(T t, c.f.a.b<? super T, Integer> bVar) {
        c.f.b.k.b(bVar, "block");
        if (t != null) {
            if (bVar.invoke(t).intValue() == -5) {
                return new co();
            }
            return new cm("Error with setting");
        }
        return new cm("No value to set");
    }

    public final cl a(Integer num) {
        return a(num, new c());
    }

    public final cl c(String str) {
        return a(str, new d());
    }

    public final cl b(Integer num) {
        return a(num, new f());
    }

    public static /* synthetic */ int a(q qVar, int i, boolean z, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: apply");
        }
        if ((i2 & 2) != 0) {
            z = true;
        }
        return qVar.a(i, z);
    }

    public final int a(int i, boolean z) {
        return a((q) this, String.valueOf(i), z, false, 4, (Object) null);
    }

    public final int b(int i, boolean z) {
        if (i != 2) {
            return a(i, z);
        }
        String strB = aa.b((Context) h(), (com.joaomgcd.taskerm.securesettings.f) this.f7200b.a(h(), "")).b();
        c.f.b.k.a((Object) strB, "get(service, actionBaseC…rvice, \"\")).blockingGet()");
        Integer numC = c.l.n.c(strB);
        if (numC == null) {
            return a(1, z);
        }
        return a(1 - numC.intValue(), z);
    }

    public final cl a(com.joaomgcd.taskerm.securesettings.r[] rVarArr, boolean z) {
        c.f.b.k.b(rVarArr, be.SETTINGS_LABEL);
        for (com.joaomgcd.taskerm.securesettings.r rVar : rVarArr) {
            cl clVarA = a(rVar, new a(z));
            if (!clVarA.b()) {
                return clVarA;
            }
        }
        return new co();
    }
}
