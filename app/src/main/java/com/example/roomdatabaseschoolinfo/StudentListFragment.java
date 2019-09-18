package com.example.roomdatabaseschoolinfo;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentListFragment extends Fragment {

    // create a variable Recycler view
    private  RecyclerView recyclerView;
    private Context context;
    private StudentAdapter adapter;
    private AddNewStudentListner listner;
    private FloatingActionButton fab;
    private List<Student> students = new ArrayList<>();

    public StudentListFragment() {
        // Required empty public constructor
    }





    // get a context to override onAtach method@Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        listner = (AddNewStudentListner) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false);
    }



  // this is mehtod override to find view by id with return type view-onVreateView.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialize to recycler view.
        recyclerView = view.findViewById(R.id.studentList_recyclerView_id);
        // fab initialize
        fab = view.findViewById(R.id.fab_id);

        students = StudentDatabase.getInstance(context).getStudentDao().getAllStudents();

        // two parameters are want to adapter, # onAtach activity er context, #arraylist er object.
        adapter = new StudentAdapter(context,students);



       // GridLayoutManager gm = new GridLayoutManager(context,2);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(lm);

        recyclerView.setAdapter(adapter);

        // fab listner to student list adding

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // When a button click
                listner.loadnewStudentPage();

            }
        });



    }

    // create a interface to fragment open a Student info class
    public interface AddNewStudentListner{

        void loadnewStudentPage();
    }
}
