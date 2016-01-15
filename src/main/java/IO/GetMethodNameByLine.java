package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class GetMethodNameByLine {
    final static String                    sourceCodeList = "f:/t/sourcecode.txt";
    private final static String            targetValue1   = "SettlementInstruction";
    private final static String            targetValue2   = "ISettlementInstruction";
    
    private final static Predicate<String> filterPath     = path -> !path
                                                                  .contains("test")
                                                                  && !path.contains("Test");
    
    public static void main(String[] args) throws Exception {
        Files.lines(Paths.get(sourceCodeList))
                .map(line -> {
                    return line.substring(0,
                            line.lastIndexOf("java") + "java".length());
                }).filter(filterPath).forEach(path -> {
                    try {
                        printMethodName(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
    
    private static void printMethodName(String path) throws ParseException,
            IOException {
        FileInputStream in = new FileInputStream(path);
        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        PackageDeclaration packagesPaths = cu.getPackage();
        MethodVisitor m = new MethodVisitor(cu);
        m.visit(cu, null);
        Map<Integer, String> lineNumberToMethod = m.getTempList();
        String classname = m.getClassName();
        List<String> lines = Files.readAllLines(Paths.get(path));
        final int len = lines.size();
        for (int i = 0; i < len; i++) {
            String lineValue = lines.get(i);
            if (!lineValue.contains("import ")
                    && lineValue.contains(targetValue1)) {
                if (!classname.equals(targetValue1)) {
                    String methodName = lineNumberToMethod.get(i);
                    if (null == methodName) {
                        methodName = lineValue;
                    }
                    System.out.println(packagesPaths.getName().toString() + "."
                            + classname + " " + methodName + " " + (i + 1));
                }
                
            }
        }
    }
    
    private static class MethodVisitor extends VoidVisitorAdapter {
        private Map<Integer, String> lineNumberToMethod = new HashMap<>();
        private String               className;
        CompilationUnit              cu;
        
        public MethodVisitor(CompilationUnit cu) {
            this.cu = cu;
            List<TypeDeclaration> typeList = cu.getTypes();
            // class name
            if (null != typeList && !typeList.isEmpty()) {
                className = typeList.stream().findFirst().get().getName();
            }
        }
        
        public Map<Integer, String> getTempList() {
            return lineNumberToMethod;
        }
        
        public String getClassName() {
            return className;
        }
        
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            int beginLine = n.getBeginLine();
            int endLine = n.getEndLine();
            IntStream.rangeClosed(beginLine, endLine).forEach(v -> {
                lineNumberToMethod.put(v, n.getName());
            });
        }
    }
    
}
