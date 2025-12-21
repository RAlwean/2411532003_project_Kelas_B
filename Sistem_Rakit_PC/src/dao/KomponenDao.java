package dao;

import java.util.List;
import model.Komponen;

public interface KomponenDao {
    public void save(String nama, String tipe, double harga);
    public void delete(int id);
    public List<Komponen> show();
}