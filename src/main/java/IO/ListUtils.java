package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ListUtils {
    private List<String>  list1 = new ArrayList<String>();
    private List<String>  list2 = new ArrayList<String>();
    private static String lines = "------------------------------------------------";
    // 将List分别粘贴到两个txt里，目录随便
    private String        path1 = "f:/t/a.txt";//这个保存List1
    private String        path2 = "f:/t/b.txt";//这个保存List2
    
    private void readByline(String path, List<String> list) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
    }
    
    @Before
    public void setupOnce() {
        try {
            readByline(path1, list1);
            readByline(path2, list2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void printList(List<String> list) {
        for (String value : list) {
            System.out.println("'"+value+"',");
        }
    }
    
    @Test
    public void intersection() {
        // 两个List的交集
        list1.retainAll(list2);
        System.err.println(lines);
        System.out.println(path1 + " 与 " + path2 + "  的交集（共同部分）:");
        list1.retainAll(list2);
        printList(list1);
    }
    
    @Test
    public void different1() {
        // 两个List的差集
        System.err.println(lines);
        System.out.println(path1 + "有   " + path2 + "没有:");
        list1.removeAll(list2);
        printList(list1);
    }
    
    @Test
    public void different2() {
        // 两个List的差集
        System.err.println(lines);
        System.out.println(path2 + "有   " + path1 + "  没有:");
        list2.removeAll(list1);
        printList(list2);
    }
    
    @Test
    public void union() {
        // 两个List的并集
        System.err.println(lines);
        System.out.println(path1 + "与   " + path1 + "  的合并:");
        list2.addAll(list1);
        printList(list2);
    }
}
