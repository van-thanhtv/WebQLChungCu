package controllers.user;

import DAO.UserDao;
import JPAUtils.EncryptUtil;
import JPAUtils.XCookie;
import entitys.UsersEntity;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(value = {"/login","/register", "/signout"})
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    public LoginServlet() {
        this.userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println((UsersEntity) request.getSession().getAttribute("user"));
        if (request.getRequestURI().contains("signout")) {
            System.out.println("logout");
            HttpSession session = request.getSession();
            UsersEntity u = (UsersEntity) session.getAttribute("sessionUser");
            if (u != null) {
                XCookie.add(response, "user_remmeber", "0", 0);
                request.getSession().removeAttribute("sessionUser");
            } else {
                response.getWriter().print("error");
            }
        } else {
            System.out.println("login");
            String remmeber = XCookie.get(request, "user_remmeber", null);
            if (remmeber != null && remmeber != "0") {
                request.setAttribute("sessionUser", userDao.findByID(Integer.valueOf(remmeber)));
                request.setAttribute("sessionUser", (UsersEntity) request.getSession().getAttribute("user_remmeber"));
            }
            request.setAttribute("pageTitle", "Login");
        }
        request.setAttribute("view", "/views/account/info/login.jsp");
        request.getRequestDispatcher("/views/account/account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();
        if (uri.contains("register")){
            UsersEntity user = new UsersEntity();
            try {
                BeanUtils.populate(user, request.getParameterMap());
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (user == null) {
                request.setAttribute("result", "false");
                request.setAttribute("message", "Đăng ký thất bại! Code: -1");
                request.setAttribute("user", user);
            } else {
                System.out.println(user.getEmail());
                UsersEntity u = userDao.findByEmail(user.getEmail());
                if (u != null) {
                    request.setAttribute("user", user);
                    request.setAttribute("result", "false");
                    if (u.getEmail().equalsIgnoreCase(user.getEmail()))
                        request.setAttribute("message", "Tài khoản đã tồn tại!");
                } else {
                    user.setIsAdmin(1);
                    try {
                        user.setPasswordUser(EncryptUtil.encrypt(user.getPasswordUser()));
                        user.setStatus((byte) 1);
                        user = userDao.create(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (user == null) {
                        request.setAttribute("result", "false");
                        request.setAttribute("message", "Đăng ký thất bại! Code: -1");
                        request.setAttribute("user", user);
                    } else {
                        request.setAttribute("message", "Đăng ký thành công, bạn hiện có thể đăng nhập ngay bây giờ ");
                    }
                }
            }
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }else {
            UsersEntity  user = new UsersEntity();
            try {
                BeanUtils.populate(user, request.getParameterMap());
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            UsersEntity u = userDao.findByEmail(user.getEmail());

            if (u == null || !EncryptUtil.check(user.getPasswordUser(), u.getPasswordUser())) {
                request.setAttribute("result", "error");
                request.setAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
                request.getRequestDispatcher("/views/account/info/login.jsp").forward(request, response);
            } else {
                if (request.getParameter("remember") != null) {
                    XCookie.add(response, "user_remmeber",String.valueOf(u.getId()), 600);
                }
                user.setStatus(u.getStatus());
                user.setAvatar(u.getAvatar());
                user.setName(u.getName());
                user.setIsAdmin(u.getIsAdmin());user.setAddress(u.getAddress());user.setBirthday(u.getBirthday());
                request.getServletContext().setAttribute("sessionUser", user);
                request.getSession().setAttribute("sessionUser", user);
                request.setAttribute("result", "success");
                request.setAttribute("message", "Đăng nhập thành công, bạn sẽ được di chuyển về trang chủ!");
                request.getRequestDispatcher("/views/account/info/login.jsp").forward(request, response);
            }
        }
    }
}