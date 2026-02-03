package com.example.foodplannerapp.ui.auth.login;

public interface LoginContract {
    interface View{
        void showLoading();
        void hideLoading();
        void showError(String message);
        void navigateToHome();
    }
    interface Presenter{
        void login(String email, String password);
    }
}
