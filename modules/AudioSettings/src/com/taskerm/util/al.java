package com.joaomgcd.taskerm.util;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class al extends an {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.a.k.d<File> f8971a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f8972b;

    /* JADX WARN: Illegal instructions before constructor call */
    public al(File file, int i) {
        c.f.b.k.b(file, "file");
        String absolutePath = file.getAbsolutePath();
        c.f.b.k.a((Object) absolutePath, "file.absolutePath");
        super("FileEventWaiter", absolutePath, i);
        this.f8972b = file;
    }

    @Override // com.joaomgcd.taskerm.util.an
    public void a(int i, String str) {
        File file;
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        if (this.f8972b.isFile()) {
            file = this.f8972b;
        } else {
            file = new File(this.f8972b, str);
        }
        b.a.k.d<File> dVar = this.f8971a;
        if (dVar != null) {
            dVar.d_(file);
        }
    }

    public final b.a.l<File> a() {
        b.a.k.d<File> dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<File>()");
        this.f8971a = dVarF;
        b();
        b.a.l<File> lVarA = dVarF.a(new a());
        c.f.b.k.a((Object) lVarA, "result.doFinally {\n     … stopWatching()\n        }");
        return lVarA;
    }

    static final class a implements b.a.d.a {
        a() {
        }

        @Override // b.a.d.a
        public final void run() {
            an.a(al.this, null, 1, null);
        }
    }
}
