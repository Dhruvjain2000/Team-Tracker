package app.tracker.android.teamtracker.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.tracker.android.teamtracker.Model.UploadReport;
import com.tracker.android.teamtracker.R;

import java.util.ArrayList;

public class CompleteInfoAdapter extends RecyclerView.Adapter<CompleteInfoAdapter.CompleteInfoAdapterHolder> {

    private ArrayList<UploadReport> contents;
    private Context context;

    public CompleteInfoAdapter(ArrayList<UploadReport> contents, Context context) {
        this.contents = contents;
        this.context = context;
    }

    @NonNull
    @Override
    public CompleteInfoAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView;
        assert layoutInflater != null;
        itemView = layoutInflater.inflate(R.layout.activity_complete_info_list_item, viewGroup, false);
        return new CompleteInfoAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteInfoAdapterHolder completeInfoAdapterHolder, int i) {
        UploadReport report = contents.get(i);
        completeInfoAdapterHolder.tvGender.setText(report.getGender());
        completeInfoAdapterHolder.tvLon.setText(report.getLon());
        completeInfoAdapterHolder.tvLat.setText(report.getLat());
        completeInfoAdapterHolder.tvCity.setText(report.getCity());
        completeInfoAdapterHolder.tvState.setText(report.getState());
        completeInfoAdapterHolder.tvAge.setText(String.valueOf(report.getAge()));
        completeInfoAdapterHolder.tvPincode.setText(report.getPincode());
        completeInfoAdapterHolder.tvName.setText(report.getName());
        completeInfoAdapterHolder.tvUID.setText(report.getUid());
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class CompleteInfoAdapterHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPincode, tvAge, tvUID, tvState, tvCity, tvLat, tvLon, tvGender;

        public CompleteInfoAdapterHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPincode = itemView.findViewById(R.id.tvPincode);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvUID = itemView.findViewById(R.id.tvUID);
            tvState = itemView.findViewById(R.id.tvState);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvLat = itemView.findViewById(R.id.tvLat);
            tvLon = itemView.findViewById(R.id.tvLon);
            tvGender = itemView.findViewById(R.id.tvGender);
        }
    }
}
