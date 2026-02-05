package com.example.foodplannerapp.ui.auth.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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

public class RegisterFragment extends Fragment implements RegisterContract.View {
    EditText emailEdt, passwordEdt, confirmPasswordEdt;
    private RegisterPresenter presenter;
    TextInputLayout registerPasswordLayout,confirmPasswordLayout;
    private boolean isPasswordVisible = false;
    TextView loginNow;
    public RegisterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        presenter = new RegisterPresenter(this);
        emailEdt = view.findViewById(R.id.register_email);
        passwordEdt = view.findViewById(R.id.register_password);
        confirmPasswordEdt = view.findViewById(R.id.register_confirm_password);
        Button registerBtn = view.findViewById(R.id.btn_signup);
        registerPasswordLayout = view.findViewById(R.id.register_password_layout);
        confirmPasswordLayout = view.findViewById(R.id.confirm_password_layout);
        NavController navController = NavHostFragment.findNavController(this);
        loginNow = view.findViewById(R.id.tv_login_now);

        registerPasswordLayout.setEndIconOnClickListener(v -> {

            if (isPasswordVisible) {
                // Hide password
                passwordEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                registerPasswordLayout.setEndIconDrawable(R.drawable.eye_off);
            } else {
                // Show password
                passwordEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                registerPasswordLayout.setEndIconDrawable(R.drawable.eye_on);
            }

            isPasswordVisible = !isPasswordVisible;

            passwordEdt.setSelection(passwordEdt.getText().length());
        });

        confirmPasswordLayout.setEndIconOnClickListener(v -> {

            if (isPasswordVisible) {
                // Hide password
                confirmPasswordEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                confirmPasswordLayout.setEndIconDrawable(R.drawable.eye_off);
            } else {
                // Show password
                confirmPasswordEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                confirmPasswordLayout.setEndIconDrawable(R.drawable.eye_on);
            }

            isPasswordVisible = !isPasswordVisible;

            confirmPasswordEdt.setSelection(confirmPasswordEdt.getText().length());
        });

        registerBtn.setOnClickListener(v->{
            presenter.register(emailEdt.getText().toString(), passwordEdt.getText().toString(), confirmPasswordEdt.getText().toString());
        });

        loginNow.setOnClickListener(v->
              navController.navigate(R.id.action_register_to_login)
                );

        return view;
    }

    @Override
    public void showLoading() {
        Toast.makeText(getContext(),"Registering...", Toast.LENGTH_SHORT).show();
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