package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealViewServlet extends HttpServlet {

    private final MealService service = new MealServiceImpl();

    private static final Logger log = getLogger(MealViewServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int caloriesPerDay = 2000;
        List<MealWithExceed> mealWithExceeds = MealsUtil.mealConvertMealWithExceed(service.getMeals(), caloriesPerDay);
        req.setAttribute("meals", mealWithExceeds);
        log.debug("forward to meals");
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
