package com.joaomgcd.taskerm.s;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import b.a.d.g;
import b.a.k.d;
import b.a.l;
import c.f.b.k;
import com.google.android.play.core.d.e;
import com.google.android.play.core.review.ReviewInfo;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static final l<ReviewInfo> a(com.google.android.play.core.review.c cVar) {
        d dVarF = d.f();
        k.a((Object) dVarF, "SingleSubject.create<ReviewInfo>()");
        cVar.a().a(new c(dVarF));
        return dVarF;
    }

    static final class c<ResultT> implements com.google.android.play.core.d.a<ReviewInfo> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f8458a;

        c(d dVar) {
            this.f8458a = dVar;
        }

        @Override // com.google.android.play.core.d.a
        public final void a(e<ReviewInfo> eVar) {
            k.a((Object) eVar, "task");
            if (eVar.b()) {
                this.f8458a.d_(eVar.c());
            } else {
                this.f8458a.a_(eVar.d());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final b.a.b b(com.google.android.play.core.review.c cVar, Activity activity, ReviewInfo reviewInfo) {
        b.a.k.a aVarG = b.a.k.a.g();
        cVar.a(activity, reviewInfo).a(new b(aVarG));
        k.a((Object) aVarG, "completable");
        return aVarG;
    }

    static final class b<ResultT> implements com.google.android.play.core.d.a<Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.a f8457a;

        b(b.a.k.a aVar) {
            this.f8457a = aVar;
        }

        @Override // com.google.android.play.core.d.a
        public final void a(e<Void> eVar) {
            this.f8457a.q_();
        }
    }

    public static final boolean a(Context context) {
        k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.a(context, "hasaskedreview", false, sharedPreferencesA);
    }

    public static final void a(Context context, boolean z) {
        k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "hasaskedreview", boolValueOf, sharedPreferencesA);
    }

    public static final b.a.b a(Activity activity) {
        k.b(activity, "receiver$0");
        com.google.android.play.core.review.c cVarA = com.google.android.play.core.review.d.a(activity);
        k.a((Object) cVarA, "manager");
        b.a.b bVarB = a(cVarA).b(new C0228a(activity, cVarA));
        k.a((Object) bVarB, "manager.requestReviewRx(…hReviewRx(this, it)\n    }");
        return bVarB;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.s.a$a, reason: collision with other inner class name */
    static final class C0228a<T, R> implements g<ReviewInfo, b.a.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f8455a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.play.core.review.c f8456b;

        C0228a(Activity activity, com.google.android.play.core.review.c cVar) {
            this.f8455a = activity;
            this.f8456b = cVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.b apply(ReviewInfo reviewInfo) {
            k.b(reviewInfo, "it");
            a.a(this.f8455a, true);
            com.google.android.play.core.review.c cVar = this.f8456b;
            k.a((Object) cVar, "manager");
            return a.b(cVar, this.f8455a, reviewInfo);
        }
    }
}
