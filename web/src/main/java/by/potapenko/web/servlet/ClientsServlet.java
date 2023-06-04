package by.potapenko.web.servlet;

import by.potapenko.database.dto.CarFilter;
import by.potapenko.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CLIENTS;
import static by.potapenko.web.util.PagesUtil.USER_ADMIN;

@WebServlet("/admin/clients")
public final class ClientsServlet extends HttpServlet {
    private final ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CarFilter filter = CarFilter.builder()
                .limit(req.getParameter("limit"))
                .page(req.getParameter("page"))
                .build();

        Integer limit = filter.getLimit();
        Integer page = filter.getPage();
        if (id == null) {
            req.setAttribute("clients", clientService.findAll(limit, page));
            req.setAttribute("limit", limit);
            req.setAttribute("page", page);
            req.setAttribute("count", clientService.getCount(Double.valueOf(limit)));
            req.getRequestDispatcher(CLIENTS).forward(req, resp);
        } else {
            req.setAttribute("client", clientService.findById(Long.parseLong(id)).get());
            req.getRequestDispatcher(USER_ADMIN).forward(req, resp);
        }
    }
}



