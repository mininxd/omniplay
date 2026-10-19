package com.joaomgcd.taskerm.action.input;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class af extends com.joaomgcd.taskerm.helper.actions.execute.l<aq> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public af(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<aq, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(aq aqVar) {
        c.f.b.k.b(aqVar, "input");
        String title = aqVar.getTitle();
        if (title == null) {
            return cn.a("Must set a title for the dialog");
        }
        o mode = aqVar.getMode();
        if (mode == null) {
            return cn.a("Must set a mode for the dialog");
        }
        String[] items = aqVar.getItems();
        if (items == null) {
            return cn.a("Must set items for the dialog");
        }
        if (items.length == 0) {
            return cn.a("Can't show a dialog with an empty array of items");
        }
        long timeoutNotNull = aqVar.getTimeoutNotNull();
        String[] selectedItems = aqVar.getSelectedItems();
        String longClickTask = aqVar.getLongClickTask();
        String positiveLabel = aqVar.getPositiveLabel();
        String negativeLabel = aqVar.getNegativeLabel();
        String neutralLabel = aqVar.getNeutralLabel();
        boolean useHtmlNotNull = aqVar.getUseHtmlNotNull();
        Integer firstVisibleIndex = aqVar.getFirstVisibleIndex();
        Integer numValueOf = firstVisibleIndex != null ? Integer.valueOf(firstVisibleIndex.intValue() - 1) : null;
        long timeoutNotNull2 = aqVar.getTimeoutNotNull();
        Boolean hideFilter = aqVar.getHideFilter();
        cl clVarB = new GenericActionDialogList(mode, title, items, selectedItems, longClickTask, positiveLabel, negativeLabel, neutralLabel, useHtmlNotNull, numValueOf, timeoutNotNull2, hideFilter != null ? hideFilter.booleanValue() : false, aqVar.getText()).run(h()).b(timeoutNotNull, TimeUnit.SECONDS).b();
        c.f.b.k.a((Object) clVarB, "GenericActionDialogList(…it.SECONDS).blockingGet()");
        return clVarB;
    }
}
