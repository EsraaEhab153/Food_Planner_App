package com.example.foodplannerapp.ui.auth.forgetPassword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplannerapp.R;

public class ForgetPasswordFragment extends Fragment implements ForgotPasswordContract.View{

    EditText emailEdt;
    ForgetPasswordPresenter presenter;
    TextView backToLogin;

    public ForgetPasswordFragment() {
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
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        presenter = new ForgetPasswordPresenter(this);

        emailEdt = view.findViewById(R.id.reset_password_email);
        Button resetBtn = view.findViewById(R.id.btn_reset_password);
        backToLogin = view.findViewById(R.id.tv_back_to_login);


        resetBtn.setOnClickListener(v -> {
            presenter.resetPassword(emailEdt.getText().toString());
        });

        backToLogin.setOnClickListener(v->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_forgot_to_login)
                );
        return view;
    }

    @Override
    public void showLoading() {
        Toast.makeText(getContext(),"Sending email...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResetSuccess() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_forgot_to_login);
    }
}