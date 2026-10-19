package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.DeadObjectException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class ai {

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Throwable f8921a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Throwable th) {
            super(0);
            this.f8921a = th;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            StringWriter stringWriter = new StringWriter();
            this.f8921a.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.toString();
        }
    }

    public static final String a(Throwable th) {
        c.f.b.k.b(th, "receiver$0");
        String str = (String) aj.a((c.f.a.b) null, new a(th), 1, (Object) null);
        if (str == null) {
            str = (String) aj.a((c.f.a.b) null, new b(th), 1, (Object) null);
        }
        return str != null ? str : "Unknown Error";
    }

    static final class b extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Throwable f8922a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Throwable th) {
            super(0);
            this.f8922a = th;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return this.f8922a.getMessage();
        }
    }

    public static final dg a(Throwable th, Context context, String str) {
        c.f.b.k.b(th, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "extraText");
        return new dg(ah.a(context) + " - " + th, "This happened when I:\nI can always reproduce it: yes/no\n\n" + ah.y(context) + "\n\n" + a(th) + "\n\n" + str + '\n');
    }

    @TargetApi(24)
    public static final void b(Throwable th, Context context, String str) {
        boolean z;
        c.f.b.k.b(th, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "extraText");
        if (ah.j(context)) {
            return;
        }
        if (f.f9323b.t()) {
            List<Throwable> listB = b(th);
            if (!(listB instanceof Collection) || !listB.isEmpty()) {
                Iterator<T> it = listB.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (((Throwable) it.next()) instanceof DeadObjectException) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
                break;
            }
            if (z) {
                return;
            }
        }
        dg dgVarA = a(th, context, str);
        String str2 = null;
        String str3 = null;
        new com.joaomgcd.taskerm.notification.at(context, ah.a(context) + " Error", "Please click to report error to developer", str2, str3, th.getMessage(), false, new com.joaomgcd.taskerm.notification.as(R.drawable.mw_action_bug_report), null, null, null, 0, 0L, new com.joaomgcd.taskerm.notification.m(context, dgVarA.a(), dgVarA.b()), false, false, new com.joaomgcd.taskerm.notification.ai(new com.joaomgcd.taskerm.notification.c(context, new NotificationActionUploadTitleAndTextToGoogleDrive(dgVarA.b(), "crashreport.txt", dgVarA.a()), context.getString(R.string.send_report_to_google_drive), (com.joaomgcd.taskerm.notification.ap) null, 8, (c.f.b.g) null)), null, null, new com.joaomgcd.taskerm.notification.ak("bugreport", "Bug Reports", "Notifications for when an unexpected error occurred in Tasker and the user should report it to the developer.", 4, -65536, null, false, null, false, null, null, 2016, null), null, false, false, 7790424, null).c().b();
    }

    static final class c extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Throwable f8923a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f8924b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Throwable th, Context context) {
            super(0);
            this.f8923a = th;
            this.f8924b = context;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            String message;
            Throwable th = this.f8923a;
            if (th == null || (message = th.getMessage()) == null) {
                return;
            }
            gr.e(this.f8924b, message, new Object[0]);
        }
    }

    public static final void a(Throwable th, Context context) {
        c.f.b.k.b(context, "context");
        com.joaomgcd.taskerm.rx.i.e(new c(th, context));
    }

    public static final List<Throwable> b(Throwable th) {
        ArrayList arrayList = new ArrayList();
        while (th != null) {
            arrayList.add(th);
            Throwable cause = th.getCause();
            if (c.f.b.k.a(cause, th)) {
                break;
            }
            th = cause;
        }
        return arrayList;
    }

    public static final String c(Throwable th) {
        Object next;
        String message;
        Iterator<T> it = b(th).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!ak.z(((Throwable) next).getMessage()));
        Throwable th2 = (Throwable) next;
        if (th2 != null && (message = th2.getMessage()) != null) {
            return message;
        }
        if (th != null) {
            return th.getMessage();
        }
        return null;
    }

    public static final b.a.l<Boolean> a(SecurityException securityException) {
        c.f.b.k.b(securityException, "receiver$0");
        String message = securityException.getMessage();
        if (aj.c(message != null ? Boolean.valueOf(c.l.n.b((CharSequence) message, (CharSequence) "WRITE_SECURE_SETTINGS", false, 2, (Object) null)) : null)) {
            b.a.l<Boolean> lVarB = b.a.l.b(new com.joaomgcd.taskerm.securesettings.a("This is a Secure Setting which needs special permissions. Look in the help page for this action for info on how to enable."));
            c.f.b.k.a((Object) lVarB, "Single.error(ExceptionSe…info on how to enable.\"))");
            return lVarB;
        }
        b.a.l<Boolean> lVarB2 = b.a.l.b(new com.joaomgcd.taskerm.securesettings.c("Can't change setting on this device. Look in the help page for this action for info on how to enable."));
        c.f.b.k.a((Object) lVarB2, "Single.error(ExceptionSe…info on how to enable.\"))");
        return lVarB2;
    }

    public static final String a(com.google.android.gms.common.api.b bVar) {
        c.f.b.k.b(bVar, "receiver$0");
        switch (bVar.b()) {
            case 4:
                return "Sign in required.";
            case 5:
            case 9:
            case 11:
            case 12:
            default:
                return "Unknown Google Error (code " + bVar.b() + ')';
            case 6:
                return "Error resolution required. Check notifications.";
            case 7:
                return "Network error. Check if you have a valid connection.";
            case 8:
                return "An internal error occurred. Please try again.";
            case 10:
                return "The application is misconfigured. Please contact developer.";
            case 13:
                return "The operation failed with no more detailed information.";
            case 14:
                return "Call was interrupted. Please try again.";
            case 15:
                return "Time out";
            case 16:
                return "The result was canceled";
            case 17:
                return "API Not Connected";
        }
    }

    public static final boolean d(Throwable th) {
        c.f.b.k.b(th, "receiver$0");
        List<Throwable> listB = b(th);
        if ((listB instanceof Collection) && listB.isEmpty()) {
            return false;
        }
        Iterator<T> it = listB.iterator();
        while (it.hasNext()) {
            if (((Throwable) it.next()) instanceof TimeoutException) {
                return true;
            }
        }
        return false;
    }
}
