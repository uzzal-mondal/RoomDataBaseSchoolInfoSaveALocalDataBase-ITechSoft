package com.example.roomdatabaseschoolinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity
        implements
        StudentDetailsFragment.StudentItemClickListner,
        StudentListFragment.AddNewStudentListner,
        AddStudentFragment.AddStudentCompleteListner,
        AdapterView.OnItemSelectedListener {


    // create a FragmentManger  class Object to use Transaction.
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fragment manager initialize with call to getSupportFragmentManager.
        fragmentManager = getSupportFragmentManager();
        // new class create Fragment Transaction
        FragmentTransaction ft = fragmentManager.beginTransaction();
        // fragment loading object.
        StudentListFragment studentListFragment = new StudentListFragment();
        // fast adding child view,fragment load,
        ft.add(R.id.fragment_container_framelayout_id, studentListFragment);
        // transaction start
        ft.commit();




    }

    //student Item click Listner Ineterface implements to override mehtod.
    @Override
    public void onStudentItemCick(Student student) {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        // going to Fragment
        StudentDetailsFragment studentDetailsFragment = new StudentDetailsFragment();
        ft.replace(R.id.fragment_container_framelayout_id, studentDetailsFragment);

        // create a Bundle class
        Bundle bundle = new Bundle();
        bundle.putSerializable("student", student);
        // details fragment a set kore dibo.
        studentDetailsFragment.setArguments(bundle);


        // back - come back call
        ft.addToBackStack(null);
        ft.commit();


    }

    @Override
    public void onStudentItemEditButtonClicked(long id) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        AddStudentFragment addStudentFragment = new AddStudentFragment();

        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        addStudentFragment.setArguments(bundle);

        ft.replace(R.id.fragment_container_framelayout_id, addStudentFragment);
        ft.addToBackStack(null);
        ft.commit();

    }


    @Override
    public void loadnewStudentPage() {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        AddStudentFragment addStudentFragment = new AddStudentFragment();
        ft.replace(R.id.fragment_container_framelayout_id, addStudentFragment);
        ft.addToBackStack(null);
        ft.commit();


    }

    @Override
    public void onAddStudentComplete() {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        StudentListFragment studentListFragment = new StudentListFragment();
        ft.replace(R.id.fragment_container_framelayout_id, studentListFragment);
        ft.addToBackStack(null);
        ft.commit();


    }

    @Override
    public void onUpdateStudentComplete() {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        StudentListFragment studentListFragment = new StudentListFragment();
        ft.replace(R.id.fragment_container_framelayout_id, studentListFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}
