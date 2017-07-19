package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private final MealService service = new MealServiceImpl();

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getParameterMap().containsKey("action")) {
            int caloriesPerDay = 2000;
            List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(service.getMeals(), LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
            req.setAttribute("meals", mealWithExceeds);
            log.debug("forward to meals no action");
            req.getRequestDispatcher("meals.jsp").forward(req, resp);
        }else{
            String action = req.getParameter("action");
            switch (action){
                case "delete":{
                    long ID = Long.parseLong(req.getParameter("id"));
                    service.delete(ID);
                    resp.sendRedirect("meals");
                    log.debug("delete success");
                    break;
                }
                case "edit":{
                    long ID = Long.parseLong(req.getParameter("id"));
                    req.setAttribute("editMeal", service.read(ID));
                    req.getRequestDispatcher("editMeal.jsp").forward(req,resp);
                    log.debug("forward to editMeal.jsp success");
                    break;
                }
                case "add":{
                    req.getRequestDispatcher("addMeal.jsp").forward(req,resp);
                    log.debug("forward to addMeal.jsp success");
                    break;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        //запрос на редактирование
        if(req.getParameterMap().containsKey("id")) {
            long ID = Long.parseLong(req.getParameter("id"));
            service.update(new Meal(ID, localDateTime, description, calories));
            log.debug("edit success");
        //запрос на создание
        }else{
            service.create(new Meal(-1, localDateTime, description, calories));
            log.debug("create success");
        }
        resp.sendRedirect("meals");
    }
}
