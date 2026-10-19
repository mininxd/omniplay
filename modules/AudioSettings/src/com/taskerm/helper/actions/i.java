package com.joaomgcd.taskerm.helper.actions;

import android.widget.EditText;
import android.widget.Spinner;
import c.f.b.k;
import com.joaomgcd.taskerm.securesettings.r;
import net.dinglisch.android.taskerm.StateEdit;
import net.dinglisch.android.taskerm.fm;
import net.dinglisch.android.taskerm.fn;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.helper.i<com.joaomgcd.taskerm.state.e<?, ?, ?, ?, ?>, fo, fm, com.joaomgcd.taskerm.state.a, StateEdit> {

    static final class a<T> implements b.a.d.f<r> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f7241a = new a();

        a() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(r rVar) {
            k.b(rVar, "it");
        }
    }

    @Override // com.joaomgcd.taskerm.helper.i
    public boolean h(int i, int i2) {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(StateEdit stateEdit) {
        super(stateEdit);
        k.b(stateEdit, "activity");
    }

    @Override // com.joaomgcd.taskerm.helper.i
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.state.a f() {
        return com.joaomgcd.taskerm.state.i.a();
    }

    @Override // com.joaomgcd.taskerm.helper.i
    public com.joaomgcd.taskerm.state.d<?> a(StateEdit stateEdit, com.joaomgcd.taskerm.state.e<?, ?, ?, ?, ?> eVar) {
        k.b(stateEdit, "receiver$0");
        k.b(eVar, "configurable");
        return (com.joaomgcd.taskerm.state.d) eVar.a(stateEdit);
    }

    public final void a(Spinner[] spinnerArr, EditText[] editTextArr) {
        k.b(spinnerArr, "argSpinners");
        k.b(editTextArr, "argEditTextViews");
        a(spinnerArr, editTextArr, false, (b.a.d.f<r>) a.f7241a);
    }

    @Override // com.joaomgcd.taskerm.helper.i
    public String d(int i, int i2) {
        return fn.c(i, i2);
    }
}
