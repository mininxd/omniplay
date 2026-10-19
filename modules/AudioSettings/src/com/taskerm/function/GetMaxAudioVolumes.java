package com.joaomgcd.taskerm.function;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import c.f.a.b;
import c.f.b.k;
import c.f.b.l;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GetMaxAudioVolumes extends FunctionBase<InputGetMaxAudioVolumes, OutputGetMaxAudioVolumes> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.get_max_audio_volumes;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.function.GetMaxAudioVolumes$doIt$1, reason: invalid class name */
    static final class AnonymousClass1 extends l implements b<Integer, Integer> {
        final /* synthetic */ AudioManager $audioManager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AudioManager audioManager) {
            super(1);
            this.$audioManager = audioManager;
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(Integer num) {
            return Integer.valueOf(invoke(num.intValue()));
        }

        public final int invoke(int i) {
            return this.$audioManager.getStreamMaxVolume(i);
        }
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputGetMaxAudioVolumes> getInputClass() {
        return InputGetMaxAudioVolumes.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputGetMaxAudioVolumes> getOutputClass() {
        return OutputGetMaxAudioVolumes.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public String[] getPermissions(Context context, InputGetMaxAudioVolumes inputGetMaxAudioVolumes) {
        k.b(context, "context");
        k.b(inputGetMaxAudioVolumes, "input");
        return bt.f9101c.d();
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    @TargetApi(24)
    public OutputGetMaxAudioVolumes doIt(Context context, InputGetMaxAudioVolumes inputGetMaxAudioVolumes) {
        k.b(context, "context");
        k.b(inputGetMaxAudioVolumes, "input");
        AudioManager audioManagerY = ah.Y(context);
        if (audioManagerY == null) {
            throw new ExceptionFunctions("Couldn't get audio manager");
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(audioManagerY);
        return new OutputGetMaxAudioVolumes(Integer.valueOf(anonymousClass1.invoke(3)), Integer.valueOf(anonymousClass1.invoke(4)), Integer.valueOf(anonymousClass1.invoke(5)), Integer.valueOf(anonymousClass1.invoke(2)), Integer.valueOf(anonymousClass1.invoke(10)), Integer.valueOf(anonymousClass1.invoke(0)), Integer.valueOf(anonymousClass1.invoke(1)), Integer.valueOf(anonymousClass1.invoke(8)));
    }
}
