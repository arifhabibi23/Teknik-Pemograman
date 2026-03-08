public class Restaurant {

    private final String[] nama_makanan;
    private final double[] harga_makanan;
    private final int[] stok;
    private int id = 0;

    public Restaurant() {
        nama_makanan = new String[10];
        harga_makanan = new double[10];
        stok = new int[10];
    }

    public void tambahMenuMakanan(String nama, double harga, int stok) {
        if (id < nama_makanan.length) {
            if (stok < 0) {
                System.out.println("Stok tidak boleh negatif!");
                return;
            }

            this.nama_makanan[id] = nama;
            this.harga_makanan[id] = harga;
            this.stok[id] = stok;
            id++;
        } else {
            System.out.println("Menu penuh!");
        }
    }

    public void tampilMenuMakanan() {
        System.out.println("\n=== DAFTAR MENU ===");
        for (int i = 0; i < id; i++) {
            System.out.println(
                    (i+1) + ". " + nama_makanan[i] +
                    " | Stok: " + stok[i] +
                    " | Rp. " + harga_makanan[i]
            );
        }
    }

    public int getStok(int index) {
        if (index >= 0 && index < id) {
            return stok[index];
        }
        return -1;
    }

    public void setStok(int index, int stokBaru) {
        if (index < 0 || index >= id) {
            System.out.println("Menu tidak ditemukan!");
            return;
        }

        if (stokBaru < 0) {
            System.out.println("Stok tidak boleh negatif!");
        } else {
            stok[index] = stokBaru;
            System.out.println("Stok berhasil diupdate.");
        }
    }

    public void pesanMenu(int nomorMenu, int jumlahPesan) {

        int index = nomorMenu - 1;
        
        if (index < 0 || index >= id) {
            System.out.println("Menu tidak ditemukan!");
            return;
        }

        if (jumlahPesan <= 0) {
            System.out.println("Jumlah pesanan tidak valid!");
            return;
        }

        if (stok[index] >= jumlahPesan) {
            stok[index] -= jumlahPesan;
            double total = harga_makanan[index] * jumlahPesan;

            System.out.println("\nPesanan berhasil!");
            System.out.println("Menu  : " + nama_makanan[index]);
            System.out.println("Jumlah: " + jumlahPesan);
            System.out.println("Total : Rp. " + total);
        } else {
            System.out.println("Pesanan ditolak! Stok tidak mencukupi.");
        }
    }
}