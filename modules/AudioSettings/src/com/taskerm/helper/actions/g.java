package com.joaomgcd.taskerm.helper.actions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.Spinner;
import c.f.b.k;
import c.f.b.l;
import c.l.n;
import c.s;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.nfc.ActivityNFCTag;
import com.joaomgcd.taskerm.nfc.NFCTagTasker;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.am;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class g extends com.joaomgcd.taskerm.helper.i<com.joaomgcd.taskerm.event.b<?, ?, ?, ?>, an, al, com.joaomgcd.taskerm.event.a, EventEdit> {
    @Override // com.joaomgcd.taskerm.helper.i
    public boolean h(int i, int i2) {
        return false;
    }

    static final class b extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f7218b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ EditText[] f7219c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f7220d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(c.f.a.b bVar, EditText[] editTextArr, int i) {
            super(0);
            this.f7218b = bVar;
            this.f7219c = editTextArr;
            this.f7220d = i;
        }

        static final class a extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f7221a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b f7222b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(String str, b bVar) {
                super(0);
                this.f7221a = str;
                this.f7222b = bVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                this.f7222b.f7219c[this.f7222b.f7220d].setText(this.f7221a);
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            String str;
            NFCTagTasker nFCTagTaskerA = ActivityNFCTag.f7974b.a(g.this.c()).b().a();
            if (nFCTagTaskerA == null || (str = (String) this.f7218b.invoke(nFCTagTaskerA)) == null) {
                return;
            }
            com.joaomgcd.taskerm.rx.i.e(new a(str, this));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(EventEdit eventEdit) {
        super(eventEdit);
        k.b(eventEdit, "activity");
    }

    @Override // com.joaomgcd.taskerm.helper.i
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.event.a f() {
        return com.joaomgcd.taskerm.event.h.a();
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.joaomgcd.taskerm.helper.l, com.joaomgcd.taskerm.helper.l<net.dinglisch.android.taskerm.an, ?, net.dinglisch.android.taskerm.EventEdit>] */
    @Override // com.joaomgcd.taskerm.helper.i
    public com.joaomgcd.taskerm.helper.l<an, ?, EventEdit> a(EventEdit eventEdit, com.joaomgcd.taskerm.event.b<?, ?, ?, ?> bVar) {
        k.b(eventEdit, "receiver$0");
        k.b(bVar, "configurable");
        return bVar.a(eventEdit);
    }

    public final void a(Spinner[] spinnerArr, EditText[] editTextArr) {
        k.b(spinnerArr, "argSpinners");
        k.b(editTextArr, "argEditTextViews");
        com.joaomgcd.taskerm.helper.h.a(this, spinnerArr, editTextArr, false, null, 8, null);
    }

    private final void a(EditText[] editTextArr, int i, c.f.a.b<? super NFCTagTasker, String> bVar) {
        a(new b(bVar, editTextArr, i));
    }

    static final class c extends l implements c.f.a.b<NFCTagTasker, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f7223a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(NFCTagTasker nFCTagTasker) {
            k.b(nFCTagTasker, "receiver$0");
            return nFCTagTasker.getIdString();
        }
    }

    static final class d extends l implements c.f.a.b<NFCTagTasker, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f7224a = new d();

        d() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(NFCTagTasker nFCTagTasker) {
            k.b(nFCTagTasker, "receiver$0");
            return nFCTagTasker.getPayload();
        }
    }

    public final void a(EditText[] editTextArr) {
        k.b(editTextArr, "argEditTextViews");
        a(editTextArr, 0, c.f7223a);
    }

    public final void b(EditText[] editTextArr) {
        k.b(editTextArr, "argEditTextViews");
        a(editTextArr, 1, d.f7224a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean v() {
        return k.a((Object) Settings.Secure.getString(((EventEdit) c()).getContentResolver(), "assist_gesture_any_assistant"), (Object) "1");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.app.Activity] */
    public final void w() {
        String str = Build.MODEL;
        if (str == null || !n.b((CharSequence) str, (CharSequence) "Pixel", false, 2, (Object) null) || v()) {
            return;
        }
        if (bt.a.h(bt.f9101c, t(), 0, 2, null).g()) {
            Settings.Secure.putString(((EventEdit) c()).getContentResolver(), "assist_gesture_any_assistant", "1");
        } else {
            a(y.a((Activity) c(), R.string.en_assist, R.string.dc_pixel_squeeze_assist, (com.joaomgcd.taskerm.util.h) null, 8, (Object) null), new a());
        }
    }

    static final class a<T> implements b.a.d.f<x> {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(x xVar) {
            k.b(xVar, "result");
            if (xVar.d()) {
                ah.a((Context) g.this.c(), "https://www.xda-developers.com/android-q-google-pixel-2-pixel-3-remap-active-edge/", false, false, false, 14, (Object) null);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.app.Activity] */
    public final void x() {
        com.joaomgcd.taskerm.helper.j.a(this, y.a((Activity) c(), R.string.en_volume_long_press, R.string.dc_volume_long_press_may_not_work, 0, false, (String) null, 56, (Object) null), (b.a.d.f) null, 2, (Object) null);
    }

    @Override // com.joaomgcd.taskerm.helper.i
    public String d(int i, int i2) {
        return am.b(i, i2);
    }
}
