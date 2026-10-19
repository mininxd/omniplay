package com.joaomgcd.taskerm.dialog;

import android.app.TimePickerDialog;
import android.widget.TimePicker;

/* JADX INFO: loaded from: classes.dex */
final class aa implements TimePickerDialog.OnTimeSetListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.q f6212a;

    aa(c.f.a.q qVar) {
        this.f6212a = qVar;
    }

    @Override // android.app.TimePickerDialog.OnTimeSetListener
    public final /* synthetic */ void onTimeSet(TimePicker timePicker, int i, int i2) {
        c.f.b.k.a(this.f6212a.a(timePicker, Integer.valueOf(i), Integer.valueOf(i2)), "invoke(...)");
    }
}
