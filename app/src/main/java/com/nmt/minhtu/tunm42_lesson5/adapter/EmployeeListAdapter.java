package com.nmt.minhtu.tunm42_lesson5.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nmt.minhtu.tunm42_lesson5.Employee;
import com.nmt.minhtu.tunm42_lesson5.IClickEmployee;
import com.nmt.minhtu.tunm42_lesson5.R;
import com.nmt.minhtu.tunm42_lesson5.database.EmployeeDatabase;
import com.nmt.minhtu.tunm42_lesson5.fragment.employee_management.AddUpdateFragment;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {
    private List<Employee> employeeList;
    private IClickEmployee iClickEmployee;
    private Context context;

    public EmployeeListAdapter(List<Employee> employeeList, IClickEmployee iClickEmployee, Context context) {
        this.employeeList = employeeList;
        this.iClickEmployee = iClickEmployee;
        this.context = context;
    }

    public EmployeeListAdapter(List<Employee> employeeList, IClickEmployee iClickEmployee) {
        this.employeeList = employeeList;
        this.iClickEmployee = iClickEmployee;
    }

    public EmployeeListAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setListEmployee(List<Employee> employeeList){
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

//    public void removeEmployeeFromList(Employee employee)

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_employee,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.txtName.setText(employee.getName());
        holder.txtDepartment.setText(employee.getDepartment());
        holder.txtOtherInfo.setText(employee.getGenderString()+"/"+employee.getAge()+" tuổi/"+employee.getPhoneNumber());
        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmployeeDatabase.getInstance(view.getContext()).employeeDAO().removeEmployee(employee);
                employeeList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });

        holder.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity compatActivity = (AppCompatActivity) view.getContext();

                Bundle bundle = new Bundle();
                bundle.putInt("id_employee",employee.getId());
                Fragment updateFragment = new AddUpdateFragment();
                updateFragment.setArguments(bundle);

                compatActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_home,updateFragment)
                        .addToBackStack("AddUpdateFragment")
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    protected class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtDepartment, txtOtherInfo,txtDelete, txtEdit;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtDepartment = itemView.findViewById(R.id.txt_department);
            txtOtherInfo = itemView.findViewById(R.id.txt_info);
            txtDelete = itemView.findViewById(R.id.txt_remove);
            txtEdit = itemView.findViewById(R.id.txt_edit);
        }
    }
}
