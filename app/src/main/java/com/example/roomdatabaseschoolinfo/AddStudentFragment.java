package com.example.roomdatabaseschoolinfo;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddStudentFragment extends Fragment  {

    private EditText nameAddEt,rollAddEt,totalAddEt,classAddEt, addressAdEt;
   // private Spinner selectAd;
    private Button addStudentButton,updateStudentButton;
    private String showSpinner;

    private Spinner spinner;
    private Context context;
    private long id = 0;
    private AddStudentCompleteListner listner;





    public AddStudentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        listner = (AddStudentCompleteListner) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameAddEt = view.findViewById(R.id.editText_name_stdAdd_id);
        rollAddEt = view.findViewById(R.id.editText_roll_stdAdd_id);
        totalAddEt = view.findViewById(R.id.editText_total_stdAdd_id);
        classAddEt = view.findViewById(R.id.editText_class_stdAdd_id);
        addressAdEt = view.findViewById(R.id.editText_address_stdAdd_id);

        // selectAd = view.findViewById(R.id.)
        addStudentButton = view.findViewById(R.id.button_stdAdd_id);
        updateStudentButton = view.findViewById(R.id.button_stdUp_id);

        //spinner = view.findViewById(R.id.editText_classText_stdAdd_id);


        // spinner initialize..
        spinner = view.findViewById(R.id.editText_spinner_stdAdd_id);
       // spinner.setOnItemClickListener((AdapterView.OnItemClickListener) context);


        List<String> categories = new ArrayList<>();
        categories.add(0,"Select Class");
        categories.add("Class 1");
        categories.add("Class 2");
        categories.add("Class 3");
        categories.add("Class 4");
        categories.add("Class 5");

        //style the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(context,android.R.layout.simple_spinner_item,categories);


        // Dropdown layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // ataching data adapter to spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if(parent.getItemAtPosition(position).equals("Select Class")){

                    // do nothing
                }else{

                    showSpinner = parent.getItemAtPosition(position).toString();
                   // Student student = new Student(id);





                    Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinner.setAdapter(adapter);



        nameAddEt.requestFocus();

        // click to add button and get Text to data..

        // edit jonno ashsi.
        Bundle bundle = getArguments();
        if (bundle != null){

          addStudentButton.setVisibility(View.GONE);
          updateStudentButton.setVisibility(View.VISIBLE);
          this.id = bundle.getLong("id");

           //    long id = bundle.getLong("id");
            Student student = StudentDatabase.getInstance(context)
                    .getStudentDao()
                    .getStudentById(id);


            // data set text now in the Room Data base storage..

            if (student != null){

                nameAddEt.setText(student.getStudentName());

                //rollAddEt.setText(student.getStudentRoll());
                rollAddEt.setText(String.valueOf(student.getStudentRoll()));
                totalAddEt.setText(String.valueOf(student.getStudentTotal()));

                classAddEt.setText(student.getStudentClass());
                addressAdEt.setText(student.getStudentAddress());




            }
        }




        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameAddEt.getText().toString();
                int roll = Integer.parseInt(rollAddEt.getText().toString());
                int total = Integer.parseInt(totalAddEt.getText().toString());

                String classSd = classAddEt.getText().toString();
                String address = addressAdEt.getText().toString();




                Student student = new Student(name,roll,total,classSd,address,showSpinner);


               long insertedRowId =  StudentDatabase.getInstance(context).getStudentDao()
                        .inserNewStudent(student);

               if (insertedRowId>0){

                   Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show();
                   listner.onAddStudentComplete();

               }

            }
        });




        updateStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();

                String name = nameAddEt.getText().toString();
                int roll = Integer.parseInt(rollAddEt.getText().toString());
                int total = Integer.parseInt(totalAddEt.getText().toString());
                String classSd = classAddEt.getText().toString();
                String address = addressAdEt.getText().toString();


                Student student = new Student(name,roll,total,classSd,address,showSpinner);
                // this is done to update id .
                student.setStudentID(id);


                int updateRow = StudentDatabase.getInstance(context)
                        .getStudentDao()
                        .updateStudent(student);

                if (updateRow > 0){

                    Toast.makeText(context, "updated "+updateRow, Toast.LENGTH_SHORT).show();
                    listner.onUpdateStudentComplete();
                }

            }
        });

    }





    public interface AddStudentCompleteListner{

        void onAddStudentComplete();
        void onUpdateStudentComplete();
    }
}
