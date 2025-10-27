package Praktikum_5;

public class Pesawat extends Kendaraan implements BahanBakar {

    private String bahanBakar;

    public Pesawat(String merk, String model, int tahunProduksi, String bahanBakar) {
        super(merk, model, tahunProduksi);
        this.bahanBakar = bahanBakar;
    }
    public void nyalakanMesin() {
        System.out.println("Nyalakan Mesin: Bersiap lepas landas");
    }
    public String jenisBahanBakar() {
        return bahanBakar;
    }
    public void infoKonsumsi() {
    }
    public void fiturKhusus() {
    }
}
