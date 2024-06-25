package com.scaler24thjune.interfaces_and_abstract_classes;

public class Bird extends Animal implements Flyable {
    Bird(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println(name + " chirps");
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying");
    }
}
