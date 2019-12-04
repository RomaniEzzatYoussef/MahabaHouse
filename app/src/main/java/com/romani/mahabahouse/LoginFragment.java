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

import com.romani.mahabahouse.models.MDB;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment
{
    Button loginButton;
    TextView errrorMessage , signUp;
    EditText username , password;
    MDB mdb;

    NavController navController = null;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mdb = MDB.getInstance(this.getContext());

        loginButton = view.findViewById(R.id.btn_login);
        username = view.findViewById(R.id.input_username);
        password = view.findViewById(R.id.input_password);
        errrorMessage = view.findViewById(R.id.error_message);
        signUp = view.findViewById(R.id.link_signup);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isUsername())
                {
                    if(isPassword())
                    {
                        Bundle bundle = new Bundle();

                        bundle.putString("userNameArg", username.getText().toString() + "");
                        navController.navigate(R.id.action_loginFragment_to_mainFragment , bundle);
                    }

                    else
                    {
                        errrorMessage.setText("password wrong , Please try again!");
                    }
                }

                else
                {
                    errrorMessage.setText("username wrong , Please try again!");
                }
            }
        });
    }

    public boolean isUsername()
    {
        ArrayList<String> usernames = (ArrayList<String>) mdb.getUserDao().getAllUsersUsername();
        for (int i = 0; i < usernames.size(); i++)
        {
            if (username.getText().toString().equals(usernames.get(i)))
                return true;

            System.out.println(usernames.get(i));
        }

        return false;
    }

    public boolean isPassword()
    {
        ArrayList<String> passwords = (ArrayList<String>) mdb.getUserDao().getAllUsersPassword();
        for (int i = 0; i < passwords.size(); i++)
        {
            if (password.getText().toString().equals(passwords.get(i)))
                return true;

            System.out.println(passwords.get(i));
        }

        return false;
    }
}
