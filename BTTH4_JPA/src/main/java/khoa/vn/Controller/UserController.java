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
import khoa.vn.Service.UserService;
import khoa.vn.Service.Impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin-user", "/admin-user/create", "/admin-user/edit", "/admin-user/update", "/admin-user/delete"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();

        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        // Kiểm tra role admin
        if (currentUser == null || currentUser.getRoleid() != 1) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (url.contains("create")) {
            req.getRequestDispatcher("/views/user/add.jsp").forward(req, resp);
            return;
        } else if (url.contains("edit")) {
            String id = req.getParameter("id");
            User user = userService.findById(Integer.parseInt(id));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/user/edit.jsp").forward(req, resp);
            return;
        } else if (url.contains("delete")) {
            String id = req.getParameter("id");
            userService.delete(Integer.parseInt(id));
            resp.sendRedirect(req.getContextPath() + "/admin-user");
            return;
        }

        // Danh sách user + tìm kiếm
        String keyword = req.getParameter("keyword");
        List<User> users = (keyword == null || keyword.isEmpty()) ? userService.findAll() : userService.search(keyword);
        req.setAttribute("users", users);
        req.getRequestDispatcher("/views/user/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String url = req.getRequestURL().toString();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int roleid = Integer.parseInt(req.getParameter("roleid"));

        if (url.contains("create")) {
            User user = new User(username, password, roleid);
            try {
                userService.create(user);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/views/user/add.jsp").forward(req, resp);
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/admin-user");
        } else if (url.contains("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            User user = userService.findById(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setRoleid(roleid);
            userService.update(user);
            resp.sendRedirect(req.getContextPath() + "/admin-user");
        }
    }
}
