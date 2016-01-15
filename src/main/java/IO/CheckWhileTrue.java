package IO;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class CheckWhileTrue {
    
    private boolean checkWhile(String filepath) {
        String valueMessage = "";
        try {
            valueMessage = getStr(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String reg = "while\\s*\\(.*?\\)";
        Pattern pattern = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = pattern.matcher(valueMessage);
        boolean result = m.find();
        List<String> whileList = new ArrayList<>();
        while (result) {
            whileList.add(m.group());
            result = m.find();
        }
        return whileList.stream().filter(value -> !value.contains("hasNext"))
                .count() > 0;
    }
    
    private static final String End = ".java";
    
    private String getStr(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    
    private static class FileVisitorUtil extends SimpleFileVisitor<Path> {
        private Set<String> list = new HashSet<>();
        
        public Set<String> getList() {
            return list;
        }
        
        private void isFile(String dir) {
            if (dir.endsWith(End)) {
                list.add(dir);
            }
        }
        
        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                BasicFileAttributes attrs) throws IOException {
            String filePath = dir.toString();
            String fileName = dir.getFileName().toString();
            if (filePath.contains("old") || filePath.contains(".svn")
                    || filePath.contains(".settings") || fileName.equals("xml")) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            return FileVisitResult.CONTINUE;
        }
        
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {
            String filePath = file.toString();
            isFile(filePath);
            return FileVisitResult.CONTINUE;
        }
    }
    
    private String getFileNames(String fileName) {
        Path p = Paths.get(fileName);
        String tempName = p.getName(p.getNameCount() - 1).toString();
        return tempName.substring(0, tempName.lastIndexOf(End)) + "Test" + End;
    }
    
    private boolean checkHasTest( final Set<String> result,final String implClass){
      return result.stream().anyMatch(
                fileName -> fileName.endsWith(getFileNames(implClass)));
    }
    @Test
    public void myt() throws Exception {
        FileVisitorUtil f = new FileVisitorUtil();
        Files.walkFileTree(Paths.get("F:\\workspace\\copy"), f);
        final Set<String> result = f.getList();
        Predicate<String> checkWhileTrue = filepath -> checkWhile(filepath)&&checkHasTest(result,filepath);
        result.stream().filter(checkWhileTrue).forEach(System.out::println);
    }
    
}
