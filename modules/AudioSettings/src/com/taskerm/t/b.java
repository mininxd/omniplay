package com.joaomgcd.taskerm.t;

import android.graphics.Point;
import c.f.b.g;
import c.f.b.k;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8715a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8716b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f8717c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Integer f8718d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Point f8719e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Integer f8720f;

    public b(String str, String str2, boolean z, Integer num, Point point, Integer num2) {
        k.b(str, "folderToSaveIn");
        k.b(str2, "fileName");
        this.f8715a = str;
        this.f8716b = str2;
        this.f8717c = z;
        this.f8718d = num;
        this.f8719e = point;
        this.f8720f = num2;
    }

    public /* synthetic */ b(String str, String str2, boolean z, Integer num, Point point, Integer num2, int i, g gVar) {
        this(str, str2, z, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? (Point) null : point, (i & 32) != 0 ? (Integer) null : num2);
    }

    public final String a() {
        return this.f8715a;
    }

    public final String b() {
        return this.f8716b;
    }

    public final boolean c() {
        return this.f8717c;
    }

    public final Integer d() {
        return this.f8718d;
    }

    public final Point e() {
        return this.f8719e;
    }

    public final Integer f() {
        return this.f8720f;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public b(File file, String str, boolean z, Integer num, Point point, Integer num2) {
        k.b(file, "folderToSaveIn");
        k.b(str, "fileName");
        String absolutePath = file.getAbsolutePath();
        k.a((Object) absolutePath, "folderToSaveIn.absolutePath");
        this(absolutePath, str, z, num, point, num2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public b(File file, boolean z, Integer num, Point point, Integer num2) {
        k.b(file, "file");
        File parentFile = file.getParentFile();
        k.a((Object) parentFile, "file.parentFile");
        this(parentFile, c.e.c.b(file), z, num, point, num2);
    }
}
