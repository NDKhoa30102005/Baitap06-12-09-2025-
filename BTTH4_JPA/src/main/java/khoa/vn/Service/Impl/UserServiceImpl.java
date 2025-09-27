package khoa.vn.Service.Impl;

import java.util.List;

import khoa.vn.DAO.UserDao;
import khoa.vn.DAO.Impl.UserDaoImpl;
import khoa.vn.Entity.User;
import khoa.vn.Service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void create(User user) {
        // Có thể thêm logic kiểm tra username trùng trước khi insert
        if (userDao.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public List<User> search(String keyword) {
        return userDao.search(keyword);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
   
}
