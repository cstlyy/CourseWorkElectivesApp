package com.example.courseworkappelectives.models;

public class StudentGrade {
    private final int gradeId;
    private final int studentPlanId;
    private final String electiveName;
    private final int year;
    private final int semester;
    private final String gradeDate;
    private final String gradeValue;

    public StudentGrade(int gradeId,
                        int studentPlanId,
                        String electiveName,
                        int year,
                        int semester,
                        String gradeDate,
                        String gradeValue) {
        this.gradeId = gradeId;
        this.studentPlanId = studentPlanId;
        this.electiveName = electiveName;
        this.year = year;
        this.semester = semester;
        this.gradeDate = gradeDate;
        this.gradeValue = gradeValue;
    }

    public int getGradeId() { return gradeId; }
    public int getStudentPlanId() { return studentPlanId; }
    public String getElectiveName() { return electiveName; }
    public int getYear() { return year; }
    public int getSemester() { return semester; }
    public String getGradeDate() { return gradeDate; }
    public String getGradeValue() { return gradeValue; }
}

