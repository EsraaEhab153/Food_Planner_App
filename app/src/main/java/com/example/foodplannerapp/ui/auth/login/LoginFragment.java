package com.example.foodplannerapp.ui.auth.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplannerapp.MainActivity;
import com.example.foodplannerapp.R;

public class LoginFragment extends Fragment implements LoginContract.View{
private LoginPresenter presenter;
EditText emailEdt , passwordEdt;
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
    loginBtn.setOnClickListener(view1 -> {
        presenter.login(emailEdt.getText().toString(), passwordEdt.getText().toString());

    });

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