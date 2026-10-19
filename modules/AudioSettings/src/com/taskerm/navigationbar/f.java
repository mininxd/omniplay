package com.joaomgcd.taskerm.navigationbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.File;
import net.dinglisch.android.taskerm.bz;

/* JADX INFO: loaded from: classes.dex */
final class f implements g {
    @Override // com.joaomgcd.taskerm.navigationbar.g
    public int a() {
        return 24;
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
        return bz.a(context, Uri.parse("file:///" + str), i, i, 0, "IconProviderFile");
    }
}
