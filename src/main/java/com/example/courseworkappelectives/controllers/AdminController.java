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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class AdminController {
    @FXML private TabPane tabPane;

    @FXML private TableView<Department> departmentsTable;
    @FXML private TableColumn<Department, Integer> deptIdColumn;
    @FXML private TableColumn<Department, String> deptNameColumn;

    @FXML private TableView<ElectiveAdmin> electivesTable;
    @FXML private TableColumn<ElectiveAdmin, Integer> electIdColumn;
    @FXML private TableColumn<ElectiveAdmin, String> electNameColumn;
    @FXML private TableColumn<ElectiveAdmin, Integer> electDeptIdColumn;
    @FXML private TableColumn<ElectiveAdmin, String> electDeptNameColumn;

    @FXML private TableView<SemesterAdmin> semestersTable;
    @FXML private TableColumn<SemesterAdmin, Integer> semIdColumn;
    @FXML private TableColumn<SemesterAdmin, Integer> semYearColumn;
    @FXML private TableColumn<SemesterAdmin, Integer> semNumColumn;
    @FXML private TableColumn<SemesterAdmin, Integer> semMinColumn;

    @FXML private TableView<PlanAdmin> plansTable;
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

    @FXML private TableView<StudentAdmin> studentsTable;
    @FXML private TableColumn<StudentAdmin, Integer> studIdColumn;
    @FXML private TableColumn<StudentAdmin, String> studSurnameColumn;
    @FXML private TableColumn<StudentAdmin, String> studNameColumn;
    @FXML private TableColumn<StudentAdmin, String> studPatronymicColumn;
    @FXML private TableColumn<StudentAdmin, String> studAddressColumn;
    @FXML private TableColumn<StudentAdmin, String> studPhoneColumn;

    @FXML private TableView<EnrollmentAdmin> enrollmentsTable;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrIdColumn;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrStudIdColumn;
    @FXML private TableColumn<EnrollmentAdmin, String> enrStudColumn;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrPlanIdColumn;
    @FXML private TableColumn<EnrollmentAdmin, String> enrElectColumn;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrYearColumn;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrSemColumn;
    @FXML private TableColumn<EnrollmentAdmin, Integer> enrFinalGradeColumn;


    @FXML private TableView<GradeAdmin> gradesAdminTable;
    @FXML private TableColumn<GradeAdmin, Integer> grIdColumn;
    @FXML private TableColumn<GradeAdmin, Integer> grEnrIdColumn;
    @FXML private TableColumn<GradeAdmin, String> grStudColumn;
    @FXML private TableColumn<GradeAdmin, String> grElectColumn;
    @FXML private TableColumn<GradeAdmin, String> grDateColumn;
    @FXML private TableColumn<GradeAdmin, String> grValueColumn;

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
        enrFinalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));


        grIdColumn.setCellValueFactory(new PropertyValueFactory<>("gradeId"));
        grEnrIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentPlanId"));
        grStudColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        grElectColumn.setCellValueFactory(new PropertyValueFactory<>("electiveName"));
        grDateColumn.setCellValueFactory(new PropertyValueFactory<>("gradeDate"));
        grValueColumn.setCellValueFactory(new PropertyValueFactory<>("gradeValue"));
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

        final String sql =
                "SELECT sp.student_plan_id, sp.user_id, sp.plan_id, " +
                        "       CONCAT(u.surname, ' ', u.user_name) AS student_name, " +
                        "       e.elective_name, s.year, s.num, " +
                        "       (SELECT g.value_grade " +
                        "          FROM grades g " +
                        "         WHERE g.student_plan_id = sp.student_plan_id " +
                        "         ORDER BY g.date_grade DESC, g.grade_id DESC " +
                        "         LIMIT 1) AS final_grade " +
                        "  FROM student_plan sp " +
                        "  LEFT JOIN users u     ON sp.user_id = u.user_id " +
                        "  LEFT JOIN plan p      ON sp.plan_id = p.plan_id " +
                        "  LEFT JOIN electives e ON p.elective_id = e.elective_id " +
                        "  LEFT JOIN semesters s ON p.semester_id = s.semester_id " +
                        " ORDER BY s.year DESC, s.num DESC, e.elective_name, student_name";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integer finalGrade = null;
                int fg = rs.getInt("final_grade");
                if (!rs.wasNull()) {
                    finalGrade = fg;
                }

                enrollmentsList.add(new EnrollmentAdmin(
                        rs.getInt("student_plan_id"),
                        rs.getInt("user_id"),
                        rs.getString("student_name"),
                        rs.getInt("plan_id"),
                        rs.getString("elective_name"),
                        rs.getInt("year"),
                        rs.getInt("num"),
                        finalGrade
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

    @FXML
    private void handleAddSemester() {
        Dialog<SemesterAdmin> dialog = createSemesterDialog(null);
        Optional<SemesterAdmin> result = dialog.showAndWait();

        result.ifPresent(semester -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO semesters (year, num, min_electives) VALUES (?, ?, ?)")) {
                stmt.setInt(1, semester.getYear());
                stmt.setInt(2, semester.getSemesterNum());
                stmt.setInt(3, semester.getMinElectives());
                stmt.executeUpdate();
                loadSemesters();
            } catch (SQLException e) {
                showError("Ошибка добавления семестра");
            }
        });
    }

    @FXML
    private void handleEditSemester() {
        SemesterAdmin selected = semestersTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите семестр");
            return;
        }

        Dialog<SemesterAdmin> dialog = createSemesterDialog(selected);
        Optional<SemesterAdmin> result = dialog.showAndWait();

        result.ifPresent(semester -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE semesters SET year = ?, num = ?, min_electives = ? WHERE semester_id = ?")) {
                stmt.setInt(1, semester.getYear());
                stmt.setInt(2, semester.getSemesterNum());
                stmt.setInt(3, semester.getMinElectives());
                stmt.setInt(4, selected.getSemesterId());
                stmt.executeUpdate();
                loadSemesters();
            } catch (SQLException e) {
                showError("Ошибка обновления семестра");
            }
        });
    }

    @FXML
    private void handleDeleteSemester() {
        SemesterAdmin selected = semestersTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите семестр");
            return;
        }

        if (confirmDelete("Удалить семестр?")) {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "DELETE FROM semesters WHERE semester_id = ?")) {
                stmt.setInt(1, selected.getSemesterId());
                stmt.executeUpdate();
                loadSemesters();
            } catch (SQLException e) {
                showError("Ошибка удаления семестра");
            }
        }
    }

    @FXML
    private void handleAddPlan() {
        Dialog<PlanAdmin> dialog = createPlanDialog(null);
        Optional<PlanAdmin> result = dialog.showAndWait();

        result.ifPresent(plan -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO plan (elective_id, semester_id, lecture_hours, practice_hours, lab_hours) VALUES (?, ?, ?, ?, ?)")) {
                stmt.setInt(1, plan.getElectiveId());
                stmt.setInt(2, plan.getSemesterId());
                stmt.setInt(3, plan.getLectureHours());
                stmt.setInt(4, plan.getPracticeHours());
                stmt.setInt(5, plan.getLabHours());
                stmt.executeUpdate();
                loadPlans();
            } catch (SQLException e) {
                showError("Ошибка добавления плана");
            }
        });
    }

    @FXML
    private void handleEditPlan() {
        PlanAdmin selected = plansTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите план");
            return;
        }

        Dialog<PlanAdmin> dialog = createPlanDialog(selected);
        Optional<PlanAdmin> result = dialog.showAndWait();

        result.ifPresent(plan -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE plan SET elective_id = ?, semester_id = ?, lecture_hours = ?, practice_hours = ?, lab_hours = ? WHERE plan_id = ?")) {
                stmt.setInt(1, plan.getElectiveId());
                stmt.setInt(2, plan.getSemesterId());
                stmt.setInt(3, plan.getLectureHours());
                stmt.setInt(4, plan.getPracticeHours());
                stmt.setInt(5, plan.getLabHours());
                stmt.setInt(6, selected.getPlanId());
                stmt.executeUpdate();
                loadPlans();
            } catch (SQLException e) {
                showError("Ошибка обновления плана");
            }
        });
    }

    @FXML
    private void handleDeletePlan() {
        PlanAdmin selected = plansTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите план");
            return;
        }

        if (confirmDelete("Удалить план?")) {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "DELETE FROM plan WHERE plan_id = ?")) {
                stmt.setInt(1, selected.getPlanId());
                stmt.executeUpdate();
                loadPlans();
            } catch (SQLException e) {
                showError("Ошибка удаления плана");
            }
        }
    }

    @FXML
    private void handleAddStudent() {
        Dialog<StudentAdmin> dialog = createStudentDialog(null);
        Optional<StudentAdmin> result = dialog.showAndWait();

        result.ifPresent(student -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO users (surname, user_name, patronymic, address, phone, password, role_id) VALUES (?, ?, ?, ?, ?, '123456', 1)")) {
                stmt.setString(1, student.getSurname());
                stmt.setString(2, student.getName());
                stmt.setString(3, student.getPatronymic());
                stmt.setString(4, student.getAddress());
                stmt.setString(5, student.getPhone());
                stmt.executeUpdate();
                loadStudents();
            } catch (SQLException e) {
                showError("Ошибка добавления студента");
            }
        });
    }

    @FXML
    private void handleEditStudent() {
        StudentAdmin selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите студента");
            return;
        }

        Dialog<StudentAdmin> dialog = createStudentDialog(selected);
        Optional<StudentAdmin> result = dialog.showAndWait();

        result.ifPresent(student -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE users SET surname = ?, user_name = ?, patronymic = ?, address = ?, phone = ? WHERE user_id = ?")) {
                stmt.setString(1, student.getSurname());
                stmt.setString(2, student.getName());
                stmt.setString(3, student.getPatronymic());
                stmt.setString(4, student.getAddress());
                stmt.setString(5, student.getPhone());
                stmt.setInt(6, selected.getUserId());
                stmt.executeUpdate();
                loadStudents();
            } catch (SQLException e) {
                showError("Ошибка обновления студента");
            }
        });
    }

    @FXML
    private void handleDeleteStudent() {
        StudentAdmin selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите студента");
            return;
        }

        if (confirmDelete("Удалить студента?")) {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "DELETE FROM users WHERE user_id = ?")) {
                stmt.setInt(1, selected.getUserId());
                stmt.executeUpdate();
                loadStudents();
            } catch (SQLException e) {
                showError("Ошибка удаления студента");
            }
        }
    }
    private Dialog<EnrollmentAdmin> createEnrollmentDialog(EnrollmentAdmin existing) {
        Dialog<EnrollmentAdmin> dialog = new Dialog<>();
        dialog.setTitle(existing == null ? "Добавить запись" : "Изменить запись");
        ObservableList<StudentAdmin> students = FXCollections.observableArrayList();
        try (Connection conn = DataBase.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT user_id, surname, user_name, patronymic, address, phone " +
                             "FROM users WHERE role_id = 1 ORDER BY surname, user_name")) {
            while (rs.next()) {
                students.add(new StudentAdmin(
                        rs.getInt("user_id"),
                        rs.getString("surname"),
                        rs.getString("user_name"),
                        rs.getString("patronymic"),
                        rs.getString("address"),
                        rs.getString("phone")));
            }
        } catch (SQLException e) { e.printStackTrace(); }

        ObservableList<PlanAdmin> plans = FXCollections.observableArrayList();
        try (Connection conn = DataBase.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT p.*, e.elective_name, s.year, s.num " +
                             "FROM plan p " +
                             "LEFT JOIN electives e ON p.elective_id = e.elective_id " +
                             "LEFT JOIN semesters s ON p.semester_id = s.semester_id " +
                             "ORDER BY s.year DESC, s.num DESC, e.elective_name")) {
            while (rs.next()) {
                plans.add(new PlanAdmin(
                        rs.getInt("plan_id"),
                        rs.getInt("elective_id"),
                        rs.getString("elective_name"),
                        rs.getInt("semester_id"),
                        rs.getInt("year"),
                        rs.getInt("num"),
                        rs.getInt("lecture_hours"),
                        rs.getInt("practice_hours"),
                        rs.getInt("lab_hours")));
            }
        } catch (SQLException e) { e.printStackTrace(); }

        ComboBox<StudentAdmin> studentCb = new ComboBox<>(students);
        studentCb.setConverter(new javafx.util.StringConverter<>() {
            public String toString(StudentAdmin s) { return s==null? "": s.getSurname()+" "+s.getName(); }
            public StudentAdmin fromString(String s) { return null; }
        });

        ComboBox<PlanAdmin> planCb = new ComboBox<>(plans);
        planCb.setConverter(new javafx.util.StringConverter<>() {
            public String toString(PlanAdmin p) { return p==null? "": p.getElectiveName()+" — "+p.getYear()+"/"+p.getSemester(); }
            public PlanAdmin fromString(String s) { return null; }
        });

        if (existing != null) {
            students.stream().filter(s -> s.getUserId()==existing.getUserId()).findFirst().ifPresent(studentCb.getSelectionModel()::select);
            plans.stream().filter(p -> p.getPlanId()==existing.getPlanId()).findFirst().ifPresent(planCb.getSelectionModel()::select);
        }

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10); grid.setPadding(new Insets(20));
        grid.addRow(0, new Label("Студент:"), studentCb);
        grid.addRow(1, new Label("План:"),    planCb);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK && studentCb.getValue()!=null && planCb.getValue()!=null) {
                StudentAdmin s = studentCb.getValue();
                PlanAdmin p = planCb.getValue();
                return new EnrollmentAdmin(
                        existing==null? 0 : existing.getStudentPlanId(),
                        s.getUserId(),
                        s.getSurname()+" "+s.getName(),
                        p.getPlanId(),
                        p.getElectiveName(),
                        p.getYear(),
                        p.getSemester()
                );
            }
            return null;
        });

        return dialog;
    }

    @FXML
    private void handleAddEnrollment() {
        Dialog<EnrollmentAdmin> dialog = createEnrollmentDialog(null);
        dialog.showAndWait().ifPresent(enr -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(
                         "INSERT INTO student_plan (user_id, plan_id) VALUES (?, ?)")) {
                ps.setInt(1, enr.getUserId());
                ps.setInt(2, enr.getPlanId());
                ps.executeUpdate();
                loadEnrollments();
            } catch (SQLException e) {
                e.printStackTrace();
                showError("Не удалось добавить запись");
            }
        });
    }

    @FXML
    private void handleEditEnrollment() {
        EnrollmentAdmin selected = enrollmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) { showError("Выберите запись"); return; }

        Dialog<EnrollmentAdmin> dialog = createEnrollmentDialog(selected);
        dialog.showAndWait().ifPresent(enr -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(
                         "UPDATE student_plan SET user_id = ?, plan_id = ? WHERE student_plan_id = ?")) {
                ps.setInt(1, enr.getUserId());
                ps.setInt(2, enr.getPlanId());
                ps.setInt(3, selected.getStudentPlanId());
                ps.executeUpdate();
                loadEnrollments();
            } catch (SQLException e) {
                e.printStackTrace();
                showError("Не удалось изменить запись");
            }
        });
    }

    @FXML
    private void handleDeleteEnrollment() {
        EnrollmentAdmin selected = enrollmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите запись");
            return;
        }

        if (confirmDelete("Удалить запись?")) {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "DELETE FROM student_plan WHERE student_plan_id = ?")) {
                stmt.setInt(1, selected.getStudentPlanId());
                stmt.executeUpdate();
                loadEnrollments();
            } catch (SQLException e) {
                showError("Ошибка удаления записи");
            }
        }
    }

    @FXML
    private void handleAddGrade() {
        Dialog<GradeAdmin> dialog = createGradeDialog(null);
        Optional<GradeAdmin> result = dialog.showAndWait();

        result.ifPresent(grade -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO grades (student_plan_id, date_grade, value_grade) VALUES (?, ?, ?)")) {
                stmt.setInt(1, grade.getStudentPlanId());
                stmt.setDate(2, Date.valueOf(grade.getGradeDate()));
                stmt.setString(3, grade.getGradeValue());
                stmt.executeUpdate();
                loadGrades();
            } catch (SQLException e) {
                showError("Ошибка добавления оценки");
            }
        });
    }

    @FXML
    private void handleEditGrade() {
        GradeAdmin selected = gradesAdminTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите оценку");
            return;
        }

        Dialog<GradeAdmin> dialog = createGradeDialog(selected);
        Optional<GradeAdmin> result = dialog.showAndWait();

        result.ifPresent(grade -> {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE grades SET date_grade = ?, value_grade = ? WHERE grade_id = ?")) {
                stmt.setDate(1, Date.valueOf(grade.getGradeDate()));
                stmt.setString(2, grade.getGradeValue());
                stmt.setInt(3, selected.getGradeId());
                stmt.executeUpdate();
                loadGrades();
            } catch (SQLException e) {
                showError("Ошибка обновления оценки");
            }
        });
    }

    @FXML
    private void handleDeleteGrade() {
        GradeAdmin selected = gradesAdminTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите оценку");
            return;
        }

        if (confirmDelete("Удалить оценку?")) {
            try (Connection conn = DataBase.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "DELETE FROM grades WHERE grade_id = ?")) {
                stmt.setInt(1, selected.getGradeId());
                stmt.executeUpdate();
                loadGrades();
            } catch (SQLException e) {
                showError("Ошибка удаления оценки");
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

    private Dialog<SemesterAdmin> createSemesterDialog(SemesterAdmin existing) {
        Dialog<SemesterAdmin> dialog = new Dialog<>();
        dialog.setTitle(existing == null ? "Добавить семестр" : "Редактировать семестр");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField yearField = new TextField(existing != null ? String.valueOf(existing.getYear()) : "");
        TextField numField = new TextField(existing != null ? String.valueOf(existing.getSemesterNum()) : "");
        TextField minField = new TextField(existing != null ? String.valueOf(existing.getMinElectives()) : "");

        grid.add(new Label("Год:"), 0, 0);
        grid.add(yearField, 1, 0);
        grid.add(new Label("Номер семестра:"), 0, 1);
        grid.add(numField, 1, 1);
        grid.add(new Label("Мин. факультативов:"), 0, 2);
        grid.add(minField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                try {
                    return new SemesterAdmin(0,
                            Integer.parseInt(yearField.getText()),
                            Integer.parseInt(numField.getText()),
                            Integer.parseInt(minField.getText()));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }

    private Dialog<PlanAdmin> createPlanDialog(PlanAdmin existing) {
        Dialog<PlanAdmin> dialog = new Dialog<>();
        dialog.setTitle(existing == null ? "Добавить план" : "Редактировать план");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        ComboBox<ElectiveAdmin> electCombo = new ComboBox<>(electivesList);
        electCombo.setConverter(new javafx.util.StringConverter<ElectiveAdmin>() {
            @Override
            public String toString(ElectiveAdmin object) {
                return object != null ? object.getElectiveName() : "";
            }
            @Override
            public ElectiveAdmin fromString(String string) {
                return null;
            }
        });

        ComboBox<SemesterAdmin> semCombo = new ComboBox<>(semestersList);
        semCombo.setConverter(new javafx.util.StringConverter<SemesterAdmin>() {
            @Override
            public String toString(SemesterAdmin object) {
                return object != null ? object.getYear() + " год, семестр " + object.getSemesterNum() : "";
            }
            @Override
            public SemesterAdmin fromString(String string) {
                return null;
            }
        });

        TextField lecField = new TextField(existing != null ? String.valueOf(existing.getLectureHours()) : "");
        TextField pracField = new TextField(existing != null ? String.valueOf(existing.getPracticeHours()) : "");
        TextField labField = new TextField(existing != null ? String.valueOf(existing.getLabHours()) : "");

        if (existing != null) {
            electCombo.getSelectionModel().select(
                    electivesList.stream()
                            .filter(e -> e.getElectiveId() == existing.getElectiveId())
                            .findFirst().orElse(null)
            );
            semCombo.getSelectionModel().select(
                    semestersList.stream()
                            .filter(s -> s.getSemesterId() == existing.getSemesterId())
                            .findFirst().orElse(null)
            );
        }

        grid.add(new Label("Факультатив:"), 0, 0);
        grid.add(electCombo, 1, 0);
        grid.add(new Label("Семестр:"), 0, 1);
        grid.add(semCombo, 1, 1);
        grid.add(new Label("Лекции (часы):"), 0, 2);
        grid.add(lecField, 1, 2);
        grid.add(new Label("Практики (часы):"), 0, 3);
        grid.add(pracField, 1, 3);
        grid.add(new Label("Лабы (часы):"), 0, 4);
        grid.add(labField, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                try {
                    ElectiveAdmin elect = electCombo.getValue();
                    SemesterAdmin sem = semCombo.getValue();
                    if (elect != null && sem != null) {
                        return new PlanAdmin(0,
                                elect.getElectiveId(), elect.getElectiveName(),
                                sem.getSemesterId(), sem.getYear(), sem.getSemesterNum(),
                                Integer.parseInt(lecField.getText()),
                                Integer.parseInt(pracField.getText()),
                                Integer.parseInt(labField.getText()));
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }

    private Dialog<StudentAdmin> createStudentDialog(StudentAdmin existing) {
        Dialog<StudentAdmin> dialog = new Dialog<>();
        dialog.setTitle(existing == null ? "Добавить студента" : "Редактировать студента");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField surnameField = new TextField(existing != null ? existing.getSurname() : "");
        TextField nameField = new TextField(existing != null ? existing.getName() : "");
        TextField patronymicField = new TextField(existing != null ? existing.getPatronymic() : "");
        TextField addressField = new TextField(existing != null ? existing.getAddress() : "");
        TextField phoneField = new TextField(existing != null ? existing.getPhone() : "");

        grid.add(new Label("Фамилия:"), 0, 0);
        grid.add(surnameField, 1, 0);
        grid.add(new Label("Имя:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Отчество:"), 0, 2);
        grid.add(patronymicField, 1, 2);
        grid.add(new Label("Адрес:"), 0, 3);
        grid.add(addressField, 1, 3);
        grid.add(new Label("Телефон:"), 0, 4);
        grid.add(phoneField, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return new StudentAdmin(0,
                        surnameField.getText(),
                        nameField.getText(),
                        patronymicField.getText(),
                        addressField.getText(),
                        phoneField.getText());
            }
            return null;
        });

        return dialog;
    }

    private Dialog<GradeAdmin> createGradeDialog(GradeAdmin existing) {
        Dialog<GradeAdmin> dialog = new Dialog<>();
        dialog.setTitle(existing == null ? "Добавить оценку" : "Редактировать оценку");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        ComboBox<EnrollmentAdmin> enrollCombo = new ComboBox<>(enrollmentsList);
        enrollCombo.setConverter(new javafx.util.StringConverter<EnrollmentAdmin>() {
            @Override
            public String toString(EnrollmentAdmin object) {
                return object != null ? object.getStudentName() + " - " + object.getElectiveName() : "";
            }
            @Override
            public EnrollmentAdmin fromString(String string) {
                return null;
            }
        });

        DatePicker datePicker = new DatePicker(existing != null ? LocalDate.parse(existing.getGradeDate()) : LocalDate.now());
        TextField valueField = new TextField(existing != null ? existing.getGradeValue() : "");

        if (existing != null) {
            enrollCombo.getSelectionModel().select(
                    enrollmentsList.stream()
                            .filter(e -> e.getStudentPlanId() == existing.getStudentPlanId())
                            .findFirst().orElse(null)
            );
        }

        grid.add(new Label("Запись студента:"), 0, 0);
        grid.add(enrollCombo, 1, 0);
        grid.add(new Label("Дата:"), 0, 1);
        grid.add(datePicker, 1, 1);
        grid.add(new Label("Оценка:"), 0, 2);
        grid.add(valueField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                EnrollmentAdmin enroll = enrollCombo.getValue();
                if (enroll != null && !valueField.getText().isEmpty()) {
                    return new GradeAdmin(0,
                            enroll.getStudentPlanId(),
                            enroll.getStudentName(),
                            enroll.getElectiveName(),
                            datePicker.getValue().toString(),
                            valueField.getText());
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
}
