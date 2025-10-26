package com.example.courseworkappelectives.controllers;

import com.example.courseworkappelectives.models.AvailablePlan;
import com.example.courseworkappelectives.models.DataBase;
import com.example.courseworkappelectives.models.StudentEnrollment;
import com.example.courseworkappelectives.models.StudentGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StudentController {
    @FXML private Label studentInfoLabel;
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

    private int userId;
    private ObservableList<AvailablePlan> availablePlansList = FXCollections.observableArrayList();
    private ObservableList<StudentEnrollment> enrollmentsList = FXCollections.observableArrayList();
    private ObservableList<StudentGrade> gradesList = FXCollections.observableArrayList();

    public void setUserId(int userId) {
        this.userId = userId;
        loadStudentInfo();
        loadAvailablePlans();
        loadMyEnrollments();
        loadGrades();
    }

    @FXML
    private void initialize() {
        planIdColumn.setCellValueFactory(new PropertyValueFactory<>("planId"));
        planElectiveColumn.setCellValueFactory(new PropertyValueFactory<>("electiveName"));
        planYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        planSemesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        planLectureColumn.setCellValueFactory(new PropertyValueFactory<>("lectureHours"));
        planPracticeColumn.setCellValueFactory(new PropertyValueFactory<>("practiceHours"));
        planLabColumn.setCellValueFactory(new PropertyValueFactory<>("labHours"));
        planTotalColumn.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
        planActionColumn.setCellValueFactory(new PropertyValueFactory<>("actionButton"));

        enrollIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentPlanId"));
        enrollPlanIdColumn.setCellValueFactory(new PropertyValueFactory<>("planId"));
        enrollElectiveColumn.setCellValueFactory(new PropertyValueFactory<>("electiveName"));
        enrollYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        enrollSemesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        enrollLectureColumn.setCellValueFactory(new PropertyValueFactory<>("lectureHours"));
        enrollPracticeColumn.setCellValueFactory(new PropertyValueFactory<>("practiceHours"));
        enrollLabColumn.setCellValueFactory(new PropertyValueFactory<>("labHours"));
        enrollTotalColumn.setCellValueFactory(new PropertyValueFactory<>("totalHours"));

        gradeIdColumn.setCellValueFactory(new PropertyValueFactory<>("gradeId"));
        gradeEnrollIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentPlanId"));
        gradeElectiveColumn.setCellValueFactory(new PropertyValueFactory<>("electiveName"));
        gradeYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        gradeSemesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        gradeDateColumn.setCellValueFactory(new PropertyValueFactory<>("gradeDate"));
        gradeValueColumn.setCellValueFactory(new PropertyValueFactory<>("gradeValue"));
    }

    private void loadStudentInfo() {
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT surname, user_name, patronymic, user_id FROM users WHERE user_id = ?")) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String patronymic = rs.getString("patronymic");
                    String fullName = String.format("%s %s %s",
                            rs.getString("surname"),
                            rs.getString("user_name"),
                            patronymic != null ? patronymic : "");
                    studentInfoLabel.setText(String.format("Студент: %s\nID: %d", fullName.trim(), rs.getInt("user_id")));
                }
            }
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


    private void loadGrades() {
        gradesList.clear();

        String query = "SELECT g.grade_id, g.student_plan_id, e.elective_name, s.year, s.num, " +
                "g.date_grade, g.value_grade " +
                "FROM grades g " +
                "JOIN student_plan sp ON g.student_plan_id = sp.student_plan_id " +
                "JOIN plan p ON sp.plan_id = p.plan_id " +
                "JOIN electives e ON p.elective_id = e.elective_id " +
                "JOIN semesters s ON p.semester_id = s.semester_id " +
                "WHERE sp.user_id = ? " +
                "ORDER BY g.date_grade DESC";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    gradesList.add(new StudentGrade(
                            rs.getInt("grade_id"),
                            rs.getInt("student_plan_id"),
                            rs.getString("elective_name"),
                            rs.getInt("year"),
                            rs.getInt("num"),
                            rs.getDate("date_grade").toString(),
                            rs.getString("value_grade")
                    ));
                }
            }

            gradesTable.setItems(gradesList);
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
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/courseworkappelectives/styles.css").toExternalForm());

            Stage stage = (Stage) tabPane.getScene().getWindow();
            stage.setScene(scene);
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
    @FXML
    private void handleEditProfile() {
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT surname, user_name, patronymic, address, phone, password FROM users WHERE user_id = ?")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String currentPassword = rs.getString("password");

                TextField surnameField = new TextField(rs.getString("surname"));
                TextField nameField = new TextField(rs.getString("user_name"));
                TextField patronymicField = new TextField(rs.getString("patronymic"));
                TextField addressField = new TextField(rs.getString("address"));
                TextField phoneField = new TextField(rs.getString("phone"));
                PasswordField currentPasswordField = new PasswordField();
                PasswordField newPasswordField = new PasswordField();
                PasswordField confirmPasswordField = new PasswordField();

                currentPasswordField.setPromptText("Текущий пароль (для смены пароля)");
                newPasswordField.setPromptText("Новый пароль");
                confirmPasswordField.setPromptText("Подтвердите новый пароль");

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20));
                grid.addRow(0, new Label("Фамилия:"), surnameField);
                grid.addRow(1, new Label("Имя:"), nameField);
                grid.addRow(2, new Label("Отчество:"), patronymicField);
                grid.addRow(3, new Label("Адрес:"), addressField);
                grid.addRow(4, new Label("Телефон:"), phoneField);
                grid.addRow(5, new Label("Текущий пароль:"), currentPasswordField);
                grid.addRow(6, new Label("Новый пароль:"), newPasswordField);
                grid.addRow(7, new Label("Подтвердите пароль:"), confirmPasswordField);

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Редактирование профиля");
                dialog.getDialogPane().setContent(grid);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String currentInput = currentPasswordField.getText();
                    String newPassword = newPasswordField.getText();
                    String confirmPassword = confirmPasswordField.getText();

                    boolean wantsToChangePassword = !newPassword.isEmpty() || !confirmPassword.isEmpty() || !currentInput.isEmpty();

                    if (wantsToChangePassword) {
                        if (currentInput.isEmpty()) {
                            showError("Для смены пароля введите текущий пароль");
                            return;
                        }

                        boolean passwordValid = false;
                        String inputHash = sha256(currentInput);

                        if (currentPassword != null) {
                            if (currentPassword.equalsIgnoreCase(inputHash)) {
                                passwordValid = true;
                            } else if (currentPassword.equals(currentInput)) {
                                passwordValid = true;
                            }
                        }

                        if (!passwordValid) {
                            showError("Текущий пароль введен неверно");
                            return;
                        }
                        if (newPassword.isEmpty()) {
                            showError("Введите новый пароль");
                            return;
                        }
                        if (!newPassword.equals(confirmPassword)) {
                            showError("Новые пароли не совпадают");
                            return;
                        }

                        String newPasswordHash = sha256(newPassword);
                        if (newPasswordHash.equals(currentPassword)) {
                            showError("Новый пароль должен отличаться от текущего");
                            return;
                        }

                        try (PreparedStatement updatePs = conn.prepareStatement(
                                "UPDATE users SET surname=?, user_name=?, patronymic=?, address=?, phone=?, password=? WHERE user_id=?")) {
                            updatePs.setString(1, surnameField.getText());
                            updatePs.setString(2, nameField.getText());
                            updatePs.setString(3, patronymicField.getText());
                            updatePs.setString(4, addressField.getText());
                            updatePs.setString(5, phoneField.getText());
                            updatePs.setString(6, newPasswordHash);
                            updatePs.setInt(7, userId);
                            updatePs.executeUpdate();
                            showInfo("Данные и пароль успешно обновлены!");
                        }
                    } else {
                        try (PreparedStatement updatePs = conn.prepareStatement(
                                "UPDATE users SET surname=?, user_name=?, patronymic=?, address=?, phone=? WHERE user_id=?")) {
                            updatePs.setString(1, surnameField.getText());
                            updatePs.setString(2, nameField.getText());
                            updatePs.setString(3, patronymicField.getText());
                            updatePs.setString(4, addressField.getText());
                            updatePs.setString(5, phoneField.getText());
                            updatePs.setInt(6, userId);
                            updatePs.executeUpdate();
                            showInfo("Данные успешно обновлены!");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError("Ошибка при обновлении данных: " + e.getMessage());
        }
    }

    private static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] d = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(d.length * 2);
            for (byte b : d) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 недоступен", e);
        }
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}