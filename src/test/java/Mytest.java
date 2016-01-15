import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import mytest.GenerateXmlMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Mytest {
    public String getKeyValue(String str) {
        str = str.substring(str.indexOf("LU_") + "LU_".length(),
                str.lastIndexOf(";"));
        return str;
    }
    
    private static Statement st;
    private static String    sql                = "SELECT DISTINCT INDEX_NAME FROM ALL_INDEXES WHERE TABLE_NAME = '%s'";
    public static Connection conn;
    
    private static String    rebuildIndexs      = "ALTER INDEX %s REBUILD;";
    private static String    analyzeSpecicIndex = "ANALYZE INDEX %s VALIDATE STRUCTURE;";
    private static String    analyzeAll    = "analyze table %s compute statistics for table for all indexes for all columns;";
    
    @Before
    public void setUp() throws SQLException {
//        TestDB basedao = new TestDB();
//        conn = basedao.getConnection();
//        st = conn.createStatement();
    }
    
    @After
    public void close() throws SQLException {
//        st.close();
//        conn.close();
        
    }
    static String fileName = null;
    static SimpleDateFormat bartDateFormat = new SimpleDateFormat(
             "yyyy_MM_dd_HH_mm_ss");
    private static String getFileName(){
        Date date = new Date();
        String log = bartDateFormat.format(date);
        return log+".txt";
    }
    public static void appendLog(String filePath ,String content) {
        try {
            if(null==fileName){
                fileName =  getFileName();
            }
            filePath+=fileName;
            content = new String(content.getBytes("GBK"), "ISO-8859-1");
            RandomAccessFile randomFile = new RandomAccessFile(filePath, "rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(content + "\r\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    private void generateSql(String tableName) throws SQLException {
       // ResultSet result = st.executeQuery(String.format(sql, tableName));
        System.out.println(String.format(analyzeAll, tableName));
//        while (result.next()) {
//            String indexName = result.getString("INDEX_NAME");
//            appendLog("F:/rebuild",String.format(rebuildIndexs, indexName));
//            appendLog("F:/analyzeSpecicIndex",String.format(analyzeSpecicIndex, indexName));
//        }
//        result.close();
    }
    
    @Test
    public void prt() throws IOException {
        Files.readAllLines(Paths.get("f:/x/a.txt")).stream()
                .filter(line -> line.contains("drop table")).map(value -> {
                    return getKeyValue(value.toUpperCase());
                }).forEach(value->{
                    try {
                        generateSql(value);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                });
        System.err.println("done!");
        
    }
}
