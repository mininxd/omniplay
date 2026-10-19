package com.joaomgcd.taskerm.dialog;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;

/* JADX INFO: loaded from: classes.dex */
public class g extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6313a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AnimationDrawable f6314b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f6315c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Context context, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, boolean z, AnimationDrawable animationDrawable, String str) {
        super(context, hVar, z);
        c.f.b.k.b(context, "context");
        this.f6313a = hVar2;
        this.f6314b = animationDrawable;
        this.f6315c = str;
    }

    public final AnimationDrawable h() {
        return this.f6314b;
    }

    public final String i() {
        return this.f6315c;
    }

    public final CharSequence f() {
        String strA;
        com.joaomgcd.taskerm.util.h hVarB = b();
        if (hVarB == null || (strA = hVarB.a()) == null) {
            return null;
        }
        return com.joaomgcd.taskerm.util.ak.F(strA);
    }

    public final CharSequence g() {
        String strA;
        com.joaomgcd.taskerm.util.h hVar = this.f6313a;
        if (hVar == null || (strA = hVar.a()) == null) {
            return null;
        }
        return com.joaomgcd.taskerm.util.ak.F(strA);
    }
}
