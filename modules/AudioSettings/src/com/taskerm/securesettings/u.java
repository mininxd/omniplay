package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import com.joaomgcd.taskerm.util.cf;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class u extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8559a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(u.class), "adb", "getAdb()Lcom/joaomgcd/taskerm/adblib/ADB;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f8560b;

    private final boolean f() {
        return true;
    }

    private final boolean g() {
        return true;
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.b.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8561a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context) {
            super(0);
            this.f8561a = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.b.a invoke() {
            return new com.joaomgcd.taskerm.b.a(this.f8561a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(Context context) {
        super(context);
        c.f.b.k.b(context, "context");
        this.f8560b = c.f.a(new a(context));
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8562a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cf apply(List<String> list) {
            c.f.b.k.b(list, "it");
            return new cf(1, new ArrayList(list), new ArrayList());
        }
    }

    @Override // com.joaomgcd.taskerm.securesettings.y
    public b.a.l<String> a(String str, long j) {
        c.f.b.k.b(str, "command");
        return com.joaomgcd.taskerm.b.a.b(new com.joaomgcd.taskerm.b.a(d()), str, (int) j, f(), 0, null, g(), 24, null);
    }

    @Override // com.joaomgcd.taskerm.securesettings.y
    public b.a.l<cf> b(String str, long j) {
        c.f.b.k.b(str, "command");
        return com.joaomgcd.taskerm.b.a.a(new com.joaomgcd.taskerm.b.a(d()), str, (int) j, f(), 0, null, g(), 24, null).c(b.f8562a);
    }
}
