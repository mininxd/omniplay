package com.joaomgcd.taskerm.event.sensor;

import android.hardware.SensorManager;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    /* JADX INFO: Access modifiers changed from: private */
    public static final float[] b(float[] fArr, boolean z) {
        if (!z || fArr.length < 3) {
            return fArr;
        }
        float f2 = fArr[0];
        float f3 = fArr[1];
        float f4 = fArr[2];
        float[] fArr2 = new float[9];
        float[] fArr3 = new float[9];
        float[] fArr4 = new float[3];
        SensorManager.getRotationMatrixFromVector(fArr2, fArr);
        SensorManager.remapCoordinateSystem(fArr2, 1, 3, fArr3);
        SensorManager.getOrientation(fArr3, fArr4);
        fArr[0] = (float) Math.toDegrees(fArr4[0]);
        fArr[1] = (float) Math.toDegrees(fArr4[1]);
        fArr[2] = (float) Math.toDegrees(fArr4[2]);
        return fArr;
    }
}
