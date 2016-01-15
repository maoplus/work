package Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;
import org.junit.Test;

public class TimeUtil {
    public static Date FormatTime(String now) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(now);
    }
    
    public static String _TIME_(Date now) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(now);
    }
    
    public static String dateToString(Date now) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        return sdf.format(now);
    }
    
    @Test
    public void toTest() {
        System.out.println(dateToString(new Date()));
    }
    
    @Test
    public void getDateIgnoreHour() {
        Date d = new Date();
        System.out.println(_TIME_(d));
    }
    
    public static Date ignareHoursMinutesSeconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    public boolean isOverlapTime(Date effectiveDateFrom1,
            Date effectiveDateTo1, Date effectiveDateFrom2,
            Date effectiveDateTo2) {
        DateTime start1 = new DateTime(
                ignareHoursMinutesSeconds(effectiveDateFrom1));
        DateTime end1 = new DateTime(
                ignareHoursMinutesSeconds(effectiveDateTo1));
        DateTime start2 = new DateTime(
                ignareHoursMinutesSeconds(effectiveDateFrom2));
        DateTime end2 = new DateTime(
                ignareHoursMinutesSeconds(effectiveDateTo2));
        Interval interval = new Interval(start1, end1);
        Interval interval2 = new Interval(start2, end2);
        return interval.overlaps(interval2);
    }
    
    @Test
    public void testDate() throws ParseException {
        Date s1 = FormatTime("2015-08-07 14:25:44");
        Date e1 = FormatTime("2015-08-17 14:25:44");
        Date s = FormatTime("2015-08-01 14:25:44");
        Date e = FormatTime("2015-08-09 14:25:44");
        System.out.println(isOverlapTime(s, e, s1, e1));
    }
    
    public Date double2Date(Double d) {
        
        Date t = null;
        
        try {
            Calendar base = Calendar.getInstance();
            base.set(1900, 0, 0, 0, 0, 0);
            // base.add(Calendar.DATE, d.intValue());
            base.add(Calendar.DATE, d.intValue());
            t = base.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return t;
        
    }
    
    @Test
    public void testList() throws ParseException {
        System.out.println(_TIME_(new Date(43320 * 1000)));
    }
    
    public Date timeDivide(Date d) {
        
        return d;
    }
    
    private int getMonthsBetween(Date start, Date end) {
        DateTime dateStart = new DateTime(start);
        DateTime dateEnd = new DateTime(end);
        return Months.monthsBetween(
                dateStart.withDayOfMonth(dateStart.getDayOfMonth()),
                dateEnd.withDayOfMonth(dateEnd.getDayOfMonth())).getMonths();
        
    }
    
    private boolean lessThanThreeMonths(Date start, Date end) {
        
        DateTime dateStart = new DateTime(start).plusMonths(3);
        DateTime dateEnd = new DateTime(end);
        return dateEnd.isBefore(dateStart);
    }
    
    @Test
    public void addMonths() throws ParseException {
        Date s1 = FormatTime("2015-05-22 14:25:44");
        DateTime date1 = new DateTime(s1);
        Date s2 = date1.plusMonths(3).toDate();
        System.out.println(_TIME_(s2));
    }
    
    @Test
    public void testMp() throws ParseException {
        Date e1 = FormatTime("2015-1-07 13:25:44");
        Date s1 = FormatTime("2015-08-07 14:25:44");
        System.out.println(lessThanThreeMonths(e1, s1));
    }
    
    @Test
    public void test44() throws ParseException {
        Date s1 = FormatTime("2015-08-07 14:25:44");
        DateTime dateStart = new DateTime(s1);
        DateTime dateStart2 = dateStart.withDayOfYear(dateStart.getDayOfYear());
        Integer a = 1;
        Integer b = null;
        System.out.println(a.equals(b));
        System.out.println(_TIME_(dateStart2.toDate()));
    }
    
    public static Date FormatTime2(String now) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(now);
    }
    
    @Test
    public void toDouble() throws ParseException {
        Date s1 = FormatTime("2015-05-28 13:00:00");
        Date s2 = FormatTime("2015-08-28 13:00:00");
        Double d1 = AbstractNameValueMapper.converToDouble(s1);
        Double d2 = AbstractNameValueMapper.converToDouble(s2);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(s1.before(s2));
        System.out.println("global_commitment_amount".toUpperCase());
    }
}
