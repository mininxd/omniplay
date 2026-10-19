package com.joaomgcd.taskerm.action.f;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import java.util.Arrays;
import net.danlew.android.joda.R;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(indexDigits = 2, varPrefix = "dt")
class aj extends ai {
    private final int centuryOfEra;
    private final int dayOfWeek;
    private final int dayOfYear;
    private final int era;
    private final int hourOfDay;
    private final boolean isWeekDay;
    private final int millisOfDay;
    private final int millisOfSecond;
    private final int minuteOfDay;
    private final int minuteOfHour;
    private final int secondOfDay;
    private final int secondOfMinute;
    private final int weekOfMonth;
    private final int weekOfYear;
    private final int yearOfCentury;
    private final int yearOfEra;
    private final DateTimeZone zone;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aj(DateTime dateTime) {
        super(dateTime);
        c.f.b.k.b(dateTime, "dateTime");
        this.millisOfSecond = dateTime.w();
        this.millisOfDay = dateTime.v();
        this.secondOfMinute = dateTime.u();
        this.secondOfDay = dateTime.t();
        this.minuteOfHour = dateTime.s();
        this.minuteOfDay = dateTime.r();
        this.hourOfDay = dateTime.q();
        this.dayOfWeek = dateTime.p();
        this.dayOfYear = dateTime.n();
        this.weekOfMonth = getDayOfMonth() % 7;
        this.weekOfYear = dateTime.m();
        this.yearOfCentury = dateTime.i();
        this.yearOfEra = dateTime.h();
        this.centuryOfEra = dateTime.g();
        this.era = dateTime.f();
        this.isWeekDay = this.dayOfWeek < 6;
        this.zone = dateTime.x();
    }

    @TaskerOutputVariable(index = 5, labelResId = R.string.millis_of_second, name = "millis_of_second")
    public final int getMillisOfSecond() {
        return this.millisOfSecond;
    }

    @TaskerOutputVariable(index = 6, labelResId = R.string.millis_of_day, name = "millis_of_day")
    public final int getMillisOfDay() {
        return this.millisOfDay;
    }

    @TaskerOutputVariable(index = 7, labelResId = R.string.second_of_minute, name = "second_of_minute")
    public final int getSecondOfMinute() {
        return this.secondOfMinute;
    }

    @TaskerOutputVariable(index = 8, labelResId = R.string.second_of_day, name = "second_of_day")
    public final int getSecondOfDay() {
        return this.secondOfDay;
    }

    @TaskerOutputVariable(index = 9, labelResId = R.string.minute_of_hour, name = "minute_of_hour")
    public final int getMinuteOfHour() {
        return this.minuteOfHour;
    }

    @TaskerOutputVariable(index = 10, labelResId = R.string.minute_of_day, name = "minute_of_day")
    public final int getMinuteOfDay() {
        return this.minuteOfDay;
    }

    @TaskerOutputVariable(index = 11, labelResId = R.string.hour_of_day, name = "hour_of_day")
    public final int getHourOfDay() {
        return this.hourOfDay;
    }

    @TaskerOutputVariable(index = 12, labelResId = R.string.day_of_week, name = "day_of_week")
    public final int getDayOfWeek() {
        return this.dayOfWeek;
    }

    @TaskerOutputVariable(index = 13, labelResId = R.string.day_of_year, name = "day_of_year")
    public final int getDayOfYear() {
        return this.dayOfYear;
    }

    @TaskerOutputVariable(index = 14, labelResId = R.string.week_of_month, name = "week_of_month")
    public final int getWeekOfMonth() {
        return this.weekOfMonth;
    }

    @TaskerOutputVariable(index = 15, labelResId = R.string.week_of_year, name = "week_of_year")
    public final int getWeekOfYear() {
        return this.weekOfYear;
    }

    @TaskerOutputVariable(index = 16, labelResId = R.string.year_of_century, name = "year_of_century")
    public final int getYearOfCentury() {
        return this.yearOfCentury;
    }

    @TaskerOutputVariable(index = 17, labelResId = R.string.year_of_era, name = "year_of_era")
    public final int getYearOfEra() {
        return this.yearOfEra;
    }

    @TaskerOutputVariable(index = 18, labelResId = R.string.century_of_era, name = "century_of_era")
    public final int getCenturyOfEra() {
        return this.centuryOfEra;
    }

    @TaskerOutputVariable(index = 19, labelResId = R.string.era, name = "era")
    public final int getEra() {
        return this.era;
    }

    @TaskerOutputVariable(index = 20, labelResId = R.string.is_week_day, name = "is_week_day")
    public final boolean isWeekDay() {
        return this.isWeekDay;
    }

    @TaskerOutputVariable(index = 21, labelResId = R.string.zone, name = "zone")
    public final DateTimeZone getZone() {
        return this.zone;
    }

    @TaskerOutputVariable(index = 22, labelResId = R.string.zone_offset, name = "zone_offset")
    public final String getZoneOffset() {
        int iB = getDateTime().x().b(getDateTime().c());
        Period period = new Period(Math.abs(iB));
        String str = iB >= 0 ? "+" : "-";
        c.f.b.z zVar = c.f.b.z.f2031a;
        Object[] objArr = {Integer.valueOf(period.a()), Integer.valueOf(period.b())};
        String str2 = String.format(str + "%02d:%02d", Arrays.copyOf(objArr, objArr.length));
        c.f.b.k.a((Object) str2, "java.lang.String.format(format, *args)");
        return str2;
    }
}
