package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;
import android.text.Editable;
import android.widget.EditText;
import net.dinglisch.android.taskerm.Settings;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends c<String, EditText> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Settings settings, String str, String str2, int i, int i2, int i3, s sVar) {
        super(settings, str, str2 == null ? "" : str2, i, i2, i3, sVar);
        c.f.b.k.b(settings, "context");
        c.f.b.k.b(str, "key");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.settings.c
    public void a(EditText editText, String str) {
        c.f.b.k.b(editText, "view");
        c.f.b.k.b(str, "value");
        editText.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.settings.c
    public void a(SharedPreferences.Editor editor, String str) {
        c.f.b.k.b(editor, "settingsEditor");
        c.f.b.k.b(str, "value");
        editor.putString(c(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.settings.c
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public String b(SharedPreferences sharedPreferences) {
        c.f.b.k.b(sharedPreferences, be.SETTINGS_LABEL);
        String string = sharedPreferences.getString(c(), d());
        return string != null ? string : "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.settings.c
    public String a(EditText editText) {
        String string;
        c.f.b.k.b(editText, "view");
        Editable text = editText.getText();
        return (text == null || (string = text.toString()) == null) ? "" : string;
    }
}
