package powermock;

public class Thinkpad extends Computer {
    
    public void prtChild() {
        super.prtParent();
        System.out.println("child");
    }
    public void prtChild2() {
        super.prtParValue("parent");
        System.out.println("child");
    }
}
