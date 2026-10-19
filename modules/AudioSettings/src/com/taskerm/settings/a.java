package com.joaomgcd.taskerm.settings;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f8569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8570b;

    public a(int i, String str) {
        this.f8569a = i;
        this.f8570b = str;
    }

    public final int f() {
        return this.f8569a;
    }

    public final String g() {
        return this.f8570b;
    }

    public final String a() {
        String strU = net.dinglisch.android.taskerm.n.u(this.f8569a);
        c.f.b.k.a((Object) strU, "ActionSpecs.getActionNameByCode(code)");
        return strU;
    }

    public final Integer b() {
        return net.dinglisch.android.taskerm.n.f(this.f8569a);
    }

    public final Integer c() {
        Integer numB = b();
        if (numB != null) {
            return Integer.valueOf(net.dinglisch.android.taskerm.n.v(numB.intValue()));
        }
        return null;
    }

    public final boolean d() {
        return c.a.d.b(com.joaomgcd.taskerm.action.d.c(), Integer.valueOf(this.f8569a));
    }

    public final String e() {
        String strT = net.dinglisch.android.taskerm.n.t(this.f8569a);
        if (c.a.d.b(com.joaomgcd.taskerm.action.d.c(), Integer.valueOf(this.f8569a))) {
            String str = this.f8570b;
            if (str != null) {
                String str2 = str + " (" + strT + ')';
                if (str2 != null) {
                    return str2;
                }
            }
            c.f.b.k.a((Object) strT, "actionName");
            return strT;
        }
        c.f.b.k.a((Object) strT, "actionName");
        return strT;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!c.f.b.k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            a aVar = (a) obj;
            if (this.f8569a != aVar.f8569a) {
                return false;
            }
            return (d() && (c.f.b.k.a((Object) this.f8570b, (Object) aVar.f8570b) ^ true)) ? false : true;
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.settings.FavoriteAction");
    }

    public int hashCode() {
        int i = this.f8569a;
        if (!d()) {
            return i;
        }
        int i2 = i * 31;
        String str = this.f8570b;
        return i2 + (str != null ? str.hashCode() : 0);
    }
}
