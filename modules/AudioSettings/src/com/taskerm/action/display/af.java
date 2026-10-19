package com.joaomgcd.taskerm.action.display;

/* JADX INFO: loaded from: classes.dex */
public final class af extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5080a;

    /* JADX WARN: Multi-variable type inference failed */
    public af() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void iconsToHide$annotations() {
    }

    public /* synthetic */ af(String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public final String getIconsToHide() {
        return this.f5080a;
    }

    public final void setIconsToHide(String str) {
        this.f5080a = str;
    }

    public af(String str) {
        this.f5080a = str;
    }

    public final String getIconsToHideForExecute() {
        String str = this.f5080a;
        if (str != null) {
            if (!(str.length() == 0)) {
                return str;
            }
        }
        return "null";
    }
}
