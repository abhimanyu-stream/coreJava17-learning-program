package com.java17.interview.prepartion;

public class CompileTimeOrStaticPolymorphism {

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        calculate.add(5, 8);
        calculate.add(5.7, 8.6);
        calculate.add(5.0f, 8.9f);
    }

}
class Calculate{

    public int add(int x, int y){
        return x + y;
    }
    public double add(double x, double y){
        return x + y;
    }
    public float add(float x, float y){
        return x + y;
    }

}