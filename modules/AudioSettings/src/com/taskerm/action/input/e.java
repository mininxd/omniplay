package com.joaomgcd.taskerm.action.input;

import android.content.Context;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(Context context, ar arVar, boolean z) {
        com.joaomgcd.taskerm.profile.i enumType = arVar.getEnumType();
        if (enumType == null) {
            throw new RuntimeException("No valid type chosen");
        }
        cl clVarB = new GenericActionPickInput(enumType, arVar.getTitle(), arVar.getText(), arVar.getDefaultInput(), z).run(context).b();
        if (!clVarB.b() || !(clVarB instanceof cq)) {
            throw new RuntimeException(clVarB.a());
        }
        Object objD = ((cq) clVarB).d();
        if (objD != null) {
            return (String) objD;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.String");
    }
}
