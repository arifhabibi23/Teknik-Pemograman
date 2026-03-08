/**
 *
 * @author Lenovo
 */
public class TestShape {
    public static void main(String[] args) {

        Shape s = new Shape();
        System.out.println(s);

        Circle c = new Circle(5, "blue", true);
        System.out.println(c);
        System.out.println("Area: " + c.getArea());

        Rectangle r = new Rectangle(4, 6, "red", false);
        System.out.println(r);
        System.out.println("Area: " + r.getArea());

        Square sq = new Square(4, "yellow", true);
        System.out.println(sq);
        System.out.println("Area: " + sq.getArea());
    }
}