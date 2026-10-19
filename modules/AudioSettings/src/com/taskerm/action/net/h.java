package com.joaomgcd.taskerm.action.net;

import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.danlew.android.joda.R;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean b(q qVar) {
        return qVar != null && com.joaomgcd.taskerm.util.aj.a(qVar, q.POST, q.PUT, q.PATCH, q.DELETE, q.OPTIONS);
    }

    private static final t[] b() {
        String[] strArr = {"audio/aac", "application/x-abiword", "application/octet-stream", "video/x-msvideo", "application/vnd.amazon.ebook", "application/x-bzip", "application/x-bzip2", "application/x-csh", "text/css", "text/csv", "application/msword", "application/vnd.ms-fontobject", "application/epub+zip", "image/gif", "text/html", "image/x-icon", "text/calendar", "application/java-archive", "image/jpeg", "application/javascript", "application/json", "audio/midi", "video/mpeg", "application/vnd.apple.installer+xml", "application/vnd.oasis.opendocument.presentation", "application/vnd.oasis.opendocument.spreadsheet", "application/vnd.oasis.opendocument.text", "audio/ogg", "video/ogg", "application/ogg", "font/otf", "image/png", "application/pdf", "application/vnd.ms-powerpoint", "application/x-rar-compressed", "application/rtf", "application/x-sh", "image/svg+xml", "application/x-shockwave-flash", "application/x-tar", "image/tiff", "application/typescript", "font/ttf", "application/vnd.visio", "audio/x-wav", "audio/webm", "video/webm", "image/webp", "font/woff", "font/woff2", "application/xhtml+xml", "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/xml", "application/vnd.mozilla.xul+xml", "application/zip", "video/3gpp", "video/3gpp2", "application/x-7z-compressed"};
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(new t(str));
        }
        Object[] array = arrayList.toArray(new t[0]);
        if (array != null) {
            return (t[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HashMap<String, t[]> c() {
        return c.a.z.b(new c.k("Accept", b()), new c.k(HttpConnection.CONTENT_TYPE, b()), new c.k("User-Agent", new t[]{new t("Google Chrome Desktop", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36"), new t("Mozilla Firefox Desktop", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0"), new t("Microsoft Edge Desktop", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"), new t("Microsoft Internet Explorer 6 / IE 6", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"), new t("Microsoft Internet Explorer 7 / IE 7", "Mozilla/5.0 (Windows; U; MSIE 7.0; Windows NT 6.0; en-US)"), new t("Microsoft Internet Explorer 8 / IE 8", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)"), new t("Microsoft Internet Explorer 9 / IE 9", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/5.0;  Trident/5.0)"), new t("Microsoft Internet Explorer 10 / IE 10", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0; MDDCJS)"), new t("Microsoft Internet Explorer 11 / IE 11", "Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko"), new t("Apple iPad", "Mozilla/5.0 (iPad; CPU OS 8_4_1 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12H321 Safari/600.1.4"), new t("Apple iPhone", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1"), new t("Googlebot (Google Search Engine Bot)", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"), new t("Bing Bot (Bing Search Engine Bot)", "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)"), new t("Samsung Phone", "Mozilla/5.0 (Linux; Android 6.0.1; SAMSUNG SM-G570Y Build/MMB29K) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/4.0 Chrome/44.0.2403.133 Mobile Safari/537.36"), new t("Google Phone", "Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Galaxy Nexus Build/IML74K) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Mobile Safari/535.7"), new t("HTC Phone", "Mozilla/5.0 (Linux; Android 7.0; HTC 10 Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.83 Mobile Safari/537.36"), new t("Curl", "curl/7.35.0"), new t("Wget", "Wget/1.15 (linux-gnu)")}));
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f5635a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f5635a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            List listK;
            Activity activity = this.f5635a;
            Set setKeySet = h.c().keySet();
            c.f.b.k.a((Object) setKeySet, "commonHeaders.keys");
            String str = (String) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, R.string.pl_headers, c.a.j.i(setKeySet), true, null, null, null, null, null, null, null, null, null, null, null, null, 65520, null)).b()).b();
            if (str != null) {
                c.f.b.k.a((Object) str, "dialogSingleChoice(Dialo…No header type selected\")");
                t[] tVarArr = (t[]) h.c().get(str);
                if (tVarArr == null || (listK = c.a.d.k(tVarArr)) == null) {
                    throw new RuntimeException("Invalid header type " + str);
                }
                t tVar = (t) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f5635a, str, listK, true, null, null, null, null, null, null, null, null, null, null, null, null, null, 131056, null)).b()).b();
                if (tVar == null) {
                    throw new RuntimeException("No header value selected");
                }
                return str + ':' + tVar.a();
            }
            throw new RuntimeException("No header type selected");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final b.a.l<String> b(Activity activity) {
        return com.joaomgcd.taskerm.rx.i.b(new a(activity));
    }

    public static final HashMap<String, String> a(String str) {
        c.f.b.k.b(str, "receiver$0");
        HashMap<String, String> map = new HashMap<>();
        for (String str2 : c.l.n.b((CharSequence) str, new String[]{"\n"}, false, 0, 6, (Object) null)) {
            int iA = c.l.n.a((CharSequence) str2, ":", 0, false, 6, (Object) null);
            if (iA > 0) {
                if (str2 != null) {
                    String strSubstring = str2.substring(0, iA);
                    c.f.b.k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    int i = iA + 1;
                    if (str2 != null) {
                        String strSubstring2 = str2.substring(i);
                        c.f.b.k.a((Object) strSubstring2, "(this as java.lang.String).substring(startIndex)");
                        map.put(strSubstring, strSubstring2);
                    } else {
                        throw new c.q("null cannot be cast to non-null type java.lang.String");
                    }
                } else {
                    throw new c.q("null cannot be cast to non-null type java.lang.String");
                }
            }
        }
        return map;
    }
}
