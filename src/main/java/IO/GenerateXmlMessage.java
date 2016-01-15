package IO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

public class GenerateXmlMessage { 
    
 @Test
 public void ttt2(){
     System.out.println("ukTreatyPassportNumber1".length());
 }
   @Test(expected=Exception.class)
   public void removevar(){
       
       
   }
    @Test
    public void ttt() throws  Exception {
       String path = "f:/x/";
       File f = new File(path);
       for(File x:f.listFiles()){
           String fileName = x.getName();
           String newName =  fileName.substring(0,fileName.lastIndexOf("."));
           String newBat = path+newName+".dat";
           String newDsc = path+newName+".dsc";
           System.out.println(newName);
            new File(newDsc).createNewFile();// create dsc file
           x.renameTo(new File(newBat));//reName to dat
       }
    }

    
}
