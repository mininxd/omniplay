package com.joaomgcd.taskerm.net;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.dk;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import net.dinglisch.android.taskerm.bn;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes.dex */
public final class ab {
    public static final String a() {
        return HttpConnection.CONTENT_TYPE;
    }

    public static final String b() {
        return "User-Agent";
    }

    public static final String c() {
        return "Authorization";
    }

    private static final String d() {
        return "UtilNet";
    }

    private static final String f() {
        return "joaomgcdTaskerMOTHERFOCKERMUAHAHA";
    }

    private static final String e() {
        return "--" + f();
    }

    public static final c.k<String, String> a(String str) {
        c.f.b.k.b(str, "token");
        return new c.k<>(c(), "Bearer " + str);
    }

    public static final c.k<String, String> b(String str) {
        c.f.b.k.b(str, "token");
        return new c.k<>(c(), "Basic " + str);
    }

    public static final class c implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            c.f.b.k.b(x509CertificateArr, "chain");
            c.f.b.k.b(str, "authType");
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            c.f.b.k.b(x509CertificateArr, "chain");
            c.f.b.k.b(str, "authType");
        }

        c() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static final SSLSocketFactory g() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustManagerArr = {new c()};
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, trustManagerArr, new SecureRandom());
        c.f.b.k.a((Object) sSLContext, "sc");
        SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
        c.f.b.k.a((Object) socketFactory, "sc.socketFactory");
        return socketFactory;
    }

    public static final void a(SSLSocketFactory sSLSocketFactory) {
        if (sSLSocketFactory == null) {
            return;
        }
        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLSocketFactory);
        } catch (Exception e2) {
            bn.b(d(), "trustAllHosts", e2);
        }
    }

    public static final <T> T a(boolean z, c.f.a.a<? extends T> aVar) {
        c.f.b.k.b(aVar, "actionSync");
        SSLSocketFactory defaultSSLSocketFactory = z ? HttpsURLConnection.getDefaultSSLSocketFactory() : null;
        if (z) {
            try {
                a(g());
            } finally {
                if (defaultSSLSocketFactory != null) {
                    a(defaultSSLSocketFactory);
                }
            }
        }
        return aVar.invoke();
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7853a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.f7853a = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            c.l.g gVarC;
            c.l.f fVarA;
            c.l.i iVarA = c.l.k.a(new c.l.k("://([^:]+:[^/]+)@"), this.f7853a, 0, 2, null);
            if (iVarA == null || (gVarC = iVarA.c()) == null || (fVarA = gVarC.a(1)) == null) {
                return null;
            }
            return fVarA.a();
        }
    }

    public static final URL a(HashMap<String, String> map, URL url) {
        c.f.b.k.b(map, "receiver$0");
        c.f.b.k.b(url, "url");
        String string = url.toString();
        c.f.b.k.a((Object) string, "url.toString()");
        boolean z = true;
        String str = (String) aj.a((c.f.a.b) null, new a(string), 1, (Object) null);
        String str2 = str;
        if (str2 != null && str2.length() != 0) {
            z = false;
        }
        if (z) {
            return url;
        }
        map.put("Authorization", "Basic " + ak.n(str));
        return new URL(c.l.n.a(string, "://" + str + '@', "://", false, 4, (Object) null));
    }

    public static final InputStream c(String str) throws IOException {
        c.f.b.k.b(str, "fileUrl");
        HttpURLConnection httpURLConnection = (HttpURLConnection) null;
        try {
            URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
            if (uRLConnectionOpenConnection == null) {
                throw new c.q("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) uRLConnectionOpenConnection;
            try {
                httpURLConnection2.setRequestMethod("GET");
                httpURLConnection2.connect();
                InputStream inputStream = httpURLConnection2.getInputStream();
                c.f.b.k.a((Object) inputStream, "urlConnection.inputStream");
                return inputStream;
            } catch (IOException e2) {
                e = e2;
                httpURLConnection = httpURLConnection2;
                if (httpURLConnection != null) {
                    InputStream errorStream = httpURLConnection.getErrorStream();
                    c.f.b.k.a((Object) errorStream, "errorStream");
                    throw new j(str, errorStream);
                }
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    public static final Long d(String str) {
        c.f.b.k.b(str, "url");
        try {
            Log.v(d(), "Checking " + str + " content size...");
            URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
            if (uRLConnectionOpenConnection == null) {
                throw new c.q("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            try {
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                long contentLength = httpURLConnection.getContentLength();
                Log.v(d(), "Found size: " + contentLength);
                httpURLConnection.disconnect();
                try {
                    httpURLConnection.getInputStream().close();
                } catch (Exception unused) {
                }
                try {
                    httpURLConnection.getOutputStream().close();
                } catch (Exception unused2) {
                }
                if (contentLength == -1) {
                    return null;
                }
                return Long.valueOf(contentLength);
            } catch (Throwable th) {
                httpURLConnection.disconnect();
                try {
                    httpURLConnection.getInputStream().close();
                } catch (Exception unused3) {
                }
                try {
                    httpURLConnection.getOutputStream().close();
                    throw th;
                } catch (Exception unused4) {
                    throw th;
                }
            }
        } catch (Throwable th2) {
            Log.v(d(), "Error finding size: " + th2);
            return null;
        }
    }

    public static final long a(OutputStream outputStream, String str) throws IOException {
        c.f.b.k.b(str, "body");
        byte[] bytes = str.getBytes(c.l.d.f2083a);
        c.f.b.k.a((Object) bytes, "(this as java.lang.String).getBytes(charset)");
        if (outputStream != null) {
            outputStream.write(bytes);
        }
        return bytes.length;
    }

    public static final long a(OutputStream outputStream, String str, String str2) {
        c.f.b.k.b(str, "body");
        String str3 = str2;
        if (str3 == null || str3.length() == 0) {
            str2 = "application/json";
        }
        return a(outputStream, "Content-Type: " + str2 + "; charset=UTF-8") + a(outputStream) + a(outputStream) + a(outputStream, str) + a(outputStream) + a(outputStream);
    }

    public static final void a(HttpURLConnection httpURLConnection, String str) {
        c.f.b.k.b(httpURLConnection, "receiver$0");
        String str2 = str;
        String str3 = str2 == null || str2.length() == 0 ? "related" : "form-data";
        httpURLConnection.setRequestProperty(a(), "multipart/" + str3 + "; boundary=" + f());
    }

    public static final long a(OutputStream outputStream) {
        return a(outputStream, "\r\n");
    }

    public static final long b(OutputStream outputStream) {
        return a(outputStream, e() + "\r\n");
    }

    public static final long c(OutputStream outputStream) {
        return a(outputStream) + a(outputStream, e() + "--");
    }

    @TargetApi(24)
    public static final long a(URLConnection uRLConnection) {
        c.f.b.k.b(uRLConnection, "receiver$0");
        return com.joaomgcd.taskerm.util.f.f9323b.t() ? uRLConnection.getContentLengthLong() : uRLConnection.getContentLength();
    }

    public static final String b(URLConnection uRLConnection) {
        c.f.b.k.b(uRLConnection, "receiver$0");
        String str = d(uRLConnection).get("Content-Disposition");
        if (str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("filename=\"([^\"]+)\"").matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static final String c(URLConnection uRLConnection) {
        String name;
        c.f.b.k.b(uRLConnection, "receiver$0");
        String strB = b(uRLConnection);
        if (strB != null) {
            return strB;
        }
        URL url = uRLConnection.getURL();
        File fileA = dk.a(url != null ? url.toString() : null);
        if (fileA == null || (name = fileA.getName()) == null) {
            return null;
        }
        if (c.l.n.b((CharSequence) name, (CharSequence) "?", false, 2, (Object) null)) {
            name = c.l.n.a(name, "?", (String) null, 2, (Object) null);
        }
        return name.length() == 0 ? "tasker_file" : name;
    }

    public static final Map<String, String> d(URLConnection uRLConnection) {
        c.f.b.k.b(uRLConnection, "receiver$0");
        Map<String, List<String>> headerFields = uRLConnection.getHeaderFields();
        c.f.b.k.a((Object) headerFields, "headerFields");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            if (entry.getKey() != null) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            Object key = entry2.getKey();
            Object value = entry2.getValue();
            c.f.b.k.a(value, "it.value");
            arrayList.add(new c.k(key, c.a.j.a((Iterable) value, null, null, null, 0, null, null, 63, null)));
        }
        return c.a.z.a(arrayList);
    }

    private static final h a(List<String> list, String str) {
        h hVar = new h();
        if (list == null) {
            return hVar;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            List<HttpCookie> list2 = HttpCookie.parse((String) it.next());
            c.f.b.k.a((Object) list2, "parsed");
            for (HttpCookie httpCookie : list2) {
                c.f.b.k.a((Object) httpCookie, "it");
                hVar.a(httpCookie);
            }
        }
        return hVar;
    }

    static final class b extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7854a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str) {
            super(0);
            this.f7854a = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            Uri uri = Uri.parse(this.f7854a);
            c.f.b.k.a((Object) uri, "Uri.parse(url)");
            return uri.getAuthority();
        }
    }

    private static final String e(String str) {
        String str2 = (String) aj.a((c.f.a.b) null, new b(str), 1, (Object) null);
        if (str2 == null) {
            throw new RuntimeException("Invalid URL for cookies: " + str);
        }
        return "Cookies" + str2;
    }

    private static final SharedPreferences a(Context context) {
        return context.getSharedPreferences("cookieprefs", 0);
    }

    public static final h a(Context context, String str) {
        Object hVar;
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "url");
        String strE = e(str);
        SharedPreferences sharedPreferencesA = a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesCookies");
        String string = sharedPreferencesA.getString(strE, null);
        if (string == null || (hVar = com.joaomgcd.taskerm.n.b.a().a(string, (Class<Object>) h.class)) == null) {
            hVar = new h();
        }
        h hVar2 = (h) hVar;
        Collection<HttpCookie> collectionValues = hVar2.values();
        c.f.b.k.a((Object) collectionValues, "cookies.values");
        ArrayList<HttpCookie> arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            if (((HttpCookie) obj).hasExpired()) {
                arrayList.add(obj);
            }
        }
        for (HttpCookie httpCookie : arrayList) {
            c.f.b.k.a((Object) httpCookie, "it");
            hVar2.b(httpCookie);
        }
        return hVar2;
    }

    public static final h a(Context context, String str, List<String> list) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "url");
        h hVarA = a(context, str);
        List<String> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return hVarA;
        }
        hVarA.a(a(list, str));
        String strE = e(str);
        SharedPreferences sharedPreferencesA = a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesCookies");
        com.joaomgcd.taskerm.q.b.a(context, strE, hVarA, sharedPreferencesA);
        return hVarA;
    }
}
