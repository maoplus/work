package IO;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipFile;

import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class FindDuplicationClass {
    // 穷举jar
    // 穷举class,key = class value = jar, guava mutiple map 允许一个key对应多个value
    // 计算一对多的key
    private final static String                 classRegex = ".*\\.class$";
    private final static Multimap<String, File> multimap   = Multimaps
                                                                   .synchronizedMultimap(HashMultimap
                                                                           .<String, File> create());
    
    @Test
    public void mutipleTest() {
        System.out.println("aaa.class".matches(classRegex));
    }
    
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/x");
        FileVisitorUtil f = new FileVisitorUtil();
        Files.walkFileTree(path, f);
        f.getJarList().parallelStream().forEach(jar -> {
            listClass(jar.toFile(), Charset.forName("UTF-8"));
        });
        multimap.asMap().entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1).forEach(v -> {
                    System.out.println(v.toString());
                });
    }
    
    public static void listClass(File zip, Charset charset) {
        try (ZipFile zipFile = new ZipFile(zip, charset)) {
            zipFile.stream()
                    .filter(jarFile -> jarFile.getName().matches(classRegex))
                    .forEach(file -> {
                        multimap.put(file.getName(), zip);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static class FileVisitorUtil extends SimpleFileVisitor<Path> {
        private Set<Path>           jarList = new HashSet<Path>();
        private static final String jarEnd  = ".jar";
        
        public Set<Path> getJarList() {
            return jarList;
        }
        
        private void isJarFile(Path dir) {
            if (dir.getFileName().toString().endsWith(jarEnd)) {
                jarList.add(dir);
            }
        }
        
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {
            isJarFile(file);
            return FileVisitResult.CONTINUE;
        }
    }
}
