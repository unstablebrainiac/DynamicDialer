package com.tobedecided.dynamicdialer;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class PredictionsFragment extends Fragment {

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
        int day = calendar.get(Calendar.DAY_OF_WEEK);   //Sunday:1, Saturday:7
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        android.util.Log.d(TAG, "onStart: " + day + " " + hour);

//        String body = "{\"Inputs\": {\"input1\": {\"ColumnNames\": [\"Name\",\"Weekday\",\"Time\",\"Type\",\"Number\",\"Name_Number\"],\"Values\": [[\" \"," + day + "," + calendar.get(Calendar.HOUR_OF_DAY) + ",\"OUTGOING\",\"0\",\" \"]]}},\"GlobalParameters\": {}}";
        String body = "{\"model\": \"model/58d61016014404745d00031a\",\n" + " \"input_data\":{ \"000001\":\"" + day + "\",\"000004\":\"" + hour + "\"}}";
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), body);
        RetrofitInterface retrofitInterface = ServiceGenerator.createService(RetrofitInterface.class);
//        retrofitInterface.getPredictions("2.0", "true", requestBody).enqueue(this);
        retrofitInterface.getPredictions("application/json", "bhavesh2109", "b5265ab5defd322e797052263c4f04e1bcb53d42", requestBody).enqueue(new Callback<GsonModels.BigMLResponse>() {
            @Override
            public void onResponse(Call<GsonModels.BigMLResponse> call, Response<GsonModels.BigMLResponse> response) {
                if (response.isSuccessful()) {
                    predictionList = new ArrayList<>();
                    android.util.Log.d(TAG, "onResponse: " + response.code());
                    GsonModels.BigMLResponse bigMLResponse = response.body();
                    List<List<String>> probabilities = bigMLResponse.getProbabilities();
                    for (int i = 0; i < probabilities.size(); i++) {
                        predictionList.add(new Prediction(probabilities.get(i).get(0), probabilities.get(i).get(0), Double.parseDouble(probabilities.get(i).get(1))));
                    }
                    Collections.sort(predictionList, Prediction.Comparators.PROBABILITY);
                    predictionsAdapter = new PredictionsAdapter(predictionList, getActivity(), new ItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + predictionList.get(position).getNumber()));
                            while (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, LogsAdapter.MY_PERMISSIONS_CALL_PHONE);
                            }
                            startActivity(intent);
                        }
                    });
                    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                    predictionsRecyclerView = (RecyclerView) getActivity().findViewById(R.id.predictions_list);
                    predictionsRecyclerView.setLayoutManager(layoutManager);
                    predictionsRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    predictionsRecyclerView.setAdapter(predictionsAdapter);
                }
            }

            @Override
            public void onFailure(Call<GsonModels.BigMLResponse> call, Throwable t) {
                android.util.Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

//    @Override
//    public void onResponse(Call<GsonModels.Response> call, Response<GsonModels.Response> response) {
//        if (response.isSuccessful()) {
//            predictionList = new ArrayList<>();
//            GsonModels.Response data = response.body();
//            GsonModels.Results results = data.getResults();
//            GsonModels.Output1 output1 = results.getOutput1();
//            GsonModels.Value value = output1.getValue();
//            List<String> columnNames = value.getColumnNames();
//            List<String> values = value.getValues().get(0);
//            for (int i = 0; i < 6; i++) {
//                columnNames.remove(0);
//                values.remove(0);
//            }
//            columnNames.remove(columnNames.size() - 1);
//            values.remove(values.size() - 1);
//
//            for (int i = 0; i < columnNames.size(); i++) {
//                predictionList.add(new Prediction(columnNames.get(i).substring(32, columnNames.get(i).length() - 11), columnNames.get(i).substring(columnNames.get(i).length() - 11, columnNames.get(i).length() - 1), Double.parseDouble(values.get(i))));
//            }
//            Collections.sort(predictionList, Prediction.Comparators.PROBABILITY);
//            predictionsAdapter = new PredictionsAdapter(predictionList, getActivity(), new ItemClickListener() {
//                @Override
//                public void onItemClick(View v, int position) {
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    intent.setData(Uri.parse("tel:" + predictionList.get(position).getNumber()));
//                    while (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, LogsAdapter.MY_PERMISSIONS_CALL_PHONE);
//                    }
//                    startActivity(intent);
//                }
//            });
//            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
//            predictionsRecyclerView = (RecyclerView) getActivity().findViewById(R.id.predictions_list);
//            predictionsRecyclerView.setLayoutManager(layoutManager);
//            predictionsRecyclerView.setItemAnimator(new DefaultItemAnimator());
//            predictionsRecyclerView.setAdapter(predictionsAdapter);
//        }
//    }
//
//    @Override
//    public void onFailure(Call<GsonModels.Response> call, Throwable t) {
//        Toast.makeText(getContext(), "Predictions could not be loaded. Please check your internet connection", Toast.LENGTH_LONG).show();
//    }
}