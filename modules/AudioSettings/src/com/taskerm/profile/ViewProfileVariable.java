package com.joaomgcd.taskerm.profile;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.joaomgcd.taskerm.util.ah;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ViewProfileVariable extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f8206a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f8207b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewProfileVariable(Activity activity, d dVar) {
        super(activity);
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(dVar, "importable");
        Context context = getContext();
        c.f.b.k.a((Object) context, "context");
        this.f8206a = ah.ay(context).inflate(R.layout.profilevariable, this);
        View view = this.f8206a;
        c.f.b.k.a((Object) view, "root");
        this.f8207b = new l(activity, view, true, dVar);
    }

    public final l getFields() {
        return this.f8207b;
    }
}
