package com.tobedecided.dynamicdialer;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

    private static final int MY_PERMISSIONS_READ_CONTACTS = 5;
    private List<Prediction> predictionList;
    private RecyclerView predictionsRecyclerView;
    private PredictionsAdapter predictionsAdapter;

    public PredictionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("DynamicDialer", Context.MODE_PRIVATE);
        String resource2 = sharedPreferences.getString("resource2", "");
        sendPredictionsRequest(resource2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_predictions, container, false);
    }

    public void sendPredictionsRequest(String resource2) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);   //Sunday:1, Saturday:7
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        android.util.Log.d(TAG, "onStart: " + day + " " + hour);

//        String body = "{\"Inputs\": {\"input1\": {\"ColumnNames\": [\"Name\",\"Weekday\",\"Time\",\"Type\",\"Number\",\"Name_Number\"],\"Values\": [[\" \"," + day + "," + calendar.get(Calendar.HOUR_OF_DAY) + ",\"OUTGOING\",\"0\",\" \"]]}},\"GlobalParameters\": {}}";
        String body = "{\"model\": \"" + resource2 + "\",\n" + " \"input_data\":{ \"000003\":\"" + day + "\",\"000004\":\"" + hour + "\"}}";
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), body);
        RetrofitInterface retrofitInterface = ServiceGenerator.createService(RetrofitInterface.class);
//        retrofitInterface.getPredictions("2.0", "true", requestBody).enqueue(this);
        retrofitInterface.getPredictions("application/json", "bhavesh2109", "b5265ab5defd322e797052263c4f04e1bcb53d42", requestBody).enqueue(new Callback<GsonModels.BigMLPredictionsResponse>() {
            @Override
            public void onResponse(Call<GsonModels.BigMLPredictionsResponse> call, Response<GsonModels.BigMLPredictionsResponse> response) {
                if (response.isSuccessful()) {
                    predictionList = new ArrayList<>();
                    android.util.Log.d(TAG, "onResponse: " + response.code());
                    GsonModels.BigMLPredictionsResponse bigMLPredictionsResponse = response.body();
                    List<List<String>> probabilities = bigMLPredictionsResponse.getProbabilities();
                    for (int i = 0; i < probabilities.size(); i++) {
                        if (!(getContactName(getContext(), probabilities.get(i).get(0)) == null) && !getContactName(getContext(), probabilities.get(i).get(0)).isEmpty())
                            predictionList.add(new Prediction(getContactIDFromNumber(probabilities.get(i).get(1), getContext()), getContactName(getContext(), probabilities.get(i).get(0)), probabilities.get(i).get(0), Double.parseDouble(probabilities.get(i).get(1))));
                    }
                    Collections.sort(predictionList, Prediction.Comparators.PROBABILITY);
                    predictionsAdapter = new PredictionsAdapter(predictionList, getActivity(), new ItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + predictionList.get(position).getNumber()));
                            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
            public void onFailure(Call<GsonModels.BigMLPredictionsResponse> call, Throwable t) {
                android.util.Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public static long getContactIDFromNumber(String contactNumber, Context context) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(contactNumber));
        Cursor cursor = context.getContentResolver().query(uri,
                new String[] { ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID },
                null, null, null);

        String contactId = "";

        if (cursor.moveToFirst()) {
            do {
                contactId = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.PhoneLookup._ID));
            } while (cursor.moveToNext());
        }
        return contactId.equals("") ? 0 : Long.parseLong(contactId);
    }


    public static String getContactName(Context context, String phoneNumber) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_READ_CONTACTS);
        }
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        String projection[] = new String[] {ContactsContract.PhoneLookup.DISPLAY_NAME,ContactsContract.PhoneLookup._ID};
        Cursor cursor =
                cr.query(
                        uri,
                        projection,
                        null,
                        null,
                        null);

        if (cursor == null) {
            while(cursor.moveToNext()){
                String contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup.DISPLAY_NAME));
                String contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
            }
        }
        String contactName = null;
        if(cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }

        if(!cursor.isClosed()) {
            cursor.close();
        }

        return contactName;
    }
}