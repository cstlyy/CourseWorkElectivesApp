package com.example.courseworkappelectives.models;

public class PlanAdmin {
    private final int planId;
    private final int electiveId;
    private final String electiveName;
    private final int semesterId;
    private final int year;
    private final int semester;
    private final int lectureHours;
    private final int practiceHours;
    private final int labHours;

    public PlanAdmin(int planId, int electiveId, String electiveName,
                     int semesterId, int year, int semester,
                     int lectureHours, int practiceHours, int labHours) {
        this.planId = planId;
        this.electiveId = electiveId;
        this.electiveName = electiveName;
        this.semesterId = semesterId;
        this.year = year;
        this.semester = semester;
        this.lectureHours = lectureHours;
        this.practiceHours = practiceHours;
        this.labHours = labHours;
    }

    public int getPlanId() { return planId; }
    public int getElectiveId() { return electiveId; }
    public String getElectiveName() { return electiveName; }
    public int getSemesterId() { return semesterId; }
    public int getYear() { return year; }
    public int getSemester() { return semester; }
    public int getLectureHours() { return lectureHours; }
    public int getPracticeHours() { return practiceHours; }
    public int getLabHours() { return labHours; }

    public int getTotalHours() { return lectureHours + practiceHours + labHours; }
}