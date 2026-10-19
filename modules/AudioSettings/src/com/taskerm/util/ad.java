package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class ad implements ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8882a;

    public ad(String str) {
        c.f.b.k.b(str, "error");
        this.f8882a = str;
    }

    @Override // com.joaomgcd.taskerm.util.ab
    public String getErrorMessage() {
        return this.f8882a;
    }
}
