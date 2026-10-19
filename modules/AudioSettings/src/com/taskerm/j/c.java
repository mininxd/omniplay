package com.joaomgcd.taskerm.j;

import android.content.Context;
import c.f.b.g;
import c.f.b.k;
import com.joaomgcd.taskerm.util.dk;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import net.dinglisch.android.taskerm.ad;
import net.dinglisch.android.taskerm.as;

/* JADX INFO: loaded from: classes.dex */
public final class c implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f7555a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ad f7556b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final File f7557c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f7558d;

    public c(Context context, ad adVar, File file, boolean z) {
        k.b(context, "context");
        this.f7555a = context;
        this.f7556b = adVar;
        this.f7557c = file;
        this.f7558d = z;
    }

    public /* synthetic */ c(Context context, ad adVar, File file, boolean z, int i, g gVar) {
        this(context, adVar, (i & 4) != 0 ? (File) null : file, (i & 8) != 0 ? false : z);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(d dVar) {
        k.b(dVar, "other");
        return d.a.a(this, dVar);
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean a(Context context) {
        k.b(context, "context");
        return d.a.a(this, context);
    }

    public String e() {
        return d.a.d(this);
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean k() {
        return d.a.a(this);
    }

    @Override // com.joaomgcd.taskerm.j.d
    public File l() {
        return d.a.b(this);
    }

    @Override // com.joaomgcd.taskerm.j.d
    public String m() {
        return d.a.c(this);
    }

    @Override // com.joaomgcd.taskerm.j.d
    public InputStream o() {
        return d.a.e(this);
    }

    private final ad i() {
        ad adVar = this.f7556b;
        if (adVar != null && adVar.j()) {
            return adVar;
        }
        d dVarA = a.a(g(), this.f7555a, this.f7558d);
        if (dVarA instanceof c) {
            return ((c) dVarA).f7556b;
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean a() {
        ad adVarI = i();
        if (adVarI != null) {
            return adVarI.e();
        }
        return false;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean b() {
        ad adVarI = i();
        if (adVarI != null) {
            return adVarI.f();
        }
        return false;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public String c() {
        String strB;
        ad adVarI = i();
        if (adVarI != null && (strB = adVarI.b()) != null) {
            return strB;
        }
        String name = g().getName();
        k.a((Object) name, "rawFile.name");
        return name;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public long d() {
        ad adVarI = i();
        if (adVarI != null) {
            return adVarI.h();
        }
        return 0L;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean h() {
        ad adVarI = i();
        if (adVarI != null) {
            return adVarI.j();
        }
        return false;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public d f() {
        File parentFile = g().getParentFile();
        if (parentFile != null) {
            return a.a(parentFile, this.f7555a, this.f7558d);
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public File g() {
        File file = this.f7557c;
        if (file == null) {
            ad adVarI = i();
            String strA = as.a(adVarI != null ? adVarI.a() : null, this.f7555a, true);
            file = strA != null ? new File(strA) : null;
        }
        if (file == null) {
            ad adVarI2 = i();
            file = new File(String.valueOf(adVarI2 != null ? adVarI2.a() : null));
        }
        return file;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public OutputStream b(String str, boolean z) {
        k.b(str, "mimeType");
        String strF = dk.f(g());
        ad adVarA = this.f7556b;
        if (adVarA == null) {
            adVarA = as.a(this.f7555a, g(), true, strF, "FileWrapperSaf.getOutputStream", false);
        }
        this.f7556b = adVarA;
        if (z) {
            return as.d(this.f7555a.getContentResolver(), adVarA);
        }
        return as.b(this.f7555a.getContentResolver(), adVarA);
    }

    @Override // com.joaomgcd.taskerm.j.d
    public InputStream j() {
        ad adVarI = i();
        if (adVarI != null) {
            return as.a(this.f7555a.getContentResolver(), adVarI);
        }
        return null;
    }

    public String toString() {
        return e();
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean a(Context context, boolean z) {
        k.b(context, "context");
        if (h()) {
            return true;
        }
        String strF = dk.f(g());
        if (strF == null) {
            strF = "vnd.android.document/directory";
        }
        return as.a(context, g(), z, strF, "FileWrapperSaf.mkDirs") != null;
    }
}
