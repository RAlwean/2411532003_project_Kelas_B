package model;

import java.util.Date;

public class Order {
    private int id;
    private int idCostumer;
    private int idService;
    private int idUser;
    private double total;
    private Date tanggal;
    private Date tanggalSelesai;
    private String status;
    private String statusPembayaran;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdCostumer() {
        return idCostumer;
    }
    public void setIdCostumer(int idCostumer) {
        this.idCostumer = idCostumer;
    }

    public int getIdService() {
        return idService;
    }
    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }
    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }
    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }
        public static Order createSampleOrder(Costumer c, Service s, int idUser) {
            Order o = new Order();
            o.setId(1001);
            o.setIdCostumer(c.getId());
            o.setIdService(s.getId());
            o.setIdUser(idUser);
            o.setTotal(s.getHarga());
            o.setTanggal(new Date());
            o.setStatus("Diproses");
            o.setStatusPembayaran("Belum Lunas");
            return o;
        }
    }

