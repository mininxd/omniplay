package com.joaomgcd.taskerm.action.system;

import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class au {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5779a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Integer f5780b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f5781c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Class<OutputAnySensor> f5782d;

    public au() {
        this(null, null, null, null, 15, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, e = R.string.convert_orientation_help)
    public static /* synthetic */ void convertOrientation$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void timeout$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void typeString$annotations() {
    }

    public au(String str, Integer num, Boolean bool, Class<OutputAnySensor> cls) {
        this.f5779a = str;
        this.f5780b = num;
        this.f5781c = bool;
        this.f5782d = cls;
    }

    public /* synthetic */ au(String str, Integer num, Boolean bool, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Integer) null : num, (i & 4) != 0 ? (Boolean) null : bool, (i & 8) != 0 ? OutputAnySensor.class : cls);
    }

    public final String getTypeString() {
        return this.f5779a;
    }

    public final void setTypeString(String str) {
        this.f5779a = str;
    }

    public final Integer getTimeout() {
        return this.f5780b;
    }

    public final void setTimeout(Integer num) {
        this.f5780b = num;
    }

    public final Boolean getConvertOrientation() {
        return this.f5781c;
    }

    public final void setConvertOrientation(Boolean bool) {
        this.f5781c = bool;
    }

    public final Class<OutputAnySensor> getOutputClass() {
        return this.f5782d;
    }

    public final void setOutputClass(Class<OutputAnySensor> cls) {
        this.f5782d = cls;
    }
}
