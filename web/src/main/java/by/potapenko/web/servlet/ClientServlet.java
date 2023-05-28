package by.potapenko.web.servlet;

import by.potapenko.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CLIENT_ADMIN;

@WebServlet("/admin/clients/client")
public class ClientServlet extends HttpServlet {
    private final ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("client_id", false);
            req.getRequestDispatcher(CLIENT_ADMIN).forward(req, resp);
        } else {
            req.setAttribute("client_id", true);
            req.setAttribute("client", clientService.findById(Long.parseLong(id)).get());
            req.getRequestDispatcher(CLIENT_ADMIN).forward(req, resp);
        }
    }
}
