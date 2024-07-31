package com.scaler24thjune.interfaces_and_abstract_classes;

public class Fish extends Animal implements Swimmable {
    Fish(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println(name + " makes bubbles");
    }


    @Override
    public void swim() {
        System.out.println(name + " is swimming");
    }
}

