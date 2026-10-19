package com.joaomgcd.taskerm.net.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import c.a.z;
import c.f.b.k;
import c.f.b.l;
import c.l.n;
import c.q;
import c.s;
import com.joaomgcd.taskerm.net.o;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class d implements com.joaomgcd.taskerm.net.auth.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f7858a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f7859b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f7860c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f7861d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f7862e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String[] f7863f;
    private final i g;
    private final boolean h;

    static final class c extends l implements c.f.a.b<HttpURLConnection, s> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(HttpURLConnection httpURLConnection) {
            a(httpURLConnection);
            return s.f2131a;
        }

        public final void a(HttpURLConnection httpURLConnection) {
            k.b(httpURLConnection, "it");
            String strA = d.this.a(d.this.f7859b, d.this.f7860c);
            k.a((Object) strA, "base64EncodeUserPass(clientId, clientSecret)");
            aj.a(httpURLConnection, strA);
            aj.b(httpURLConnection, d.this.g.a());
        }
    }

    public d(Context context, String str, String str2, String str3, String str4, String[] strArr, i iVar, boolean z) {
        k.b(context, "context");
        k.b(str, "clientId");
        k.b(str2, "clientSecret");
        k.b(str3, "endpointCode");
        k.b(str4, "endpointRefresh");
        k.b(strArr, "scopes");
        k.b(iVar, "tokenDataType");
        this.f7858a = context;
        this.f7859b = str;
        this.f7860c = str2;
        this.f7861d = str3;
        this.f7862e = str4;
        this.f7863f = strArr;
        this.g = iVar;
        this.h = z;
    }

    public String a(String str, String str2) {
        k.b(str, "user");
        k.b(str2, "pass");
        return com.joaomgcd.taskerm.net.auth.b.a.a(this, str, str2);
    }

    public String a(JSONObject jSONObject) {
        k.b(jSONObject, "receiver$0");
        return com.joaomgcd.taskerm.net.auth.b.a.a(this, jSONObject);
    }

    public Long b(JSONObject jSONObject) {
        k.b(jSONObject, "receiver$0");
        return com.joaomgcd.taskerm.net.auth.b.a.b(this, jSONObject);
    }

    public String c(JSONObject jSONObject) {
        k.b(jSONObject, "receiver$0");
        return com.joaomgcd.taskerm.net.auth.b.a.c(this, jSONObject);
    }

    public /* synthetic */ d(Context context, String str, String str2, String str3, String str4, String[] strArr, i iVar, boolean z, int i, c.f.b.g gVar) {
        this(context, str, str2, str3, str4, strArr, (i & 64) != 0 ? i.form : iVar, (i & 128) != 0 ? false : z);
    }

    private final String a() {
        return c.a.d.a(this.f7863f, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null);
    }

    private final String a(boolean z) {
        List<c.k> listD;
        Uri.Builder builderAppendQueryParameter = Uri.parse(this.f7861d).buildUpon().appendQueryParameter("client_id", this.f7859b).appendQueryParameter("response_type", "code");
        String string = UUID.randomUUID().toString();
        k.a((Object) string, "UUID.randomUUID().toString()");
        Uri.Builder builderAppendQueryParameter2 = builderAppendQueryParameter.appendQueryParameter("state", n.a(string, "-", "", false, 4, (Object) null));
        if (z) {
            builderAppendQueryParameter2.appendQueryParameter("redirect_uri", "https://tasker.joaoapps.com/auth.html");
        }
        if (!(this.f7863f.length == 0)) {
            builderAppendQueryParameter2.appendQueryParameter("scope", a());
        }
        HashMap map = (HashMap) f.b().get(this.f7861d);
        if (map != null && (listD = z.d(map)) != null) {
            for (c.k kVar : listD) {
                builderAppendQueryParameter2.appendQueryParameter((String) kVar.a(), (String) kVar.b());
            }
        }
        if (this.h) {
            builderAppendQueryParameter2.appendQueryParameter("show_dialog", "true");
        }
        String string2 = builderAppendQueryParameter2.build().toString();
        k.a((Object) string2, "Uri.parse(endpointCode).…      .build().toString()");
        return string2;
    }

    private final String b(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("grant_type", "authorization_code");
        jSONObject.put("code", str);
        if (str2 == null) {
            str2 = "https://tasker.joaoapps.com/auth.html";
        }
        jSONObject.put("redirect_uri", str2);
        jSONObject.put("client_id", this.f7859b);
        jSONObject.put("client_secret", this.f7860c);
        switch (this.g) {
            case json:
                String string = jSONObject.toString();
                k.a((Object) string, "fields.toString()");
                return string;
            case form:
                return aj.a(jSONObject);
            default:
                throw new c.i();
        }
    }

    private final String a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("grant_type", "refresh_token");
        jSONObject.put("refresh_token", str);
        switch (this.g) {
            case json:
                String string = jSONObject.toString();
                k.a((Object) string, "fields.toString()");
                return string;
            case form:
                return aj.a(jSONObject);
            default:
                throw new c.i();
        }
    }

    private final String b(String str) {
        com.joaomgcd.taskerm.net.d dVar = new com.joaomgcd.taskerm.net.d(str, this.f7862e, null, null, 12, null);
        dVar.a(new c());
        return (String) com.joaomgcd.taskerm.net.l.a(new o(dVar), null, 1, null);
    }

    public static final class b<T> implements b.a.d.i<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f7867a = new b();

        @Override // b.a.d.i
        public final boolean a(Object obj) {
            k.b(obj, "it");
            return obj instanceof com.joaomgcd.taskerm.net.auth.a;
        }
    }

    @Override // com.joaomgcd.taskerm.net.auth.b
    public b.a.l<h> a(long j) {
        return com.joaomgcd.taskerm.rx.i.b(new a(j));
    }

    static final class a extends l implements c.f.a.a<C0200a> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f7865b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(long j) {
            super(0);
            this.f7865b = j;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final C0200a invoke() {
            AuthInfo authInfoC = d.this.h ? null : d.this.c();
            if (authInfoC == null) {
                authInfoC = d.this.b(this.f7865b);
            } else if (authInfoC.isExpired()) {
                authInfoC = d.this.a(authInfoC, this.f7865b);
            }
            d.this.a(authInfoC);
            return new C0200a(authInfoC);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.net.auth.d$a$a, reason: collision with other inner class name */
        public static final class C0200a extends h {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AuthInfo f7866a;

            C0200a(AuthInfo authInfo) {
                this.f7866a = authInfo;
            }

            @Override // com.joaomgcd.taskerm.net.auth.h
            public void a(HashMap<String, String> map) {
                k.b(map, "headers");
                map.put(this.f7866a.getAuthHeaderKey(), this.f7866a.getAuthHeaderValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AuthInfo a(AuthInfo authInfo, long j) {
        try {
            String refreshToken = authInfo.getRefreshToken();
            if (refreshToken == null) {
                return authInfo;
            }
            JSONObject jSONObject = new JSONObject(b(a(refreshToken)));
            String strC = c(jSONObject);
            if (strC == null) {
                strC = refreshToken;
            }
            String strA = a(jSONObject);
            if (strA == null) {
                throw new RuntimeException("Could not get access token");
            }
            return new AuthInfo(strC, strA, b(jSONObject));
        } catch (com.joaomgcd.taskerm.net.i unused) {
            a((AuthInfo) null);
            return b(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AuthInfo b(long j) {
        String queryParameter;
        String str = aj.a(ak.C(this.f7861d)).get("redirect_uri");
        String str2 = str;
        ah.a(this.f7858a, a(str2 == null || str2.length() == 0), true, false, true);
        try {
            b.a.h<Object> hVarA = com.joaomgcd.taskerm.rx.b.f8378b.a().a((b.a.d.i<? super Object>) b.f7867a);
            if (hVarA != null) {
                b.a.l<Object> lVarD = hVarA.d();
                if (lVarD == null) {
                    throw new q("null cannot be cast to non-null type io.reactivex.Single<T>");
                }
                b.a.l<Object> lVarB = lVarD.b(j, TimeUnit.MILLISECONDS);
                k.a((Object) lVarB, "result.timeout(it, TimeUnit.MILLISECONDS)");
                Uri uriA = ((com.joaomgcd.taskerm.net.auth.a) lVarB.b()).a();
                if (uriA != null && (queryParameter = uriA.getQueryParameter("error")) != null) {
                    throw new RuntimeException("Error getting code: " + queryParameter);
                }
                String queryParameter2 = uriA != null ? uriA.getQueryParameter("code") : null;
                if (queryParameter2 == null) {
                    throw new RuntimeException("Could not get code");
                }
                JSONObject jSONObject = new JSONObject(b(b(queryParameter2, str)));
                String strC = c(jSONObject);
                String strA = a(jSONObject);
                if (strA == null) {
                    throw new RuntimeException("Could not get access token");
                }
                return new AuthInfo(strC, strA, b(jSONObject));
            }
            throw new q("null cannot be cast to non-null type io.reactivex.Observable<T>");
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception unused) {
            throw new RuntimeException("Timed out while getting code");
        }
    }

    private final String b() {
        return this.f7859b + ':' + this.f7860c + ':' + this.f7861d + ':' + this.f7862e + ':' + a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AuthInfo c() {
        Context context = this.f7858a;
        String strB = b();
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.net.auth.c.a(this.f7858a);
        k.a((Object) sharedPreferencesA, "context.preferencesHttpAuth");
        String string = sharedPreferencesA.getString(strB, null);
        return (AuthInfo) (string != null ? com.joaomgcd.taskerm.n.b.a().a(string, AuthInfo.class) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(AuthInfo authInfo) {
        Context context = this.f7858a;
        String strB = b();
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.net.auth.c.a(this.f7858a);
        k.a((Object) sharedPreferencesA, "context.preferencesHttpAuth");
        com.joaomgcd.taskerm.q.b.a(context, strB, authInfo, sharedPreferencesA);
    }
}
