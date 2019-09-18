package com.example.roomdatabaseschoolinfo;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentDetailsFragment extends Fragment {

    // create a variable student Details Fragment.
    private ImageView stdIamgeView;
    private TextView nameStd, rollStd,totalStd, classStd, addressStd,spinStd;


    public StudentDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_details, container, false);
    }




    // view Return then View inflate korar jonno
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stdIamgeView  = view.findViewById(R.id.imageView_details_id);
        nameStd  = view.findViewById(R.id.textStdName_details_id);
        rollStd  = view.findViewById(R.id.textStdRoll_details_id);
        totalStd  = view.findViewById(R.id.textStdTotal_details_id);
        classStd  = view.findViewById(R.id.textStdClass_details_id);
        addressStd  = view.findViewById(R.id.textStdAddress_details_id);
        spinStd  = view.findViewById(R.id.textStdAddress_spinner_id);

        // bundle null theke jabe.
        Bundle bundle = getArguments();

        if (bundle != null){
            Student student = (Student) bundle.getSerializable("student");
            //stdIamgeView.setImageResource(student.getImage());
            nameStd.setText("Name: "+student.getStudentName());
            rollStd.setText("Roll: "+String.valueOf(student.getStudentRoll()));
            totalStd.setText("Total student: "+String.valueOf(student.getStudentTotal()));
            classStd.setText("Class: "+student.getStudentClass());
            addressStd.setText("Address: "+student.getStudentAddress());
            spinStd.setText(student.getStudentClassSelectSpin());
            stdIamgeView.setImageResource(student.getImage());

        }


    }

    // create a interface go to main Activity with any Fragment.
    public interface StudentItemClickListner{

        // parameter hisabe pass korsi akta studer er object jetate click koroa hoise
        void onStudentItemCick(Student student);

        void onStudentItemEditButtonClicked(long id);
    }
}
