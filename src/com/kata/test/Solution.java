package com.kata.test;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws InputExceptions {

        numberConverter converter = new numberConverter();
        String[] action = {"+", "-", "/", "*"};
        String[] splitterAction = {"\\+", "-", "/", "\\*"};
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение в одну строку: [7+8] + Enter");
        String expression = scan.nextLine();

        int actIndex = -1;
        for (int i = 0; i < action.length; i++) {
            if (expression.contains(action[i])){
                actIndex = i;
                break;
            }
        }

        if (actIndex == -1){
            System.out.println("Неверное выражение");
            return;
        }

        String[] nums = expression.split(splitterAction[actIndex]);
        if(nums.length > 2){
            try {
                throw new InputExceptions();
            } catch (InputExceptions e){
                System.out.println("Неверный формат ввода данных");
                return;
            }
        }

        if (converter.isRoman(nums[0]) == converter.isRoman(nums[1])){
            int num1, num2;

            boolean isRoman = converter.isRoman(nums[0]);
            if (isRoman){
                num1 = converter.toInt(nums[0]);
                num2 = converter.toInt(nums[1]);
            }
            else {
                num1 = Integer.parseInt(nums[0]);
                num2 = Integer.parseInt(nums[1]);
            }

            int result = 0;
            switch (action[actIndex]){
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    try {
                        result = num1 / num2;
                    } catch (ArithmeticException | InputMismatchException e) {
                        System.out.println("Исключение : " + e);
                        System.out.println("Only integer non-zero parameters allowed");

                        break;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Не верный знак операции");
            }

            if (isRoman) {
                System.out.println(converter.toRoman(result));
            }
            else
                System.out.println(result);

        }else{
            System.out.println("Ожидаются числа в одном формате");
        }

    }

}
