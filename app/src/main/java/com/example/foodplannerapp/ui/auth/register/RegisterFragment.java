package com.example.foodplannerapp.ui.auth.register;

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

public class RegisterFragment extends Fragment implements RegisterContract.View {
    EditText emailEdt, passwordEdt, confirmPasswordEdt;
    private RegisterPresenter presenter;

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

        registerBtn.setOnClickListener(v->{
            presenter.register(emailEdt.getText().toString(), passwordEdt.getText().toString(), confirmPasswordEdt.getText().toString());
        });

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