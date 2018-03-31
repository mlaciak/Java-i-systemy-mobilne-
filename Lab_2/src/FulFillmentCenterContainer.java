import java.util.*;

public class FulFillmentCenterContainer {
    private Map<String,FulfillmentCenter> ffcmap=new TreeMap<>();

    //---------------------------------------------------- GETERY SETERY! ----------------------
    public Map<String, FulfillmentCenter> getFfcmap() {
        return ffcmap;
    }

    public void setFfcmap(Map<String, FulfillmentCenter> ffcmap) {
        this.ffcmap = ffcmap;
    }

    //-----------------------------------------------------METODY -------------------------------
    public void addCenter(String name, double maxCapacity){
        System.out.println("\nDodanie nowego magazynu o nazwie "+name+" I pojemności "+maxCapacity);
        FulfillmentCenter m=new FulfillmentCenter(name,maxCapacity);
        ffcmap.put(name, m);
    }

    public void addCenter(FulfillmentCenter FFC){
        System.out.println("\nDodanie cetrum o nazwie "+FFC.getName());
        ffcmap.put(FFC.getName(),FFC);
    }

    public void removeCenter(String name){
        System.out.println("\nUsunięcie centrum o nazwie "+name);
        ffcmap.remove(name);
    }

    public List<FulfillmentCenter> findEmpty(){
        System.out.println("\nSzukanie pustego centrum");
        List<FulfillmentCenter> Ffc=new LinkedList<>();
        Set<Map.Entry<String,FulfillmentCenter>> entrySet = ffcmap.entrySet();
        for(Map.Entry<String,FulfillmentCenter> entry:entrySet)
        {
            if ( entry.getValue().getCurrentCapacity() == 0 )
            {
                Ffc.add(entry.getValue());
            }
        }
        return Ffc;
    }

    public void summary(){
        System.out.println("\nWypisanie wszystkich informacji z konteneru");
        List<FulfillmentCenter> Ffc=new LinkedList<>();
        Set<Map.Entry<String,FulfillmentCenter>> entrySet = ffcmap.entrySet();
        for(Map.Entry<String,FulfillmentCenter> entry:entrySet)
        {
            System.out.println("Nazwa magazynku: "+entry.getValue().getName());
            double k=entry.getValue().getCurrentCapacity();
            double z=entry.getValue().getMaxCapacity();
            System.out.println("Procentowe zapełnienie magazynu: "+(k/z)*100+"%");
        }
    }
}