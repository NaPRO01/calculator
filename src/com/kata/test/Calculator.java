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


        ArrayList<String> inputs = new ArrayList();
        for(int i = 0; i < input.length(); ++i){
            if( isOperation(input.charAt(i)) ){
                inputs.add(input.substring(0,i));   // Левое число
                inputs.add(input.substring(i,i+1)); // Знак операции
                inputs.add(input.substring(i+1));   // Правое число
                break;
            }
        }

        if(inputs.isEmpty()){
            throw new InputExceptions("Ошибка ввода! Не найдена допустимая операция.");
        }else if(inputs.size() == 1){
            throw new InputExceptions("Ошибка ввода! Отсутствуют значения.");
        }else if(inputs.size() == 2){
            throw new InputExceptions("Ошибка ввода! Одно из значений отсутствует.");
        }

        int left, right;
        boolean isRoman = false;

        /* Парсинг чисел */
        try{

            left = Integer.parseInt(inputs.get(0));
            try{

                right = Integer.parseInt(inputs.get(2));
            }
            catch (NumberFormatException e){
                throw new InputExceptions("Ошибка ввода! Неожиданное значение '" + inputs.get(2) + "'.");
            }
        }
        catch (NumberFormatException e){
            try{

                left = RomanNumber.toRoman(inputs.get(0)).getNumber();
            }
            catch (IllegalArgumentException e1){
                throw new InputExceptions("Ошибка ввода! Неожиданное значение '" + inputs.get(0) + "'.");
            }
            try{

                right = RomanNumber.toRoman(inputs.get(2)).getNumber();
            }
            catch (IllegalArgumentException e1){
                throw new InputExceptions("Ошибка ввода! Неожиданное значение '" + inputs.get(2) + "'.");
            }
            isRoman = true;
        }

        /* Проверяем входные числа на выход за пределы диапазона [1..10] */
        if (!isValidNumber(left) || !isValidNumber(right)) {

            throw new InputExceptions("Ошибка ввода! Недопустимое значение'" + (isValidNumber(right) ?
                    (isRoman ? RomanNumber.toRoman(left): left) :
                    (isRoman ? RomanNumber.toRoman(right): right)
            ) + "'.");

        }
        else{
            int result;

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

                return isRoman ? RomanNumber.toRoman(result).toString() : Integer.toString(result);

        }

    }

}
