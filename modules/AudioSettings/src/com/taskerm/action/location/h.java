package com.joaomgcd.taskerm.action.location;

import android.location.Location;
import android.os.SystemClock;
import c.f.b.l;
import c.l.n;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private j f5529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5530b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5531c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Class<OutputMockLocation> f5532d;

    public h() {
        this(null, null, null, null, 15, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void accuracy$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void location$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void mode$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public h(j jVar, String str, String str2, Class<OutputMockLocation> cls) {
        this.f5529a = jVar;
        this.f5530b = str;
        this.f5531c = str2;
        this.f5532d = cls;
    }

    public /* synthetic */ h(j jVar, String str, String str2, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (j) null : jVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? OutputMockLocation.class : cls);
    }

    public final j getMode() {
        return this.f5529a;
    }

    public final void setMode(j jVar) {
        this.f5529a = jVar;
    }

    public final String getLocation() {
        return this.f5530b;
    }

    public final void setLocation(String str) {
        this.f5530b = str;
    }

    public final String getAccuracy() {
        return this.f5531c;
    }

    public final void setAccuracy(String str) {
        this.f5531c = str;
    }

    public final Class<OutputMockLocation> getOutputClass() {
        return this.f5532d;
    }

    public final void setOutputClass(Class<OutputMockLocation> cls) {
        this.f5532d = cls;
    }

    static final class a extends l implements c.f.a.a<List<? extends Double>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f5533a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(List list) {
            super(0);
            this.f5533a = list;
        }

        @Override // c.f.a.a
        public final List<? extends Double> invoke() {
            List list = this.f5533a;
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Double dB = n.b((String) it.next());
                if (dB != null) {
                    arrayList.add(dB);
                }
            }
            return arrayList;
        }
    }

    public final Location getMockedLocation() {
        List listB;
        Float fA;
        String strA = ak.A(this.f5530b);
        if (strA == null || (listB = n.b((CharSequence) strA, new String[]{","}, false, 0, 6, (Object) null)) == null) {
            throw new RuntimeException("Empty longitude and latitude");
        }
        List list = (List) aj.a((c.f.a.b) null, new a(listB), 1, (Object) null);
        if (list == null) {
            throw new RuntimeException("Invalid longitude and latitude: " + listB);
        }
        double dDoubleValue = ((Number) list.get(0)).doubleValue();
        double dDoubleValue2 = ((Number) list.get(1)).doubleValue();
        String str = this.f5531c;
        if (str == null || (fA = n.a(str)) == null) {
            throw new RuntimeException("Invalid accuracy: " + this.f5531c);
        }
        float fFloatValue = fA.floatValue();
        Location location = new Location("fused");
        location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        location.setTime(System.currentTimeMillis());
        location.setAccuracy(fFloatValue);
        location.setLatitude(dDoubleValue);
        location.setLongitude(dDoubleValue2);
        return location;
    }
}
