package com.joaomgcd.taskerm.action.audio;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import com.joaomgcd.taskerm.function.DoNotDisturbCategory;
import com.joaomgcd.taskerm.function.DoNotDisturbEnum;
import com.joaomgcd.taskerm.function.DoNotDisturbSenders;
import com.joaomgcd.taskerm.function.DoNotDisturbSuppressedEffect;
import com.joaomgcd.taskerm.function.SetNotificationPolicy;
import com.joaomgcd.taskerm.notification.au;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.db;

/* JADX INFO: loaded from: classes.dex */
public final class n extends com.joaomgcd.taskerm.helper.actions.execute.l<r> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4987a;

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<r, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
        this.f4987a = -1;
    }

    private final DoNotDisturbSenders a(Integer num) {
        if (num == null) {
            return null;
        }
        int iIntValue = num.intValue();
        for (DoNotDisturbSenders doNotDisturbSenders : DoNotDisturbSenders.values()) {
            if (doNotDisturbSenders.getId() == iIntValue) {
                return doNotDisturbSenders;
            }
        }
        return null;
    }

    private final DoNotDisturbCategory[] b(Integer num) {
        if (num == null) {
            return null;
        }
        int iIntValue = num.intValue();
        DoNotDisturbCategory[] doNotDisturbCategoryArrValues = DoNotDisturbCategory.values();
        ArrayList arrayList = new ArrayList();
        for (DoNotDisturbCategory doNotDisturbCategory : doNotDisturbCategoryArrValues) {
            if (aj.b(iIntValue, doNotDisturbCategory.getId())) {
                arrayList.add(doNotDisturbCategory);
            }
        }
        Object[] array = arrayList.toArray(new DoNotDisturbCategory[0]);
        if (array != null) {
            return (DoNotDisturbCategory[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DoNotDisturbSuppressedEffect[] c(Integer num) {
        if (num == null) {
            return null;
        }
        int iIntValue = num.intValue();
        DoNotDisturbSuppressedEffect[] doNotDisturbSuppressedEffectArrValues = DoNotDisturbSuppressedEffect.values();
        ArrayList arrayList = new ArrayList();
        for (DoNotDisturbSuppressedEffect doNotDisturbSuppressedEffect : doNotDisturbSuppressedEffectArrValues) {
            if (aj.b(iIntValue, doNotDisturbSuppressedEffect.getId())) {
                arrayList.add(doNotDisturbSuppressedEffect);
            }
        }
        Object[] array = arrayList.toArray(new DoNotDisturbSuppressedEffect[0]);
        if (array != null) {
            return (DoNotDisturbSuppressedEffect[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @TargetApi(24)
    public cl a(r rVar) {
        c.f.b.k.b(rVar, "input");
        if (!db.c()) {
            String str = i().n() + ": don't have needed OS features";
            bn.d("ActionSpecs", str);
            return new cm(str);
        }
        g mode = rVar.getMode();
        if (mode == null) {
            return new cm("Do Not Disturb: No mode selected");
        }
        if (com.joaomgcd.taskerm.util.f.f9323b.q() && mode == g.Custom) {
            b bVar = new b();
            SetNotificationPolicy.Companion.getInputFromFlags(SetNotificationPolicy.Companion.getFlagsString(rVar.getCallersNotNull(), rVar.getMessageSendersNotNull(), bVar.invoke(DoNotDisturbCategory.values(), rVar.getCategoriesNotNull()), bVar.invoke(DoNotDisturbSuppressedEffect.values(), rVar.getSuppressedEffectsNotNull()), rVar.getRepeatCallersNotNull())).set(h());
            return new co();
        }
        if (mode == g.Query) {
            return d();
        }
        int iC = net.dinglisch.android.taskerm.n.c(mode.ordinal());
        bn.b("ActionSpecs", "cur mode: " + db.a(db.d(h())) + " wanted: " + db.a(iC));
        if (!db.a(h(), iC)) {
            h().f10621d.a(i().i(), new com.joaomgcd.taskerm.notification.ab(h()));
            return new cm("Notification listener not enabled?");
        }
        return new co();
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class b<T> extends c.f.b.l implements c.f.a.m<T[], String, List<? extends T>> {
        b() {
            super(2);
        }

        /* JADX WARN: Incorrect types in method signature: <T::Lcom/joaomgcd/taskerm/function/DoNotDisturbEnum;>([TT;Ljava/lang/String;)Ljava/util/List<TT;>; */
        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List invoke(DoNotDisturbEnum[] doNotDisturbEnumArr, String str) {
            DoNotDisturbEnum doNotDisturbEnum;
            c.f.b.k.b(doNotDisturbEnumArr, OutputAnySensor.VAR_VALUES);
            c.f.b.k.b(str, "str");
            List<String> listB = c.l.n.b((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listB, 10));
            for (String str2 : listB) {
                int length = doNotDisturbEnumArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        doNotDisturbEnum = null;
                        break;
                    }
                    doNotDisturbEnum = doNotDisturbEnumArr[i];
                    int description = doNotDisturbEnum.getDescription();
                    ExecuteService executeServiceH = n.this.h();
                    Locale locale = Locale.US;
                    c.f.b.k.a((Object) locale, "Locale.US");
                    boolean zA = c.f.b.k.a((Object) aj.a(description, executeServiceH, locale, new Object[0]), (Object) str2);
                    if (!zA) {
                        zA = c.f.b.k.a((Object) aj.a(doNotDisturbEnum.getDescription(), n.this.h(), new Object[0]), (Object) str2);
                    }
                    if (zA) {
                        break;
                    }
                    i++;
                }
                arrayList.add(doNotDisturbEnum);
            }
            return c.a.j.f((Iterable) arrayList);
        }
    }

    @TargetApi(24)
    public final cl d() {
        NotificationManager.Policy notificationPolicy;
        g gVar = (g) ((Enum) aj.a(net.dinglisch.android.taskerm.n.d(db.d(h())), g.class));
        NotificationManager notificationManagerB = au.b(h());
        if (notificationManagerB == null || (notificationPolicy = notificationManagerB.getNotificationPolicy()) == null) {
            return cn.a("Could not get current DND status");
        }
        DoNotDisturbCategory[] doNotDisturbCategoryArrB = b(Integer.valueOf(notificationPolicy.priorityCategories));
        if (doNotDisturbCategoryArrB == null) {
            return cn.a("Could not get current DND categories");
        }
        DoNotDisturbSenders doNotDisturbSendersA = a(Integer.valueOf(notificationPolicy.priorityCallSenders));
        DoNotDisturbSenders doNotDisturbSendersA2 = a(Integer.valueOf(notificationPolicy.priorityMessageSenders));
        if (!c.a.d.b(doNotDisturbCategoryArrB, DoNotDisturbCategory.Calls)) {
            doNotDisturbSendersA = DoNotDisturbSenders.None;
        }
        return cn.a(new OutputDoNotDisturb(gVar, doNotDisturbSendersA, !c.a.d.b(doNotDisturbCategoryArrB, DoNotDisturbCategory.Messages) ? DoNotDisturbSenders.None : doNotDisturbSendersA2, doNotDisturbCategoryArrB, (DoNotDisturbSuppressedEffect[]) aj.a((c.f.a.b) null, new a(notificationPolicy), 1, (Object) null)));
    }

    static final class a extends c.f.b.l implements c.f.a.a<DoNotDisturbSuppressedEffect[]> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ NotificationManager.Policy f4989b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(NotificationManager.Policy policy) {
            super(0);
            this.f4989b = policy;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final DoNotDisturbSuppressedEffect[] invoke() {
            return n.this.c(Integer.valueOf(this.f4989b.suppressedVisualEffects));
        }
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    protected int b() {
        return this.f4987a;
    }
}
