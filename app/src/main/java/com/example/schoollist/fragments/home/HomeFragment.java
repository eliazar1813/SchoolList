package com.example.schoollist.fragments.home;

import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollist.R;
import com.example.schoollist.adapters.HomeAdapterList;
import com.example.schoollist.models.SchoolModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    HomeAdapterList adapter;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        BaseViewModel homeViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        view = inflater.inflate(R.layout.home_dashboard, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        homeViewModel.init();
        adapter = new HomeAdapterList(homeViewModel.getSchoolList().getValue());
        recyclerView = view.findViewById(R.id.homeRecyclerView);
        recyclerView.setAdapter(adapter);

        homeViewModel.getSchoolList().observe(getViewLifecycleOwner(), new Observer<List<SchoolModel>>() {
            @Override
            public void onChanged(List<SchoolModel> schoolModels) {
                adapter.schoolList = schoolModels;
                adapter.copyOfSchoolList = new ArrayList<>(schoolModels);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_bar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

    }
}
