package powermock;
public class Customer extends LegacyCustomer {
    private String email;
 
    public Customer(String name, String email) {
        super(name);
        this.email = email;
        System.out.println("create");
    }
    public String getEmail() {
        return email;
    }
    
    
}