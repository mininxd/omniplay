package com.joaomgcd.taskerm.action.e;

import com.joaomgcd.taskerm.helper.actions.execute.w;

/* JADX INFO: loaded from: classes.dex */
public final class k extends w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5164a;

    /* JADX WARN: Multi-variable type inference failed */
    public k() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void command$annotations() {
    }

    public /* synthetic */ k(String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public final String getCommand() {
        return this.f5164a;
    }

    public final void setCommand(String str) {
        this.f5164a = str;
    }

    public k(String str) {
        this.f5164a = str;
    }
}
