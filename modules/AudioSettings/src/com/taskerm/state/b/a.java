package com.joaomgcd.taskerm.state.b;

import c.f.b.g;
import com.joaomgcd.taskerm.inputoutput.b;
import com.joaomgcd.taskerm.rtt.RTTReadings;
import com.joaomgcd.taskerm.rtt.RTTRoom;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f8651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f8652b;

    /* JADX WARN: Multi-variable type inference failed */
    public a() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @b(a = 0)
    public static /* synthetic */ void name$annotations() {
    }

    @b(a = 1)
    public static /* synthetic */ void readingsJson$annotations() {
    }

    public a(String str, String str2) {
        this.f8651a = str;
        this.f8652b = str2;
    }

    public /* synthetic */ a(String str, String str2, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2);
    }

    public final String getName() {
        return this.f8651a;
    }

    public final void setName(String str) {
        this.f8651a = str;
    }

    public final String getReadingsJson() {
        return this.f8652b;
    }

    public final void setReadingsJson(String str) {
        this.f8652b = str;
    }

    public final RTTRoom getRoom() {
        String str;
        RTTReadings rTTReadings;
        String str2 = this.f8651a;
        if (str2 == null || (str = this.f8652b) == null || (rTTReadings = (RTTReadings) com.joaomgcd.taskerm.n.b.a().a(str, RTTReadings.class)) == null) {
            return null;
        }
        return new RTTRoom(str2, rTTReadings);
    }
}
