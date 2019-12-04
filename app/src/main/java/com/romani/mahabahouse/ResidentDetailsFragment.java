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
import android.widget.TextView;
import android.widget.Toast;

import com.romani.mahabahouse.models.MDB;
import com.romani.mahabahouse.models.Resident;


public class ResidentDetailsFragment extends Fragment
{

    TextView ID , name, mobile, room, address, nationalNumber, birthdate, mail, parentMobile, college, university, collegeYear, checkIn, checkOut;
    Button backToMainFragment , edit , previous , next;
    String residentID;
    Resident resident = new Resident();
    MDB mdb = MDB.getInstance(this.getContext());
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_resident_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        residentID  = getArguments().getString("ID");
        resident = mdb.getResidentDao().getResidentByID(residentID);
        setViews();

        backToMainFragment.setOnClickListener( e -> navController.navigate(R.id.action_residentDetailsFragment_to_mainFragment));
        edit.setOnClickListener( e ->
        {
            Bundle bundle = new Bundle();
            bundle.putString("ID",resident.getResidentID() + "");
            bundle.putString("viewDetails" , "edit");
            navController.navigate(R.id.action_residentDetailsFragment_to_addNewResidentFragment , bundle);
        });

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

    public void setViews()
    {
        ID.setText(resident.getResidentID()+"");
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
        ID = view.findViewById(R.id.ID_TXTView);
        name = view.findViewById(R.id.name_TXTView);
        mobile = view.findViewById(R.id.mobile_TXTView);
        room = view.findViewById(R.id.room_TXTView);
        address = view.findViewById(R.id.address_TXTView);
        nationalNumber = view.findViewById(R.id.nationalNumber_TXTView);
        birthdate = view.findViewById(R.id.birthdate_TXTView);
        mail = view.findViewById(R.id.mail_TXTView);
        parentMobile = view.findViewById(R.id.parentMobile_TXTView);
        college = view.findViewById(R.id.college_TXTView);
        university = view.findViewById(R.id.university_TXTView);
        collegeYear = view.findViewById(R.id.collegeYear_TXTView);
        checkIn = view.findViewById(R.id.checkIn_TXTView);
        checkOut = view.findViewById(R.id.checkOut_TXTView);

        backToMainFragment = view.findViewById(R.id.backToMainFragmentBTN);
        edit = view.findViewById(R.id.goToAddNewResidentFragmentBTN);

        next = view.findViewById(R.id.nextResidentBTN);
        previous = view.findViewById(R.id.previousResidentBTN);

        navController = Navigation.findNavController(view);
    }

}
