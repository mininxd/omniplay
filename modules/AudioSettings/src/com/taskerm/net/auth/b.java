package com.joaomgcd.taskerm.net.auth;

import android.util.Base64;
import b.a.l;
import c.f.b.k;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.aj;
import java.nio.charset.Charset;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public interface b {
    l<h> a(long j);

    public static final class a {
        public static String a(b bVar, String str, String str2) {
            k.b(str, "user");
            k.b(str2, "pass");
            String str3 = str + ':' + str2;
            Charset charset = c.l.d.f2083a;
            if (str3 == null) {
                throw new q("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes = str3.getBytes(charset);
            k.a((Object) bytes, "(this as java.lang.String).getBytes(charset)");
            return Base64.encodeToString(bytes, 2);
        }

        public static String a(b bVar, JSONObject jSONObject) {
            k.b(jSONObject, "receiver$0");
            return aj.a(jSONObject, "access_token");
        }

        public static Long b(b bVar, JSONObject jSONObject) {
            k.b(jSONObject, "receiver$0");
            String strA = aj.a(jSONObject, "expires_in");
            if (strA != null) {
                return n.d(strA);
            }
            return null;
        }

        public static String c(b bVar, JSONObject jSONObject) {
            k.b(jSONObject, "receiver$0");
            return aj.a(jSONObject, "refresh_token");
        }
    }
}
