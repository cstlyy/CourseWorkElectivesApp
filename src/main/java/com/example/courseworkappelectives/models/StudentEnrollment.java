package com.example.courseworkappelectives.models;

public class StudentEnrollment {
    private final int studentPlanId;
    private final int planId;
    private final String electiveName;
    private final int year;
    private final int semester;
    private final int lectureHours;
    private final int practiceHours;
    private final int labHours;

    public StudentEnrollment(int studentPlanId,
                             int planId,
                             String electiveName,
                             int year,
                             int semester,
                             int lectureHours,
                             int practiceHours,
                             int labHours) {
        this.studentPlanId = studentPlanId;
        this.planId = planId;
        this.electiveName = electiveName;
        this.year = year;
        this.semester = semester;
        this.lectureHours = lectureHours;
        this.practiceHours = practiceHours;
        this.labHours = labHours;
    }

    public int getStudentPlanId() { return studentPlanId; }
    public int getPlanId() { return planId; }
    public String getElectiveName() { return electiveName; }
    public int getYear() { return year; }
    public int getSemester() { return semester; }
    public int getLectureHours() { return lectureHours; }
    public int getPracticeHours() { return practiceHours; }
    public int getLabHours() { return labHours; }

    public int getTotalHours() { return lectureHours + practiceHours + labHours; }
}

