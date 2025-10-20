package com.example.courseworkappelectives.controllers;

import com.example.courseworkappelectives.models.*;
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

import java.sql.*;
import java.util.Optional;

public class AdminController {

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



    @FXML
    private void initialize() {
        deptIdColumn.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        deptNameColumn.setCellValueFactory(new PropertyValueFactory<>("departmentName"));

        electIdColumn.setCellValueFactory(new PropertyValueFactory<>("electiveId"));
        electNameColumn.setCellValueFactory(new PropertyValueFactory<>("electiveName"));
        electDeptIdColumn.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        electDeptNameColumn.setCellValueFactory(new PropertyValueFactory<>("departmentName"));

        semIdColumn.setCellValueFactory(new PropertyValueFactory<>("semesterId"));
        semYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        semNumColumn.setCellValueFactory(new PropertyValueFactory<>("semesterNum"));
        semMinColumn.setCellValueFactory(new PropertyValueFactory<>("minElectives"));

        planIdCol.setCellValueFactory(new PropertyValueFactory<>("planId"));
        planElectIdCol.setCellValueFactory(new PropertyValueFactory<>("electiveId"));
        planElectCol.setCellValueFactory(new PropertyValueFactory<>("electiveName"));
        planSemIdCol.setCellValueFactory(new PropertyValueFactory<>("semesterId"));
        planYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        planSemCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        planLecCol.setCellValueFactory(new PropertyValueFactory<>("lectureHours"));
        planPracCol.setCellValueFactory(new PropertyValueFactory<>("practiceHours"));
        planLabCol.setCellValueFactory(new PropertyValueFactory<>("labHours"));
        planTotalCol.setCellValueFactory(new PropertyValueFactory<>("totalHours"));

        studIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        studSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        studNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        studPatronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        studAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        studPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        enrIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentPlanId"));
        enrStudIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        enrStudColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        enrPlanIdColumn.setCellValueFactory(new PropertyValueFactory<>("planId"));
        enrElectColumn.setCellValueFactory(new PropertyValueFactory<>("electiveName"));
        enrYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        enrSemColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));

        grIdColumn.setCellValueFactory(new PropertyValueFactory<>("gradeId"));
        grEnrIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentPlanId"));
        grStudColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        grElectColumn.setCellValueFactory(new PropertyValueFactory<>("electiveName"));
        grDateColumn.setCellValueFactory(new PropertyValueFactory<>("gradeDate"));
        grValueColumn.setCellValueFactory(new PropertyValueFactory<>("gradeValue"));
    }


    private int userId;
    private ObservableList<Department> departmentsList = FXCollections.observableArrayList();
    private ObservableList<ElectiveAdmin> electivesList = FXCollections.observableArrayList();
    private ObservableList<SemesterAdmin> semestersList = FXCollections.observableArrayList();
    private ObservableList<PlanAdmin> plansList = FXCollections.observableArrayList();
    private ObservableList<StudentAdmin> studentsList = FXCollections.observableArrayList();
    private ObservableList<EnrollmentAdmin> enrollmentsList = FXCollections.observableArrayList();
    private ObservableList<GradeAdmin> gradesList = FXCollections.observableArrayList();



    public void setUserId(int userId) {
        this.userId = userId;
        loadAllData();
    }

    private void loadAllData() {
        loadDepartments();
        loadElectives();
        loadSemesters();
        loadPlans();
        loadStudents();
        loadEnrollments();
        loadGrades();
    }

    private void loadDepartments() {
        departmentsList.clear();
        try (Connection conn = DataBase.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM departments ORDER BY department_id")) {
            while (rs.next()) {
                departmentsList.add(new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                ));
            }
            departmentsTable.setItems(departmentsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadElectives() {
        electivesList.clear();
        try (Connection conn = DataBase.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT e.*, d.department_name FROM electives e " +
                             "LEFT JOIN departments d ON e.department_id = d.department_id " +
                             "ORDER BY e.elective_id ASC")) {
            while (rs.next()) {
                electivesList.add(new ElectiveAdmin(
                        rs.getInt("elective_id"),
                        rs.getString("elective_name"),
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                ));
            }
            electivesTable.setItems(electivesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSemesters() {
        semestersList.clear();
        try (Connection conn = DataBase.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM semesters ORDER BY year DESC, num")) {
            while (rs.next()) {
                semestersList.add(new SemesterAdmin(
                        rs.getInt("semester_id"),
                        rs.getInt("year"),
                        rs.getInt("num"),
                        rs.getInt("min_electives")
                ));
            }
            semestersTable.setItems(semestersList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadPlans() {
        plansList.clear();
        try (Connection conn = DataBase.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT p.*, e.elective_name, s.year, s.num FROM plan p " +
                             "LEFT JOIN electives e ON p.elective_id = e.elective_id " +
                             "LEFT JOIN semesters s ON p.semester_id = s.semester_id " +
                             "ORDER BY p.plan_id")) {
            while (rs.next()) {
                plansList.add(new PlanAdmin(
                        rs.getInt("plan_id"),
                        rs.getInt("elective_id"),
                        rs.getString("elective_name"),
                        rs.getInt("semester_id"),
                        rs.getInt("year"),
                        rs.getInt("num"),
                        rs.getInt("lecture_hours"),
                        rs.getInt("practice_hours"),
                        rs.getInt("lab_hours")
                ));
            }
            plansTable.setItems(plansList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadStudents() {
        studentsList.clear();
        try (Connection conn = DataBase.getInstance().getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT user_id, surname, user_name, patronymic, address, phone " +
                             "FROM users WHERE role_id = 1 ORDER BY user_id")) {
            while (rs.next()) {
                studentsList.add(new StudentAdmin(
                        rs.getInt("user_id"),
                        rs.getString("surname"),
                        rs.getString("user_name"),
                        rs.getString("patronymic"),
                        rs.getString("address"),
                        rs.getString("phone")
                ));
            }
            studentsTable.setItems(studentsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadEnrollments() {
        enrollmentsList.clear();
        try (Connection conn = DataBase.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT sp.student_plan_id, sp.user_id, sp.plan_id, " +
                             "CONCAT(u.surname, ' ', u.user_name) as student_name, " +
                             "e.elective_name, s.year, s.num " +
                             "FROM student_plan sp " +
                             "LEFT JOIN users u ON sp.user_id = u.user_id " +
                             "LEFT JOIN plan p ON sp.plan_id = p.plan_id " +
                             "LEFT JOIN electives e ON p.elective_id = e.elective_id " +
                             "LEFT JOIN semesters s ON p.semester_id = s.semester_id " +
                             "ORDER BY sp.student_plan_id ASC")) {
            while (rs.next()) {
                enrollmentsList.add(new EnrollmentAdmin(
                        rs.getInt("student_plan_id"),
                        rs.getInt("user_id"),
                        rs.getString("student_name"),
                        rs.getInt("plan_id"),
                        rs.getString("elective_name"),
                        rs.getInt("year"),
                        rs.getInt("num")
                ));
            }
            enrollmentsTable.setItems(enrollmentsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadGrades() {
        gradesList.clear();
        try (Connection conn = DataBase.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT g.grade_id, g.student_plan_id, g.date_grade, g.value_grade, " +
                             "CONCAT(u.surname, ' ', u.user_name) as student_name, " +
                             "e.elective_name " +
                             "FROM grades g " +
                             "LEFT JOIN student_plan sp ON g.student_plan_id = sp.student_plan_id " +
                             "LEFT JOIN users u ON sp.user_id = u.user_id " +
                             "LEFT JOIN plan p ON sp.plan_id = p.plan_id " +
                             "LEFT JOIN electives e ON p.elective_id = e.elective_id " +
                             "ORDER BY g.date_grade ASC ")) {
            while (rs.next()) {
                gradesList.add(new GradeAdmin(
                        rs.getInt("grade_id"),
                        rs.getInt("student_plan_id"),
                        rs.getString("student_name"),
                        rs.getString("elective_name"),
                        rs.getDate("date_grade").toString(),
                        rs.getString("value_grade")
                ));
            }
            gradesAdminTable.setItems(gradesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddDepartment() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Добавить кафедру");
        dialog.setHeaderText("Новая кафедра");
        dialog.setContentText("Название:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO departments (department_name) VALUES (?)")) {
                stmt.setString(1, name);
                stmt.executeUpdate();
                loadDepartments();
            } catch (SQLException e) {
                showError("Ошибка добавления кафедры");
            }
        });
    }

    @FXML
    private void handleEditDepartment() {
        Department selected = departmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите кафедру");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(selected.getDepartmentName());
        dialog.setTitle("Редактировать кафедру");
        dialog.setHeaderText("Изменить название");
        dialog.setContentText("Название:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE departments SET department_name = ? WHERE department_id = ?")) {
                stmt.setString(1, name);
                stmt.setInt(2, selected.getDepartmentId());
                stmt.executeUpdate();
                loadDepartments();
            } catch (SQLException e) {
                showError("Ошибка обновления кафедры");
            }
        });
    }

    @FXML
    private void handleDeleteDepartment() {
        Department selected = departmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите кафедру");
            return;
        }

        if (confirmDelete("Удалить кафедру?")) {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "DELETE FROM departments WHERE department_id = ?")) {
                stmt.setInt(1, selected.getDepartmentId());
                stmt.executeUpdate();
                loadDepartments();
            } catch (SQLException e) {
                showError("Ошибка удаления кафедры");
            }
        }
    }

    @FXML
    private void handleAddElective() {
        Dialog<ElectiveAdmin> dialog = createElectiveDialog(null);
        Optional<ElectiveAdmin> result = dialog.showAndWait();

        result.ifPresent(elective -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO electives (elective_name, department_id) VALUES (?, ?)")) {
                stmt.setString(1, elective.getElectiveName());
                stmt.setInt(2, elective.getDepartmentId());
                stmt.executeUpdate();
                loadElectives();
            } catch (SQLException e) {
                showError("Ошибка добавления факультатива");
            }
        });
    }

    @FXML
    private void handleEditElective() {
        ElectiveAdmin selected = electivesTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите факультатив");
            return;
        }

        Dialog<ElectiveAdmin> dialog = createElectiveDialog(selected);
        Optional<ElectiveAdmin> result = dialog.showAndWait();

        result.ifPresent(elective -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE electives SET elective_name = ?, department_id = ? WHERE elective_id = ?")) {
                stmt.setString(1, elective.getElectiveName());
                stmt.setInt(2, elective.getDepartmentId());
                stmt.setInt(3, selected.getElectiveId());
                stmt.executeUpdate();
                loadElectives();
            } catch (SQLException e) {
                showError("Ошибка обновления факультатива");
            }
        });
    }

    @FXML
    private void handleDeleteElective() {
        ElectiveAdmin selected = electivesTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите факультатив");
            return;
        }

        if (confirmDelete("Удалить факультатив?")) {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "DELETE FROM electives WHERE elective_id = ?")) {
                stmt.setInt(1, selected.getElectiveId());
                stmt.executeUpdate();
                loadElectives();
            } catch (SQLException e) {
                showError("Ошибка удаления факультатива");
            }
        }
    }









    private Dialog<ElectiveAdmin> createElectiveDialog(ElectiveAdmin existing) {
        Dialog<ElectiveAdmin> dialog = new Dialog<>();
        dialog.setTitle(existing == null ? "Добавить факультатив" : "Редактировать факультатив");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField nameField = new TextField(existing != null ? existing.getElectiveName() : "");
        ComboBox<Department> deptCombo = new ComboBox<>(departmentsList);
        deptCombo.setConverter(new javafx.util.StringConverter<Department>() {
            @Override
            public String toString(Department object) {
                return object != null ? object.getDepartmentName() : "";
            }
            @Override
            public Department fromString(String string) {
                return null;
            }
        });

        if (existing != null) {
            deptCombo.getSelectionModel().select(
                    departmentsList.stream()
                            .filter(d -> d.getDepartmentId() == existing.getDepartmentId())
                            .findFirst().orElse(null)
            );
        }

        grid.add(new Label("Название:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Кафедра:"), 0, 1);
        grid.add(deptCombo, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                Department dept = deptCombo.getValue();
                if (dept != null && !nameField.getText().isEmpty()) {
                    return new ElectiveAdmin(0, nameField.getText(), dept.getDepartmentId(), dept.getDepartmentName());
                }
            }
            return null;
        });

        return dialog;
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

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean confirmDelete(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
