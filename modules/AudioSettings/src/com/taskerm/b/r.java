package com.joaomgcd.taskerm.b;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cq;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s {
    @Override // com.joaomgcd.taskerm.b.c
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.util.l b() {
        return new com.joaomgcd.taskerm.util.l(R.string.set_launcher);
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f5969a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f5969a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String str;
            cq cqVar = (cq) com.joaomgcd.taskerm.dialog.y.a((Context) this.f5969a, net.dinglisch.android.taskerm.cq.a(), (List) null, false, 12, (Object) null).b();
            if (cqVar == null || (str = (String) cqVar.d()) == null) {
                throw new RuntimeException("No app selected");
            }
            ComponentName componentNameU = ah.u(this.f5969a, str);
            if (componentNameU == null) {
                throw new RuntimeException("Could not find launcher for app");
            }
            com.joaomgcd.taskerm.b.a.b bVar = com.joaomgcd.taskerm.b.a.f5839b;
            String className = componentNameU.getClassName();
            c.f.b.k.a((Object) className, "activityInfo.className");
            return bVar.a(str, className);
        }
    }

    @Override // com.joaomgcd.taskerm.b.c
    public b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "host");
        return com.joaomgcd.taskerm.rx.i.b(new a(activity));
    }
}
