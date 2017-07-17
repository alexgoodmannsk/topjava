package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by PC1 on 16.07.2017.
 */
public interface MealService {
    void create(Meal meal);
    Meal read(long id);
    void update(Meal meal);
    void delete(long id);
    List<Meal> getMeals();
}
