package com.joaomgcd.taskerm.navigationbar;

import android.content.Context;
import android.graphics.Bitmap;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.n;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
final class c implements g {
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
    public boolean b() {
        return g.a.b(this);
    }

    @Override // com.joaomgcd.taskerm.navigationbar.g
    public Bitmap a(Context context, String str, int i) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "iconValue");
        return ah.a(context, new n("IconProviderApp", str, null, Integer.valueOf(i), Integer.valueOf(i), 4, null));
    }
}
