package com.joaomgcd.taskerm.action.display;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class g extends com.joaomgcd.taskerm.helper.actions.execute.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5109a;

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5110a = new a();

        a() {
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            return Boolean.valueOf(a((String) obj));
        }

        public final boolean a(String str) {
            c.f.b.k.b(str, "it");
            return c.l.n.b((CharSequence) str, (CharSequence) "immersive", false, 2, (Object) null);
        }
    }

    public g() {
        super(new net.dinglisch.android.taskerm.m(906, R.string.an_immersive_mode, 40, 4, "immersive_mode", 0, Integer.valueOf(R.string.pl_mode), "", 0, 1));
        this.f5109a = 5112;
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.a, com.joaomgcd.taskerm.f.d
    public com.joaomgcd.taskerm.action.setting.e<com.joaomgcd.taskerm.helper.actions.execute.w> a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new r(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public y a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new y(executeService, cVar, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        return Integer.valueOf(R.array.immersive_mode_sources);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, com.joaomgcd.taskerm.helper.actions.execute.w wVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.E();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5109a);
    }

    private final b.a.l<Boolean> a(Context context) {
        return com.joaomgcd.taskerm.securesettings.aa.b(context, new com.joaomgcd.taskerm.securesettings.f(com.joaomgcd.taskerm.securesettings.p.Global, "policy_control", false, 0, 0, 28, null)).c(a.f5110a);
    }

    private final String a(String str, Context context) {
        if (!c.f.b.k.a((Object) str, (Object) "4")) {
            if (!c.f.b.k.a((Object) str, (Object) "0")) {
                h.b(context, str);
            }
            return str;
        }
        Boolean boolB = a(context).b();
        c.f.b.k.a((Object) boolB, "isEnabled");
        if (boolB.booleanValue()) {
            return "0";
        }
        String strB = h.b(context);
        return strB != null ? strB : "3";
    }

    private final String b(String str, Context context) {
        String str2;
        StringBuilder sb = new StringBuilder();
        String strA = a(str, context);
        if (strA.hashCode() == 48 && strA.equals("0")) {
            str2 = "null";
        } else {
            str2 = c(str, context) + '=';
        }
        sb.append(str2);
        sb.append('*');
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003b, code lost:
    
        if (r4.equals("0") != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
    
        r3 = "full";
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
    
        if (r4.equals("3") != false) goto L16;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String c(java.lang.String r3, android.content.Context r4) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "immersive."
            r0.append(r1)
            java.lang.String r4 = r2.a(r3, r4)
            int r1 = r4.hashCode()
            switch(r1) {
                case 48: goto L35;
                case 49: goto L2a;
                case 50: goto L1f;
                case 51: goto L16;
                default: goto L15;
            }
        L15:
            goto L47
        L16:
            java.lang.String r1 = "3"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L47
            goto L3d
        L1f:
            java.lang.String r1 = "2"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L47
            java.lang.String r3 = "navigation"
            goto L3f
        L2a:
            java.lang.String r1 = "1"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L47
            java.lang.String r3 = "status"
            goto L3f
        L35:
            java.lang.String r1 = "0"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L47
        L3d:
            java.lang.String r3 = "full"
        L3f:
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            return r3
        L47:
            com.joaomgcd.taskerm.securesettings.c r4 = new com.joaomgcd.taskerm.securesettings.c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Could not set immersive mode to "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r4.<init>(r3)
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.joaomgcd.taskerm.action.display.g.c(java.lang.String, android.content.Context):java.lang.String");
    }

    @Override // com.joaomgcd.taskerm.action.setting.a
    public com.joaomgcd.taskerm.securesettings.r a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "value");
        return new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Global, "policy_control", b(str, context), false, 0, 0, 48, null);
    }
}
