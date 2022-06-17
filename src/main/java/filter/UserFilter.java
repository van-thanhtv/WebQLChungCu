package filter;

import entitys.UsersEntity;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName="filter1",urlPatterns = {"/Contract", "/storeContract", "/updateContract", "/deleteContract", "/editContract","/showDetail","/storeCustomer", "/editCustomer", "/updateCustomer", "/deleteCustomer", "/Customer"})
public class UserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        UsersEntity user = (UsersEntity) session.getAttribute("sessionUser");
        if (user == null ) {
            httpRes.sendRedirect("/login");
            return;
        }

        chain.doFilter(request, response);
    }
}
