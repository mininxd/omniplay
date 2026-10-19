package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import android.database.ContentObserver;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
final class i extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f8500a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f8501b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f8502c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final b.a.k.b<h> f8503d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f8504e;

    @Override // android.database.ContentObserver
    public boolean deliverSelfNotifications() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Context context, f fVar, int i, b.a.k.b<h> bVar, String str) {
        super(com.joaomgcd.taskerm.rx.i.f8398c);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(fVar, "secureSetting");
        c.f.b.k.b(bVar, "publishSubject");
        c.f.b.k.b(str, "originalName");
        this.f8500a = context;
        this.f8501b = fVar;
        this.f8502c = i;
        this.f8503d = bVar;
        this.f8504e = str;
    }

    public final f a() {
        return this.f8501b;
    }

    public final int b() {
        return this.f8502c;
    }

    public final b.a.k.b<h> c() {
        return this.f8503d;
    }

    public final String d() {
        return this.f8504e;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        a(false);
    }

    static final class a<T> implements b.a.d.f<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f8506b;

        a(boolean z) {
            this.f8506b = z;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            bn.b("CustomSettingListener", "Got event for setting " + i.this.a().b() + ':' + i.this.a().c() + ':' + str + " for profile " + i.this.b() + " (refresh only: " + this.f8506b + ')');
            i.this.c().b_(new h(new r(i.this.a().b(), i.this.a().c(), str, i.this.a().d(), 0, 0, 48, null), i.this.b(), this.f8506b));
        }
    }

    public final void a(boolean z) {
        aa.b(this.f8500a, this.f8501b).a(new a(z), b.f8507a);
    }

    static final class b<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8507a = new b();

        b() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            bn.b("CustomSettingListener", th.getMessage(), th);
        }
    }
}
