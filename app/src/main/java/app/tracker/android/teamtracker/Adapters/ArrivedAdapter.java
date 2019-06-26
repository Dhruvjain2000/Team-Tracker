package app.tracker.android.teamtracker.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.tracker.android.teamtracker.Model.Initial;
import com.tracker.android.teamtracker.R;

import java.util.ArrayList;

public class ArrivedAdapter extends RecyclerView.Adapter<ArrivedAdapter.ArrivedAdapterHolder> {

    private ArrayList<Initial> contents;
    private Context context;

    public ArrivedAdapter(ArrayList<Initial> contents, Context context) {

        this.contents = contents;
        this.context = context;
    }

    @NonNull
    @Override
    public ArrivedAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView;
        assert layoutInflater != null;
        itemView = layoutInflater.inflate(R.layout.activity_arrived_list_item, viewGroup, false);
        return new ArrivedAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrivedAdapterHolder arrivedAdapterHolder, int i) {
        Initial initial = contents.get(i);
        arrivedAdapterHolder.tvName.setText(initial.getName());
        arrivedAdapterHolder.tvDate.setText(initial.getDate());
        arrivedAdapterHolder.tvCurrentLocation.setText(initial.getCurrentLocation());
        arrivedAdapterHolder.tvClientName.setText(initial.getClientName());
        arrivedAdapterHolder.tvClientLocation.setText(initial.getCurrentLocation());
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class ArrivedAdapterHolder extends RecyclerView.ViewHolder {
        TextView tvClientLocation, tvClientName, tvCurrentLocation,tvDate, tvName;
        public ArrivedAdapterHolder(@NonNull View itemView) {
            super(itemView);

            tvClientLocation = itemView.findViewById(R.id.tvClientLocation);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            tvCurrentLocation = itemView.findViewById(R.id.tvCurrentLocation);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
