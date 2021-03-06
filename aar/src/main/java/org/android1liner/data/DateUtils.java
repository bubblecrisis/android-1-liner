package org.android1liner.data;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    private DateUtils() {}
    public static final String GMTZ = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String HHMM = "hh:mm a";
    public static final String YYYYMMDD = "yyyy MM dd";
    public static final String DDMMYYYY = "dd/MM/yyyy";
    public static final String DDMMYY_HHMM = "dd/MM/yy hh:mm a";

    public static boolean isToday(long timeSinceEpoc) {
        return isToday(new Date(timeSinceEpoc));
    }

    public static boolean isToday(Date date) {
        Date today = dateOnly(new Date());
        Date compare = dateOnly(date);
        return (today.equals(compare));
    }

    public static int year(Date date) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int month(Date date) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH);
    }

    public static Date dateOnly(Date date) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date endOfDay(Date date) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 9999);
        return c.getTime();
    }

    public static SimpleDateFormat DDMMYYYY() {
        return new SimpleDateFormat(DDMMYYYY);
    }

    public static SimpleDateFormat GMTZ() {
        return new SimpleDateFormat(GMTZ);
    }

    public static SimpleDateFormat DDMMYY_HHMM() {
        return new SimpleDateFormat(DDMMYY_HHMM);
    }

    public static String formatShortest(Date date) {
        if (isToday(date)) {
            return new SimpleDateFormat("h:mm a").format(date);
        }
        else {
            return new SimpleDateFormat("dd/MM/yy h:mm a").format(date);
        }
    }

    /**
     * Parse a date using SimpleDateFormat. Returns null instead of exception.
     * @param format
     * @param date
     * @return
     */
    public static Date safeParse(SimpleDateFormat format, String date) {
        try {
            return format.parse(date);
        }
        catch(Exception e) {
            return null;
        }
    }

    public static Date smartParse(String date) {
        if (date == null) return null;
        Date d = null;
        d = safeParse(DDMMYY_HHMM(), date); if (d != null) return d;
        d = safeParse(DDMMYYYY(), date); if (d != null) return d;
        // TOOD: more parsing options
        return null;
    }
}