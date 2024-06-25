package com.scaler24thjune.interfaces_and_abstract_classes;

public class Duck extends Animal implements Flyable, Swimmable {
    Duck(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println(name + " quacks");
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying");
    }

    @Override
    public void swim() {
        System.out.println(name + " is swimming");
    }
}

