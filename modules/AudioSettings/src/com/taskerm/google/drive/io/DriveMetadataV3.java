package com.joaomgcd.taskerm.google.drive.io;

import android.annotation.SuppressLint;
import c.f.b.g;
import c.f.b.k;
import c.l.n;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.ak;
import cyanogenmod.app.ProfileManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "gd")
public class DriveMetadataV3 extends DriveMetadata {
    private String md5Checksum;
    private String modifiedTime;
    private String name;
    private DriveMetadatasV3[] parents;
    private Long size;

    public /* synthetic */ DriveMetadataV3(String str, String str2, String str3, String str4, Long l, DriveMetadatasV3[] driveMetadatasV3Arr, String str5, int i, g gVar) {
        this(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (Long) null : l, (i & 32) != 0 ? (DriveMetadatasV3[]) null : driveMetadatasV3Arr, (i & 64) != 0 ? (String) null : str5);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.name_file_google_drive, labelResId = R.string.file_name, name = ProfileManager.EXTRA_PROFILE_NAME)
    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.size_file_google_drive, labelResId = R.string.file_size, name = "size")
    public final Long getSize() {
        return this.size;
    }

    public final void setSize(Long l) {
        this.size = l;
    }

    @com.joaomgcd.taskerm.inputoutput.g
    public final DriveMetadatasV3[] getParents() {
        return this.parents;
    }

    public final void setParents(DriveMetadatasV3[] driveMetadatasV3Arr) {
        this.parents = driveMetadatasV3Arr;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.name_md5_google_drive, labelResId = R.string.file_md5, name = "md5")
    public final String getMd5Checksum() {
        return this.md5Checksum;
    }

    public final void setMd5Checksum(String str) {
        this.md5Checksum = str;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DriveMetadataV3(String str, String str2, String str3, String str4, Long l, DriveMetadatasV3[] driveMetadatasV3Arr, String str5) {
        super(str, str3, str4);
        k.b(str, be.EXTRA_ID);
        this.name = str2;
        this.size = l;
        this.parents = driveMetadatasV3Arr;
        this.md5Checksum = str5;
    }

    public String toString() {
        String str = this.name;
        return str != null ? str : "Unknown Google Drive File";
    }

    public final void setModifiedTime(String str) {
        this.modifiedTime = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.time_google_drive, labelResId = R.string.file_time, name = "time")
    @SuppressLint({"SimpleDateFormat"})
    public final String getModifiedTime() {
        String strA;
        String strA2;
        Date date;
        String str = this.modifiedTime;
        if (str == null || (strA = ak.a(str, "Z")) == null || (strA2 = n.a(strA, "T", " ", false, 4, (Object) null)) == null || (date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strA2)) == null) {
            return null;
        }
        return String.valueOf(date.getTime() / 1000);
    }
}
