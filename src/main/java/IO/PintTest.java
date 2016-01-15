package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PintTest {
    private static final String TIME_OUT      = "request timed out";
    private static final String NOT_FIND_HOST = "could not find host";
    private static final String CONNECTION    = "ttl=";
                                              
    private void remind(String message) {
        try {
            Runtime.getRuntime().exec("cmd /c start echo " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private List<Boolean> connectionStatus = new ArrayList<Boolean>();
    
    @Before
    public void setUp() {
        connectionStatus.add(null);
    }
    public Boolean isAddressAvailable(String ip) {
        Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
        Process process = null; // 声明处理类对象
        String line = null; // 返回行信息
        InputStream is = null; // 输入流
        InputStreamReader isr = null; // 字节流
        BufferedReader br = null;
        try {
            process = runtime.exec("ping " + ip + " -n 1"); // PING
            is = process.getInputStream(); // 实例化输入流
            isr = new InputStreamReader(is, "GBK");// 把输入流转换成字节流
            br = new BufferedReader(isr);// 从字节中读取文本
            while ((line = br.readLine()) != null) {
                String content = line.toLowerCase();
                if (content.contains(CONNECTION)) {
                    System.out.println("connected");
                    return Boolean.TRUE;
                }
                else if (content.contains(TIME_OUT)) {
                    System.out.println("not connected");
                    return Boolean.FALSE;
                }
                else if (content.contains(NOT_FIND_HOST)) {
                    System.out.println("not connected");
                    return Boolean.FALSE;
                }
            }
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void sleeps(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void addTest(){
        for(byte b:"This is ADF.".getBytes() ){
            System.out.println(b);
        }
        
    }
    
    @Test
    public void warnConnection() {
        while (true) {
            final Boolean previousStatus = connectionStatus.remove(0);
            Boolean status = isAddressAvailable("10.72.16.141");//baidu.com
            if (Boolean.TRUE.equals(previousStatus)
                    && Boolean.FALSE.equals(status)) {
                remind("Request timed out.");
            }
            else if (Boolean.FALSE.equals(previousStatus)
                    && Boolean.TRUE.equals(status)) {
                remind("The connection is successful");
            }
            connectionStatus.add(status);
            sleeps();
        }
    }
    
}