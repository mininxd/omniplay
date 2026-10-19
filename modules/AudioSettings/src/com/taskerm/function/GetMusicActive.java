package com.joaomgcd.taskerm.function;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ah;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GetMusicActive extends FunctionBase<InputGetMusicActive, OutputGetMusicActive> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.music_active;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputGetMusicActive> getInputClass() {
        return InputGetMusicActive.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputGetMusicActive> getOutputClass() {
        return OutputGetMusicActive.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Integer getTipResId() {
        return Integer.valueOf(R.string.music_active_tip);
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    @TargetApi(29)
    public OutputGetMusicActive doIt(Context context, InputGetMusicActive inputGetMusicActive) {
        k.b(context, "context");
        k.b(inputGetMusicActive, "input");
        AudioManager audioManagerY = ah.Y(context);
        if (audioManagerY == null) {
            throw new ExceptionFunctions("Couldn't get audio manager");
        }
        return new OutputGetMusicActive(audioManagerY.isMusicActive());
    }
}
