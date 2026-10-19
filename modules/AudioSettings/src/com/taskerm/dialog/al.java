package com.joaomgcd.taskerm.dialog;

import android.content.Context;
import com.joaomgcd.taskerm.dialog.ag;
import java.lang.Enum;

/* JADX INFO: loaded from: classes.dex */
final class al<T extends Enum<T> & ag> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6258a;

    /* JADX INFO: Incorrect field signature: TT; */
    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Enum f6259b;

    /* JADX WARN: Incorrect types in method signature: (Landroid/content/Context;TT;)V */
    public al(Context context, Enum r3) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(r3, "inputType");
        this.f6258a = context;
        this.f6259b = r3;
    }

    /* JADX WARN: Incorrect return type in method signature: ()TT; */
    public final Enum a() {
        return this.f6259b;
    }

    public String toString() {
        return com.joaomgcd.taskerm.util.aj.a(((ag) this.f6259b).a(), this.f6258a, new Object[0]);
    }
}
