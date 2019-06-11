package com.example.android.teamtracker.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.teamtracker.Model.Final;
import com.example.android.teamtracker.Model.Initial;
import com.example.android.teamtracker.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DepartedAdapter extends RecyclerView.Adapter<DepartedAdapter.DepartedAdapterHolder> {

    private ArrayList<Final> contents;
    private Context context;


    public DepartedAdapter(ArrayList<Final> contents, Context context) {
        this.contents = contents;
        this.context = context;
    }

    @NonNull
    @Override
    public DepartedAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView;
        assert layoutInflater != null;
        itemView = layoutInflater.inflate(R.layout.activity_departed_list_item, viewGroup, false);
        return new DepartedAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartedAdapterHolder departedAdapterHolder, int i) {
        Final f = contents.get(i);
        departedAdapterHolder.tvName.setText(f.getName());
        departedAdapterHolder.tvDate.setText(f.getDate());
        departedAdapterHolder.tvCurrentLocation.setText(f.getCurrentLocation());

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class DepartedAdapterHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvCurrentLocation, tvDate;

        public DepartedAdapterHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCurrentLocation = itemView.findViewById(R.id.tvCurrentLocation);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
