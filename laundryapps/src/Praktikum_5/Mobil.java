package Praktikum_5;

public final class Mobil extends Kendaraan implements BahanBakar {

    private String bahanBakar;

    public Mobil(String merk, String model, int tahunProduksi, String bahanBakar) {
        super(merk, model, tahunProduksi);
        this.bahanBakar = bahanBakar;
    }
    public void nyalakanMesin() {
        System.out.println("Nyalakan Mesin: Tekan tombol start");
    }
    public String jenisBahanBakar() {
        return bahanBakar;
    }
    public void infoKonsumsi() {
        System.out.println("Info Konsumsi: Konsumsi bahan bakar tergantung kapasitas mesin");
    }
    public void fiturKhusus() {
        System.out.println("Fitur Mobil: Memiliki AC dan audio premium");
    }
}
