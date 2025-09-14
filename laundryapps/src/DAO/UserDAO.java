package DAO;

import java.util.List;
import model.User;

public interface UserDAO {
    public void save(User user);
    public List<User> show();
    public void update(User user);
    public void delete(int id);
}
