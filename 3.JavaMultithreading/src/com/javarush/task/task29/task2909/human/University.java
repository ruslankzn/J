package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private List<Student> students = new ArrayList<>();

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        int index = 0;

        for(int i = 0; i < students.size(); i ++){
            if (averageGrade == students.get(i).getAverageGrade()){
                index = i;
                break;
            }
        }
        return students.get(index);
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        int index = 0;
        double maximumGrade = 0;

        for(int i = 0; i < students.size(); i ++){
            if(students.get(i).getAverageGrade() > maximumGrade){
                maximumGrade = students.get(i).getAverageGrade();
                index = i;
            }
        }
        return students.get(index);
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        int index = 0;
        double minimumGrade = 100;

        for(int i = 0; i < students.size(); i ++){
            if(students.get(i).getAverageGrade() < minimumGrade){
                minimumGrade = students.get(i).getAverageGrade();
                index = i;
            }
        }
        return students.get(index);
    }

    public void expel(Student student) {
        students.remove(student);
    }
}