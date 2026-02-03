package com.example.foodplannerapp.ui.auth.login;

import com.example.foodplannerapp.ui.auth.AuthRepository;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View view;
    private AuthRepository authRepository;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
        authRepository = new AuthRepository();
    }
    @Override
    public void login(String email, String password) {
        if(email.isEmpty() || password.isEmpty()) {
            view.showError("Please fill all fields");
            return;
        }
        view.showLoading();
        authRepository.login(email, password, new AuthRepository.AuthCallback() {
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
