package com.example.roomdatabaseschoolinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    // need to two properties , context with studentList.
    private Context context;
    private List<Student> studentList;
    private StudentDetailsFragment.StudentItemClickListner listner;

    // create a constructor
    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
        listner = (StudentDetailsFragment.StudentItemClickListner) context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a Layout inflater.
        View view = LayoutInflater.from(context).inflate(R.layout.student_row_recycle_layout, parent, false);
        StudentViewHolder holder = new StudentViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, final int position) {


        // declare to final 
        final Student student = studentList.get(position);

        // data set ..

        holder.textClassNameStdTv.setText("Class: " + studentList.get(position).getStudentClass());
        holder.textTotalNameStdTv.setText("Roll: " + String.valueOf(studentList.get(position).getStudentTotal()));
        // holder.textTotalNameStdTv.setText(Integer.parseInt(studentList.get(position).getStudentTotal()));
        holder.imageView.setImageResource(R.drawable.school);

        // image that is fixed..
        holder.imageView.setImageResource(R.drawable.school);

        //animation
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // this is position in this method..


                // listner can get and click to student.
                listner.onStudentItemCick(student);


            }
        });


        // menu text setup in OnClickListner..

        holder.menuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.student_row_menu, popupMenu.getMenu());
                popupMenu.show();

                // popupmenu setOnMenuItemClickListner create.,,
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        // menu handaling to switch case use id
                        switch (item.getItemId()) {


                            case R.id.row_itemDelete_id:
                                // long id = studentList.get(position).getStudentID();
                                int deletedRow = StudentDatabase.getInstance(context)
                                        .getStudentDao()
                                        .deleteStudent(student);
                                if (deletedRow > 0) {
                                    Toast.makeText(context, " deleted", Toast.LENGTH_SHORT).show();
                                    // collection theke data del korte hobe.
                                    studentList.remove(student);
                                    notifyDataSetChanged();
                                }
                                break;

                            case R.id.row_itemEdit_id:
                                long id = student.getStudentID();
                                listner.onStudentItemEditButtonClicked(id);

                                break;


                        }

                        return false;
                    }
                });


                Toast.makeText(context, "menu click", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        // want a list
        return studentList.size();
    }


    // create a inner class.
    public class StudentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textClassNameStdTv, textTotalNameStdTv, menuTv;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            // create to findViewById with itemView
            imageView = itemView.findViewById(R.id.image_sdtRow_id);
            textClassNameStdTv = itemView.findViewById(R.id.textClassName_sdtRow_id);
            textTotalNameStdTv = itemView.findViewById(R.id.textTotalStudent_sdtRow_id);
            menuTv = itemView.findViewById(R.id.rowMenu_id);


        }
    }

    public interface StudentItemClickListener {

        // declare to now abstract method.
        void onStudentItemClicked(Student student);

        void onStudentItemEditButtonClicked(long id);


    }
}
