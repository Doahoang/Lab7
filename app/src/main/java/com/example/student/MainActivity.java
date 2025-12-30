package com.example.student;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtEmail;
    Button btnAdd;
    RecyclerView recyclerView;

    DatabaseHandler db;
    StudentAdapter adapter;
    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recycler);

        // Database
        db = new DatabaseHandler(this);

        // RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();

        // Thêm sinh viên
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty()) {
                    db.addStudent(new Student(name, email));
                    edtName.setText("");
                    edtEmail.setText("");
                    loadData(); // reload
                }
            }
        });
    }

    // Load lại dữ liệu
    private void loadData() {
        studentList = db.getAllStudents();
        adapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(adapter);
    }
}
