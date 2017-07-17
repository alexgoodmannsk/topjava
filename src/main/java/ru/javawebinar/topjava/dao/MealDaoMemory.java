package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class MealDaoMemory implements MealDao {

    private static volatile AtomicLong ID = new AtomicLong(0);
    private static final MealDaoMemory INSTANCE = new MealDaoMemory();
    private static final Map<Long, Meal> CACHE = new ConcurrentHashMap<>();

    static {
        CACHE.put(ID.incrementAndGet(),new Meal(ID.get(),LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        CACHE.put(ID.incrementAndGet(),new Meal(ID.get(),LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        CACHE.put(ID.incrementAndGet(),new Meal(ID.get(),LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        CACHE.put(ID.incrementAndGet(),new Meal(ID.get(),LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        CACHE.put(ID.incrementAndGet(),new Meal(ID.get(),LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        CACHE.put(ID.incrementAndGet(),new Meal(ID.get(),LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }
    private MealDaoMemory(){}

    public static MealDaoMemory getInstance(){
        return INSTANCE;
    }

    @Override
    public void create(Meal meal) {
        meal.setId(ID.incrementAndGet());
        CACHE.put(meal.getId(), meal);
    }

    @Override
    public Meal read(long id) {
        return CACHE.get(id);
    }

    @Override
    public void update(Meal meal) {
        CACHE.put(meal.getId(), meal);
    }

    @Override
    public void delete(long id) {
        if(CACHE.containsKey(id)) CACHE.remove(id);
    }

    @Override
    public List<Meal> getMeals() {
        return new ArrayList<>(CACHE.values());
    }

}
