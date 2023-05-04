
package by.potapenko.web.servlet;

import by.potapenko.database.dto.CarFilter;
import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.potapenko.web.util.PagesUtil.CATALOG_FILTER;

@WebServlet("/catalog/filter")
public final class CatalogFilterServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        int limit = req.getParameter("limit") != null ? Integer.parseInt(req.getParameter("limit")) : 3;

        Map<String, String> parameters = new HashMap<>();
        for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
                String name = "&" + entry.getKey();
                String value = entry.getValue()[0];
                parameters.put(name, value);
            }
            String params = String.valueOf(parameters).replaceAll("\s|,", "")
                                                      .replace("}", "")
                                                      .replace("{", "");
            if (!parameters.isEmpty()) {
            CarFilter carFilter = CarFilter.builder()
                    .brand(req.getParameter("brand"))
                    .model(req.getParameter("model"))
                    .color(req.getParameter("color"))
                    .fuelType(req.getParameter("fuel_type"))
                    .transmission(req.getParameter("transmission"))
                    .fuelConsumption(Double.parseDouble(req.getParameter("fuel_consumption")))
                    .limit(limit)
                    .page(page)
                    .build();

                req.setAttribute("limit", limit);
                req.setAttribute("page", page);
                req.setAttribute("count", (int) Math.ceil(carService.getSizeCarFilter(carFilter) / (limit * 1.0)));

                req.setAttribute("find_car_error", false);
                req.setAttribute("parameters", params);
                req.setAttribute("cars", carService.findByFilter(carFilter));
                req.getRequestDispatcher(CATALOG_FILTER).forward(req, resp);
            } else {
                req.setAttribute("find_car_error", true);
                req.getRequestDispatcher(CATALOG_FILTER).forward(req, resp);
            }
        }
    }






