package com.joaomgcd.taskerm.inputoutput;

import android.content.Context;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import cyanogenmod.alarmclock.ClockContract;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class f extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7544a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f7545b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f7546c;

    public f(String str, String str2, String str3, String str4, boolean z) {
        this(str, str2, str3, str4, z, 0, 0, false, 224, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(String str, String str2, String str3, String str4, boolean z, int i, int i2, boolean z2) {
        super(str, str2, z, i, i2, false, 32, null);
        c.f.b.k.b(str2, "nameNoSuffix");
        c.f.b.k.b(str3, ClockContract.AlarmSettingColumns.LABEL);
        c.f.b.k.b(str4, "htmlLabel");
        this.f7544a = str3;
        this.f7545b = str4;
        this.f7546c = z2;
    }

    public /* synthetic */ f(String str, String str2, String str3, String str4, boolean z, int i, int i2, boolean z2, int i3, c.f.b.g gVar) {
        this(str, str2, str3, str4, (i3 & 16) != 0 ? false : z, (i3 & 32) != 0 ? -1 : i, (i3 & 64) != 0 ? Integer.MAX_VALUE : i2, (i3 & 128) != 0 ? false : z2);
    }

    public final void b(boolean z) {
        this.f7546c = z;
    }

    public final String i() {
        return this.f7544a;
    }

    public f(Context context, TaskerOutputObject taskerOutputObject, TaskerOutputVariable taskerOutputVariable, boolean z) {
        String string;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(taskerOutputVariable, "taskerVariable");
        String strVarPrefix = taskerOutputObject != null ? taskerOutputObject.varPrefix() : null;
        String strName = taskerOutputVariable.name();
        int iIndex = taskerOutputVariable.index();
        if (iIndex >= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(aj.c(iIndex, taskerOutputObject != null ? taskerOutputObject.indexDigits() : 1));
            sb.append(". ");
            sb.append(context.getString(taskerOutputVariable.labelResId()));
            string = sb.toString();
        } else {
            string = context.getString(taskerOutputVariable.labelResId());
        }
        String str = string;
        c.f.b.k.a((Object) str, "taskerVariable.index.let…kerVariable.labelResId) }");
        String string2 = context.getString(taskerOutputVariable.htmlLabelResId());
        c.f.b.k.a((Object) string2, "context.getString(taskerVariable.htmlLabelResId)");
        this(strVarPrefix, strName, str, string2, z, taskerOutputVariable.minApi(), taskerOutputVariable.maxApi(), false, 128, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public f(Context context, TaskerOutputObject taskerOutputObject, TaskerOutputVariable taskerOutputVariable, Method method, boolean z, boolean z2) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(taskerOutputVariable, "taskerVariable");
        c.f.b.k.b(method, "method");
        Class<?> returnType = method.getReturnType();
        c.f.b.k.a((Object) returnType, "method.returnType");
        this(context, taskerOutputObject, taskerOutputVariable, returnType.isArray() || z || z2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public f(String str, String str2, String str3) {
        c.f.b.k.b(str, "nameNoSuffix");
        String str4 = str2;
        this(str3, str, str4 == null || str4.length() == 0 ? ak.s(str) : str2, "", false, 0, 0, false, 240, null);
    }

    public /* synthetic */ f(String str, String str2, String str3, int i, c.f.b.g gVar) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3);
    }

    public String toString() {
        return '%' + b() + '\n' + this.f7544a + '\n' + this.f7545b;
    }
}
