package com.joaomgcd.taskerm.j;

import android.content.Context;
import c.f.b.k;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class b implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final File f7553a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f7554b;

    public b(File file) {
        k.b(file, "raw");
        this.f7554b = file;
        this.f7553a = this.f7554b;
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

    public String n() {
        return d.a.d(this);
    }

    @Override // com.joaomgcd.taskerm.j.d
    public InputStream o() {
        return d.a.e(this);
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean a() {
        return this.f7554b.isDirectory();
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean b() {
        return this.f7554b.isFile();
    }

    @Override // com.joaomgcd.taskerm.j.d
    public String c() {
        String name = this.f7554b.getName();
        k.a((Object) name, "raw.name");
        return name;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public long d() {
        return this.f7554b.length();
    }

    @Override // com.joaomgcd.taskerm.j.d
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public b f() {
        File parentFile = this.f7554b.getParentFile();
        if (parentFile != null) {
            return new b(parentFile);
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public File g() {
        return this.f7553a;
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean h() {
        return this.f7554b.exists();
    }

    public String toString() {
        return n();
    }

    @Override // com.joaomgcd.taskerm.j.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public FileOutputStream b(String str, boolean z) {
        k.b(str, "mimeType");
        return new FileOutputStream(this.f7554b, z);
    }

    @Override // com.joaomgcd.taskerm.j.d
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public FileInputStream j() {
        return new FileInputStream(this.f7554b);
    }

    @Override // com.joaomgcd.taskerm.j.d
    public boolean a(Context context, boolean z) {
        k.b(context, "context");
        if (z) {
            return this.f7554b.mkdirs();
        }
        return this.f7554b.mkdir();
    }
}
