package com.joaomgcd.taskerm.action.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.joaomgcd.taskerm.util.cq;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class ac extends com.joaomgcd.taskerm.helper.actions.c<ba> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ac(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ba, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (ba) obj);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, ba baVar) {
        b.a.l lVarC;
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(baVar, "input");
        super.a((com.joaomgcd.taskerm.helper.actions.b) bVar, iVar, baVar);
        switch (bVar.a()) {
            case 1:
                com.joaomgcd.taskerm.nfc.f writeTypeEnum = baVar.getWriteTypeEnum();
                switch (writeTypeEnum) {
                    case Text:
                    case Uri:
                        lVarC = com.joaomgcd.taskerm.dialog.y.a((Activity) u(), R.string.payload, baVar.getWrite(), 0, true, (String) null, 40, (Object) null).c(new b(writeTypeEnum));
                        break;
                    case Application:
                        lVarC = com.joaomgcd.taskerm.dialog.y.a((Context) u(), (Intent) null, (List) null, false, 14, (Object) null).c(c.f5555a);
                        break;
                    default:
                        throw new c.i();
                }
                break;
            case 2:
                lVarC = com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(u(), R.string.payload_type, c.a.d.k(com.joaomgcd.taskerm.nfc.f.values()), false, null, null, null, null, null, null, null, null, null, null, null, null, 65520, null)).c(a.f5553a);
                break;
            default:
                lVarC = b.a.l.a("");
                break;
        }
        c.f.b.k.a((Object) lVarC, "when (args.argNo) {\n    …Single.just(\"\")\n        }");
        b(lVarC, new d(bVar));
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5553a = new a();

        a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(com.joaomgcd.taskerm.dialog.ae<com.joaomgcd.taskerm.nfc.f> aeVar) {
            c.f.b.k.b(aeVar, "it");
            String strJ = aeVar.a().j();
            return strJ != null ? strJ : "";
        }
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.nfc.f f5554a;

        b(com.joaomgcd.taskerm.nfc.f fVar) {
            this.f5554a = fVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            String strJ = xVar.j();
            if (strJ == null) {
                strJ = "";
            }
            if (this.f5554a == com.joaomgcd.taskerm.nfc.f.Uri) {
                try {
                    Uri.parse(strJ);
                } catch (Exception unused) {
                    throw new RuntimeException("Invalid URI");
                }
            }
            return strJ;
        }
    }

    static final class c<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f5555a = new c();

        c() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(cq<String, com.joaomgcd.taskerm.util.ad> cqVar) {
            c.f.b.k.b(cqVar, "it");
            String strD = cqVar.d();
            return strD != null ? strD : "";
        }
    }

    static final class d<T> implements b.a.d.f<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.actions.b f5557b;

        d(com.joaomgcd.taskerm.helper.actions.b bVar) {
            this.f5557b = bVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            if (str.length() == 0) {
                return;
            }
            ac.this.a(this.f5557b.a(), str);
        }
    }
}
