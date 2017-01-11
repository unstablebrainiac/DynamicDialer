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
 * Created by sajalnarang on 8/1/17.
 */

public class PredictionsAdapter extends RecyclerView.Adapter<PredictionsAdapter.PredictionsHolder> {
    private List<Prediction> predictionList;
    private Context context;
    private ItemClickListener itemClickListener;

    public PredictionsAdapter(List<Prediction> predictionList, Context context, ItemClickListener itemClickListener) {
        this.predictionList = predictionList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public PredictionsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.prediction_list_row, parent, false);
        final PredictionsHolder predictionsHolder = new PredictionsHolder(userView);
        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, predictionsHolder.getAdapterPosition());
            }
        });
        return predictionsHolder;
    }

    @Override
    public void onBindViewHolder(PredictionsHolder holder, int position) {
        final Prediction selectedPrediction = predictionList.get(position);
        holder.nameTv.setText(selectedPrediction.getName());
        holder.numberTv.setText(selectedPrediction.getNumber());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + selectedPrediction.getNumber()));
                while (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, LogsAdapter.MY_PERMISSIONS_CALL_PHONE);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return predictionList.size();
    }

    public class PredictionsHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView numberTv;
        private ImageButton callButton;

        public PredictionsHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name);
            numberTv = (TextView) itemView.findViewById(R.id.number);
            callButton = (ImageButton) itemView.findViewById(R.id.call_button);
        }
    }
}
