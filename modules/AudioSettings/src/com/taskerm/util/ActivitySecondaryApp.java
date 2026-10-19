package com.joaomgcd.taskerm.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Set;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ActivitySecondaryApp extends Activity {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            com.joaomgcd.taskerm.dialog.x xVarB = com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h((Activity) ActivitySecondaryApp.this, R.string.en_secondary_app_opened, R.string.dc_secondary_app_explained, R.string.button_label_yes, R.string.button_label_no, R.string.ml_disable, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null)).b();
            if (!xVarB.e()) {
                if (xVarB.g()) {
                    com.joaomgcd.taskerm.dialog.y.d(ActivitySecondaryApp.this, R.string.en_secondary_app_opened, R.string.dc_secondary_app_can_be_enabled_in_settings).b();
                    e.a(ActivitySecondaryApp.this, false).b();
                } else if (xVarB.d()) {
                    ah.a((Context) ActivitySecondaryApp.this, "https://www.youtube.com/watch?v=WLsw2D2NJVU", false, false, false, 14, (Object) null);
                }
            }
            ActivitySecondaryApp.this.finish();
        }
    }

    @Override // android.app.Activity
    @SuppressLint({"CheckResult"})
    protected void onCreate(Bundle bundle) {
        ArrayList arrayListA;
        super.onCreate(bundle);
        getWindow().addFlags(6815872);
        if (com.joaomgcd.taskerm.helper.r.f7464c.a(2077)) {
            Intent intent = getIntent();
            com.joaomgcd.taskerm.inputoutput.a.b bVar = null;
            Uri data = intent != null ? intent.getData() : null;
            if (data != null) {
                Set<String> queryParameterNames = data.getQueryParameterNames();
                if (queryParameterNames != null) {
                    Set<String> set = queryParameterNames;
                    ArrayList arrayList = new ArrayList(c.a.j.a(set, 10));
                    for (String str : set) {
                        c.f.b.k.a((Object) str, "it");
                        arrayList.add(new com.joaomgcd.taskerm.inputoutput.a.a((String) null, str, data.getQueryParameter(str)));
                    }
                    arrayListA = arrayList;
                } else {
                    arrayListA = null;
                }
            } else {
                arrayListA = c.a.j.a();
            }
            if (arrayListA != null) {
                bVar = new com.joaomgcd.taskerm.inputoutput.a.b();
                bVar.addAll(arrayListA);
            }
            com.joaomgcd.taskerm.event.app.g.a(this, bVar);
            finish();
            return;
        }
        com.joaomgcd.taskerm.rx.i.c(new a());
    }
}
