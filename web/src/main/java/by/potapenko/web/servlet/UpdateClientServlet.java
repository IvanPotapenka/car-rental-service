package by.potapenko.web.servlet;

import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.ContactClient;
import by.potapenko.database.entity.DocumentEntity;
import by.potapenko.database.entity.enam.SeriesPassport;
import by.potapenko.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.time.LocalDate;

import static by.potapenko.web.util.PagesUtil.UPDATE_CLIENT;

//@WebServlet("/admin/clients/client/update_client")
public class UpdateClientServlet extends HttpServlet {
    private final ClientService clientService ;

    public UpdateClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("client", clientService.findById(Long.parseLong(id)).get());
        req.getRequestDispatcher(UPDATE_CLIENT).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DocumentEntity passport = DocumentEntity.builder()
                .series(SeriesPassport.valueOf(req.getParameter("series")))
                .number(Integer.valueOf(req.getParameter("number")))
                .driverLicense(req.getParameter("driverLicense"))
                .build();
        ClientEntity updateClient = ClientEntity.builder()
                .id(Long.valueOf(req.getParameter("client_id")))
                .firstName(req.getParameter("first_name"))
                .lastName(req.getParameter("last_name"))
                .dateOfBirthday(LocalDate.parse(req.getParameter("date_of_birthday")))
                .contact(ContactClient.builder()
                        .phone(req.getParameter("phone"))
                        .address(req.getParameter("address"))
                        .build())
                .build();
//        updateClient.setDateOfCreation(LocalDateTime.parse(req.getParameter("date_of_creation")));
        clientService.create(updateClient).ifPresentOrElse(
                client -> successCreateCar(req, resp, client),
                () -> faultCreateCar(req, resp));
    }

    @SneakyThrows
    private static void successCreateCar(HttpServletRequest req, HttpServletResponse resp, ClientEntity client) {
        req.setAttribute("update_client_error", false);
        req.setAttribute("client", client);
        req.getRequestDispatcher(UPDATE_CLIENT).forward(req, resp);
    }

    @SneakyThrows
    private static void faultCreateCar(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("update_client_error", true);
        req.getRequestDispatcher(UPDATE_CLIENT).forward(req, resp);
    }
}


