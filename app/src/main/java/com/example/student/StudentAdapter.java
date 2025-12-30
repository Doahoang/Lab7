package com.example.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    private List<Student> list;
    private DatabaseHandler db;

    public StudentAdapter(List<Student> list) {
        this.list = list;
    }

    class StudentHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtEmail;
        ImageView btnDelete;

        public StudentHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            db = new DatabaseHandler(itemView.getContext());
        }
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentHolder(v);
    }

    @Override
    public void onBindViewHolder(StudentHolder h, int position) {
        Student s = list.get(position);
        h.txtName.setText(s.getName());
        h.txtEmail.setText(s.getEmail());

        h.btnDelete.setOnClickListener(v -> {
            db.deleteStudent(s.getId());
            list.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
