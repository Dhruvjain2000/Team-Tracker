package app.tracker.android.teamtracker.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.tracker.android.teamtracker.Model.LoginHistory;
import com.tracker.android.teamtracker.R;

import java.util.ArrayList;
import java.util.Collections;

public class LoginInfoAdapter extends RecyclerView.Adapter<LoginInfoAdapter.LoginInfoAdapterHolder> {

    private ArrayList<LoginHistory> contents;
    private Context context;

    public LoginInfoAdapter(ArrayList<LoginHistory> contents, Context context) {
        this.contents = contents;
        this.context = context;
        Collections.reverse(contents);
    }

    @NonNull
    @Override
    public LoginInfoAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView;
        assert layoutInflater != null;
        itemView = layoutInflater.inflate(R.layout.activity_login_info_item, viewGroup, false);
        return new LoginInfoAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LoginInfoAdapterHolder loginInfoAdapterHolder, int i) {
        LoginHistory lh = contents.get(i);
        loginInfoAdapterHolder.tvDateAndTime.setText(lh.getDateAndtime());
        loginInfoAdapterHolder.tvEmail.setText(lh.getEmail());
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class LoginInfoAdapterHolder extends RecyclerView.ViewHolder {
        TextView tvEmail, tvDateAndTime;

        public LoginInfoAdapterHolder(@NonNull View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvDateAndTime = itemView.findViewById(R.id.tvDateAndTime);
        }
    }
}
