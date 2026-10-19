package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import net.dinglisch.android.taskerm.Settings;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public abstract class v extends c<Integer, SeekBar> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f8592a;

    public void a(int i) {
    }

    public abstract int i();

    public abstract int j();

    @Override // com.joaomgcd.taskerm.settings.c
    public /* synthetic */ void a(SharedPreferences.Editor editor, Integer num) {
        a(editor, num.intValue());
    }

    @Override // com.joaomgcd.taskerm.settings.c
    public /* synthetic */ void a(View view, Integer num) {
        a((SeekBar) view, num.intValue());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(Settings settings, String str, Integer num, int i, int i2, int i3, int i4, s sVar) {
        super(settings, str, Integer.valueOf(num != null ? num.intValue() : 0), i, i2, i3, sVar);
        c.f.b.k.b(settings, "context");
        c.f.b.k.b(str, "key");
        this.f8592a = i4;
    }

    public final TextView k() {
        return (TextView) b().findViewById(this.f8592a);
    }

    protected void a(SeekBar seekBar, int i) {
        c.f.b.k.b(seekBar, "view");
        seekBar.setMax(j());
        seekBar.setProgress(i - i());
        TextView textViewK = k();
        c.f.b.k.a((Object) textViewK, "viewLabel");
        textViewK.setText(String.valueOf(i));
        seekBar.setOnSeekBarChangeListener(new a());
    }

    public static final class a implements SeekBar.OnSeekBarChangeListener {
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int i2 = i + v.this.i();
            TextView textViewK = v.this.k();
            c.f.b.k.a((Object) textViewK, "viewLabel");
            textViewK.setText(String.valueOf(i2));
            v.this.a(i2);
        }
    }

    protected void a(SharedPreferences.Editor editor, int i) {
        c.f.b.k.b(editor, "settingsEditor");
        editor.putInt(c(), i + i());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.settings.c
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Integer b(SharedPreferences sharedPreferences) {
        c.f.b.k.b(sharedPreferences, be.SETTINGS_LABEL);
        return Integer.valueOf(sharedPreferences.getInt(c(), d().intValue()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.settings.c
    public Integer a(SeekBar seekBar) {
        c.f.b.k.b(seekBar, "view");
        return Integer.valueOf(seekBar.getProgress());
    }
}
