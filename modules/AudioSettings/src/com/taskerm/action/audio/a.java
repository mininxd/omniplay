package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<p, h, l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4948a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4949c;

    public a() {
        super(new net.dinglisch.android.taskerm.m(387, R.string.an_accessibility_volume, 20, 5, "accessibility_volume", 0, Integer.valueOf(R.string.pl_level), "0:255", 0, 1, 3, Integer.valueOf(R.string.pl_display), "", 0, 0, 3, Integer.valueOf(R.string.pl_sound), "", 0, 0));
        this.f4948a = 5248;
        this.f4949c = 26;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public p d() {
        return new p(null, null, null, 7, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public h a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new h(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public l a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new l(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, p pVar) {
        c.f.b.k.b(context, "context");
        if (bt.a.h(bt.f9101c, context, 0, 2, null).g()) {
            return null;
        }
        return bt.f9101c.z();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f4948a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return Integer.valueOf(this.f4949c);
    }
}
