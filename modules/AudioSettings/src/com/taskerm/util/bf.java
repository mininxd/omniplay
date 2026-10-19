package com.joaomgcd.taskerm.util;

import androidx.core.app.FrameMetricsAggregator;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class bf extends be {
    public bf() {
        super(com.joaomgcd.taskerm.action.d.a());
    }

    @Override // com.joaomgcd.taskerm.util.be
    protected HashMap<Integer, Integer> a() {
        return c.a.z.b(new c.k(Integer.valueOf(FrameMetricsAggregator.EVERY_DURATION), 23), new c.k(249, 28));
    }
}
