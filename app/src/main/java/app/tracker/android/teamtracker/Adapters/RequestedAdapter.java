package app.tracker.android.teamtracker.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tracker.android.teamtracker.R;
import app.tracker.android.teamtracker.RequestParts.UploadRequest;

import java.util.ArrayList;

public class RequestedAdapter extends RecyclerView.Adapter<RequestedAdapter.RequestedAdapterHolder> {

    private ArrayList<UploadRequest> contents;
    private Context context;

    public RequestedAdapter(ArrayList<UploadRequest> contents, Context context) {
        this.contents = contents;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestedAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView;
        assert layoutInflater != null;
        itemView = layoutInflater.inflate(R.layout.activity_requested_list_item, viewGroup, false);
        return new RequestedAdapterHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RequestedAdapterHolder requestedAdapterHolder, int i) {
        UploadRequest requestedContent = contents.get(i);
        requestedAdapterHolder.tvRequestedName.setText(requestedContent.getName());
        requestedAdapterHolder.tvRequestedPartName.setText(requestedContent.getPartRequest());
        requestedAdapterHolder.tvRequestedPincode.setText(String.valueOf(requestedContent.getPincode()));
        requestedAdapterHolder.tvRequestedCityName.setText(requestedContent.getCity());
        requestedAdapterHolder.tvRequestedPartCount.setText(String.valueOf(requestedContent.getCount()));

     }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class RequestedAdapterHolder extends RecyclerView.ViewHolder {

        TextView tvRequestedName, tvRequestedPartName, tvRequestedPartCount, tvRequestedCityName, tvRequestedPincode;
        public RequestedAdapterHolder(@NonNull View itemView) {
            super(itemView);

            tvRequestedName = itemView.findViewById(R.id.tvRequestedName);
            tvRequestedPartName = itemView.findViewById(R.id.tvRequestedPartName);
            tvRequestedPartCount = itemView.findViewById(R.id.tvRequestedPartCount);
            tvRequestedCityName = itemView.findViewById(R.id.tvRequestedCityName);
            tvRequestedPincode = itemView.findViewById(R.id.tvRequestedPincode);
        }
    }
}
