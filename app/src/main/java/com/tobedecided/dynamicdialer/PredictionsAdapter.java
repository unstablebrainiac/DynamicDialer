package com.tobedecided.dynamicdialer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sajalnarang on 8/1/17.
 */

public class PredictionsAdapter extends RecyclerView.Adapter<PredictionsAdapter.PredictionsHolder> {
    private List<Prediction> predictionList;
    private ItemClickListener itemClickListener;

    public PredictionsAdapter(List<Prediction> predictionList, ItemClickListener itemClickListener) {
        this.predictionList = predictionList;
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
    }

    @Override
    public int getItemCount() {
        return predictionList.size();
    }

    public class PredictionsHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;

        public PredictionsHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
