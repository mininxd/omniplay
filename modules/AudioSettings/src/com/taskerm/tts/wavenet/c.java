package com.joaomgcd.taskerm.tts.wavenet;

import c.f.b.k;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final File f8793a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f8794b;

    public c(File file, b bVar) {
        k.b(file, "file");
        k.b(bVar, "inputSynthesize");
        this.f8793a = file;
        this.f8794b = bVar;
    }

    public final File a() {
        return this.f8793a;
    }

    public final b b() {
        return this.f8794b;
    }
}
