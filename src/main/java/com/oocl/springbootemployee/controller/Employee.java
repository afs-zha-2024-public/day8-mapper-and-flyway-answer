package com.oocl.springbootemployee.controller;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private Gender gender;
    private Double salary;

    public Employee(Integer id, String name, Integer age, Gender gender, Double salary) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}