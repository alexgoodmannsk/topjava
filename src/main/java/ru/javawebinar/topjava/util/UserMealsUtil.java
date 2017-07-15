package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> test = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        for (UserMealWithExceed x: test) {
            System.out.println(x.getDateTime() + " " + x.getDescription() + " " + x.getCalories() + " " + x.isExceed());
        }
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        //Считаем калории для каждого дня и заносим их в мар
        Map<LocalDate, Integer> map = mealList.stream()
                //группируем наш стрим в мап по ключу toLocalDate()
                .collect(Collectors.groupingBy(s -> s.getDateTime().toLocalDate(),
                //суммируем каллории в каждой группе чтобы привести value в мап к Integer
                Collectors.summingInt(UserMeal::getCalories)));
        //Формируем отфильтрованный список
        return mealList.stream()
                //фильтруем по времени
                .filter(s -> TimeUtil.isBetween(s.getDateTime().toLocalTime(), startTime, endTime))
                //преобразуем meal в mealWithExceed
                .map(s -> new UserMealWithExceed(s.getDateTime(), s.getDescription(), s.getCalories(), map.get(s.getDateTime().toLocalDate()) > caloriesPerDay))
                //преобразуем наш стрим в список
                .collect(Collectors.toList());
    }
}
