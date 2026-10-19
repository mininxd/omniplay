package com.joaomgcd.taskerservercommon.license;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseTaskerServerLicense {
    private final boolean success;

    public ResponseTaskerServerLicense() {
        this(false, 1, null);
    }

    public ResponseTaskerServerLicense(boolean z) {
        this.success = z;
    }

    public /* synthetic */ ResponseTaskerServerLicense(boolean z, int i, g gVar) {
        this((i & 1) != 0 ? true : z);
    }

    public final boolean getSuccess() {
        return this.success;
    }
}
