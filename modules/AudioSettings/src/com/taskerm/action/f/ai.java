package com.joaomgcd.taskerm.action.f;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.alarmclock.ClockContract;
import net.danlew.android.joda.R;
import org.joda.time.DateTime;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "dt")
class ai {
    private final DateTime dateTime;
    private final int dayOfMonth;
    private final long millis;
    private final int monthOfYear;
    private final long seconds;
    private final int year;

    public ai(DateTime dateTime) {
        c.f.b.k.b(dateTime, "dateTime");
        this.dateTime = dateTime;
        this.millis = this.dateTime.c();
        this.seconds = this.millis / 1000;
        this.dayOfMonth = this.dateTime.o();
        this.monthOfYear = this.dateTime.l();
        this.year = this.dateTime.j();
    }

    public final DateTime getDateTime() {
        return this.dateTime;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.milliseconds_since_epoch, index = 1, labelResId = R.string.word_milliseconds, name = "millis")
    public final long getMillis() {
        return this.millis;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.seconds_since_epoch, index = 2, labelResId = R.string.pl_seconds, name = "seconds")
    public final long getSeconds() {
        return this.seconds;
    }

    @TaskerOutputVariable(index = 3, labelResId = R.string.day_of_month, name = "day_of_month")
    public final int getDayOfMonth() {
        return this.dayOfMonth;
    }

    @TaskerOutputVariable(index = 4, labelResId = R.string.month_of_year, name = "month_of_year")
    public final int getMonthOfYear() {
        return this.monthOfYear;
    }

    @TaskerOutputVariable(index = 5, labelResId = R.string.word_year, name = ClockContract.InstancesColumns.YEAR)
    public final int getYear() {
        return this.year;
    }
}
