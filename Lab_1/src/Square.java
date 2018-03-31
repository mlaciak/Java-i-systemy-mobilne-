public class Square extends Figure implements Print {

    private double a,b;
    public static String inputText=Figure.napis+"Square!\n";
    public Square(double a, double b) {
        try{
            if((a==0) || (b==0))
                throw new NumberFormatException("Jedna z podanych liczb jest zerem!");
            else{
                this.a = a;
                this.b = b;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("błąd! "+e);
        }

    }

    @Override
    double calculatePerimeter() {
        return (2*a+2*b);
    }

    @Override
    double calculateArea() {
        return (a*b);
    }

    @Override
    public void print() {
        System.out.println("Square specs for: a = "+a+" b = "+b);
        System.out.println("Persimeter = "+calculatePerimeter());
        System.out.println("Area = "+calculateArea()+"\n");
    }
}
