package com.joaomgcd.taskerm.google.drive.io;

import c.f.b.g;
import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "gd")
public class DriveMetadata {
    private String id;
    private String mimeType;
    private String webContentLink;

    public DriveMetadata(String str, String str2, String str3) {
        k.b(str, be.EXTRA_ID);
        this.id = str;
        this.mimeType = str2;
        this.webContentLink = str3;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.unique_id_for_file_google_drive, labelResId = R.string.file_id, name = be.EXTRA_ID)
    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        k.b(str, "<set-?>");
        this.id = str;
    }

    public /* synthetic */ DriveMetadata(String str, String str2, String str3, int i, g gVar) {
        this(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.mimetype_for_file_google_drive, labelResId = R.string.pl_mime_type, name = "mimetype")
    public final String getMimeType() {
        return this.mimeType;
    }

    public final void setMimeType(String str) {
        this.mimeType = str;
    }

    public final String getWebContentLink() {
        return this.webContentLink;
    }

    public final void setWebContentLink(String str) {
        this.webContentLink = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.url_can_be_used_show_on_google_drive, labelResId = R.string.web_url, name = "weburl")
    public final String getWebUrl() {
        return "https://drive.google.com/file/d/" + this.id + "/view";
    }

    public final boolean isFolder() {
        return k.a((Object) this.mimeType, (Object) RequestsKt.getMIME_TYPE_GOOGLE_DRIVE_FOLDER());
    }
}
