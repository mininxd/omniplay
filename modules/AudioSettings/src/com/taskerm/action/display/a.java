package com.joaomgcd.taskerm.action.display;

import android.content.Context;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5069a;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.display.a$a, reason: collision with other inner class name */
    static final class C0094a extends c.f.b.l implements c.f.a.b<ActionEdit, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0094a f5071a = new C0094a();

        C0094a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(ActionEdit actionEdit) {
            a(actionEdit);
            return c.s.f2131a;
        }

        public final void a(ActionEdit actionEdit) {
            c.f.b.k.b(actionEdit, "it");
            com.joaomgcd.taskerm.dialog.y.c(actionEdit, R.string.dc_dark_mode_may_not_work);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.display.a$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Context, com.joaomgcd.taskerm.display.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f5070a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.display.a invoke(Context context) {
            c.f.b.k.b(context, "it");
            return new com.joaomgcd.taskerm.display.a(context);
        }
    }

    public a() {
        super(361, 40, R.string.an_dark_mode, "dark_mode", AnonymousClass1.f5070a);
        this.f5069a = 5206;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5069a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, com.joaomgcd.taskerm.action.i iVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.E();
    }

    @Override // com.joaomgcd.taskerm.action.c
    public c.f.a.b<ActionEdit, c.s> h_() {
        return C0094a.f5071a;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return 26;
    }
}
