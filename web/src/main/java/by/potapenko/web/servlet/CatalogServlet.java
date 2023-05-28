package by.potapenko.web.servlet;

import by.potapenko.database.dto.CarFilter;
import by.potapenko.database.entity.CarEntity;
import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static by.potapenko.web.util.PagesUtil.CATALOG;

@WebServlet("/catalog")
public final class CatalogServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CarFilter filter = CarFilter.builder()
                .limit(req.getParameter("limit"))
                .page(req.getParameter("page"))
                .build();

        Integer limit = filter.getLimit();
        Integer page = filter.getPage();

        req.setAttribute("limit", limit);
        req.setAttribute("page", page);
        req.setAttribute("count",carService.getCount(Double.valueOf(limit)));

        List<CarEntity> carEntity = carService.findAll(limit, page);

        if (carEntity.isEmpty()) {
            req.setAttribute("find_car_error", true);
            req.getRequestDispatcher(CATALOG).forward(req, resp);
        } else {
            req.setAttribute("find_car_error", false);
            req.setAttribute("cars", carEntity);
            req.getRequestDispatcher(CATALOG).forward(req, resp);
        }
    }
}





