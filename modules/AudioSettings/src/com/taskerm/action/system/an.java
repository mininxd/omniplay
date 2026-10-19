package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class an {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5763a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f5764b;

    /* JADX WARN: Multi-variable type inference failed */
    public an() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void providerPath$annotations() {
    }

    public an(String str, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5763a = str;
        this.f5764b = jVar;
    }

    public /* synthetic */ an(String str, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getProviderPath() {
        return this.f5763a;
    }

    public final void setProviderPath(String str) {
        this.f5763a = str;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f5764b;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5764b = jVar;
    }

    public final Uri getUri() {
        Uri uriC = com.joaomgcd.taskerm.util.ak.C("content://" + this.f5763a);
        if (uriC != null) {
            return uriC;
        }
        throw new RuntimeException("Invalid provider path");
    }

    static final class a extends c.f.b.l implements c.f.a.a<String[]> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f5766b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context) {
            super(0);
            this.f5766b = context;
        }

        @Override // c.f.a.a
        public final String[] invoke() {
            return com.joaomgcd.taskerm.util.ah.b(this.f5766b, an.this.getUri());
        }
    }

    public final ProviderInfo getProvider(Context context) {
        c.f.b.k.b(context, "context");
        String str = this.f5763a;
        Object obj = null;
        if (str == null) {
            return null;
        }
        List<ProviderInfo> listQueryContentProviders = context.getPackageManager().queryContentProviders((String) null, 0, 0);
        c.f.b.k.a((Object) listQueryContentProviders, "context.packageManager.q…tentProviders(null, 0, 0)");
        for (Object obj2 : listQueryContentProviders) {
            if (c.f.b.k.a((Object) ((ProviderInfo) obj2).authority, (Object) str)) {
                obj = obj2;
                break;
            }
        }
        return (ProviderInfo) obj;
    }

    public final String[] getColumns(Context context) {
        c.f.b.k.b(context, "context");
        String[] strArr = (String[]) com.joaomgcd.taskerm.util.aj.a((c.f.a.b) null, new a(context), 1, (Object) null);
        return strArr != null ? strArr : new String[0];
    }
}
