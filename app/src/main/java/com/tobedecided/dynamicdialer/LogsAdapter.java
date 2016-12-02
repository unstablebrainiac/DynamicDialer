package com.tobedecided.dynamicdialer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sajalnarang on 30/11/16.
 */

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.LogsHolder> {

    private List<Log> logList;
    private ItemClickListener itemClickListener;

    public LogsAdapter(List<Log> logList, ItemClickListener itemClickListener) {
        this.logList = logList;
        this.itemClickListener = itemClickListener;
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
        Log selectedLog = logList.get(position);
        holder.nameTv.setText(selectedLog.getName());
        holder.typeTv.setText(selectedLog.getType());
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    public class LogsHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView typeTv;

        public LogsHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name);
            typeTv = (TextView) itemView.findViewById(R.id.type);
        }
    }
}
