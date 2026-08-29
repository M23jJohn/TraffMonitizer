package androidx.compose.material3.internal;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.DecimalStyle;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CalendarModelImpl.android.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J$\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00112\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\u0016J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0014\u0010\u001a\u001a\u00020\u001b2\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\u0016J\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u000bH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u000bH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0007H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0018\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u0007H\u0016J\u001a\u0010'\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011H\u0016J\u0018\u0010(\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u0007H\u0016J\b\u0010*\u001a\u00020\u0011H\u0016J\f\u0010+\u001a\u00020!*\u00020\u000bH\u0002J\f\u0010+\u001a\u00020!*\u00020\u001fH\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR&\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006-"}, d2 = {"Landroidx/compose/material3/internal/CalendarModelImpl;", "Landroidx/compose/material3/internal/CalendarModel;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "(Ljava/util/Locale;)V", "firstDayOfWeek", "", "getFirstDayOfWeek", "()I", "today", "Landroidx/compose/material3/internal/CalendarDate;", "getToday", "()Landroidx/compose/material3/internal/CalendarDate;", "weekdayNames", "", "Lkotlin/Pair;", "", "getWeekdayNames", "()Ljava/util/List;", "formatWithPattern", "utcTimeMillis", "", "pattern", "getCanonicalDate", "timeInMillis", "getDateInputFormat", "Landroidx/compose/material3/internal/DateInputFormat;", "getDayOfWeek", "date", "getMonth", "Landroidx/compose/material3/internal/CalendarMonth;", "firstDayLocalDate", "Ljava/time/LocalDate;", "year", "month", "minusMonths", "from", "subtractedMonthsCount", "parse", "plusMonths", "addedMonthsCount", "toString", "toLocalDate", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CalendarModelImpl extends CalendarModel {
    private static final ZoneId utcTimeZoneId;
    private final int firstDayOfWeek;
    private final List<Pair<String, String>> weekdayNames;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public CalendarModelImpl(Locale locale) {
        super(locale);
        WeekFields of;
        DayOfWeek firstDayOfWeek;
        int value;
        DayOfWeek[] values;
        TextStyle textStyle;
        String displayName;
        TextStyle textStyle2;
        String displayName2;
        of = WeekFields.of(locale);
        firstDayOfWeek = of.getFirstDayOfWeek();
        value = firstDayOfWeek.getValue();
        this.firstDayOfWeek = value;
        values = DayOfWeek.values();
        ArrayList arrayList = new ArrayList(values.length);
        for (DayOfWeek dayOfWeek : values) {
            textStyle = TextStyle.FULL;
            displayName = dayOfWeek.getDisplayName(textStyle, locale);
            textStyle2 = TextStyle.NARROW;
            displayName2 = dayOfWeek.getDisplayName(textStyle2, locale);
            arrayList.add(TuplesKt.to(displayName, displayName2));
        }
        this.weekdayNames = arrayList;
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public CalendarDate getToday() {
        LocalDate now;
        int year;
        int monthValue;
        int dayOfMonth;
        LocalTime localTime;
        LocalDateTime atTime;
        ZonedDateTime atZone;
        long epochMilli;
        now = LocalDate.now();
        year = now.getYear();
        monthValue = now.getMonthValue();
        dayOfMonth = now.getDayOfMonth();
        localTime = LocalTime.MIDNIGHT;
        atTime = now.atTime(localTime);
        atZone = atTime.atZone(utcTimeZoneId);
        epochMilli = atZone.toInstant().toEpochMilli();
        return new CalendarDate(year, monthValue, dayOfMonth, epochMilli);
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public List<Pair<String, String>> getWeekdayNames() {
        return this.weekdayNames;
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public DateInputFormat getDateInputFormat(Locale locale) {
        FormatStyle formatStyle;
        Chronology ofLocale;
        String localizedDateTimePattern;
        formatStyle = FormatStyle.SHORT;
        ofLocale = Chronology.ofLocale(locale);
        localizedDateTimePattern = DateTimeFormatterBuilder.getLocalizedDateTimePattern(formatStyle, null, ofLocale, locale);
        return CalendarModelKt.datePatternAsInputFormat(localizedDateTimePattern);
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public CalendarDate getCanonicalDate(long timeInMillis) {
        Instant ofEpochMilli;
        ZonedDateTime atZone;
        LocalDate localDate;
        int year;
        int monthValue;
        int dayOfMonth;
        LocalDateTime atStartOfDay;
        ZoneOffset zoneOffset;
        ofEpochMilli = Instant.ofEpochMilli(timeInMillis);
        atZone = ofEpochMilli.atZone(utcTimeZoneId);
        localDate = atZone.toLocalDate();
        year = localDate.getYear();
        monthValue = localDate.getMonthValue();
        dayOfMonth = localDate.getDayOfMonth();
        atStartOfDay = localDate.atStartOfDay();
        zoneOffset = ZoneOffset.UTC;
        return new CalendarDate(year, monthValue, dayOfMonth, 1000 * atStartOfDay.toEpochSecond(zoneOffset));
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public CalendarMonth getMonth(long timeInMillis) {
        Instant ofEpochMilli;
        ZonedDateTime atZone;
        ZonedDateTime withDayOfMonth;
        LocalDate localDate;
        ofEpochMilli = Instant.ofEpochMilli(timeInMillis);
        atZone = ofEpochMilli.atZone(utcTimeZoneId);
        withDayOfMonth = atZone.withDayOfMonth(1);
        localDate = withDayOfMonth.toLocalDate();
        return getMonth(localDate);
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public CalendarMonth getMonth(CalendarDate date) {
        LocalDate of;
        of = LocalDate.of(date.getYear(), date.getMonth(), 1);
        return getMonth(of);
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public CalendarMonth getMonth(int year, int month) {
        LocalDate of;
        of = LocalDate.of(year, month, 1);
        return getMonth(of);
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public int getDayOfWeek(CalendarDate date) {
        DayOfWeek dayOfWeek;
        int value;
        dayOfWeek = toLocalDate(date).getDayOfWeek();
        value = dayOfWeek.getValue();
        return value;
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public CalendarMonth plusMonths(CalendarMonth from, int addedMonthsCount) {
        LocalDate plusMonths;
        if (addedMonthsCount <= 0) {
            return from;
        }
        plusMonths = toLocalDate(from).plusMonths(addedMonthsCount);
        return getMonth(plusMonths);
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public CalendarMonth minusMonths(CalendarMonth from, int subtractedMonthsCount) {
        LocalDate minusMonths;
        if (subtractedMonthsCount <= 0) {
            return from;
        }
        minusMonths = toLocalDate(from).minusMonths(subtractedMonthsCount);
        return getMonth(minusMonths);
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public String formatWithPattern(long utcTimeMillis, String pattern, Locale locale) {
        return INSTANCE.formatWithPattern(utcTimeMillis, pattern, locale, getFormatterCache$material3_release());
    }

    @Override // androidx.compose.material3.internal.CalendarModel
    public CalendarDate parse(String date, String pattern) {
        DateTimeFormatter ofPattern;
        LocalDate parse;
        int year;
        Month month;
        int value;
        int dayOfMonth;
        LocalTime localTime;
        LocalDateTime atTime;
        ZonedDateTime atZone;
        long epochMilli;
        ofPattern = DateTimeFormatter.ofPattern(pattern);
        try {
            parse = LocalDate.parse(date, ofPattern);
            year = parse.getYear();
            month = parse.getMonth();
            value = month.getValue();
            dayOfMonth = parse.getDayOfMonth();
            localTime = LocalTime.MIDNIGHT;
            atTime = parse.atTime(localTime);
            atZone = atTime.atZone(utcTimeZoneId);
            epochMilli = atZone.toInstant().toEpochMilli();
            return new CalendarDate(year, value, dayOfMonth, epochMilli);
        } catch (DateTimeParseException unused) {
            return null;
        }
    }

    public String toString() {
        return "CalendarModel";
    }

    /* compiled from: CalendarModelImpl.android.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0010J0\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\b2\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/internal/CalendarModelImpl$Companion;", "", "()V", "utcTimeZoneId", "Ljava/time/ZoneId;", "getUtcTimeZoneId$material3_release", "()Ljava/time/ZoneId;", "formatWithPattern", "", "utcTimeMillis", "", "pattern", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "cache", "", "getCachedDateTimeFormatter", "Ljava/time/format/DateTimeFormatter;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String formatWithPattern(long utcTimeMillis, String pattern, Locale locale, Map<String, Object> cache) {
            Instant ofEpochMilli;
            ZonedDateTime atZone;
            LocalDate localDate;
            String format;
            DateTimeFormatter cachedDateTimeFormatter = getCachedDateTimeFormatter(pattern, locale, cache);
            ofEpochMilli = Instant.ofEpochMilli(utcTimeMillis);
            atZone = ofEpochMilli.atZone(getUtcTimeZoneId$material3_release());
            localDate = atZone.toLocalDate();
            format = localDate.format(cachedDateTimeFormatter);
            return format;
        }

        public final ZoneId getUtcTimeZoneId$material3_release() {
            return CalendarModelImpl.utcTimeZoneId;
        }

        private final DateTimeFormatter getCachedDateTimeFormatter(String pattern, Locale locale, Map<String, Object> cache) {
            DateTimeFormatter ofPattern;
            DecimalStyle of;
            String str = "P:" + pattern + locale.toLanguageTag();
            Object obj = cache.get(str);
            if (obj == null) {
                ofPattern = DateTimeFormatter.ofPattern(pattern, locale);
                of = DecimalStyle.of(locale);
                obj = ofPattern.withDecimalStyle(of);
                cache.put(str, obj);
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.time.format.DateTimeFormatter");
            return Listener$$ExternalSyntheticApiModelOutline0.m3077m(obj);
        }
    }

    static {
        ZoneId of;
        of = ZoneId.of("UTC");
        utcTimeZoneId = of;
    }

    private final CalendarMonth getMonth(LocalDate firstDayLocalDate) {
        DayOfWeek dayOfWeek;
        int value;
        LocalTime localTime;
        LocalDateTime atTime;
        ZonedDateTime atZone;
        long epochMilli;
        int year;
        int monthValue;
        int lengthOfMonth;
        dayOfWeek = firstDayLocalDate.getDayOfWeek();
        value = dayOfWeek.getValue();
        int firstDayOfWeek = value - getFirstDayOfWeek();
        if (firstDayOfWeek < 0) {
            firstDayOfWeek += 7;
        }
        int i = firstDayOfWeek;
        localTime = LocalTime.MIDNIGHT;
        atTime = firstDayLocalDate.atTime(localTime);
        atZone = atTime.atZone(utcTimeZoneId);
        epochMilli = atZone.toInstant().toEpochMilli();
        year = firstDayLocalDate.getYear();
        monthValue = firstDayLocalDate.getMonthValue();
        lengthOfMonth = firstDayLocalDate.lengthOfMonth();
        return new CalendarMonth(year, monthValue, lengthOfMonth, i, epochMilli);
    }

    private final LocalDate toLocalDate(CalendarMonth calendarMonth) {
        Instant ofEpochMilli;
        ZonedDateTime atZone;
        LocalDate localDate;
        ofEpochMilli = Instant.ofEpochMilli(calendarMonth.getStartUtcTimeMillis());
        atZone = ofEpochMilli.atZone(utcTimeZoneId);
        localDate = atZone.toLocalDate();
        return localDate;
    }

    private final LocalDate toLocalDate(CalendarDate calendarDate) {
        LocalDate of;
        of = LocalDate.of(calendarDate.getYear(), calendarDate.getMonth(), calendarDate.getDayOfMonth());
        return of;
    }
}
