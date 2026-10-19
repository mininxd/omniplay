package com.joaomgcd.taskerm.google.drive.io;

import c.f.b.g;
import c.f.b.k;
import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public class DriveCreationOptions {
    private final String description;
    private String mimeType;
    private final String name;
    private final String[] parents;
    private final Boolean trashed;

    public DriveCreationOptions(String str, String str2, String[] strArr, String str3, Boolean bool) {
        k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        k.b(strArr, "parents");
        this.name = str;
        this.mimeType = str2;
        this.parents = strArr;
        this.description = str3;
        this.trashed = bool;
    }

    public /* synthetic */ DriveCreationOptions(String str, String str2, String[] strArr, String str3, Boolean bool, int i, g gVar) {
        this(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? new String[0] : strArr, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (Boolean) null : bool);
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final String getName() {
        return this.name;
    }

    public final String[] getParents() {
        return this.parents;
    }

    public final Boolean getTrashed() {
        return this.trashed;
    }

    public final void setMimeType(String str) {
        this.mimeType = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DriveCreationOptions(String str, String str2, String str3) {
        this(str, null, str2 == null ? new String[0] : new String[]{str2}, str3, null, 18, null);
        k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
    }

    public /* synthetic */ DriveCreationOptions(String str, String str2, String str3, int i, g gVar) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3);
    }
}
