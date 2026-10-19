package com.joaomgcd.taskerm.navigationbar;

import android.content.Context;
import android.graphics.Bitmap;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
final class d implements g {
    @Override // com.joaomgcd.taskerm.navigationbar.g
    public int a() {
        return g.a.a(this);
    }

    @Override // com.joaomgcd.taskerm.navigationbar.g
    public Bitmap a(Context context, String str, int i) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "iconValue");
        return g.a.a(this, context, str, i);
    }

    @Override // com.joaomgcd.taskerm.navigationbar.g
    public Bitmap b(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "iconValue");
        return g.a.b(this, context, str);
    }

    @Override // com.joaomgcd.taskerm.navigationbar.g
    public boolean b() {
        return g.a.b(this);
    }

    @Override // com.joaomgcd.taskerm.navigationbar.g
    public File a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "iconValue");
        StringBuilder sb = new StringBuilder();
        File cacheDir = context.getCacheDir();
        c.f.b.k.a((Object) cacheDir, "context.cacheDir");
        sb.append(cacheDir.getAbsolutePath());
        sb.append('/');
        sb.append(str);
        return new File(sb.toString());
    }
}
