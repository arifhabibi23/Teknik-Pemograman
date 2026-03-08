import java.util.Scanner;

public class DataTypes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            try {
                long n = sc.nextLong();
                System.out.println(n + " can be fitted in:");

                if (n >= Byte.MIN_VALUE && n <= Byte.MAX_VALUE)
                    System.out.println("* byte");

                if (n >= Short.MIN_VALUE && n <= Short.MAX_VALUE)
                    System.out.println("* short");

                if (n >= Integer.MIN_VALUE && n <= Integer.MAX_VALUE)
                    System.out.println("* int");

                System.out.println("* long");

            } catch (Exception e) {
                System.out.println("can't be fitted anywhere.");
                sc.next();
            }
        }
        sc.close();
    }
}
