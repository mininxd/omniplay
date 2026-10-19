package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import com.joaomgcd.taskerm.util.cf;

/* JADX INFO: loaded from: classes.dex */
public abstract class y extends t {
    public abstract b.a.l<String> a(String str, long j);

    public abstract b.a.l<cf> b(String str, long j);

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8566a = new a();

        a() {
        }

        public final boolean a(cf cfVar) {
            c.f.b.k.b(cfVar, "it");
            return true;
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            return Boolean.valueOf(a((cf) obj));
        }
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8567a = new b();

        b() {
        }

        public final boolean a(cf cfVar) {
            c.f.b.k.b(cfVar, "it");
            return true;
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            return Boolean.valueOf(a((cf) obj));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(Context context) {
        super(context);
        c.f.b.k.b(context, "context");
    }

    public final String e() {
        return b().a();
    }

    public static /* synthetic */ b.a.l a(y yVar, String str, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: runShellCommandResult");
        }
        if ((i & 2) != 0) {
            j = 3000;
        }
        return yVar.b(str, j);
    }

    public static /* synthetic */ b.a.l b(y yVar, String str, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: runStringResult");
        }
        if ((i & 2) != 0) {
            j = 3000;
        }
        return yVar.a(str, j);
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    protected b.a.l<Boolean> b(String str, String str2) {
        c.f.b.k.b(str, "key");
        c.f.b.k.b(str2, "value");
        b.a.l<Boolean> lVarC = a(this, "settings put " + e() + ' ' + str + " '" + str2 + '\'', 0L, 2, null).c(b.f8567a);
        c.f.b.k.a((Object) lVarC, "runShellCommandResult(\"s…y '$value'\").map { true }");
        return lVarC;
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    public b.a.l<String> a(String str) {
        c.f.b.k.b(str, "key");
        return b(this, "settings get " + e() + ' ' + str, 0L, 2, null);
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    public b.a.l<Boolean> b(String str) {
        c.f.b.k.b(str, "key");
        b.a.l<Boolean> lVarC = a(this, "settings delete " + e() + ' ' + str, 0L, 2, null).c(a.f8566a);
        c.f.b.k.a((Object) lVarC, "runShellCommandResult(\"s…Table $key\").map { true }");
        return lVarC;
    }
}
