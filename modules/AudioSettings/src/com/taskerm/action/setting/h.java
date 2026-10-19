package com.joaomgcd.taskerm.action.setting;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import b.a.p;
import com.joaomgcd.taskerm.action.setting.j;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityForResultForIntent;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class h<TInput extends j> extends com.joaomgcd.taskerm.helper.actions.execute.l<TInput> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<TInput, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(TInput tinput) {
        c.f.b.k.b(tinput, "input");
        Intent intent = tinput.getIntent(h());
        if (tinput.getWaitForResult()) {
            b.a.l<cl> lVarRun = new GenericActionActivityForResultForIntent(intent).run(h());
            if (!tinput.getErrorOnCancel()) {
                lVarRun = lVarRun.a(a.f5709a).d(b.f5710a);
                c.f.b.k.a((Object) lVarRun, "single.flatMap { Single.…(SimpleResultSuccess()) }");
            }
            cl clVarB = lVarRun.b();
            c.f.b.k.a((Object) clVarB, "single.blockingGet()");
            return clVarB;
        }
        try {
            h().startActivity(intent);
            return new co();
        } catch (Exception e2) {
            return new cm(e2);
        }
    }

    static final class a<T, R> implements b.a.d.g<T, p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5709a = new a();

        a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<cl> apply(cl clVar) {
            c.f.b.k.b(clVar, "it");
            return b.a.l.a(new co());
        }
    }

    static final class b<T, R> implements b.a.d.g<Throwable, p<? extends cl>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5710a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<co> apply(Throwable th) {
            c.f.b.k.b(th, "it");
            return b.a.l.a(new co());
        }
    }
}
