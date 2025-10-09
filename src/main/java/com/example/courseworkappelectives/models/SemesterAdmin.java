package com.example.courseworkappelectives.models;

public class SemesterAdmin {
    private final int semesterId;
    private final int year;
    private final int semesterNum;
    private final int minElectives;

    public SemesterAdmin(int semesterId, int year, int semesterNum, int minElectives) {
        this.semesterId = semesterId;
        this.year = year;
        this.semesterNum = semesterNum;
        this.minElectives = minElectives;
    }

    public int getSemesterId()   { return semesterId; }
    public int getYear()         { return year; }
    public int getSemesterNum()  { return semesterNum; }
    public int getMinElectives() { return minElectives; }
}

