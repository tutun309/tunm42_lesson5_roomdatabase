package com.nmt.minhtu.tunm42_lesson5.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.nmt.minhtu.tunm42_lesson5.HomeActivity;
import com.nmt.minhtu.tunm42_lesson5.R;


public class LoginFragment extends Fragment {
    private EditText edtUserName, edtPassword;
    private CheckBox cbAccountRemember;
    private Button btnSignIn, btnSignUp;
    private SharedPreferences sharedPreferences;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        autoFillAccount();
        sharedPreferences = getContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        cbAccountRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cbAccountRemember.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("account_remember",true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("account_remember",false);
                    editor.commit();
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_main, new SignUpFragment())
                        .addToBackStack("SignUpFragment")
                        .commit();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void initView(View view) {
        edtUserName = view.findViewById(R.id.edt_userName);
        edtPassword = view.findViewById(R.id.edt_pass);
        cbAccountRemember = view.findViewById(R.id.cb_account);
        btnSignIn = view.findViewById(R.id.btn_signIn);
        btnSignUp = view.findViewById(R.id.btn_signUp);
    }

    private void autoFillAccount() {
        sharedPreferences = getContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        Boolean isAccountRemember = sharedPreferences.getBoolean("account_remember",false);
        String userName = sharedPreferences.getString("user_name", "");
        String password = sharedPreferences.getString("password", "");

        if (isAccountRemember) {
            cbAccountRemember.setChecked(true);
            if (!userName.equals("") && !password.equals("")) {
                edtUserName.setText(userName);
                edtPassword.setText(password);
            }
        }else{
            cbAccountRemember.setChecked(false);
        }
    }

    private void signIn(){
        String name = edtUserName.getText().toString();
        String pass = edtPassword.getText().toString();
        String nameLocal = sharedPreferences.getString("user_name", "");
        String passLocal = sharedPreferences.getString("password", "");

        if(nameLocal.equals("")|| passLocal.equals("")){
            Toast.makeText(getContext(), "Ban chua dang ki!!!!!!",Toast.LENGTH_LONG).show();
            return;
        }

        if (name.equals(nameLocal)
                && pass.equals(passLocal)) {
            Intent intent = new Intent(getContext(), HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        } else {
            Toast.makeText(getContext(), "sai thong tin!!!!!!",Toast.LENGTH_LONG).show();
        }
    }
}