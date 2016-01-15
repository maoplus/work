package guava;

public class MyApple {
    private int          weight          = 0;
    private String       color           = "yellow";
    private String       productionPlace = "China";
    private final String address         = "earth";
    
    public String getProductionPlace() {
        return productionPlace;
    }
    
    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public MyApple(int weight, String color, String productionPlace) {
        this.weight = weight;
        this.color = color;
        this.productionPlace = productionPlace;
    }
    
    public MyApple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }
    
    public MyApple(int weight) {
        this.weight = weight;
    }
    
    public MyApple() {
    }
    
    public Integer getWeight() {
        return weight;
    }
    
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String toString() {
        return "Apple{" + "color='" + color + '\'' + ", weight=" + weight + '}';
    }
}