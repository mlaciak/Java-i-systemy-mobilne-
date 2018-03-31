import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*
        Triangle T=new Triangle(20,20,20);
        T.print();

        System.out.println();

        Square S=new Square(10,20);
        S.print();

        System.out.println();

        Circle C=new Circle(10);
        C.print();
  */
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean noExit = true;
        while (noExit) {
            System.out.println("Select figure to calculate! \n1. Triangle\n2. Square\n3. Circle\n4. Exit");
            int select = scanner.nextInt();

            switch (select) {
                case 1:
                    //System.out.println("Input lengths for Triangle!");
                    System.out.println(Triangle.inputText);
                    System.out.print("a = ");
                    double a = scanner.nextDouble();
                    System.out.print("b = ");
                    double b = scanner.nextDouble();
                    System.out.print("c = ");
                    double c = scanner.nextDouble();
                    Triangle T = new Triangle(a, b, c);
                    T.print();
                    break;
                case 2:
                    //System.out.println("Input lengths for Square!");
                    System.out.println(Square.inputText);
                    System.out.print("a = ");
                    a = scanner.nextDouble();
                    System.out.print("b = ");
                    b = scanner.nextDouble();
                    Square S = new Square(a, b);
                    S.print();
                    break;
                case 3:
                    //System.out.println("Input radius for Circle!");
                    System.out.println(Circle.inputText);
                    System.out.print("r = ");
                    double r = scanner.nextDouble();
                    Circle C = new Circle(r);
                    C.print();
                    break;
                case 4:
                    noExit = false;
                    break;
            }

        }
        System.exit(0);
    }
}
