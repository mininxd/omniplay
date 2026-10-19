package com.joaomgcd.taskerm.rx;

import android.app.IntentService;
import android.content.Intent;
import cyanogenmod.app.ProfileManager;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class IntentServiceSubject<T> extends IntentService {
    protected abstract List<T> a(Intent intent);

    protected abstract e<T> b();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntentServiceSubject(String str) {
        super(str);
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        List<T> listA;
        if (intent == null || (listA = a(intent)) == null) {
            return;
        }
        Iterator<T> it = listA.iterator();
        while (it.hasNext()) {
            b().a(it.next());
        }
    }
}
