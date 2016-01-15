package IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;

public class FileDemo {
    @Test
    public void test1() throws IOException {
        String a = null;
        a.length();
    }
    
    private String getIP(String str) {
        int beginIndex = str.indexOf("G") + 1;
        int endIndex = str.indexOf(")") - 1;
        
        String targetResult = str.substring(beginIndex, endIndex);
        return targetResult;
    }
    
    int count = 0;
    
    @Test
    public void test3() throws IOException {
        
        Stream<String> s = Files.lines(Paths.get("C:/x/a.txt"));
        s.filter(line -> line.contains("testG")).map(line -> {
            return "PartyServiceJdbcImpl.g" + getIP(line);
        }).distinct().forEach(line -> {
            System.out.println(line);
        });
    }
    
    public String getValue(String str, String reg) {
        str = str.toLowerCase();
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(str);
        boolean result = m.find();
        String targetResult = "";
        while (result) {
            String value = m.group();
            targetResult = value;
            result = m.find();
        }
        return targetResult;
    }
    
    @Test
    public void testGetValue() throws IOException {
        Files.lines(Paths.get("f:/t/a.txt"))
                .filter(line -> line.contains("nextval"))
                .map(line -> {
                    return getValue(line, "\\w+\\.nextval").replace(".nextval",
                            "");
                }).distinct().forEach(line -> {
                    System.out.println(line);
                });
    }
    
    @Test
    public void test5() throws IOException {
        
        Stream<String> s = Files.lines(Paths.get("C:/x/a.txt")).map(t -> {
            return t.replaceAll("\\\\", "/");
        });
        s.forEach(v -> {
            try {
                Stream<String> value = Files.lines(Paths.get(v));
                value.forEach(line -> {
                    if (line.contains("NullPointerException")) {
                        System.out.println(v + "   " + line);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        });
    }
    
    public String getMarkit(String str) {
        String reg = "MarkitClear\\.\\w+";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(str);
        boolean result = m.find();
        String targetResult = "";
        while (result) {
            String value = m.group();
            targetResult = value;
            result = m.find();
        }
        
        return targetResult.toLowerCase().replaceAll("\\s", "");
    }
    
    @Test
    public void test6() throws IOException {
        Stream<String> commentList = Files.lines(Paths.get("C:/x/comment.txt"))
                .filter(a -> a.contains("MarkitClear")).map(t -> {
                    return getMarkit(t);
                });
        List<String> trade = Files.lines(Paths.get("C:/x/trade.txt"))
                .filter(a -> a.contains("MarkitClear")).map(t -> {
                    return getMarkit(t);
                }).collect(Collectors.toList());
        commentList
                .filter(comment -> !trade.stream().anyMatch(
                        Predicate.isEqual(comment))).forEach(
                        s -> System.out.println(s));
    }
    
    @Test
    public void replaceNumber() throws IOException {
        Files.lines(Paths.get("f:/pp.txt")).map(t -> {
            t = t.replaceAll("\\d+\\s+", "");
            return t;
        }).filter(linevalue -> linevalue.contains("hikari")).distinct()
                .forEach(System.out::println);
    }
    
    @Test
    public void replaceP() throws IOException {
        final String regex = "hikari.idleMaxAgeInMinutes";
        final String replacement = "hikari.idleMaxAgeInSeconds";
        
        Files.lines(Paths.get("f:/t/p.txt")).map(t -> {
            return Paths.get(t.replaceAll("\\\\", "/"));
        }).forEach(ps -> {
            try {
                List<String> lines = Files.lines(ps).map(r -> {
                    if (r.startsWith(regex)) {
                        r = r.replaceAll(regex, replacement);
                    }
                    
                    return r;
                }).collect(Collectors.toList());
                
                Files.write(ps, lines);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        replaceXML(regex, replacement);
    }
    
    public void replaceXML(String regex2, String replacement2)
            throws IOException {
        // final String regex = "66666666666666666666666";
        // final String replacement = "fuckdddddddd";
        Files.lines(Paths.get("f:/t/x.txt")).map(t -> {
            return Paths.get(t.replaceAll("\\\\", "/"));
        }).forEach(ps -> {
            try {
                List<String> lines = Files.lines(ps).map(r -> {
                    
                    if (r.contains(regex2)) {
                        r = r.replaceAll(regex2, replacement2);
                    }
                    
                    return r;
                }).collect(Collectors.toList());
                
                Files.write(ps, lines);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ;
    }
    
    @Test
    public void onlyreplaceXML() throws IOException {
        final String regex = "bonecp.statementsCacheSize";
        final String replacement = "hikari.statementsCacheSize";
        Files.lines(Paths.get("f:/t/x.txt")).map(t -> {
            return Paths.get(t.replaceAll("\\\\", "/"));
        }).forEach(ps -> {
            try {
                List<String> lines = Files.lines(ps).map(r -> {
                    if (r.contains(regex)) {
                        r = r.replaceAll(regex, replacement);
                        
                    }
                    
                    return r;
                }).collect(Collectors.toList());
                
                Files.write(ps, lines);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ;
    }
    
    @Test
    public void removeP() throws IOException {
        final String regex = "hikari";
        Files.lines(Paths.get("f:/t/a.txt"))
                .map(t -> {
                    return Paths.get(t.replaceAll("\\\\", "/"));
                })
                .forEach(
                        ps -> {
                            try {
                                List<String> lines = Files
                                        .lines(ps)
                                        .filter(linevalue -> !(linevalue
                                                .startsWith(regex)))
                                        .collect(Collectors.toList());
                                Files.write(ps, lines);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
    }
    
    private boolean getQoute(String a) {
        int count = 0;
        final char targetChar = '{';
        for (int i = 0; i < a.length(); i++) {
            if (targetChar == a.charAt(i)) {
                count++;
            }
        }
        return count >= 1;
    }
    
    @Test
    public void testGetErrorCode() throws IOException {
        String v = Files.lines(Paths.get("f:/t/a.txt"))
                .filter(a -> getQoute(a)).map(t -> {
                    return t.substring(0, t.indexOf("#")).trim();
                }).reduce("", (a, b) -> a + "|" + b);// .forEach(System.out::println);
        System.out.println(v);
    }
    
    @Test
    public void GetErrorCode() throws IOException {
        Files.lines(Paths.get("f:/t/a.txt")).filter(a -> getQoute(a))
                .forEach(System.out::println);
        
    }
    
    @Test
    public void readWriteP() {
        CaseFormat fromFormat = CaseFormat.LOWER_CAMEL;
        CaseFormat toFormat = CaseFormat.UPPER_CAMEL;
        String s = "lowerCamelHello";
        System.out.println(fromFormat.to(toFormat, s));
    }
    
    @Test
    public void changeVersion() throws IOException {
        Files.walk(Paths.get("F:\\workspace\\2"))
                .filter(file -> file.endsWith("pom.xml"))
                .forEach(System.out::println);
    }
    

    private static String capitalizationName(String name) {
        char[] cs = name.toCharArray();
        if (null == cs || cs.length == 0) {
            return "";
        }
        if ('a' <= cs[0] && cs[0] <= 'z') {
            cs[0] -= 32;
        }
        else {
            return name;
        }
        return String.valueOf(cs);
    }
    
    @Test
    public void readFile() throws IOException {
        // Files.readAllLines(Paths.get("f:/t/a.txt")).forEach(v->{ myt(v);});
        Set<String> set = Sets.newHashSet();
        System.out.println(set.add("a"));
        System.out.println(set.add("a"));
    }
    
    // .reduce((a, b) -> a +"."+ b)
    
    public void printGetX(String arr) {
        String result = Arrays.stream(arr.split("[^a-zA-Z]"))
                .filter(word -> word.matches("\\w{2,}"))
                .map(v -> "get" + capitalizationName(v) + "()")
                .reduce((a, b) -> a + "." + b).get();
        System.out.println(result);
    }

}
