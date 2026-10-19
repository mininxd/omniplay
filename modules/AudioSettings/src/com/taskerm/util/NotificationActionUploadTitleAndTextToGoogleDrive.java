package com.joaomgcd.taskerm.util;

import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.ActivityGenericAction;
import com.joaomgcd.taskerm.genericaction.GenericActionActivity;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3Upload;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
final class NotificationActionUploadTitleAndTextToGoogleDrive extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8856a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8857b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f8858c;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new NotificationActionUploadTitleAndTextToGoogleDrive(parcel.readString(), parcel.readString(), parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new NotificationActionUploadTitleAndTextToGoogleDrive[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c.f.b.k.b(parcel, "parcel");
        parcel.writeString(this.f8856a);
        parcel.writeString(this.f8857b);
        parcel.writeString(this.f8858c);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationActionUploadTitleAndTextToGoogleDrive(String str, String str2, String str3) {
        super("NotificationActionUploadTitleAndTextToGoogleDrive");
        c.f.b.k.b(str, "text");
        c.f.b.k.b(str2, "fileName");
        this.f8856a = str;
        this.f8857b = str2;
        this.f8858c = str3;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public b.a.l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        String str;
        c.f.b.k.b(activityGenericAction, "context");
        ActivityGenericAction activityGenericAction2 = activityGenericAction;
        String strB = com.joaomgcd.taskerm.google.drive.a.f6982a.a(activityGenericAction2).b();
        if (strB == null) {
            b.a.l<cl> lVarA = b.a.l.a(cn.a("Couldn't sign in to Google Drive"));
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResult…ign in to Google Drive\"))");
            return lVarA;
        }
        com.joaomgcd.taskerm.google.drive.j jVar = new com.joaomgcd.taskerm.google.drive.j(activityGenericAction2, strB);
        com.joaomgcd.taskerm.dialog.ac acVarA = com.joaomgcd.taskerm.dialog.ac.a.a(com.joaomgcd.taskerm.dialog.ac.f6217a, activityGenericAction2, R.string.dc_uploading_report_files, false, 4, null);
        try {
            String str2 = this.f8858c;
            if (str2 == null || str2.length() == 0) {
                str = this.f8856a;
            } else {
                str = this.f8858c + "\n\n" + this.f8856a;
            }
            cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive> cqVarB = jVar.a(new com.joaomgcd.taskerm.google.drive.h(str, this.f8857b, "Tasker/data/crashreports"), "support@joaoapps.com").b();
            com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
            if (!cqVarB.b()) {
                b.a.l<cl> lVarA2 = b.a.l.a(cn.a("Couldn't upload and share crash report to Google Drive: " + cqVarB.a()));
                c.f.b.k.a((Object) lVarA2, "Single.just(SimpleResult…rrorMessageFromResult}\"))");
                return lVarA2;
            }
            DriveMetadataV3Upload driveMetadataV3UploadD = cqVarB.d();
            if (driveMetadataV3UploadD == null) {
                b.a.l<cl> lVarA3 = b.a.l.a(cn.a("Couldn't get uploaded file details for crash report to Google Drive"));
                c.f.b.k.a((Object) lVarA3, "Single.just(SimpleResult…report to Google Drive\"))");
                return lVarA3;
            }
            ah.m(activityGenericAction, driveMetadataV3UploadD.getWebUrl());
            com.joaomgcd.taskerm.dialog.y.d(activityGenericAction, R.string.uploaded, R.string.google_drive_url_copied_to_clipboard_send_to_developer).b();
            b.a.l<cl> lVarA4 = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA4, "Single.just(SimpleResultSuccess())");
            return lVarA4;
        } finally {
            com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
        }
    }
}
