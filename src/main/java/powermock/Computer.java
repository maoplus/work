package powermock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class Computer {
    private   String          name="xiajiajia";
    public   final String          myname="xiajiajia";
    private String          cpu;
    protected static Logger logger = LoggerFactory.getLogger(Computer.class
                                           .getName());
    protected int           count  = 0;
    public Computer(){
        System.out.println("create computer");
    }
    public void prtParent() {
        if (count <= 0) {
            System.out.println("error happend");
        }
        else {
            System.out.println("good");
        }
        System.out.println("parent" + count);
    }
    
    public void prtParValue(String value){
        System.out.println("hello i am "+value);
    }
     
    
    public TransactionStatus getStatus() {
        return status;
    }
    
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
    
    
    protected TransactionStatus          status;
    
    public Computer withName(String name) {
        this.name = name;
        return this;
    }
    
    public Computer withCpu(String cpu) {
        this.cpu = cpu;
        return this;
    }
    
   
   public String getName(String name1,String name2){
       return name1+"."+name2;
   }
    
    public String getCpu() {
        
        return cpu;
    }
    
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Computer(Object...message){
      System.out.println("mock");  
    }
    
    
}
