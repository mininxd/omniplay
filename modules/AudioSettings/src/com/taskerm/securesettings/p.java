package com.joaomgcd.taskerm.securesettings;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import com.joaomgcd.taskerm.util.ak;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public enum p {
    Global("global", Settings.Global.class, AnonymousClass1.f8539a, AnonymousClass5.f8546a, AnonymousClass6.f8547a, AnonymousClass7.f8548a),
    Secure("secure", Settings.Secure.class, AnonymousClass8.f8549a, AnonymousClass9.f8550a, AnonymousClass10.f8540a, AnonymousClass11.f8541a),
    System("system", Settings.System.class, AnonymousClass12.f8542a, AnonymousClass2.f8543a, AnonymousClass3.f8544a, AnonymousClass4.f8545a);


    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f8537e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Class<?> f8538f;
    private final c.f.a.q<Context, ContentResolver, String, String> g;
    private final c.f.a.q<ContentResolver, String, String, Boolean> h;
    private final c.f.a.m<ContentResolver, String, Boolean> i;
    private final c.f.a.a<HashSet<String>> j;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$5, reason: invalid class name */
    static final class AnonymousClass5 extends c.f.b.l implements c.f.a.q<ContentResolver, String, String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass5 f8546a = new AnonymousClass5();

        AnonymousClass5() {
            super(3);
        }

        @Override // c.f.a.q
        public /* synthetic */ Boolean a(ContentResolver contentResolver, String str, String str2) {
            return Boolean.valueOf(a2(contentResolver, str, str2));
        }

        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public final boolean a2(ContentResolver contentResolver, String str, String str2) {
            c.f.b.k.b(contentResolver, "cr");
            c.f.b.k.b(str, "key");
            c.f.b.k.b(str2, "value");
            return Settings.Global.putString(contentResolver, str, str2);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$6, reason: invalid class name */
    static final class AnonymousClass6 extends c.f.b.l implements c.f.a.m<ContentResolver, String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass6 f8547a = new AnonymousClass6();

        AnonymousClass6() {
            super(2);
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(ContentResolver contentResolver, String str) {
            return Boolean.valueOf(a(contentResolver, str));
        }

        public final boolean a(ContentResolver contentResolver, String str) {
            c.f.b.k.b(contentResolver, "cr");
            c.f.b.k.b(str, "key");
            return Settings.Global.putString(contentResolver, str, null);
        }
    }

    p(String str, Class cls, c.f.a.q qVar, c.f.a.q qVar2, c.f.a.m mVar, c.f.a.a aVar) {
        c.f.b.k.b(str, "tableName");
        c.f.b.k.b(cls, "settingsClass");
        c.f.b.k.b(qVar, "getter");
        c.f.b.k.b(qVar2, "setter");
        c.f.b.k.b(mVar, "deleter");
        c.f.b.k.b(aVar, "manualKeys");
        this.f8537e = str;
        this.f8538f = cls;
        this.g = qVar;
        this.h = qVar2;
        this.i = mVar;
        this.j = aVar;
    }

    public final String a() {
        return this.f8537e;
    }

    public final c.f.a.q<Context, ContentResolver, String, String> b() {
        return this.g;
    }

    public final c.f.a.q<ContentResolver, String, String, Boolean> c() {
        return this.h;
    }

    public final c.f.a.m<ContentResolver, String, Boolean> d() {
        return this.i;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.q<Context, ContentResolver, String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f8539a = new AnonymousClass1();

        AnonymousClass1() {
            super(3);
        }

        @Override // c.f.a.q
        public final String a(Context context, ContentResolver contentResolver, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(contentResolver, "cr");
            c.f.b.k.b(str, "key");
            String strA = ak.A(Settings.Global.getString(contentResolver, str));
            return strA != null ? strA : k.d(context, p.Global, str);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$7, reason: invalid class name */
    static final class AnonymousClass7 extends c.f.b.l implements c.f.a.a<HashSet<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass7 f8548a = new AnonymousClass7();

        AnonymousClass7() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashSet<String> invoke() {
            return new HashSet<>();
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$10, reason: invalid class name */
    static final class AnonymousClass10 extends c.f.b.l implements c.f.a.m<ContentResolver, String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass10 f8540a = new AnonymousClass10();

        AnonymousClass10() {
            super(2);
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(ContentResolver contentResolver, String str) {
            return Boolean.valueOf(a(contentResolver, str));
        }

        public final boolean a(ContentResolver contentResolver, String str) {
            c.f.b.k.b(contentResolver, "cr");
            c.f.b.k.b(str, "key");
            return Settings.Secure.putString(contentResolver, str, null);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$9, reason: invalid class name */
    static final class AnonymousClass9 extends c.f.b.l implements c.f.a.q<ContentResolver, String, String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass9 f8550a = new AnonymousClass9();

        AnonymousClass9() {
            super(3);
        }

        @Override // c.f.a.q
        public /* synthetic */ Boolean a(ContentResolver contentResolver, String str, String str2) {
            return Boolean.valueOf(a2(contentResolver, str, str2));
        }

        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public final boolean a2(ContentResolver contentResolver, String str, String str2) {
            c.f.b.k.b(contentResolver, "cr");
            c.f.b.k.b(str, "key");
            c.f.b.k.b(str2, "value");
            return Settings.Secure.putString(contentResolver, str, str2);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$8, reason: invalid class name */
    static final class AnonymousClass8 extends c.f.b.l implements c.f.a.q<Context, ContentResolver, String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass8 f8549a = new AnonymousClass8();

        AnonymousClass8() {
            super(3);
        }

        @Override // c.f.a.q
        public final String a(Context context, ContentResolver contentResolver, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(contentResolver, "cr");
            c.f.b.k.b(str, "key");
            String strA = ak.A(Settings.Secure.getString(contentResolver, str));
            return strA != null ? strA : k.d(context, p.Secure, str);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$11, reason: invalid class name */
    static final class AnonymousClass11 extends c.f.b.l implements c.f.a.a<HashSet<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass11 f8541a = new AnonymousClass11();

        AnonymousClass11() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashSet<String> invoke() {
            return new HashSet<>();
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$2, reason: invalid class name */
    static final class AnonymousClass2 extends c.f.b.l implements c.f.a.q<ContentResolver, String, String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass2 f8543a = new AnonymousClass2();

        AnonymousClass2() {
            super(3);
        }

        @Override // c.f.a.q
        public /* synthetic */ Boolean a(ContentResolver contentResolver, String str, String str2) {
            return Boolean.valueOf(a2(contentResolver, str, str2));
        }

        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        public final boolean a2(ContentResolver contentResolver, String str, String str2) {
            c.f.b.k.b(contentResolver, "cr");
            c.f.b.k.b(str, "key");
            c.f.b.k.b(str2, "value");
            return Settings.System.putString(contentResolver, str, str2);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$3, reason: invalid class name */
    static final class AnonymousClass3 extends c.f.b.l implements c.f.a.m<ContentResolver, String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass3 f8544a = new AnonymousClass3();

        AnonymousClass3() {
            super(2);
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(ContentResolver contentResolver, String str) {
            return Boolean.valueOf(a(contentResolver, str));
        }

        public final boolean a(ContentResolver contentResolver, String str) {
            c.f.b.k.b(contentResolver, "cr");
            c.f.b.k.b(str, "key");
            return Settings.System.putString(contentResolver, str, null);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$12, reason: invalid class name */
    static final class AnonymousClass12 extends c.f.b.l implements c.f.a.q<Context, ContentResolver, String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass12 f8542a = new AnonymousClass12();

        AnonymousClass12() {
            super(3);
        }

        @Override // c.f.a.q
        public final String a(Context context, ContentResolver contentResolver, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(contentResolver, "cr");
            c.f.b.k.b(str, "key");
            String strA = ak.A(Settings.System.getString(contentResolver, str));
            return strA != null ? strA : k.d(context, p.System, str);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.p$4, reason: invalid class name */
    static final class AnonymousClass4 extends c.f.b.l implements c.f.a.a<HashSet<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass4 f8545a = new AnonymousClass4();

        AnonymousClass4() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashSet<String> invoke() {
            return new HashSet<>();
        }
    }

    public final List<Setting> a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "tag");
        return k.c(context, this, str);
    }
}
