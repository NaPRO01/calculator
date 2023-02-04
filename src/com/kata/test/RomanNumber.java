package com.kata.test;

public enum RomanNumber {
    I(1),II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);

    private int number;

    RomanNumber(int num){
        this.number = num;
    }

    public int getNumber() {
        return number;
    }


    public static RomanNumber toRoman(int num){
        return values()[num-1];
    }
    public static RomanNumber toRoman(String str){
        return RomanNumber.valueOf(str);
    }


    public boolean moreThen(RomanNumber rhs){
        return this.getNumber() > rhs.getNumber();
    }
    public boolean lessThen(RomanNumber rhs){
        return this.getNumber() < rhs.getNumber();
    }
    public boolean equal(RomanNumber rhs){
        return this.getNumber() == rhs.getNumber();
    }


    public static RomanNumber sum(RomanNumber lhs, RomanNumber rhs){
        return toRoman(lhs.getNumber() + rhs.getNumber());
    }
    public static RomanNumber sub(RomanNumber lhs, RomanNumber rhs){
        return toRoman(lhs.getNumber() - rhs.getNumber());
    }
    public static RomanNumber mult(RomanNumber lhs, RomanNumber rhs){
        return toRoman(lhs.getNumber() * rhs.getNumber());
    }
    public static RomanNumber div(RomanNumber lhs, RomanNumber rhs){
        return toRoman(lhs.getNumber() / rhs.getNumber());
    }
}
