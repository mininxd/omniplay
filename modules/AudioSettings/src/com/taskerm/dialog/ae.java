package com.joaomgcd.taskerm.dialog;

/* JADX INFO: loaded from: classes.dex */
public class ae<T> extends p<ab> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final T f6235a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Integer f6236b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ae(ab abVar, T t, Integer num) {
        super(abVar, null);
        c.f.b.k.b(abVar, "message");
        this.f6235a = t;
        this.f6236b = num;
    }

    public /* synthetic */ ae(ab abVar, Object obj, Integer num, int i, c.f.b.g gVar) {
        this(abVar, (i & 2) != 0 ? null : obj, (i & 4) != 0 ? (Integer) null : num);
    }

    public final T b() {
        return this.f6235a;
    }

    public final Integer c() {
        return this.f6236b;
    }

    public ae() {
        this(new ab(1, null, 2, null), null, null, 6, null);
    }
}
