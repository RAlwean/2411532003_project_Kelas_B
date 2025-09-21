package DAO;

import java.util.List;
import model.Layanan;

public interface LayananDAO {
    public void save(Layanan layanan);
    public List<Layanan> show();
    public void update(Layanan layanan);
    public void delete(int id);
}
