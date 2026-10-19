package com.joaomgcd.taskerm.datashare.p000import;

import android.app.Activity;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import c.e;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.s;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ay;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.gn;
import net.dinglisch.android.taskerm.go;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityPreviewTaskerDataFromUri extends ActivityImportTaskerDataBase {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f6202a = {x.a(new v(x.a(ActivityPreviewTaskerDataFromUri.class), "textPrimaryColor", "getTextPrimaryColor()I")), x.a(new v(x.a(ActivityPreviewTaskerDataFromUri.class), "backgroundColor", "getBackgroundColor()I"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f6203b = f.a(new c());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f6204c = f.a(new a());

    /* JADX INFO: Access modifiers changed from: private */
    public final int e() {
        e eVar = this.f6203b;
        g gVar = f6202a[0];
        return ((Number) eVar.b()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int f() {
        e eVar = this.f6204c;
        g gVar = f6202a[1];
        return ((Number) eVar.b()).intValue();
    }

    @Override // com.joaomgcd.taskerm.datashare.p000import.ActivityImportTaskerDataBase
    public boolean b() {
        return false;
    }

    static final class a extends l implements c.f.a.a<Integer> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            return go.j(ActivityPreviewTaskerDataFromUri.this);
        }
    }

    static final class b extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f6207b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str) {
            super(0);
            this.f6207b = str;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            View decorView;
            Drawable background;
            int iF = ActivityPreviewTaskerDataFromUri.this.f();
            Window window = ActivityPreviewTaskerDataFromUri.this.getWindow();
            if (window != null && (decorView = window.getDecorView()) != null && (background = decorView.getBackground()) != null) {
                background.setColorFilter(new LightingColorFilter(-16777216, iF));
            }
            TextView textView = (TextView) ActivityPreviewTaskerDataFromUri.this.findViewById(R.id.text_preview);
            textView.setTextColor(ActivityPreviewTaskerDataFromUri.this.e());
            k.a((Object) textView, "textView");
            textView.setText(this.f6207b);
        }
    }

    static final class c extends l implements c.f.a.a<Integer> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            return go.e(ActivityPreviewTaskerDataFromUri.this);
        }
    }

    @Override // com.joaomgcd.taskerm.datashare.p000import.ActivityImportTaskerDataBase
    public b.a.l<cl> d() {
        return com.joaomgcd.taskerm.datashare.export.f.a((Activity) this, true);
    }

    @Override // com.joaomgcd.taskerm.datashare.p000import.ActivityImportTaskerDataBase
    public Integer c() {
        return Integer.valueOf(R.layout.entity_preview);
    }

    @Override // com.joaomgcd.taskerm.datashare.p000import.ActivityImportTaskerDataBase
    public void a(cl clVar) {
        k.b(clVar, "result");
        super.a(clVar);
        if (!(clVar instanceof cq)) {
            clVar = null;
        }
        cq cqVar = (cq) clVar;
        c.k kVar = cqVar != null ? (c.k) cqVar.d() : null;
        if (kVar == null) {
            ak.b("Invalid import", this);
            finish();
        } else {
            String strA = ((ay) kVar.d()).a(this, (fx) kVar.c(), new com.joaomgcd.taskerm.f.g(false));
            k.a((Object) strA, "importable.getString(thi…escriptionOptions(false))");
            i.e(new b(strA));
        }
    }

    @Override // com.joaomgcd.taskerm.datashare.p000import.ActivityImportTaskerDataBase
    public int a() {
        return gn.c(this);
    }
}
