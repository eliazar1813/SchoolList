package com.example.schoollist.fragments.accepted;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollist.R;
import com.example.schoollist.adapters.AcceptedAdapterList;
import com.example.schoollist.models.SchoolModel;

import java.util.List;

public class AcceptedFragment extends Fragment {

    AcceptedAdapterList adapter;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    View view;
    TextView emptyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AcceptedViewModel acceptedViewModel = AcceptedViewModel.getInstance();
        view = inflater.inflate(R.layout.accepted_dashboard, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        emptyText = view.findViewById(R.id.emptyText);
        emptyText.setText("Accepted list is empty");

        acceptedViewModel.init();
        adapter = new AcceptedAdapterList(acceptedViewModel.getSchoolList().getValue());
        recyclerView = view.findViewById(R.id.acceptedRecyclerView);
        recyclerView.setAdapter(adapter);

        acceptedViewModel.getSchoolList().observe(getViewLifecycleOwner(), new Observer<List<SchoolModel>>() {
            @Override
            public void onChanged(List<SchoolModel> schoolModels) {
                emptyText.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                adapter.schoolList = schoolModels;
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }
}
