package com.joaomgcd.taskerm.action.display;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.action.a<ag, s, z> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5111a;

    public i() {
        super(new net.dinglisch.android.taskerm.m(374, R.string.an_screen_capture, 40, 4, "screen_capture", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_mode), "", 0, 1, 1, Integer.valueOf(R.string.pl_output_file), "f", 0, 1, 3, Integer.valueOf(R.string.pl_sound), "", 0, 1, 1, Integer.valueOf(R.string.pl_video_encoder), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_resolution), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_video_bitrate), "t:1:?", 0, 1));
        this.f5111a = 5288;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ag d() {
        return new ag(null, null, null, null, null, null, null, 127, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public s a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new s(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public z a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new z(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5111a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 1) {
            return null;
        }
        return Integer.valueOf(R.array.video_capture_modes);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, ag agVar) {
        c.f.b.k.b(context, "context");
        if (c.f.b.k.a((Object) (agVar != null ? agVar.getCaptureSound() : null), (Object) true)) {
            return bt.f9101c.r();
        }
        return null;
    }
}
