package com.example.foodplannerapp.ui.auth.register;

public interface RegisterContract {
    interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void navigateToHome();
    }
    interface Presenter {
        void register(String email, String password, String confirmPassword);
    }
}
