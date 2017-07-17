package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemory;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealServiceImpl implements MealService {
    MealDao storage = MealDaoMemory.getInstance();

    @Override
    public void create(Meal meal) {
        storage.create(meal);
    }

    @Override
    public Meal read(long id) {
        return storage.read(id);
    }

    @Override
    public void update(Meal meal) {
        storage.update(meal);
    }

    @Override
    public void delete(long id) {
        storage.delete(id);
    }

    @Override
    public List<Meal> getMeals() {
        return storage.getMeals();
    }
}
