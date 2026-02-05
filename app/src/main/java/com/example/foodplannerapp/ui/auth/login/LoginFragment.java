package com.example.foodplannerapp.ui.auth.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplannerapp.MainActivity;
import com.example.foodplannerapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment implements LoginContract.View{
private LoginPresenter presenter;
EditText emailEdt , passwordEdt;
TextInputLayout passwordLayout;
private boolean isPasswordVisible = false;
TextView tvRegisterNow,tvForgetPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
    presenter = new LoginPresenter(this);
    emailEdt = view.findViewById(R.id.login_email);
    passwordEdt = view.findViewById(R.id.login_password);
    Button loginBtn = view.findViewById(R.id.btn_signin);
    passwordLayout = view.findViewById(R.id.password_input_layout);
    tvRegisterNow = view.findViewById(R.id.tv_register_now);
    tvForgetPassword = view.findViewById(R.id.tv_forget_password);
    NavController navController =NavHostFragment.findNavController(this);
        passwordLayout.setEndIconOnClickListener(v -> {

            if (isPasswordVisible) {
                // Hide password
                passwordEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                passwordLayout.setEndIconDrawable(R.drawable.eye_off);
            } else {
                // Show password
                passwordEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                passwordLayout.setEndIconDrawable(R.drawable.eye_on);
            }

            isPasswordVisible = !isPasswordVisible;

            passwordEdt.setSelection(passwordEdt.getText().length());
        });

    loginBtn.setOnClickListener(view1 -> {
        presenter.login(emailEdt.getText().toString(), passwordEdt.getText().toString());

    });

    tvRegisterNow.setOnClickListener(view2 ->
        navController.navigate(R.id.action_login_to_register)
    );

    tvForgetPassword.setOnClickListener(view3 ->
            navController.navigate(R.id.action_login_to_forgot)
            );
        return view;
    }

    @Override
    public void showLoading() {
        Toast.makeText(getContext(),"Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {
Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
      startActivity(new Intent(getActivity(), MainActivity.class));
      requireActivity().finish();
    }
}