import java.util.Comparator;

public class MyComparator implements Comparator<Item> {
    public int compare(Item a, Item b){
        int compareResult=a.getAmount() - b.getAmount();
        if(compareResult==0){
            return a.compareTo(b);
        }
        return compareResult;
    }
}