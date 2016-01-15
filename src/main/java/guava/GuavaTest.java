package guava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import com.mycompany.javavalidator.Car;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class GuavaTest {
    @Test
    public void anymathsTest() {
        List<MyApple> al = Arrays.asList(new MyApple(1, "red", "shanghai"),
                new MyApple(2, "grey", "dongjing"), new MyApple(3, "gray",
                        "niuyue"), new MyApple(4, "blue", "shandong"),
                new MyApple(5, "green", "henan"));
        
        boolean isRed = Iterables.any(al, new Predicate<MyApple>() {
            @Override
            public boolean apply(MyApple input) {
                return input.getColor().equals("yellow");
            }
        });
        System.out.println(isRed);
    }
    
    private List<String> createList() {
        System.out.println("create");
        return Lists.newArrayList("a", "b", "c", "d");
    }
    
    @Test
    public void myt() {
        Predicate<String> visibilityFilter = new Predicate<String>() {
            @Override
            public boolean apply(String visibility) {
                return visibility.equals("aa");
            }
        };
        List<String> list = new ArrayList<String>(Collections2.filter(
                createList(), visibilityFilter));
        
        for (String x : list) {
            System.out.println(x);
        }
    }
    @Test
    public void generateStr(){ 
        String []abc = {"a","b","c"};
        String []abc2 = {"c","b","a"};
        Assert.assertArrayEquals(abc, abc2);
       System.out.println(Strings.repeat("0", 5));
    }
    
    private void change(Set<Car> set){
        Car c2 =   set.iterator().next();  
        c2.setLicensePlate("abc");
    }
    
    @Test
    public void getSet(){
        Set<Car> set = Sets.newHashSet();
        Car c = new Car("licence","manufacturer",9);
        set.add(c);
        change(set);
        System.out.println(set.iterator().next().getLicensePlate());
    }
    
}
