package com.joaomgcd.taskerm.action.d;

import c.f.b.k;
import c.f.b.l;
import c.l.n;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.helper.actions.c<d> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(ActionEdit actionEdit, com.joaomgcd.taskerm.action.d.a aVar) {
        super(actionEdit, aVar);
        k.b(actionEdit, "actionEdit");
        k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        if (aj.a(Integer.valueOf(i), 3, 4)) {
            return !j().getVariablePassthrough();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:21:0x0044  */
    @Override // com.joaomgcd.taskerm.helper.l
    public cl a(d dVar) {
        boolean z;
        k.b(dVar, "inputFromActivity");
        String variablePassthroughList = dVar.getVariablePassthroughList();
        String str = variablePassthroughList;
        if (str == null || str.length() == 0) {
            return super.a(dVar);
        }
        if (n.b((CharSequence) str, (CharSequence) ",", false, 2, (Object) null)) {
            String[] strArr = (String[]) aj.a((c.f.a.b) null, new a(variablePassthroughList), 1, (Object) null);
            if (strArr != null) {
                z = strArr.length == 0;
            }
            if (z) {
                return cn.a("Not a valid variable list");
            }
        }
        return super.a(dVar);
    }

    static final class a extends l implements c.f.a.a<String[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f5063a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.f5063a = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String[] invoke() {
            return aj.a(this.f5063a);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Boolean f(int i) {
        if (i != 0) {
            return null;
        }
        return Boolean.valueOf(j().getVariablePassthrough());
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        if (i != 2) {
            return true;
        }
        a(0, 3, 4);
        return true;
    }
}
