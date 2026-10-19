package com.joaomgcd.taskerm.action.input;

/* JADX INFO: loaded from: classes.dex */
public final class be extends bc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5473a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f5474b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public be(String str) {
        super(0.0f, 1, null);
        c.f.b.k.b(str, "task");
        this.f5474b = str;
        this.f5473a = "task(" + this.f5474b + ')';
    }

    public String toString() {
        return "Task: " + this.f5474b;
    }

    @Override // com.joaomgcd.taskerm.action.input.bc
    public String b() {
        return this.f5473a;
    }
}
