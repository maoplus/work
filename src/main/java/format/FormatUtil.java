package format;

import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class FormatUtil {
    
    public void useLocaleCategory() {
        ResourceBundle bundle = ResourceBundle.getBundle("format.messages");
        String str = bundle.getString("GREETING");
        String msg = MessageFormat.format(str,
                new Object[] { "张三", new Date() });
        System.out.println(msg);
    }
    
    public static String formMessageFromDB(String errorDesc, Object... params) {
        try {
            MessageFormat formatter = new MessageFormat("");
            formatter.applyPattern(errorDesc);
            return formatter.format(params);
        } catch (Exception e) {
        }
        return errorDesc;
    }
    
    public static String formMessage(String bundle, String template,
            Object... params) {
        try {
            ResourceBundle messages = ResourceBundle.getBundle(bundle);
            MessageFormat formatter = new MessageFormat("");
            String originalMessage = messages.getString(template);
            formatter.applyPattern(originalMessage);
            return formatter.format(params);
        } catch (Exception e) {
        }
        return template;
    }
    
    public static String formMessage2(Object... params) {
        try {
            MessageFormat formatter = new MessageFormat("");
            String originalMessage = "Facility CUSIP cannot already exist under another credit agreement {0}.";
            formatter.applyPattern(originalMessage);
            return formatter.format(params);
        } catch (Exception e) {
        }
        return "";
    }
    
    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            int a = 0;
            int c = 2 / a;
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
        
        System.out.println(formMessage("format.bridge", "LMP_EXEC_016",
                "java.lang.ArithmeticException: / by zero"));
    }
}
