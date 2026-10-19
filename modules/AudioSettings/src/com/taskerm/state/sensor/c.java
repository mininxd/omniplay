package com.joaomgcd.taskerm.state.sensor;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8681a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Integer f8682b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Integer f8683c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Integer f8684d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Class<OutputSleeping> f8685e;

    public c() {
        this(null, null, null, null, 15, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.max_light_confidence_explained)
    public static /* synthetic */ void maxLight$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, e = R.string.max_motion_confidence_explained)
    public static /* synthetic */ void maxMotion$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, e = R.string.min_sleep_confidence_explained)
    public static /* synthetic */ void minConfidence$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public c(Integer num, Integer num2, Integer num3, Class<OutputSleeping> cls) {
        this.f8682b = num;
        this.f8683c = num2;
        this.f8684d = num3;
        this.f8685e = cls;
    }

    public /* synthetic */ c(Integer num, Integer num2, Integer num3, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2, (i & 4) != 0 ? (Integer) null : num3, (i & 8) != 0 ? OutputSleeping.class : cls);
    }

    public final Integer getMinConfidence() {
        return this.f8682b;
    }

    public final void setMinConfidence(Integer num) {
        this.f8682b = num;
    }

    public final Integer getMaxLight() {
        return this.f8683c;
    }

    public final void setMaxLight(Integer num) {
        this.f8683c = num;
    }

    public final Integer getMaxMotion() {
        return this.f8684d;
    }

    public final void setMaxMotion(Integer num) {
        this.f8684d = num;
    }

    public final Class<OutputSleeping> getOutputClass() {
        return this.f8685e;
    }

    public final void setOutputClass(Class<OutputSleeping> cls) {
        this.f8685e = cls;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }
}
