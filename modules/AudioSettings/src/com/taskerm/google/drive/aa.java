package com.joaomgcd.taskerm.google.drive;

import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public final class aa extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6988a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aa(String str, boolean z) {
        super(z, null);
        c.f.b.k.b(str, be.EXTRA_ID);
        this.f6988a = str;
    }

    public /* synthetic */ aa(String str, boolean z, int i, c.f.b.g gVar) {
        this(str, (i & 2) != 0 ? false : z);
    }

    public final String b() {
        return this.f6988a;
    }

    public String toString() {
        return "File with Id: " + this.f6988a;
    }
}
