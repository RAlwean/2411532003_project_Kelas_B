package DAO;

import java.util.List;
import model.Pelanggan;

public interface PelangganDAO {
    public void save(Pelanggan pelanggan);
    public List<Pelanggan> show();
    public void update(Pelanggan pelanggan);
    public void delete(int id);
}
