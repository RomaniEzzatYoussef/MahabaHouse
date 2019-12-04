package com.romani.mahabahouse;


import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.romani.mahabahouse.models.MDB;
import com.romani.mahabahouse.models.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment
{
    TextView loginFragment;
    NavController navController = null;

    EditText fullName , username , password , email ;
    DatePicker brithdate;
    Spinner userType;

    User user = new User();
    MDB mdb;

    Button registerBTN;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mdb = MDB.getInstance(this.getContext());

        fullName = view.findViewById(R.id.input_fullname);
        username = view.findViewById(R.id.input_username);
        password = view.findViewById(R.id.input_password);
        email = view.findViewById(R.id.input_email);
        brithdate = view.findViewById(R.id.datePicker_brithdate);
        userType = view.findViewById(R.id.userTypeSpinner);
        registerBTN = view.findViewById(R.id.btnRegister);


        loginFragment = view.findViewById(R.id.link_to_login);
        navController = Navigation.findNavController(view);

        loginFragment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                setUser();
                mdb.getUserDao().insert(user);

                Bundle bundle = new Bundle();

                bundle.putString("userNameArg",username.getText().toString() + "");
                bundle.putString("fullNameArg", fullName.getText().toString() + "");
                navController.navigate(R.id.action_registerFragment_to_mainFragment , bundle);
            }
        });


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void setUser()
    {
        int   day  = brithdate.getDayOfMonth();
        int   month= brithdate.getMonth();
        int   year = brithdate.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String brithdate = sdf.format(calendar.getTime());

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String loginDate = df.format(Calendar.getInstance().getTime());

        user.setFullName(fullName.getText().toString());
        user.setUserName(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setEmail(email.getText().toString());
        user.setBirthDate(brithdate);
        user.setLoginDate(loginDate);
        user.setUserType(userType.getSelectedItem().toString());
    }

}
