package com.joaomgcd.taskerm.event;

import c.f.b.k;
import com.joaomgcd.taskerm.f.l;
import cyanogenmod.app.ProfileManager;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public abstract class d<TInput> extends l<TInput, an> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(String str, b<?, ?, ?, ?> bVar) {
        super(str, bVar);
        k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        k.b(bVar, "eventBase");
    }
}
