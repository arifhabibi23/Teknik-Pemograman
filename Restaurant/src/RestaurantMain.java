import java.util.Scanner;

public class RestaurantMain {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            Restaurant menu = new Restaurant();
            
            menu.tambahMenuMakanan("Pizza", 25000, 20);
            menu.tambahMenuMakanan("Spaghetti", 80000, 20);
            menu.tambahMenuMakanan("Tenderloin Steak", 60000, 30);
            menu.tambahMenuMakanan("Chicken Steak", 45000, 30);
            
            int pilihan;
            
            do {
                menu.tampilMenuMakanan();
                
                System.out.println("\n9. Keluar");
                System.out.print("Pilih nomor menu yang ingin dipesan: ");
                pilihan = input.nextInt();
                
                if (pilihan == 0) {
                    System.out.println("Terima kasih telah memesan!");
                    break;
                }
                
                System.out.print("Masukkan jumlah pesanan: ");
                int jumlah = input.nextInt();
                
                menu.pesanMenu(pilihan, jumlah);
                
                System.out.println();
            } while (true);
        }
    }
}