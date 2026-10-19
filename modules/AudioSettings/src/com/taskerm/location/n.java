package com.joaomgcd.taskerm.location;

import android.content.Intent;
import com.google.android.gms.location.ActivityTransitionEvent;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final o f7734a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ActivityTransitionEvent f7735b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Intent f7736c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f7737d;

    public n(ActivityTransitionEvent activityTransitionEvent, Intent intent, boolean z) {
        o oVar;
        c.f.b.k.b(activityTransitionEvent, "transition");
        c.f.b.k.b(intent, "intent");
        this.f7735b = activityTransitionEvent;
        this.f7736c = intent;
        this.f7737d = z;
        o[] oVarArrValues = o.values();
        int length = oVarArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                oVar = null;
                break;
            }
            oVar = oVarArrValues[i];
            if (oVar.a() == this.f7735b.a()) {
                break;
            } else {
                i++;
            }
        }
        if (oVar != null) {
            this.f7734a = oVar;
            return;
        }
        throw new RuntimeException("Invalid activity type " + this.f7735b.a());
    }

    public final boolean a() {
        return this.f7735b.b() == 0;
    }

    public final void a(String str) {
        c.f.b.k.b(str, "typeOfUpdate");
        StringBuilder sb = new StringBuilder();
        sb.append("Received ");
        sb.append(str);
        sb.append(": ");
        sb.append(a() ? "entered" : "exited");
        sb.append(' ');
        sb.append(this.f7734a);
        bn.b("PhysicalStuff", sb.toString());
    }
}
