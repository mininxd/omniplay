package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import android.content.pm.ActivityInfo;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class p extends ArrayList<o> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f5825a;

    public p(Context context) {
        String str;
        c.f.b.k.b(context, "context");
        this.f5825a = context;
        for (ActivityInfo activityInfo : com.joaomgcd.taskerm.util.ah.t(this.f5825a, "android.intent.action.ASSIST")) {
            if (activityInfo != null && (str = activityInfo.packageName) != null) {
                add(new o(this.f5825a, str));
            }
        }
    }

    public int a() {
        return super.size();
    }

    public boolean a(o oVar) {
        return super.contains(oVar);
    }

    public int b(o oVar) {
        return super.indexOf(oVar);
    }

    public int c(o oVar) {
        return super.lastIndexOf(oVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof o) {
            return a((o) obj);
        }
        return false;
    }

    public boolean d(o oVar) {
        return super.remove(oVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof o) {
            return b((o) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof o) {
            return c((o) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof o) {
            return d((o) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }
}
