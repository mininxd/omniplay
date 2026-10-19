package com.joaomgcd.taskerm.helper;

import android.text.Editable;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ProfileProperties;

/* JADX INFO: loaded from: classes.dex */
public final class t extends h<ProfileProperties> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(ProfileProperties profileProperties) {
        super(profileProperties);
        c.f.b.k.b(profileProperties, "activity");
    }

    private final com.joaomgcd.taskerm.profile.b f() {
        ProfileProperties profilePropertiesC = c();
        View viewFindViewById = c().findViewById(R.id.root_layout);
        c.f.b.k.a((Object) viewFindViewById, "activity.findViewById(R.id.root_layout)");
        return new com.joaomgcd.taskerm.profile.b(profilePropertiesC, viewFindViewById, R.string.ppselect_text_profile_variables, R.string.ppselect_text_profile_variables_help, new com.joaomgcd.taskerm.profile.e(c().f11269d));
    }

    public final void a(com.joaomgcd.taskerm.profile.j jVar) {
        f().a(jVar);
    }

    public final com.joaomgcd.taskerm.profile.j d() {
        return f().a();
    }

    public final String e() {
        Editable text;
        String string;
        EditText editText = c().f11266a;
        if (editText == null || (text = editText.getText()) == null || (string = text.toString()) == null) {
            return null;
        }
        if (string.length() == 0) {
            return null;
        }
        return string;
    }

    public final void a(String str) {
        TextView textView;
        Button button;
        EditText editText = c().f11266a;
        if (editText == null || (textView = c().f11268c) == null || (button = c().f11267b) == null) {
            return;
        }
        if (str == null) {
            str = "";
        }
        b(str);
        a(this, (Boolean) null, false, 3, (Object) null);
        editText.setOnFocusChangeListener(new a());
        textView.setOnClickListener(new b());
        button.setOnClickListener(new c(editText));
    }

    static final class a implements View.OnFocusChangeListener {
        a() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            if (z) {
                return;
            }
            t.this.c(t.this.g());
            t.a(t.this, (Boolean) null, false, 3, (Object) null);
        }
    }

    static final class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            t.this.a((Boolean) true, true);
        }
    }

    static final class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText f7513a;

        c(EditText editText) {
            this.f7513a = editText;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f7513a.clearFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String g() {
        Editable text;
        EditText editText = c().f11266a;
        if (editText == null || (text = editText.getText()) == null) {
            return null;
        }
        return text.toString();
    }

    private final void b(String str) {
        c().f11266a.setText(str);
        c(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(String str) {
        TextView textView = c().f11268c;
        c.f.b.k.a((Object) textView, "activity.commentsTextFormatted");
        textView.setText(Html.fromHtml(str));
    }

    static /* synthetic */ void a(t tVar, Boolean bool, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        tVar.a(bool, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(Boolean bool, boolean z) {
        TextView textView;
        Button button;
        EditText editText = c().f11266a;
        if (editText == null || (textView = c().f11268c) == null || (button = c().f11267b) == null) {
            return;
        }
        Editable text = editText.getText();
        String string = text != null ? text.toString() : null;
        if ((string == null || string.length() == 0) || c.f.b.k.a((Object) bool, (Object) true)) {
            editText.setVisibility(0);
            button.setVisibility(0);
            textView.setVisibility(8);
            if (z) {
                editText.requestFocus();
                return;
            }
            return;
        }
        editText.setVisibility(8);
        button.setVisibility(8);
        textView.setVisibility(0);
    }
}
