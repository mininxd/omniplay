package com.joaomgcd.taskerm.google.drive.io;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class DrivePermissionsV3ShareFile extends DrivePermissionsV3Create {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DrivePermissionsV3ShareFile(String str) {
        super("writer", "user", str);
        k.b(str, "emailAddress");
    }
}
