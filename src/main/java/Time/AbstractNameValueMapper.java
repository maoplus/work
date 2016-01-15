package Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Contains basic functionality that can be useful in any result mapper
 * 
 * @author bakulkumar.kakadiya
 *
 * @param <T>
 */
public abstract class AbstractNameValueMapper {
    
    private static final String           AUTOMATION_BASE_EDT = "12/30/1899 00:00:00 EDT";
    private static final String           AUTOMATION_BASE_EST = "12/30/1899 00:00:00 EST";
    
    /* date manipulation helper */
    private static final SimpleDateFormat format              = new SimpleDateFormat(
                                                                      "MM/dd/yyyy HH:mm:ss ZZZ");
    public static long                    OLE_AUTOMATION_BASE_EST;
    public static long                    OLE_AUTOMATION_BASE_EDT;
    static {
        try {
            OLE_AUTOMATION_BASE_EST = format.parse(AUTOMATION_BASE_EST)
                    .getTime();
            OLE_AUTOMATION_BASE_EDT = format.parse(AUTOMATION_BASE_EDT)
                    .getTime();
        } catch (Exception e) {
        }
    }
    public static double                  MILLIS_A_DAY        = 24 * 60 * 60 * 1000;
    
    public static double toOADate(Date date) {
        return AbstractNameValueMapper.converToDouble(date);
    }
    
    // ui always sends in EST
    public static Date fromOADate(double date) {
        return AbstractNameValueMapper.fromDoubleToDate(date);
    }
    
    public static double converToDouble(final Date date) {
        // log.info("Converting  " + format.format(date));
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.getTimeZone().inDaylightTime(date)) {
            // log.info("Using EDT base as date in daylight time");
            return (date.getTime() - OLE_AUTOMATION_BASE_EDT) / MILLIS_A_DAY;
        }
        else {
            // log.info("Using EST base ");
            return (date.getTime() - OLE_AUTOMATION_BASE_EST) / MILLIS_A_DAY;
        }
    }
    
    // ui always sends in EST base
    public static Date fromDoubleToDate(double dateValue) {
        return dateValue == 0.0 ? null : new Date((long) (dateValue
                * MILLIS_A_DAY + OLE_AUTOMATION_BASE_EST));
    }
    
}