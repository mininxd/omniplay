package com.joaomgcd.taskerm.action.input;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class ai extends com.joaomgcd.taskerm.helper.actions.execute.l<av> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ai(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<av, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(av avVar) {
        c.f.b.k.b(avVar, "input");
        com.joaomgcd.taskerm.x.c cVar = new com.joaomgcd.taskerm.x.c(h(), null, 2, null);
        ExecuteService executeServiceH = h();
        String title = avVar.getTitle();
        Integer maximumResults = avVar.getMaximumResults();
        com.joaomgcd.taskerm.x.c.EnumC0252c languageModel = avVar.getLanguageModel();
        String language = avVar.getLanguage();
        Boolean hidePopup = avVar.getHidePopup();
        com.joaomgcd.taskerm.x.b bVarB = cVar.a(new com.joaomgcd.taskerm.x.c.a(executeServiceH, title, maximumResults, languageModel, language, hidePopup != null ? hidePopup.booleanValue() : false)).b(avVar.getTimeoutNotNull(), TimeUnit.SECONDS).c(new a()).b();
        c.f.b.k.a((Object) bVarB, "recognized");
        com.joaomgcd.taskerm.x.b bVar = bVarB;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) bVar, 10));
        Iterator<com.joaomgcd.taskerm.x.a> it = bVar.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            gw.a(h(), 92, c.a.d.a((String[]) array, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null));
            ArrayList arrayList2 = new ArrayList(c.a.j.a((Iterable) bVar, 10));
            for (com.joaomgcd.taskerm.x.a aVar : bVar) {
                arrayList2.add(new OutputGetVoiceSingle(aVar.a(), c.g.a.a(aVar.b() * 100.0f)));
            }
            Object[] array2 = arrayList2.toArray(new OutputGetVoiceSingle[0]);
            if (array2 != null) {
                bi biVar = new bi((OutputGetVoiceSingle[]) array2);
                com.joaomgcd.taskerm.inputoutput.a.b bVar2 = new com.joaomgcd.taskerm.inputoutput.a.b();
                bVar2.a(h(), biVar);
                bVar2.a(h(), c.a.d.c(biVar.a()));
                return cn.a(bVar2);
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    static final class a<T> implements b.a.d.f<Throwable> {
        a() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            gw.a(ai.this.h(), 92);
        }
    }
}
