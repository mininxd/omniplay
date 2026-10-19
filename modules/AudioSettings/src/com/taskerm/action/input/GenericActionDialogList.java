package com.joaomgcd.taskerm.action.input;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.ActivityGenericAction;
import com.joaomgcd.taskerm.genericaction.GenericActionDialog;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.fs;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionDialogList extends GenericActionDialog {
    public static final Parcelable.Creator CREATOR = new a();
    private final Integer firstVisibleIndex;
    private final boolean hideFilter;
    private final String[] items;
    private final String longClickTask;
    private final o mode;
    private final String negativeLabel;
    private final String neutralLabel;
    private final String positiveLabel;
    private final String[] selected;
    private final String text;
    private final long timeoutSeconds;
    private final String title;
    private final boolean useHtml;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new GenericActionDialogList((o) Enum.valueOf(o.class, parcel.readString()), parcel.readString(), parcel.createStringArray(), parcel.createStringArray(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readLong(), parcel.readInt() != 0, parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionDialogList[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iIntValue;
        c.f.b.k.b(parcel, "parcel");
        parcel.writeString(this.mode.name());
        parcel.writeString(this.title);
        parcel.writeStringArray(this.items);
        parcel.writeStringArray(this.selected);
        parcel.writeString(this.longClickTask);
        parcel.writeString(this.positiveLabel);
        parcel.writeString(this.negativeLabel);
        parcel.writeString(this.neutralLabel);
        parcel.writeInt(this.useHtml ? 1 : 0);
        Integer num = this.firstVisibleIndex;
        if (num != null) {
            parcel.writeInt(1);
            iIntValue = num.intValue();
        } else {
            iIntValue = 0;
        }
        parcel.writeInt(iIntValue);
        parcel.writeLong(this.timeoutSeconds);
        parcel.writeInt(this.hideFilter ? 1 : 0);
        parcel.writeString(this.text);
    }

    static final class b extends c.f.b.l implements c.f.a.b<String, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActivityGenericAction f5359b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f5360c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(ActivityGenericAction activityGenericAction, String str) {
            super(1);
            this.f5359b = activityGenericAction;
            this.f5360c = str;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            c.f.b.k.b(str, "item");
            ActivityGenericAction activityGenericAction = this.f5359b;
            String longClickTask = GenericActionDialogList.this.getLongClickTask();
            Bundle bundle = new Bundle();
            bundle.putString(this.f5360c, str);
            ExecuteService.a(activityGenericAction, longClickTask, bundle);
        }
    }

    public final o getMode() {
        return this.mode;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String[] getItems() {
        return this.items;
    }

    public final String[] getSelected() {
        return this.selected;
    }

    public final String getLongClickTask() {
        return this.longClickTask;
    }

    public final String getPositiveLabel() {
        return this.positiveLabel;
    }

    public final String getNegativeLabel() {
        return this.negativeLabel;
    }

    public final String getNeutralLabel() {
        return this.neutralLabel;
    }

    public final boolean getUseHtml() {
        return this.useHtml;
    }

    public final Integer getFirstVisibleIndex() {
        return this.firstVisibleIndex;
    }

    public final long getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    public final boolean getHideFilter() {
        return this.hideFilter;
    }

    public final String getText() {
        return this.text;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionDialogList(o oVar, String str, String[] strArr, String[] strArr2, String str2, String str3, String str4, String str5, boolean z, Integer num, long j, boolean z2, String str6) {
        super("ActionDialogList");
        c.f.b.k.b(oVar, "mode");
        c.f.b.k.b(str, "title");
        c.f.b.k.b(strArr, "items");
        this.mode = oVar;
        this.title = str;
        this.items = strArr;
        this.selected = strArr2;
        this.longClickTask = str2;
        this.positiveLabel = str3;
        this.negativeLabel = str4;
        this.neutralLabel = str5;
        this.useHtml = z;
        this.firstVisibleIndex = num;
        this.timeoutSeconds = j;
        this.hideFilter = z2;
        this.text = str6;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionDialog
    public b.a.l<cl> showDialog(ActivityGenericAction activityGenericAction, int i) {
        com.joaomgcd.taskerm.dialog.ad adVar;
        b.a.l lVarA;
        String strA;
        OutputDialogListMultiple outputDialogListMultiple;
        c.f.b.k.b(activityGenericAction, "activity");
        List listK = c.a.d.k(this.items);
        String str = this.positiveLabel;
        com.joaomgcd.taskerm.util.j jVarD = str != null ? com.joaomgcd.taskerm.util.ak.D(str) : null;
        String str2 = this.negativeLabel;
        com.joaomgcd.taskerm.util.j jVarD2 = str2 != null ? com.joaomgcd.taskerm.util.ak.D(str2) : null;
        String str3 = this.neutralLabel;
        com.joaomgcd.taskerm.util.j jVarD3 = str3 != null ? com.joaomgcd.taskerm.util.ak.D(str3) : null;
        com.joaomgcd.taskerm.util.h hVarB = jVarD != null ? jVarD : com.joaomgcd.taskerm.util.aj.b(R.string.button_label_ok, activityGenericAction);
        if (hVarB == null) {
            b.a.l<cl> lVarA2 = b.a.l.a(cn.a("Invalid label for positive multiple"));
            c.f.b.k.a((Object) lVarA2, "Single.just(SimpleResult… for positive multiple\"))");
            return lVarA2;
        }
        boolean z = !this.hideFilter;
        switch (this.mode) {
            case Single:
                com.joaomgcd.taskerm.dialog.ad adVar2 = new com.joaomgcd.taskerm.dialog.ad(activityGenericAction, this.title, listK, false, null, null, null, Boolean.valueOf(z), Boolean.valueOf(this.useHtml), Integer.valueOf(i), this.firstVisibleIndex, jVarD, jVarD2, jVarD3, null, null, this.text, 49264, null);
                if (this.longClickTask != null) {
                    adVar = adVar2;
                    adVar.b(new b(activityGenericAction, fs.b(1)));
                } else {
                    adVar = adVar2;
                    adVar.a((Boolean) true);
                }
                lVarA = com.joaomgcd.taskerm.dialog.k.a(adVar);
                break;
            case Multiple:
                com.joaomgcd.taskerm.dialog.e eVar = new com.joaomgcd.taskerm.dialog.e(activityGenericAction, com.joaomgcd.taskerm.util.ak.D(this.title), listK, false, null, hVarB, jVarD2, jVarD3, Boolean.valueOf(this.useHtml), Integer.valueOf(i), Boolean.valueOf(z), null, this.text, 2064, null);
                if (this.selected != null) {
                    if (!(this.selected.length == 0)) {
                        eVar.a(new c());
                    }
                }
                lVarA = com.joaomgcd.taskerm.dialog.k.a(eVar);
                break;
            default:
                throw new c.i();
        }
        com.joaomgcd.taskerm.dialog.p pVar = (com.joaomgcd.taskerm.dialog.p) lVarA.b(this.timeoutSeconds, TimeUnit.SECONDS).b();
        com.joaomgcd.taskerm.dialog.x xVarA = pVar.a();
        if (xVarA.f()) {
            b.a.l<cl> lVarA3 = b.a.l.a(cn.a("Cancelled"));
            c.f.b.k.a((Object) lVarA3, "Single.just(SimpleResultErrorString(\"Cancelled\"))");
            return lVarA3;
        }
        if (xVarA.d()) {
            strA = this.mode == o.Single ? this.positiveLabel : hVarB.a();
        } else if (xVarA.e()) {
            strA = this.negativeLabel;
        } else {
            strA = xVarA.g() ? this.neutralLabel : null;
        }
        if (pVar instanceof com.joaomgcd.taskerm.dialog.ae) {
            com.joaomgcd.taskerm.dialog.ae aeVar = (com.joaomgcd.taskerm.dialog.ae) pVar;
            String str4 = (String) aeVar.b();
            Integer numC = aeVar.c();
            outputDialogListMultiple = new OutputDialogListSingle(str4, numC != null ? Integer.valueOf(numC.intValue() + 1) : null, strA, aeVar.a().o());
        } else {
            if (!(pVar instanceof com.joaomgcd.taskerm.dialog.as)) {
                throw new c.i();
            }
            if (strA == null) {
                b.a.l<cl> lVarA4 = b.a.l.a(cn.a("No button pressed"));
                c.f.b.k.a((Object) lVarA4, "Single.just(SimpleResult…ing(\"No button pressed\"))");
                return lVarA4;
            }
            com.joaomgcd.taskerm.dialog.as asVar = (com.joaomgcd.taskerm.dialog.as) pVar;
            List listB = asVar.b();
            if (listB == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
            }
            Object[] array = listB.toArray(new String[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            String[] strArr = (String[]) array;
            List<Integer> listC = asVar.c();
            if (listC == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.collections.List<kotlin.Int>");
            }
            Object[] array2 = listC.toArray(new Integer[0]);
            if (array2 == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            outputDialogListMultiple = new OutputDialogListMultiple(strArr, (Integer[]) array2, strA);
        }
        b.a.l<cl> lVarA5 = b.a.l.a(cn.a(outputDialogListMultiple));
        c.f.b.k.a((Object) lVarA5, "Single.just(SimpleResultSuccess(result))");
        return lVarA5;
    }

    static final class c extends c.f.b.l implements c.f.a.b<String, com.joaomgcd.taskerm.dialog.w> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.w invoke(String str) {
            c.f.b.k.b(str, "it");
            return new com.joaomgcd.taskerm.dialog.w(str, null, c.a.d.b(GenericActionDialogList.this.getSelected(), str), 2, null);
        }
    }
}
