package com.joaomgcd.taskerm.floatingview;

import android.app.Activity;
import android.content.Context;
import b.a.d.f;
import c.a.d;
import c.a.j;
import c.f.b.g;
import c.f.b.k;
import c.f.b.l;
import c.f.b.w;
import c.s;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6892a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f6893b;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.floatingview.a$a, reason: collision with other inner class name */
    static final class C0149a extends l implements c.f.a.a<List<? extends com.joaomgcd.taskerm.p.a.b>> {
        C0149a() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.floatingview.a$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ w.a f6895a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(w.a aVar) {
                super(0);
                this.f6895a = aVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                this.f6895a.f2025a = true;
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.floatingview.a$a$2, reason: invalid class name */
        static final class AnonymousClass2 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ w.a f6896a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(w.a aVar) {
                super(0);
                this.f6896a = aVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                this.f6896a.f2025a = false;
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<com.joaomgcd.taskerm.p.a.b> invoke() {
            boolean z;
            Context contextB = a.this.b();
            String[] strArr = (String[]) d.a((Object[]) bt.f9101c.D(), (Object[]) bt.f9101c.J());
            bt btVar = new bt(contextB, 0, (String[]) Arrays.copyOf(strArr, strArr.length), 2, (g) null);
            if (!bt.f9101c.a(a.this.b(), btVar).b().b()) {
                String str = "Still missing permissions: " + btVar.l();
                if (a.this.b() instanceof Activity) {
                    y.c((Activity) a.this.b(), R.string.en_logcat_entry, str).b();
                } else {
                    ak.c(str, a.this.b());
                }
                return j.a();
            }
            if (a.this.b() instanceof Activity) {
                x xVar = (x) y.a((Activity) a.this.b(), R.string.en_logcat_entry, R.string.dc_logcat_helper_starting, R.string.word_volume, false, (String) null, 48, (Object) null).b();
                if (!xVar.g()) {
                    z = false;
                } else {
                    if (!((x) y.a((Activity) a.this.b(), R.string.en_logcat_entry, R.string.dc_logcat_helper_starting_volume, 0, 8, (Object) null).b()).d()) {
                        return j.a();
                    }
                    z = true;
                }
                if (xVar.h()) {
                    return j.a();
                }
                ah.T(a.this.b());
            } else {
                z = false;
            }
            w.a aVar = new w.a();
            aVar.f2025a = false;
            ArrayList arrayList = new ArrayList();
            b.a.b.b bVarA = com.joaomgcd.taskerm.p.a.f8124a.a(new com.joaomgcd.taskerm.p.a.C0210a(null, null, null, null, 15, null)).a(new C0150a(aVar, arrayList), new b());
            new BubbleStartStop(a.this.b(), "logging", z).a(new AnonymousClass1(aVar), new AnonymousClass2(aVar)).b();
            bVarA.b();
            return j.l(arrayList);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.floatingview.a$a$a, reason: collision with other inner class name */
        static final class C0150a<T> implements f<com.joaomgcd.taskerm.p.a.b> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ w.a f6897a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ArrayList f6898b;

            C0150a(w.a aVar, ArrayList arrayList) {
                this.f6897a = aVar;
                this.f6898b = arrayList;
            }

            @Override // b.a.d.f
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final void accept(com.joaomgcd.taskerm.p.a.b bVar) {
                k.b(bVar, "it");
                if (this.f6897a.f2025a) {
                    this.f6898b.add(bVar);
                }
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.floatingview.a$a$b */
        static final class b<T> implements f<Throwable> {
            b() {
            }

            @Override // b.a.d.f
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final void accept(Throwable th) {
                k.b(th, "it");
                i.a(a.this.b(), th);
            }
        }
    }

    public a(Context context, boolean z) {
        k.b(context, "context");
        this.f6892a = context;
        this.f6893b = z;
    }

    public final Context b() {
        return this.f6892a;
    }

    public final b.a.l<List<com.joaomgcd.taskerm.p.a.b>> a() {
        return i.b(new C0149a());
    }
}
