package mytest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.base.Strings;

public class ITest {
    private static final String  reg     = "(c.q.w.rules.|c.q.workflow.rules.)\\w+";
    private static final Pattern pattern = Pattern.compile(reg);
    
    public String getKeyValue(String str) {
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            return m.group();
        }
        return "";
    }
    
    private List<String> getList(String filePah) throws IOException {
        return (List<String>) Files
                .readAllLines(Paths.get(filePah))
                .stream()
                .filter(line -> line.contains("c.q.w.rules.")
                        || line.contains("c.q.workflow.rules.")).map(line -> {
                    return getKeyValue(line);
                }).distinct().sorted().collect(Collectors.toList());
    }
    
    @Test
    public void format() {
        String sql = "SELECT DISTINCT INDEX_NAME FROM ALL_INDEXES WHERE TABLE_NAME = '%s'";
        System.out.println(String.format(sql, "credit"));
    }
    
    @Test
    public void mytt() {
        System.out
                .println(getKeyValue("RADEID/MC00024406] INFO  c.q.w.rules.TradeSettingsCheckRule - Entering [TradeSet"));
    }
    
    @Test
    public void ttt() throws IOException {
        List<String> errors = getList("C:\\Users\\xiajiajia\\Documents\\错误时间上传.java");
        List<String> rights = getList("C:\\Users\\xiajiajia\\Documents\\正常时间上传.java");
        errors.removeAll(rights);
        errors.forEach(System.out::println);
    }
    
    Set<String> whileList = new HashSet<>();
    
    public void checkWhile(String valueMessage) {
        String reg = "\\d+\\)";
        String reg2 = "\\d+[?=\\)]";
        Pattern pattern = Pattern.compile(reg2);
        Matcher m = pattern.matcher(valueMessage);
        boolean result = m.find();
        
        while (result) {
            whileList.add(m.group().replace(")", ","));
            result = m.find();
        }
    }
    
    @Test
    public void ttt2() throws IOException{
        List<String> bosss= Files.readAllLines(Paths.get("F:\\download\\JPM_Davita_BulkMessage.xml"));
        long len = bosss.size();
        for(int i=0;i<len;i++){
            String line = bosss.get(i);
           if(line.contains("partyIdScheme")){
               if(hasPartyId(line)){
                 System.out.println((i+1)+" number  "+line);  
               }
           } 
        }
    }
    public boolean hasPartyId(String line){
        int begin =  line.indexOf(">")+1;
        int end = line.lastIndexOf("<");
        if(begin>=end){
            return true;
        }
        return Strings.isNullOrEmpty(line.substring(begin,end ));
    }
    
    @Test
    public void readByLine() throws IOException {
        long count = 0;
        Files.readAllLines(Paths.get("F:\\download\\JPM_Davita_BulkMessage.xml")).stream()
                .filter(line -> line.contains("partyIdScheme=")).forEach(value -> {
                    checkWhile(value);
                });
        whileList.stream().forEach(System.out::println);
    }
    
}
