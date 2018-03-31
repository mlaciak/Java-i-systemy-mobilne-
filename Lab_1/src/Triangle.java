import static java.lang.StrictMath.sqrt;

public class Triangle extends Figure implements Print {

    private double a,b,c;
    public static String inputText=Figure.napis+"Triangle!\n";
    public Triangle(double a, double b, double c) {
        try {
            if (a <= 0 || b <= 0 || c <= 0)
                throw new NumberFormatException();
            else {
                this.a = a;
                this.b = b;
                this.c = c;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("błąd!"+e);
        }
    }

    //obliczanie obwodu:
    @Override
    double calculatePerimeter() {
        return (a+b+c);
    }

    /*do obliczenia pola używam wzoru herona p=(1/2 obwodu)
    * pole = pierwiastek [ p(p-a)(p-b)(p-c)]
    */
    @Override
    double calculateArea() {
        double p = calculatePerimeter();
        p=p/2;
        double Area=0;
        try{
            if((p-c)==0)
                throw new ArithmeticException("Odcinki o podanej długości nie tworzą trójkąta!");
            else
            {
                Area=sqrt((p*(p-a)*(p-b)*(p-c)));
            }
        }
        catch (ArithmeticException e) {
            System.out.println("błąd! " + e);
        }
        return (Area);
    }

    @Override
    public void print() {
        System.out.println("Triangle specs for: a = "+a+" b = "+b+" c = "+c);
        System.out.println("Persimeter = "+calculatePerimeter());
        System.out.println("Area = "+calculateArea()+"\n");
    }
}
