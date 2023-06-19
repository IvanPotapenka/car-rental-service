package by.potapenko.web.servlet;

import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.ORDER;

//@WebServlet("/order")
public final class OrderServlet extends HttpServlet {
    private final CarService carService;

    public OrderServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        session.setAttribute("car", carService.findById(Long.parseLong(id)).get());
        req.setAttribute("car", carService.findById(Long.parseLong(id)).get());
        req.getRequestDispatcher(ORDER).forward(req, resp);
    }
}

