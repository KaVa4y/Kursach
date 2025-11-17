package com.kursach.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов
        initViews();

        // Настройка обработчиков кликов
        setupClickListeners();
    }

    private void initViews() {
        editTextUsername = findViewById(R.id.editTextText);
        editTextPassword = findViewById(R.id.editTextText2);
        buttonLogin = findViewById(R.id.button);
        buttonRegister = findViewById(R.id.button2);
    }

    private void setupClickListeners() {
        // Обработчик для кнопки входа
        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (validateInput(username, password)) {
                // В реальном приложении здесь была бы проверка с базой данных
                if (authenticateUser(username, password)) {
                    // Успешный вход - переход на вторую активность
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    // finish(); // Раскомментируйте если хотите закрыть MainActivity
                } else {
                    showToast("Неверные учетные данные");
                }
            }
        });

        // Обработчик для кнопки регистрации
        buttonRegister.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (validateInput(username, password)) {
                // В реальном приложении здесь была бы регистрация в базе данных
                if (registerUser(username, password)) {
                    showToast("Регистрация успешна! Теперь вы можете войти.");
                    clearInputFields();
                } else {
                    showToast("Ошибка регистрации. Пользователь可能 уже существует");
                }
            }
        });
    }

    private boolean validateInput(String username, String password) {
        if (username.isEmpty()) {
            editTextUsername.setError("Введите имя пользователя");
            return false;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Введите пароль");
            return false;
        }

        if (password.length() < 4) {
            editTextPassword.setError("Пароль должен содержать минимум 4 символа");
            return false;
        }

        return true;
    }

    private boolean authenticateUser(String username, String password) {
        // Заглушка для аутентификации
        // В реальном приложении здесь был бы запрос к базе данных
        return username.equals("admin") && password.equals("1234");
    }

    private boolean registerUser(String username, String password) {
        // Заглушка для регистрации
        // В реальном приложении здесь была бы запись в базу данных
        return !username.isEmpty() && !password.isEmpty();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void clearInputFields() {
        editTextUsername.setText("");
        editTextPassword.setText("");
        editTextUsername.requestFocus();
    }
}