package com.example.courseworkappelectives.controllers;

import com.example.courseworkappelectives.models.AvailablePlan;
import com.example.courseworkappelectives.models.DataBase;
import com.example.courseworkappelectives.models.StudentEnrollment;
import com.example.courseworkappelectives.models.StudentGrade;
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
    @FXML private Label studentInfoLabel;
    private int userId;
    @FXML private TabPane tabPane;

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
}
