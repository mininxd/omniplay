package com.joaomgcd.taskerm.dialog;

import android.os.Handler;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class ah<T> extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WeakReference<T> f6238a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f6239b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.f.a.m<T, Message, c.s> f6240c;

    /* JADX WARN: Multi-variable type inference failed */
    public ah(T t, c.f.a.m<? super T, ? super Message, c.s> mVar) {
        c.f.b.k.b(mVar, "func");
        this.f6239b = t;
        this.f6240c = mVar;
        this.f6238a = new WeakReference<>(this.f6239b);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        c.f.b.k.b(message, NotificationCompat.CATEGORY_MESSAGE);
        T t = this.f6238a.get();
        if (t != null) {
            this.f6240c.invoke(t, message);
        }
    }
}
