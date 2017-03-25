package com.tobedecided.dynamicdialer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sajalnarang on 30/11/16.
 */

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.LogsHolder> {

    public static final int MY_PERMISSIONS_CALL_PHONE = 1;
    private List<Log> logList;
    private Context context;
    private ItemClickListener itemClickListener;

    public LogsAdapter(List<Log> logList, Context context, ItemClickListener itemClickListener) {
        this.logList = logList;
        this.itemClickListener = itemClickListener;
        this.context = context;
    }

    @Override
    public LogsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_list_row, parent, false);
        final LogsHolder logsHolder = new LogsHolder(userView);
        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, logsHolder.getAdapterPosition());
            }
        });
        return logsHolder;
    }

    @Override
    public void onBindViewHolder(LogsHolder holder, int position) {
        final Log selectedLog = logList.get(position);
        holder.nameTv.setText(selectedLog.getName());
        holder.typeTv.setText(selectedLog.getType());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + selectedLog.getNumber()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_CALL_PHONE);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    public class LogsHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView typeTv;
        private ImageButton callButton;

        public LogsHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name);
            typeTv = (TextView) itemView.findViewById(R.id.type);
            callButton = (ImageButton) itemView.findViewById(R.id.call_button);
        }
    }


}
