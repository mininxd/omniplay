package com.joaomgcd.taskerm.google.drive.io;

import c.f.b.g;
import c.f.b.k;
import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public final class DriveCreationOptionsFolder extends DriveCreationOptions {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DriveCreationOptionsFolder(String str, String[] strArr, String str2) {
        super(str, RequestsKt.getMIME_TYPE_GOOGLE_DRIVE_FOLDER(), strArr, str2, null, 16, null);
        k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        k.b(strArr, "parents");
    }

    public /* synthetic */ DriveCreationOptionsFolder(String str, String[] strArr, String str2, int i, g gVar) {
        this(str, (i & 2) != 0 ? new String[0] : strArr, (i & 4) != 0 ? (String) null : str2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DriveCreationOptionsFolder(String str, String str2) {
        this(str, str2 == null ? new String[0] : new String[]{str2}, null, 4, null);
        k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
    }
}
