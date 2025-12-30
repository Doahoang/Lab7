package com.example.student;

public class Student {
    private int id;
    private String name;
    private String email;

    // Constructor không id (dùng khi thêm)
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Constructor có id (dùng khi đọc từ SQLite)
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // GETTER
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
