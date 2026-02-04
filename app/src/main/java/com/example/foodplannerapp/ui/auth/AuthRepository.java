package com.example.foodplannerapp.ui.auth;

import com.google.firebase.auth.FirebaseAuth;

public class AuthRepository {
    private FirebaseAuth auth;

    public AuthRepository() {
        auth = FirebaseAuth.getInstance();
    }

    public void login(String email, String password, AuthCallback authCallback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        authCallback.onSuccess();
                    } else {
                        authCallback.onFailure(task.getException().getMessage());
                    }
                });


    }

    public void register(String email, String password, AuthCallback authCallback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        authCallback.onSuccess();
                    } else {
                        authCallback.onFailure(task.getException().getMessage());
                    }
                });


    }

    public void resetPassword(String email, AuthCallback callback){

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful()){
                        callback.onSuccess();
                    }else{
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    public interface AuthCallback {
        void onSuccess();

        void onFailure(String message);
    }

}
