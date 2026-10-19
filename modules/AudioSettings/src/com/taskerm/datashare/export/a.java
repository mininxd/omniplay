package com.joaomgcd.taskerm.datashare.export;

import android.content.Context;
import android.net.Uri;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.retrofit.j;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerservercommon.datashare.DataShareData;
import com.joaomgcd.taskerservercommon.datashare.DataShareInfo;
import com.joaomgcd.taskerservercommon.datashare.DataShareType;
import com.joaomgcd.taskerservercommon.datashare.RequestAddDataShare;
import com.joaomgcd.taskerservercommon.response.ResponseAddDataShares;
import com.joaomgcd.taskerservercommon.response.ResponseDataShareData;
import com.joaomgcd.taskerservercommon.response.ResponseDataShareStats;
import com.joaomgcd.taskerservercommon.response.ResponseGetTags;
import f.c.o;
import f.c.s;
import f.c.t;
import java.io.UnsupportedEncodingException;
import java.util.List;
import net.dinglisch.android.taskerm.fx;

/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0120a f6113a = C0120a.f6114a;

    @f.c.f(a = "tags")
    l<ResponseGetTags> a();

    @o(a = "shares")
    l<ResponseAddDataShares> a(@f.c.a RequestAddDataShare requestAddDataShare);

    @f.c.f(a = "sharedata/{encryptedUserAccount}/{shareId}")
    l<ResponseDataShareData> a(@s(a = "shareId", b = true) String str, @s(a = "encryptedUserAccount", b = true) String str2, @t(a = "countDownload") Boolean bool);

    @o(a = "sharestats/{encryptedUserAccount}/{shareId}")
    l<ResponseDataShareStats> a(@s(a = "shareId", b = true) String str, @s(a = "encryptedUserAccount", b = true) String str2, @t(a = "countViews") Boolean bool, @t(a = "countDownload") Boolean bool2);

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.a$a, reason: collision with other inner class name */
    public static final class C0120a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ C0120a f6114a = new C0120a();

        private C0120a() {
        }

        public final a a(Context context) {
            k.b(context, "context");
            return (a) j.a(context, a.class, "datashare", true);
        }

        public final boolean a(String str) {
            k.b(str, "receiver$0");
            return fx.c(str);
        }

        public final boolean b(String str) {
            k.b(str, "receiver$0");
            return fx.d(str);
        }

        public final boolean c(String str) {
            k.b(str, "receiver$0");
            return fx.e(str);
        }

        public final DataShareType d(String str) {
            k.b(str, "receiver$0");
            if (a(str)) {
                return DataShareType.Task;
            }
            if (b(str)) {
                return DataShareType.Profile;
            }
            if (c(str)) {
                return DataShareType.Project;
            }
            throw new RuntimeException("Invalid Import URL type");
        }

        public final boolean e(String str) {
            k.b(str, "receiver$0");
            return a(str) || c(str) || b(str);
        }

        public final RequestAddDataShare a(String str, String str2, String str3, Boolean bool, String[] strArr) {
            k.b(str, "importableName");
            k.b(str2, "description");
            k.b(str3, "importableData");
            DataShareType dataShareTypeD = d(str3);
            return new RequestAddDataShare(dataShareTypeD + ':' + str, new DataShareInfo(str, dataShareTypeD, str, str2, null, 16, null), new DataShareData(str, str3, null, 4, null), null, bool, strArr != null ? c.a.d.j(strArr) : null, null, 72, null);
        }

        public final g a(Context context, String str, boolean z, a aVar) throws UnsupportedEncodingException {
            String strL;
            k.b(context, "context");
            k.b(str, "shareUri");
            k.b(aVar, "client");
            if (!fx.b(str)) {
                return null;
            }
            Uri uri = Uri.parse(str);
            k.a((Object) uri, "uri");
            String host = uri.getHost();
            k.a((Object) host, "uri.host");
            String strL2 = ak.l(host);
            if (strL2 == null) {
                throw new RuntimeException("No user");
            }
            List<String> pathSegments = uri.getPathSegments();
            k.a((Object) pathSegments, "uri.pathSegments");
            String str2 = (String) c.a.j.a((List) pathSegments, 0);
            if (str2 == null || (strL = ak.l(str2)) == null) {
                throw new RuntimeException("No shareId");
            }
            String shareData = aVar.a(strL, strL2, Boolean.valueOf(z)).b().getShareData();
            if (!e(shareData)) {
                throw new RuntimeException("Is not valid share link");
            }
            return new g(strL2, strL, shareData);
        }
    }
}
