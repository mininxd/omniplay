package com.joaomgcd.taskerm.google.drive;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.google.drive.io.DriveCreationOptions;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV2;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadatasV3;
import com.joaomgcd.taskerm.google.drive.io.DrivePermissionsV3Create;
import com.joaomgcd.taskerm.google.drive.io.DrivePermissionsV3CreateResponse;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.bz;
import com.joaomgcd.taskerm.util.ca;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0156a f6982a = C0156a.f6983a;

    @f.c.o(a = "files")
    b.a.l<DriveMetadataV3> a(@f.c.a DriveCreationOptions driveCreationOptions);

    @f.c.f(a = "files/{fileId}?fields=id%2Ckind%2Cname%2CmimeType%2Csize%2Cmd5Checksum%2CmodifiedTime")
    b.a.l<DriveMetadataV3> a(@f.c.s(a = "fileId") String str);

    @f.c.n(a = "files/{fileId}")
    b.a.l<DriveMetadataV3> a(@f.c.s(a = "fileId") String str, @f.c.a DriveCreationOptions driveCreationOptions);

    @f.c.f(a = "files?fields=files(id%2Ckind%2Cname%2CmimeType%2Csize%2Cmd5Checksum%2CmodifiedTime)")
    b.a.l<DriveMetadatasV3> a(@f.c.t(a = "q") String str, @f.c.t(a = "spaces") String str2, @f.c.t(a = "pageSize") int i);

    @f.c.o(a = "files/{fileId}/permissions")
    b.a.l<DrivePermissionsV3CreateResponse> a(@f.c.s(a = "fileId") String str, @f.c.t(a = "sendNotificationEmail") boolean z, @f.c.a DrivePermissionsV3Create drivePermissionsV3Create);

    public static final class b {
        @f.c.f(a = "files?fields=files(id%2Ckind%2Cname%2CmimeType%2Csize%2Cmd5Checksum%2CmodifiedTime)")
        public static /* synthetic */ b.a.l a(a aVar, String str, String str2, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: list");
            }
            if ((i2 & 4) != 0) {
                i = 1000;
            }
            return aVar.a(str, str2, i);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.google.drive.a$a, reason: collision with other inner class name */
    public static final class C0156a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ C0156a f6983a = new C0156a();

        public final String a() {
            return "appDataFolder";
        }

        public final String b() {
            return "drive";
        }

        private C0156a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String[] b(Context context) {
            return ah.z(context) ? d() : c();
        }

        private final String[] c() {
            return new String[]{"https://www.googleapis.com/auth/drive.file", "https://www.googleapis.com/auth/drive.appfolder"};
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String[] d() {
            return new String[]{"https://www.googleapis.com/auth/drive"};
        }

        private final com.joaomgcd.taskerm.signin.a a(Context context, String[] strArr, String str) {
            return new com.joaomgcd.taskerm.signin.a("Google Drive", null, false, strArr, str, false, 38, null);
        }

        static /* synthetic */ com.joaomgcd.taskerm.signin.a a(C0156a c0156a, Context context, String[] strArr, String str, int i, Object obj) {
            if ((i & 4) != 0) {
                str = (String) null;
            }
            return c0156a.a(context, strArr, str);
        }

        public final b.a.l<GoogleSignInAccount> a(Context context, boolean z, boolean z2, String str) {
            c.f.b.k.b(context, "context");
            return com.joaomgcd.taskerm.signin.c.f8605a.a(context, z2, a(context, z ? d() : c(), str));
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.google.drive.a$a$b */
        static final class b<T, R> implements b.a.d.g<T, R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final b f6987a = new b();

            b() {
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String apply(GoogleSignInAccount googleSignInAccount) {
                c.f.b.k.b(googleSignInAccount, "it");
                String strC = googleSignInAccount.c();
                if (strC != null) {
                    return strC;
                }
                throw new RuntimeException("Could not get user email");
            }
        }

        public final b.a.l<String> a(Context context) {
            c.f.b.k.b(context, "context");
            C0156a c0156a = this;
            GoogleSignInAccount googleSignInAccountA = com.joaomgcd.taskerm.signin.c.f8605a.a(context);
            return c0156a.a(context, false, true, googleSignInAccountA != null ? googleSignInAccountA.c() : null).c(b.f6987a);
        }

        public static /* synthetic */ b.a.l a(C0156a c0156a, Activity activity, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return c0156a.a(activity, z);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.google.drive.a$a$a, reason: collision with other inner class name */
        static final class C0157a extends c.f.b.l implements c.f.a.a<GoogleSignInAccount> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Activity f6985b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ boolean f6986c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0157a(Activity activity, boolean z) {
                super(0);
                this.f6985b = activity;
                this.f6986c = z;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final GoogleSignInAccount invoke() {
                GoogleSignInAccount googleSignInAccountB = com.joaomgcd.taskerm.signin.c.f8605a.a(this.f6985b, this.f6986c, C0156a.a(C0156a.this, this.f6985b, C0156a.this.b(this.f6985b), (String) null, 4, (Object) null)).b();
                c.f.b.k.a((Object) googleSignInAccountB, "account");
                String strC = googleSignInAccountB.c();
                if (strC == null) {
                    return googleSignInAccountB;
                }
                Boolean boolB = com.joaomgcd.taskerm.signin.c.f8605a.a(this.f6985b, strC, C0156a.this.d()).b();
                c.f.b.k.a((Object) boolB, "signedInFull");
                if (boolB.booleanValue() || !((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a(this.f6985b, R.string.warning_dialog_title, R.string.tip_google_drive_full_access, 0, 8, (Object) null).b()).d()) {
                    return googleSignInAccountB;
                }
                ah.b((Context) this.f6985b, true);
                return C0156a.this.a(this.f6985b, true).b();
            }
        }

        public final b.a.l<GoogleSignInAccount> a(Activity activity, boolean z) {
            c.f.b.k.b(activity, "context");
            return com.joaomgcd.taskerm.rx.i.b(new C0157a(activity, z));
        }

        public final a a(Context context, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "account");
            return (a) com.joaomgcd.taskerm.google.a.a(context, a.class, "https://www.googleapis.com/drive/v3/", str, b(context));
        }

        public final b.a.l<DriveMetadataV3> a(Context context, String str, com.joaomgcd.taskerm.net.v vVar, DriveCreationOptions driveCreationOptions, bz bzVar) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "account");
            c.f.b.k.b(vVar, "content");
            c.f.b.k.b(driveCreationOptions, "options");
            c.f.b.k.b(bzVar, "progressReporter");
            return com.joaomgcd.taskerm.net.l.b(new com.joaomgcd.taskerm.net.p(new com.joaomgcd.taskerm.net.f(vVar, driveCreationOptions, "https://www.googleapis.com/upload/drive/v3/files?uploadType=multipart", "POST", DriveMetadataV3.class, ErrorPayloadGoogleDrive.class, b(context, str), bzVar, null, 256, null)), null, 1, null);
        }

        public final b.a.l<DriveMetadataV2> a(Context context, String str, String str2, com.joaomgcd.taskerm.net.v vVar, DriveCreationOptions driveCreationOptions, bz bzVar) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "account");
            c.f.b.k.b(str2, "existingFileId");
            c.f.b.k.b(vVar, "content");
            c.f.b.k.b(driveCreationOptions, "options");
            c.f.b.k.b(bzVar, "progressReporter");
            return com.joaomgcd.taskerm.net.l.b(new com.joaomgcd.taskerm.net.p(new com.joaomgcd.taskerm.net.f(vVar, driveCreationOptions, "https://www.googleapis.com/upload/drive/v2/files/" + str2 + "?uploadType=multipart", "PUT", DriveMetadataV2.class, ErrorPayloadGoogleDrive.class, b(context, str), bzVar, null, 256, null)), null, 1, null);
        }

        public final b.a.l<String> b(Context context, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "account");
            return com.joaomgcd.taskerm.signin.c.f8605a.a(context, str, true, b(context));
        }

        public final <T> b.a.l<T> a(Context context, String str, Class<T> cls, String str2, ca caVar, T t) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "account");
            c.f.b.k.b(cls, "resultClass");
            c.f.b.k.b(str2, "fileId");
            c.f.b.k.b(caVar, "progressReporter");
            return com.joaomgcd.taskerm.net.l.b(new com.joaomgcd.taskerm.net.l(new com.joaomgcd.taskerm.net.c("https://www.googleapis.com/drive/v3/files/" + str2 + "?alt=media", "GET", cls, ErrorPayloadGoogleDrive.class, t, b(context, str), null, caVar, null, null, false, false, 3904, null)), null, 1, null);
        }
    }
}
