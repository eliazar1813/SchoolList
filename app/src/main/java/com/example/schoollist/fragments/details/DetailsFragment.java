package com.example.schoollist.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.example.schoollist.R;
import com.example.schoollist.models.SatScoreModel;
import com.example.schoollist.models.SchoolModel;

import java.util.HashMap;

public class DetailsFragment extends Fragment {

    SchoolModel currentSchool;
    SatScoreModel satScoreModel;
    TextView mathScore;
    TextView writeScore;
    TextView readScore;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        DetailsViewModel detailsViewModel = DetailsViewModel.getInstance();
        View view = inflater.inflate(R.layout.school_details_layout, container, false);

        currentSchool = detailsViewModel.getCurrentSchool();

        TextView email = view.findViewById(R.id.schoolEmail);
        email.setText(currentSchool.getSchool_email());

        TextView schoolName = view.findViewById(R.id.schoolName);
        schoolName.setText(currentSchool.getSchool_name());

        TextView location = view.findViewById(R.id.location);
        location.setText(currentSchool.getLocation());

        TextView schoolPhone = view.findViewById(R.id.school_phone);
        schoolPhone.setText(currentSchool.getPhone_number());


        TextView webSite = view.findViewById(R.id.web_site);
        webSite.setText(currentSchool.getPhone_number());

        TextView borough = view.findViewById(R.id.borough);
        borough.setText(currentSchool.getBorough());

        mathScore = view.findViewById(R.id.mathSatScore);
        writeScore = view.findViewById(R.id.writeSatScore);
        readScore = view.findViewById(R.id.readSatScore);


        detailsViewModel.getSatScores().observe(getViewLifecycleOwner(), new Observer<HashMap<String, SatScoreModel>>() {
            @Override
            public void onChanged(HashMap<String, SatScoreModel> stringSatScoreModelHashMap) {

                if(stringSatScoreModelHashMap.containsKey(currentSchool.getDbn())){
                    satScoreModel = stringSatScoreModelHashMap.get(currentSchool.getDbn());
                    mathScore.setText(satScoreModel.getSat_Math_Avg());
                    writeScore.setText(satScoreModel.getSat_write_Avg());
                    readScore.setText(satScoreModel.getSat_read_Avg());
                }
            }
        });


        return view;
    }
}
