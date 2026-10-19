package com.joaomgcd.taskerm.datashare.export;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import c.f.a.m;
import c.f.b.p;
import c.f.b.v;
import c.f.b.x;
import c.l.n;
import c.q;
import c.s;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.as;
import com.joaomgcd.taskerm.dialog.w;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerservercommon.datashare.DataShareTag;
import com.joaomgcd.taskerservercommon.response.ResponseGetTags;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.HTMLView;
import net.dinglisch.android.taskerm.HasArgsEdit;
import net.dinglisch.android.taskerm.MyCheckBox;
import net.dinglisch.android.taskerm.ay;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.cs;
import net.dinglisch.android.taskerm.de;
import net.dinglisch.android.taskerm.dl;
import net.dinglisch.android.taskerm.fi;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class ShareDataConfig extends HasArgsEdit {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6085a = {x.a(new v(x.a(ShareDataConfig.class), "coroutineScope", "getCoroutineScope()Lcom/joaomgcd/taskerm/coroutines/ActivityScope;")), x.a(new p(x.a(ShareDataConfig.class), "userAgreedToConditions", "getUserAgreedToConditions()Z")), x.a(new v(x.a(ShareDataConfig.class), "apiClient", "getApiClient()Lcom/joaomgcd/taskerm/datashare/export/APIDataShare;"))};
    private fx O;
    private dl P;
    private com.joaomgcd.taskerm.datashare.export.e Q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ay f6087c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f6086b = c.f.a(b.f6089a);
    private final com.joaomgcd.taskerm.q.d R = new com.joaomgcd.taskerm.q.d(this, false, null, "userAgreedToShareConditions", 4, null);
    private final c.e S = c.f.a(new a());
    private final int T = 100;

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(boolean z) {
        this.R.a(this, f6085a[1], Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean g() {
        return this.R.a(this, f6085a[1]).booleanValue();
    }

    public final com.joaomgcd.taskerm.datashare.export.a a() {
        c.e eVar = this.S;
        c.j.g gVar = f6085a[2];
        return (com.joaomgcd.taskerm.datashare.export.a) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.helper.b
    public void a(bt btVar, cm cmVar) {
        c.f.b.k.b(btVar, "permissions");
        c.f.b.k.b(cmVar, "result");
    }

    @Override // net.dinglisch.android.taskerm.HasArgsEdit, android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    static final class g extends c.f.b.l implements c.f.a.a<List<? extends String>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f6103b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(List list) {
            super(0);
            this.f6103b = list;
        }

        public static final class a<T> implements Comparator<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f6105a;

            public a(AnonymousClass1 anonymousClass1) {
                this.f6105a = anonymousClass1;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return c.b.a.a(Boolean.valueOf(!this.f6105a.a((String) t)), Boolean.valueOf(!this.f6105a.a((String) t2)));
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.ShareDataConfig$g$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<String, Boolean> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ Boolean invoke(String str) {
                return Boolean.valueOf(a(str));
            }

            public final boolean a(String str) {
                c.f.b.k.b(str, "receiver$0");
                return g.this.f6103b.contains(str);
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<String> invoke() {
            List<DataShareTag> tags;
            List<String> listB;
            ac acVarA = ac.a.a(ac.f6217a, ShareDataConfig.this, R.string.getting_tags_from_server, false, 4, null);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            ResponseGetTags responseGetTagsB = ShareDataConfig.this.a().a().b();
            if (responseGetTagsB != null && (tags = responseGetTagsB.getTags()) != null) {
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = tags.iterator();
                while (it.hasNext()) {
                    String id = ((DataShareTag) it.next()).getId();
                    if (id != null) {
                        arrayList.add(id);
                    }
                }
                List listA = c.a.j.a((Iterable) arrayList, (Comparator) new a(anonymousClass1));
                if (listA != null) {
                    ac.a(acVarA, null, 1, null);
                    as asVar = (as) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.e(ShareDataConfig.this, aj.a(R.string.pl_tags, ShareDataConfig.this), listA, false, new b(anonymousClass1), null, aj.b(R.string.button_label_cancel, ShareDataConfig.this), null, null, null, true, 3, null, 5024, null)).b();
                    if (asVar == null || (listB = asVar.b()) == null) {
                        throw new RuntimeException("Didn't select any tags");
                    }
                    if (listB.size() <= 3) {
                        return listB;
                    }
                    throw new RuntimeException("Too many tags selected. Maximum is 3");
                }
            }
            throw new RuntimeException("Couldn't get tags from server");
        }

        static final class b extends c.f.b.l implements c.f.a.b<String, w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f6106a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(AnonymousClass1 anonymousClass1) {
                super(1);
                this.f6106a = anonymousClass1;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final w invoke(String str) {
                c.f.b.k.b(str, "it");
                return new w(str, null, this.f6106a.a(str), 2, null);
            }
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Bundle f6093b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Bundle bundle) {
            super(0);
            this.f6093b = bundle;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.ShareDataConfig$d$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ GoogleSignInAccount f6095b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(GoogleSignInAccount googleSignInAccount) {
                super(0);
                this.f6095b = googleSignInAccount;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.ShareDataConfig$d$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01191 extends c.f.b.l implements c.f.a.a<s> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ Throwable f6097b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01191(Throwable th) {
                    super(0);
                    this.f6097b = th;
                }

                @Override // c.f.a.a
                public /* synthetic */ s invoke() {
                    a();
                    return s.f2131a;
                }

                public final void a() {
                    gr.a(ShareDataConfig.this, this.f6097b.getMessage(), new Object[0]);
                }
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                try {
                    bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Showing sharing as");
                    ShareDataConfig shareDataConfig = ShareDataConfig.this;
                    GoogleSignInAccount googleSignInAccount = this.f6095b;
                    c.f.b.k.a((Object) googleSignInAccount, "googleSignInAccount");
                    shareDataConfig.a(googleSignInAccount);
                    bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Populating views");
                    ShareDataConfig.this.h();
                    bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Setting UI from project");
                    ShareDataConfig.this.z();
                    bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Setting help buttons from spec");
                    ShareDataConfig.this.b(ShareDataConfig.this, ShareDataConfig.this.f());
                    bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "oncreatedone doinmain");
                    ShareDataConfig.this.i.b(R.string.you_can_use_html_to_format_share_descriptions);
                } catch (Throwable th) {
                    bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Error sharing to link doinmain", th);
                    com.joaomgcd.taskerm.rx.i.e(new C01191(th));
                    ShareDataConfig.this.a("exception in main block doinmain");
                }
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.ShareDataConfig$d$2, reason: invalid class name */
        static final class AnonymousClass2 extends c.f.b.l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Throwable f6099b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(Throwable th) {
                super(0);
                this.f6099b = th;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                gr.a(ShareDataConfig.this, this.f6099b.getMessage(), new Object[0]);
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            Bundle bundle;
            try {
                if (!ShareDataConfig.this.g()) {
                    if (y.a(new com.joaomgcd.taskerm.dialog.c(ShareDataConfig.this, "disclaimer_share.html", HTMLView.c.Confirm)).b().d()) {
                        ShareDataConfig.this.c(true);
                    } else {
                        throw new com.joaomgcd.taskerm.signin.b("User didn't accept conditions to share");
                    }
                }
                GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) com.joaomgcd.taskerm.signin.c.a.a(com.joaomgcd.taskerm.signin.c.f8605a, ShareDataConfig.this, false, null, 6, null).b();
                ac acVarA = ac.a.a(ac.f6217a, ShareDataConfig.this, R.string.loading_apps_text, false, 4, null);
                try {
                    ShareDataConfig shareDataConfig = ShareDataConfig.this;
                    fx fxVarA = fx.a(ShareDataConfig.this);
                    bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Loading data from cache file");
                    try {
                        fxVarA.a((Context) ShareDataConfig.this, "ShareLink", com.joaomgcd.taskerm.datashare.export.f.c(), true);
                        bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Loaded data from cache file");
                        try {
                            if (this.f6093b == null) {
                                bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Loading extra data from intent");
                                bundle = ShareDataConfig.this.getIntent().getBundleExtra(com.joaomgcd.taskerm.datashare.export.f.a());
                            } else {
                                bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Loading extra data from icicle");
                                bundle = this.f6093b.getBundle(com.joaomgcd.taskerm.datashare.export.f.a());
                            }
                        } catch (Exception e2) {
                            bn.a(com.joaomgcd.taskerm.datashare.export.f.e(), "Can't get extra data for share", e2);
                            bundle = null;
                        }
                        if (bundle != null) {
                            ShareDataConfig.this.f6087c = fx.a(ShareDataConfig.this, bundle);
                            ShareDataConfig shareDataConfig2 = ShareDataConfig.this;
                            ay ayVar = ShareDataConfig.this.f6087c;
                            shareDataConfig2.Q = ayVar != null ? ayVar.e() : null;
                            ay ayVar2 = ShareDataConfig.this.f6087c;
                            if (ayVar2 != null && (ayVar2 instanceof dl)) {
                                Set<Integer> setQ = fxVarA.q();
                                c.f.b.k.a((Object) setQ, "macroIDSet");
                                Object[] array = setQ.toArray(new Integer[0]);
                                if (array != null) {
                                    for (Integer num : (Integer[]) array) {
                                        if (!((dl) ayVar2).q().contains(num)) {
                                            c.f.b.k.a((Object) num, "it");
                                            fxVarA.o(num.intValue());
                                        }
                                    }
                                } else {
                                    throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                                }
                            }
                        }
                        shareDataConfig.O = fxVarA;
                        ShareDataConfig shareDataConfig3 = ShareDataConfig.this;
                        fx fxVar = ShareDataConfig.this.O;
                        shareDataConfig3.P = fxVar != null ? fxVar.z(0) : null;
                        dl dlVar = ShareDataConfig.this.P;
                        if (dlVar != null) {
                            ay ayVar3 = ShareDataConfig.this.f6087c;
                            dlVar.a(ayVar3 != null ? ayVar3.l() : null);
                        }
                        ac.a(acVarA, null, 1, null);
                        com.joaomgcd.taskerm.rx.i.e(new AnonymousClass1(googleSignInAccount));
                    } catch (Throwable th) {
                        bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Couldn't load data from cache file", th);
                        ai.a(th, ShareDataConfig.this);
                        ShareDataConfig.this.a("Couldn't load data from cache file");
                        ac.a(acVarA, null, 1, null);
                        return;
                    }
                } catch (Throwable th2) {
                    ac.a(acVarA, null, 1, null);
                    throw th2;
                }
            } catch (Throwable th3) {
                bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Error sharing to link", th3);
                com.joaomgcd.taskerm.rx.i.e(new AnonymousClass2(th3));
                ShareDataConfig.this.a("exception in main block");
            }
            bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "oncreatedone doinbackground");
        }
    }

    static final class i implements View.OnClickListener {
        i() {
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.ShareDataConfig$i$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<List<? extends String>, s> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(List<? extends String> list) {
                a(list);
                return s.f2131a;
            }

            public final void a(List<String> list) {
                c.f.b.k.b(list, "it");
                ShareDataConfig.this.c(4, c.a.j.a(list, ",", null, null, 0, null, null, 62, null));
            }
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            List listA;
            String strB = ShareDataConfig.this.b(4);
            if (strB == null || (listA = n.b((CharSequence) strB, new String[]{","}, false, 0, 6, (Object) null)) == null) {
                listA = c.a.j.a();
            }
            com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.rx.i.c(ShareDataConfig.this.a((List<String>) listA)), ShareDataConfig.this, new AnonymousClass1());
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ GoogleSignInAccount f6091b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(GoogleSignInAccount googleSignInAccount) {
            super(0);
            this.f6091b = googleSignInAccount;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            gr.c(ShareDataConfig.this, "Sharing as " + this.f6091b.c(), new Object[0]);
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<s> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            try {
                GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) com.joaomgcd.taskerm.signin.c.a.a(com.joaomgcd.taskerm.signin.c.f8605a, ShareDataConfig.this, false, null, 4, null).b();
                ShareDataConfig shareDataConfig = ShareDataConfig.this;
                c.f.b.k.a((Object) googleSignInAccount, "account");
                shareDataConfig.a(googleSignInAccount);
            } catch (Exception e2) {
                com.joaomgcd.taskerm.rx.i.a(ShareDataConfig.this, e2);
            }
        }
    }

    static final class j extends c.f.b.l implements m<Integer, Boolean, s> {
        j() {
            super(2);
        }

        @Override // c.f.a.m
        public /* synthetic */ s invoke(Integer num, Boolean bool) {
            return a(num.intValue(), bool);
        }

        public final s a(int i, Boolean bool) {
            MyCheckBox myCheckBox = ShareDataConfig.this.z[i];
            if (myCheckBox == null) {
                return null;
            }
            myCheckBox.setCheckedNoSignal(bool != null ? bool.booleanValue() : false);
            return s.f2131a;
        }
    }

    static final class k extends c.f.b.l implements c.f.a.b<ae<fs>, s> {
        k() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(ae<fs> aeVar) {
            a(aeVar);
            return s.f2131a;
        }

        public final void a(ae<fs> aeVar) {
            c.f.b.k.b(aeVar, "it");
            fs fsVarB = aeVar.b();
            if (fsVarB != null) {
                ShareDataConfig.this.c(1, fsVarB.l());
            }
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.g.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6089a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.g.a invoke() {
            return new com.joaomgcd.taskerm.g.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final fi f() {
        return new fi(6, 1, Integer.valueOf(R.string.pl_description), "t:5", 0, -1, Integer.valueOf(R.string.dc_help_data_share_description), 1, Integer.valueOf(R.string.pl_launch_task), "m:1:?", 0, -1, Integer.valueOf(R.string.dc_help_link_launch_task), 3, Integer.valueOf(R.string.bypass_permissions), "", 0, 0, Integer.valueOf(R.string.dc_help_share_share_ask_permissions), 3, Integer.valueOf(R.string.pl_public), "", 0, 0, Integer.valueOf(R.string.dc_help_share_public), 1, Integer.valueOf(R.string.pl_tags), "t:1:?", 0, -1, Integer.valueOf(R.string.dc_help_share_tags));
    }

    @Override // net.dinglisch.android.taskerm.MyActivity, android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    public void onCreate(Bundle bundle) {
        super.a(bundle, R.layout.arglist);
        bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Started share link activity");
        com.joaomgcd.taskerm.rx.i.c(new d(bundle));
    }

    @Override // net.dinglisch.android.taskerm.HasArgsEdit, net.dinglisch.android.taskerm.MyActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        bn.b(com.joaomgcd.taskerm.datashare.export.f.e(), "Destroyed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(GoogleSignInAccount googleSignInAccount) {
        com.joaomgcd.taskerm.rx.i.e(new c(googleSignInAccount));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(String str) {
        setResult(0);
        bn.c(com.joaomgcd.taskerm.datashare.export.f.e(), "Cancelling export and finishing: " + str);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void h() {
        a(findViewById(R.id.content_scroller));
        b(f().e(), 66603);
        this.z[3].setOnCheckedChangeListener(new f());
    }

    static final class f implements CompoundButton.OnCheckedChangeListener {
        f() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            EditText editText = ShareDataConfig.this.f10753d[4];
            c.f.b.k.a((Object) editText, "argEditTextViews[ARG_TAGS]");
            editText.setHint(!z ? aj.a(R.string.word_optional, ShareDataConfig.this, new Object[0]) : "");
            if (z) {
                com.joaomgcd.taskerm.helper.j.a(ShareDataConfig.this.i, y.a(ShareDataConfig.this, R.string.public_shares_have_to_include_tags), (b.a.d.f) null, 2, (Object) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String b(int i2) {
        return gr.a((TextView) this.f10753d[i2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(int i2, String str) {
        if (str != null) {
            gr.b(this.f10753d[i2], str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void z() {
        String[] strArrE;
        boolean z;
        Iterator<Integer> it = c.i.d.b(0, f().e()).iterator();
        while (it.hasNext()) {
            int iB = ((c.a.y) it).b();
            switch (f().d(iB)) {
                case 0:
                    this.f10753d[iB].setLines(1);
                    EditText editText = this.f10753d[iB];
                    c.f.b.k.a((Object) editText, "argEditTextViews[a]");
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                    de.a((TextView) this.f10753d[iB], 2);
                    z = false;
                    break;
                case 1:
                    this.f10753d[iB].setLines(iB == 0 ? 15 : 1);
                    z = !net.dinglisch.android.taskerm.b.g(f().a(iB));
                    break;
                default:
                    z = false;
                    break;
            }
            int i2 = aj.a(Integer.valueOf(iB), 0, 1, 4) ? 0 : 8;
            int i3 = aj.a(Integer.valueOf(iB), 2, 3) ? 0 : 8;
            EditText editText2 = this.f10753d[iB];
            c.f.b.k.a((Object) editText2, "argEditTextViews[a]");
            editText2.setHint(z ? cs.a(this, R.string.word_optional, new Object[0]) : "");
            String strA = cs.a(this, f().g(iB), new Object[0]);
            c.f.b.k.a((Object) strA, "MyRes.getString(this, ARG_SPEC.getArgLabelID(a))");
            TextView textView = this.x[iB];
            c.f.b.k.a((Object) textView, "argLabels[a]");
            textView.setText(strA);
            TextView textView2 = this.x[iB];
            c.f.b.k.a((Object) textView2, "argLabels[a]");
            textView2.setVisibility(0);
            EditText editText3 = this.f10753d[iB];
            c.f.b.k.a((Object) editText3, "argEditTextViews[a]");
            editText3.setVisibility(i2);
            ImageButton imageButton = this.J[iB];
            c.f.b.k.a((Object) imageButton, "argChooseButtons[a]");
            imageButton.setVisibility(8);
            MyCheckBox myCheckBox = this.z[iB];
            c.f.b.k.a((Object) myCheckBox, "argCheckBoxes[a]");
            myCheckBox.setVisibility(i3);
        }
        fx fxVar = this.O;
        if ((fxVar != null && fxVar.n() == 0) || !(this.f6087c instanceof dl)) {
            ViewGroup viewGroup = this.u[1];
            c.f.b.k.a((Object) viewGroup, "argLayouts[ARG_LAUNCH_TASK_NAME]");
            viewGroup.setVisibility(8);
        } else {
            ImageButton imageButton2 = this.J[1];
            imageButton2.setVisibility(0);
            imageButton2.setOnClickListener(new h());
        }
        ImageButton imageButton3 = this.J[4];
        imageButton3.setVisibility(0);
        imageButton3.setOnClickListener(new i());
        ShareDataConfig shareDataConfig = this;
        Object[] objArr = new Object[1];
        dl dlVar = this.P;
        String strA2 = null;
        objArr[0] = dlVar != null ? dlVar.l() : null;
        setTitle(cs.a(shareDataConfig, R.string.dt_share, objArr));
        j jVar = new j();
        com.joaomgcd.taskerm.datashare.export.e eVar = this.Q;
        c(0, eVar != null ? eVar.b() : null);
        com.joaomgcd.taskerm.datashare.export.e eVar2 = this.Q;
        c(1, eVar2 != null ? eVar2.c() : null);
        com.joaomgcd.taskerm.datashare.export.e eVar3 = this.Q;
        jVar.a(2, eVar3 != null ? Boolean.valueOf(eVar3.a()) : null);
        com.joaomgcd.taskerm.datashare.export.e eVar4 = this.Q;
        jVar.a(3, eVar4 != null ? eVar4.d() : null);
        com.joaomgcd.taskerm.datashare.export.e eVar5 = this.Q;
        if (eVar5 != null && (strArrE = eVar5.e()) != null) {
            strA2 = c.a.d.a(strArrE, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null);
        }
        c(4, strA2);
    }

    static final class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ShareDataConfig.this.A();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void A() {
        ArrayList<fs> arrayListB;
        fx fxVar = this.O;
        if (fxVar == null || (arrayListB = fxVar.b(-2, fs.c.Alpha)) == null) {
            return;
        }
        com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.dialog.k.a(new ad(this, R.string.taskselect_title, arrayListB, true, l.f6112a, null, null, null, null, null, null, null, null, null, null, null, 65504, null)), this, new k());
    }

    static final class l extends c.f.b.l implements c.f.a.b<fs, w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final l f6112a = new l();

        l() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final w invoke(fs fsVar) {
            c.f.b.k.a((Object) fsVar, "it");
            String strL = fsVar.l();
            c.f.b.k.a((Object) strL, "it.name");
            return new w(strL, fsVar.C(), false, 4, null);
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.datashare.export.a> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.datashare.export.a invoke() {
            return com.joaomgcd.taskerm.datashare.export.a.f6113a.a(ShareDataConfig.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.l<List<String>> a(List<String> list) {
        return com.joaomgcd.taskerm.rx.i.b(new g(list));
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        c.f.b.k.b(menuItem, "item");
        if (menuItem.getItemId() == 6) {
            finish();
            return true;
        }
        if (menuItem.getItemId() == 16908332) {
            l_();
            return true;
        }
        if (menuItem.getItemId() == this.T) {
            com.joaomgcd.taskerm.rx.i.c(new e());
            return true;
        }
        return a(menuItem, (String) null, "sharecreation.html");
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        l_();
    }

    /* JADX WARN: Code duplicated, block: B:41:0x00ca  */
    private final com.joaomgcd.taskerm.datashare.export.e B() {
        String[] strArr;
        boolean z;
        List<String> listB;
        if (!a(this, f())) {
            return null;
        }
        String strA = gr.a((TextView) this.f10753d[0]);
        String strA2 = gr.a((TextView) this.f10753d[1]);
        MyCheckBox myCheckBox = this.z[2];
        Boolean boolValueOf = myCheckBox != null ? Boolean.valueOf(myCheckBox.isChecked()) : null;
        MyCheckBox myCheckBox2 = this.z[3];
        Boolean boolValueOf2 = myCheckBox2 != null ? Boolean.valueOf(myCheckBox2.isChecked()) : null;
        String strB = b(4);
        if (strB == null || (listB = n.b((CharSequence) strB, new String[]{","}, false, 0, 6, (Object) null)) == null) {
            strArr = null;
        } else {
            ArrayList arrayList = new ArrayList();
            for (String str : listB) {
                if (str != null) {
                    String strA3 = ak.A(n.b((CharSequence) str).toString());
                    if (strA3 != null) {
                        arrayList.add(strA3);
                    }
                } else {
                    throw new q("null cannot be cast to non-null type kotlin.CharSequence");
                }
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array == null) {
                throw new q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
        }
        if (c.f.b.k.a((Object) boolValueOf2, (Object) true)) {
            if (strArr != null) {
                z = strArr.length == 0;
            }
            if (z) {
                com.joaomgcd.taskerm.helper.j.a(this.i, y.d(this, R.string.pl_public, R.string.public_shares_have_to_include_tags), (c.f.a.b) null, 2, (Object) null);
                return null;
            }
        }
        return new com.joaomgcd.taskerm.datashare.export.e(strA, strA2, boolValueOf, boolValueOf2, strArr);
    }

    public final void l_() {
        com.joaomgcd.taskerm.datashare.export.e eVarB = B();
        if (eVarB != null) {
            a(eVarB);
            finish();
        }
    }

    private final void a(com.joaomgcd.taskerm.datashare.export.e eVar) {
        setResult(-1, new Intent().putExtra(com.joaomgcd.taskerm.datashare.export.f.b(), eVar.a(0).c()));
    }

    @Override // net.dinglisch.android.taskerm.HasArgsEdit
    public void a(int i2) {
        z();
    }

    @Override // net.dinglisch.android.taskerm.MyActivity, android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        c.f.b.k.b(menu, "menu");
        boolean zA = super.a(menu, R.string.ml_help_this_screen, -1);
        menu.add(0, this.T, 0, aj.a(R.string.pl_change_share_account, this, new Object[0]));
        return zA;
    }
}
