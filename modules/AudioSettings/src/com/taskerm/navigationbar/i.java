package com.joaomgcd.taskerm.navigationbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.io.File;
import net.dinglisch.android.taskerm.hg;

/* JADX INFO: loaded from: classes.dex */
final class i implements g {
    @Override // com.joaomgcd.taskerm.navigationbar.g
    public boolean b() {
        return false;
    }

    @Override // com.joaomgcd.taskerm.navigationbar.g
    public int a() {
        return g.a.a(this);
    }

    @Override // com.joaomgcd.taskerm.navigationbar.g
    public File a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "iconValue");
        return g.a.a(this, context, str);
    }

    @Override // com.joaomgcd.taskerm.navigationbar.g
    public Bitmap b(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "iconValue");
        return g.a.b(this, context, str);
    }

    @Override // com.joaomgcd.taskerm.navigationbar.g
    public Bitmap a(Context context, String str, int i) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "iconValue");
        hg.a aVarD = IconProvider.f7827a.d(str);
        if (aVarD == null) {
            return null;
        }
        Drawable drawableA = hg.a(context, aVarD);
        c.f.b.k.a((Object) drawableA, "Wallpaper.getCurrentWallpaper(context, type)");
        return com.joaomgcd.taskerm.m.b.a(drawableA);
    }
}
