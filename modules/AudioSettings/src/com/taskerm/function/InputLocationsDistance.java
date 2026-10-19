package com.joaomgcd.taskerm.function;

import c.f.b.k;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputLocationsDistance {
    private final String latitude1;
    private final String latitude2;
    private final String longitude1;
    private final String longitude2;

    @FunctionInput(explanationResId = R.string.distance_arg_1, index = 0)
    public static /* synthetic */ void latitude1$annotations() {
    }

    @FunctionInput(explanationResId = R.string.distance_arg_3, index = 2)
    public static /* synthetic */ void latitude2$annotations() {
    }

    @FunctionInput(explanationResId = R.string.distance_arg_2, index = 1)
    public static /* synthetic */ void longitude1$annotations() {
    }

    @FunctionInput(explanationResId = R.string.distance_arg_4, index = 3)
    public static /* synthetic */ void longitude2$annotations() {
    }

    public InputLocationsDistance(String str, String str2, String str3, String str4) {
        k.b(str, "latitude1");
        k.b(str2, "longitude1");
        k.b(str3, "latitude2");
        k.b(str4, "longitude2");
        this.latitude1 = str;
        this.longitude1 = str2;
        this.latitude2 = str3;
        this.longitude2 = str4;
    }

    public final String getLatitude1() {
        return this.latitude1;
    }

    public final String getLongitude1() {
        return this.longitude1;
    }

    public final String getLatitude2() {
        return this.latitude2;
    }

    public final String getLongitude2() {
        return this.longitude2;
    }
}
