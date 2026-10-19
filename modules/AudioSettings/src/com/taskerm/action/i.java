package com.joaomgcd.taskerm.action;

import com.joaomgcd.taskerm.inputoutput.Toggle;

/* JADX INFO: loaded from: classes.dex */
@com.joaomgcd.taskerm.r.b
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Toggle f5356a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<j> f5357b;

    /* JADX WARN: Multi-variable type inference failed */
    public i() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void toggle$annotations() {
    }

    public i(Toggle toggle, Class<j> cls) {
        c.f.b.k.b(toggle, "toggle");
        this.f5356a = toggle;
        this.f5357b = cls;
    }

    public /* synthetic */ i(Toggle toggle, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? Toggle.Off : toggle, (i & 2) != 0 ? j.class : cls);
    }

    public final Toggle getToggle() {
        return this.f5356a;
    }

    public final void setToggle(Toggle toggle) {
        c.f.b.k.b(toggle, "<set-?>");
        this.f5356a = toggle;
    }

    public final Class<j> getOutputClass() {
        return this.f5357b;
    }

    public final void setOutputClass(Class<j> cls) {
        this.f5357b = cls;
    }
}
