package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealDeleteServlet extends HttpServlet {

    private final MealService service = new MealServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long ID = Long.parseLong(req.getParameter("id"));
        service.delete(ID);
        resp.sendRedirect("meals");
    }

}
