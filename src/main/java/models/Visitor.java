package models;

import javax.persistence.Column;

public class Visitor extends Person {
    private int age;
    private int height;

    public Visitor() {
    }

    public Visitor(String name, int age, int height,Park park) {
        super(name, park);
        this.age = age;
        this.height = height;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "height")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
