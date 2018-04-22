import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class FulFillmentCenterContainerTest {

    @Test
    public void getFfcmap() {
        FulFillmentCenterContainer Container=new FulFillmentCenterContainer();
        FulfillmentCenter center2=new FulfillmentCenter("niecentrum",30);
        FulfillmentCenter center=new FulfillmentCenter("centrum",30);
        Container.addCenter(center);
        Container.addCenter(center2);
        assertTrue(!Container.getFfcmap().isEmpty());
    }

    @Test
    public void setFfcmap() {
        Map<String,FulfillmentCenter> ffcmap2=new TreeMap<>();
        FulfillmentCenter m=new FulfillmentCenter("banana",100);
        ffcmap2.put("banana", m);
        FulFillmentCenterContainer Container=new FulFillmentCenterContainer();
        Container.setFfcmap(ffcmap2);
        assertEquals(Container.getFfcmap().size(),ffcmap2.size());
    }

    @Test
    public void addCenter() {
        FulFillmentCenterContainer Container=new FulFillmentCenterContainer();
        Container.addCenter("centrum",29);
        assertEquals(Container.getFfcmap().get("centrum").getName(),"centrum");
    }

    @Test
    public void addCenter1() {
        FulFillmentCenterContainer Container=new FulFillmentCenterContainer();
        FulfillmentCenter center=new FulfillmentCenter("centrum",30);
        Container.addCenter(center);
        assertEquals(Container.getFfcmap().get("centrum").getName(),"centrum");
    }

    @Test
    public void removeCenter() {
        FulFillmentCenterContainer Container=new FulFillmentCenterContainer();
        FulfillmentCenter center2=new FulfillmentCenter("niecentrum",30);
        Container.addCenter(center2);
        Container.removeCenter("niecentrum");
        assertTrue(Container.getFfcmap().isEmpty());
    }

    @Test
    public void findEmpty() {
        FulFillmentCenterContainer Container=new FulFillmentCenterContainer();
        FulfillmentCenter center2=new FulfillmentCenter("niecentrum",30);
        FulfillmentCenter center=new FulfillmentCenter("centrum",30);
        Container.addCenter(center);
        Container.addCenter(center2);
        List<FulfillmentCenter> emptyList=Container.findEmpty();
        assertEquals(emptyList.size(),2,0);
    }

    @Test
    public void summary() {
        FulFillmentCenterContainer Container=new FulFillmentCenterContainer();
        String expectedOutput = "Wypisanie wszystkich informacji z konteneru"+System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Container.summary();
        assertEquals(expectedOutput, outContent.toString());
    }
}