package com.nmt.minhtu.tunm42_lesson5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.nmt.minhtu.tunm42_lesson5.fragment.employee_management.HomeFragment;
import com.nmt.minhtu.tunm42_lesson5.fragment.login.LoginFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_home, new HomeFragment(),"HomeFragment");
        fragmentTransaction.addToBackStack("HomeFragment");
        fragmentTransaction.commit();
    }
}