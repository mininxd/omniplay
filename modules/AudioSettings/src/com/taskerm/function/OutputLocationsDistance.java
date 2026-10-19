package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputLocationsDistance {
    private final double distance;

    public OutputLocationsDistance(double d2) {
        this.distance = d2;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.distance_explained, labelResId = R.string.distance, name = "distance")
    public final double getDistance() {
        return this.distance;
    }
}
