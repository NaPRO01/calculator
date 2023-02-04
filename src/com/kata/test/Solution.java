package com.kata.test;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        System.out.print( "Введите операцию в формате [a + b]: " );
        Scanner scan = new Scanner(System.in);
        try{
            System.out.println( Calculator.calc(scan.nextLine()) );
        }catch (InputExceptions e){
            System.err.println(e.getMessage());
        }
    }
}


