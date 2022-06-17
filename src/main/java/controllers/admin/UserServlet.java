package controllers.admin;

import DAO.UserDao;
import JPAUtils.EncryptUtil;
import JPAUtils.FileUtil;
import entitys.UsersEntity;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet({"/store", "/edit", "/update", "/delete", "/User"})
public class UserServlet extends HttpServlet {
    private UserDao dao;

    public UserServlet() {
        this.dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("User")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UsersEntity> list = this.dao.all();
        request.setAttribute("ds", list);
        request.setAttribute("view", "/views/admin/user/create.jsp");
        request.setAttribute("view1", "/views/admin/user/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }


    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            UsersEntity before = this.dao.findByID(id);
            UsersEntity entity = new UsersEntity();
            BeanUtils.populate(entity, request.getParameterMap());
            File file = FileUtil.saveFileUpload("avatar", request.getPart("avatar"));
            if (file.getName().equals("avatar")) {
                entity.setAvatar(before.getAvatar());
            } else {
                entity.setAvatar(file.getName());
            }
            entity.setPasswordUser(before.getPasswordUser());
            entity.setStatus(before.getStatus());
            this.dao.update(entity);
            session.setAttribute("message","Cập Nhật Thành Công");
            response.sendRedirect("/User");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error","Cập Nhật Thất Bại");
            response.sendRedirect("/User");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            UsersEntity entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus((byte) 0);
            this.dao.update(entity);
            session.setAttribute("message","Xóa Thành Công");
            response.sendRedirect("/User");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error","Xóa Thất Bại");
            response.sendRedirect("/User");
        }
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsersEntity entity = new UsersEntity();
        List<UsersEntity> list = new ArrayList<>();
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            System.out.println(entity.getBirthday());
            File file = FileUtil.saveFileUpload("avatar", request.getPart("avatar"));
            if (file.getName().equals("avatar")) {
                entity.setAvatar("undraw_profile.svg");
            } else {
                entity.setAvatar(file.getName());
            }
            String encrypted = EncryptUtil.encrypt(request.getParameter("passwordUser"));
            entity.setStatus((byte) 1);
            entity.setPasswordUser(encrypted);
            this.dao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            list.add(entity);
            request.setAttribute("ds", list);
            List<UsersEntity> all = this.dao.all();
            request.setAttribute("ds", all);
            response.sendRedirect("/User");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Thêm Mới Thất Bại");
        }

    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        UsersEntity entity = this.dao.findByID(id);
        request.setAttribute("user", entity);
        List<UsersEntity> list = this.dao.all();
        request.setAttribute("ds", list);
        request.setAttribute("view", "/views/admin/user/edit.jsp");
        request.setAttribute("view1", "/views/admin/user/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }
}
