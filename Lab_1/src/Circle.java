import static java.lang.Math.PI;

public class Circle extends Figure implements Print {

    private double r;
    public static String inputText=Figure.napis+"Circle!\n";
    public Circle(double r) {
        try{
            if(r<=0)
                throw new NumberFormatException("r nie może być mniejsze bądź równe zero!");
            else{
                this.r = r;
            }
        }
        catch (NumberFormatException e){
            System.out.println("Błąd! "+e);
        }
    }

    @Override
    double calculatePerimeter() {
        return (2*PI*r);
    }

    @Override
    double calculateArea() {
        return (PI*r*r);
    }

    @Override
    public void print() {
        System.out.println("Circle specs for: r = "+r);
        System.out.println("Persimeter = "+calculatePerimeter());
        System.out.println("Area = "+calculateArea()+"\n");
    }
}
