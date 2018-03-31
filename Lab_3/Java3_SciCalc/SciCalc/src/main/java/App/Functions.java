package App;

public class Functions extends Object{
    private String fullName;
    private String equivalent;

    public Functions(String fullName, String equivalent) {
        this.fullName = fullName;
        this.equivalent = equivalent;
    }

    //Tu może być błąd NO I JEST :D ma być toString :D
    public String getFullName() {
        return fullName;
    }

    public String getEquivalent() {
        return equivalent;
    }
    public String toString(){
        return fullName;
    }
}