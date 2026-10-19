package com.joaomgcd.taskerm.action.net;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dk;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class an extends com.joaomgcd.taskerm.helper.actions.execute.l<ay> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public an(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ay, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    /* JADX WARN: Code duplicated, block: B:16:0x006a  */
    /* JADX WARN: Code duplicated, block: B:46:0x0134  */
    /* JADX WARN: Code duplicated, block: B:49:0x0143  */
    /* JADX WARN: Code duplicated, block: B:52:0x015f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0164  */
    /* JADX WARN: Code duplicated, block: B:56:0x016e  */
    /* JADX WARN: Code duplicated, block: B:57:0x0173  */
    /* JADX WARN: Code duplicated, block: B:60:0x0183  */
    /* JADX WARN: Code duplicated, block: B:61:0x0188  */
    /* JADX WARN: Code duplicated, block: B:68:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:70:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:71:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:73:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:74:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:78:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:79:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:81:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:82:0x0200  */
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ay ayVar) {
        String strName;
        String absolutePath;
        String str;
        boolean z;
        com.joaomgcd.taskerm.net.g cVar;
        String headers;
        HashMap<String, String> map;
        Boolean trustAnyCertificate;
        boolean zBooleanValue;
        Boolean followRedirects;
        boolean zBooleanValue2;
        Boolean useCookies;
        boolean zBooleanValue3;
        com.joaomgcd.taskerm.net.k kVar;
        Object objB;
        Long lC;
        long jValueOf;
        r hTTPRequestOutput;
        com.joaomgcd.taskerm.net.f eVar;
        c.f.b.k.b(ayVar, "input");
        q method = ayVar.getMethod();
        if (method == null || (strName = method.name()) == null) {
            return cn.a("No HTTP method specified");
        }
        String urlWithQueryParameters = ayVar.getUrlWithQueryParameters();
        if (urlWithQueryParameters == null) {
            return cn.a("No URL specified");
        }
        if (!c.l.n.a(urlWithQueryParameters, "http", false, 2, (Object) null)) {
            urlWithQueryParameters = "https://" + urlWithQueryParameters;
        }
        String str2 = urlWithQueryParameters;
        String realBody = ayVar.getRealBody();
        String realFile = ayVar.getRealFile();
        if (realFile != null) {
            String str3 = realFile;
            if (c.l.n.b((CharSequence) str3, (CharSequence) ":", false, 2, (Object) null)) {
                List listB = c.l.n.b((CharSequence) str3, new String[]{":"}, false, 0, 6, (Object) null);
                String str4 = (String) listB.get(1);
                str = (String) listB.get(0);
                absolutePath = str4;
            } else {
                absolutePath = realFile;
                str = null;
            }
        } else {
            absolutePath = realFile;
            str = null;
        }
        if (com.joaomgcd.taskerm.util.ak.z(absolutePath)) {
            File fileA = h().a(absolutePath, i().n(), false, true, false);
            absolutePath = fileA != null ? fileA.getAbsolutePath() : null;
        }
        String fileOutput = ayVar.getFileOutput();
        File fileA2 = fileOutput != null ? dk.a(fileOutput) : null;
        Class cls = fileA2 == null ? String.class : File.class;
        b.a.l lVar = (b.a.l) null;
        ayVar.getJavascriptDelayNotNull();
        try {
            if (realBody != null) {
                if (absolutePath != null) {
                    eVar = new com.joaomgcd.taskerm.net.f(new com.joaomgcd.taskerm.net.y(absolutePath, str), realBody, str2, strName, cls, com.joaomgcd.taskerm.util.ad.class, lVar, null, fileA2);
                } else {
                    eVar = new com.joaomgcd.taskerm.net.e(realBody, str2, strName, cls, com.joaomgcd.taskerm.util.ad.class, lVar, null, fileA2);
                }
                cVar = eVar;
            } else {
                if (absolutePath != null) {
                    cVar = new com.joaomgcd.taskerm.net.g(new com.joaomgcd.taskerm.net.y(absolutePath, str), str2, strName, cls, com.joaomgcd.taskerm.util.ad.class, lVar, null, fileA2);
                } else {
                    z = true;
                    cVar = new com.joaomgcd.taskerm.net.c(str2, strName, cls, com.joaomgcd.taskerm.util.ad.class, fileA2, lVar, null, null, null, null, false, false, 4032, null);
                }
                headers = ayVar.getHeaders();
                if (headers != null || (map = h.a(headers)) == null) {
                    map = new HashMap<>();
                }
                if (map.get(com.joaomgcd.taskerm.net.ab.b()) == null) {
                    String strB = com.joaomgcd.taskerm.net.ab.b();
                    String strA = gr.a(z);
                    c.f.b.k.a((Object) strA, "Utils.getUserAgent(true)");
                    map.put(strB, strA);
                }
                cVar.a(map);
                trustAnyCertificate = ayVar.getTrustAnyCertificate();
                if (trustAnyCertificate != null) {
                    zBooleanValue = trustAnyCertificate.booleanValue();
                } else {
                    zBooleanValue = false;
                }
                cVar.a(zBooleanValue);
                followRedirects = ayVar.getFollowRedirects();
                if (followRedirects != null) {
                    zBooleanValue2 = followRedirects.booleanValue();
                } else {
                    zBooleanValue2 = false;
                }
                cVar.b(zBooleanValue2);
                ExecuteService executeServiceH = h();
                useCookies = ayVar.getUseCookies();
                if (useCookies != null) {
                    zBooleanValue3 = useCookies.booleanValue();
                } else {
                    zBooleanValue3 = false;
                }
                cVar.a(executeServiceH, zBooleanValue3);
                kVar = new com.joaomgcd.taskerm.net.k(null, null, null, null, 15, null);
                objB = cVar.a(kVar).b(ayVar.getTimeoutMs(), TimeUnit.MILLISECONDS).b();
                lC = kVar.c();
                if (lC != null || lC.longValue() == -1) {
                    if (objB instanceof String) {
                        jValueOf = Long.valueOf(((String) objB).length());
                    } else if (objB instanceof File) {
                        jValueOf = Long.valueOf(((File) objB).length());
                    } else {
                        jValueOf = 0L;
                    }
                    kVar.a(jValueOf);
                }
                if (objB instanceof String) {
                    hTTPRequestOutput = new r((String) objB, kVar);
                } else if (objB instanceof File) {
                    hTTPRequestOutput = new HTTPRequestOutput(kVar, (File) objB);
                } else {
                    hTTPRequestOutput = null;
                }
                return new cq(z, hTTPRequestOutput, null);
            }
            objB = cVar.a(kVar).b(ayVar.getTimeoutMs(), TimeUnit.MILLISECONDS).b();
            lC = kVar.c();
            if (lC != null) {
                if (objB instanceof String) {
                    jValueOf = Long.valueOf(((String) objB).length());
                } else if (objB instanceof File) {
                    jValueOf = Long.valueOf(((File) objB).length());
                } else {
                    jValueOf = 0L;
                }
                kVar.a(jValueOf);
            } else {
                if (objB instanceof String) {
                    jValueOf = Long.valueOf(((String) objB).length());
                } else if (objB instanceof File) {
                    jValueOf = Long.valueOf(((File) objB).length());
                } else {
                    jValueOf = 0L;
                }
                kVar.a(jValueOf);
            }
            if (objB instanceof String) {
                hTTPRequestOutput = new r((String) objB, kVar);
            } else if (objB instanceof File) {
                hTTPRequestOutput = new HTTPRequestOutput(kVar, (File) objB);
            } else {
                hTTPRequestOutput = null;
            }
            return new cq(z, hTTPRequestOutput, null);
        } catch (Throwable th) {
            return new cq(false, new r(th.getMessage(), kVar), new com.joaomgcd.taskerm.util.ac(th));
        }
        z = true;
        headers = ayVar.getHeaders();
        if (headers != null) {
            map = new HashMap<>();
        } else {
            map = new HashMap<>();
        }
        if (map.get(com.joaomgcd.taskerm.net.ab.b()) == null) {
            String strB2 = com.joaomgcd.taskerm.net.ab.b();
            String strA2 = gr.a(z);
            c.f.b.k.a((Object) strA2, "Utils.getUserAgent(true)");
            map.put(strB2, strA2);
        }
        cVar.a(map);
        trustAnyCertificate = ayVar.getTrustAnyCertificate();
        if (trustAnyCertificate != null) {
            zBooleanValue = trustAnyCertificate.booleanValue();
        } else {
            zBooleanValue = false;
        }
        cVar.a(zBooleanValue);
        followRedirects = ayVar.getFollowRedirects();
        if (followRedirects != null) {
            zBooleanValue2 = followRedirects.booleanValue();
        } else {
            zBooleanValue2 = false;
        }
        cVar.b(zBooleanValue2);
        ExecuteService executeServiceH2 = h();
        useCookies = ayVar.getUseCookies();
        if (useCookies != null) {
            zBooleanValue3 = useCookies.booleanValue();
        } else {
            zBooleanValue3 = false;
        }
        cVar.a(executeServiceH2, zBooleanValue3);
        kVar = new com.joaomgcd.taskerm.net.k(null, null, null, null, 15, null);
    }
}
