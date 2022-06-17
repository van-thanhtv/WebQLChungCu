package controllers.admin;
import Dao.*;
import entitys.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@MultipartConfig
@WebServlet({"/Contract", "/storeContract", "/updateContract", "/deleteContract", "/editContract","/showDetail"})
public class ContractServlet extends HttpServlet {
    private contractDao contractDao;
    private roomDao roomDao;
    private typeDao typeDao;
    private customerDao customerDao;
    public ContractServlet() {
        this.contractDao=new contractDao();
        this.roomDao = new roomDao();
        this.typeDao=new typeDao();
        this.customerDao=new customerDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("Contract")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("updateContract")) {
            this.update(request, response);
        } else if (uri.contains("storeContract")) {
            this.store(request, response);
        } else if (uri.contains("editContract")) {
            this.edit(request, response);
        } else if (uri.contains("deleteContract")) {
            this.delete(request, response);
        }else if (uri.contains("showDetail")){
            this.show(request,response);
        }

    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypecontractEntity> dstype=this.typeDao.all();
        request.setAttribute("dstype",dstype);
        List<ContractEntity> list = this.contractDao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/contract/create.jsp");
        request.setAttribute("view1", "/views/admin/contract/table.jsp");
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id1= Integer.parseInt(request.getParameter("customer_id"));
        int id= Integer.parseInt(request.getParameter("id"));
        int id2= Integer.parseInt(request.getParameter("room_id"));
        int id3= Integer.parseInt(request.getParameter("type_id"));
        try {
            Customer customer = this.customerDao.findByID(id1);
            RoomEntity room = this.roomDao.findByID(id2);
            TypecontractEntity type = this.typeDao.findByID(id3);
            ContractEntity entity=this.contractDao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setIdtype(type);
            entity.setIdCustomer(customer);
            entity.setIdRoom(room);
            this.contractDao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/Contract");
        } catch (Exception e) {
            response.sendRedirect("/Contract");
            session.setAttribute("error", "Cập Nhật Thất Bại");
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            ContractEntity entity = this.contractDao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(false);
            this.contractDao.update(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Contract");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Contract");
            e.printStackTrace();
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypecontractEntity> dstype=this.typeDao.all();
        request.setAttribute("dstype",dstype);
        int id = Integer.parseInt(request.getParameter("id"));
        ContractEntity entity = this.contractDao.findByID(id);
        request.setAttribute("contract", entity);
        List<ContractEntity> list = this.contractDao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/contract/edit.jsp");
        request.setAttribute("view1", "/views/admin/contract/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id= Integer.parseInt(request.getParameter("id"));
       ContractEntity contract =this.contractDao.findByID(id);
       request.setAttribute("contract",contract);
        request.setAttribute("view", "/views/admin/contract/detail.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id1= Integer.parseInt(request.getParameter("customer_id"));
        int id2= Integer.parseInt(request.getParameter("room_id"));
        int id3= Integer.parseInt(request.getParameter("type_id"));
        ContractEntity entity = new ContractEntity();
        List<ContractEntity> list = new ArrayList<>();
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            Customer customer=this.customerDao.findByID(id1);
            RoomEntity room=this.roomDao.findByID(id2);
            if (customer==null){
                session.setAttribute("error","Khách Hàng Không Tồn Tại");
                response.sendRedirect("/Contract");
                return;
            }else {
                if (room==null){
                    session.setAttribute("error","Phòng Không Tồn Tại");
                    response.sendRedirect("/Contract");
                    return;
                }else {
                    TypecontractEntity type= this.typeDao.findByID(id3);
                    UsersEntity user = (UsersEntity) session.getAttribute("sessionUser");
                    entity.setIdUser(user);
                    entity.setIdtype(type);
                    entity.setIdCustomer(customer);
                    entity.setIdRoom(room);
                    entity.setStatus(true);
                    java.util.Date t = new java.util.Date();
                    entity.setDateCreate(new Date(t.getTime()));
                    this.contractDao.create(entity);
                    session.setAttribute("message", "Thêm Mới Thành Công");
                    list.add(entity);
                    request.setAttribute("list", list);
                    List<ContractEntity> all = this.contractDao.all();
                    request.setAttribute("list", all);
                    response.sendRedirect("/Contract");
                }
            }

        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/Contract");
            e.printStackTrace();
        }
    }
}
