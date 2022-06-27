package com.nmt.minhtu.tunm42_lesson5.fragment.employee_management;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nmt.minhtu.tunm42_lesson5.Employee;
import com.nmt.minhtu.tunm42_lesson5.R;
import com.nmt.minhtu.tunm42_lesson5.database.EmployeeDAO;
import com.nmt.minhtu.tunm42_lesson5.database.EmployeeDatabase;

import java.util.Calendar;


public class AddUpdateFragment extends Fragment {
    private EditText edtName, edtPhoneNumber, edtDateOfBirth;
    private Spinner spnDepartment;
    private RadioGroup radioGroupGender;
    private Button btnSave;
    private TextView icBack, txtLabel;

    public AddUpdateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        String[] departments = {"Giám đốc", "Văn  Phòng", "Nhân Sự", "Kỹ Thuật", "Bảo Vệ"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext()
                , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
                , departments);
        spnDepartment.setAdapter(arrayAdapter);

        edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = "";
                        if (month > 8) {
                            date = day + "/" + (month + 1) + "/" + year;
                        } else {
                            date = day + "/" + "0" + (month + 1) + "/" + year;
                        }
                        edtDateOfBirth.setText(date);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployee();

            }
        });

        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void addEmployee() {
        String name = edtName.getText().toString();
        String phoneNumber = edtPhoneNumber.getText().toString();
        String dateOfBirth = edtDateOfBirth.getText().toString();
        String department = spnDepartment.getSelectedItem().toString();
        int id = radioGroupGender.getCheckedRadioButtonId();
        int gender = 0;
        if (id == R.id.radio_female) {
            gender = 1;
        }

        if (name.equals("") || phoneNumber.equals("") || dateOfBirth.equals("")
                || dateOfBirth.equals("")) {
            Toast.makeText(getContext(), "Điền đủ thông tin!!!", Toast.LENGTH_LONG).show();
        } else {
            EmployeeDatabase.getInstance(getContext()).employeeDAO().addEmployee(
                    new Employee(R.drawable.icon_person, name, phoneNumber, dateOfBirth, department, gender));
        }
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_home, getParentFragmentManager().findFragmentByTag("HomeFragment"))
                .commit();
    }

    private void initView(View view) {
        edtName = view.findViewById(R.id.edt_userName);
        edtPhoneNumber = view.findViewById(R.id.edt_phone_number);
        edtDateOfBirth = view.findViewById(R.id.edt_date);
        radioGroupGender = view.findViewById(R.id.radio_gr_gender);
        spnDepartment = view.findViewById(R.id.spn_department);
        btnSave = view.findViewById(R.id.btn_save);
        icBack = view.findViewById(R.id.ic_back);
        txtLabel = view.findViewById(R.id.txt_add_employee);
    }

    private void setDataToUpdate(Employee employee) {
        edtName.setText(employee.getName());
        edtDateOfBirth.setText(employee.getDateOfBirth());
        edtPhoneNumber.setText(employee.getPhoneNumber());
        if (employee.getGender() == 0) {
            radioGroupGender.check(R.id.radio_male);
        } else {
            radioGroupGender.check(R.id.radio_female);
        }
    }

    private void updateEmployee(Employee employee) {
        String name = edtName.getText().toString();
        String phoneNumber = edtPhoneNumber.getText().toString();
        String dateOfBirth = edtDateOfBirth.getText().toString();
        String department = spnDepartment.getSelectedItem().toString();
        int id = radioGroupGender.getCheckedRadioButtonId();
        int gender = 0;
        if (id == R.id.radio_female) {
            gender = 1;
        }

        if (name.equals("") || phoneNumber.equals("") || dateOfBirth.equals("")
                || dateOfBirth.equals("")) {
            Toast.makeText(getContext(), "Điền đủ thông tin!!!", Toast.LENGTH_LONG).show();
        } else {
            employee.setName(name);
            employee.setPhoneNumber(phoneNumber);
            employee.setDateOfBirth(dateOfBirth);
            employee.setGender(gender);
            employee.setDepartment(department);
            EmployeeDatabase.getInstance(getContext()).employeeDAO().updateEmployee(employee);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            txtLabel.setText("sửa nhân viên");
            Employee employee = EmployeeDatabase.getInstance(getContext()).employeeDAO().getEmployeeById(bundle.getInt("id_employee"));
            if (employee != null) {
                setDataToUpdate(employee);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateEmployee(employee);
                        getParentFragmentManager().popBackStack();
                    }
                });
            }
        } else {
            Log.d("TAG", "onResume: " + "nullllllllll");
        }
    }
}