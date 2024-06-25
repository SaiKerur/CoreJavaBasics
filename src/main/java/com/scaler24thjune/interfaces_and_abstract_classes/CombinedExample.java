package com.scaler24thjune.interfaces_and_abstract_classes;

public class CombinedExample {
    public static void main(String[] args) {
        Bird bird = new Bird("Sparrow");
        Fish fish = new Fish("Goldfish");
        Duck duck = new Duck("Mallard");

        bird.makeSound();
        bird.fly();
        bird.sleep();

        fish.makeSound();
        fish.swim();
        fish.sleep();

        duck.makeSound();
        duck.fly();
        duck.swim();
        duck.sleep();
    }
}
