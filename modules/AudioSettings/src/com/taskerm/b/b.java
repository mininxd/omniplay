package com.joaomgcd.taskerm.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.joaomgcd.taskerm.util.cq;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class b extends s {
    @Override // com.joaomgcd.taskerm.b.c
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.util.l b() {
        return new com.joaomgcd.taskerm.util.l(R.string.clear_app_data);
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f5885a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f5885a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String str;
            cq cqVar = (cq) com.joaomgcd.taskerm.dialog.y.a((Context) this.f5885a, (Intent) null, (List) null, false, 14, (Object) null).b();
            if (cqVar == null || (str = (String) cqVar.d()) == null) {
                throw new RuntimeException("No app selected");
            }
            return "pm clear " + str;
        }
    }

    @Override // com.joaomgcd.taskerm.b.c
    public b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "host");
        return com.joaomgcd.taskerm.rx.i.b(new a(activity));
    }
}
