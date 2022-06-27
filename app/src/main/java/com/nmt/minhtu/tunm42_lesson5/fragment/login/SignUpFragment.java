package com.nmt.minhtu.tunm42_lesson5.fragment.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nmt.minhtu.tunm42_lesson5.R;
import com.nmt.minhtu.tunm42_lesson5.fragment.login.LoginFragment;


public class SignUpFragment extends Fragment {
    private EditText edtUserName, edtPassword;
    private Button btnCreatAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        btnCreatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAccount();
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_main, new LoginFragment())
                        .commit();
            }
        });
    }

    private void initView(View view){
        edtUserName = view.findViewById(R.id.edt_userName);
        edtPassword = view.findViewById(R.id.edt_pass);
        btnCreatAccount = view.findViewById(R.id.btn_createAccount);
    }
    private void saveAccount(){
        String userName = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();

        if(!userName.equals("") && !password.equals("")){
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user_name", userName);
            editor.putString("password",password);
            editor.commit();
            Toast.makeText(getContext(),"Tạo tài khoản thành công",Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getContext(),"Điền đủ thông tin!",Toast.LENGTH_LONG).show();
        }
    }
}