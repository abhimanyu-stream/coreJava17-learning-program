package com.java17.interview.prepartion;

public class RuntimePolymorphism_MethodOverriding {

    public static void main(String[] args) {

        Animalu a1 = new Dogu();
        Animalu a2 = new Catu();

        a1.sound();
        a2.sound();
    }
    
}
class Animalu {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dogu extends Animalu {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Catu extends Animalu {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

