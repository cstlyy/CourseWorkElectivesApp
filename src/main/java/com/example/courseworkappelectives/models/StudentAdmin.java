package com.example.courseworkappelectives.models;

public class StudentAdmin {
    private final int userId;
    private final String surname;
    private final String name;
    private final String patronymic;
    private final String address;
    private final String phone;

    public StudentAdmin(int userId, String surname, String name, String patronymic, String address, String phone) {
        this.userId = userId;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.address = address;
        this.phone = phone;
    }

    public int getUserId() { return userId; }
    public String getSurname() { return surname; }
    public String getName() { return name; }
    public String getPatronymic() { return patronymic; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}

