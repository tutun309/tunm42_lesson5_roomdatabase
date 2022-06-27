package com.nmt.minhtu.tunm42_lesson5.fragment.employee_management;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nmt.minhtu.tunm42_lesson5.Employee;
import com.nmt.minhtu.tunm42_lesson5.IClickEmployee;
import com.nmt.minhtu.tunm42_lesson5.R;
import com.nmt.minhtu.tunm42_lesson5.adapter.EmployeeListAdapter;
import com.nmt.minhtu.tunm42_lesson5.database.EmployeeDatabase;

import java.util.List;

public class HomeFragment extends Fragment {
    private FloatingActionButton btnAdd;
    private RecyclerView rcvListEmployee;
    private List<Employee> employeeList;
    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        employeeList = EmployeeDatabase.getInstance(getContext()).employeeDAO().getListEmployee();
        EmployeeListAdapter adapter = new EmployeeListAdapter(employeeList, new IClickEmployee() {
            @Override
            public void OnClickEmployee(int position) {
                setData(position);
            }
        },getContext());
        rcvListEmployee.setAdapter(adapter);
        rcvListEmployee.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_home,new AddUpdateFragment())
                        .addToBackStack("AddUpdateFragment")
                        .commit();
            }
        });
    }

    private void setData(int position) {

    }

    private void initView(View view){
        btnAdd = view.findViewById(R.id.btn_add);
        rcvListEmployee = view.findViewById(R.id.rcv_list_employee);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}