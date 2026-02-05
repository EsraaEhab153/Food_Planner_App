package com.example.foodplannerapp.ui.auth.forgetPassword;

import com.example.foodplannerapp.ui.auth.AuthRepository;

public class ForgetPasswordPresenter implements ForgotPasswordContract.Presenter{

    private ForgotPasswordContract.View view;
    private AuthRepository repository;

    public ForgetPasswordPresenter(ForgotPasswordContract.View view) {
        this.view = view;
        repository = new AuthRepository();
    }

    @Override
    public void resetPassword(String email) {
        if(email.isEmpty()){
            view.showMessage("Enter your email");
            return;
        }

        view.showLoading();

        repository.resetPassword(email, new AuthRepository.AuthCallback() {

            @Override
            public void onSuccess() {
                view.hideLoading();
                view.showMessage("Reset email sent, please check your inbox");
                view.onResetSuccess();
            }

            @Override
            public void onFailure(String message) {
                view.hideLoading();
                view.showMessage(message);
            }
        });
    }
}
