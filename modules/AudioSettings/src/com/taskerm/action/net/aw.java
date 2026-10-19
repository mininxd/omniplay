package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public final class aw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5591a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "timeoutMs", "getTimeoutMs()J"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5592b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private n f5593c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5594d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5595e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5596f;
    private String g;
    private String h;
    private Boolean i;
    private Integer j;
    private String k;
    private String l;
    private Class<HTTPAuthOutput> m;

    public aw() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void clientId$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void clientSecret$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void endpointCode$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void endpointRefresh$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void method$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 10)
    public static /* synthetic */ void password$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void reAuth$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void scopes$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void timeoutSeconds$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9)
    public static /* synthetic */ void userName$annotations() {
    }

    public final long getTimeoutMs() {
        c.e eVar = this.f5592b;
        c.j.g gVar = f5591a[0];
        return ((Number) eVar.b()).longValue();
    }

    static final class a extends c.f.b.l implements c.f.a.a<Long> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Long invoke() {
            return Long.valueOf(invoke2());
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final long invoke2() {
            Integer timeoutSeconds = aw.this.getTimeoutSeconds();
            return ((long) (timeoutSeconds != null ? timeoutSeconds.intValue() : 30)) * 1000;
        }
    }

    public aw(n nVar, String str, String str2, String str3, String str4, String str5, Boolean bool, Integer num, String str6, String str7, Class<HTTPAuthOutput> cls) {
        this.f5593c = nVar;
        this.f5594d = str;
        this.f5595e = str2;
        this.f5596f = str3;
        this.g = str4;
        this.h = str5;
        this.i = bool;
        this.j = num;
        this.k = str6;
        this.l = str7;
        this.m = cls;
        this.f5592b = c.f.a(new a());
    }

    public /* synthetic */ aw(n nVar, String str, String str2, String str3, String str4, String str5, Boolean bool, Integer num, String str6, String str7, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (n) null : nVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (String) null : str5, (i & 64) != 0 ? (Boolean) null : bool, (i & 128) != 0 ? (Integer) null : num, (i & 256) != 0 ? (String) null : str6, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (String) null : str7, (i & 1024) != 0 ? HTTPAuthOutput.class : cls);
    }

    public final n getMethod() {
        return this.f5593c;
    }

    public final void setMethod(n nVar) {
        this.f5593c = nVar;
    }

    public final String getClientId() {
        return this.f5594d;
    }

    public final void setClientId(String str) {
        this.f5594d = str;
    }

    public final String getClientSecret() {
        return this.f5595e;
    }

    public final void setClientSecret(String str) {
        this.f5595e = str;
    }

    public final String getEndpointCode() {
        return this.f5596f;
    }

    public final void setEndpointCode(String str) {
        this.f5596f = str;
    }

    public final String getEndpointRefresh() {
        return this.g;
    }

    public final void setEndpointRefresh(String str) {
        this.g = str;
    }

    public final String getScopes() {
        return this.h;
    }

    public final void setScopes(String str) {
        this.h = str;
    }

    public final Boolean getReAuth() {
        return this.i;
    }

    public final void setReAuth(Boolean bool) {
        this.i = bool;
    }

    public final Integer getTimeoutSeconds() {
        return this.j;
    }

    public final void setTimeoutSeconds(Integer num) {
        this.j = num;
    }

    public final String getUserName() {
        return this.k;
    }

    public final void setUserName(String str) {
        this.k = str;
    }

    public final String getPassword() {
        return this.l;
    }

    public final void setPassword(String str) {
        this.l = str;
    }

    public final Class<HTTPAuthOutput> getOutputClass() {
        return this.m;
    }

    public final void setOutputClass(Class<HTTPAuthOutput> cls) {
        this.m = cls;
    }

    public final com.joaomgcd.taskerm.net.auth.b getAuthenticator(Context context) {
        c.f.b.k.b(context, "context");
        n nVar = this.f5593c;
        if (nVar != null) {
            switch (nVar) {
                case OAuth2:
                    return getAuthenticatorOauth2(context);
                case UserPass:
                    return a(this);
                default:
                    throw new c.i();
            }
        }
        throw new RuntimeException("No auth method selected");
    }

    public final com.joaomgcd.taskerm.net.auth.d getAuthenticatorOauth2(Context context) {
        c.f.b.k.b(context, "context");
        String str = this.f5594d;
        if (str == null) {
            throw new RuntimeException("No client ID provided");
        }
        String str2 = this.f5595e;
        if (str2 == null) {
            throw new RuntimeException("No client Secret provided");
        }
        String str3 = this.f5596f;
        if (str3 == null) {
            throw new RuntimeException("No code endpoint provided");
        }
        String str4 = this.g;
        if (str4 == null) {
            throw new RuntimeException("No refresh endpoint provided");
        }
        String str5 = this.h;
        if (str5 == null) {
            str5 = "";
        }
        Object[] array = c.l.n.b((CharSequence) str5, new String[]{"\n"}, false, 0, 6, (Object) null).toArray(new String[0]);
        if (array != null) {
            String[] strArr = (String[]) array;
            Boolean bool = this.i;
            return new com.joaomgcd.taskerm.net.auth.d(context, str, str2, str3, str4, strArr, null, bool != null ? bool.booleanValue() : false, 64, null);
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final com.joaomgcd.taskerm.net.auth.g a(aw awVar) {
        String str = awVar.k;
        if (str == null) {
            throw new RuntimeException("No username provided");
        }
        String str2 = awVar.l;
        if (str2 == null) {
            throw new RuntimeException("No password provided");
        }
        return new com.joaomgcd.taskerm.net.auth.g(str, str2);
    }
}
