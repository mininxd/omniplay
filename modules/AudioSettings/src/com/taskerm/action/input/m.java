package com.joaomgcd.taskerm.action.input;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public final class m {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(Context context, StoredPowerMenuActions storedPowerMenuActions) {
        com.joaomgcd.taskerm.q.b.a(context, "STORED_POWER_MENU_ACTIONS", storedPowerMenuActions, (SharedPreferences) null, 4, (Object) null);
    }

    public static final StoredPowerMenuActions a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return c(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StoredPowerMenuActions c(Context context) {
        SharedPreferences sharedPreferencesE = com.joaomgcd.taskerm.q.b.e(context);
        c.f.b.k.a((Object) sharedPreferencesE, "preferences");
        String string = sharedPreferencesE.getString("STORED_POWER_MENU_ACTIONS", null);
        StoredPowerMenuActions storedPowerMenuActions = (StoredPowerMenuActions) (string != null ? com.joaomgcd.taskerm.n.b.a().a(string, StoredPowerMenuActions.class) : null);
        return storedPowerMenuActions != null ? storedPowerMenuActions : new StoredPowerMenuActions();
    }
}
