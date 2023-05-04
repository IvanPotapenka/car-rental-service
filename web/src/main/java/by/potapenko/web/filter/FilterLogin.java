package by.potapenko.web.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(urlPatterns = {"/cars", "/users", "/admin"})
public class FilterLogin implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Object user = ((HttpServletRequest) servletRequest).getSession().getAttribute("admin");
        if (user != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("/login_admin").forward(servletRequest, servletResponse);
        }
    }
}
