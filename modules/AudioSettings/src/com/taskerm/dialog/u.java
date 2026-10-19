package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    static final class a extends c.f.b.l implements c.f.a.a<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6396a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f6396a = activity;
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            List<? extends al<?>> listB;
            List<? extends al<?>> listB2;
            al alVar;
            al alVar2;
            al alVar3 = (al) ((ae) k.a(new ad(this.f6396a, R.string.pl_input_type, u.b(ai.values(), this.f6396a), false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).b();
            if (alVar3 == null) {
                throw new RuntimeException("Cancelled");
            }
            ai aiVar = (ai) alVar3.a();
            c.f.b.w.b bVar = new c.f.b.w.b();
            bVar.f2026a = aiVar.b();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(bVar);
            switch (aiVar) {
                case Text:
                    listB = u.b(ao.values(), this.f6396a);
                    break;
                case Number:
                    listB = u.b(an.values(), this.f6396a);
                    break;
                case PhoneNumber:
                    listB = null;
                    break;
                case DateTime:
                    listB = u.b(am.values(), this.f6396a);
                    break;
                default:
                    throw new c.i();
            }
            List<al<?>> listInvoke = anonymousClass1.invoke(listB);
            if (listInvoke != null && (!listInvoke.isEmpty()) && (alVar2 = (al) ((ae) k.a(new ad(this.f6396a, R.string.pl_input_type, listInvoke, false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).b()) != null) {
                int i = bVar.f2026a;
                Object objA = alVar2.a();
                if (objA == null) {
                    throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.dialog.IInputType");
                }
                bVar.f2026a = ((ag) objA).b() | i;
            }
            while (true) {
                switch (aiVar) {
                    case Text:
                        listB2 = u.b(ak.values(), this.f6396a);
                        break;
                    case Number:
                        listB2 = u.b(aj.values(), this.f6396a);
                        break;
                    case PhoneNumber:
                    case DateTime:
                        listB2 = null;
                        break;
                    default:
                        throw new c.i();
                }
                List<al<?>> listInvoke2 = anonymousClass1.invoke(listB2);
                if (listInvoke2 != null && !listInvoke2.isEmpty() && (alVar = (al) ((ae) k.a(new ad(this.f6396a, R.string.pl_input_type_cancel_continue, listInvoke2, false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).b()) != null) {
                    int i2 = bVar.f2026a;
                    Object objA2 = alVar.a();
                    if (objA2 == null) {
                        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.dialog.IInputType");
                    }
                    bVar.f2026a = ((ag) objA2).b() | i2;
                }
            }
            return bVar.f2026a;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.u$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<List<? extends al<?>>, List<? extends al<?>>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.f.b.w.b f6397a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(c.f.b.w.b bVar) {
                super(1);
                this.f6397a = bVar;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final List<al<?>> invoke(List<? extends al<?>> list) {
                if (list == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    int i = this.f6397a.f2026a;
                    Object objA = ((al) obj).a();
                    if (objA == null) {
                        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.dialog.IInputType");
                    }
                    if (!com.joaomgcd.taskerm.util.aj.b(i, ((ag) objA).b())) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
            }
        }
    }

    public static final b.a.l<Integer> a(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return com.joaomgcd.taskerm.rx.i.b(new a(activity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Incorrect types in method signature: <T:Ljava/lang/Enum<TT;>;:Lcom/joaomgcd/taskerm/dialog/ag;>([TT;Landroid/app/Activity;)Ljava/util/List<Lcom/joaomgcd/taskerm/dialog/al<TT;>;>; */
    public static final List b(Enum[] enumArr, Activity activity) {
        ArrayList arrayList = new ArrayList(enumArr.length);
        for (Enum r0 : enumArr) {
            arrayList.add(new al(activity, r0));
        }
        return arrayList;
    }
}
