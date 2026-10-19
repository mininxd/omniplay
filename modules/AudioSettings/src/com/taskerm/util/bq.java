package com.joaomgcd.taskerm.util;

import android.content.Context;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class bq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f9082a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final bs f9083b;

    static final class a extends c.f.b.l implements c.f.a.b<Integer, bp> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f9085b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f9086c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, String str) {
            super(1);
            this.f9085b = context;
            this.f9086c = str;
        }

        @Override // c.f.a.b
        public /* synthetic */ bp invoke(Integer num) {
            return a(num.intValue());
        }

        public final bp a(int i) {
            return new bp(this.f9085b, bq.this, this.f9086c, aj.a(i, this.f9085b, new Object[0]), null, 16, null);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.m<Integer, Integer, bp> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f9088b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f9089c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Context context, String str) {
            super(2);
            this.f9088b = context;
            this.f9089c = str;
        }

        @Override // c.f.a.m
        public /* synthetic */ bp invoke(Integer num, Integer num2) {
            return a(num.intValue(), num2.intValue());
        }

        public final bp a(int i, int i2) {
            return new bp(this.f9088b, bq.this, this.f9089c, aj.a(i, this.f9088b, new Object[0]), aj.a(i2, this.f9088b, new Object[0]));
        }
    }

    public bq(int i, bs bsVar) {
        c.f.b.k.b(bsVar, "type");
        this.f9082a = i;
        this.f9083b = bsVar;
    }

    public final String a() {
        return this.f9083b.a().invoke(Integer.valueOf(this.f9082a));
    }

    public final bp a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "permission");
        a aVar = new a(context, str);
        b bVar = new b(context, str);
        bp bpVarA = null;
        switch (br.f9090a[this.f9083b.ordinal()]) {
            case 1:
                int i = this.f9082a;
                bpVarA = (bp) null;
                break;
            case 2:
                int i2 = this.f9082a;
                if (i2 == 7) {
                    bpVarA = bVar.a(R.string.permission_reason_state_cell_near, R.string.permission_reason_state_cell_near_reason);
                } else if (i2 == 160 || i2 == 170) {
                    bpVarA = bVar.a(R.string.permission_reason_state_wifi, R.string.permission_reason_state_wifi_reason);
                }
                break;
            case 3:
                int i3 = this.f9082a;
                if (i3 == 90) {
                    bpVarA = aVar.a(R.string.permission_reason_action_make_call);
                } else if (i3 == 910) {
                    bpVarA = aVar.a(R.string.permission_reason_action_call_log);
                }
                break;
            default:
                throw new c.i();
        }
        return bpVarA != null ? bpVarA : new bp(context, this, str, null, null, 24, null);
    }
}
