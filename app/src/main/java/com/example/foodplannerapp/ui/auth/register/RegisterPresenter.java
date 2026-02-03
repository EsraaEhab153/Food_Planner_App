package com.example.foodplannerapp.ui.auth.register;

import com.example.foodplannerapp.ui.auth.AuthRepository;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private AuthRepository authRepository;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        authRepository = new AuthRepository();
    }

    @Override
    public void register(String email, String password, String confirmPassword) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view.showError("Please fill all fields");
            return;
        }
        if (!password.equals(confirmPassword)) {
            view.showError("Passwords do not match");
            return;
        }
        view.showLoading();
        authRepository.register(email, password, new AuthRepository.AuthCallback() {

            @Override
            public void onSuccess() {
                view.hideLoading();
                view.navigateToHome();
            }

            @Override
            public void onFailure(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }
}
