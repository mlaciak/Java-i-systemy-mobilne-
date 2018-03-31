import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        //ITEM:------------------------------------------------------------------------------
        Item item=new Item("Prety stalowe",ItemCondition.NEW, 20, 15);
        Item item2=new Item("Banany",ItemCondition.REFURBISHED, 10, 10);
        Item item4=new Item("Cuksy",ItemCondition.NEW,5,30);
        item.print();
        item2.print();

        System.out.println("------------ITEM DODAWANIE I DRUKOWANIE POWYŻEJ----------------------------");

        //FulFillmentCenter:-----------------------------------------------------------------
        FulfillmentCenter Centrum1=new FulfillmentCenter();
        FulfillmentCenter Centrum2=new FulfillmentCenter("Magazyn", 200);

        Centrum1.addProduct(item);
        Centrum2.addProduct(item2);
        Centrum2.addProduct(item4);
        Item z =Centrum2.search("Banany");
        z.print();

        System.out.println("-------------DODANIE 3 PRODUKTÓW DO CENTRUM 1 I 2 I SZUKANIE BANANY-----------");


        System.out.println("\nWyświetlenie wyniku compareTo "+item.compareTo(item2));

        Centrum2.summary();
        Item item3=new Item("Banany",ItemCondition.REFURBISHED, 5, 5);
        Centrum2.getProduct(item3);
        Centrum2.summary();

        System.out.println("---------------WYSWIETLENIE SUMMARY, POBRANIE ITEMU I ZNOW SUMMARY----------");

        Centrum2.removeProduct(item3);
        Centrum2.summary();

        Centrum1.searchPartial("Pre");

        System.out.println("----------USUNIECIE PRODUKTU, SUMMARY, SZUKANIE PO CZESCI STRINGA-----------------");

        System.out.println("Ilość produków NEW: "+Centrum2.countByCondition(ItemCondition.NEW));


        Centrum2.addProduct(item);
        Centrum2.addProduct(item2);

        System.out.println("--------------ILE PRODUKTOW new I DODANIE 2 ITEMOW----------------------------");

        Centrum2.sortByName();

        Centrum2.sortByAmount();

        System.out.println("------------SORTOWANIE PO NAZWIE I SORTOWANIE PO ILOSCI----------------");


        System.out.println(Centrum2.countByCondition(ItemCondition.NEW));
        System.out.println("-----------ZLICZENIE ILE W CENTRUM 2 Z CONDITION NEW------------------------");

        Item k=Centrum2.max();
        k.print();

        System.out.println("--------------NAJWIESZKA ILOSC PRZEDMIOTU Z CENTRUM 2----------------------------");



        //----------------------------------------------------FulFilmentCenterContainer
        FulFillmentCenterContainer Container=new FulFillmentCenterContainer();

        FulfillmentCenter CentrumPuste=new FulfillmentCenter("Puste",500);
        Container.addCenter("Bananowe",300);
        Container.addCenter(Centrum1);
        Container.addCenter(Centrum2);

        System.out.println("--------UTWORZENIE NOWEGO CENTRUM I DODANIE DO CONTENERA I DODANIE 2 POPRZDNICH-----------------");

        List<FulfillmentCenter> emptyList=Container.findEmpty();
        for(int i=0; i<emptyList.size();i++){
            System.out.println(emptyList.get(i).getName());
        }
        System.out.println("--------WYPISUJE PUSTE MAGAZYNY Z KONTENERU-----------------");

        Container.summary();

        System.out.println("--------PODSUMOWANIE KONTENERU Z PROCENTOWYM WYPEŁNIENIEM-----------------");

    }
}