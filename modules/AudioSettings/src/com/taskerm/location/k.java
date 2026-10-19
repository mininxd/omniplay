package com.joaomgcd.taskerm.location;

import android.location.Location;
import android.os.Bundle;
import com.joaomgcd.taskerm.util.az;
import com.joaomgcd.taskerm.util.ba;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final az f7727a = ba.a("location");

    public static final int a(Location location) {
        Object obj;
        String string;
        Integer numC;
        c.f.b.k.b(location, "receiver$0");
        Bundle extras = location.getExtras();
        if (extras == null || (obj = extras.get("satellites")) == null || (string = obj.toString()) == null || (numC = c.l.n.c(string)) == null) {
            return 0;
        }
        return numC.intValue();
    }
}
