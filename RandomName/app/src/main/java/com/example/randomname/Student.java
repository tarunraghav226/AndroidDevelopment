package com.example.randomname;

public class Student {

    private String name;
    private String section;
    private String year;

    public Student(String name, String section, String year) {
        this.name = name;
        this.section = section;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Name-->" + getName() +
                "\nSection-->" + getSection() +
                "\nYear-->" + getYear();
    }
}
