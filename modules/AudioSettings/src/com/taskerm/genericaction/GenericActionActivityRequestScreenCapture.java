package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ac;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityRequestScreenCapture extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionActivityRequestScreenCapture();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityRequestScreenCapture[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeInt(1);
    }

    public GenericActionActivityRequestScreenCapture() {
        super("GenericActionActivityRequestScreenCapture");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        Intent intentCreateScreenCaptureIntent;
        l<Intent> lVarA;
        k.b(activity, "context");
        MediaProjectionManager mediaProjectionManagerM = ah.M(activity);
        if (mediaProjectionManagerM != null && (intentCreateScreenCaptureIntent = mediaProjectionManagerM.createScreenCaptureIntent()) != null && (lVarA = l.a(intentCreateScreenCaptureIntent)) != null) {
            return lVarA;
        }
        l<Intent> lVarB = l.b(new RuntimeException("Couldn't get projection manager"));
        k.a((Object) lVarB, "Single.error(RuntimeExce…get projection manager\"))");
        return lVarB;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<cl> getResult(Context context, Intent intent) {
        MediaProjection mediaProjection;
        k.b(context, "context");
        k.b(intent, "intent");
        try {
            MediaProjectionManager mediaProjectionManagerM = ah.M(context);
            if (mediaProjectionManagerM == null || (mediaProjection = mediaProjectionManagerM.getMediaProjection(-1, intent)) == null) {
                l<cl> lVarA = l.a(cn.a("Couldn't get media projection"));
                k.a((Object) lVarA, "Single.just(SimpleResult…t get media projection\"))");
                return lVarA;
            }
            l<cl> lVarA2 = l.a(cn.a(mediaProjection));
            k.a((Object) lVarA2, "Single.just(SimpleResultSuccess(result))");
            return lVarA2;
        } catch (com.google.android.gms.common.api.b e2) {
            l<cl> lVarA3 = l.a(cn.a(new ac(e2)));
            k.a((Object) lVarA3, "Single.just(SimpleResult…rorPayloadException(ex)))");
            return lVarA3;
        }
    }
}
