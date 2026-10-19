package com.joaomgcd.taskerm.google.drive.io;

import c.f.b.g;
import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "gd")
public final class DriveMetadataV3Upload extends DriveMetadataV3 {
    private boolean uploaded;

    public /* synthetic */ DriveMetadataV3Upload(String str, String str2, String str3, String str4, Long l, DriveMetadatasV3[] driveMetadatasV3Arr, String str5, boolean z, int i, g gVar) {
        this(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (Long) null : l, (i & 32) != 0 ? (DriveMetadatasV3[]) null : driveMetadatasV3Arr, (i & 64) != 0 ? (String) null : str5, z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DriveMetadataV3Upload(String str, String str2, String str3, String str4, Long l, DriveMetadatasV3[] driveMetadatasV3Arr, String str5, boolean z) {
        super(str, str2, str3, str4, l, driveMetadatasV3Arr, str5);
        k.b(str, be.EXTRA_ID);
        this.uploaded = z;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.uploaded_google_drive, labelResId = R.string.uploaded, name = "uploaded")
    public final boolean getUploaded() {
        return this.uploaded;
    }

    public final void setUploaded(boolean z) {
        this.uploaded = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DriveMetadataV3Upload(DriveMetadataV3 driveMetadataV3, boolean z) {
        this(driveMetadataV3.getId(), driveMetadataV3.getName(), driveMetadataV3.getMimeType(), driveMetadataV3.getWebContentLink(), driveMetadataV3.getSize(), driveMetadataV3.getParents(), driveMetadataV3.getMd5Checksum(), z);
        k.b(driveMetadataV3, "metadataV3");
    }
}
