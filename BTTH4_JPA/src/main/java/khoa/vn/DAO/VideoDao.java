package khoa.vn.DAO;

import java.util.List;
import khoa.vn.Entity.Video;
import khoa.vn.Entity.User;

public interface VideoDao {
    List<Video> findAll();
    Video findById(int id);
    void insert(Video video);
    void update(Video video);
    void delete(int id);
    List<Video> search(String keyword);
    List<Video> findByUser(User user);
}
