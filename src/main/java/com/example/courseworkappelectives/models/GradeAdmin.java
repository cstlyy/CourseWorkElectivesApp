package com.example.courseworkappelectives.models;

public class GradeAdmin {
    private final int gradeId;
    private final int studentPlanId;
    private final String studentName;
    private final String electiveName;
    private final String gradeDate;
    private final String gradeValue;

    public GradeAdmin(int gradeId, int studentPlanId, String studentName,
                      String electiveName, String gradeDate, String gradeValue) {
        this.gradeId = gradeId;
        this.studentPlanId = studentPlanId;
        this.studentName = studentName;
        this.electiveName = electiveName;
        this.gradeDate = gradeDate;
        this.gradeValue = gradeValue;
    }

    public int getGradeId() { return gradeId; }
    public int getStudentPlanId() { return studentPlanId; }
    public String getStudentName() { return studentName; }
    public String getElectiveName() { return electiveName; }
    public String getGradeDate() { return gradeDate; }
    public String getGradeValue() { return gradeValue; }
}
