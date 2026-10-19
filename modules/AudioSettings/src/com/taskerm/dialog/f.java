package com.joaomgcd.taskerm.dialog;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class f extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f6311a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.f.a.a<c.s> f6312b;

    public f(Context context, int i, int i2) {
        this(context, i, i2, false, false, 24, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Context context, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, boolean z, boolean z2, AnimationDrawable animationDrawable, String str, c.f.a.a<c.s> aVar) {
        super(context, hVar, hVar2, z2, animationDrawable, str);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(hVar, "title");
        c.f.b.k.b(hVar2, "text");
        this.f6311a = z;
        this.f6312b = aVar;
    }

    public /* synthetic */ f(Context context, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, boolean z, boolean z2, AnimationDrawable animationDrawable, String str, c.f.a.a aVar, int i, c.f.b.g gVar) {
        this(context, hVar, hVar2, (i & 8) != 0 ? true : z, (i & 16) != 0 ? true : z2, (i & 32) != 0 ? (AnimationDrawable) null : animationDrawable, (i & 64) != 0 ? (String) null : str, (i & 128) != 0 ? (c.f.a.a) null : aVar);
    }

    public final boolean d() {
        return this.f6311a;
    }

    public final c.f.a.a<c.s> e() {
        return this.f6312b;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public f(Context context, int i, int i2, boolean z, boolean z2) {
        this(context, new com.joaomgcd.taskerm.util.k(context, i, new Object[0]), new com.joaomgcd.taskerm.util.k(context, i2, new Object[0]), z, z2, null, null, null, 224, null);
        c.f.b.k.b(context, "context");
    }

    public /* synthetic */ f(Context context, int i, int i2, boolean z, boolean z2, int i3, c.f.b.g gVar) {
        this(context, i, i2, (i3 & 8) != 0 ? true : z, (i3 & 16) != 0 ? true : z2);
    }
}
