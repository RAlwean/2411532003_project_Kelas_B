package Praktikum_5;

public class Bus extends Kendaraan implements Tranportasi {

    private int kapasitasPenumpang;
    private String bahanBakar;

    public Bus(String merk, String model, int tahunProduksi, int kapasitasPenumpang, String bahanBakar) {
        super(merk, model, tahunProduksi);
        this.kapasitasPenumpang = kapasitasPenumpang;
        this.bahanBakar = bahanBakar;
    }

    @Override
    public void nyalakanMesin() {
        System.out.println("Nyalakan Mesin: Putar kunci untuk menyalakan");
    }
    public String jenisBahanBakar() {
        return bahanBakar;
    }
    public void infoKonsumsi() {
        System.out.println("Info Konsumsi: Konsumsi bahan bakar tergantung kapasitas mesin");
    }
    public int kapasitasPenumpang() {
        return kapasitasPenumpang;
    }
    public void fiturKhusus() {
        System.out.println("Kapasitas Penumpang: " + kapasitasPenumpang + " penumpang");
        System.out.println("Fitur Bus: Dilengkapi kursi nyaman dan fasilitas hiburan");
        JadwalPerjalanan jadwal = new JadwalPerjalanan("Jakarta – Bandung", "08:00");
        jadwal.tampilkanJadwal();
    }

    // Inner class
    public class JadwalPerjalanan {
        private String rute;
        private String waktuBerangkat;

        public JadwalPerjalanan(String rute, String waktuBerangkat) {
            this.rute = rute;
            this.waktuBerangkat = waktuBerangkat;
        }

        public void tampilkanJadwal() {
            System.out.println("Jadwal Perjalanan: Rute " + rute + ", Waktu Berangkat: " + waktuBerangkat);
        }
    }
}
