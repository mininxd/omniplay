package com.joaomgcd.taskerm.util;

import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.ActivityGenericAction;
import com.joaomgcd.taskerm.genericaction.GenericActionActivity;
import java.util.ArrayList;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class BiometricDialog extends q {
    public BiometricDialog() {
        super(null);
    }

    public static final class ActionBiometricDialog extends GenericActionActivity {
        public static final Parcelable.Creator CREATOR = new a();
        private CancellationSignal cancellationSignal;
        private final boolean confirmationRequired;
        private final String description;
        private final boolean deviceCredentialsAllowed;
        private final String negativeButtonText;
        private final int numberOfAttempts;
        private final String subtitle;
        private final String title;

        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                c.f.b.k.b(parcel, "in");
                return new ActionBiometricDialog(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new ActionBiometricDialog[i];
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            c.f.b.k.b(parcel, "parcel");
            parcel.writeString(this.title);
            parcel.writeString(this.subtitle);
            parcel.writeString(this.description);
            parcel.writeString(this.negativeButtonText);
            parcel.writeInt(this.numberOfAttempts);
            parcel.writeInt(this.confirmationRequired ? 1 : 0);
            parcel.writeInt(this.deviceCredentialsAllowed ? 1 : 0);
        }

        static final class c extends c.f.b.l implements c.f.a.b<b.a.k.d<ce>, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ ArrayList f8829a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ce f8830b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(ArrayList arrayList, ce ceVar) {
                super(1);
                this.f8829a = arrayList;
                this.f8830b = ceVar;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.BiometricDialog$ActionBiometricDialog$c$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<c.s> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ b.a.k.d f8832b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(b.a.k.d dVar) {
                    super(0);
                    this.f8832b = dVar;
                }

                @Override // c.f.a.a
                public /* synthetic */ c.s invoke() {
                    a();
                    return c.s.f2131a;
                }

                public final void a() {
                    c.this.f8829a.add(new co());
                    this.f8832b.d_(c.this.f8830b);
                }
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(b.a.k.d<ce> dVar) {
                a(dVar);
                return c.s.f2131a;
            }

            public final void a(b.a.k.d<ce> dVar) {
                c.f.b.k.b(dVar, "receiver$0");
                com.joaomgcd.taskerm.rx.i.a(dVar, new AnonymousClass1(dVar));
            }
        }

        static final class d extends c.f.b.l implements c.f.a.q<b.a.k.d<ce>, String, Boolean, c.s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ArrayList f8834b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ ce f8835c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            d(ArrayList arrayList, ce ceVar) {
                super(3);
                this.f8834b = arrayList;
                this.f8835c = ceVar;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.BiometricDialog$ActionBiometricDialog$d$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<c.s> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ b.a.k.d f8837b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ String f8838c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ boolean f8839d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(b.a.k.d dVar, String str, boolean z) {
                    super(0);
                    this.f8837b = dVar;
                    this.f8838c = str;
                    this.f8839d = z;
                }

                @Override // c.f.a.a
                public /* synthetic */ c.s invoke() {
                    a();
                    return c.s.f2131a;
                }

                public final void a() {
                    d.this.f8834b.add(new cm(this.f8838c));
                    if (d.this.f8834b.size() >= ActionBiometricDialog.this.getNumberOfAttempts()) {
                        CancellationSignal cancellationSignal = ActionBiometricDialog.this.cancellationSignal;
                        if (cancellationSignal != null) {
                            cancellationSignal.cancel();
                        }
                        d.this.f8834b.add(new cm("Exceeded number of attempts"));
                        this.f8837b.d_(d.this.f8835c);
                        return;
                    }
                    if (this.f8839d) {
                        return;
                    }
                    this.f8837b.d_(d.this.f8835c);
                }
            }

            @Override // c.f.a.q
            public /* synthetic */ c.s a(b.a.k.d<ce> dVar, String str, Boolean bool) {
                a(dVar, str, bool.booleanValue());
                return c.s.f2131a;
            }

            public final void a(b.a.k.d<ce> dVar, String str, boolean z) {
                c.f.b.k.b(dVar, "receiver$0");
                c.f.b.k.b(str, "message");
                com.joaomgcd.taskerm.rx.i.a(dVar, new AnonymousClass1(dVar, str, z));
            }
        }

        static final class b extends c.f.b.l implements c.f.a.b<Integer, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ ActivityGenericAction f8828a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(ActivityGenericAction activityGenericAction) {
                super(1);
                this.f8828a = activityGenericAction;
            }

            @Override // c.f.a.b
            public /* synthetic */ String invoke(Integer num) {
                return a(num.intValue());
            }

            public final String a(int i) {
                return aj.a(i, this.f8828a, new Object[0]);
            }
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }

        public final String getDescription() {
            return this.description;
        }

        public final String getNegativeButtonText() {
            return this.negativeButtonText;
        }

        public final int getNumberOfAttempts() {
            return this.numberOfAttempts;
        }

        public final boolean getConfirmationRequired() {
            return this.confirmationRequired;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionBiometricDialog(String str, String str2, String str3, String str4, int i, boolean z, boolean z2) {
            super("ActionBiometricDialog");
            c.f.b.k.b(str, "title");
            c.f.b.k.b(str4, "negativeButtonText");
            this.title = str;
            this.subtitle = str2;
            this.description = str3;
            this.negativeButtonText = str4;
            this.numberOfAttempts = i;
            this.confirmationRequired = z;
            this.deviceCredentialsAllowed = z2;
        }

        public final boolean getDeviceCredentialsAllowed() {
            return this.deviceCredentialsAllowed;
        }

        public final void cancel() {
            CancellationSignal cancellationSignal = this.cancellationSignal;
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
            }
        }

        @Override // com.joaomgcd.taskerm.genericaction.GenericAction
        public b.a.l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
            c.f.b.k.b(activityGenericAction, "context");
            b.a.k.d dVarF = b.a.k.d.f();
            c.f.b.k.a((Object) dVarF, "SingleSubject.create<ResultAuthenticationDialog>()");
            ArrayList arrayList = new ArrayList();
            ce ceVar = new ce((ArrayList<cl>) arrayList);
            b bVar = new b(activityGenericAction);
            c cVar = new c(arrayList, ceVar);
            d dVar = new d(arrayList, ceVar);
            BiometricPrompt.Builder builder = new BiometricPrompt.Builder(activityGenericAction);
            builder.setTitle(this.title);
            String str = this.subtitle;
            if (str != null) {
                builder.setSubtitle(str);
            }
            String str2 = this.description;
            if (str2 != null) {
                builder.setDescription(str2);
            }
            if (!this.deviceCredentialsAllowed) {
                builder.setNegativeButton(this.negativeButtonText, AsyncTask.THREAD_POOL_EXECUTOR, new e(dVar, dVarF, bVar));
            }
            if (com.joaomgcd.taskerm.util.f.f9323b.g()) {
                builder.setConfirmationRequired(this.confirmationRequired).setDeviceCredentialAllowed(this.deviceCredentialsAllowed);
            }
            BiometricPrompt biometricPromptBuild = builder.build();
            CancellationSignal cancellationSignal = new CancellationSignal();
            this.cancellationSignal = cancellationSignal;
            biometricPromptBuild.authenticate(cancellationSignal, AsyncTask.THREAD_POOL_EXECUTOR, new f(dVar, dVarF, bVar, cVar));
            b.a.l<cl> lVarC = dVarF.b(new g()).c(h.f8848a);
            c.f.b.k.a((Object) lVarC, "publisher.doOnSuccess {\n…tSuccessWithPayload(it) }");
            return lVarC;
        }

        static final class e implements DialogInterface.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ d f8840a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b.a.k.d f8841b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ b f8842c;

            e(d dVar, b.a.k.d dVar2, b bVar) {
                this.f8840a = dVar;
                this.f8841b = dVar2;
                this.f8842c = bVar;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f8840a.a(this.f8841b, this.f8842c.a(R.string.word_cancelled), false);
            }
        }

        public static final class f extends BiometricPrompt.AuthenticationCallback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ d f8843a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b.a.k.d f8844b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ b f8845c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ c f8846d;

            f(d dVar, b.a.k.d dVar2, b bVar, c cVar) {
                this.f8843a = dVar;
                this.f8844b = dVar2;
                this.f8845c = bVar;
                this.f8846d = cVar;
            }

            @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence charSequence) {
                String strA;
                d dVar = this.f8843a;
                b.a.k.d<ce> dVar2 = this.f8844b;
                if (charSequence == null || (strA = charSequence.toString()) == null) {
                    strA = this.f8845c.a(R.string.word_unknown);
                }
                dVar.a(dVar2, strA, false);
            }

            @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationFailed() {
                this.f8843a.a(this.f8844b, "Not Recognized", true);
            }

            @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationHelp(int i, CharSequence charSequence) {
                String string;
                d dVar = this.f8843a;
                b.a.k.d<ce> dVar2 = this.f8844b;
                if (charSequence == null || (string = charSequence.toString()) == null) {
                    string = "Something's wrong with the fingerprint sensor";
                }
                dVar.a(dVar2, string, true);
            }

            @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
                this.f8846d.a(this.f8844b);
            }
        }

        static final class g<T> implements b.a.d.f<ce> {
            g() {
            }

            @Override // b.a.d.f
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final void accept(ce ceVar) {
                c.f.b.k.b(ceVar, "it");
                ActionBiometricDialog.this.cancellationSignal = (CancellationSignal) null;
            }
        }

        static final class h<T, R> implements b.a.d.g<T, R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final h f8848a = new h();

            h() {
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cp<ce> apply(ce ceVar) {
                c.f.b.k.b(ceVar, "it");
                return new cp<>(ceVar);
            }
        }
    }

    @Override // com.joaomgcd.taskerm.util.q
    protected b.a.l<ce> b(m mVar) {
        c.f.b.k.b(mVar, "args");
        if (ah.n(mVar.a())) {
            b.a.l lVarC = new ActionBiometricDialog(mVar.b(), mVar.c(), mVar.d(), mVar.e(), mVar.f(), mVar.g(), mVar.h()).run(mVar.a()).c(a.f8849a);
            c.f.b.k.a((Object) lVarC, "ActionBiometricDialog(ar…ialog>).payload\n        }");
            return lVarC;
        }
        b.a.l<ce> lVarB = b.a.l.b(new RuntimeException("Device doesn't support biometric authentication"));
        c.f.b.k.a((Object) lVarB, "Single.error(RuntimeExce…ometric authentication\"))");
        return lVarB;
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8849a = new a();

        a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ce apply(cl clVar) {
            c.f.b.k.b(clVar, "it");
            return (ce) ((cp) clVar).c();
        }
    }
}
