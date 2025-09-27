package khoa.vn.Service;

import java.util.List;
import khoa.vn.Entity.Video;
import khoa.vn.Entity.User;

public interface VideoService {
    List<Video> findAll();
    Video findById(int id);
    void create(Video video);
    void update(Video video);
    void delete(int id);
    List<Video> search(String keyword);
    List<Video> findByUser(User user);
}
