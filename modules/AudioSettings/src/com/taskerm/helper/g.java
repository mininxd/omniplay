package com.joaomgcd.taskerm.helper;

import android.accessibilityservice.FingerprintGestureController;
import android.annotation.TargetApi;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ax;
import com.joaomgcd.taskerm.util.az;
import com.joaomgcd.taskerm.util.ba;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.MyAccessibilityService;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class g extends u<MyAccessibilityService> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final az f7253b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ax<b.a.k.b<a>> f7254c;

    static final class b extends c.f.b.l implements c.f.a.a<b.a.k.b<a>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MyAccessibilityService f7261b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(MyAccessibilityService myAccessibilityService) {
            super(0);
            this.f7261b = myAccessibilityService;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.g$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<a, Integer> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f7262a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ Integer invoke(a aVar) {
                return Integer.valueOf(a(aVar));
            }

            public final int a(a aVar) {
                return aVar.b();
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<a> invoke() {
            b.a.k.b<a> bVarJ = b.a.k.b.j();
            g gVar = g.this;
            c.f.b.k.a((Object) bVarJ, "publishSubject");
            b.a.h hVarA = com.joaomgcd.taskerm.rx.i.a(bVarJ, 300, TimeUnit.MILLISECONDS, AnonymousClass1.f7262a);
            c.f.b.k.a((Object) hVarA, "publishSubject.groupedFi…SECONDS) { it.eventType }");
            b.a.b.b bVarD = com.joaomgcd.taskerm.rx.i.c(hVarA, g.this.a()).d(new b.a.d.f<a>() { // from class: com.joaomgcd.taskerm.helper.g.b.2
                @Override // b.a.d.f
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final void accept(a aVar) {
                    c.f.b.k.b(aVar, "e");
                    u.a(g.this, "winsource: " + aVar.c() + " / " + aVar.d(), (Exception) null, 2, (Object) null);
                    b.this.f7261b.sendOrderedBroadcast(aVar.a(), null);
                }
            });
            c.f.b.k.a((Object) bVarD, "publishSubject.groupedFi…e.intent, null)\n        }");
            gVar.a(bVarD);
            return bVarJ;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public g(MyAccessibilityService myAccessibilityService) {
        c.f.b.k.b(myAccessibilityService, NotificationCompat.CATEGORY_SERVICE);
        String str = MyAccessibilityService.f11165a;
        c.f.b.k.a((Object) str, "MyAccessibilityService.TAG");
        super(myAccessibilityService, str);
        this.f7253b = ba.a("Accessibility");
        this.f7254c = ba.a(new b(myAccessibilityService));
    }

    public final az a() {
        return this.f7253b;
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f7255a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final CharSequence f7256b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final CharSequence f7257c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String[] f7258d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Boolean f7259e;

        public final int b() {
            return this.f7255a;
        }

        public final CharSequence c() {
            return this.f7256b;
        }

        public final CharSequence d() {
            return this.f7257c;
        }

        public final Intent a() {
            if (this.f7255a != 32) {
                return null;
            }
            Intent intent = new Intent("net.dinglisch.android.tasker.NWINNY");
            intent.putExtra("strgns", this.f7258d);
            intent.putExtra("fscr", this.f7259e);
            intent.putExtra("pkg", this.f7256b);
            intent.putExtra("cls", this.f7257c);
            return intent;
        }
    }

    @Override // com.joaomgcd.taskerm.helper.u, com.joaomgcd.taskerm.helper.j
    public void b() {
        this.f7254c.a();
        this.f7253b.g();
        super.b();
    }

    public final void c() {
        d();
    }

    @TargetApi(26)
    public final void d() {
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            return;
        }
        u().getFingerprintGestureController().registerFingerprintGestureCallback(new c(), this.f7253b.d());
    }

    public static final class c extends FingerprintGestureController.FingerprintGestureCallback {
        c() {
        }

        @Override // android.accessibilityservice.FingerprintGestureController.FingerprintGestureCallback
        public void onGestureDetected(int i) {
            com.joaomgcd.taskerm.event.hardware.b.a(g.this.u(), i);
        }
    }

    public final void a(AccessibilityEvent accessibilityEvent) {
        c.f.b.k.b(accessibilityEvent, "e");
        bn.b(MyAccessibilityService.f11165a, accessibilityEvent.toString());
    }
}
