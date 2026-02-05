package com.example.foodplannerapp.ui.auth.forgetPassword;

public interface ForgotPasswordContract {
    interface View {
        void showLoading();
        void hideLoading();
        void showMessage(String message);
        void onResetSuccess();
    }

    interface Presenter {
        void resetPassword(String email);
    }
}
