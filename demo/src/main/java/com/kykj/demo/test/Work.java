package com.kykj.demo.test;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/24 10:44
 */
public class Work {
    private String name;
    private String age;
    Work(){}
    Work(String name) {
        this.name = name;
    }

    public String getName() {
        return name == null ? "123" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Work{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
