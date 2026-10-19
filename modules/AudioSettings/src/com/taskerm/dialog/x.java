package com.joaomgcd.taskerm.dialog;

import android.os.Bundle;
import android.os.Message;
import net.dinglisch.android.taskerm.cc;

/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Integer f6403a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Bundle f6404b;

    /* JADX WARN: Multi-variable type inference failed */
    public x() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public x(Integer num) {
        this(num, null, 2, 0 == true ? 1 : 0);
    }

    protected int a() {
        return 0;
    }

    protected int b() {
        return 1;
    }

    protected int c() {
        return 2;
    }

    public x(Integer num, Bundle bundle) {
        this.f6403a = num;
        this.f6404b = bundle;
    }

    public /* synthetic */ x(Integer num, Bundle bundle, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Bundle) null : bundle);
    }

    public final Integer l() {
        return this.f6403a;
    }

    public final Bundle m() {
        return this.f6404b;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public x(Message message) {
        this(Integer.valueOf(message.what), message.getData());
        c.f.b.k.b(message, "message");
    }

    public final boolean d() {
        Integer num = this.f6403a;
        return num != null && num.intValue() == a();
    }

    public final boolean e() {
        return com.joaomgcd.taskerm.util.aj.a(this.f6403a, Integer.valueOf(b()));
    }

    public final boolean f() {
        return com.joaomgcd.taskerm.util.aj.a(this.f6403a, 99);
    }

    public final boolean g() {
        Integer num = this.f6403a;
        return num != null && num.intValue() == c();
    }

    public final boolean h() {
        return e() || f();
    }

    public final boolean i() {
        return e() || f() || g();
    }

    public final String j() {
        Bundle bundle = this.f6404b;
        if (bundle != null) {
            return bundle.getString("text");
        }
        return null;
    }

    public final String k() {
        Bundle bundle = this.f6404b;
        if (bundle != null) {
            return cc.a(bundle.getInt("colour"));
        }
        return null;
    }

    public static /* synthetic */ Object a(x xVar, String str, c.f.a.b bVar, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getResultOrThrow");
        }
        if ((i & 1) != 0) {
            str = "Not selected";
        }
        return xVar.a(str, bVar);
    }

    public final <T> T a(String str, c.f.a.b<? super x, ? extends T> bVar) {
        c.f.b.k.b(str, "error");
        c.f.b.k.b(bVar, "getter");
        if (d()) {
            return bVar.invoke(this);
        }
        throw new RuntimeException(str);
    }
}
