package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.dr;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public final class d extends b<c.s> {
    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ d(Activity activity, c.f.a.a aVar, Integer num, Integer num2, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, com.joaomgcd.taskerm.util.h hVar4, com.joaomgcd.taskerm.util.h hVar5, Boolean bool, Integer num3, int i, c.f.b.g gVar) {
        Integer num4 = (i & 4) != 0 ? (Integer) null : num;
        this(activity, aVar, num4, (i & 8) != 0 ? num4 : num2, (i & 16) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar, (i & 32) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar2, (i & 64) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar3, (i & 128) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar4, (i & 256) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar5, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Boolean) null : bool, (i & 1024) != 0 ? Integer.valueOf(gn.c(activity)) : num3);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.d$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.helper.k, b.a.l<View>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f6299a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Integer f6300b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Integer f6301c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Activity f6302d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.util.h f6303e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ Boolean f6304f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(c.f.a.a aVar, Integer num, Integer num2, Activity activity, com.joaomgcd.taskerm.util.h hVar, Boolean bool) {
            super(1);
            this.f6299a = aVar;
            this.f6300b = num;
            this.f6301c = num2;
            this.f6302d = activity;
            this.f6303e = hVar;
            this.f6304f = bool;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<View> invoke(final com.joaomgcd.taskerm.helper.k kVar) {
            c.f.b.k.b(kVar, "helper");
            b.a.l<View> lVarC = ((b.a.l) this.f6299a.invoke()).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.dialog.d.1.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final Bitmap apply(Bitmap bitmap) {
                    c.f.b.k.b(bitmap, "it");
                    return (AnonymousClass1.this.f6300b == null || AnonymousClass1.this.f6301c == null) ? bitmap : com.joaomgcd.taskerm.m.b.a(bitmap, AnonymousClass1.this.f6300b.intValue(), AnonymousClass1.this.f6301c.intValue());
                }
            }).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.dialog.d.1.2
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final View apply(Bitmap bitmap) {
                    c.f.b.k.b(bitmap, "bitmap");
                    ImageView imageView = new ImageView(AnonymousClass1.this.f6302d);
                    imageView.setImageBitmap(bitmap);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight(), 1.0f);
                    layoutParams.gravity = 17;
                    imageView.setLayoutParams(layoutParams);
                    int iA = dr.a((Context) AnonymousClass1.this.f6302d, 16);
                    imageView.setPadding(iA, iA, iA, iA);
                    if (AnonymousClass1.this.f6303e == null) {
                        return imageView;
                    }
                    LinearLayout linearLayout = new LinearLayout(AnonymousClass1.this.f6302d);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    linearLayout.setOrientation(1);
                    ScrollView scrollView = new ScrollView(AnonymousClass1.this.f6302d);
                    int iB = (dr.b(AnonymousClass1.this.f6302d) - bitmap.getHeight()) - dr.a((Context) AnonymousClass1.this.f6302d, 200);
                    scrollView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                    com.joaomgcd.taskerm.helper.k kVar2 = kVar;
                    Activity activity = AnonymousClass1.this.f6302d;
                    ScrollView scrollView2 = scrollView;
                    String strA = AnonymousClass1.this.f6303e.a();
                    Boolean bool = AnonymousClass1.this.f6304f;
                    kVar2.a(activity, scrollView2, strA, bool != null ? bool.booleanValue() : false);
                    scrollView.measure(0, 0);
                    if (scrollView.getMeasuredHeight() > iB) {
                        scrollView.setLayoutParams(new LinearLayout.LayoutParams(-1, iB));
                    }
                    linearLayout.addView(imageView);
                    linearLayout.addView(scrollView);
                    return linearLayout;
                }
            });
            c.f.b.k.a((Object) lVarC, "image().map { if (width …   layout\n        }\n    }");
            return lVarC;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(Activity activity, c.f.a.a<? extends b.a.l<Bitmap>> aVar, Integer num, Integer num2, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, com.joaomgcd.taskerm.util.h hVar4, com.joaomgcd.taskerm.util.h hVar5, Boolean bool, Integer num3) {
        super(activity, new AnonymousClass1(aVar, num, num2, activity, hVar2, bool), AnonymousClass2.f6308a, hVar, hVar3, hVar4, hVar5, bool, num3);
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(aVar, "image");
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.d$2, reason: invalid class name */
    static final class AnonymousClass2 extends c.f.b.l implements c.f.a.a<b.a.l<c.s>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass2 f6308a = new AnonymousClass2();

        AnonymousClass2() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<c.s> invoke() {
            b.a.l<c.s> lVarA = b.a.l.a(c.s.f2131a);
            c.f.b.k.a((Object) lVarA, "Single.just(Unit)");
            return lVarA;
        }
    }
}
