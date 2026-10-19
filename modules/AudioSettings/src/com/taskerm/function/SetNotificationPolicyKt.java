package com.joaomgcd.taskerm.function;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SetNotificationPolicyKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends DoNotDisturbEnum> int getFlags(List<? extends T> list) {
        Iterator<T> it = list.iterator();
        int id = 0;
        while (it.hasNext()) {
            id |= ((DoNotDisturbEnum) it.next()).getId();
        }
        return id;
    }
}
