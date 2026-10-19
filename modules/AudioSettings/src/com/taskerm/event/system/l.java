package com.joaomgcd.taskerm.event.system;

import com.joaomgcd.taskerm.action.system.OutputGetClipboard;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f6778a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f6779b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<OutputGetClipboard> f6780c;

    /* JADX WARN: Multi-variable type inference failed */
    public l() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, e = R.string.ignore_tasker_sets_help)
    public static /* synthetic */ void ignoreTaskerSets$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public l(Boolean bool, Class<OutputGetClipboard> cls) {
        this.f6779b = bool;
        this.f6780c = cls;
    }

    public /* synthetic */ l(Boolean bool, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Boolean) null : bool, (i & 2) != 0 ? OutputGetClipboard.class : cls);
    }

    public final Boolean getIgnoreTaskerSets() {
        return this.f6779b;
    }

    public final void setIgnoreTaskerSets(Boolean bool) {
        this.f6779b = bool;
    }

    public final Class<OutputGetClipboard> getOutputClass() {
        return this.f6780c;
    }

    public final void setOutputClass(Class<OutputGetClipboard> cls) {
        this.f6780c = cls;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }
}
