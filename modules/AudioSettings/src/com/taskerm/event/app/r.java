package com.joaomgcd.taskerm.event.app;

import java.util.ArrayList;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6639a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f6640b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f6641c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Boolean f6642d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f6643e;

    public r() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, e = R.string.event_command_explained)
    public static /* synthetic */ void command$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void structuredOutput$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.event_command_variables_explained)
    public static /* synthetic */ void variables$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, e = R.string.event_command_variables_last_is_array_explained)
    public static /* synthetic */ void variablesLastIsArray$annotations() {
    }

    public r(String str, String str2, Boolean bool, Boolean bool2, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f6639a = str;
        this.f6640b = str2;
        this.f6641c = bool;
        this.f6642d = bool2;
        this.f6643e = jVar;
    }

    public /* synthetic */ r(String str, String str2, Boolean bool, Boolean bool2, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (Boolean) null : bool, (i & 8) != 0 ? (Boolean) null : bool2, (i & 16) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getCommand() {
        return this.f6639a;
    }

    public final void setCommand(String str) {
        this.f6639a = str;
    }

    public final String getVariables() {
        return this.f6640b;
    }

    public final void setVariables(String str) {
        this.f6640b = str;
    }

    public final Boolean getVariablesLastIsArray() {
        return this.f6641c;
    }

    public final void setVariablesLastIsArray(Boolean bool) {
        this.f6641c = bool;
    }

    public final Boolean getStructuredOutput() {
        return this.f6642d;
    }

    public final void setStructuredOutput(Boolean bool) {
        this.f6642d = bool;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f6643e;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f6643e = jVar;
    }

    public final List<String> getVariablesList() {
        String str = this.f6640b;
        if (str == null || str.length() == 0) {
            return c.a.j.a();
        }
        List listB = c.l.n.b((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listB) {
            if (((String) obj).length() >= 4) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final boolean getVariablesLastIsArrayNotNull() {
        Boolean bool = this.f6641c;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
