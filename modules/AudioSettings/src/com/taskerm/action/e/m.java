package com.joaomgcd.taskerm.action.e;

import android.content.Context;
import c.q;
import com.joaomgcd.taskerm.function.FunctionArgs;
import com.joaomgcd.taskerm.function.FunctionBase;
import com.joaomgcd.taskerm.function.FunctionBaseKt;
import com.joaomgcd.taskerm.helper.actions.execute.w;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class m extends w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5168a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f5169b;

    /* JADX WARN: Multi-variable type inference failed */
    public m() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void function$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public /* synthetic */ m(String str, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getFunction() {
        return this.f5168a;
    }

    public final void setFunction(String str) {
        this.f5168a = str;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f5169b;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5169b = jVar;
    }

    public m(String str, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5168a = str;
        this.f5169b = jVar;
    }

    public final List<d> getFunctions$Tasker_5_15_14__marketNoTrialRelease() {
        String str = this.f5168a;
        List<FunctionArgs> functionArgs = str != null ? FunctionBaseKt.getFunctionArgs(str) : null;
        List<FunctionArgs> list = functionArgs;
        if (list == null || list.isEmpty()) {
            return c.a.j.a();
        }
        List<FunctionArgs> list2 = functionArgs;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list2, 10));
        for (FunctionArgs functionArgs2 : list2) {
            FunctionBase<?, ?> functionBase = FunctionBase.Companion.get(functionArgs2);
            arrayList.add(new d(functionBase, functionBase.getTrimParameters() ? functionArgs2.getTrimmedParameters() : functionArgs2.getParameters()));
        }
        return arrayList;
    }

    static final class a extends c.f.b.l implements c.f.a.a<List<? extends String>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f5171b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context) {
            super(0);
            this.f5171b = context;
        }

        @Override // c.f.a.a
        public final List<? extends String> invoke() {
            List<d> functions$Tasker_5_15_14__marketNoTrialRelease = m.this.getFunctions$Tasker_5_15_14__marketNoTrialRelease();
            ArrayList arrayList = new ArrayList();
            for (d dVar : functions$Tasker_5_15_14__marketNoTrialRelease) {
                String[] permissions = dVar.a().getPermissions(this.f5171b, dVar.b());
                if (permissions != null) {
                    arrayList.add(permissions);
                }
            }
            Object[] array = arrayList.toArray(new String[0][]);
            if (array != null) {
                return c.a.d.a((Object[][]) array);
            }
            throw new q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    public final String[] getPermissions$Tasker_5_15_14__marketNoTrialRelease(Context context) {
        c.f.b.k.b(context, "context");
        List list = (List) aj.a((c.f.a.b) null, new a(context), 1, (Object) null);
        if (list == null) {
            return null;
        }
        Object[] array = list.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
