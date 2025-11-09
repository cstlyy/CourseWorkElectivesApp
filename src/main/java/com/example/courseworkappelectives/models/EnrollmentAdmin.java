package com.example.courseworkappelectives.models;

public class EnrollmentAdmin {
    private final int studentPlanId;
    private final int userId;
    private final String studentName;
    private final int planId;
    private final String electiveName;
    private final int year;
    private final int semester;
    private final Integer finalGrade;


    public EnrollmentAdmin(int studentPlanId, int userId, String studentName,
                           int planId, String electiveName, int year, int semester) {
        this(studentPlanId, userId, studentName, planId, electiveName, year, semester, null);
    }

    public EnrollmentAdmin(int studentPlanId, int userId, String studentName,
                           int planId, String electiveName, int year, int semester,
                           Integer finalGrade) {
        this.studentPlanId = studentPlanId;
        this.userId = userId;
        this.studentName = studentName;
        this.planId = planId;
        this.electiveName = electiveName;
        this.year = year;
        this.semester = semester;
        this.finalGrade = finalGrade;
    }

    public int getStudentPlanId() { return studentPlanId; }
    public int getUserId() { return userId; }
    public String getStudentName() { return studentName; }
    public int getPlanId() { return planId; }
    public String getElectiveName() { return electiveName; }
    public int getYear() { return year; }
    public int getSemester() { return semester; }
    public Integer getFinalGrade() { return finalGrade; }
}
