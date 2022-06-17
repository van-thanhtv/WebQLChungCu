package filter;


import entitys.UsersEntity;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName="filter2",urlPatterns = {"/Building", "/storeBuilding", "/updateBuilding", "/deleteBuilding", "/editBuilding", "/Floor", "/storeFloor", "/updateFloor", "/deleteFloor", "/editFloor", "/Room", "/storeRoom", "/updateRoom", "/deleteRoom", "/editRoom", "/store", "/edit", "/update", "/delete", "/User"})
public class AuthenticationFilter implements Filter {

    public AuthenticationFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        UsersEntity user = (UsersEntity) session.getAttribute("sessionUser");
        if (user == null || user.getIsAdmin() == 0) {
            httpRes.sendRedirect("/login");
            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}