package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5750a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5751b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5752c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Integer f5753d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Boolean f5754e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5755f;
    private Class<OutputADBWifi> g;

    public ak() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void command$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void host$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void port$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6, e = R.string.help_adb_wifi_result_encoding)
    public static /* synthetic */ void resultEncoding$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void timeout$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5, e = R.string.help_adb_wifi_enable_debugging)
    public static /* synthetic */ void toggleDebugging$annotations() {
    }

    public ak(String str, String str2, String str3, Integer num, Boolean bool, String str4, Class<OutputADBWifi> cls) {
        this.f5750a = str;
        this.f5751b = str2;
        this.f5752c = str3;
        this.f5753d = num;
        this.f5754e = bool;
        this.f5755f = str4;
        this.g = cls;
    }

    public /* synthetic */ ak(String str, String str2, String str3, Integer num, Boolean bool, String str4, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? (Boolean) null : bool, (i & 32) != 0 ? (String) null : str4, (i & 64) != 0 ? OutputADBWifi.class : cls);
    }

    public final String getCommand() {
        return this.f5750a;
    }

    public final void setCommand(String str) {
        this.f5750a = str;
    }

    public final String getHost() {
        return this.f5751b;
    }

    public final void setHost(String str) {
        this.f5751b = str;
    }

    public final String getPort() {
        return this.f5752c;
    }

    public final void setPort(String str) {
        this.f5752c = str;
    }

    public final Integer getTimeout() {
        return this.f5753d;
    }

    public final void setTimeout(Integer num) {
        this.f5753d = num;
    }

    public final Boolean getToggleDebugging() {
        return this.f5754e;
    }

    public final void setToggleDebugging(Boolean bool) {
        this.f5754e = bool;
    }

    public final String getResultEncoding() {
        return this.f5755f;
    }

    public final void setResultEncoding(String str) {
        this.f5755f = str;
    }

    public final Class<OutputADBWifi> getOutputClass() {
        return this.g;
    }

    public final void setOutputClass(Class<OutputADBWifi> cls) {
        this.g = cls;
    }

    public final int getPortNotNull(Context context) {
        Integer numC;
        c.f.b.k.b(context, "context");
        String str = this.f5752c;
        return (str == null || (numC = c.l.n.c(str)) == null) ? com.joaomgcd.taskerm.settings.ad.a(context) : numC.intValue();
    }

    public final String getHostNotNull() {
        String str = this.f5751b;
        return str != null ? str : "localhost";
    }

    public final boolean getToggleDebuggingNotNull() {
        Boolean bool = this.f5754e;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
