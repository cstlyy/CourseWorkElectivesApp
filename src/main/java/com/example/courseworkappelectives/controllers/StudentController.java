package com.example.courseworkappelectives.controllers;

import com.example.courseworkappelectives.models.AvailablePlan;
import com.example.courseworkappelectives.models.DataBase;
import com.example.courseworkappelectives.models.StudentEnrollment;
import com.example.courseworkappelectives.models.StudentGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentController {

    private int userId;
    @FXML private Label studentInfoLabel;
    @FXML private TabPane tabPane;
    private ObservableList<AvailablePlan> availablePlansList = FXCollections.observableArrayList();
    private ObservableList<StudentEnrollment> enrollmentsList = FXCollections.observableArrayList();

    @FXML private TableView<AvailablePlan> availablePlansTable;
    @FXML private TableColumn<AvailablePlan, Integer> planIdColumn;
    @FXML private TableColumn<AvailablePlan, String> planElectiveColumn;
    @FXML private TableColumn<AvailablePlan, Integer> planYearColumn;
    @FXML private TableColumn<AvailablePlan, Integer> planSemesterColumn;
    @FXML private TableColumn<AvailablePlan, Integer> planLectureColumn;
    @FXML private TableColumn<AvailablePlan, Integer> planPracticeColumn;
    @FXML private TableColumn<AvailablePlan, Integer> planLabColumn;
    @FXML private TableColumn<AvailablePlan, Integer> planTotalColumn;
    @FXML private TableColumn<AvailablePlan, Button> planActionColumn;

    @FXML private TableView<StudentEnrollment> myEnrollmentsTable;
    @FXML private TableColumn<StudentEnrollment, Integer> enrollIdColumn;
    @FXML private TableColumn<StudentEnrollment, Integer> enrollPlanIdColumn;
    @FXML private TableColumn<StudentEnrollment, String> enrollElectiveColumn;
    @FXML private TableColumn<StudentEnrollment, Integer> enrollYearColumn;
    @FXML private TableColumn<StudentEnrollment, Integer> enrollSemesterColumn;
    @FXML private TableColumn<StudentEnrollment, Integer> enrollLectureColumn;
    @FXML private TableColumn<StudentEnrollment, Integer> enrollPracticeColumn;
    @FXML private TableColumn<StudentEnrollment, Integer> enrollLabColumn;
    @FXML private TableColumn<StudentEnrollment, Integer> enrollTotalColumn;

    @FXML private TableView<StudentGrade> gradesTable;
    @FXML private TableColumn<StudentGrade, Integer> gradeIdColumn;
    @FXML private TableColumn<StudentGrade, Integer> gradeEnrollIdColumn;
    @FXML private TableColumn<StudentGrade, String> gradeElectiveColumn;
    @FXML private TableColumn<StudentGrade, Integer> gradeYearColumn;
    @FXML private TableColumn<StudentGrade, Integer> gradeSemesterColumn;
    @FXML private TableColumn<StudentGrade, String> gradeDateColumn;
    @FXML private TableColumn<StudentGrade, String> gradeValueColumn;
    public void setUserId(int userId) {
        this.userId = userId;
        loadStudentInfo();
    }
    private void loadStudentInfo() {
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT surname, user_name FROM users WHERE user_id = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                studentInfoLabel.setText("Студент: " + rs.getString("surname") + " " + rs.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadMyEnrollments() {
        enrollmentsList.clear();

        String query = "SELECT sp.student_plan_id, sp.plan_id, e.elective_name, s.year, s.num, " +
                "p.lecture_hours, p.practice_hours, p.lab_hours " +
                "FROM student_plan sp " +
                "JOIN plan p ON sp.plan_id = p.plan_id " +
                "JOIN electives e ON p.elective_id = e.elective_id " +
                "JOIN semesters s ON p.semester_id = s.semester_id " +
                "WHERE sp.user_id = ? " +
                "ORDER BY s.year DESC, s.num DESC";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    enrollmentsList.add(new StudentEnrollment(
                            rs.getInt("student_plan_id"),
                            rs.getInt("plan_id"),
                            rs.getString("elective_name"),
                            rs.getInt("year"),
                            rs.getInt("num"),
                            rs.getInt("lecture_hours"),
                            rs.getInt("practice_hours"),
                            rs.getInt("lab_hours")
                    ));
                }
            }

            myEnrollmentsTable.setItems(enrollmentsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadAvailablePlans() {
        availablePlansList.clear();

        String query = "SELECT p.plan_id, e.elective_name, s.year, s.num, " +
                "p.lecture_hours, p.practice_hours, p.lab_hours " +
                "FROM plan p " +
                "JOIN electives e ON p.elective_id = e.elective_id " +
                "JOIN semesters s ON p.semester_id = s.semester_id " +
                "WHERE p.plan_id NOT IN (SELECT plan_id FROM student_plan WHERE user_id = ?) " +
                "ORDER BY s.year DESC, s.num DESC, e.elective_name";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AvailablePlan plan = new AvailablePlan(
                            rs.getInt("plan_id"),
                            rs.getString("elective_name"),
                            rs.getInt("year"),
                            rs.getInt("num"),
                            rs.getInt("lecture_hours"),
                            rs.getInt("practice_hours"),
                            rs.getInt("lab_hours")
                    );

                    plan.getActionButton().setOnAction(e -> enrollInPlan(plan.getPlanId()));
                    availablePlansList.add(plan);
                }
            }

            availablePlansTable.setItems(availablePlansList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void enrollInPlan(int planId) {
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO student_plan (user_id, plan_id) VALUES (?, ?)")) {

            stmt.setInt(1, userId);
            stmt.setInt(2, planId);

            stmt.executeUpdate();
            loadAvailablePlans();
            loadMyEnrollments();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Ошибка", "Не удалось записаться на факультатив");
        }
    }


    @FXML
    private void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/courseworkappelectives/login.fxml"));
            Stage stage = (Stage) studentInfoLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
