package com.example.schoollist.adapters;

import android.widget.Filter;
import android.widget.Filterable;
import com.example.schoollist.models.SchoolModel;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapterList extends RecyclerAdapter implements Filterable {
    public List<SchoolModel> copyOfSchoolList;
    public HomeAdapterList(List<SchoolModel> schoolList) {
        super(schoolList);
        if(schoolList != null) copyOfSchoolList = new ArrayList<>(schoolList);
    }

    @Override
    public Filter getFilter() {
        return filterList;
    }
    private final Filter filterList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<SchoolModel> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(copyOfSchoolList);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(SchoolModel school: copyOfSchoolList){
                   if(school.getSchool_name().toLowerCase().contains(filterPattern)){
                       filteredList.add(school);
                   }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            schoolList.clear();
            schoolList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
