package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CompareUnitTestWithImpl {
    private final static String tespStart = "test";
    
    public static void main(String[] args) throws Exception {
        Map<String, String> map = Files
                .readAllLines(Paths.get(getClassPath()))
                .stream()
                .map(v -> {
                    v = v.replace("\\s*", "");
                    return v;
                })
                .collect(
                        Collectors.toMap(v -> v.split("=")[0],
                                v -> v.split("=")[1]));
        CompareUnitTestWithImpl m = new CompareUnitTestWithImpl();
        List<String> impl = m.retriveSource(map.get("impl")).stream()
                .filter(v -> !v.matches("set\\w+ActionTemplate"))
                .collect(Collectors.toList());
        List<String> test = m
                .retriveSource(map.get(tespStart))
                .stream()
                .filter(v -> v.startsWith(tespStart))
                .map(v -> {
                    return firstLetterLowercase(v.substring(tespStart.length()));
                }).collect(Collectors.toList());
        m.retriveSource(map.get(tespStart))
                .stream()
                .filter(v -> v.startsWith(tespStart))
                .map(v -> {
                    return "TradeServiceJdbcImplTest"
                            + "#"
                            + v
                            + "#"
                            + firstLetterLowercase(v.substring(tespStart
                                    .length()));
                }).forEach(System.out::println);
//        impl.stream().forEach(
//                v -> System.out.println("PartyServiceJdbcImpl." + v));
        // impl.removeAll(test);
        // System.out.println("Belowing have no unit test.");
        // System.out
        // .println("--------------------------------------------------------------------------------------");
        // impl.stream().forEach(System.out::println);
    }
    
    static String firstLetterLowercase(String name) {
        char[] cs = name.toCharArray();
        if ('A' <= cs[0] && cs[0] <= 'Z') {
            cs[0] += 32;
        }
        else {
            return name;
        }
        return String.valueOf(cs);
    }
    
    private List<String> retriveSource(String path) throws ParseException,
            IOException {
        FileInputStream in = new FileInputStream(path);
        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        MethodVisitor m = new MethodVisitor();
        m.visit(cu, null);
        return m.getTempList();
    }
    
    // private static String getPath() throws IOException, URISyntaxException {
    // return new String(Files.readAllBytes(Paths.get(getClassPath())));
    // }
    
    private static URI getClassPath() throws URISyntaxException {
        return CompareUnitTestWithImpl.class.getClassLoader().getResource("test.txt")
                .toURI();
    }
    
    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     * 
     * @param <A>
     */
    private class MethodVisitor extends VoidVisitorAdapter {
        private List<String> tempList = new ArrayList<>();
        
        public List<String> getTempList() {
            return tempList;
        }
        
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            if (1 == n.getModifiers()) {
                List<AnnotationExpr> annotations = n.getAnnotations();
                boolean isHasDeprecated = false;
                if (null != annotations && !annotations.isEmpty()) {
                    isHasDeprecated = annotations.stream().anyMatch(
                            v -> v.getName().toString().equals("Deprecated"));
                }
                if (!isHasDeprecated) {
                    tempList.add(n.getName());
                }
            }
        }
    }
}