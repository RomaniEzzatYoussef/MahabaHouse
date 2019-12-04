package com.romani.mahabahouse;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.romani.mahabahouse.models.MDB;
import com.romani.mahabahouse.models.Resident;

public class AddNewResidentFragment extends Fragment
{
    EditText name, mobile, room, address, nationalNumber, birthdate, mail, parentMobile, college, university, collegeYear, checkIn, checkOut;
    Button backToMainFragment , previous , next;
    FloatingActionButton save;
    String viewDetails , residentID;
    Resident resident = new Resident();
    MDB mdb = MDB.getInstance(this.getContext());
    NavController navController;
    TextView title;

    public AddNewResidentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_resident, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);

        viewDetails  = getArguments().getString("viewDetails");
        residentID  = getArguments().getString("ID");

        if (viewDetails ==  "edit")
        {
            resident = mdb.getResidentDao().getResidentByID(residentID);
            title.setText("Edit Resident");

            setViews();
        }

        save.setOnClickListener(e -> saveNewResident(viewDetails));
        backToMainFragment.setOnClickListener( e -> navController.navigate(R.id.action_addNewResidentFragment_to_mainFragment));

        previous.setOnClickListener(e ->
        {
            long currentID = resident.getResidentID();
            if (currentID > 1)
            {
                resident = mdb.getResidentDao().getResidentByID((currentID - 1)+"");
                setViews();
            }

            else
            {
                Toast.makeText(this.getContext() , "This is the First Resident" , Toast.LENGTH_LONG).show();
            }
        });

        next.setOnClickListener(e ->
        {
            long currentID = resident.getResidentID();
            int size = mdb.getResidentDao().getSizeOFResidents();
            if (currentID < size)
            {
                resident = mdb.getResidentDao().getResidentByID((currentID + 1)+"");
                setViews();
            }

            else
            {
                Toast.makeText(this.getContext() , "This is the Last Resident" , Toast.LENGTH_LONG).show();
            }
        });

    }

    public void saveNewResident(String type)
    {
        resident.setResidentName(name.getText().toString());
        resident.setMobile(mobile.getText().toString());
        resident.setRoom(room.getText().toString());
        resident.setAddress(address.getText().toString());
        resident.setNationalNumber(nationalNumber.getText().toString());
        resident.setBirthDate(birthdate.getText().toString());
        resident.setMail(mail.getText().toString());
        resident.setParentMobile(parentMobile.getText().toString());
        resident.setCollege(college.getText().toString());
        resident.setUniversity(university.getText().toString());
        resident.setCollegeYear(collegeYear.getText().toString());
        resident.setCheckIn(checkIn.getText().toString());
        resident.setCheckOut(checkOut.getText().toString());

        if (type == "edit")
            mdb.getResidentDao().update(resident);

        if (type == "new")
            mdb.getResidentDao().insert(resident);

        navController.navigate(R.id.action_addNewResidentFragment_to_mainFragment);
    }

    public void setViews()
    {
        name.setText(resident.getResidentName());
        mobile.setText(resident.getMobile());
        room.setText(resident.getRoom());
        address.setText(resident.getAddress());
        nationalNumber.setText(resident.getNationalNumber());
        birthdate.setText(resident.getBirthDate());
        mail.setText(resident.getMail());
        parentMobile.setText(resident.getParentMobile());
        college.setText(resident.getCollege());
        university.setText(resident.getUniversity());
        collegeYear.setText(resident.getCollegeYear());
        checkIn.setText(resident.getCheckIn());
        checkOut.setText(resident.getCheckOut());
    }

    public void assignViews(View view)
    {
        name = view.findViewById(R.id.name_input);
        mobile = view.findViewById(R.id.mobile_input);
        room = view.findViewById(R.id.room_input);
        address = view.findViewById(R.id.address_input);
        nationalNumber = view.findViewById(R.id.nationalNumber_input);
        birthdate = view.findViewById(R.id.birthdate_input);
        mail = view.findViewById(R.id.mail_input);
        parentMobile = view.findViewById(R.id.parentMobile_input);
        college = view.findViewById(R.id.college_input);
        university = view.findViewById(R.id.university_input);
        collegeYear = view.findViewById(R.id.collegeYear_input);
        checkIn = view.findViewById(R.id.checkIn_input);
        checkOut = view.findViewById(R.id.checkout_input);

        backToMainFragment = view.findViewById(R.id.backToMainFragmentBTN_FANR);
        next = view.findViewById(R.id.nextResidentBTN_FANR);
        previous = view.findViewById(R.id.previousResidentBTN_FANR);

        save = view.findViewById(R.id.saveBTN);
        navController = Navigation.findNavController(view);

        title = view.findViewById(R.id.addNewBook_txtView);
    }
}
