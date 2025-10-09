package com.example.courseworkappelectives.models;

public class ElectiveAdmin {
    private final int electiveId;
    private final String electiveName;
    private final int departmentId;
    private final String departmentName;

    public ElectiveAdmin(int electiveId, String electiveName, int departmentId, String departmentName) {
        this.electiveId = electiveId;
        this.electiveName = electiveName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getElectiveId() { return electiveId; }
    public String getElectiveName() { return electiveName; }
    public int getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }
}
