package com.joaomgcd.taskerm.navigationbar;

import android.content.Context;
import android.graphics.Bitmap;
import java.io.File;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.fy;

/* JADX INFO: loaded from: classes.dex */
final class h implements g {
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
        fs fsVarH;
        net.dinglisch.android.taskerm.g gVarC;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "iconValue");
        fy fyVarC = fx.c(context);
        if (fyVarC == null || (fsVarH = fyVarC.h(str)) == null || (gVarC = fsVarH.C()) == null) {
            return null;
        }
        return gVarC.a(context, i, i, "IconProviderTask");
    }
}
