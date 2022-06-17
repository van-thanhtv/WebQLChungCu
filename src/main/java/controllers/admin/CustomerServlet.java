package controllers.admin;

import Dao.customerDao;
import entitys.Customer;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet({"/storeCustomer", "/editCustomer", "/updateCustomer", "/deleteCustomer", "/Customer"})
public class CustomerServlet extends HttpServlet {
    private customerDao dao;

    public CustomerServlet() {
        this.dao = new customerDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("Customer")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("storeCustomer")) {
            this.store(request, response);
        } else if (uri.contains("updateCustomer")) {
            this.update(request, response);
        } else if (uri.contains("editCustomer")) {
            this.edit(request, response);
        } else if (uri.contains("deleteCustomer")) {
            this.delete(request, response);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> list = this.dao.all();
        request.setAttribute("ds", list);
        request.setAttribute("view", "/views/admin/customer/create.jsp");
        request.setAttribute("view1", "/views/admin/customer/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }


    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            Customer before = this.dao.findByID(id);
            Customer entity = new Customer();
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(before.getStatus());
            this.dao.update(entity);
            session.setAttribute("message","Cập Nhật Thành Công");
            response.sendRedirect("/Customer");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error","Cập Nhật Thất Bại");
            response.sendRedirect("/Customer");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            Customer entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(false);
            this.dao.update(entity);
            session.setAttribute("message","Xóa Thành Công");
            response.sendRedirect("/Customer");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error","Xóa Thất Bại");
            response.sendRedirect("/Customer");
        }
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer entity = new Customer();
        List<Customer> list = new ArrayList<>();
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(true);
            this.dao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            list.add(entity);
            request.setAttribute("ds", list);
            List<Customer> all = this.dao.all();
            request.setAttribute("ds", all);
            response.sendRedirect("/Customer");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Customer");
            session.setAttribute("error", "Thêm Mới Thất Bại");
        }

    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        Customer entity = this.dao.findByID(id);
        request.setAttribute("customer", entity);
        List<Customer> list = this.dao.all();
        request.setAttribute("ds", list);
        request.setAttribute("view", "/views/admin/customer/edit.jsp");
        request.setAttribute("view1", "/views/admin/customer/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }
}
