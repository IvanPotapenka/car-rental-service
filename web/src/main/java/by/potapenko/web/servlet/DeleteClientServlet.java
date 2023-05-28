package by.potapenko.web.servlet;

import by.potapenko.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CLIENTS;
import static by.potapenko.web.util.PagesUtil.CLIENT_ADMIN;
@WebServlet("/admin/clients/client/delete_client")
public class DeleteClientServlet extends HttpServlet {
    private final ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.getRequestDispatcher(CLIENT_ADMIN).forward(req, resp);
        } else {
            clientService.deleteById(Long.valueOf(id));
            req.setAttribute("client_delete_success", true);
            req.getRequestDispatcher(CLIENTS).forward(req, resp);
        }
    }
}

