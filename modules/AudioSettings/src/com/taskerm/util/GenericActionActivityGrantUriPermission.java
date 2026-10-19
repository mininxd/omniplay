package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.storage.StorageVolume;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityGrantUriPermission extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final File file;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new GenericActionActivityGrantUriPermission((File) parcel.readSerializable());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityGrantUriPermission[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c.f.b.k.b(parcel, "parcel");
        parcel.writeSerializable(this.file);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityGrantUriPermission(File file) {
        super("GenericActionActivityGrantUriPermission");
        c.f.b.k.b(file, "file");
        this.file = file;
    }

    public final File getFile() {
        return this.file;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    @TargetApi(29)
    public b.a.l<Intent> getIntentToStartForResult(Activity activity) {
        Intent intentA;
        c.f.b.k.b(activity, "context");
        if (f.f9323b.g()) {
            String absolutePath = this.file.getAbsolutePath();
            c.f.b.k.a((Object) absolutePath, "file.absolutePath");
            StorageVolume storageVolumeC = dk.c(activity, absolutePath);
            intentA = storageVolumeC != null ? storageVolumeC.createOpenDocumentTreeIntent() : null;
        } else {
            intentA = net.dinglisch.android.taskerm.as.a(true);
        }
        b.a.l<Intent> lVarA = b.a.l.a(intentA);
        c.f.b.k.a((Object) lVarA, "Single.just(if (Api.isMi…tOpenDocTreeIntent(true))");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        cl clVarA;
        c.f.b.k.b(activity, "activity");
        if (intent == null) {
            return cn.a("No path selected. Please select " + this.file);
        }
        Activity activity2 = activity;
        net.dinglisch.android.taskerm.as.a(activity2, -1, intent);
        if (net.dinglisch.android.taskerm.as.a((Context) activity2, this.file, true, true)) {
            clVarA = new co();
        } else {
            clVarA = cn.a("Doesn't have access to " + this.file);
        }
        return clVarA;
    }
}
