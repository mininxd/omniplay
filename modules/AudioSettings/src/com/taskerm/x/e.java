package com.joaomgcd.taskerm.x;

import c.a.j;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    /* JADX INFO: Access modifiers changed from: private */
    public static final b b(List<String> list, float[] fArr, Integer num) {
        Float fA;
        b bVar = new b();
        List listC = j.c(list, num != null ? num.intValue() : 9999);
        int i = 0;
        for (Object obj : listC) {
            int i2 = i + 1;
            if (i < 0) {
                j.b();
            }
            bVar.add(new a((String) obj, (fArr == null || (fA = c.a.d.a(fArr, i)) == null) ? 0.0f : fA.floatValue()));
            i = i2;
        }
        return bVar;
    }
}
