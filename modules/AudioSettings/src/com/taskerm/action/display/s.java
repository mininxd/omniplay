package com.joaomgcd.taskerm.action.display;

import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class s extends com.joaomgcd.taskerm.helper.actions.c<ag> {

    static final class a extends c.f.b.l implements c.f.a.b<Integer, b.a.l<String>> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        public final b.a.l<String> a(int i) {
            b.a.l<String> lVarA = com.joaomgcd.taskerm.dialog.k.a(s.this.u(), R.string.pl_video_encoder, al.class);
            c.f.b.k.a((Object) lVarA, "dialogSingleChoiceEnumNa….string.pl_video_encoder)");
            return lVarA;
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<Integer, b.a.l<String>> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.display.s$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<String> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                Integer numC;
                Integer numC2;
                String str = (String) com.joaomgcd.taskerm.dialog.y.a(s.this.u(), R.string.pl_resolution, (120 & 4) != 0 ? (Integer) null : Integer.valueOf(R.string.pl_resolution_width), (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b();
                if (str == null || (numC = c.l.n.c(str)) == null) {
                    throw new RuntimeException("Invalid width");
                }
                int iIntValue = numC.intValue();
                String str2 = (String) com.joaomgcd.taskerm.dialog.y.a(s.this.u(), R.string.pl_resolution, (120 & 4) != 0 ? (Integer) null : Integer.valueOf(R.string.pl_resolution_height), (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b();
                if (str2 == null || (numC2 = c.l.n.c(str2)) == null) {
                    throw new RuntimeException("Invalid height");
                }
                int iIntValue2 = numC2.intValue();
                StringBuilder sb = new StringBuilder();
                sb.append(iIntValue);
                sb.append(',');
                sb.append(iIntValue2);
                return sb.toString();
            }
        }

        public final b.a.l<String> a(int i) {
            return com.joaomgcd.taskerm.rx.i.b(new AnonymousClass1());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ag, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        ag agVarJ = j();
        if (com.joaomgcd.taskerm.util.f.f9323b.e() && i == 4) {
            return true;
        }
        l mode = agVarJ.getMode();
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return mode != l.Start;
            default:
                return false;
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        if (i != 1) {
            return;
        }
        a(2, 3, 4, 5, 6);
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        b.a.l<com.joaomgcd.taskerm.dialog.x> lVarG = com.joaomgcd.taskerm.dialog.y.g(u());
        c.f.b.k.a((Object) lVarG, "dialogScreenCapturePermi…StopReminding(actionEdit)");
        com.joaomgcd.taskerm.helper.l.a(this, lVarG, (b.a.d.f) null, 1, (Object) null);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    protected com.joaomgcd.taskerm.helper.l.a[] b() {
        return new com.joaomgcd.taskerm.helper.l.a[]{new com.joaomgcd.taskerm.helper.l.a(4, new a()), new com.joaomgcd.taskerm.helper.l.a(5, new b())};
    }
}
