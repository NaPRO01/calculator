package com.kata.test;

import java.util.ArrayList;

public class Calculator {

    public static boolean isOperation(char c){
        return c == '+' || c == '-' ||c == '*' || c == '/';
    }
    public static boolean isValidNumber(int num){
        return num>=1 && num<=10;
    }


    public static String calc(String input) throws InputExceptions {
        input = input.replaceAll(" ", ""); // Удаляем все пробелы
        /* Проверка на пустой ввод */
        if(input.isEmpty()){
            throw new InputExceptions("Ошибка ввода! Пустая входная строка.");
        }

        /* Ищем знак операции и отталкиваясь от него определяем левое и правое число */
        ArrayList<String> inputs = new ArrayList();
        for(int i = 0; i < input.length(); ++i){
            if( isOperation(input.charAt(i)) ){ // Ищем знак операции и делим строку на 3 элемента
                inputs.add(input.substring(0,i));   // Левое число
                inputs.add(input.substring(i,i+1)); // Знак операции
                inputs.add(input.substring(i+1));   // Правое число
                break;
            }
        }
        /* Проверка некоректный ввод */
        if(inputs.isEmpty()){
            throw new InputExceptions("Ошибка ввода! Не найдена допустимая операция.");
        }else if(inputs.size() == 1){
            throw new InputExceptions("Ошибка ввода! Отсутствуют значения.");
        }else if(inputs.size() == 2){
            throw new InputExceptions("Ошибка ввода! Одно из значений отсутствует.");
        }

        int left, right;  // Переменные для записи чисел после парсинга
        boolean isRoman = false; // Флаг римских чисел.

        /* Парсинг чисел */
        try{
            // Пробуем получить обычное число
            left = Integer.parseInt(inputs.get(0));
            try{
                // Если сработало, пробуем получить второе число
                right = Integer.parseInt(inputs.get(2));
            }
            catch (NumberFormatException e){
                throw new InputExceptions("Ошибка ввода! Неожиданное значение '" + inputs.get(2) + "'.");
            }
        }
        catch (NumberFormatException e){
            try{
                // Пробуем получить римское число
                left = RomanNumber.toRoman(inputs.get(0)).getNumber();
            }
            catch (IllegalArgumentException e1){
                throw new InputExceptions("Ошибка ввода! Неожиданное значение '" + inputs.get(0) + "'.");
            }
            try{
                // Пробуем получить второе римское число
                right = RomanNumber.toRoman(inputs.get(2)).getNumber();
            }
            catch (IllegalArgumentException e1){
                throw new InputExceptions("Ошибка ввода! Неожиданное значение '" + inputs.get(2) + "'.");
            }
            isRoman = true; // Активируем флаг римских чисел
        }

        /* Проверяем входные числа на выход за пределы диапазона [1..10] */
        if (!isValidNumber(left) || !isValidNumber(right)) {
            /* Кидаем ошибку с указанием на некоректное значение*/
            throw new InputExceptions("Ошибка ввода! Недопустимое значение'" + (isValidNumber(right) ?
                    (isRoman ? RomanNumber.toRoman(left): left) :
                    (isRoman ? RomanNumber.toRoman(right): right)
            ) + "'.");

        }
        else{
            int result;
            /* Определяем нужную операцию */
            switch (inputs.get(1).charAt(0)) {
                case '+':
                    result = left + right;
                    break;
                case '-':
                    result = left - right;
                    break;
                case '*':
                    result = left * right;
                    break;
                case '/':
                    result = left / right;
                    break;
                default:
                    throw new InputExceptions("Ошибка ввода! Неожиданный оператор '" + inputs.get(1) + "'.");
            }
            /* Проверяем результат на принадлежность диапазону [1..10] */
            if(isValidNumber(result)){
                return isRoman ? RomanNumber.toRoman(result).toString() :
                        Integer.toString(result);

            }else{
                throw new InputExceptions("Результат вычисления не поддерживается калькулятором.");
            }
        }

    }

}
