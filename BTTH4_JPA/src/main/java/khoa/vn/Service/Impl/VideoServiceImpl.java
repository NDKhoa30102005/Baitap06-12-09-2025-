package khoa.vn.Service.Impl;

import java.util.List;
import khoa.vn.DAO.VideoDao;
import khoa.vn.DAO.Impl.VideoDaoImpl;
import khoa.vn.Entity.Video;
import khoa.vn.Entity.User;
import khoa.vn.Service.VideoService;

public class VideoServiceImpl implements VideoService {

    private VideoDao videoDao = new VideoDaoImpl();

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public Video findById(int id) {
        return videoDao.findById(id);
    }

    @Override
    public void create(Video video) {
        videoDao.insert(video);
    }

    @Override
    public void update(Video video) {
        videoDao.update(video);
    }

    @Override
    public void delete(int id) {
        videoDao.delete(id);
    }

    @Override
    public List<Video> search(String keyword) {
        return videoDao.search(keyword);
    }

    @Override
    public List<Video> findByUser(User user) {
        return videoDao.findByUser(user);
    }
}
