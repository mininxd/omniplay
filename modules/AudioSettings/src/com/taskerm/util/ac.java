package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class ac implements ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8881a;

    public ac(Throwable th) {
        c.f.b.k.b(th, "error");
        String message = th.getMessage();
        this.f8881a = message == null ? th.toString() : message;
    }

    @Override // com.joaomgcd.taskerm.util.ab
    public String getErrorMessage() {
        return this.f8881a;
    }
}
