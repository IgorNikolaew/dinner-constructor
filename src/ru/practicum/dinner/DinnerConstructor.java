package ru.practicum.dinner;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Random; //    ПОКА НЕ ЗНАЮ ЧТО ТУТ

public class DinnerConstructor {

   static HashMap<String, ArrayList<String>> dinnersByType = new HashMap<>();// хранилище блюд: ключ — тип блюда (например, "Суп"), значение — список названий блюд этого типа
    static Random random = new Random(); //этот вспомогательный класс поможет сделать произвольные сочетания блюд

    //в этом методе мы добавляем компонент в подборку
    public static void addNewDish(String dishType, String dishName) {
        // УБРАЛ ЛИШНЕЕ ArrayList<String> dishesForType = new ArrayList<>(); //переменная для списка блюд
        if (dinnersByType.containsKey(dishType)) { //здесь мы должны проверить, содержит ли наше хранилище такое блюдо
              dinnersByType.get(dishType).add(dishName);
            //УБРАЛ ЛИШНЕЕ dishesForType = dinnersByType.get(dishType);//если мы уже работали с этим типом - используем существующий список
        } else {
            ArrayList<String> dishesForType = new ArrayList<>();//для нового типа блюд создаём пустой список компонентов.
            dishesForType.add(dishName);

            dinnersByType.put(dishType, dishesForType); //запоминаем новый список в хранилище
        }

        //УБРАЛ ЛИШНЮЮ ПЕРЕМЕННУЮ dishesForType.add(dishName); //независимо от того, новый это список или существующий - добавим в него конкретное блюдо


    }

    //метод для генерирования вариантов комбинации блюд
    public static ArrayList<ArrayList<String>> generateCombos(int comboNumber, ArrayList<String> dishTypes) {
        ArrayList<ArrayList<String>> combos = new ArrayList<>(); //пустой список для хранения получившихся комбинаций блюд
        for (int i=1; i <= comboNumber; i++) {
            ArrayList<String> combo = generateCombo(dishTypes); //одна комбинация блюд генерируется в отдельном методе
            combos.add(combo);
        }
        return combos;
    }


    //метод для проверки дубликатов блюд

    public static boolean checkType(String type) {
        return dinnersByType.containsKey(type); //если хранилище уже содержит такое блюдо - вернём true
    }

    //метод для генерирования одной комбинации блюд
    private static ArrayList<String> generateCombo(ArrayList<String> dishTypes) {
        ArrayList<String> selectedDishes = new ArrayList<>();
        for (String dishType: dishTypes) {
            ArrayList<String> availableDishes = dinnersByType.get(dishType); //достаём из хранилища варианты блюд по типу
            String selectedDish = getRandomDish(availableDishes); //полцчим произвольное блюдо
            selectedDishes.add(selectedDish); //добавим блюдо в подборку комбинацию
        }
        return selectedDishes;
    }

    private static String getRandomDish(ArrayList<String> availableDishes) {
        int numberOfDishesForType = availableDishes.size(); //получаем общее количество доступных блюд этого типа
        int dishIndex = random.nextInt(numberOfDishesForType); //генерируем случайное число от 0 до (кол-во блюд - 1), чтобы выбрать случайное блюдо
        String selectedDish = availableDishes.get(dishIndex); //выберем произвольное блюдо по индексу
        return selectedDish;
    }

}
