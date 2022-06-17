package controllers.admin;
import Dao.buildingDao;
import Dao.floorDao;
import entitys.BuildingEntity;
import entitys.FloorEntity;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@MultipartConfig
@WebServlet({"/Floor", "/storeFloor", "/updateFloor", "/deleteFloor", "/editFloor"})
public class FloorServlet extends HttpServlet {
    private buildingDao buildingDao;
    private floorDao dao;

    public FloorServlet() {
        this.dao = new floorDao();
        this.buildingDao = new buildingDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("Floor")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("updateFloor")) {
            this.update(request, response);
        } else if (uri.contains("storeFloor")) {
            this.store(request, response);
        } else if (uri.contains("editFloor")) {
            this.edit(request, response);
        } else if (uri.contains("deleteFloor")) {
            this.delete(request, response);
        }

    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BuildingEntity> dsbuilding = this.buildingDao.all();
        request.setAttribute("dsbuilding", dsbuilding);
        List<FloorEntity> list = this.dao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/floor/create.jsp");
        request.setAttribute("view1", "/views/admin/floor/table.jsp");
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        int idCate = Integer.parseInt(request.getParameter("building_id"));
        try {
            BuildingEntity building = this.buildingDao.findByID(idCate);
            FloorEntity entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setIdBuilding(building);
            this.dao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/Floor");
        } catch (Exception e) {
            response.sendRedirect("/Floor");
            session.setAttribute("error", "Cập Nhật Thất Bại");
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            FloorEntity entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(false);
            this.dao.update(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Floor");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Floor");
            e.printStackTrace();
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BuildingEntity> dsbuilding = this.buildingDao.all();
        request.setAttribute("dsbuilding", dsbuilding);
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        FloorEntity entity = this.dao.findByID(id);
        request.setAttribute("floor", entity);
        List<FloorEntity> list = this.dao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/floor/edit.jsp");
        request.setAttribute("view1", "/views/admin/floor/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("building_id");
        int id = Integer.parseInt(s);
        FloorEntity entity = new FloorEntity();
        List<FloorEntity> list = new ArrayList<>();
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            BuildingEntity building = this.buildingDao.findByID(id);
            entity.setIdBuilding(building);
            entity.setDateCreate(new Date());
            entity.setStatus(true);
            this.dao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            list.add(entity);
            request.setAttribute("list", list);
            List<FloorEntity> all = this.dao.all();
            request.setAttribute("list", all);
            response.sendRedirect("/Floor");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/Floor");
            e.printStackTrace();
        }
    }
//    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id= Integer.parseInt(request.getParameter("id"));
//        List<FloorEntity> list = this.dao.findByIDBuilding(id);
//        request.setAttribute("show", list);
//    }
}
