package com.joaomgcd.taskerm.action.a;

import c.a.z;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.dialog.k;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.j;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.helper.actions.c<g> {

    static final class a extends l implements c.f.a.b<Integer, b.a.l<String>> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.a.c$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<String> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String strB = k.a(c.this.u(), R.string.pl_position, com.joaomgcd.taskerm.toast.d.class).b();
                if (!((x) y.a(c.this.u(), R.string.pl_position, R.string.want_to_set_an_offset_toast_position, 0, 8, (Object) null).b()).d()) {
                    return strB;
                }
                if (!((x) y.a(c.this.u(), R.string.pl_position, R.string.use_different_x_y_offsets, 0, 8, (Object) null).b()).d()) {
                    return strB + ',' + ((String) y.a(c.this.u(), R.string.offset, (120 & 4) != 0 ? (Integer) null : null, (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : new j("28"), (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b());
                }
                return strB + ',' + ((String) y.a(c.this.u(), R.string.pl_horizontal_offset, (120 & 4) != 0 ? (Integer) null : null, (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : new j("28"), (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b()) + ',' + ((String) y.a(c.this.u(), R.string.pl_vertical_offset, (120 & 4) != 0 ? (Integer) null : null, (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : new j("28"), (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b());
            }
        }

        public final b.a.l<String> a(int i) {
            return i.b(new AnonymousClass1());
        }
    }

    static final class b extends l implements c.f.a.b<String, s> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(String str) {
            a(str);
            return s.f2131a;
        }

        public final void a(String str) {
            c.this.u().a(11);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<g, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        if (i != 2) {
            return true;
        }
        a(3, 4, 5, 10, 6, 7, 8, 11, 12, 13, 14);
        return true;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return (aj.a(Integer.valueOf(i), 0, 1, 2, 9) || j().getTaskerLayoutNotNull()) ? false : true;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, s>> a() {
        return z.a(new c.k(7, new b()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    protected com.joaomgcd.taskerm.helper.l.a[] b() {
        return new com.joaomgcd.taskerm.helper.l.a[]{new com.joaomgcd.taskerm.helper.l.a(13, new a())};
    }
}
