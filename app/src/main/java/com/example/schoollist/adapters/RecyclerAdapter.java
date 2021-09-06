package com.example.schoollist.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollist.R;
import com.example.schoollist.models.SchoolModel;
import worker8.com.github.radiogroupplus.RadioGroupPlus;

import java.util.List;

public abstract class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public List<SchoolModel> schoolList;
    Context context;

    public RecyclerAdapter(List<SchoolModel> schoolList){
        this.schoolList = schoolList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new RecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.school_list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.show(schoolList.get(position));
    }

    @Override
    public int getItemCount() {

        if (schoolList == null) return 0;

        return schoolList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView schoolName;
        ImageView moreOptions;
        RadioGroupPlus radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolName = itemView.findViewById(R.id.schoolTextView);
            moreOptions = itemView.findViewById(R.id.options);
        }

        public void show(SchoolModel currentSchool) {
            schoolName.setText(currentSchool.getSchool_name());
            moreOptions.setImageResource(R.drawable.three_dots_icon);

            moreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Creating alter build dialog
                    AlertDialog.Builder dialogBuilder = getAlertBuilder(R.layout.radio_button_layout);

                    dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int choiceOption = radioGroup.getCheckedRadioButtonId();
                            switch (choiceOption){
                                case R.id.radio_button_favorite:
//                                  Add Current School to Favorite viewModel List

                                case R.id.radio_button_accept:

                                case R.id.radio_button_share:

                                default:
                                    //Do nothing
                            }
                        }
                    });

                    dialogBuilder.create().show();
                }
            });
        }

        //Method to generate build Dialog
        public AlertDialog.Builder getAlertBuilder(int layoutResourceId){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = context.getSystemService(LayoutInflater.class);
            View view = inflater.inflate(layoutResourceId, null);
            radioGroup = view.findViewById(R.id.radio_button_list);
            dialogBuilder.setView(view);

            return dialogBuilder;
        }
    }
}
