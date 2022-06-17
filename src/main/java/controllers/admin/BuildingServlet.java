package controllers.admin;

import DAO.UserDao;
import Dao.buildingDao;
import Dao.floorDao;
import entitys.BuildingEntity;
import entitys.FloorEntity;
import entitys.UsersEntity;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet({"/Building", "/storeBuilding", "/updateBuilding", "/deleteBuilding", "/editBuilding"})
public class BuildingServlet extends HttpServlet {
    private buildingDao dao;
    private UserDao userDao;
    private floorDao foorDao;

    public BuildingServlet() {
        this.dao = new buildingDao();
        this.userDao = new UserDao();
        this.foorDao = new floorDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("Building")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("updateBuilding")) {
            this.update(request, response);
        } else if (uri.contains("storeBuilding")) {
            this.store(request, response);
        } else if (uri.contains("editBuilding")) {
            this.edit(request, response);
        } else if (uri.contains("deleteBuilding")) {
            this.delete(request, response);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BuildingEntity> list = this.dao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/building/create.jsp");
        request.setAttribute("view1", "/views/admin/building/table.jsp");
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            BuildingEntity entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            this.dao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/Building");
        } catch (Exception e) {
            session.setAttribute("error", "Cập Nhật Thất Bại");
            response.sendRedirect("/Building");
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            BuildingEntity entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(0);
            this.dao.update(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Building");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Building");
            e.printStackTrace();
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        BuildingEntity entity = this.dao.findByID(id);
        request.setAttribute("building", entity);
        List<BuildingEntity> list = this.dao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/building/edit.jsp");
        request.setAttribute("view1", "/views/admin/building/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BuildingEntity entity = new BuildingEntity();
        List<BuildingEntity> list = new ArrayList<>();
        List<BuildingEntity> all = this.dao.all();
//        String s = request.getParameter("quantity");
//        int quantity=Integer.parseInt(s);
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            UsersEntity user = (UsersEntity) session.getAttribute("user");
            entity.setIdUser(user);
            entity.setStatus(1);
            entity.setDateCreate(new Date());
            this.dao.create(entity);
//            System.out.println(all.size());
//            List<FloorEntity> floorEntityList = this.foorDao.findByIDBuilding(all.size());
//                for (int i = 1; i <= quantity; i++) {
//                    FloorEntity floorEntity = new FloorEntity();
//                    floorEntity.setNameFloor("Tang" + i);
//                    floorEntity.setIdBuilding(entity);
//                    System.out.println(quantity);
//                    floorEntity.setStatus(true);
//                    floorEntity.setDateCreate(new Date());
//                    this.foorDao.create(floorEntity);
//                    floorEntityList.add(floorEntity);
//                    System.out.println(floorEntity);
//                    System.out.println(floorEntityList);
////                }
//            }
            session.setAttribute("message", "Thêm Mới Thành Công");
            list.add(entity);
            request.setAttribute("list", list);
            request.setAttribute("list", all);
            response.sendRedirect("/Building");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/Building");
            e.printStackTrace();
        }
    }
}
