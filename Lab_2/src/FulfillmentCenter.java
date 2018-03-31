import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FulfillmentCenter {
    public List<Item> items=new LinkedList<>();
    private String name;
    private double maxCapacity;
    private double currentCapacity;
    private double maxMassOfAllItems;

    //konstruktory -----------------------------------------------------------------------------------
    public FulfillmentCenter() {
        this.name = "Center";
        this.maxCapacity = 100;
        this.currentCapacity=0;
    }

    public FulfillmentCenter(String name, double maxCapacity){
        this.name=name;
        this.maxCapacity=maxCapacity;
        this.currentCapacity=0;
    }
    public FulfillmentCenter(List<Item> items, String name, double maxCapacity, double maxMassOfAllItems) {
        this.items = items;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.maxMassOfAllItems = maxMassOfAllItems;
        this.currentCapacity=0;
    }
    // getery i setery --------------------------------------------------------------------------------
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public double getMaxMassOfAllItems() {
        return maxMassOfAllItems;
    }

    public void setMaxMassOfAllItems(double maxMassOfAllItems) {
        this.maxMassOfAllItems = maxMassOfAllItems;
    }

    //metody-------------------------------------------------------------------
    public void addProduct(Item item){
        System.out.println("\nDodaje produkt o nazwie: "+item.getName());
        if((item.getAmount() + getCurrentCapacity())> getMaxCapacity()) {
            System.err.println("Failet to add item, the max capacity of the magazine is exceeded");
        }
        else{
            int k=searchIfExists(item.getName());
            if(k>=0){
                items.get(k).setAmount(items.get(k).getAmount()+item.getAmount());
            }
            else {
                items.add(item);
                maxMassOfAllItems=maxMassOfAllItems+item.getMass();
                currentCapacity=currentCapacity+item.getAmount();
            }
        }
    }

    public Item search(String name){
        System.out.println("Szuka produktu o nazwie: "+name);
        int placeOfItem=-1;
        Item item=new Item();
        item.setName(name);

        for(int i=0; i<items.size();i++){
            if(compare(items.get(i),item)==0){
                placeOfItem=i;
            }
        }
        try {
            if(placeOfItem==-1) {
                throw new NumberFormatException("Nie ma przedmiotu o takiej nazwie błąd funkcji search w klasie FulfillmentCenter");
            }
        }
        catch (NumberFormatException e){
            System.out.println(e);
        }
        return items.get(placeOfItem);
    }

    public int searchIfExists(String name){
        System.out.println("\nSprawdza, czy istnieje produkt o nazwie: "+name);
        int placeOfItem=-1;
        Item item=new Item();
        item.setName(name);

        for(int i=0; i<items.size();i++){
            if(compare(items.get(i),item)==0){
                placeOfItem=i;
            }
        }
        return placeOfItem;
    }

    public int compare(Item a, Item b){
        System.out.println("Porównywanie 2 produktów z wykorzystaniem compareTo");
        if(a==null || b==null){
            return -1;
        }
        else {
            return a.compareTo(b);
        }
    }

    public void getProduct(Item item){
        System.out.println("\nPobieranie produktu o nazwie: "+item.getName()+" i ilości: "+item.getAmount());
        int k=searchIfExists(item.getName());
        try {
            if(k==-1){
                throw new NumberFormatException("Nie ma przedmiotu o takiej nazwie błąd funkcji getProduct w klasie FulfillmentCenter");
            }
            else {
                if((items.get(k).getAmount()-item.getAmount())<0){
                    throw new NumberFormatException("Nie można pobrać takiej ilości, ponieważ nie ma tyle w magazynie!");
                }
                else if((items.get(k).getAmount()-item.getAmount())==0){
                    items.remove(k);
                    System.out.println("Pobrano taką ilość danego, przedmiotu, iż nie znajduje się on więcej na magazynie");
                }
                else {
                    items.get(k).setAmount(items.get(k).getAmount()-item.getAmount());
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println(e);
            //System.err.println(e);
        }
    }

    public void removeProduct(Item item){
        System.out.println("\nUsuwanie produktu, o nazwie: "+item.getName());
        int k=searchIfExists(item.getName());
        if(k==-1){
            System.out.println("PRODUKT NIE ISTNIEJE!");
        }
        else {
            items.remove(k);
            System.out.println("Produkt usunięty");
        }
    }

    public void searchPartial(String name){
        System.out.println("\nWypisanie przedmiotów, których nazwa częściowo pokrywa się z: "+name);
        for(int i=0; i<items.size();i++){
            if(items.get(i).getName().contains(name)){
                items.get(i).print();
            }
        }
    }

    public int countByCondition(ItemCondition itemCondition){
        System.out.println("\nWypisanie ilości przedmiotów, których stan = " +itemCondition);
        int licznik=0;
        for(int i=0; i<items.size();i++){
            if(items.get(i).getCondition()==itemCondition){
                licznik++;
            }
        }
        return licznik;
    }

    public void summary(){
        System.out.println("\nWypisanie wszystkich przedmiotów");
        if(items.size()==0){
            System.out.println("Nie ma produktów w magazynie!");
        }
        else {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).print();
            }
        }
    }

    public void sortByName(){
        System.out.println("\nSortowanie po nazwie");
        Collections.sort(items);

        for(int i=0; i<items.size();i++){
                items.get(i).print();
        }
        //System.out.println(items);
    }

    public void sortByAmount(){
        System.out.println("\nSortowanie po ilości");
        Collections.sort(items, new MyComparator());
        //System.out.println(items);
        for(int i=0; i<items.size();i++){
            items.get(i).print();
        }
    }

    public Item max() {
        System.out.println("\nZwraca produkt, którego jest najwięcej");
        return Collections.max(items,new MyComparator());
    }
}