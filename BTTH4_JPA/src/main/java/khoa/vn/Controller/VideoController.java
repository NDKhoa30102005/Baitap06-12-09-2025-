package khoa.vn.Controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import khoa.vn.Entity.User;
import khoa.vn.Entity.Video;
import khoa.vn.Service.VideoService;
import khoa.vn.Service.Impl.VideoServiceImpl;

@WebServlet(urlPatterns = {"/admin-video", "/admin-video/create", "/admin-video/edit", "/admin-video/update", "/admin-video/delete"})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        // Chỉ admin hoặc user mới được
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (url.contains("create")) {
            req.getRequestDispatcher("/views/video/add.jsp").forward(req, resp);
            return;
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);
            req.setAttribute("video", video);
            req.getRequestDispatcher("/views/video/edit.jsp").forward(req, resp);
            return;
        } else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            videoService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin-video");
            return;
        }

        String keyword = req.getParameter("keyword");
        List<Video> videos;
        if (currentUser.getRoleid() == 1) {
            videos = (keyword == null || keyword.isEmpty()) ? videoService.findAll() : videoService.search(keyword);
        } else {
            videos = (keyword == null || keyword.isEmpty()) ? videoService.findByUser(currentUser) : videoService.findByUser(currentUser).stream()
                        .filter(v -> v.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                        .toList();
        }
        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/views/video/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String url = req.getRequestURL().toString();
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String urlVideo = req.getParameter("url");

        if (url.contains("create")) {
            Video video = new Video(title, description, urlVideo, currentUser);
            videoService.create(video);
            resp.sendRedirect(req.getContextPath() + "/admin-video");
        } else if (url.contains("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);
            video.setTitle(title);
            video.setDescription(description);
            video.setUrl(urlVideo);
            videoService.update(video);
            resp.sendRedirect(req.getContextPath() + "/admin-video");
        }
    }
}
