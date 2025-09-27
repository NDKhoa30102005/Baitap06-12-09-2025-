package khoa.vn.Service;

import java.util.List;

import khoa.vn.Entity.User;

public interface UserService {
    User findByUsernameAndPassword(String username, String password);
    List<User> findAll();
    User findById(int id);
    void create(User user);
    void update(User user);
    void delete(int id);
    List<User> search(String keyword);
    User findByUsername(String username);
}


