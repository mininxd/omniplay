package com.joaomgcd.taskerm.a;

import android.content.Context;
import c.f.b.k;
import net.dinglisch.android.taskerm.MyAccessibilityService;

/* JADX INFO: loaded from: classes.dex */
public final class d extends c {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(Context context) {
        super(context, MyAccessibilityService.class);
        k.b(context, "context");
    }

    @Override // com.joaomgcd.taskerm.a.c
    public Boolean a() {
        return Boolean.valueOf(MyAccessibilityService.c());
    }
}
