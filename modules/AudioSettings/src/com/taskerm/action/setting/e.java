package com.joaomgcd.taskerm.action.setting;

import android.content.Context;
import com.joaomgcd.taskerm.helper.actions.execute.w;
import com.joaomgcd.taskerm.securesettings.r;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public abstract class e<TInput extends w> extends com.joaomgcd.taskerm.helper.actions.c<TInput> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.f.a.b<String, r> f5703a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(ActionEdit actionEdit, com.joaomgcd.taskerm.action.setting.a<TInput> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
        this.f5703a = new a(aVar, actionEdit);
    }

    static final class a extends c.f.b.l implements c.f.a.b<String, r> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.action.setting.a f5704a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActionEdit f5705b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(com.joaomgcd.taskerm.action.setting.a aVar, ActionEdit actionEdit) {
            super(1);
            this.f5704a = aVar;
            this.f5705b = actionEdit;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final r invoke(String str) {
            c.f.b.k.b(str, "it");
            return this.f5704a.a((Context) this.f5705b, str);
        }
    }

    protected final c.f.a.b<String, r> f() {
        return this.f5703a;
    }
}
