package com.joaomgcd.taskerm.action.file;

import c.f.b.g;
import com.joaomgcd.taskerm.helper.actions.execute.w;
import com.joaomgcd.taskerm.inputoutput.b;

/* JADX INFO: loaded from: classes.dex */
public final class a extends w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class<OutputBrowseFiles> f5298a;

    /* JADX WARN: Multi-variable type inference failed */
    public a() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @b(a = 3)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public /* synthetic */ a(Class cls, int i, g gVar) {
        this((i & 1) != 0 ? OutputBrowseFiles.class : cls);
    }

    public final Class<OutputBrowseFiles> getOutputClass() {
        return this.f5298a;
    }

    public final void setOutputClass(Class<OutputBrowseFiles> cls) {
        this.f5298a = cls;
    }

    public a(Class<OutputBrowseFiles> cls) {
        this.f5298a = cls;
    }
}
