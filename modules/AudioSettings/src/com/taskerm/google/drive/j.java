package com.joaomgcd.taskerm.google.drive;

import android.content.Context;
import android.util.Log;
import com.joaomgcd.taskerm.google.drive.io.DriveCreationOptions;
import com.joaomgcd.taskerm.google.drive.io.DriveCreationOptionsFolder;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3Upload;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadatasV3;
import com.joaomgcd.taskerm.google.drive.io.DrivePermissionsV3Create;
import com.joaomgcd.taskerm.google.drive.io.DrivePermissionsV3CreateResponse;
import com.joaomgcd.taskerm.google.drive.io.DrivePermissionsV3MakePublic;
import com.joaomgcd.taskerm.google.drive.io.DrivePermissionsV3ShareFile;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import com.joaomgcd.taskerm.google.drive.io.RequestsKt;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.util.bx;
import com.joaomgcd.taskerm.util.bz;
import com.joaomgcd.taskerm.util.ca;
import com.joaomgcd.taskerm.util.cq;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7018a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(j.class), "client", "getClient()Lcom/joaomgcd/taskerm/google/drive/APIGoogleDrive;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(j.class), "queryCache", "getQueryCache()Lcom/joaomgcd/taskerm/google/drive/QueryCache;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7019b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7020c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f7021d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f7022e;

    /* JADX INFO: Access modifiers changed from: private */
    public final int d() {
        return 1000;
    }

    private final y e() {
        c.e eVar = this.f7020c;
        c.j.g gVar = f7018a[1];
        return (y) eVar.b();
    }

    public final com.joaomgcd.taskerm.google.drive.a a() {
        c.e eVar = this.f7019b;
        c.j.g gVar = f7018a[0];
        return (com.joaomgcd.taskerm.google.drive.a) eVar.b();
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class b<T> extends c.f.b.l implements c.f.a.a<cq<T, ErrorPayloadGoogleDrive>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7025b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.google.drive.b f7026c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, com.joaomgcd.taskerm.google.drive.b bVar) {
            super(0);
            this.f7025b = str;
            this.f7026c = bVar;
        }

        static final class a extends c.f.b.l implements c.f.a.b<bx, c.s> {
            a() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(bx bxVar) {
                a(bxVar);
                return c.s.f2131a;
            }

            public final void a(bx bxVar) {
                c.f.b.k.b(bxVar, "it");
                j.this.a(bxVar, b.this.f7025b, b.this.f7026c.c(), false);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cq<T, ErrorPayloadGoogleDrive> invoke() {
            String id;
            j.this.a(new bx(null, 0L, false, "Checking file info for download...", 4, null), this.f7025b, this.f7026c.c(), false);
            cq<DriveMetadataV3, ErrorPayloadGoogleDrive> cqVarB = j.this.a(this.f7026c.a()).b();
            if (!cqVarB.b()) {
                return new cq<>(false, null, cqVarB.e());
            }
            int iD = j.this.d();
            DriveMetadataV3 driveMetadataV3D = cqVarB.d();
            ca caVar = new ca(iD, driveMetadataV3D != null ? driveMetadataV3D.getSize() : null, new a());
            com.joaomgcd.taskerm.google.drive.a.C0156a c0156a = com.joaomgcd.taskerm.google.drive.a.f6982a;
            Context contextB = j.this.b();
            String strC = j.this.c();
            Class clsB = this.f7026c.b();
            DriveMetadataV3 driveMetadataV3D2 = cqVarB.d();
            if (driveMetadataV3D2 != null && (id = driveMetadataV3D2.getId()) != null) {
                return (cq) com.joaomgcd.taskerm.google.drive.k.b(com.joaomgcd.taskerm.google.drive.k.b(c0156a.a(contextB, strC, (Class<Object>) clsB, id, caVar, this.f7026c.d())), j.this.b(), this.f7025b).b();
            }
            throw new RuntimeException("No file Id from file info for download");
        }
    }

    static final class k extends c.f.b.l implements c.f.a.a<DriveMetadataV3Upload> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7050b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.google.drive.g f7051c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        k(String str, com.joaomgcd.taskerm.google.drive.g gVar) {
            super(0);
            this.f7050b = str;
            this.f7051c = gVar;
        }

        static final class a extends c.f.b.l implements c.f.a.b<bx, c.s> {
            a() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(bx bxVar) {
                a(bxVar);
                return c.s.f2131a;
            }

            public final void a(bx bxVar) {
                c.f.b.k.b(bxVar, "it");
                j.this.a(bxVar, k.this.f7050b, k.this.f7051c.e(), true);
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final DriveMetadataV3Upload invoke() {
            String id;
            String str;
            DriveMetadataV3 v3;
            bz bzVar = new bz(j.this.d(), new a());
            bzVar.b(new bx(null, 0L, false, "Checking file for upload...", 4, null));
            u uVarC = this.f7051c.c();
            if (uVarC instanceof v) {
                id = ((v) this.f7051c.c()).b();
            } else if (uVarC instanceof w) {
                id = ((DriveMetadataV3) j.this.a(((w) this.f7051c.c()).b()).b()).getId();
            } else {
                throw new c.i();
            }
            String id2 = null;
            String str2 = (String) null;
            if (this.f7051c.d()) {
                try {
                    DriveMetadataV3 driveMetadataV3C = j.this.c(new ac(this.f7051c.b(), id, this.f7051c.c().a(), false, 8, null));
                    if (c.f.b.k.a((Object) driveMetadataV3C.getMd5Checksum(), (Object) this.f7051c.a().d().b())) {
                        return new DriveMetadataV3Upload(driveMetadataV3C, false);
                    }
                    id2 = driveMetadataV3C.getId();
                    str = id2;
                } catch (com.joaomgcd.taskerm.google.drive.i unused) {
                }
            } else {
                str = str2;
            }
            DriveCreationOptions driveCreationOptions = new DriveCreationOptions(this.f7051c.b(), id, this.f7051c.e());
            if (this.f7051c.f()) {
                driveCreationOptions.setMimeType("application/vnd.google-apps.document");
            }
            if (str == null) {
                v3 = com.joaomgcd.taskerm.google.drive.a.f6982a.a(j.this.b(), j.this.c(), this.f7051c.a(), driveCreationOptions, bzVar).b();
            } else {
                v3 = com.joaomgcd.taskerm.google.drive.a.f6982a.a(j.this.b(), j.this.c(), str, this.f7051c.a(), driveCreationOptions, bzVar).b().getV3();
            }
            c.f.b.k.a((Object) v3, "it");
            return new DriveMetadataV3Upload(v3, true);
        }
    }

    static final class i extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ bx f7042b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f7043c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f7044d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7045e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(bx bxVar, boolean z, String str, String str2) {
            super(0);
            this.f7042b = bxVar;
            this.f7043c = z;
            this.f7044d = str;
            this.f7045e = str2;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() throws InterruptedException {
            a();
            return c.s.f2131a;
        }

        public final void a() throws InterruptedException {
            String str;
            String string;
            if (this.f7042b.d() != null) {
                string = this.f7042b.d();
            } else {
                String str2 = (this.f7043c ? "Uploading" : "Downloading") + ' ' + this.f7044d + "...";
                String str3 = "kB";
                long jB = this.f7042b.b() / 1024;
                Long lValueOf = this.f7042b.a() == null ? null : Long.valueOf(this.f7042b.a().longValue() / 1024);
                if (lValueOf != null && lValueOf.longValue() > 10240) {
                    str3 = "MB";
                    jB /= 1024;
                    lValueOf = Long.valueOf(lValueOf.longValue() / 1024);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(" (");
                sb.append(jB);
                sb.append(' ');
                sb.append(str3);
                if (lValueOf != null) {
                    str = '/' + lValueOf + ' ' + str3;
                } else {
                    str = "";
                }
                sb.append(str);
                sb.append(')');
                string = sb.toString();
            }
            bn.b(com.joaomgcd.taskerm.google.drive.k.b(), string);
            Log.v(com.joaomgcd.taskerm.google.drive.k.b(), string);
            at atVarB = com.joaomgcd.taskerm.google.drive.k.b(j.this.b(), string, this.f7043c, this.f7045e, this.f7042b);
            if (this.f7042b.c()) {
                Thread.sleep(1000L);
                atVarB.d();
            } else {
                atVarB.c().b();
            }
        }
    }

    public j(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "account");
        this.f7021d = context;
        this.f7022e = str;
        this.f7019b = c.f.a(new a());
        this.f7020c = c.f.a(h.f7040a);
    }

    public final Context b() {
        return this.f7021d;
    }

    public final String c() {
        return this.f7022e;
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.google.drive.a> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.google.drive.a invoke() {
            return com.joaomgcd.taskerm.google.drive.a.f6982a.a(j.this.b(), j.this.c());
        }
    }

    static final class h extends c.f.b.l implements c.f.a.a<y> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f7040a = new h();

        h() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final y invoke() {
            return new y();
        }
    }

    static /* synthetic */ DriveMetadataV3 a(j jVar, String str, String str2, boolean z, com.joaomgcd.taskerm.google.drive.l lVar, boolean z2, boolean z3, int i2, Object obj) {
        return jVar.a(str, str2, z, lVar, (i2 & 8) != 0 ? false : z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DriveMetadataV3 a(String str, String str2, boolean z, com.joaomgcd.taskerm.google.drive.l lVar, boolean z2, boolean z3) {
        DriveMetadataV3[] files;
        String strB = com.joaomgcd.taskerm.google.drive.k.b(str, str2, z, z3);
        DriveMetadatasV3 driveMetadatasV3 = (DriveMetadatasV3) e().get(strB);
        if (driveMetadatasV3 == null) {
            driveMetadatasV3 = (DriveMetadatasV3) com.joaomgcd.taskerm.google.drive.a.b.a(a(), strB, lVar.a(), 0, 4, null).b();
        }
        DriveMetadataV3 driveMetadataV3B = (driveMetadatasV3 == null || (files = driveMetadatasV3.getFiles()) == null) ? null : (DriveMetadataV3) c.a.d.a(files, 0);
        if (driveMetadataV3B == null && z2) {
            driveMetadataV3B = a().a(new DriveCreationOptionsFolder(str, str2)).b();
        }
        if (driveMetadataV3B != null) {
            e().put(strB, new DriveMetadatasV3(new DriveMetadataV3[]{driveMetadataV3B}));
            if (driveMetadataV3B != null) {
                return driveMetadataV3B;
            }
        }
        throw new com.joaomgcd.taskerm.google.drive.i(str + " not found");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DriveMetadatasV3 a(String str, q qVar, com.joaomgcd.taskerm.google.drive.l lVar) {
        Object objB = com.joaomgcd.taskerm.google.drive.a.b.a(a(), com.joaomgcd.taskerm.google.drive.k.b(str, qVar), lVar.a(), 0, 4, null).b();
        c.f.b.k.a(objB, "client.list(query, space…spacesName).blockingGet()");
        return (DriveMetadatasV3) objB;
    }

    static final class e extends c.f.b.l implements c.f.a.a<DriveMetadataV3> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ z f7034b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(z zVar) {
            super(0);
            this.f7034b = zVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final DriveMetadataV3 invoke() {
            return j.this.c(this.f7034b);
        }
    }

    public final b.a.l<cq<DriveMetadataV3, ErrorPayloadGoogleDrive>> a(z zVar) {
        c.f.b.k.b(zVar, "queryInfo");
        return com.joaomgcd.taskerm.google.drive.k.a(com.joaomgcd.taskerm.rx.i.b(new e(zVar)), this.f7021d, (String) null, 2, (Object) null);
    }

    static final class g extends c.f.b.l implements c.f.a.a<DriveMetadatasV3> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.google.drive.d f7039b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(com.joaomgcd.taskerm.google.drive.d dVar) {
            super(0);
            this.f7039b = dVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final DriveMetadatasV3 invoke() {
            String strB;
            com.joaomgcd.taskerm.google.drive.m mVarB = this.f7039b.b();
            if (mVarB instanceof p) {
                return (DriveMetadatasV3) com.joaomgcd.taskerm.google.drive.a.b.a(j.this.a(), ((p) mVarB).b(), mVarB.a().a(), 0, 4, null).b();
            }
            if (mVarB instanceof o) {
                strB = ((DriveMetadataV3) j.this.a(((o) mVarB).b()).b()).getId();
            } else {
                if (!(mVarB instanceof n)) {
                    throw new RuntimeException("Unsupported find type");
                }
                strB = ((n) mVarB).b();
            }
            return j.this.a(strB, this.f7039b.a(), mVarB.a());
        }
    }

    public final b.a.l<cq<DriveMetadatasV3, ErrorPayloadGoogleDrive>> a(com.joaomgcd.taskerm.google.drive.d dVar) {
        c.f.b.k.b(dVar, "args");
        return com.joaomgcd.taskerm.google.drive.k.a(com.joaomgcd.taskerm.google.drive.k.b(com.joaomgcd.taskerm.rx.i.b(new g(dVar))), this.f7021d, (String) null, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DriveMetadataV3 c(z zVar) {
        if (zVar instanceof aa) {
            DriveMetadataV3 driveMetadataV3B = a().a(((aa) zVar).b()).b();
            c.f.b.k.a((Object) driveMetadataV3B, "client.get(queryInfo.id).blockingGet()");
            return driveMetadataV3B;
        }
        if (zVar instanceof ab) {
            ab abVar = (ab) zVar;
            return a(this, abVar.b(), a(abVar.c()).b().getId(), false, abVar.c().b(), false, zVar.a(), 8, null);
        }
        if (zVar instanceof ac) {
            ac acVar = (ac) zVar;
            return a(this, acVar.b(), acVar.c(), false, acVar.d(), false, zVar.a(), 8, null);
        }
        if (zVar instanceof ad) {
            ad adVar = (ad) zVar;
            return a(adVar.b(), a(adVar.c()).b().getId(), true, adVar.c().b(), adVar.c().c(), zVar.a());
        }
        if (!(zVar instanceof ae)) {
            throw new c.i();
        }
        ae aeVar = (ae) zVar;
        List listB = c.l.n.b((CharSequence) aeVar.b(), new String[]{"/"}, false, 0, 6, (Object) null);
        String strA = c.a.j.a(listB.subList(0, listB.size() - 1), "/", null, null, 0, null, null, 62, null);
        if (strA.length() == 0) {
            strA = null;
        }
        return a(this, (String) c.a.j.g(listB), a(new com.joaomgcd.taskerm.google.drive.c(strA, aeVar.c(), false, false, 8, null)).b().getId(), false, aeVar.c(), false, zVar.a(), 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(bx bxVar, String str, String str2, boolean z) {
        com.joaomgcd.taskerm.rx.i.c(new i(bxVar, z, str2, str));
    }

    static final class d extends c.f.b.l implements c.f.a.a<DriveMetadataV3> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.google.drive.c f7032b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(com.joaomgcd.taskerm.google.drive.c cVar) {
            super(0);
            this.f7032b = cVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final DriveMetadataV3 invoke() {
            String strA = this.f7032b.a();
            if (strA == null) {
                return new DriveMetadataV3("root", "Root folder", RequestsKt.getMIME_TYPE_GOOGLE_DRIVE_FOLDER(), null, null, null, null, 120, null);
            }
            List listB = c.l.n.b((CharSequence) strA, new String[]{"/"}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList();
            for (Object obj : listB) {
                if (!c.f.b.k.a(obj, (Object) "")) {
                    arrayList.add(obj);
                }
            }
            DriveMetadataV3 driveMetadataV3A = (DriveMetadataV3) null;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                driveMetadataV3A = j.this.a((String) it.next(), driveMetadataV3A != null ? driveMetadataV3A.getId() : null, true, this.f7032b.b(), this.f7032b.c(), this.f7032b.d());
            }
            if (driveMetadataV3A != null) {
                return driveMetadataV3A;
            }
            throw new RuntimeException("Folder " + this.f7032b.a() + " not found");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.l<DriveMetadataV3> a(com.joaomgcd.taskerm.google.drive.c cVar) {
        return com.joaomgcd.taskerm.rx.i.b(new d(cVar));
    }

    static final class l<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final l f7053a = new l();

        l() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive> apply(List<? extends cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> list) {
            c.f.b.k.b(list, "it");
            return (cq) c.a.j.e((List) list);
        }
    }

    public final b.a.l<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> a(com.joaomgcd.taskerm.google.drive.g gVar, String str) {
        c.f.b.k.b(gVar, "argsUpload");
        c.f.b.k.b(str, "emailAddress");
        return a(new com.joaomgcd.taskerm.google.drive.g[]{gVar}, str).c(l.f7053a);
    }

    public final b.a.l<List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>>> a(com.joaomgcd.taskerm.google.drive.g[] gVarArr, String str) {
        c.f.b.k.b(gVarArr, "multiple");
        c.f.b.k.b(str, "emailAddress");
        return com.joaomgcd.taskerm.rx.i.b(new m(gVarArr, str));
    }

    static final class m extends c.f.b.l implements c.f.a.a<List<? extends cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.google.drive.g[] f7055b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f7056c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        m(com.joaomgcd.taskerm.google.drive.g[] gVarArr, String str) {
            super(0);
            this.f7055b = gVarArr;
            this.f7056c = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> invoke() {
            boolean z;
            boolean z2;
            String id;
            List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> listB = j.this.a(this.f7055b).b();
            c.f.b.k.a((Object) listB, "uploadResult");
            List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> list = listB;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    if (!((cq) it.next()).b()) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
                break;
            }
            if (!z) {
                return listB;
            }
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            Iterator<T> it2 = list.iterator();
            while (true) {
                com.joaomgcd.taskerm.google.drive.f fVar = null;
                if (!it2.hasNext()) {
                    break;
                }
                DriveMetadataV3Upload driveMetadataV3Upload = (DriveMetadataV3Upload) ((cq) it2.next()).d();
                if (driveMetadataV3Upload != null && (id = driveMetadataV3Upload.getId()) != null) {
                    fVar = new com.joaomgcd.taskerm.google.drive.f(id, this.f7056c);
                }
                arrayList.add(fVar);
            }
            List listF = c.a.j.f((Iterable) arrayList);
            j jVar = j.this;
            Object[] array = listF.toArray(new com.joaomgcd.taskerm.google.drive.f[0]);
            if (array != null) {
                List<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>> listB2 = jVar.a((com.joaomgcd.taskerm.google.drive.f[]) array).b();
                c.f.b.k.a((Object) listB2, "shareResult");
                List<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>> list2 = listB2;
                if ((list2 instanceof Collection) && list2.isEmpty()) {
                    z2 = true;
                } else {
                    Iterator<T> it3 = list2.iterator();
                    while (it3.hasNext()) {
                        if (!((cq) it3.next()).b()) {
                            z2 = false;
                        }
                    }
                    z2 = true;
                }
                return !z2 ? c.a.j.d(new cq(false, (DriveMetadataV3Upload) null, new ErrorPayloadGoogleDrive("Couldn't share after upload"))) : listB;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    public final b.a.l<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> a(com.joaomgcd.taskerm.google.drive.g gVar) {
        c.f.b.k.b(gVar, "args");
        String string = UUID.randomUUID().toString();
        c.f.b.k.a((Object) string, "UUID.randomUUID().toString()");
        return com.joaomgcd.taskerm.google.drive.k.b(com.joaomgcd.taskerm.google.drive.k.b(com.joaomgcd.taskerm.rx.i.b(new k(string, gVar))), this.f7021d, string);
    }

    public final b.a.l<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>> a(com.joaomgcd.taskerm.google.drive.e eVar) {
        c.f.b.k.b(eVar, "args");
        return a(eVar.a(), new DrivePermissionsV3MakePublic());
    }

    public final b.a.l<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>> a(com.joaomgcd.taskerm.google.drive.f fVar) {
        c.f.b.k.b(fVar, "args");
        return a(fVar.a(), new DrivePermissionsV3ShareFile(fVar.b()));
    }

    static final class f extends c.f.b.l implements c.f.a.a<DrivePermissionsV3CreateResponse> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7036b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ DrivePermissionsV3Create f7037c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(String str, DrivePermissionsV3Create drivePermissionsV3Create) {
            super(0);
            this.f7036b = str;
            this.f7037c = drivePermissionsV3Create;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final DrivePermissionsV3CreateResponse invoke() {
            return j.this.a().a(this.f7036b, false, this.f7037c).b();
        }
    }

    private final b.a.l<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>> a(String str, DrivePermissionsV3Create drivePermissionsV3Create) {
        return com.joaomgcd.taskerm.google.drive.k.a(com.joaomgcd.taskerm.google.drive.k.b(com.joaomgcd.taskerm.rx.i.b(new f(str, drivePermissionsV3Create))), this.f7021d, (String) null, 2, (Object) null);
    }

    public final <T> b.a.l<cq<T, ErrorPayloadGoogleDrive>> a(com.joaomgcd.taskerm.google.drive.b<T> bVar) {
        c.f.b.k.b(bVar, "args");
        String string = UUID.randomUUID().toString();
        c.f.b.k.a((Object) string, "UUID.randomUUID().toString()");
        return com.joaomgcd.taskerm.rx.i.b(new b(string, bVar));
    }

    public final b.a.l<cq<String, ErrorPayloadGoogleDrive>> b(z zVar) {
        c.f.b.k.b(zVar, "queryInfo");
        return a(new com.joaomgcd.taskerm.google.drive.b(zVar, String.class, null, null, 12, null));
    }

    public final b.a.l<cq<File, ErrorPayloadGoogleDrive>> a(z zVar, File file) {
        c.f.b.k.b(zVar, "queryInfo");
        c.f.b.k.b(file, "file");
        return a(new com.joaomgcd.taskerm.google.drive.b(zVar, File.class, null, file, 4, null));
    }

    public final b.a.l<cq<List<File>, ErrorPayloadGoogleDrive>> a(com.joaomgcd.taskerm.j.d dVar, DriveMetadataV3[] driveMetadataV3Arr) {
        c.f.b.k.b(dVar, "directory");
        c.f.b.k.b(driveMetadataV3Arr, "metadatasV3");
        return a(dVar, new DriveMetadatasV3(driveMetadataV3Arr));
    }

    static final class c extends c.f.b.l implements c.f.a.a<List<? extends File>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.j.d f7029b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ DriveMetadatasV3 f7030c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(com.joaomgcd.taskerm.j.d dVar, DriveMetadatasV3 driveMetadatasV3) {
            super(0);
            this.f7029b = dVar;
            this.f7030c = driveMetadatasV3;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<File> invoke() {
            Object next;
            String errorMessage;
            ArrayList arrayList = new ArrayList();
            if (!this.f7029b.a()) {
                throw new RuntimeException("Provided file is not directory");
            }
            DriveMetadataV3[] files = this.f7030c.getFiles();
            if (files == null) {
                throw new RuntimeException("No metadata to download into directory");
            }
            ArrayList arrayList2 = new ArrayList(files.length);
            for (DriveMetadataV3 driveMetadataV3 : files) {
                File fileG = this.f7029b.g();
                String name = driveMetadataV3.getName();
                if (name == null) {
                    name = "";
                }
                File file = new File(fileG, name);
                arrayList.add(file);
                arrayList2.add(j.this.a(new aa(driveMetadataV3.getId(), false, 2, null), file));
            }
            List list = (List) b.a.l.a((Iterable) arrayList2).g().b();
            c.f.b.k.a((Object) list, "downloadsResult");
            Iterator it = list.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!(!((cq) next).b()));
            cq cqVar = (cq) next;
            if (cqVar != null) {
                ErrorPayloadGoogleDrive errorPayloadGoogleDrive = (ErrorPayloadGoogleDrive) cqVar.e();
                if (errorPayloadGoogleDrive == null || (errorMessage = errorPayloadGoogleDrive.getErrorMessage()) == null) {
                    errorMessage = "Unknown Error";
                }
                throw new RuntimeException(errorMessage);
            }
            return arrayList;
        }
    }

    public final b.a.l<cq<List<File>, ErrorPayloadGoogleDrive>> a(com.joaomgcd.taskerm.j.d dVar, DriveMetadatasV3 driveMetadatasV3) {
        c.f.b.k.b(dVar, "directory");
        c.f.b.k.b(driveMetadatasV3, "metadatasV3");
        return com.joaomgcd.taskerm.google.drive.k.a(com.joaomgcd.taskerm.rx.i.b(new c(dVar, driveMetadatasV3)), this.f7021d, (String) null, 2, (Object) null);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.google.drive.j$j, reason: collision with other inner class name */
    static final class C0158j extends c.f.b.l implements c.f.a.a<DriveMetadataV3> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ z f7047b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f7048c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0158j(z zVar, boolean z) {
            super(0);
            this.f7047b = zVar;
            this.f7048c = z;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final DriveMetadataV3 invoke() {
            DriveMetadataV3 driveMetadataV3C = j.this.c(this.f7047b);
            String name = driveMetadataV3C.getName();
            if (name == null) {
                throw new RuntimeException("File to trash has no namme");
            }
            return j.this.a().a(driveMetadataV3C.getId(), new DriveCreationOptions(name, null, null, null, Boolean.valueOf(this.f7048c), 14, null)).b();
        }
    }

    public final b.a.l<cq<DriveMetadataV3, ErrorPayloadGoogleDrive>> a(z zVar, boolean z) {
        c.f.b.k.b(zVar, "queryInfo");
        return com.joaomgcd.taskerm.google.drive.k.a(com.joaomgcd.taskerm.google.drive.k.b(com.joaomgcd.taskerm.rx.i.b(new C0158j(zVar, z))), this.f7021d, (String) null, 2, (Object) null);
    }

    public final b.a.l<List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>>> a(com.joaomgcd.taskerm.google.drive.g[] gVarArr) {
        c.f.b.k.b(gVarArr, "multiple");
        ArrayList arrayList = new ArrayList(gVarArr.length);
        for (com.joaomgcd.taskerm.google.drive.g gVar : gVarArr) {
            arrayList.add(a(gVar));
        }
        b.a.l<List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>>> lVarG = b.a.l.a((Iterable) arrayList).g();
        c.f.b.k.a((Object) lVarG, "Single.concat(multiple.m… { upload(it) }).toList()");
        return lVarG;
    }

    public final b.a.l<List<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>>> a(com.joaomgcd.taskerm.google.drive.e[] eVarArr) {
        c.f.b.k.b(eVarArr, "multiple");
        ArrayList arrayList = new ArrayList(eVarArr.length);
        for (com.joaomgcd.taskerm.google.drive.e eVar : eVarArr) {
            arrayList.add(a(eVar));
        }
        b.a.l<List<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>>> lVarG = b.a.l.a((Iterable) arrayList).g();
        c.f.b.k.a((Object) lVarG, "Single.concat(multiple.m…akePublic(it) }).toList()");
        return lVarG;
    }

    public final b.a.l<List<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>>> a(com.joaomgcd.taskerm.google.drive.f[] fVarArr) {
        c.f.b.k.b(fVarArr, "multiple");
        ArrayList arrayList = new ArrayList(fVarArr.length);
        for (com.joaomgcd.taskerm.google.drive.f fVar : fVarArr) {
            arrayList.add(a(fVar));
        }
        b.a.l<List<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>>> lVarG = b.a.l.a((Iterable) arrayList).g();
        c.f.b.k.a((Object) lVarG, "Single.concat(multiple.map { share(it) }).toList()");
        return lVarG;
    }
}
