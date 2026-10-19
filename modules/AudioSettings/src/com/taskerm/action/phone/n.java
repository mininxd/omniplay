package com.joaomgcd.taskerm.action.phone;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5696a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5697b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5698c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Boolean f5699d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f5700e;

    public n() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, e = R.string.help_contact_via_app_backout)
    public static /* synthetic */ void _backOut$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void contact$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void mimetype$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void text$annotations() {
    }

    public n(String str, String str2, String str3, Boolean bool, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5696a = str;
        this.f5697b = str2;
        this.f5698c = str3;
        this.f5699d = bool;
        this.f5700e = jVar;
    }

    public /* synthetic */ n(String str, String str2, String str3, Boolean bool, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (Boolean) null : bool, (i & 16) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getContact() {
        return this.f5696a;
    }

    public final void setContact(String str) {
        this.f5696a = str;
    }

    public final String getMimetype() {
        return this.f5697b;
    }

    public final void setMimetype(String str) {
        this.f5697b = str;
    }

    public final String getText() {
        return this.f5698c;
    }

    public final void setText(String str) {
        this.f5698c = str;
    }

    public final Boolean get_backOut() {
        return this.f5699d;
    }

    public final void set_backOut(Boolean bool) {
        this.f5699d = bool;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f5700e;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5700e = jVar;
    }

    public final boolean getBackOut() {
        Boolean bool = this.f5699d;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
