package com.romani.mahabahouse.models;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.romani.mahabahouse.MainFragment;
import com.romani.mahabahouse.R;

import java.util.ArrayList;

public class ResidentAdapter extends RecyclerView.Adapter {

    ArrayList<Resident> residents;
    Context context;
    View view;
    MDB mdb;
    NavController navController = null;

    public ResidentAdapter(Context context, View view , ArrayList<Resident> residents)
    {
        this.context = context;
        this.view = view;
        this.residents = residents;

        mdb = MDB.getInstance(this.context);
    }

    @NonNull
    @Override
    public ResidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View residentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resident_items, parent, false);
        ResidentViewHolder residentViewHolder = new ResidentViewHolder(residentView);
        return residentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position)
    {
        final ResidentViewHolder residentViewHolder = (ResidentViewHolder) holder;
        residentViewHolder.residentID.setText(residents.get(position).getResidentID() + "");
        residentViewHolder.residentName.setText(residents.get(position).getResidentName());
        residentViewHolder.mobile.setText(residents.get(position).getMobile());
        residentViewHolder.room.setText(residents.get(position).getRoom());
        residentViewHolder.college.setText(residents.get(position).getCollege());
        navController = Navigation.findNavController(view);
        residentViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Bundle bundle = new Bundle();

                bundle.putString("ID",residents.get(position).getResidentID() + "");
                navController.navigate(R.id.action_mainFragment_to_residentDetailsFragment , bundle);
            }
        });

        residentViewHolder.residentOptionsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                PopupMenu popup = new PopupMenu(context, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_options, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        Bundle bundle = new Bundle();
                        switch (item.getItemId()) {
                            case R.id.item_edit:


                                bundle.putString("ID",residents.get(position).getResidentID() + "");
                                bundle.putString("viewDetails" , "edit");
                                navController.navigate(R.id.action_mainFragment_to_addNewResidentFragment , bundle);
                                return true;
                            case R.id.item_details:


                                bundle.putString("ID",residents.get(position).getResidentID() + "");
                                navController.navigate(R.id.action_mainFragment_to_residentDetailsFragment , bundle);
                                return true;

                            case R.id.item_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                builder.setTitle("Confirm");
                                builder.setMessage("Are you sure you want to delete resident '" + residentViewHolder.residentName.getText().toString() +"' ?");

                                builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
                                {

                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        // Do nothing but close the dialog
/*
                                        libraryDB.getBookDao().delete(borrowers.get(position));
                                        borrowers.remove(position);
                                        notifyDataSetChanged();

                                        Toast.makeText(context , "Book " + bookViewHolder.bookName.getText().toString() + " Deleted" , Toast.LENGTH_LONG).show();
*/
                                    }
                                });

                                builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
                                {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {

                                        // Do nothing
                                        dialog.dismiss();
                                    }
                                });

                                AlertDialog alert = builder.create();
                                alert.show();

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();




            }
        });
    }

    @Override
    public int getItemCount() {
        return residents.size();
    }

    class ResidentViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout relativeLayout;
        Button residentOptionsBTN;
        TextView residentID , residentName , mobile ,room , college;

        public ResidentViewHolder(@NonNull View itemView)
        {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.item_layout);
            residentOptionsBTN = itemView.findViewById(R.id.resident_options_BTN);
            residentID = itemView.findViewById(R.id.resident_id_TXTView);
            residentName = itemView.findViewById(R.id.resident_name_TXTView);
            mobile = itemView.findViewById(R.id.mobile_text_view);
            room = itemView.findViewById(R.id.room_text_view);
            college = itemView.findViewById(R.id.college_text_view);
        }
    }
}
