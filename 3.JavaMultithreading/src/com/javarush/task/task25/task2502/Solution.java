package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels = new ArrayList<Wheel>();



        public Car() {
            //init wheels here
            String[] temp = loadWheelNamesFromDB();
            if(temp.length!=4)
                throw new IllegalArgumentException();
            else
                for(int i=0; i<temp.length; i++)
                    wheels.add(Wheel.valueOf(temp[i]));
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
