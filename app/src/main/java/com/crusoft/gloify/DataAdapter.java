package com.crusoft.gloify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<AndroidModel> android;
    Context context;

    public DataAdapter(Context context,ArrayList<AndroidModel> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerviewitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
        //set color for textview
        viewHolder.tv_name.setTextColor(ContextCompat.getColor(context, R.color.green_300));
        viewHolder.tv_version.setTextColor(ContextCompat.getColor(context, R.color.blue_100));
        viewHolder.tv_api_level.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        //set text for textview
        viewHolder.tv_name.setText("Android Name:" + android.get(i).getName());
        viewHolder.tv_version.setText("Android Version:" + android.get(i).getVer());
        viewHolder.tv_api_level.setText("Android :" +android.get(i).getApi());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_version = (TextView)view.findViewById(R.id.tv_version);
            tv_api_level = (TextView)view.findViewById(R.id.tv_api_level);

        }
    }
}
