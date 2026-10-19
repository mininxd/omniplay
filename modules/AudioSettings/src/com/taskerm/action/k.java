package com.joaomgcd.taskerm.action;

import c.f.b.l;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.dm;

/* JADX INFO: loaded from: classes.dex */
public interface k {
    b.a.b a(boolean z, long j, long j2, long j3);

    cl a(boolean z, i iVar);

    boolean a();

    public static final class a {

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.k$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<Boolean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ k f5516a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ boolean f5517b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(k kVar, boolean z) {
                super(0);
                this.f5516a = kVar;
                this.f5517b = z;
            }

            @Override // c.f.a.a
            public /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(a());
            }

            public final boolean a() {
                return this.f5516a.a() == this.f5517b;
            }
        }

        public static b.a.b a(k kVar, boolean z, long j, long j2, long j3) {
            return dm.a(new AnonymousClass1(kVar, z), j, j2, j3);
        }

        public static /* synthetic */ b.a.b a(k kVar, boolean z, long j, long j2, long j3, int i, Object obj) {
            if (obj == null) {
                return kVar.a(z, (i & 2) != 0 ? 100L : j, (i & 4) != 0 ? 5000L : j2, (i & 8) != 0 ? 0L : j3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: waitForState");
        }
    }
}
