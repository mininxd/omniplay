package com.joaomgcd.taskerm.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.action.i;
import com.joaomgcd.taskerm.action.k;
import com.joaomgcd.taskerm.helper.r;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.aq;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class c implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f7998a;

    public enum a {
        None,
        Off,
        TurningOn,
        On,
        TurningOff,
        Card
    }

    private final String i() {
        return "NFCTasker";
    }

    static final class b extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Tag f8006b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8007c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f8008d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Tag tag, c.f.a.b bVar, boolean z) {
            super(0);
            this.f8006b = tag;
            this.f8007c = bVar;
            this.f8008d = z;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.nfc.c$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.b<Intent, s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ NFCTagTasker f8009a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(NFCTagTasker nFCTagTasker) {
                super(1);
                this.f8009a = nFCTagTasker;
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(Intent intent) {
                a(intent);
                return s.f2131a;
            }

            public final void a(Intent intent) {
                c.f.b.k.b(intent, "receiver$0");
                intent.putExtra("nfctvalue", this.f8009a.getIdString());
                intent.putExtra("nfctpayload", this.f8009a.getPayload());
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            b.a.b bVar;
            NFCTagTasker nFCTagTasker = new NFCTagTasker(this.f8006b);
            com.joaomgcd.taskerm.nfc.b bVar2 = new com.joaomgcd.taskerm.nfc.b(nFCTagTasker);
            c.f.a.b bVar3 = this.f8007c;
            if (bVar3 != null && (bVar = (b.a.b) bVar3.invoke(bVar2)) != null) {
                bVar.b();
            }
            if (this.f8008d) {
                r.a.a(r.f7464c, c.this.g(), "net.dinglisch.android.tasker.NFCTSCN", 0, new AnonymousClass1(nFCTagTasker), 4, null);
            }
            c.this.a(bVar2);
        }
    }

    public c(Context context) {
        c.f.b.k.b(context, "context");
        this.f7998a = context;
    }

    @Override // com.joaomgcd.taskerm.action.k
    public b.a.b a(boolean z, long j, long j2, long j3) {
        return k.a.a(this, z, j, j2, j3);
    }

    public final Context g() {
        return this.f7998a;
    }

    private final NfcAdapter h() {
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this.f7998a);
        if (defaultAdapter == null) {
            NfcManager nfcManagerP = ah.P(this.f7998a);
            defaultAdapter = nfcManagerP != null ? nfcManagerP.getDefaultAdapter() : null;
        }
        if (defaultAdapter != null) {
            return defaultAdapter;
        }
        throw new RuntimeException("NfcAdapter not present");
    }

    private final aq<NfcAdapter> a(String str) {
        return Cdo.a((Object) h(), str, (String) null, false, 6, (Object) null);
    }

    private final <R> R b(String str) {
        c.f.b.k.b(str, "receiver$0");
        return (R) a(str).a();
    }

    @Override // com.joaomgcd.taskerm.action.k
    public boolean a() {
        try {
            return h().isEnabled() || b() == a.TurningOn;
        } catch (Exception unused) {
            return false;
        }
    }

    public final a b() {
        return (a) ((Enum) aj.a(((Number) b("getAdapterState")).intValue(), a.class));
    }

    public final boolean c() {
        return ((Boolean) b("enable")).booleanValue();
    }

    public final boolean d() {
        return ((Boolean) b(be.DISABLE_LABEL)).booleanValue();
    }

    @Override // com.joaomgcd.taskerm.action.k
    public cl a(boolean z, i iVar) {
        c.f.b.k.b(iVar, "input");
        try {
            if (z) {
                c();
            } else {
                d();
            }
            return new co();
        } catch (Exception e2) {
            Exception exc = e2;
            bn.c(i(), ai.a(exc));
            return new cm(exc);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ b.a.b a(c cVar, Intent intent, boolean z, c.f.a.b bVar, int i, Object obj) {
        if ((i & 4) != 0) {
            bVar = (c.f.a.b) null;
        }
        return cVar.a(intent, z, bVar);
    }

    public final b.a.b a(Intent intent, boolean z, c.f.a.b<? super com.joaomgcd.taskerm.nfc.b, ? extends b.a.b> bVar) {
        Tag tagA;
        if (intent == null || (tagA = a(intent)) == null) {
            b.a.b bVarA = b.a.b.a();
            c.f.b.k.a((Object) bVarA, "Completable.complete()");
            return bVarA;
        }
        return com.joaomgcd.taskerm.rx.i.d(new b(tagA, bVar, z));
    }

    public final void e() {
        a(new com.joaomgcd.taskerm.nfc.b(null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(com.joaomgcd.taskerm.nfc.b bVar) {
        d.f8011a = bVar;
        com.joaomgcd.taskerm.rx.b.c(bVar);
    }

    public final void a(Activity activity) {
        c.f.b.k.b(activity, "activity");
        Activity activity2 = activity;
        h().enableForegroundDispatch(activity, PendingIntent.getActivity(activity2, 0, new Intent(activity2, activity.getClass()).addFlags(536870912), 0), null, null);
    }

    public final void b(Activity activity) {
        c.f.b.k.b(activity, "activity");
        h().disableForegroundDispatch(activity);
    }

    private final Tag a(Intent intent) {
        if (intent != null) {
            return (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
        }
        return null;
    }

    public final b.a.b a(String str, f fVar) {
        NFCTagTasker nFCTagTaskerA;
        c.f.b.k.b(fVar, "type");
        com.joaomgcd.taskerm.nfc.b bVar = d.f8011a;
        if (bVar == null || (nFCTagTaskerA = bVar.a()) == null) {
            b.a.b bVarA = b.a.b.a((Throwable) new RuntimeException("Tag not present to write"));
            c.f.b.k.a((Object) bVarA, "Completable.error(Runtim…g not present to write\"))");
            return bVarA;
        }
        b.a.b bVarA2 = nFCTagTaskerA.write(str, fVar).a((b.a.d.f<? super Throwable>) C0205c.f8010a);
        c.f.b.k.a((Object) bVarA2, "tagTasker.write(text, ty…tScanned = null\n        }");
        return bVarA2;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.nfc.c$c, reason: collision with other inner class name */
    static final class C0205c<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0205c f8010a = new C0205c();

        C0205c() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            d.f8011a = (com.joaomgcd.taskerm.nfc.b) null;
        }
    }

    public final com.joaomgcd.taskerm.nfc.b f() {
        return d.f8011a;
    }
}
