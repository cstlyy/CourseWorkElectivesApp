package com.example.courseworkappelectives.models;

public class Department {
    private final int departmentId;
    private final String departmentName;

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getDepartmentId() {return departmentId;}
    public String getDepartmentName() {return departmentName; }
}
