package com.joaomgcd.taskerm.util;

import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class cy implements cu<File> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f9248a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f9249b;

    public cy(File file) {
        c.f.b.k.b(file, "file");
        this.f9249b = file;
        this.f9248a = true;
    }

    @Override // com.joaomgcd.taskerm.util.cu
    public boolean a() {
        return this.f9248a;
    }

    @Override // com.joaomgcd.taskerm.util.cu
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public File a(InputStream inputStream, ca caVar, String str, String str2) {
        c.f.b.k.b(inputStream, "stream");
        c.f.b.k.b(str, "contentType");
        File fileA = dk.a(this.f9249b, str2);
        dk.b(fileA);
        aj.a(inputStream, fileA, str, caVar);
        return fileA;
    }
}
