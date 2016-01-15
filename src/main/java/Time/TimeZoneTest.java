package Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;

public class TimeZoneTest {
    private static final String dateString = "2015-10-23";
    
    public static Date FormatTime(String now) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(now);
    }
    
    public static String _TIME_(Date now) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(now);
    }
    
    @Test
    public void hello3() throws ParseException {
        Date d = new Date( 0 );
        Date dbefore = FormatTime(dateString);
        System.out.println("new Date(0) before :" + d.toString());
        System.out.println("SimpleDateFormat before:" + _TIME_(dbefore));
        //设置时区，系统里的时区不是北京时间
        TimeZone.setDefault(TimeZone.getTimeZone("Canada/Newfoundland"));
        Date d2 = new Date( 0 );
        Date dafter = FormatTime(dateString);
        System.out.println("new Date( 0 ) after :" + d2.toString());
        System.out.println("SimpleDateFormat after:" + _TIME_(dafter));
    }
}
