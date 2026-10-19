package com.joaomgcd.taskerm.util;

import android.os.ParcelUuid;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f9394a = new a(null);

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        private final ParcelUuid a(String str) {
            return new ParcelUuid(UUID.fromString(str));
        }

        public final ParcelUuid a() {
            return w.f9394a.a("00001108-0000-1000-8000-00805f9b34fb");
        }

        public final ParcelUuid b() {
            return w.f9394a.a("0000111e-0000-1000-8000-00805f9b34fb");
        }

        public final ParcelUuid c() {
            return w.f9394a.a("0000110b-0000-1000-8000-00805f9b34fb");
        }

        public final ParcelUuid d() {
            return w.f9394a.a("0000110d-0000-1000-8000-00805f9b34fb");
        }

        public final ParcelUuid e() {
            return w.f9394a.a("0000110a-0000-1000-8000-00805f9b34fb");
        }

        public final ParcelUuid f() {
            return w.f9394a.a("00001124-0000-1000-8000-00805f9b34fb");
        }

        public final ParcelUuid[] g() {
            return new ParcelUuid[]{w.f9394a.a(), w.f9394a.b()};
        }

        public final ParcelUuid[] h() {
            return new ParcelUuid[]{w.f9394a.c(), w.f9394a.d()};
        }

        public final ParcelUuid[] i() {
            return new ParcelUuid[]{w.f9394a.e()};
        }

        public final ParcelUuid[] j() {
            return new ParcelUuid[]{w.f9394a.f()};
        }
    }
}
