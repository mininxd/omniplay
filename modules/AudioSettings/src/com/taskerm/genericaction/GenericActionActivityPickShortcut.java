package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Parcel;
import android.os.Parcelable;
import c.f.b.k;
import c.f.b.l;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.w;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityPickShortcut extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionActivityPickShortcut();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityPickShortcut[i];
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

    public GenericActionActivityPickShortcut() {
        super("GenericActionActivityPickShortcut");
    }

    static final class b extends l implements c.f.a.a<Intent> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6926a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Activity activity) {
            super(0);
            this.f6926a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Intent invoke() {
            List<ResolveInfo> listQueryIntentActivities = this.f6926a.getPackageManager().queryIntentActivities(new Intent("android.intent.action.CREATE_SHORTCUT"), 131072);
            k.a((Object) listQueryIntentActivities, "context.packageManager.q…PackageManager.MATCH_ALL)");
            List<ResolveInfo> list = listQueryIntentActivities;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((ResolveInfo) it.next()).activityInfo);
            }
            ActivityInfo activityInfo = (ActivityInfo) ((ae) com.joaomgcd.taskerm.dialog.k.a(new ad(this.f6926a, R.string.an_shortcut, arrayList, true, new a(), null, null, true, null, null, null, null, null, null, null, null, 65376, null)).b()).b();
            if (activityInfo == null) {
                throw new RuntimeException("No shortcut selected");
            }
            Intent intent = new Intent();
            intent.setAction("android.intent.action.CREATE_SHORTCUT");
            intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
            intent.addCategory("android.intent.category.DEFAULT");
            return intent;
        }

        static final class a extends l implements c.f.a.b<ActivityInfo, w> {
            a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final w invoke(ActivityInfo activityInfo) {
                net.dinglisch.android.taskerm.g gVar = new net.dinglisch.android.taskerm.g();
                gVar.b(activityInfo.packageName, activityInfo.name);
                return new w(ah.a(b.this.f6926a, activityInfo.packageName, (String) null, 2, (Object) null) + ": " + activityInfo.loadLabel(b.this.f6926a.getPackageManager()).toString(), gVar, false, 4, null);
            }
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public b.a.l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        return com.joaomgcd.taskerm.rx.i.b(new b(activity));
    }

    private final Intent getResultIntent(Intent intent) {
        Intent intent2;
        return (intent == null || (intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.shortcut.INTENT")) == null) ? intent : intent2;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public b.a.l<cl> getResult(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "intent");
        b.a.l<cl> lVarA = b.a.l.a(cn.a(getResultIntent(intent)));
        k.a((Object) lVarA, "Single.just(SimpleResult…ess(intent.resultIntent))");
        return lVarA;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public cq<? extends Object, com.joaomgcd.taskerm.util.ad> checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        return getResultIntent(intent) != null ? cn.a((Object) null) : cn.a("No shortcut selected");
    }
}
