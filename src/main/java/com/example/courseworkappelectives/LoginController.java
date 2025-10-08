package com.example.courseworkappelectives;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML private TextField phoneField;
    @FXML private PasswordField passwordField;
    @FXML private TextField regSurnameField;
    @FXML private TextField regNameField;
    @FXML private TextField regPatronymicField;
    @FXML private TextField regAddressField;
    @FXML private TextField regPhoneField;
    @FXML private PasswordField regPasswordField;
    @FXML private Label statusLabel;

    @FXML
    private void handleLogin() {
        String phone = phoneField.getText().trim();
        String password = passwordField.getText();

        if (phone.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Заполните все поля");
            return;
        }

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT user_id, role_id, password FROM users WHERE phone = ?")) {

            stmt.setString(1, phone);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    statusLabel.setText("Неверный номер телефона или пароль");
                    return;
                }

                int userId = rs.getInt("user_id");
                int roleId = rs.getInt("role_id");
                String storedPassword = rs.getString("password");

                if (!storedPassword.equals(password)) {
                    statusLabel.setText("Неверный номер телефона или пароль");
                    return;
                }

                openDashboard(userId, roleId);
            }
        } catch (SQLException e) {
            statusLabel.setText("Ошибка подключения к БД");
        }
    }

    @FXML
    private void handleRegister() {
        String surname = regSurnameField.getText().trim();
        String name = regNameField.getText().trim();
        String patronymic = regPatronymicField.getText().trim();
        String address = regAddressField.getText().trim();
        String phone = regPhoneField.getText().trim();
        String password = regPasswordField.getText();

        if (surname.isEmpty() || name.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Заполните обязательные поля");
            return;
        }

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO users (surname, user_name, patronymic, address, phone, password, role_id) " +
                             "VALUES (?, ?, ?, ?, ?, ?, 1)")) {

            stmt.setString(1, surname);
            stmt.setString(2, name);
            stmt.setString(3, patronymic);
            stmt.setString(4, address);
            stmt.setString(5, phone);
            stmt.setString(6, password);

            stmt.executeUpdate();
            statusLabel.setText("Регистрация успешна!");

        } catch (SQLException e) {
            statusLabel.setText("Ошибка регистрации");
        }
    }

    private void openDashboard(int userId, int roleId) {
        try {
            FXMLLoader loader;
            if (roleId == 1) {
                loader = new FXMLLoader(getClass().getResource("/com/example/appforstudents/student.fxml"));
            } else {
                loader = new FXMLLoader(getClass().getResource("/com/example/appforstudents/admin.fxml"));
            }

            Parent root = loader.load();

            if (roleId == 1) {
                StudentController controller = loader.getController();
                controller.setUserId(userId);
            } else {
                AdminController controller = loader.getController();
                controller.setUserId(userId);
            }

            Stage stage = (Stage) phoneField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}