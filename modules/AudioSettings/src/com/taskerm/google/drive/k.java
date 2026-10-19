package com.joaomgcd.taskerm.google.drive;

import android.content.Context;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import com.joaomgcd.taskerm.notification.ak;
import com.joaomgcd.taskerm.notification.as;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.retrofit.ErrorTaskerServer;
import com.joaomgcd.taskerm.util.bx;
import com.joaomgcd.taskerm.util.cq;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String b() {
        return "GoogleDrive";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final c.s b(Context context, String str) {
        if (str != null) {
            return b(context, "Error", false, str, new bx(100L, 100L, false, null, 12, null)).d();
        }
        return null;
    }

    static /* synthetic */ b.a.l a(b.a.l lVar, Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return b(lVar, context, str);
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f7057a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7058b;

        a(Context context, String str) {
            this.f7057a = context;
            this.f7058b = str;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cq<TResult, ErrorPayloadGoogleDrive> apply(TResult tresult) {
            k.b(this.f7057a, this.f7058b);
            return new cq<>(true, tresult, null, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <TResult> b.a.l<cq<TResult, ErrorPayloadGoogleDrive>> b(b.a.l<TResult> lVar, Context context, String str) {
        b.a.l<cq<TResult, ErrorPayloadGoogleDrive>> lVarD = lVar.c(new a(context, str)).d(new b(context, str));
        c.f.b.k.a((Object) lVarD, "map {\n            cancel…     })\n                }");
        return lVarD;
    }

    /* JADX INFO: Add missing generic type declarations: [TResult] */
    static final class b<T, R, TResult> implements b.a.d.g<Throwable, b.a.p<? extends cq<TResult, ErrorPayloadGoogleDrive>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f7059a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7060b;

        b(Context context, String str) {
            this.f7059a = context;
            this.f7060b = str;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<cq<TResult, ErrorPayloadGoogleDrive>> apply(Throwable th) {
            cq cqVar;
            c.f.b.k.b(th, "it");
            k.b(this.f7059a, this.f7060b);
            if (!(th instanceof com.joaomgcd.taskerm.net.i)) {
                if (th instanceof com.joaomgcd.taskerm.retrofit.g) {
                    com.joaomgcd.taskerm.retrofit.g gVar = (com.joaomgcd.taskerm.retrofit.g) th;
                    cqVar = new cq(false, null, new ErrorPayloadGoogleDrive(new ErrorTaskerServer.Error(null, gVar.a(), gVar.b())));
                } else {
                    cqVar = new cq(false, null, new ErrorPayloadGoogleDrive(new ErrorTaskerServer.Error(null, -1, th.getMessage())));
                }
            } else {
                Object objA = ((com.joaomgcd.taskerm.net.i) th).a();
                if (objA != null) {
                    cqVar = new cq(false, null, (ErrorPayloadGoogleDrive) objA);
                } else {
                    throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive");
                }
            }
            return b.a.l.a(cqVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final at b(Context context, String str, boolean z, String str2, bx bxVar) {
        as asVar = new as(z ? R.drawable.hd_av_upload : R.drawable.hd_av_download);
        Integer numValueOf = Integer.valueOf((int) bxVar.b());
        Long lA = bxVar.a();
        return new at(context, "Tasker Google Drive", str, null, null, null, false, asVar, null, str2, null, -2, 0L, null, false, false, null, numValueOf, lA != null ? Integer.valueOf((int) lA.longValue()) : null, new ak(b(), "Google Drive", "Notifications that show download and upload progress from and to Google Drive", 1, null, null, false, null, false, null, null, 2032, null), null, false, false, 7468408, null);
    }

    static final class c<T1, T2> implements b.a.d.d<Integer, Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f7061a = new c();

        c() {
        }

        @Override // b.a.d.d
        public final boolean a(Integer num, Throwable th) {
            c.f.b.k.b(num, "count");
            c.f.b.k.b(th, "error");
            return c.f.b.k.a(num.intValue(), 1) > 0 ? false : false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <TResult> b.a.l<TResult> b(b.a.l<TResult> lVar) {
        b.a.l<TResult> lVarA = lVar.a(c.f7061a);
        c.f.b.k.a((Object) lVarA, "retry { count, error ->\n…\n    return@retry false\n}");
        return lVarA;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(String str, String str2, boolean z, boolean z2) {
        String str3 = "trashed = " + z2 + " and name = '" + c.l.n.a(str, "'", "\\'", false, 4, (Object) null) + '\'';
        if (z) {
            str3 = str3 + " and mimeType = 'application/vnd.google-apps.folder'";
        }
        if (z2) {
            return str3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        sb.append(" and '");
        if (str2 == null) {
            str2 = "root";
        }
        sb.append(str2);
        sb.append("' in parents");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(String str, q qVar) {
        String str2;
        String str3 = "trashed = false and '" + str + "' in parents";
        boolean z = qVar instanceof r;
        if (z) {
            return str3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        sb.append(" and mimeType ");
        if (qVar instanceof t) {
            str2 = "=";
        } else if (qVar instanceof s) {
            str2 = "!=";
        } else {
            if (!z) {
                throw new c.i();
            }
            str2 = "";
        }
        sb.append(str2);
        sb.append(" 'application/vnd.google-apps.folder'");
        return sb.toString();
    }
}
