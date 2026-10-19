package com.joaomgcd.taskerm.action.input;

import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class az {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5460a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PowerMenuActionEditAction f5461b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PowerMenuActionType f5462c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5463d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5464e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5465f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private com.joaomgcd.taskerm.inputoutput.j n;

    public az() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void action$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void active$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7, e = R.string.command_prefix_explained_power_menu_actions)
    public static /* synthetic */ void commandPrefix$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void icon$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, e = R.string.set_same_id_to_overwrite)
    public static /* synthetic */ void id$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 11)
    public static /* synthetic */ void rangeCurrent$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 13)
    public static /* synthetic */ void rangeFormat$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 10)
    public static /* synthetic */ void rangeMax$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9)
    public static /* synthetic */ void rangeMin$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 12)
    public static /* synthetic */ void rangeStep$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void subtitle$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void title$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void type$annotations() {
    }

    public az(String str, PowerMenuActionEditAction powerMenuActionEditAction, PowerMenuActionType powerMenuActionType, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5460a = str;
        this.f5461b = powerMenuActionEditAction;
        this.f5462c = powerMenuActionType;
        this.f5463d = str2;
        this.f5464e = str3;
        this.f5465f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.k = str9;
        this.l = str10;
        this.m = str11;
        this.n = jVar;
    }

    public /* synthetic */ az(String str, PowerMenuActionEditAction powerMenuActionEditAction, PowerMenuActionType powerMenuActionType, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (PowerMenuActionEditAction) null : powerMenuActionEditAction, (i & 4) != 0 ? (PowerMenuActionType) null : powerMenuActionType, (i & 8) != 0 ? (String) null : str2, (i & 16) != 0 ? (String) null : str3, (i & 32) != 0 ? (String) null : str4, (i & 64) != 0 ? (String) null : str5, (i & 128) != 0 ? (String) null : str6, (i & 256) != 0 ? (String) null : str7, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (String) null : str8, (i & 1024) != 0 ? (String) null : str9, (i & 2048) != 0 ? (String) null : str10, (i & NotificationCompat.FLAG_BUBBLE) != 0 ? (String) null : str11, (i & 8192) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getId() {
        return this.f5460a;
    }

    public final void setId(String str) {
        this.f5460a = str;
    }

    public final PowerMenuActionEditAction getAction() {
        return this.f5461b;
    }

    public final void setAction(PowerMenuActionEditAction powerMenuActionEditAction) {
        this.f5461b = powerMenuActionEditAction;
    }

    public final PowerMenuActionType getType() {
        return this.f5462c;
    }

    public final void setType(PowerMenuActionType powerMenuActionType) {
        this.f5462c = powerMenuActionType;
    }

    public final String getTitle() {
        return this.f5463d;
    }

    public final void setTitle(String str) {
        this.f5463d = str;
    }

    public final String getSubtitle() {
        return this.f5464e;
    }

    public final void setSubtitle(String str) {
        this.f5464e = str;
    }

    public final String getIcon() {
        return this.f5465f;
    }

    public final void setIcon(String str) {
        this.f5465f = str;
    }

    public final String getCommandPrefix() {
        return this.g;
    }

    public final void setCommandPrefix(String str) {
        this.g = str;
    }

    public final String getActive() {
        return this.h;
    }

    public final void setActive(String str) {
        this.h = str;
    }

    public final String getRangeMin() {
        return this.i;
    }

    public final void setRangeMin(String str) {
        this.i = str;
    }

    public final String getRangeMax() {
        return this.j;
    }

    public final void setRangeMax(String str) {
        this.j = str;
    }

    public final String getRangeCurrent() {
        return this.k;
    }

    public final void setRangeCurrent(String str) {
        this.k = str;
    }

    public final String getRangeFormat() {
        return this.l;
    }

    public final void setRangeFormat(String str) {
        this.l = str;
    }

    public final String getRangeStep() {
        return this.m;
    }

    public final void setRangeStep(String str) {
        this.m = str;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.n;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.n = jVar;
    }

    public final StoredPowerMenuAction getStoredPowerMenuAction() {
        String str = (String) com.joaomgcd.taskerm.util.aj.a(this.f5460a, net.dinglisch.android.taskerm.be.EXTRA_ID);
        PowerMenuActionType powerMenuActionType = (PowerMenuActionType) com.joaomgcd.taskerm.util.aj.a(this.f5462c, "type");
        String str2 = (String) com.joaomgcd.taskerm.util.aj.a(this.f5463d, "title");
        String str3 = this.f5464e;
        String str4 = this.f5465f;
        String str5 = this.g;
        String str6 = this.h;
        Boolean boolValueOf = str6 != null ? Boolean.valueOf(Boolean.parseBoolean(str6)) : null;
        String str7 = this.i;
        Float fA = str7 != null ? c.l.n.a(str7) : null;
        String str8 = this.j;
        Float fA2 = str8 != null ? c.l.n.a(str8) : null;
        String str9 = this.k;
        Float fA3 = str9 != null ? c.l.n.a(str9) : null;
        String str10 = this.m;
        return new StoredPowerMenuAction(str, powerMenuActionType, str2, str3, str4, str5, boolValueOf, fA, fA2, fA3, str10 != null ? c.l.n.a(str10) : null, this.l);
    }
}
