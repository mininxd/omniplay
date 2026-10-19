package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import c.q;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cp;
import java.io.File;
import net.dinglisch.android.taskerm.FileSelect;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivitySelectFile extends GenericActionActivityForResult {
    private final String match;
    private final boolean removePrefix;
    public static final a Companion = new a(null);
    public static final Parcelable.Creator CREATOR = new b();

    public static class b implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivitySelectFile(parcel.readString(), parcel.readInt() != 0);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivitySelectFile[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GenericActionActivitySelectFile() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeString(this.match);
        parcel.writeInt(this.removePrefix ? 1 : 0);
    }

    public GenericActionActivitySelectFile(String str, boolean z) {
        super("GenericActionActivitySelectFile");
        this.match = str;
        this.removePrefix = z;
    }

    public /* synthetic */ GenericActionActivitySelectFile(String str, boolean z, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? false : z);
    }

    public final String getMatch() {
        return this.match;
    }

    public final boolean getRemovePrefix() {
        return this.removePrefix;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        l<Intent> lVarA = l.a(FileSelect.a(activity, null, externalStorageDirectory == null ? null : externalStorageDirectory.toString(), FileSelect.b.File, this.match, true, true));
        k.a((Object) lVarA, "Single.just(intent)");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<cl> getResult(Context context, Intent intent) {
        String string;
        k.b(context, "context");
        k.b(intent, "intent");
        Bundle extras = intent.getExtras();
        if (extras == null || (string = extras.getString("path")) == null) {
            l<cl> lVarA = l.a(new cm("No file selected"));
            k.a((Object) lVarA, "Single.just(SimpleResultError(\"No file selected\"))");
            return lVarA;
        }
        if (this.removePrefix) {
            string = gr.b(string);
            k.a((Object) string, "Utils.removeSDPrefix(path)");
        }
        l<cl> lVarA2 = l.a(new cp(string));
        k.a((Object) lVarA2, "Single.just(SimpleResultSuccessWithPayload(path))");
        return lVarA2;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public static /* synthetic */ l a(a aVar, Context context, String str, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                str = (String) null;
            }
            if ((i & 4) != 0) {
                z = false;
            }
            return aVar.a(context, str, z);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.genericaction.GenericActionActivitySelectFile$a$a, reason: collision with other inner class name */
        static final class C0152a extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f6942a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ boolean f6943b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ Context f6944c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0152a(String str, boolean z, Context context) {
                super(0);
                this.f6942a = str;
                this.f6943b = z;
                this.f6944c = context;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                cl clVarB = new GenericActionActivitySelectFile(this.f6942a, this.f6943b).run(this.f6944c).b();
                if (clVarB instanceof cm) {
                    throw new RuntimeException(((cm) clVarB).c());
                }
                if (clVarB != null) {
                    return (String) ((cp) clVarB).c();
                }
                throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.util.SimpleResultSuccessWithPayload<kotlin.String>");
            }
        }

        public final l<String> a(Context context, String str, boolean z) {
            k.b(context, "context");
            return com.joaomgcd.taskerm.rx.i.b(new C0152a(str, z, context));
        }
    }
}
