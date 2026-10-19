package com.joaomgcd.taskerm.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import b.a.p;
import c.l.n;
import com.joaomgcd.taskerm.contacts.ContactData;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.ap;
import com.joaomgcd.taskerm.dialog.aq;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.cc;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'd' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:422)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:351)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f8244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final i f8245b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final i f8246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final i f8247d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final i f8248e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final i f8249f;
    public static final i g;
    public static final i h;
    public static final i i;
    public static final i j;
    public static final i k;

    @SuppressLint({"MissingPermission"})
    public static final i l;

    @SuppressLint({"MissingPermission"})
    public static final i m;
    public static final i n;
    public static final i o;
    public static final i p;
    public static final i q;
    public static final i r;
    public static final i s;
    public static final i t;
    public static final i u;
    public static final i v;
    public static final i w;
    public static final i x;
    public static final a y;
    private static final /* synthetic */ i[] z;
    private final c.f.a.m<Activity, c, b.a.l<String>> A;
    private final c.f.a.m<Activity, c, b.a.l<String>> B;
    private final String C;
    private final int D;
    private final boolean E;
    private final boolean F;
    private final boolean G;
    private final c.f.a.m<Activity, c, b.a.l<String>> H;
    private final c.f.a.m<Activity, c, b.a.l<String>> I;
    private final int J;
    private final String[] K;

    public static i valueOf(String str) {
        return (i) Enum.valueOf(i.class, str);
    }

    public static i[] values() {
        return (i[]) z.clone();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected i(String str, int i2, String str2, int i3, boolean z2, boolean z3, boolean z4, c.f.a.m mVar, c.f.a.m mVar2, int i4, String[] strArr) {
        super(str, i2);
        c.f.b.k.b(str2, be.EXTRA_ID);
        c.f.b.k.b(strArr, "neededPermissions");
        this.C = str2;
        this.D = i3;
        this.E = z2;
        this.F = z3;
        this.G = z4;
        this.H = mVar;
        this.I = mVar2;
        this.J = i4;
        this.K = strArr;
        c.f.a.m<Activity, c, b.a.l<String>> mVar3 = this.H;
        this.A = mVar3 != null ? a(mVar3) : null;
        c.f.a.m<Activity, c, b.a.l<String>> mVar4 = this.I;
        this.B = mVar4 != null ? a(mVar4) : null;
    }

    public final String d() {
        return this.C;
    }

    public final int e() {
        return this.D;
    }

    public final boolean f() {
        return this.E;
    }

    public final boolean g() {
        return this.F;
    }

    public final boolean h() {
        return this.G;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* synthetic */ i(String str, int i2, String str2, int i3, boolean z2, boolean z3, boolean z4, c.f.a.m mVar, c.f.a.m mVar2, int i4, String[] strArr, int i5, c.f.b.g gVar) {
        boolean z5 = (i5 & 16) != 0 ? false : z4;
        c.f.a.m mVar3 = (i5 & 32) != 0 ? (c.f.a.m) null : mVar;
        this(str, i2, str2, i3, z2, z3, z5, mVar3, (i5 & 64) != 0 ? mVar3 : mVar2, (i5 & 128) != 0 ? 0 : i4, (i5 & 256) != 0 ? new String[0] : strArr);
    }

    public final int i() {
        return this.J;
    }

    public final String[] j() {
        return this.K;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        i iVar = new i("Text", 0, "t", R.string.pl_text, false, true, false, null, null, 0, null, 496, null);
        f8244a = iVar;
        i iVar2 = new i("Number", 1, "n", R.string.pl_number, false, true, false, null, null, 2, null, 368, null);
        f8245b = iVar2;
        i iVar3 = new i("TrueOrFalse", 2, "b", R.string.pl_true_or_false, false, true, 0 == true ? 1 : 0, AnonymousClass1.f8250a, null, 0, null, 448, null);
        f8246c = iVar3;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        c.f.a.m mVar = null;
        int i2 = 0;
        String[] strArr = null;
        int i3 = 448;
        c.f.b.g gVar = null;
        i iVar4 = new i("YesOrNo", 3, "yn", R.string.pl_yes_or_no, z2, z3, z4, AnonymousClass12.f8256a, mVar, i2, strArr, i3, gVar);
        f8247d = iVar4;
        i iVar5 = new i("OnOrOff", 4, "onoff", R.string.pl_on_or_off, z2, z3, z4, AnonymousClass16.f8265a, mVar, i2, strArr, i3, gVar);
        f8248e = iVar5;
        i iVar6 = new i("File", 5, "f", R.string.pl_file, true, false, true, AnonymousClass17.f8267a, k.a(false, 1, (Object) null), i2, strArr, 384, gVar);
        f8249f = iVar6;
        boolean z5 = true;
        boolean z6 = false;
        boolean z7 = true;
        c.f.a.m mVar2 = null;
        int i4 = 0;
        String[] strArr2 = null;
        int i5 = 448;
        c.f.b.g gVar2 = null;
        i iVar7 = new i("Image", 6, "i", R.string.pl_image, z5, z6, z7, AnonymousClass18.f8269a, mVar2, i4, strArr2, i5, gVar2);
        g = iVar7;
        i iVar8 = new i("Images", 7, "is", R.string.images, z5, z6, z7, AnonymousClass19.f8271a, mVar2, i4, strArr2, i5, gVar2);
        h = iVar8;
        i iVar9 = new i("Directory", 8, "d", R.string.pl_dir, z5, z6, z7, AnonymousClass20.f8275a, k.a(false, 1, (Object) null), i4, strArr2, 384, gVar2);
        i = iVar9;
        boolean z8 = false;
        boolean z9 = false;
        c.f.a.m mVar3 = null;
        int i6 = 0;
        String[] strArr3 = null;
        int i7 = 448;
        c.f.b.g gVar3 = null;
        i iVar10 = new i("WifiSSID", 9, "ws", R.string.test_net_wifi_ssid, z8, z9, z6, AnonymousClass21.f8277a, mVar3, i6, strArr3, i7, gVar3);
        j = iVar10;
        i iVar11 = new i("WifiMac", 10, "wm", R.string.test_net_wifi_mac, z8, z9, z6, AnonymousClass22.f8280a, mVar3, i6, strArr3, i7, gVar3);
        k = iVar11;
        i iVar12 = new i("BluetoothName", 11, "bn", R.string.bt_connect_name_description, z8, z9, z6, AnonymousClass2.f8273a, mVar3, i6, strArr3, i7, gVar3);
        l = iVar12;
        i iVar13 = new i("BluetoothMac", 12, "bn", R.string.bt_connect_address_description, z8, z9, z6, AnonymousClass3.f8283a, mVar3, i6, strArr3, i7, gVar3);
        m = iVar13;
        boolean z10 = true;
        i iVar14 = new i("Contact", 13, "c", R.string.pl_contact, z10, z9, z6, AnonymousClass4.f8285a, mVar3, i6, strArr3, i7, gVar3);
        n = iVar14;
        i iVar15 = new i("ContactNumber", 14, "cn", R.string.pl_contact_number, z10, z9, z6, AnonymousClass5.f8288a, mVar3, i6, strArr3, i7, gVar3);
        o = iVar15;
        i iVar16 = new i("ContactGroup", 15, "cg", R.string.pl_contact_or_contact_group, z10, z9, z6, AnonymousClass6.f8290a, mVar3, i6, strArr3, i7, gVar3);
        p = iVar16;
        boolean z11 = false;
        boolean z12 = true;
        boolean z13 = true;
        i iVar17 = new i("Time", 16, "ti", R.string.pl_time, z11, z12, z13, AnonymousClass7.f8292a, mVar3, i6, strArr3, i7, gVar3);
        q = iVar17;
        i iVar18 = new i("Date", 17, "da", R.string.pl_date, z11, z12, z13, AnonymousClass8.f8294a, mVar3, i6, strArr3, i7, gVar3);
        r = iVar18;
        boolean z14 = true;
        boolean z15 = false;
        boolean z16 = false;
        i iVar19 = new i("App", 18, "a", R.string.pl_app, z14, z15, z16, AnonymousClass9.f8296a, mVar3, i6, strArr3, i7, gVar3);
        s = iVar19;
        i iVar20 = new i("Apps", 19, OutputAnySensor.VAR_PREFIX, R.string.apps, z14, z15, z16, AnonymousClass10.f8252a, mVar3, i6, strArr3, i7, gVar3);
        t = iVar20;
        i iVar21 = new i("AppLauncher", 20, "la", R.string.ml_launcher, z14, z15, z16, AnonymousClass11.f8254a, mVar3, i6, strArr3, i7, gVar3);
        u = iVar21;
        boolean z17 = false;
        i iVar22 = new i("Color", 21, "cl", R.string.pl_colour, z17, z15, true, AnonymousClass13.f8258a, mVar3, i6, strArr3, i7, gVar3);
        v = iVar22;
        boolean z18 = true;
        boolean z19 = false;
        int i8 = 192;
        i iVar23 = new i("CalendarName", 22, "can", R.string.pl_calendar, z17, z18, z19, AnonymousClass14.f8261a, mVar3, i6, bt.f9101c.g(), i8, gVar3);
        w = iVar23;
        i iVar24 = new i("CalendarEntry", 23, "cae", R.string.sn_calendar_entry, z17, z18, z19, AnonymousClass15.f8263a, mVar3, i6, bt.f9101c.g(), i8, gVar3);
        x = iVar24;
        z = new i[]{iVar, iVar2, iVar3, iVar4, iVar5, iVar6, iVar7, iVar8, iVar9, iVar10, iVar11, iVar12, iVar13, iVar14, iVar15, iVar16, iVar17, iVar18, iVar19, iVar20, iVar21, iVar22, iVar23, iVar24};
        y = new a(null);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f8250a = new AnonymousClass1();

        AnonymousClass1() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l<String> lVarC = y.c(activity, cVar.a(), cVar.b(), 0, 8, null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.1.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(x xVar) {
                    c.f.b.k.b(xVar, "it");
                    return xVar.d() ? "true" : "false";
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogTrueFalse(activity…_VALUE else FALSE_VALUE }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$12, reason: invalid class name */
    static final class AnonymousClass12 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass12 f8256a = new AnonymousClass12();

        AnonymousClass12() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l<String> lVarC = y.a(activity, cVar.a(), cVar.b(), 0, 8, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.12.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(x xVar) {
                    c.f.b.k.b(xVar, "it");
                    return xVar.d() ? "yes" : "no";
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogYesNo(activity, pr…YES_VALUE else NO_VALUE }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$16, reason: invalid class name */
    static final class AnonymousClass16 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass16 f8265a = new AnonymousClass16();

        AnonymousClass16() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l<String> lVarC = y.b(activity, cVar.a(), cVar.b(), 0, 8, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.16.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(x xVar) {
                    c.f.b.k.b(xVar, "it");
                    return xVar.d() ? "on" : "off";
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogOnOff(activity, pr…ON_VALUE else OFF_VALUE }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$17, reason: invalid class name */
    static final class AnonymousClass17 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass17 f8267a = new AnonymousClass17();

        AnonymousClass17() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l<String> lVarC = y.a((Context) activity, (String) null, false, cVar.e(), true, false, false, 6, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.17.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(String[] strArr) {
                    c.f.b.k.b(strArr, "it");
                    return (String) c.a.d.c(strArr);
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogSelectFile(activit…REFIX).map { it.first() }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$18, reason: invalid class name */
    static final class AnonymousClass18 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass18 f8269a = new AnonymousClass18();

        AnonymousClass18() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l<String> lVarC = y.a(activity, false, false, false, false, false, false, false, 252, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.18.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(String[] strArr) {
                    c.f.b.k.b(strArr, "it");
                    return (String) c.a.d.c(strArr);
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogSelectImage(activi…false).map { it.first() }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$19, reason: invalid class name */
    static final class AnonymousClass19 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass19 f8271a = new AnonymousClass19();

        AnonymousClass19() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l<String> lVarC = y.a(activity, true, false, false, false, false, false, false, 252, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.19.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(String[] strArr) {
                    c.f.b.k.b(strArr, "it");
                    return c.a.d.a(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null);
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogSelectImage(activi… { it.joinToString(\",\") }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$20, reason: invalid class name */
    static final class AnonymousClass20 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass20 f8275a = new AnonymousClass20();

        AnonymousClass20() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l lVarC = y.a((Context) activity, false, cVar.e(), true, false).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.20.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(String str) {
                    c.f.b.k.b(str, "it");
                    return str;
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogSelectDirectory(ac…ING_SD_PREFIX).map { it }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$21, reason: invalid class name */
    static final class AnonymousClass21 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass21 f8277a = new AnonymousClass21();

        AnonymousClass21() {
            super(2);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$21$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<ScanResult, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f8278a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(ScanResult scanResult) {
                c.f.b.k.b(scanResult, "receiver$0");
                String str = scanResult.SSID;
                c.f.b.k.a((Object) str, "SSID");
                return str;
            }
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l lVarC = y.a(activity, AnonymousClass1.f8278a).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.21.2
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ScanResult scanResult) {
                    c.f.b.k.b(scanResult, "it");
                    return scanResult.SSID;
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPickWifiNetwork(ac… { SSID }.map { it.SSID }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$22, reason: invalid class name */
    static final class AnonymousClass22 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass22 f8280a = new AnonymousClass22();

        AnonymousClass22() {
            super(2);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$22$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<ScanResult, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f8281a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(ScanResult scanResult) {
                c.f.b.k.b(scanResult, "receiver$0");
                return scanResult.BSSID + " (" + scanResult.SSID + ')';
            }
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l lVarC = y.a(activity, AnonymousClass1.f8281a).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.22.2
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ScanResult scanResult) {
                    c.f.b.k.b(scanResult, "it");
                    return scanResult.BSSID;
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPickWifiNetwork(ac…SSID)\" }.map { it.BSSID }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$2, reason: invalid class name */
    static final class AnonymousClass2 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass2 f8273a = new AnonymousClass2();

        AnonymousClass2() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l lVarC = y.n(activity).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.2.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ae<BluetoothDevice> aeVar) {
                    c.f.b.k.b(aeVar, "it");
                    BluetoothDevice bluetoothDeviceB = aeVar.b();
                    String strA = ak.A(bluetoothDeviceB != null ? bluetoothDeviceB.getName() : null);
                    if (strA != null) {
                        return strA;
                    }
                    throw new RuntimeException("Blueooth Device doesn't have a name");
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPairedBluetoothDev…e doesn't have a name\") }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$3, reason: invalid class name */
    static final class AnonymousClass3 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass3 f8283a = new AnonymousClass3();

        AnonymousClass3() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l lVarC = y.n(activity).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.3.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ae<BluetoothDevice> aeVar) {
                    c.f.b.k.b(aeVar, "it");
                    BluetoothDevice bluetoothDeviceB = aeVar.b();
                    String strA = ak.A(bluetoothDeviceB != null ? bluetoothDeviceB.getAddress() : null);
                    if (strA != null) {
                        return strA;
                    }
                    throw new RuntimeException("Blueooth Device doesn't have a name");
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPairedBluetoothDev…e doesn't have a name\") }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$4, reason: invalid class name */
    static final class AnonymousClass4 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass4 f8285a = new AnonymousClass4();

        AnonymousClass4() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(final Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l<String> lVarA = y.a(activity, (String) null, (com.joaomgcd.taskerm.genericaction.b) null, 6, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.4.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ContactData contactData) {
                    c.f.b.k.b(contactData, "it");
                    String name = contactData.getName();
                    if (name != null) {
                        return name;
                    }
                    throw new RuntimeException("Contact doesn't have a name");
                }
            }).a(new b.a.d.g<T, p<? extends R>>() { // from class: com.joaomgcd.taskerm.profile.i.4.2
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final b.a.l<String> apply(String str) {
                    c.f.b.k.b(str, "it");
                    return k.b(str, activity, R.string.pl_contact);
                }
            });
            c.f.b.k.a((Object) lVarA, "dialogPickContact(activi…y, R.string.pl_contact) }");
            return lVarA;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$5, reason: invalid class name */
    static final class AnonymousClass5 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass5 f8288a = new AnonymousClass5();

        AnonymousClass5() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l<String> lVarC = y.a(activity, (String) null, com.joaomgcd.taskerm.genericaction.b.Phonev2, 2, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.5.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ContactData contactData) {
                    c.f.b.k.b(contactData, "it");
                    String number = contactData.getNumber();
                    if (number != null) {
                        return number;
                    }
                    throw new RuntimeException("Contact doesn't have a phone number");
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPickContact(activi… phone number\")\n        }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$6, reason: invalid class name */
    static final class AnonymousClass6 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass6 f8290a = new AnonymousClass6();

        AnonymousClass6() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(final Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l lVarA = y.q(activity).a((b.a.d.g<? super String, ? extends p<? extends R>>) new b.a.d.g<T, p<? extends R>>() { // from class: com.joaomgcd.taskerm.profile.i.6.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final b.a.l<String> apply(String str) {
                    c.f.b.k.b(str, "it");
                    return k.b(str, activity, R.string.pl_contact_or_contact_group);
                }
            });
            c.f.b.k.a((Object) lVarA, "dialogPickContactOrGroup…ntact_or_contact_group) }");
            return lVarA;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$7, reason: invalid class name */
    static final class AnonymousClass7 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass7 f8292a = new AnonymousClass7();

        AnonymousClass7() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            final String str = ".";
            List<String> listA = ak.a((CharSequence) cVar.e(), ".");
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listA, 10));
            Iterator<T> it = listA.iterator();
            while (it.hasNext()) {
                arrayList.add(n.c((String) it.next()));
            }
            ArrayList arrayList2 = arrayList;
            Integer num = (Integer) c.a.j.a((List) arrayList2, 0);
            Integer num2 = (Integer) c.a.j.a((List) arrayList2, 1);
            b.a.l<String> lVarC = y.a(activity, (num == null || num2 == null) ? null : new aq(num.intValue(), num2.intValue()), (String) null, c.a.j.a(c.a.j.e(ak.A(cVar.a()), ak.A(cVar.b())), " - ", null, null, 0, null, null, 62, null), 4, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.7.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(aq aqVar) {
                    c.f.b.k.b(aqVar, "it");
                    return aj.b(aqVar.a()) + str + aj.b(aqVar.b());
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPickTime(activity,…${it.minute.pad2With0}\" }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$8, reason: invalid class name */
    static final class AnonymousClass8 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass8 f8294a = new AnonymousClass8();

        AnonymousClass8() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            List<String> listA = ak.a((CharSequence) cVar.e(), "-");
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listA, 10));
            Iterator<T> it = listA.iterator();
            while (it.hasNext()) {
                arrayList.add(n.c((String) it.next()));
            }
            ArrayList arrayList2 = arrayList;
            Integer num = (Integer) c.a.j.a((List) arrayList2, 0);
            Integer num2 = (Integer) c.a.j.a((List) arrayList2, 1);
            Integer num3 = (Integer) c.a.j.a((List) arrayList2, 2);
            b.a.l<String> lVarC = y.a(activity, (num == null || num2 == null || num3 == null) ? null : new ap(num.intValue(), num2.intValue(), num3.intValue()), (String) null, k.c(cVar), 4, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.8.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ap apVar) {
                    c.f.b.k.b(apVar, "it");
                    return aj.c(apVar.a()) + '-' + aj.b(apVar.b()) + '-' + aj.b(apVar.c());
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPickDate(activity,…0}-${it.day.pad2With0}\" }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$9, reason: invalid class name */
    static final class AnonymousClass9 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass9 f8296a = new AnonymousClass9();

        AnonymousClass9() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l<String> lVarC = y.a((Context) activity, (Intent) null, (List) null, false, 14, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.9.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(cq<String, ad> cqVar) {
                    c.f.b.k.b(cqVar, "it");
                    return cqVar.c();
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogSelectApp(activity…map { it.payloadOrThrow }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$10, reason: invalid class name */
    static final class AnonymousClass10 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass10 f8252a = new AnonymousClass10();

        AnonymousClass10() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l<String> lVarC = y.a((Context) activity, (Intent) null, (List) null, true, 6, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.10.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(cq<String, ad> cqVar) {
                    c.f.b.k.b(cqVar, "it");
                    return cqVar.c();
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogSelectApp(activity…map { it.payloadOrThrow }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$11, reason: invalid class name */
    static final class AnonymousClass11 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass11 f8254a = new AnonymousClass11();

        AnonymousClass11() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "<anonymous parameter 1>");
            b.a.l lVarC = y.a((Context) activity).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.11.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(cq<String, ad> cqVar) {
                    c.f.b.k.b(cqVar, "it");
                    return cqVar.c();
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogSelectLauncher(act…map { it.payloadOrThrow }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$13, reason: invalid class name */
    static final class AnonymousClass13 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass13 f8258a = new AnonymousClass13();

        AnonymousClass13() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l lVarC = y.f(activity, cc.a(cVar.e(), cc.c("#FF000000"))).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.13.1

                /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$13$1$1, reason: invalid class name and collision with other inner class name */
                static final class C02211 extends c.f.b.l implements c.f.a.b<x, String> {

                    /* JADX INFO: renamed from: a, reason: collision with root package name */
                    public static final C02211 f8260a = new C02211();

                    C02211() {
                        super(1);
                    }

                    @Override // c.f.a.b
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final String invoke(x xVar) {
                        c.f.b.k.b(xVar, "receiver$0");
                        String strK = xVar.k();
                        return strK != null ? strK : "";
                    }
                }

                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(x xVar) {
                    c.f.b.k.b(xVar, "it");
                    return (String) x.a(xVar, null, C02211.f8260a, 1, null);
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPickColor(activity…OrThrow { color ?: \"\" } }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$14, reason: invalid class name */
    static final class AnonymousClass14 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass14 f8261a = new AnonymousClass14();

        AnonymousClass14() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l lVarC = y.a(activity, (com.joaomgcd.taskerm.util.h) ak.D(k.c(cVar))).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.14.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ae<String> aeVar) {
                    c.f.b.k.b(aeVar, "it");
                    String strB = aeVar.b();
                    if (strB != null) {
                        return strB;
                    }
                    throw new RuntimeException("No calendar selected");
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPickCalendar(activ…\"No calendar selected\") }");
            return lVarC;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.i$15, reason: invalid class name */
    static final class AnonymousClass15 extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass15 f8263a = new AnonymousClass15();

        AnonymousClass15() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            b.a.l lVarC = y.b(activity, ak.D(k.c(cVar))).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.i.15.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ae<String> aeVar) {
                    c.f.b.k.b(aeVar, "it");
                    String strB = aeVar.b();
                    if (strB != null) {
                        return strB;
                    }
                    throw new RuntimeException("No calendar selected");
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPickCalendarEntry(…\"No calendar selected\") }");
            return lVarC;
        }
    }

    public final boolean a() {
        return this.A != null;
    }

    public final String a(Context context) {
        c.f.b.k.b(context, "context");
        return aj.a(this.D, context, new Object[0]);
    }

    static final class b extends c.f.b.l implements c.f.a.m<Activity, c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.m f8298a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ i f8299b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(c.f.a.m mVar, i iVar) {
            super(2);
            this.f8298a = mVar;
            this.f8299b = iVar;
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(final Activity activity, final c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            String[] strArrJ = this.f8299b.j();
            b.a.l lVarA = new bt(activity, 111, (String[]) Arrays.copyOf(strArrJ, strArrJ.length)).a(activity).a((b.a.d.g<? super cl, ? extends p<? extends R>>) new b.a.d.g<T, p<? extends R>>() { // from class: com.joaomgcd.taskerm.profile.i.b.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final b.a.l<String> apply(cl clVar) {
                    c.f.b.k.b(clVar, "it");
                    return (b.a.l) b.this.f8298a.invoke(activity, cVar);
                }
            });
            c.f.b.k.a((Object) lVarA, "Permissions(activity, 11…ivity, profileVariable) }");
            return lVarA;
        }
    }

    private final c.f.a.m<Activity, c, b.a.l<String>> a(c.f.a.m<? super Activity, ? super c, ? extends b.a.l<String>> mVar) {
        if (mVar != null) {
            return new b(mVar, this);
        }
        return null;
    }

    public final c.f.a.m<Activity, c, b.a.l<String>> b() {
        return this.A;
    }

    public final c.f.a.m<Activity, c, b.a.l<String>> c() {
        return this.B;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final i a(String str) {
            c.f.b.k.b(str, "typeId");
            for (i iVar : i.values()) {
                if (c.f.b.k.a((Object) iVar.d(), (Object) str)) {
                    return iVar;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }
    }
}
