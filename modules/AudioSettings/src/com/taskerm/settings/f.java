package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import net.dinglisch.android.taskerm.Settings;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends c<Boolean, CheckBox> {
    protected void a(SharedPreferences.Editor editor, boolean z) {
        c.f.b.k.b(editor, "settingsEditor");
    }

    public void a(boolean z) {
    }

    @Override // com.joaomgcd.taskerm.settings.c
    public /* synthetic */ void a(SharedPreferences.Editor editor, Boolean bool) {
        a(editor, bool.booleanValue());
    }

    @Override // com.joaomgcd.taskerm.settings.c
    public /* synthetic */ void a(View view, Boolean bool) {
        a((CheckBox) view, bool.booleanValue());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Settings settings, String str, boolean z, int i, int i2, int i3, s sVar) {
        super(settings, str, Boolean.valueOf(z), i, i2, i3, sVar);
        c.f.b.k.b(settings, "context");
        c.f.b.k.b(str, "key");
    }

    static final class a implements CompoundButton.OnCheckedChangeListener {
        a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            f.this.a(z);
        }
    }

    protected void a(CheckBox checkBox, boolean z) {
        c.f.b.k.b(checkBox, "view");
        checkBox.setOnCheckedChangeListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.settings.c
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Boolean b(SharedPreferences sharedPreferences) {
        c.f.b.k.b(sharedPreferences, be.SETTINGS_LABEL);
        return Boolean.valueOf(sharedPreferences.getBoolean(c(), d().booleanValue()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.settings.c
    public Boolean a(CheckBox checkBox) {
        c.f.b.k.b(checkBox, "view");
        return Boolean.valueOf(checkBox.isChecked());
    }
}
