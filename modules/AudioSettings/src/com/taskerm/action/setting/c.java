package com.joaomgcd.taskerm.action.setting;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class c extends b<k, f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5702a;

    public c() {
        super(new net.dinglisch.android.taskerm.m(337, R.string.an_notification_settings, 30, 0, "notification_settings", 1, Integer.valueOf(R.string.pl_package), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_category), "t:1:?", 0, 1));
        this.f5702a = 5178;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public k d() {
        return new k(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public f a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new f(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5702a);
    }
}
