package by.potapenko.web.servlet;

import by.potapenko.database.entity.ClientEntity;
import by.potapenko.service.ClientService;
import by.potapenko.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static by.potapenko.web.util.PagesUtil.CREATE_RENTAL;

@WebServlet("/admin/rentals/create_rental")
public class CreateRentalServlet extends HttpServlet {
    RentalService rentalService = RentalService.getInstance();
    ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_RENTAL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        List<ClientEntity> clients = clientService.getSearchResult(search);
        req.setAttribute("clients", clients);
        req.getRequestDispatcher(CREATE_RENTAL).forward(req, resp);


        //        req.setAttribute("client", ClientEntity.builder()
//                .firstName(req.getParameter("first_name"))
//                .lastName(req.getParameter("last_name"))
//                .middleName(req.getParameter("middle_name"))
//                .dateOfBirthday(LocalDate.parse(req.getParameter("date_of_birthday")))
//                .contact(ClientEntity.Contact.builder()
//                        .email(req.getParameter("email"))
//                        .phone(req.getParameter("phone"))
//                        .city(req.getParameter("city"))
//                        .street(req.getParameter("street"))
//                        .houseNumber(Integer.parseInt(req.getParameter("house_number")))
//                        .apartmentNumber(Integer.parseInt(req.getParameter("apartment_number")))
//                        .build())
//                .document(ClientEntity.Document.builder()
//                        .passport(req.getParameter("passport"))
//                        .driverLicense(req.getParameter("driver_license"))
//                        .build())
//                .build());
//        HttpSession session = req.getSession();
//        RentalEntity rental = RentalEntity.builder()
//                .client((ClientEntity) session.getAttribute("client"))
//                .car((CarEntity) session.getAttribute("car"))
//                .rentalDate((LocalDate) session.getAttribute("rental_date"))
//                .returnDate((LocalDate) session.getAttribute("return_date"))
//                .rentalDays((Integer) session.getAttribute("rental_days"))
//                .user((UserEntity) session.getAttribute("user"))
//                .price(((CarEntity) session.getAttribute("car")).getPrice())
//                .build();
//        req.setAttribute("rental", rental);
//        rentalService.create(rental).ifPresentOrElse(
//                order -> successCreateRental(req, resp, order),
//                () -> faultCreateRental(req, resp));
    }

//    @SneakyThrows
//    private static void successCreateRental(HttpServletRequest req, HttpServletResponse resp, RentalEntity rental) {
//        req.setAttribute("rental", rental);
//        req.setAttribute("create_client", true);
//        req.getRequestDispatcher(CREATE_RENTAL).forward(req, resp);
//    }
//
//    @SneakyThrows
//    private static void faultCreateRental(HttpServletRequest req, HttpServletResponse resp) {
//        req.setAttribute("create_rental", false);
//        req.getRequestDispatcher(CREATE_CLIENT).forward(req, resp);
//    }
}

