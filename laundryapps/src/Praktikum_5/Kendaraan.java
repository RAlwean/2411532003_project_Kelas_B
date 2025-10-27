package Praktikum_5;

public abstract class Kendaraan {
    protected String merk;
    protected String model;
    protected int tahunProduksi;

    public Kendaraan(String merk, String model, int tahunProduksi) {
        this.merk = merk;
        this.model = model;
        this.tahunProduksi = tahunProduksi;
    }

    public Kendaraan(String merk2, String model2, String tahunProduksi2, String bahanBakar) {
		// TODO Auto-generated constructor stub
	}

	public abstract void nyalakanMesin();
    public abstract String jenisBahanBakar();
    public abstract void infoKonsumsi();
    public abstract void fiturKhusus();

    public void tampilkanInfo() {
        System.out.println("Merk: " + merk);
        System.out.println("Model: " + model);
        System.out.println("Tahun Produksi: " + tahunProduksi);
        nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + jenisBahanBakar());
        infoKonsumsi();
        fiturKhusus();
        System.out.println();
    }
}
