package com.example.courseworkappelectives.controllers;

import com.example.courseworkappelectives.models.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

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
                    statusLabel.setText("неверный номер телефона или пароль");
                    return;
                }

                int userId = rs.getInt("user_id");
                int roleId = rs.getInt("role_id");
                String stored = rs.getString("password");
                String inputHash = sha256(password);

                boolean ok = false;
                if (stored != null) {

                    if (stored.equalsIgnoreCase(inputHash)) ok = true;
                    else if (stored.equals(password)) {
                        ok = true;
                        try (PreparedStatement up = conn.prepareStatement(
                                "UPDATE users SET password = ? WHERE user_id = ?")) {
                            up.setString(1, inputHash);
                            up.setInt(2, userId);
                            up.executeUpdate();
                        }
                    }
                }

                if (!ok) {
                    statusLabel.setText("неверный номер телефона или пароль");
                    return;
                }

                openDashboard(userId, roleId);
            }
        } catch (SQLException e) {
            statusLabel.setText("Ошибка подключения к БД");
            e.printStackTrace();
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
                             "VALUES (?, ?, ?, ?, ?, ?, 1)",
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, surname);
            stmt.setString(2, name);
            stmt.setString(3, patronymic);
            stmt.setString(4, address);
            stmt.setString(5, phone);
            stmt.setString(6, sha256(password));

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        int userId = keys.getInt(1);
                        statusLabel.setText("Регистрация успешна!");
                        openDashboard(userId, 1);
                    }
                }
            }
        } catch (SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
                statusLabel.setText("Телефон уже зарегистрирован");
            } else {
                statusLabel.setText("Ошибка регистрации");
                e.printStackTrace();
            }
        }
    }

    private void openDashboard(int userId, int roleId) {
        try {
            FXMLLoader loader;
            if (roleId == 1) {
                loader = new FXMLLoader(getClass().getResource("/com/example/courseworkappelectives/student.fxml"));
            } else {
                loader = new FXMLLoader(getClass().getResource("/com/example/courseworkappelectives/admin.fxml"));
            }

            Parent root = loader.load();

            if (roleId == 1) {
                StudentController controller = loader.getController();
                controller.setUserId(userId);
            } else {
                AdminController controller = loader.getController();
                controller.setUserId(userId);
            }

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/courseworkappelectives/styles.css").toExternalForm());

            Stage stage = (Stage) phoneField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
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
}