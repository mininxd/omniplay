package com.joaomgcd.taskerm.action.system;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.dm;
import cyanogenmod.alarmclock.ClockContract;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "na")
public final class OutputQueryNextAlarm {
    static final /* synthetic */ c.j.g[] $$delegatedProperties = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(OutputQueryNextAlarm.class), "date", "getDate()Ljava/util/Date;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(OutputQueryNextAlarm.class), "timeToAlarmMs", "getTimeToAlarmMs()J")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(OutputQueryNextAlarm.class), "timeToAlarmSeconds", "getTimeToAlarmSeconds()J")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(OutputQueryNextAlarm.class), "timeToAlarmMinutes", "getTimeToAlarmMinutes()D")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(OutputQueryNextAlarm.class), "timeToAlarmHours", "getTimeToAlarmHours()D")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(OutputQueryNextAlarm.class), "timeToAlarmDays", "getTimeToAlarmDays()D"))};
    private final String packageName;
    private final long triggerTime;
    private final c.e date$delegate = c.f.a(new a());
    private final c.e timeToAlarmMs$delegate = c.f.a(new e());
    private final c.e timeToAlarmSeconds$delegate = c.f.a(new f());
    private final c.e timeToAlarmMinutes$delegate = c.f.a(new d());
    private final c.e timeToAlarmHours$delegate = c.f.a(new c());
    private final c.e timeToAlarmDays$delegate = c.f.a(new b());

    public final Date getDate() {
        c.e eVar = this.date$delegate;
        c.j.g gVar = $$delegatedProperties[0];
        return (Date) eVar.b();
    }

    public final double getTimeToAlarmDays() {
        c.e eVar = this.timeToAlarmDays$delegate;
        c.j.g gVar = $$delegatedProperties[5];
        return ((Number) eVar.b()).doubleValue();
    }

    public final double getTimeToAlarmHours() {
        c.e eVar = this.timeToAlarmHours$delegate;
        c.j.g gVar = $$delegatedProperties[4];
        return ((Number) eVar.b()).doubleValue();
    }

    public final double getTimeToAlarmMinutes() {
        c.e eVar = this.timeToAlarmMinutes$delegate;
        c.j.g gVar = $$delegatedProperties[3];
        return ((Number) eVar.b()).doubleValue();
    }

    public final long getTimeToAlarmMs() {
        c.e eVar = this.timeToAlarmMs$delegate;
        c.j.g gVar = $$delegatedProperties[1];
        return ((Number) eVar.b()).longValue();
    }

    public final long getTimeToAlarmSeconds() {
        c.e eVar = this.timeToAlarmSeconds$delegate;
        c.j.g gVar = $$delegatedProperties[2];
        return ((Number) eVar.b()).longValue();
    }

    static final class b extends c.f.b.l implements c.f.a.a<Double> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Double invoke() {
            return Double.valueOf(a());
        }

        public final double a() {
            return OutputQueryNextAlarm.this.getTimeToAlarmHours() / ((double) 24);
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<Double> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Double invoke() {
            return Double.valueOf(a());
        }

        public final double a() {
            return OutputQueryNextAlarm.this.getTimeToAlarmMinutes() / ((double) 60);
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<Double> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Double invoke() {
            return Double.valueOf(a());
        }

        public final double a() {
            return OutputQueryNextAlarm.this.getTimeToAlarmSeconds() / ((double) 60);
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<Long> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Long invoke() {
            return Long.valueOf(a());
        }

        public final long a() {
            return OutputQueryNextAlarm.this.getTriggerTime() - dm.f();
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<Long> {
        f() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Long invoke() {
            return Long.valueOf(a());
        }

        public final long a() {
            return OutputQueryNextAlarm.this.getTimeToAlarmMs() / 1000;
        }
    }

    public OutputQueryNextAlarm(long j, String str) {
        this.triggerTime = j;
        this.packageName = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_time_time_ms_description, labelResId = R.string.query_next_alarm_time_time_ms, name = "time_ms")
    public final long getTriggerTime() {
        return this.triggerTime;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_package_description, labelResId = R.string.pl_package, name = "package")
    public final String getPackageName() {
        return this.packageName;
    }

    static final class a extends c.f.b.l implements c.f.a.a<Date> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Date invoke() {
            return new Date(OutputQueryNextAlarm.this.getTriggerTime());
        }
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_time_description, labelResId = R.string.pl_time, name = "time")
    public final String getTime() {
        String str = new SimpleDateFormat("HH:mm").format(getDate());
        c.f.b.k.a((Object) str, "SimpleDateFormat(\"HH:mm\").format(date)");
        return str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_time_day_description, labelResId = R.string.word_day, name = ClockContract.InstancesColumns.DAY)
    public final String getDay() {
        String str = new SimpleDateFormat("dd").format(getDate());
        c.f.b.k.a((Object) str, "SimpleDateFormat(\"dd\").format(date)");
        return str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_time_month_description, labelResId = R.string.word_month, name = ClockContract.InstancesColumns.MONTH)
    public final String getMonth() {
        String str = new SimpleDateFormat("MM").format(getDate());
        c.f.b.k.a((Object) str, "SimpleDateFormat(\"MM\").format(date)");
        return str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_time_year_description, labelResId = R.string.word_year, name = ClockContract.InstancesColumns.YEAR)
    public final String getYear() {
        String str = new SimpleDateFormat("yyyy").format(getDate());
        c.f.b.k.a((Object) str, "SimpleDateFormat(\"yyyy\").format(date)");
        return str;
    }

    public final BigDecimal getRound(double d2) {
        return new BigDecimal(d2).setScale(2, RoundingMode.HALF_EVEN);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_time_to_alarm_description, labelResId = R.string.query_next_alarm_time_to_alarm_seconds, name = "time_to_alarm_seconds")
    public final String getTimeToAlarmSecondsString() {
        return String.valueOf(getTimeToAlarmSeconds());
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_time_to_alarm_description, labelResId = R.string.query_next_alarm_time_to_alarm_minutes, name = "time_to_alarm_minutes")
    public final String getTimeToAlarmMinutesString() {
        String string = getRound(getTimeToAlarmMinutes()).toString();
        c.f.b.k.a((Object) string, "timeToAlarmMinutes.round.toString()");
        return string;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_time_to_alarm_description, labelResId = R.string.query_next_alarm_time_to_alarm_hours, name = "time_to_alarm_hours")
    public final String getTimeToAlarmHoursString() {
        String string = getRound(getTimeToAlarmHours()).toString();
        c.f.b.k.a((Object) string, "timeToAlarmHours.round.toString()");
        return string;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.query_next_alarm_time_to_alarm_description, labelResId = R.string.query_next_alarm_time_to_alarm_days, name = "time_to_alarm_days")
    public final String getTimeToAlarmDaysString() {
        String string = getRound(getTimeToAlarmDays()).toString();
        c.f.b.k.a((Object) string, "timeToAlarmDays.round.toString()");
        return string;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!c.f.b.k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            OutputQueryNextAlarm outputQueryNextAlarm = (OutputQueryNextAlarm) obj;
            return this.triggerTime == outputQueryNextAlarm.triggerTime && !(c.f.b.k.a((Object) this.packageName, (Object) outputQueryNextAlarm.packageName) ^ true);
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.action.system.OutputQueryNextAlarm");
    }

    public int hashCode() {
        int iHashCode = Long.valueOf(this.triggerTime).hashCode() * 31;
        String str = this.packageName;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return this.packageName + " - " + getTime();
    }
}
