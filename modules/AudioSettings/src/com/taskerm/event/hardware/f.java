package com.joaomgcd.taskerm.event.hardware;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Boolean f6671a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f6672b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f6673c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Boolean f6674d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Class<OutputFingerprintGesture> f6675e;

    public f() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void down$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void left$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void right$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void up$annotations() {
    }

    public f(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Class<OutputFingerprintGesture> cls) {
        this.f6671a = bool;
        this.f6672b = bool2;
        this.f6673c = bool3;
        this.f6674d = bool4;
        this.f6675e = cls;
    }

    public /* synthetic */ f(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Class cls, int i, g gVar) {
        this((i & 1) != 0 ? (Boolean) null : bool, (i & 2) != 0 ? (Boolean) null : bool2, (i & 4) != 0 ? (Boolean) null : bool3, (i & 8) != 0 ? (Boolean) null : bool4, (i & 16) != 0 ? OutputFingerprintGesture.class : cls);
    }

    public final Boolean getLeft() {
        return this.f6671a;
    }

    public final void setLeft(Boolean bool) {
        this.f6671a = bool;
    }

    public final Boolean getRight() {
        return this.f6672b;
    }

    public final void setRight(Boolean bool) {
        this.f6672b = bool;
    }

    public final Boolean getUp() {
        return this.f6673c;
    }

    public final void setUp(Boolean bool) {
        this.f6673c = bool;
    }

    public final Boolean getDown() {
        return this.f6674d;
    }

    public final void setDown(Boolean bool) {
        this.f6674d = bool;
    }

    public final Class<OutputFingerprintGesture> getOutputClass() {
        return this.f6675e;
    }

    public final void setOutputClass(Class<OutputFingerprintGesture> cls) {
        this.f6675e = cls;
    }
}
