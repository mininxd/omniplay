package com.joaomgcd.taskerm.action.a;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.a<h, d, f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4913a;

    public b() {
        super(new m(334, R.string.an_say_wavenet, 10, 4, "say_wavenet", 1, Integer.valueOf(R.string.pl_text_ssml), "s:4", 0, 1, 1, Integer.valueOf(R.string.pl_voice), "t:1", 0, 1, 0, Integer.valueOf(R.string.pl_stream), "0:0:3", 0, 0, 0, Integer.valueOf(R.string.pl_pitch), "1:40:20", 0, 0, 0, Integer.valueOf(R.string.pl_speed), "2:32:8", 0, 0, 3, Integer.valueOf(R.string.pl_continue_task_immediately), "", 0, 0, 1, Integer.valueOf(R.string.pl_file), "d:2:?", 0, 1, 1, Integer.valueOf(R.string.pl_override_api_key), "t:1:?", 0, 0, 3, Integer.valueOf(R.string.pl_respect_audio_focus), "true", 0, 0));
        this.f4913a = 5166;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h d() {
        return new h(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public d a(ActionEdit actionEdit) {
        k.b(actionEdit, "actionEdit");
        return new d(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public f a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        return new f(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f4913a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        k.b(resources, "res");
        if (i != 2) {
            return null;
        }
        return Integer.valueOf(R.array.array_sound_streams);
    }
}
