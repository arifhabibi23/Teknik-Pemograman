import java.util.Scanner;

public class Strings
{
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);

      System.out.print("How many strings do you want to sort? ");
      int n = scan.nextInt();
      scan.nextLine();

      String[] words = new String[n];

      System.out.println("Enter the strings:");

      for (int i = 0; i < n; i++)
         words[i] = scan.nextLine();

      Sorting.insertionSort(words);

      System.out.println("\nStrings in sorted order:");

      for (int i = 0; i < words.length; i++)
         System.out.println(words[i]);
   }
}