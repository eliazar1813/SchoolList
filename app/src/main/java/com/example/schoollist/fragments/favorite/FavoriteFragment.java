package com.example.schoollist.fragments.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollist.R;
import com.example.schoollist.adapters.FavoriteAdapterList;
import com.example.schoollist.models.SchoolModel;

import java.util.List;

public class FavoriteFragment extends Fragment {
    FavoriteAdapterList adapter;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    View view;
    TextView emptyText;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        FavoriteViewModel favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        view = inflater.inflate(R.layout.favorite_dashboard, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        emptyText = view.findViewById(R.id.text_EmptyFavorite);
        emptyText.setText("Favorite list is empty");

        favoriteViewModel.init();
        adapter = new FavoriteAdapterList(favoriteViewModel.getSchoolList().getValue());
        recyclerView = view.findViewById(R.id.favoriteRecyclerView);
        recyclerView.setAdapter(adapter);

        favoriteViewModel.getSchoolList().observe(getViewLifecycleOwner(), new Observer<List<SchoolModel>>() {
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
