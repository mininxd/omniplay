package com.joaomgcd.taskerm.b;

import android.app.Activity;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class l extends s {
    @Override // com.joaomgcd.taskerm.b.c
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.util.l b() {
        return new com.joaomgcd.taskerm.util.l(R.string.an_take_screenshot);
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f5918a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f5918a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "screencap -p " + com.joaomgcd.taskerm.dialog.k.a(this.f5918a, false).b();
        }
    }

    @Override // com.joaomgcd.taskerm.b.c
    public b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "host");
        return com.joaomgcd.taskerm.rx.i.b(new a(activity));
    }
}
