package com.joaomgcd.taskerm.action.phone;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private e f5690a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5691b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f5692c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Boolean f5693d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Boolean f5694e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f5695f;

    public m() {
        this(null, null, null, null, null, null, 63, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, e = R.string.prevent_call_from_going_through)
    public static /* synthetic */ void _allowType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.call_reject_help)
    public static /* synthetic */ void _reject$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, e = R.string.call_silence_help)
    public static /* synthetic */ void _silence$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, e = R.string.call_skip_log_help)
    public static /* synthetic */ void _skipLog$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5, e = R.string.call_skip_notification)
    public static /* synthetic */ void _skipNotification$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public m(e eVar, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5690a = eVar;
        this.f5691b = bool;
        this.f5692c = bool2;
        this.f5693d = bool3;
        this.f5694e = bool4;
        this.f5695f = jVar;
    }

    public /* synthetic */ m(e eVar, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (e) null : eVar, (i & 2) != 0 ? (Boolean) null : bool, (i & 4) != 0 ? (Boolean) null : bool2, (i & 8) != 0 ? (Boolean) null : bool3, (i & 16) != 0 ? (Boolean) null : bool4, (i & 32) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final e get_allowType() {
        return this.f5690a;
    }

    public final void set_allowType(e eVar) {
        this.f5690a = eVar;
    }

    public final Boolean get_reject() {
        return this.f5691b;
    }

    public final void set_reject(Boolean bool) {
        this.f5691b = bool;
    }

    public final Boolean get_silence() {
        return this.f5692c;
    }

    public final void set_silence(Boolean bool) {
        this.f5692c = bool;
    }

    public final Boolean get_skipLog() {
        return this.f5693d;
    }

    public final void set_skipLog(Boolean bool) {
        this.f5693d = bool;
    }

    public final Boolean get_skipNotification() {
        return this.f5694e;
    }

    public final void set_skipNotification(Boolean bool) {
        this.f5694e = bool;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f5695f;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5695f = jVar;
    }

    public final e getAllowType() {
        e eVar = this.f5690a;
        return eVar != null ? eVar : e.Disallow;
    }

    public final boolean getReject() {
        Boolean bool = this.f5691b;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean getSilence() {
        Boolean bool = this.f5692c;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean getSkipLog() {
        Boolean bool = this.f5693d;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean getSkipNotification() {
        Boolean bool = this.f5694e;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
