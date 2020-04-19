package com.example.randomname;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ListStudentViewer> {

    private ArrayList<Student> arrayList;
    private StudentActivity studentActivity;

    public StudentAdapter(StudentActivity studentActivity, ArrayList<Student> arrayList) {
        this.arrayList = arrayList;
        this.studentActivity = studentActivity;
    }

    @NonNull
    @Override
    public ListStudentViewer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view, parent, false);
        return new ListStudentViewer(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListStudentViewer holder, int position) {
        Student student = arrayList.get(position);

        holder.name.setText(student.getName());
        holder.section.setText(student.getSection());
        holder.year.setText(student.getYear());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ListStudentViewer extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView section;
        TextView year;

        public ListStudentViewer(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.viewStudentName);
            section = (TextView) itemView.findViewById(R.id.viewStudentSection);
            year = (TextView) itemView.findViewById(R.id.viewStudentYear);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            return;
        }
    }
}