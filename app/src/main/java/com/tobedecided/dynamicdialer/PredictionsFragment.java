package com.tobedecided.dynamicdialer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PredictionsFragment extends Fragment implements Callback<GsonModels.Response>{

    private List<Prediction> predictionList;
    private RecyclerView predictionsRecyclerView;
    private PredictionsAdapter predictionsAdapter;

    public PredictionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_predictions, container, false);
        return inflatedView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Calendar calendar = Calendar.getInstance();
        int day = 0;
        switch (calendar.DAY_OF_WEEK) {
            case Calendar.MONDAY:
                day = 1;
                break;
            case Calendar.TUESDAY:
                day = 2;
                break;
            case Calendar.WEDNESDAY:
                day = 3;
                break;
            case Calendar.THURSDAY:
                day = 4;
                break;
            case Calendar.FRIDAY:
                day = 5;
                break;
            case Calendar.SATURDAY:
                day = 6;
                break;
            case Calendar.SUNDAY:
                day = 7;
                break;
        }

//        String body = "{\"Inputs\": {\"input1\": {\"ColumnNames\": [\"Name\",\"Weekday\",\"Time\",\"Type\"],\"Values\": [[\" \",\"" + day + "\",\"" + calendar.HOUR_OF_DAY + "\",\"OUTGOING\"],]}},\"GlobalParameters\": {}}";
        String body = "{\"Inputs\": {\"input1\": {\"ColumnNames\": [\"Name\",\"Weekday\",\"Time\",\"Type\",\"Number\",\"Name_Number\"],\"Values\": [[\" \"," + day + "," + calendar.HOUR_OF_DAY + ",\"OUTGOING\",\"0\",\" \"]]}},\"GlobalParameters\": {}}";
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), body);
        RetrofitInterface retrofitInterface = ServiceGenerator.createService(RetrofitInterface.class);
        retrofitInterface.getPredictions("2.0", "true", requestBody).enqueue(this);
    }

    @Override
    public void onResponse(Call<GsonModels.Response> call, Response<GsonModels.Response> response) {
        if (response.isSuccessful()) {
            predictionList = new ArrayList<>();
            GsonModels.Response data = response.body();
            GsonModels.Results results = data.getResults();
            GsonModels.Output1 output1 = results.getOutput1();
            GsonModels.Value value = output1.getValue();
            List<String> columnNames = value.getColumnNames();
            List<String> values = value.getValues().get(0);
            for (int i = 0 ; i < 6 ; i++) {
                columnNames.remove(0);
                values.remove(0);
            }
            columnNames.remove(columnNames.size() - 1);
            values.remove(values.size() - 1);

            for (int i = 0 ; i < columnNames.size() ; i++) {
                predictionList.add(new Prediction(columnNames.get(i).substring(32, columnNames.get(i).length() - 11), columnNames.get(i).substring(columnNames.get(i).length() - 11, columnNames.get(i).length() - 1), Double.parseDouble(values.get(i))));
            }
            Collections.sort(predictionList, Prediction.Comparators.PROBABILITY);
            predictionsAdapter = new PredictionsAdapter(predictionList, getActivity(), new ItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    //TODO: Handle Prediction clicks
                }
            });
            RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getContext());
            predictionsRecyclerView = (RecyclerView) getActivity().findViewById(R.id.predictions_list);
            predictionsRecyclerView.setLayoutManager(layoutManager);
            predictionsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            predictionsRecyclerView.setAdapter(predictionsAdapter);
        }
    }

    @Override
    public void onFailure(Call<GsonModels.Response> call, Throwable t) {
        Toast.makeText(getContext(), "Predictions could not be loaded. Please check your internet connection", Toast.LENGTH_LONG).show();
    }
}