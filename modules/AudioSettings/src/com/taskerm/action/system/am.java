package com.joaomgcd.taskerm.action.system;

import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class am extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5762b;

    /* JADX WARN: Multi-variable type inference failed */
    public am() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void permissionsString$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, e = R.string.ask_for_permissions_prompt_if_not_set)
    public static /* synthetic */ void promptIfNotSet$annotations() {
    }

    public /* synthetic */ am(String str, String str2, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2);
    }

    public final String getPermissionsString() {
        return this.f5761a;
    }

    public final void setPermissionsString(String str) {
        this.f5761a = str;
    }

    public final String getPromptIfNotSet() {
        return this.f5762b;
    }

    public final void setPromptIfNotSet(String str) {
        this.f5762b = str;
    }

    public am(String str, String str2) {
        this.f5761a = str;
        this.f5762b = str2;
    }

    public final String[] getPermissions() {
        List listB;
        String str = this.f5761a;
        if (str != null && (listB = c.l.n.b((CharSequence) str, new String[]{c.b()}, false, 0, 6, (Object) null)) != null) {
            Object[] array = listB.toArray(new String[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            String[] strArr = (String[]) array;
            if (strArr != null) {
                return strArr;
            }
        }
        return new String[0];
    }
}
