public class Item implements Comparable<Item> {
    private String name;
    private ItemCondition condition;
    private double mass;
    private int amount;


    public Item() {
    }

    public Item(String name, ItemCondition condition, double mass, int amount) {
        this.name = name;
        this.condition = condition;
        this.mass = mass;
        this.amount = amount;
    }
//------------------------------------------------     GETERY I SETERY
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCondition getCondition() {
        return condition;
    }

    public void setCondition(ItemCondition condition) {
        this.condition = condition;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
//-----------------------------------------------------METODY! -------------------------------------
    //jeżeli są takie same zwróci 0, jeżeli klasa wyżej, ale nie takie same to -1, a jak niżej to 1
    @Override
    public int compareTo(Item o) {
        int compareResult=name.compareTo(o.getName());
        return compareResult;
    }

    void print(){
        System.out.println("\nName of Item: "+name);
        System.out.println("Item condition: "+condition);
        System.out.println("Item mass: "+mass);
        System.out.println("Item amount: "+amount+'\n');
    }
}
