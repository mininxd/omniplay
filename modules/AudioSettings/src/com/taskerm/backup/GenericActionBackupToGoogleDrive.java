package com.joaomgcd.taskerm.backup;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import b.a.l;
import c.f.b.k;
import c.s;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.genericaction.GenericActionJob;
import com.joaomgcd.taskerm.genericaction.g;
import com.joaomgcd.taskerm.google.drive.aa;
import com.joaomgcd.taskerm.google.drive.c;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3Upload;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadatasV3;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import com.joaomgcd.taskerm.google.drive.j;
import com.joaomgcd.taskerm.google.drive.o;
import com.joaomgcd.taskerm.google.drive.t;
import com.joaomgcd.taskerm.google.drive.w;
import com.joaomgcd.taskerm.net.z;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.settings.ad;
import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dm;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.fx;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericActionBackupToGoogleDrive extends GenericActionJob {
    public static final a Companion = new a(null);

    public static final l<cl> backupToGoogleDrive(Activity activity, String str) {
        return Companion.a(activity, str);
    }

    public static final l<cl> backupToGoogleDriveDirect(Context context, String str, String str2, boolean z) {
        return Companion.a(context, str, str2, z);
    }

    public static final String getTAG() {
        return Companion.a();
    }

    public static final l<cq<? extends String, ? extends ab>> restoreFromGoogleDrive(Activity activity) {
        return Companion.a(activity);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionBackupToGoogleDrive(g gVar) {
        super("GenericActionBackupToGoogleDrive", gVar);
        k.b(gVar, "jobType");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(Service service) {
        String errorMessage;
        cm cmVar;
        k.b(service, "context");
        try {
            String strB = fx.e(service).b(service, ad.c(service));
            j jVar = new j(service, Companion.b(service));
            String str = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xml";
            String strB2 = Companion.a(service).b();
            k.a((Object) strB, "xml");
            cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive> cqVarB = jVar.a(new com.joaomgcd.taskerm.google.drive.g(new z(strB, null, 2, null), str, new w(new c(strB2, Companion.b(), true, false, 8, null), Companion.b()), false, "Tasker Backup Data", false, 40, null)).b();
            if (cqVarB.b()) {
                bn.b(Companion.a(), "Uploaded to Google Drive: " + str);
                cmVar = new co();
            } else {
                ErrorPayloadGoogleDrive errorPayloadGoogleDrive = (ErrorPayloadGoogleDrive) cqVarB.e();
                if (errorPayloadGoogleDrive == null || (errorMessage = errorPayloadGoogleDrive.getErrorMessage()) == null) {
                    errorMessage = "Unknown error backing up data to google drive";
                }
                bn.c(Companion.a(), errorMessage);
                cmVar = new cm(errorMessage);
            }
            l<cl> lVarA = l.a(cmVar);
            k.a((Object) lVarA, "Single.just(if (result.s…r(message)\n            })");
            return lVarA;
        } catch (Exception e2) {
            l<cl> lVarA2 = l.a(new cm(e2));
            k.a((Object) lVarA2, "Single.just(SimpleResultError(ex))");
            return lVarA2;
        }
    }

    public static final class a {
        /* JADX INFO: Access modifiers changed from: private */
        public final String c() {
            return "Tasker/data/backups/";
        }

        public final String a() {
            return "GoogleDriveBackup";
        }

        static final class d extends c.f.b.l implements c.f.a.a<cq<? extends String, ? extends ab>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Activity f6004a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            d(Activity activity) {
                super(0);
                this.f6004a = activity;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.backup.GenericActionBackupToGoogleDrive$a$d$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Integer, s> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ c.f.b.w.d f6006b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(c.f.b.w.d dVar) {
                    super(1);
                    this.f6006b = dVar;
                }

                @Override // c.f.a.b
                public /* synthetic */ s invoke(Integer num) {
                    a(num.intValue());
                    return s.f2131a;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r8v1, types: [T, com.joaomgcd.taskerm.dialog.ac] */
                public final void a(int i) {
                    ac acVar = (ac) this.f6006b.f2028a;
                    if (acVar != null) {
                        ac.a(acVar, null, 1, null);
                    }
                    this.f6006b.f2028a = ac.a.a(ac.f6217a, d.this.f6004a, i, false, 4, null);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v1, types: [T, com.joaomgcd.taskerm.dialog.ac] */
            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cq<? extends String, ? extends ab> invoke() {
                String strC;
                DriveMetadataV3 driveMetadataV3;
                c.f.b.w.d dVar = new c.f.b.w.d();
                dVar.f2028a = (ac) 0;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
                anonymousClass1.a(R.string.dc_getting_google_drive_backups);
                try {
                    try {
                        strC = GenericActionBackupToGoogleDrive.Companion.b(this.f6004a);
                    } catch (com.joaomgcd.taskerm.signin.b unused) {
                        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) com.joaomgcd.taskerm.google.drive.a.C0156a.a(com.joaomgcd.taskerm.google.drive.a.f6982a, this.f6004a, false, 2, (Object) null).b();
                        if (googleSignInAccount == null || (strC = googleSignInAccount.c()) == null) {
                            cq<? extends String, ? extends ab> cqVar = new cq<>(false, null, new com.joaomgcd.taskerm.util.ad("Couldn't sign in"));
                            ac acVar = (ac) dVar.f2028a;
                            if (acVar != null) {
                                ac.a(acVar, null, 1, null);
                            }
                            return cqVar;
                        }
                    }
                    j jVar = new j(this.f6004a, strC);
                    String strC2 = GenericActionBackupToGoogleDrive.Companion.c();
                    cq<DriveMetadatasV3, ErrorPayloadGoogleDrive> cqVarB = jVar.a(new com.joaomgcd.taskerm.google.drive.d(new t(), new o(new com.joaomgcd.taskerm.google.drive.c(strC2, GenericActionBackupToGoogleDrive.Companion.b(), false, false, 8, null)))).b();
                    DriveMetadatasV3 driveMetadatasV3D = cqVarB.d();
                    DriveMetadataV3[] files = driveMetadatasV3D != null ? driveMetadatasV3D.getFiles() : null;
                    if (cqVarB.b() && files != null) {
                        if (!(files.length == 0)) {
                            if (files.length == 1) {
                                driveMetadataV3 = files[0];
                            } else {
                                ae aeVar = (ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f6004a, R.string.ml_backup_type_google_drive, c.a.d.k(files), true, null, null, null, null, null, null, null, null, null, null, null, null, 65520, null)).b();
                                driveMetadataV3 = aeVar != null ? (DriveMetadataV3) aeVar.b() : null;
                            }
                            if (driveMetadataV3 == null) {
                                cq<? extends String, ? extends ab> cqVar2 = new cq<>(false, null, new com.joaomgcd.taskerm.util.ad("User cancelled backup restore"));
                                ac acVar2 = (ac) dVar.f2028a;
                                if (acVar2 != null) {
                                    ac.a(acVar2, null, 1, null);
                                }
                                return cqVar2;
                            }
                            cq<DriveMetadatasV3, ErrorPayloadGoogleDrive> cqVarB2 = jVar.a(new com.joaomgcd.taskerm.google.drive.d(new com.joaomgcd.taskerm.google.drive.s(), new o(new com.joaomgcd.taskerm.google.drive.c(strC2 + driveMetadataV3.getName(), GenericActionBackupToGoogleDrive.Companion.b(), false, false, 8, null)))).b();
                            DriveMetadatasV3 driveMetadatasV3D2 = cqVarB2.d();
                            DriveMetadataV3[] files2 = driveMetadatasV3D2 != null ? driveMetadatasV3D2.getFiles() : null;
                            if (cqVarB2.b() && files2 != null) {
                                if (!(files2.length == 0)) {
                                    ae aeVar2 = (ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f6004a, R.string.ml_backup_type_google_drive, c.a.d.k(files2), true, null, null, null, null, null, null, null, null, null, null, null, null, 65520, null)).b();
                                    DriveMetadataV3 driveMetadataV4 = aeVar2 != null ? (DriveMetadataV3) aeVar2.b() : null;
                                    if (driveMetadataV4 == null) {
                                        cq<? extends String, ? extends ab> cqVar3 = new cq<>(false, null, new com.joaomgcd.taskerm.util.ad("User cancelled backup restore"));
                                        ac acVar3 = (ac) dVar.f2028a;
                                        if (acVar3 != null) {
                                            ac.a(acVar3, null, 1, null);
                                        }
                                        return cqVar3;
                                    }
                                    anonymousClass1.a(R.string.dc_downoading_google_drive_backup);
                                    cq<String, ErrorPayloadGoogleDrive> cqVarB3 = jVar.b(new aa(driveMetadataV4.getId(), false, 2, null)).b();
                                    if (cqVarB3.b()) {
                                        cq<? extends String, ? extends ab> cqVar4 = new cq<>(true, cqVarB3.d(), null);
                                        ac acVar4 = (ac) dVar.f2028a;
                                        if (acVar4 != null) {
                                            ac.a(acVar4, null, 1, null);
                                        }
                                        return cqVar4;
                                    }
                                    cq<? extends String, ? extends ab> cqVar5 = new cq<>(false, null, cqVarB3.e());
                                    ac acVar5 = (ac) dVar.f2028a;
                                    if (acVar5 != null) {
                                        ac.a(acVar5, null, 1, null);
                                    }
                                    return cqVar5;
                                }
                            }
                            cq<? extends String, ? extends ab> cqVar6 = new cq<>(false, null, new com.joaomgcd.taskerm.util.ad("No backups found for " + driveMetadataV3.getName()));
                            ac acVar6 = (ac) dVar.f2028a;
                            if (acVar6 != null) {
                                ac.a(acVar6, null, 1, null);
                            }
                            return cqVar6;
                        }
                    }
                    cq<? extends String, ? extends ab> cqVar7 = new cq<>(false, null, new com.joaomgcd.taskerm.util.ad("No backups found on your Google Drive. Enable them in Preferences -> Misc"));
                    ac acVar7 = (ac) dVar.f2028a;
                    if (acVar7 != null) {
                        ac.a(acVar7, null, 1, null);
                    }
                    return cqVar7;
                } catch (Throwable th) {
                    ac acVar8 = (ac) dVar.f2028a;
                    if (acVar8 != null) {
                        ac.a(acVar8, null, 1, null);
                    }
                    throw th;
                }
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String b(Context context) {
            String strJ = Settings.j(context);
            if (strJ == null) {
                strJ = com.joaomgcd.taskerm.signin.c.f8605a.c(context);
            }
            k.a((Object) strJ, "(Settings.getGoogleDrive…stSignedInEmail(context))");
            return strJ;
        }

        static final class c extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f6003a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(Context context) {
                super(0);
                this.f6003a = context;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return GenericActionBackupToGoogleDrive.Companion.c() + dm.a(this.f6003a).b();
            }
        }

        public final l<String> a(Context context) {
            k.b(context, "context");
            return i.b(new c(context));
        }

        public final com.joaomgcd.taskerm.google.drive.l b() {
            return com.joaomgcd.taskerm.google.drive.l.Normal;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.backup.GenericActionBackupToGoogleDrive$a$a, reason: collision with other inner class name */
        static final class C0114a extends c.f.b.l implements c.f.a.a<cl> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Activity f5997a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f5998b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0114a(Activity activity, String str) {
                super(0);
                this.f5997a = activity;
                this.f5998b = str;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                if (!((x) y.a(this.f5997a, R.string.ml_backup_type_google_drive, R.string.dc_backup_to_google_drive_too, 0, 8, (Object) null).b()).d()) {
                    return new cm("User didn't want to backup to Google Drive");
                }
                try {
                    GenericActionBackupToGoogleDrive.Companion.b(this.f5997a);
                } catch (com.joaomgcd.taskerm.signin.b unused) {
                    GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) com.joaomgcd.taskerm.google.drive.a.C0156a.a(com.joaomgcd.taskerm.google.drive.a.f6982a, this.f5997a, false, 2, (Object) null).b();
                    if (googleSignInAccount == null || googleSignInAccount.c() == null) {
                        return cn.a(new com.joaomgcd.taskerm.util.ad("Couldn't sign in"));
                    }
                }
                ac acVarA = ac.a.a(ac.f6217a, this.f5997a, R.string.dc_backing_up_to_google_drive, false, 4, null);
                cl clVar = (cl) a.a(GenericActionBackupToGoogleDrive.Companion, this.f5997a, this.f5998b, null, ad.c(this.f5997a), 4, null).b();
                ac.a(acVarA, null, 1, null);
                return clVar;
            }
        }

        public final l<cl> a(Activity activity, String str) {
            k.b(activity, "context");
            k.b(str, "filename");
            return i.b(new C0114a(activity, str));
        }

        public static /* synthetic */ l a(a aVar, Context context, String str, String str2, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                str2 = (String) null;
            }
            if ((i & 8) != 0) {
                z = false;
            }
            return aVar.a(context, str, str2, z);
        }

        static final class b extends c.f.b.l implements c.f.a.a<cq<? extends Object, ? extends ab>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f5999a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f6000b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ boolean f6001c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ String f6002d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(Context context, String str, boolean z, String str2) {
                super(0);
                this.f5999a = context;
                this.f6000b = str;
                this.f6001c = z;
                this.f6002d = str2;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cq<? extends Object, ? extends ab> invoke() {
                String strB = GenericActionBackupToGoogleDrive.Companion.a(this.f5999a).b();
                com.joaomgcd.taskerm.google.drive.l lVarB = GenericActionBackupToGoogleDrive.Companion.b();
                w wVar = new w(new com.joaomgcd.taskerm.google.drive.c(strB, lVarB, true, false, 8, null), lVarB);
                String strB2 = this.f6000b;
                if (strB2 == null) {
                    strB2 = GenericActionBackupToGoogleDrive.Companion.b(this.f5999a);
                }
                if (strB2.length() == 0) {
                    return new cq<>();
                }
                j jVar = new j(this.f5999a, strB2);
                fx fxVarE = fx.e(this.f5999a);
                if (fxVarE == null) {
                    return cn.a("No data to backup");
                }
                String strB3 = fxVarE.b(this.f5999a, this.f6001c);
                k.a((Object) strB3, "xml");
                return jVar.a(new com.joaomgcd.taskerm.google.drive.g(new z(strB3, null, 2, null), this.f6002d, wVar, true, "Backup from action", false, 32, null)).b();
            }
        }

        public final l<cl> a(Context context, String str, String str2, boolean z) {
            k.b(context, "context");
            k.b(str, "filename");
            return i.b(new b(context, str2, z, str));
        }

        public final l<cq<? extends String, ? extends ab>> a(Activity activity) {
            k.b(activity, "context");
            return i.b(new d(activity));
        }
    }
}
