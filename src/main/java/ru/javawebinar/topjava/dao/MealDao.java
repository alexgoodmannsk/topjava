package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    void create(Meal meal);
    Meal read(long id);
    void update(Meal meal);
    void delete(long id);
    List<Meal> getMeals();
}
