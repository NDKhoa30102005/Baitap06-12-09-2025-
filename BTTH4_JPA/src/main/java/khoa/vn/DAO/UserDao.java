package khoa.vn.DAO;

import java.util.List;
import khoa.vn.Entity.User;

public interface UserDao {

    // Tìm user theo username + password (dùng cho login)
    User findByUsernameAndPassword(String username, String password);

    // Lấy tất cả user
    List<User> findAll();

    // Tìm user theo id
    User findById(int id);

    // Thêm user mới
    void insert(User user);

    // Cập nhật thông tin user
    void update(User user);

    // Xóa user theo id
    void delete(int id);

    // Tìm kiếm user theo username
    List<User> search(String keyword);

    // Tìm user theo username
    User findByUsername(String username);
}
