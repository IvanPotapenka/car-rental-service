package by.potapenko.web.servlet;

import by.potapenko.database.entity.CarEntity;
import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.ContactClient;
import by.potapenko.database.entity.DocumentEntity;
import by.potapenko.database.entity.RentalEntity;
import by.potapenko.database.entity.enam.SeriesPassport;
import by.potapenko.service.ClientService;
import by.potapenko.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;

import static by.potapenko.web.util.PagesUtil.BOOKING;
import static by.potapenko.web.util.PagesUtil.CLIENT_ORDER;

//@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    private final ClientService clientService;
    private final RentalService rentalService ;
    public BookingServlet(ClientService clientService, RentalService rentalService) {
        this.clientService = clientService;
        this.rentalService = rentalService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LocalDate rentalDate = LocalDate.parse(req.getParameter("rental_date"));
        LocalDate returnDate = LocalDate.parse(req.getParameter("return_date"));
        int rentalDays = (returnDate.getDayOfMonth() - rentalDate.getDayOfMonth());
        session.setAttribute("rental_date", rentalDate);
        session.setAttribute("return_date", returnDate);
        session.setAttribute("rental_days", rentalDays);
        req.getRequestDispatcher(BOOKING).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ClientEntity client = ClientEntity.builder()
//                .user((UserEntity) session.getAttribute("user"))
                .firstName(req.getParameter("first_name"))
                .lastName(req.getParameter("last_name"))
                .dateOfBirthday(LocalDate.parse(req.getParameter("date_of_birthday")))
                .contact(ContactClient.builder()
                        .phone(req.getParameter("phone"))
                        .address(req.getParameter("address"))
                        .build())
                .build();

        client.setDocument(DocumentEntity.builder()
                .series(SeriesPassport.valueOf(req.getParameter("series")))
                .number(Integer.valueOf(req.getParameter("number")))
                .driverLicense(req.getParameter("driver_license"))
                .build());

        client.addCar((CarEntity) session.getAttribute("car"));
        clientService.create(client);

        RentalEntity rental = RentalEntity.builder()
                .car((CarEntity) session.getAttribute("car"))
                .rentalDate((LocalDate) session.getAttribute("rental_date"))
                .returnDate((LocalDate) session.getAttribute("return_date"))
                .rentalDays((Integer) session.getAttribute("rental_days"))
                .price(((CarEntity) session.getAttribute("car")).getPrice())
                .build();

        client.addOrder(rental);

        rentalService.create(rental).ifPresentOrElse(
                order -> successCreateRental(req, resp, order),
                () -> faultCreateRental(req, resp));
    }

    @SneakyThrows
    private static void successCreateRental(HttpServletRequest req, HttpServletResponse resp, RentalEntity rental) {
        req.setAttribute("rental", rental);
        req.getRequestDispatcher(CLIENT_ORDER).forward(req, resp);
    }

    @SneakyThrows
    private static void faultCreateRental(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher(CLIENT_ORDER).forward(req, resp);
    }
}

