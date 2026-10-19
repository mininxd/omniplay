package com.joaomgcd.taskerm.action.net;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.nfc.NFCTagTasker;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cq;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class ao extends com.joaomgcd.taskerm.helper.actions.execute.l<ba> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5571a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(ao.class), "nfcTasker", "getNfcTasker()Lcom/joaomgcd/taskerm/nfc/NFCTasker;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5572b;

    private final com.joaomgcd.taskerm.nfc.c d() {
        c.e eVar = this.f5572b;
        c.j.g gVar = f5571a[0];
        return (com.joaomgcd.taskerm.nfc.c) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ao(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ba, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
        this.f5572b = c.f.a(new a(executeService));
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.nfc.c> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ExecuteService f5573a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(ExecuteService executeService) {
            super(0);
            this.f5573a = executeService;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.nfc.c invoke() {
            return new com.joaomgcd.taskerm.nfc.c(this.f5573a);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ba baVar) {
        String[] strArr;
        List<String> techTypes;
        c.f.b.k.b(baVar, "input");
        try {
            String write = baVar.getWrite();
            String str = write;
            if (!(str == null || str.length() == 0)) {
                d().a(write, baVar.getWriteTypeEnum()).b();
            }
            com.joaomgcd.taskerm.nfc.b bVarF = d().f();
            NFCTagTasker nFCTagTaskerA = bVarF != null ? bVarF.a() : null;
            boolean zIsPresent = nFCTagTaskerA != null ? nFCTagTaskerA.isPresent() : false;
            String idString = nFCTagTaskerA != null ? nFCTagTaskerA.getIdString() : null;
            String payload = nFCTagTaskerA != null ? nFCTagTaskerA.getPayload() : null;
            if (nFCTagTaskerA == null || (techTypes = nFCTagTaskerA.getTechTypes()) == null) {
                strArr = null;
            } else {
                Object[] array = techTypes.toArray(new String[0]);
                if (array == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                }
                strArr = (String[]) array;
            }
            return new cq(true, new OutputNFCTag(idString, payload, strArr, Boolean.valueOf(zIsPresent)), null);
        } catch (Exception e2) {
            return new cm(e2);
        }
    }
}
