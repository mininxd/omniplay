package com.joaomgcd.taskerm.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import c.f.b.v;
import c.f.b.w;
import c.f.b.x;
import c.q;
import c.s;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import java.util.ArrayList;
import java.util.HashMap;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.MyActivity;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.dn;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityPickLocation extends MyActivity implements com.joaomgcd.taskerm.helper.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7623a = {x.a(new v(x.a(ActivityPickLocation.class), "helper", "getHelper()Lcom/joaomgcd/taskerm/location/HelperActivityPickLocation;")), x.a(new v(x.a(ActivityPickLocation.class), "locationGetter", "getLocationGetter()Lcom/joaomgcd/taskerm/location/LocationGetter;")), x.a(new v(x.a(ActivityPickLocation.class), "args", "getArgs()Lcom/joaomgcd/taskerm/location/ArgsPickLocation;")), x.a(new v(x.a(ActivityPickLocation.class), "shouldSelectRadius", "getShouldSelectRadius()Z"))};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Location f7628f;
    private Integer g;
    private HashMap i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7624b = c.f.a(new b());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7625c = c.f.a(new g());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7626d = c.f.a(new a());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f7627e = c.f.a(new k());
    private int h = 10;

    private final com.joaomgcd.taskerm.location.f e() {
        c.e eVar = this.f7624b;
        c.j.g gVar = f7623a[0];
        return (com.joaomgcd.taskerm.location.f) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.joaomgcd.taskerm.location.j f() {
        c.e eVar = this.f7625c;
        c.j.g gVar = f7623a[1];
        return (com.joaomgcd.taskerm.location.j) eVar.b();
    }

    public final com.joaomgcd.taskerm.location.b a() {
        c.e eVar = this.f7626d;
        c.j.g gVar = f7623a[2];
        return (com.joaomgcd.taskerm.location.b) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.helper.b
    public void a(bt btVar, cm cmVar) {
        c.f.b.k.b(btVar, "permissions");
        c.f.b.k.b(cmVar, "result");
    }

    public View b(int i2) {
        if (this.i == null) {
            this.i = new HashMap();
        }
        View view = (View) this.i.get(Integer.valueOf(i2));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i2);
        this.i.put(Integer.valueOf(i2), viewFindViewById);
        return viewFindViewById;
    }

    public final boolean b() {
        c.e eVar = this.f7627e;
        c.j.g gVar = f7623a[3];
        return ((Boolean) eVar.b()).booleanValue();
    }

    static final class c extends c.f.b.l implements c.f.a.a<s> {
        c() {
            super(0);
        }

        static final class a extends c.f.b.l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ com.google.android.gms.maps.c f7633b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(com.google.android.gms.maps.c cVar) {
                super(0);
                this.f7633b = cVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                MapType mapTypeD;
                com.joaomgcd.taskerm.location.b bVarA = ActivityPickLocation.this.a();
                if (bVarA == null || (mapTypeD = bVarA.d()) == null) {
                    return;
                }
                com.google.android.gms.maps.c cVar = this.f7633b;
                c.f.b.k.a((Object) cVar, "map");
                cVar.a(mapTypeD.getTypeValue());
            }
        }

        static final class b extends c.f.b.l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ com.google.android.gms.maps.c f7634a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ c f7635b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.location.h f7636c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ com.google.android.gms.maps.c f7637d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ Location f7638e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final /* synthetic */ Integer[] f7639f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(com.google.android.gms.maps.c cVar, c cVar2, com.joaomgcd.taskerm.location.h hVar, com.google.android.gms.maps.c cVar3, Location location, Integer[] numArr) {
                super(0);
                this.f7634a = cVar;
                this.f7635b = cVar2;
                this.f7636c = hVar;
                this.f7637d = cVar3;
                this.f7638e = location;
                this.f7639f = numArr;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                this.f7634a.b(true);
                com.google.android.gms.maps.i iVarC = this.f7634a.c();
                c.f.b.k.a((Object) iVarC, "uiSettings");
                iVarC.c(true);
                com.joaomgcd.taskerm.location.h hVar = this.f7636c;
                if (hVar != null) {
                    ActivityPickLocation activityPickLocation = ActivityPickLocation.this;
                    com.google.android.gms.maps.c cVar = this.f7637d;
                    c.f.b.k.a((Object) cVar, "map");
                    activityPickLocation.a(cVar, hVar);
                }
                Location location = this.f7638e;
                if (location != null) {
                    p.a(this.f7634a, new com.joaomgcd.taskerm.location.i(location, 16.0f));
                }
                this.f7634a.a(new com.google.android.gms.maps.c.b() { // from class: com.joaomgcd.taskerm.location.ActivityPickLocation.c.b.1
                    @Override // com.google.android.gms.maps.c.b
                    public final void a(LatLng latLng) {
                        c.f.b.k.a((Object) latLng, "it");
                        Location locationA = p.a(latLng);
                        Integer num = ActivityPickLocation.this.g;
                        int iIntValue = num != null ? num.intValue() : ActivityPickLocation.this.c(10);
                        ActivityPickLocation activityPickLocation2 = ActivityPickLocation.this;
                        com.google.android.gms.maps.c cVar2 = b.this.f7637d;
                        c.f.b.k.a((Object) cVar2, "map");
                        activityPickLocation2.a(cVar2, new com.joaomgcd.taskerm.location.h(locationA, Integer.valueOf(iIntValue)));
                    }
                });
                Spinner spinner = (Spinner) ActivityPickLocation.this.b(dn.a.spinner_radius);
                c.f.b.k.a((Object) spinner, "spinner_radius");
                ActivityPickLocation activityPickLocation2 = ActivityPickLocation.this;
                Integer[] numArr = this.f7639f;
                ArrayList arrayList = new ArrayList(numArr.length);
                for (Integer num : numArr) {
                    arrayList.add(String.valueOf(num.intValue()));
                }
                Object[] array = arrayList.toArray(new String[0]);
                if (array != null) {
                    spinner.setAdapter((SpinnerAdapter) gr.a((Context) activityPickLocation2, (String[]) array));
                    ((Spinner) ActivityPickLocation.this.b(dn.a.spinner_radius)).setSelection(c.a.d.c(this.f7639f, Integer.valueOf(ActivityPickLocation.this.h)));
                    final w.a aVar = new w.a();
                    aVar.f2025a = false;
                    Spinner spinner2 = (Spinner) ActivityPickLocation.this.b(dn.a.spinner_radius);
                    c.f.b.k.a((Object) spinner2, "spinner_radius");
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.joaomgcd.taskerm.location.ActivityPickLocation.c.b.2
                        @Override // android.widget.AdapterView.OnItemSelectedListener
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }

                        @Override // android.widget.AdapterView.OnItemSelectedListener
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                            Integer num2;
                            if (aVar.f2025a) {
                                Location location2 = ActivityPickLocation.this.f7628f;
                                if (location2 == null || (num2 = ActivityPickLocation.this.g) == null) {
                                    return;
                                }
                                int iD = ActivityPickLocation.this.d(num2.intValue());
                                ActivityPickLocation.this.h = b.this.f7639f[i].intValue();
                                com.joaomgcd.taskerm.location.h hVar2 = new com.joaomgcd.taskerm.location.h(location2, Integer.valueOf(ActivityPickLocation.this.c(iD)));
                                ActivityPickLocation activityPickLocation3 = ActivityPickLocation.this;
                                com.google.android.gms.maps.c cVar2 = b.this.f7637d;
                                c.f.b.k.a((Object) cVar2, "map");
                                activityPickLocation3.a(cVar2, hVar2);
                                ActivityPickLocation activityPickLocation4 = ActivityPickLocation.this;
                                SeekBar seekBar = (SeekBar) ActivityPickLocation.this.b(dn.a.seek_radius);
                                c.f.b.k.a((Object) seekBar, "seek_radius");
                                activityPickLocation4.a(seekBar.getProgress());
                                return;
                            }
                            aVar.f2025a = true;
                        }
                    });
                    return;
                }
                throw new q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0032 A[Catch: Throwable -> 0x005c, TRY_LEAVE, TryCatch #0 {Throwable -> 0x005c, blocks: (B:7:0x002b, B:10:0x0032), top: B:31:0x002b }] */
        public final void a() {
            Location locationA;
            Location location;
            com.google.android.gms.maps.c cVar = (com.google.android.gms.maps.c) ActivityPickLocation.this.g().b();
            com.joaomgcd.taskerm.rx.i.e(new a(cVar));
            com.joaomgcd.taskerm.location.b bVarA = ActivityPickLocation.this.a();
            Integer num = null;
            com.joaomgcd.taskerm.location.h hVarC = bVarA != null ? bVarA.c() : null;
            if (hVarC == null) {
                locationA = ActivityPickLocation.this.f().a(new com.joaomgcd.taskerm.location.j.a(300, null, null, null, null, null, null, null, 254, null)).b();
            } else {
                try {
                    locationA = hVarC.a();
                    if (locationA == null) {
                        locationA = ActivityPickLocation.this.f().a(new com.joaomgcd.taskerm.location.j.a(300, null, null, null, null, null, null, null, 254, null)).b();
                    }
                } catch (Throwable unused) {
                    location = null;
                }
            }
            location = locationA;
            Integer[] numArr = {1, 10, 100, 1000, 10000, Integer.valueOf(be.SCENE_JSI_ID_START)};
            if ((hVarC != null ? hVarC.f() : null) != null) {
                for (Integer num2 : numArr) {
                    if (c.f.b.k.a(num2.intValue() * 100, hVarC.f().intValue()) >= 0) {
                        num = num2;
                        break;
                    }
                }
                if (num != null) {
                    ActivityPickLocation.this.h = num.intValue();
                }
            }
            com.joaomgcd.taskerm.rx.i.e(new b(cVar, this, hVarC, cVar, location, numArr));
        }
    }

    static final class k extends c.f.b.l implements c.f.a.a<Boolean> {
        k() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            Boolean boolB;
            com.joaomgcd.taskerm.location.b bVarA = ActivityPickLocation.this.a();
            if (bVarA == null || (boolB = bVarA.b()) == null) {
                return false;
            }
            return boolB.booleanValue();
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.location.f> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.location.f invoke() {
            return new com.joaomgcd.taskerm.location.f(ActivityPickLocation.this);
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.location.j> {
        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.location.j invoke() {
            return new com.joaomgcd.taskerm.location.j(ActivityPickLocation.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.l<com.google.android.gms.maps.c> g() {
        MapView mapView = (MapView) b(dn.a.mapview);
        c.f.b.k.a((Object) mapView, "mapview");
        return p.a(mapView);
    }

    @Override // net.dinglisch.android.taskerm.MyActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pick_location);
        ((MapView) b(dn.a.mapview)).a(bundle);
        bt btVarE = bt.a.e(bt.f9101c, this, 0, 2, null);
        if (!btVarE.g()) {
            e().a(bt.b(btVarE, this, null, 2, null), new h(), new i());
        } else {
            c();
        }
    }

    static final class h<T> implements b.a.d.f<cl> {
        h() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(cl clVar) {
            c.f.b.k.b(clVar, "it");
            if (!clVar.b()) {
                ActivityPickLocation.this.finish();
            } else {
                ActivityPickLocation.this.c();
            }
        }
    }

    static final class i<T> implements b.a.d.f<Throwable> {
        i() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            ActivityPickLocation.this.finish();
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.location.b> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.location.b invoke() {
            String stringExtra = ActivityPickLocation.this.getIntent().getStringExtra("args");
            if (stringExtra != null) {
                return (com.joaomgcd.taskerm.location.b) com.joaomgcd.taskerm.n.b.a().a(stringExtra, com.joaomgcd.taskerm.location.b.class);
            }
            return null;
        }
    }

    public final void c() {
        String strA;
        com.joaomgcd.taskerm.location.b bVarA = a();
        setTitle((bVarA == null || (strA = bVarA.a()) == null) ? aj.a(R.string.pick_location, this, new Object[0]) : strA);
        Button button = (Button) b(dn.a.button_ok);
        c.f.b.k.a((Object) button, "button_ok");
        button.setEnabled(false);
        com.joaomgcd.taskerm.rx.i.c(new c());
        ((Button) b(dn.a.button_ok)).setOnClickListener(new d());
        ((Button) b(dn.a.button_cancel)).setOnClickListener(new e());
        ((SeekBar) b(dn.a.seek_radius)).setOnSeekBarChangeListener(new f());
    }

    static final class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Location location = ActivityPickLocation.this.f7628f;
            if (location == null) {
                ak.b("Please pick a location", ActivityPickLocation.this);
                return;
            }
            ActivityPickLocation activityPickLocation = ActivityPickLocation.this;
            Intent intent = new Intent();
            intent.putExtra("location", location);
            intent.putExtra("radius", ActivityPickLocation.this.g);
            activityPickLocation.setResult(-1, intent);
            ActivityPickLocation.this.finish();
        }
    }

    static final class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ActivityPickLocation.this.setResult(0);
            ActivityPickLocation.this.finish();
        }
    }

    public static final class f implements SeekBar.OnSeekBarChangeListener {
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        f() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                ActivityPickLocation.this.a(i);
            }
        }
    }

    public final void a(int i2) {
        Location location;
        if (b() && (location = this.f7628f) != null) {
            e().a(g(), new j(i2, location));
        }
    }

    static final class j<T> implements b.a.d.f<com.google.android.gms.maps.c> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7650b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Location f7651c;

        j(int i, Location location) {
            this.f7650b = i;
            this.f7651c = location;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(com.google.android.gms.maps.c cVar) {
            c.f.b.k.b(cVar, "it");
            int iC = ActivityPickLocation.this.c(this.f7650b);
            com.joaomgcd.taskerm.location.e eVar = new com.joaomgcd.taskerm.location.e(this.f7651c, iC);
            p.a(cVar, eVar);
            ActivityPickLocation.this.g = Integer.valueOf(iC);
            p.a(cVar, eVar.b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(com.google.android.gms.maps.c cVar, com.joaomgcd.taskerm.location.h hVar) {
        this.f7628f = hVar.a();
        p.b(cVar, hVar.a());
        Integer numF = hVar.f();
        int iIntValue = numF != null ? numF.intValue() : c(10);
        this.g = Integer.valueOf(iIntValue);
        if (b()) {
            p.a(cVar, new com.joaomgcd.taskerm.location.e(hVar.a(), iIntValue));
        }
        Button button = (Button) b(dn.a.button_ok);
        c.f.b.k.a((Object) button, "button_ok");
        button.setEnabled(true);
        a(Integer.valueOf(d(iIntValue)));
    }

    private final void a(Integer num) {
        LinearLayout linearLayout = (LinearLayout) b(dn.a.radius_selection);
        c.f.b.k.a((Object) linearLayout, "radius_selection");
        linearLayout.setVisibility(b() ? 0 : 8);
        if (b()) {
            SeekBar seekBar = (SeekBar) b(dn.a.seek_radius);
            c.f.b.k.a((Object) seekBar, "seek_radius");
            seekBar.setProgress(num != null ? num.intValue() : 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int c(int i2) {
        return i2 * this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int d(int i2) {
        return i2 / this.h;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        c.f.b.k.b(bundle, "outState");
        super.onSaveInstanceState(bundle);
        ((MapView) b(dn.a.mapview)).b(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        ((MapView) b(dn.a.mapview)).f();
    }

    @Override // net.dinglisch.android.taskerm.MyActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        ((MapView) b(dn.a.mapview)).d();
    }

    @Override // net.dinglisch.android.taskerm.MyActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ((MapView) b(dn.a.mapview)).a();
    }

    @Override // net.dinglisch.android.taskerm.MyActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        ((MapView) b(dn.a.mapview)).b();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        ((MapView) b(dn.a.mapview)).c();
    }

    @Override // net.dinglisch.android.taskerm.MyActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ((MapView) b(dn.a.mapview)).e();
        MapView mapView = (MapView) b(dn.a.mapview);
        c.f.b.k.a((Object) mapView, "mapview");
        p.b(mapView);
    }
}
