package com.joaomgcd.taskerm.google.drive.io;

import c.f.b.g;
import c.f.b.k;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public final class DriveMetadataV2 extends DriveMetadata {
    private Long fileSize;
    private final String title;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DriveMetadataV2(String str, String str2, String str3, String str4, Long l) {
        super(str, str2, str3);
        k.b(str, be.EXTRA_ID);
        this.title = str4;
        this.fileSize = l;
    }

    public /* synthetic */ DriveMetadataV2(String str, String str2, String str3, String str4, Long l, int i, g gVar) {
        this(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (Long) null : l);
    }

    public final Long getFileSize() {
        return this.fileSize;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setFileSize(Long l) {
        this.fileSize = l;
    }

    public final DriveMetadataV3 getV3() {
        return new DriveMetadataV3(getId(), this.title, getMimeType(), getWebContentLink(), this.fileSize, null, null, 96, null);
    }
}
