package com.joaomgcd.taskerm.google.drive.io;

import c.f.b.g;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "gd")
public final class DriveMetadatasV3 {
    private final DriveMetadataV3[] files;

    /* JADX WARN: Multi-variable type inference failed */
    public DriveMetadatasV3() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public DriveMetadatasV3(DriveMetadataV3[] driveMetadataV3Arr) {
        this.files = driveMetadataV3Arr;
    }

    public /* synthetic */ DriveMetadatasV3(DriveMetadataV3[] driveMetadataV3Arr, int i, g gVar) {
        this((i & 1) != 0 ? (DriveMetadataV3[]) null : driveMetadataV3Arr);
    }

    public final DriveMetadataV3[] getFiles() {
        return this.files;
    }
}
