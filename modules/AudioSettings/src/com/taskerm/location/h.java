package com.joaomgcd.taskerm.location;

import android.location.Location;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.dm;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7685a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final double f7686b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final double f7687c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Integer f7688d;

    public h(double d2, double d3, Integer num) {
        this.f7686b = d2;
        this.f7687c = d3;
        this.f7688d = num;
    }

    public final double d() {
        return this.f7686b;
    }

    public final double e() {
        return this.f7687c;
    }

    public final Integer f() {
        return this.f7688d;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public h(String str, String str2, Integer num) {
        this(dm.a(str, str2), num);
        c.f.b.k.b(str, "latitude");
        c.f.b.k.b(str2, "longitude");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public h(Location location, Integer num) {
        this(location.getLatitude(), location.getLongitude(), num);
        c.f.b.k.b(location, "location");
    }

    public final Location a() {
        return dm.a(this.f7686b, this.f7687c);
    }

    public final String b() {
        return aj.a(this.f7686b) + ',' + aj.a(this.f7687c);
    }

    public final String c() {
        return "https://www.google.com/maps?ll=" + b() + "&q=" + b() + "&z=" + (this.f7688d != null ? p.a(this.f7688d.intValue()) : 16.0f);
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final h a(String str) {
            Double dB;
            List listB = str != null ? c.l.n.b((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null) : null;
            h hVar = (h) null;
            if (listB == null || listB.size() < 2) {
                return hVar;
            }
            String str2 = (String) listB.get(0);
            String str3 = (String) listB.get(1);
            String str4 = listB.size() == 3 ? (String) listB.get(2) : null;
            try {
                return new h(str2, str3, (str4 == null || (dB = c.l.n.b(str4)) == null) ? null : Integer.valueOf(c.g.a.a(dB.doubleValue())));
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
