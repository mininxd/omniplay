package com.joaomgcd.taskerm.dialog;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

/* JADX INFO: loaded from: classes.dex */
final class z implements DatePickerDialog.OnDateSetListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.r f6584a;

    z(c.f.a.r rVar) {
        this.f6584a = rVar;
    }

    @Override // android.app.DatePickerDialog.OnDateSetListener
    public final /* synthetic */ void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        c.f.b.k.a(this.f6584a.a(datePicker, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)), "invoke(...)");
    }
}
