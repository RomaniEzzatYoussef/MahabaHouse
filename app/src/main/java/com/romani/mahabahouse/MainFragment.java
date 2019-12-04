package com.romani.mahabahouse;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.romani.mahabahouse.models.MDB;
import com.romani.mahabahouse.models.Resident;
import com.romani.mahabahouse.models.ResidentAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment
{
    EditText searchByResidentName , searchByRoom;
    RecyclerView recyclerView;
    ArrayList<Resident> residents = new ArrayList<>();
    ProgressBar progressBar;
    MDB mdb;
    View view;
    FloatingActionButton gotoAddResidentFragment;
    Button refreshBTN , logoutBTN;
    NavController navController = null;

    TextView userN;
    String fullName = "";
    String userName = "";
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.residents_recyclerView);
        searchByResidentName = view.findViewById(R.id.searchByResidentName);
        searchByRoom = view.findViewById(R.id.searchByResidentRoom);
        progressBar = view.findViewById(R.id.myProgress);
        gotoAddResidentFragment = view.findViewById(R.id.goToAddNewResidentFragmentButton);
        refreshBTN = view.findViewById(R.id.refresh_BTN);
        logoutBTN = view.findViewById(R.id.logoutBTN);
        navController = Navigation.findNavController(view);

        this.view = view;
        userN = view.findViewById(R.id.userName);
        userName  = getArguments().getString("userNameArg");
        fullName  = getArguments().getString("fullNameArg");
        userN.setText(userName);

        mdb = MDB.getInstance(this.getContext());
        bindRecycler();

        searchByResidentName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                residents = (ArrayList<Resident>) mdb.getResidentDao().getResidentByName(searchByResidentName.getText().toString());
                bindRecycler();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchByRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                residents = (ArrayList<Resident>) mdb.getResidentDao().getResidentByRoom(searchByRoom.getText().toString());
                bindRecycler();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        gotoAddResidentFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Bundle bundle = new Bundle();
                bundle.putString("viewDetails", "new");
                navController.navigate(R.id.action_mainFragment_to_addNewResidentFragment , bundle);
            }
        });


        refreshBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                residents = (ArrayList<Resident>) mdb.getResidentDao().getAllResidents();
                bindRecycler();
            }
        });

        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                navController.navigate(R.id.action_mainFragment_to_loginFragment);
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        residents = (ArrayList<Resident>) mdb.getResidentDao().getAllResidents();
        bindRecycler();
    }

    public void bindRecycler() {
        ResidentAdapter residentAdapter = new ResidentAdapter(this.getContext() , view ,  residents);
        recyclerView.setAdapter(residentAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
    }


}
