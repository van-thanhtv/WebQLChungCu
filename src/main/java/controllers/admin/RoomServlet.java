package controllers.admin;
import Dao.buildingDao;
import Dao.floorDao;
import Dao.roomDao;
import entitys.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@MultipartConfig
@WebServlet({"/Room", "/storeRoom", "/updateRoom", "/deleteRoom", "/editRoom"})
public class RoomServlet extends HttpServlet {
    private floorDao floorDao;
    private roomDao roomDao;
    private buildingDao buildingDao;

    public RoomServlet() {
        this.buildingDao=new buildingDao();
        this.floorDao = new floorDao();
        this.roomDao = new roomDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("Room")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("updateRoom")) {
            this.update(request, response);
        } else if (uri.contains("storeRoom")) {
            this.store(request, response);
        } else if (uri.contains("editRoom")) {
            this.edit(request, response);
        } else if (uri.contains("deleteRoom")) {
            this.delete(request, response);
        }

    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BuildingEntity> dsbuilding = this.buildingDao.all();
        request.setAttribute("dsbuilding", dsbuilding);
        List<FloorEntity> dsfloor = this.floorDao.all();
        request.setAttribute("dsfloor", dsfloor);
        List<RoomEntity> list = this.roomDao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/room/create.jsp");
        request.setAttribute("view1", "/views/admin/room/table.jsp");
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        int idCate = Integer.parseInt(request.getParameter("floor_id"));
        try {
            FloorEntity floor = this.floorDao.findByID(idCate);
            RoomEntity entity = this.roomDao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setIdFloor(floor);
            this.roomDao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/Room");
        } catch (Exception e) {
            response.sendRedirect("/Room");
            session.setAttribute("error", "Cập Nhật Thất Bại");
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            RoomEntity entity = this.roomDao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(false);
            this.roomDao.update(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Room");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Room");
            e.printStackTrace();
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BuildingEntity> dsbuilding = this.buildingDao.all();
        request.setAttribute("dsbuilding", dsbuilding);
        List<FloorEntity> dsfloor = this.floorDao.all();
        request.setAttribute("dsfloor", dsfloor);
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        RoomEntity room = this.roomDao.findByID(id);
        request.setAttribute("room", room);
        List<RoomEntity> list = this.roomDao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/room/edit.jsp");
        request.setAttribute("view1", "/views/admin/room/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("floor_id");
        int id = Integer.parseInt(s);
        RoomEntity entity = new RoomEntity();
        List<RoomEntity> list = new ArrayList<>();
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            FloorEntity floor = this.floorDao.findByID(id);
            entity.setIdFloor(floor);
            entity.setStatus(true);
            this.roomDao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            list.add(entity);
            request.setAttribute("list", list);
            List<RoomEntity> all = this.roomDao.all();
            request.setAttribute("list", all);
            response.sendRedirect("/Room");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/Room");
            e.printStackTrace();
        }
    }
}
