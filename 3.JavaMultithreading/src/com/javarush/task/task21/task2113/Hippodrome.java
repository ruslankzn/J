package com.javarush.task.task21.task2113;

import java.util.*;

public class Hippodrome {
    public static Hippodrome game;
    public static void main(String[] args) throws InterruptedException {

        List<Horse> horses = new ArrayList<Horse>();
        Horse h1 = new Horse("\uD83D\uDC0E",3,0);
        Horse h2 = new Horse("\uD83D\uDC0E",3,0);
        Horse h3 = new Horse("\uD83D\uDC0E",3,0);
        horses.add(h1);
        horses.add(h2);
        horses.add(h3);

        game = new Hippodrome(horses);

        game.run();
        game.printWinner();
    }


    private List<Horse> horses;

    public List<Horse> getHorses(){
        return this.horses;
    }

    public Hippodrome(List<Horse> horses){ this.horses = horses; }

    public void run() throws InterruptedException {
        for(int i = 0; i <100; i++){
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move(){
        for(Horse horse: horses)
            horse.move();
    }

    public void print(){
        for(Horse horse: horses)
            horse.print();
       // for(int i = 0; i < 10; i++)
            System.out.println();
    }

    public Horse getWinner(){
        Horse winner = horses.get(0);
        double dist = horses.get(0).getDistance();
        for(Horse horse: horses){
            dist = horse.getDistance();
            winner = horse;
        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
