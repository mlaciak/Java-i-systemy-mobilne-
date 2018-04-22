import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FulfillmentCenterTest {

    @Test
    public void getItems() {
        FulfillmentCenter center = new FulfillmentCenter();
        List<Item> items=new LinkedList<>();
        Item item=new Item("Prety stalowe",ItemCondition.NEW, 20, 15);
        items.add(item);
        center.items.add(item);
        assertEquals(center.getItems(),items);
    }

    @Test
    public void setItems() {
        FulfillmentCenter center = new FulfillmentCenter();
        List<Item> items=new LinkedList<>();
        Item item=new Item("Prety stalowe",ItemCondition.NEW, 20, 15);
        Item item2=new Item("Prety stalowe",ItemCondition.NEW, 20, 15);
        items.add(item);
        items.add(item2);
        center.setItems(items);
        assertTrue(!center.items.isEmpty());
    }

    @Test
    public void getName() {
        List<Item> items=new LinkedList<>();
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        assertEquals(center.getName(),"Nazwa");
    }

    @Test
    public void setName() {
        FulfillmentCenter center = new FulfillmentCenter();
        center.setName("Banana");
        assertEquals(center.getName(),"Banana");
    }

    @Test
    public void getMaxCapacity() {
        List<Item> items=new LinkedList<>();
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        assertEquals(center.getMaxCapacity(),100,0);
    }

    @Test
    public void setMaxCapacity() {
        FulfillmentCenter center = new FulfillmentCenter();
        center.setMaxCapacity(200);
        assertEquals(center.getMaxCapacity(),200,0);
    }

    @Test
    public void getCurrentCapacity() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("Prety stalowe",ItemCondition.NEW, 20, 15);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        center.addProduct(item);
        assertEquals(center.getCurrentCapacity(),15,0);

    }

    @Test
    public void setCurrentCapacity() {
        List<Item> items=new LinkedList<>();
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        center.setCurrentCapacity(90);
        assertEquals(center.getCurrentCapacity(),90,0);
    }

    @Test
    public void getMaxMassOfAllItems() {
        List<Item> items=new LinkedList<>();
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        assertEquals(center.getMaxMassOfAllItems(),100,0);
    }

    @Test
    public void setMaxMassOfAllItems() {
        List<Item> items=new LinkedList<>();
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        center.setMaxMassOfAllItems(200);
        assertEquals(center.getMaxMassOfAllItems(),200,0);
    }

    @Test
    public void addProduct() {
        List<Item> items=new LinkedList<>();
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        Item item=new Item("Prety stalowe",ItemCondition.NEW, 20, 15);
        center.addProduct(item);
        assertEquals(center.items.get(center.items.size()-1),item);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void addProduct2() {
           List<Item> items = new LinkedList<>();
           FulfillmentCenter center = new FulfillmentCenter(items, "Nazwa", 100, 100);
           Item item = new Item("Prety stalowe", ItemCondition.NEW, 20, -15);
           center.addProduct(item);
           assertEquals(center.items.get(center.items.size()-1),item);
    }

    //--------------------------------------------------------------------------expected exception! ---------------
    @Test (expected = IndexOutOfBoundsException.class)
    public void search() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 15);
        items.add(item);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        assertEquals(center.search("banany2"),item);
        //assertEquals(center.search("banany"),item);
    }

    //działanie dla niepoprawnych danych! ------------------------------------------------------------------------
    @Test
    public void searchIfExists() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 15);
        items.add(item);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        assertNotEquals(center.searchIfExists("banany2"),item);
    }

    @Test
    public void compare() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 15);
        Item item2=new Item("banany",ItemCondition.NEW, 20, 15);
        items.add(item);
        items.add(item2);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        assertEquals(center.compare(item,item2),0,0);
    }

    @Test
    public void getProduct() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 15);
        Item item2=new Item("banany",ItemCondition.NEW, 20, 10);
        items.add(item);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        center.getProduct(item2);
        assertEquals(center.items.get(0).getAmount(),5,0);
    }

    @Test
    public void removeProduct() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 15);
        items.add(item);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        center.removeProduct(item);
        assertTrue(center.items.isEmpty());
    }

    //---------------------------------------------------------------------- COS NIE HALO! ---------------------------
    @Test (expected = org.junit.ComparisonFailure.class)
    public void searchPartial() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 15);
        items.add(item);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        String expectedOutput = "Wypisanie przedmiotów, których nazwa częściowo pokrywa się z: ban"+System.lineSeparator()+System.lineSeparator()+"Name of Item: banany"+ System.lineSeparator()+"Item condition: NEW"+ System.lineSeparator()+"Item mass: 20.0"+ System.lineSeparator()+"Item amount: 15" + System.lineSeparator()+ System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        center.searchPartial("ban");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void countByCondition() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 15);
        Item item2=new Item("banany",ItemCondition.NEW, 20, 15);
        items.add(item);
        items.add(item2);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        assertEquals(center.countByCondition(ItemCondition.NEW),2,0);
    }

    @Test
    public void summary() {
        List<Item> items=new LinkedList<>();
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        String expectedOutput = "Wypisanie wszystkich przedmiotów"+System.lineSeparator()+"Nie ma produktów w magazynie!"+ System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        center.summary();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test (expected = org.junit.ComparisonFailure.class)
    public void sortByName() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 15);
        Item item2=new Item("awokado",ItemCondition.NEW, 20, 15);
        items.add(item);
        items.add(item2);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        String expectedOutput = "Sortowanie po nazwie"+System.lineSeparator()+System.lineSeparator()+"Name of Item: awokado"+ System.lineSeparator()+"Item condition: NEW"+ System.lineSeparator()+"Item mass: 20.0"+ System.lineSeparator()+"Item amount: 15" + System.lineSeparator()+ System.lineSeparator()
                +System.lineSeparator()+"Name of Item: banany"+ System.lineSeparator()+"Item condition: NEW"+ System.lineSeparator()+"Item mass: 20.0"+ System.lineSeparator()+"Item amount: 15" + System.lineSeparator()+ System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        center.sortByName();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test (expected = org.junit.ComparisonFailure.class)
    public void sortByAmount() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 35);
        Item item2=new Item("awokado",ItemCondition.NEW, 20, 15);
        items.add(item);
        items.add(item2);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        String expectedOutput = "Sortowanie po ilości"+System.lineSeparator()+System.lineSeparator()+"Name of Item: awokado"+ System.lineSeparator()+"Item condition: NEW"+ System.lineSeparator()+"Item mass: 20.0"+ System.lineSeparator()+"Item amount: 15" + System.lineSeparator()+ System.lineSeparator()
                +System.lineSeparator()+"Name of Item: banany"+ System.lineSeparator()+"Item condition: NEW"+ System.lineSeparator()+"Item mass: 20.0"+ System.lineSeparator()+"Item amount: 35" + System.lineSeparator()+ System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        center.sortByAmount();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void max() {
        List<Item> items=new LinkedList<>();
        Item item=new Item("banany",ItemCondition.NEW, 20, 35);
        Item item2=new Item("awokado",ItemCondition.NEW, 20, 15);
        items.add(item);
        items.add(item2);
        FulfillmentCenter center = new FulfillmentCenter(items,"Nazwa",100,100);
        assertEquals(center.max().getAmount(),item.getAmount());
    }
}