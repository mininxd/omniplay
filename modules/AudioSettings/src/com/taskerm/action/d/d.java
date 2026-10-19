package com.joaomgcd.taskerm.action.d;

import c.f.b.g;
import com.joaomgcd.taskerm.helper.actions.execute.w;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class d extends w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f5066c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Boolean f5067d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5068e;

    public d() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void _stop$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.help_return_passthrough)
    public static /* synthetic */ void _variablePassthrough$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, e = R.string.help_return_passthrough_replace)
    public static /* synthetic */ void _variablePassthroughReplace$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void value$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, d = true, e = R.string.help_return_passthrough_list)
    public static /* synthetic */ void variablePassthroughList$annotations() {
    }

    public /* synthetic */ d(String str, Boolean bool, Boolean bool2, Boolean bool3, String str2, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Boolean) null : bool, (i & 4) != 0 ? (Boolean) null : bool2, (i & 8) != 0 ? (Boolean) null : bool3, (i & 16) != 0 ? (String) null : str2);
    }

    public final String getValue() {
        return this.f5064a;
    }

    public final void setValue(String str) {
        this.f5064a = str;
    }

    public final Boolean get_stop() {
        return this.f5065b;
    }

    public final void set_stop(Boolean bool) {
        this.f5065b = bool;
    }

    public final Boolean get_variablePassthrough() {
        return this.f5066c;
    }

    public final void set_variablePassthrough(Boolean bool) {
        this.f5066c = bool;
    }

    public final Boolean get_variablePassthroughReplace() {
        return this.f5067d;
    }

    public final void set_variablePassthroughReplace(Boolean bool) {
        this.f5067d = bool;
    }

    public final String getVariablePassthroughList() {
        return this.f5068e;
    }

    public final void setVariablePassthroughList(String str) {
        this.f5068e = str;
    }

    public d(String str, Boolean bool, Boolean bool2, Boolean bool3, String str2) {
        this.f5064a = str;
        this.f5065b = bool;
        this.f5066c = bool2;
        this.f5067d = bool3;
        this.f5068e = str2;
    }

    public final boolean getStop() {
        Boolean bool = this.f5065b;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public final boolean getVariablePassthrough() {
        Boolean bool = this.f5066c;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean getVariablePassthroughReplace() {
        Boolean bool = this.f5067d;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
