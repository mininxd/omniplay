package com.joaomgcd.taskerm.action.location;

import com.joaomgcd.taskerm.location.MapType;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5534a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5535b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5536c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private MapType f5537d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Class<OutputPickLocation> f5538e;

    public i() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void initialLocation$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void mapType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void selectRadius$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void title$annotations() {
    }

    public i(String str, Boolean bool, String str2, MapType mapType, Class<OutputPickLocation> cls) {
        this.f5534a = str;
        this.f5535b = bool;
        this.f5536c = str2;
        this.f5537d = mapType;
        this.f5538e = cls;
    }

    public /* synthetic */ i(String str, Boolean bool, String str2, MapType mapType, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Boolean) null : bool, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (MapType) null : mapType, (i & 16) != 0 ? OutputPickLocation.class : cls);
    }

    public final String getTitle() {
        return this.f5534a;
    }

    public final void setTitle(String str) {
        this.f5534a = str;
    }

    public final Boolean getSelectRadius() {
        return this.f5535b;
    }

    public final void setSelectRadius(Boolean bool) {
        this.f5535b = bool;
    }

    public final String getInitialLocation() {
        return this.f5536c;
    }

    public final void setInitialLocation(String str) {
        this.f5536c = str;
    }

    public final MapType getMapType() {
        return this.f5537d;
    }

    public final void setMapType(MapType mapType) {
        this.f5537d = mapType;
    }

    public final Class<OutputPickLocation> getOutputClass() {
        return this.f5538e;
    }

    public final void setOutputClass(Class<OutputPickLocation> cls) {
        this.f5538e = cls;
    }

    public final com.joaomgcd.taskerm.location.h getLocationAndRadius() {
        return com.joaomgcd.taskerm.location.h.f7685a.a(this.f5536c);
    }
}
