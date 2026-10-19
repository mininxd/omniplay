package com.joaomgcd.taskerm.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public class ar<TMessage> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8989a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(ar.class), "scheduler", "getScheduler()Lio/reactivex/Scheduler;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ax<a<TMessage>> f8990b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f8991c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8992d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f8993e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.f.a.b<TMessage, c.s> f8994f;

    public final b.a.k d() {
        c.e eVar = this.f8992d;
        c.j.g gVar = f8989a[0];
        return (b.a.k) eVar.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ar(String str, c.f.a.b<? super TMessage, c.s> bVar) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        this.f8993e = str;
        this.f8994f = bVar;
        this.f8990b = ba.a(new c());
        this.f8992d = c.f.a(new b());
    }

    public /* synthetic */ ar(String str, c.f.a.b bVar, int i, c.f.b.g gVar) {
        this(str, (i & 2) != 0 ? (c.f.a.b) null : bVar);
    }

    public final c.f.a.b<TMessage, c.s> e() {
        return this.f8994f;
    }

    static final class c extends c.f.b.l implements c.f.a.a<a<TMessage>> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final a<TMessage> invoke() {
            return new a<>(ar.this.f(), ar.this.e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String f() {
        return "handler" + this.f8993e;
    }

    private static final class a<TMessage> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ c.j.g[] f8995a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "handlerThread", "getHandlerThread()Landroid/os/HandlerThread;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "handler", "getHandler()Landroid/os/Handler;"))};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.e f8996b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c.e f8997c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f8998d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final c.f.a.b<TMessage, c.s> f8999e;

        public final HandlerThread a() {
            c.e eVar = this.f8996b;
            c.j.g gVar = f8995a[0];
            return (HandlerThread) eVar.b();
        }

        public final Handler b() {
            c.e eVar = this.f8997c;
            c.j.g gVar = f8995a[1];
            return (Handler) eVar.b();
        }

        static final class b extends c.f.b.l implements c.f.a.a<HandlerThread> {
            b() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final HandlerThread invoke() {
                HandlerThread handlerThread = new HandlerThread(a.this.c(), 10);
                handlerThread.start();
                return handlerThread;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public a(String str, c.f.a.b<? super TMessage, c.s> bVar) {
            c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
            this.f8998d = str;
            this.f8999e = bVar;
            this.f8996b = c.f.a(new b());
            this.f8997c = c.f.a(new C0245a());
        }

        public final String c() {
            return this.f8998d;
        }

        public final c.f.a.b<TMessage, c.s> d() {
            return this.f8999e;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.ar$a$a, reason: collision with other inner class name */
        static final class C0245a extends c.f.b.l implements c.f.a.a<Handler> {
            C0245a() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Handler invoke() {
                final c.f.a.b<TMessage, c.s> bVarD = a.this.d();
                if (bVarD == null) {
                    return new Handler(a.this.a().getLooper());
                }
                return new Handler(a.this.a().getLooper()) { // from class: com.joaomgcd.taskerm.util.ar.a.a.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        c.f.b.k.b(message, NotificationCompat.CATEGORY_MESSAGE);
                        bVarD.invoke(message.obj);
                    }
                };
            }
        }
    }

    public final void a() {
        if (this.f8991c) {
            ax<a<TMessage>> axVar = this.f8990b;
            axVar.b().a().quitSafely();
            axVar.a();
        }
    }

    public final Handler b() {
        this.f8991c = true;
        return this.f8990b.b().b();
    }

    static final class b extends c.f.b.l implements c.f.a.a<b.a.k> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k invoke() {
            return b.a.a.b.a.a(ar.this.c());
        }
    }

    public final Looper c() {
        Looper looper = b().getLooper();
        c.f.b.k.a((Object) looper, "handler.looper");
        return looper;
    }
}
