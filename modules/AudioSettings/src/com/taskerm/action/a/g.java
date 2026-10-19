package com.joaomgcd.taskerm.action.a;

import androidx.core.app.NotificationCompat;
import c.a.j;
import c.f.b.k;
import c.f.b.l;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.aj;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4931a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f4932b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f4933c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4934d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f4935e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f4936f;
    private String g;
    private String h;
    private String i;
    private Boolean j;
    private String k;
    private Boolean l;
    private Boolean m;
    private String n;
    private Boolean o;

    public g() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6, e = R.string.help_toast_background_color)
    public static /* synthetic */ void backgroundColor$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9, e = R.string.help_toast_continue_task_right_away)
    public static /* synthetic */ void continueTaskRightAway$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 11, e = R.string.help_toast_hide_on_click)
    public static /* synthetic */ void disappearOnClick$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, e = R.string.help_toast_icon)
    public static /* synthetic */ void icon$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5, e = R.string.help_toast_icon_size)
    public static /* synthetic */ void iconSize$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void long$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 13, e = R.string.help_toast_position)
    public static /* synthetic */ void position$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 12, e = R.string.help_toast_show_over_everything)
    public static /* synthetic */ void showOverEverything$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7, e = R.string.help_toast_command)
    public static /* synthetic */ void task$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.help_toast_tasker_layout)
    public static /* synthetic */ void taskerLayout$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void text$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 10, e = R.string.help_toast_text_color)
    public static /* synthetic */ void textColor$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8, e = R.string.help_toast_timeout_override)
    public static /* synthetic */ void timeoutOverride$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, e = R.string.help_toast_title)
    public static /* synthetic */ void title$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 14)
    public static /* synthetic */ void useHtml$annotations() {
    }

    public g(String str, Boolean bool, Boolean bool2, String str2, String str3, String str4, String str5, String str6, String str7, Boolean bool3, String str8, Boolean bool4, Boolean bool5, String str9, Boolean bool6) {
        this.f4931a = str;
        this.f4932b = bool;
        this.f4933c = bool2;
        this.f4934d = str2;
        this.f4935e = str3;
        this.f4936f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = bool3;
        this.k = str8;
        this.l = bool4;
        this.m = bool5;
        this.n = str9;
        this.o = bool6;
    }

    public /* synthetic */ g(String str, Boolean bool, Boolean bool2, String str2, String str3, String str4, String str5, String str6, String str7, Boolean bool3, String str8, Boolean bool4, Boolean bool5, String str9, Boolean bool6, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Boolean) null : bool, (i & 4) != 0 ? (Boolean) null : bool2, (i & 8) != 0 ? (String) null : str2, (i & 16) != 0 ? (String) null : str3, (i & 32) != 0 ? (String) null : str4, (i & 64) != 0 ? (String) null : str5, (i & 128) != 0 ? (String) null : str6, (i & 256) != 0 ? (String) null : str7, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Boolean) null : bool3, (i & 1024) != 0 ? (String) null : str8, (i & 2048) != 0 ? (Boolean) null : bool4, (i & NotificationCompat.FLAG_BUBBLE) != 0 ? (Boolean) null : bool5, (i & 8192) != 0 ? (String) null : str9, (i & 16384) != 0 ? (Boolean) null : bool6);
    }

    public final String getText() {
        return this.f4931a;
    }

    public final void setText(String str) {
        this.f4931a = str;
    }

    public final Boolean getLong() {
        return this.f4932b;
    }

    public final void setLong(Boolean bool) {
        this.f4932b = bool;
    }

    public final Boolean getTaskerLayout() {
        return this.f4933c;
    }

    public final void setTaskerLayout(Boolean bool) {
        this.f4933c = bool;
    }

    public final String getTitle() {
        return this.f4934d;
    }

    public final void setTitle(String str) {
        this.f4934d = str;
    }

    public final String getIcon() {
        return this.f4935e;
    }

    public final void setIcon(String str) {
        this.f4935e = str;
    }

    public final String getIconSize() {
        return this.f4936f;
    }

    public final void setIconSize(String str) {
        this.f4936f = str;
    }

    public final String getBackgroundColor() {
        return this.g;
    }

    public final void setBackgroundColor(String str) {
        this.g = str;
    }

    public final String getTask() {
        return this.h;
    }

    public final void setTask(String str) {
        this.h = str;
    }

    public final String getTimeoutOverride() {
        return this.i;
    }

    public final void setTimeoutOverride(String str) {
        this.i = str;
    }

    public final Boolean getContinueTaskRightAway() {
        return this.j;
    }

    public final void setContinueTaskRightAway(Boolean bool) {
        this.j = bool;
    }

    public final String getTextColor() {
        return this.k;
    }

    public final void setTextColor(String str) {
        this.k = str;
    }

    public final Boolean getDisappearOnClick() {
        return this.l;
    }

    public final void setDisappearOnClick(Boolean bool) {
        this.l = bool;
    }

    public final Boolean getShowOverEverything() {
        return this.m;
    }

    public final void setShowOverEverything(Boolean bool) {
        this.m = bool;
    }

    public final String getPosition() {
        return this.n;
    }

    public final void setPosition(String str) {
        this.n = str;
    }

    public final Boolean getUseHtml() {
        return this.o;
    }

    public final void setUseHtml(Boolean bool) {
        this.o = bool;
    }

    public final boolean getTaskerLayoutNotNull() {
        Boolean bool = this.f4933c;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean getLongNotNull() {
        Boolean bool = this.f4932b;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean getContinueTaskRightAwayNotNull() {
        Boolean bool = this.j;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private final <T> T a(int i, c.f.a.b<? super String, ? extends T> bVar) {
        String str = this.n;
        if (str == null) {
            return null;
        }
        String str2 = str;
        if (n.b((CharSequence) str2, (CharSequence) ",", false, 2, (Object) null)) {
            str = (String) j.a(n.b((CharSequence) str2, new String[]{","}, false, 0, 6, (Object) null), i);
        } else if (i != 0) {
            str = null;
        }
        if (str == null) {
            return null;
        }
        if (str != null) {
            return bVar.invoke(n.b((CharSequence) str).toString());
        }
        throw new q("null cannot be cast to non-null type kotlin.CharSequence");
    }

    static final class a extends l implements c.f.a.b<String, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f4937a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        public final Integer invoke(String str) {
            k.b(str, "it");
            return n.c(str);
        }
    }

    static final class b extends l implements c.f.a.b<String, com.joaomgcd.taskerm.toast.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f4938a = new b();

        b() {
            super(1);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.a.g$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<com.joaomgcd.taskerm.toast.d> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f4939a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(String str) {
                super(0);
                this.f4939a = str;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // c.f.a.a
            public final com.joaomgcd.taskerm.toast.d invoke() {
                return com.joaomgcd.taskerm.toast.d.valueOf(this.f4939a);
            }
        }

        @Override // c.f.a.b
        public final com.joaomgcd.taskerm.toast.d invoke(String str) {
            k.b(str, "it");
            return (com.joaomgcd.taskerm.toast.d) aj.a((c.f.a.b) null, new AnonymousClass1(str), 1, (Object) null);
        }
    }

    private final Integer a(int i) {
        return (Integer) a(i, a.f4937a);
    }

    public final com.joaomgcd.taskerm.toast.d getPositionEnum() {
        return (com.joaomgcd.taskerm.toast.d) a(0, b.f4938a);
    }

    public final Integer getPositionOffsetX() {
        return a(1);
    }

    public final Integer getPositionOffsetY() {
        return a(2);
    }
}
