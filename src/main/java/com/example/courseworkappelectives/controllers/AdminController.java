package com.example.courseworkappelectives.controllers;

import com.example.courseworkappelectives.models.*;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminController {
    public void setUserId(int userId) {}


    @FXML private TabPane tabPane;

    //таблица Кафедры
    @FXML private javafx.scene.control.TableView<Department> departmentsTable;
    @FXML private TableColumn<Department, Integer> deptIdColumn;
    @FXML private TableColumn<Department, String> deptNameColumn;

    //таблица Факультативы
    @FXML private javafx.scene.control.TableView<ElectiveAdmin> electivesTable;
    @FXML private TableColumn<ElectiveAdmin, Integer> electIdColumn;
    @FXML private TableColumn<ElectiveAdmin, String> electNameColumn;
    @FXML private TableColumn<ElectiveAdmin, Integer> electDeptIdColumn;
    @FXML private TableColumn<ElectiveAdmin, String> electDeptNameColumn;

    //таблица Семестры
    @FXML private javafx.scene.control.TableView<SemesterAdmin> semestersTable;
    @FXML private TableColumn<SemesterAdmin, Integer> semIdColumn;
    @FXML private TableColumn<SemesterAdmin, Integer> semYearColumn;
    @FXML private TableColumn<SemesterAdmin, Integer> semNumColumn;
    @FXML private TableColumn<SemesterAdmin, Integer> semMinColumn;

    //таблица Планы
    @FXML private javafx.scene.control.TableView<PlanAdmin> plansTable;
    @FXML private TableColumn<PlanAdmin, Integer> planIdCol;
    @FXML private TableColumn<PlanAdmin, Integer> planElectIdCol;
    @FXML private TableColumn<PlanAdmin, String> planElectCol;
    @FXML private TableColumn<PlanAdmin, Integer> planSemIdCol;
    @FXML private TableColumn<PlanAdmin, Integer> planYearCol;
    @FXML private TableColumn<PlanAdmin, Integer> planSemCol;
    @FXML private TableColumn<PlanAdmin, Integer> planLecCol;
    @FXML private TableColumn<PlanAdmin, Integer> planPracCol;
    @FXML private TableColumn<PlanAdmin, Integer> planLabCol;
    @FXML private TableColumn<PlanAdmin, Integer> planTotalCol;

    //таблица Студенты
    @FXML private javafx.scene.control.TableView<StudentAdmin> studentsTable;
    @FXML private TableColumn<StudentAdmin, Integer> studIdColumn;
    @FXML private TableColumn<StudentAdmin, String> studSurnameColumn;
    @FXML private TableColumn<StudentAdmin, String> studNameColumn;
    @FXML private TableColumn<StudentAdmin, String> studPatronymicColumn;
    @FXML private TableColumn<StudentAdmin, String> studAddressColumn;
    @FXML private TableColumn<StudentAdmin, String> studPhoneColumn;

    //таблица Записи (на факультативы)
    @FXML private javafx.scene.control.TableView<EnrollmentAdmin> enrollmentsTable;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrIdColumn;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrStudIdColumn;
    @FXML private TableColumn<EnrollmentAdmin, String> enrStudColumn;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrPlanIdColumn;
    @FXML private TableColumn<EnrollmentAdmin, String> enrElectColumn;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrYearColumn;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrSemColumn;

    //таблица Оценки
    @FXML private TableView<GradeAdmin> gradesAdminTable;
    @FXML private TableColumn<GradeAdmin, Integer> grIdColumn;
    @FXML private TableColumn<GradeAdmin, Integer> grEnrIdColumn;
    @FXML private TableColumn<GradeAdmin, String> grStudColumn;
    @FXML private TableColumn<GradeAdmin, String> grElectColumn;
    @FXML private TableColumn<GradeAdmin, String> grDateColumn;
    @FXML private TableColumn<GradeAdmin, String> grValueColumn;
}
