package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.dm;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class LocationsDistance extends FunctionBase<InputLocationsDistance, OutputLocationsDistance> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.locations_distance;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputLocationsDistance> getInputClass() {
        return InputLocationsDistance.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputLocationsDistance> getOutputClass() {
        return OutputLocationsDistance.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputLocationsDistance doIt(Context context, InputLocationsDistance inputLocationsDistance) {
        k.b(context, "context");
        k.b(inputLocationsDistance, "input");
        return new OutputLocationsDistance(aj.a(dm.a(inputLocationsDistance.getLatitude1(), inputLocationsDistance.getLongitude1()).distanceTo(dm.a(inputLocationsDistance.getLatitude2(), inputLocationsDistance.getLongitude2())), 2));
    }
}
