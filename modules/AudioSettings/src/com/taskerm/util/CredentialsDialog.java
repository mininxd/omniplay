package com.joaomgcd.taskerm.util;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult;

/* JADX INFO: loaded from: classes.dex */
public final class CredentialsDialog extends q {
    public CredentialsDialog() {
        super(null);
    }

    public static final class ActionAskForCredentials extends GenericActionActivityForResult {
        public static final Parcelable.Creator CREATOR = new a();
        private final com.joaomgcd.taskerm.genericaction.i conflictResolution;
        private final String description;
        private final String title;

        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                c.f.b.k.b(parcel, "in");
                return new ActionAskForCredentials(parcel.readString(), parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new ActionAskForCredentials[i];
            }
        }

        protected static /* synthetic */ void conflictResolution$annotations() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
        public String getErrorOnResultNotOk() {
            return "Credentials not provided";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            c.f.b.k.b(parcel, "parcel");
            parcel.writeString(this.title);
            parcel.writeString(this.description);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionAskForCredentials(String str, String str2) {
            super("ActionAskForCredentials");
            c.f.b.k.b(str, "title");
            this.title = str;
            this.description = str2;
            this.conflictResolution = new com.joaomgcd.taskerm.genericaction.i();
        }

        public final String getDescription() {
            return this.description;
        }

        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.joaomgcd.taskerm.genericaction.GenericAction
        public com.joaomgcd.taskerm.genericaction.i getConflictResolution() {
            return this.conflictResolution;
        }

        @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
        public b.a.l<Intent> getIntentToStartForResult(Activity activity) {
            c.f.b.k.b(activity, "context");
            KeyguardManager keyguardManager = (KeyguardManager) activity.getSystemService("keyguard");
            if (keyguardManager == null) {
                b.a.l<Intent> lVarB = b.a.l.b(new RuntimeException("Can't get Keyguard manager"));
                c.f.b.k.a((Object) lVarB, "Single.error(RuntimeExce…t get Keyguard manager\"))");
                return lVarB;
            }
            Intent intentCreateConfirmDeviceCredentialIntent = keyguardManager.createConfirmDeviceCredentialIntent(this.title, this.description);
            if (intentCreateConfirmDeviceCredentialIntent == null) {
                b.a.l<Intent> lVarB2 = b.a.l.b(new RuntimeException("Device doesn't have credentials setup"));
                c.f.b.k.a((Object) lVarB2, "Single.error(RuntimeExce…have credentials setup\"))");
                return lVarB2;
            }
            b.a.l<Intent> lVarA = b.a.l.a(intentCreateConfirmDeviceCredentialIntent);
            c.f.b.k.a((Object) lVarA, "Single.just(intent)");
            return lVarA;
        }
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8854a = new a();

        a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ce apply(cl clVar) {
            c.f.b.k.b(clVar, "it");
            return new ce(clVar);
        }
    }

    @Override // com.joaomgcd.taskerm.util.q
    protected b.a.l<ce> b(m mVar) {
        c.f.b.k.b(mVar, "args");
        b.a.l lVarC = new ActionAskForCredentials(mVar.b(), mVar.d()).run(mVar.a()).c(a.f8854a);
        c.f.b.k.a((Object) lVarC, "ActionAskForCredentials(…ationDialog(it)\n        }");
        return lVarC;
    }
}
