package com.joaomgcd.taskerm.dialog;

import android.os.Bundle;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public final class ab extends x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f6213a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f6214b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f6215c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Integer f6216d;

    /* JADX WARN: Multi-variable type inference failed */
    public ab() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @Override // com.joaomgcd.taskerm.dialog.x
    protected int a() {
        return 3;
    }

    @Override // com.joaomgcd.taskerm.dialog.x
    protected int b() {
        return 4;
    }

    @Override // com.joaomgcd.taskerm.dialog.x
    protected int c() {
        return 5;
    }

    public ab(Integer num, Bundle bundle) {
        super(num, bundle);
        boolean z = false;
        this.f6213a = num != null && num.intValue() == 1;
        if (num != null && num.intValue() == 2) {
            z = true;
        }
        this.f6214b = z;
        this.f6215c = bundle != null ? bundle.getString("text") : null;
        this.f6216d = bundle != null ? Integer.valueOf(bundle.getInt("index")) : null;
    }

    public /* synthetic */ ab(Integer num, Bundle bundle, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Bundle) null : bundle);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ab(Message message) {
        this(Integer.valueOf(message.what), message.getData());
        c.f.b.k.b(message, "message");
    }

    public final boolean n() {
        return this.f6213a;
    }

    public final boolean o() {
        return this.f6214b;
    }

    public final Integer p() {
        return this.f6216d;
    }
}
