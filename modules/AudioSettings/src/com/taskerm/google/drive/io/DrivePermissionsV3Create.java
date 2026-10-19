package com.joaomgcd.taskerm.google.drive.io;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public class DrivePermissionsV3Create {
    private final String emailAddress;
    private final String role;
    private final String type;

    public DrivePermissionsV3Create(String str, String str2, String str3) {
        k.b(str, "role");
        k.b(str2, "type");
        this.role = str;
        this.type = str2;
        this.emailAddress = str3;
    }

    public final String getEmailAddress() {
        return this.emailAddress;
    }

    public final String getRole() {
        return this.role;
    }

    public final String getType() {
        return this.type;
    }
}
