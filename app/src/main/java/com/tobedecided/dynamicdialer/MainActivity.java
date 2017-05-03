package com.tobedecided.dynamicdialer;

import android.Manifest;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int URL_LOADER = 1;
    private static final int MY_PERMISSIONS_READ_CALL_LOG = 1;
    private static final int MY_PERMISSIONS_READ_CONTACTS = 2;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static long getContactIDFromNumber(String contactNumber, Context context) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(contactNumber));
        Cursor cursor = context.getContentResolver().query(uri,
                new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID},
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

    public static void sendLogs(String body, final SharedPreferences sharedPreferences) {
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), body);
        RetrofitInterface retrofitInterface = ServiceGenerator.createService(RetrofitInterface.class);
        retrofitInterface.sendSource("application/json", "bhavesh2109", "b5265ab5defd322e797052263c4f04e1bcb53d42", requestBody).enqueue(new Callback<GsonModels.BigMLSourceResponse>() {
            @Override
            public void onResponse(Call<GsonModels.BigMLSourceResponse> call, Response<GsonModels.BigMLSourceResponse> response) {
                if (response.isSuccessful()) {
                    GsonModels.BigMLSourceResponse bigMLSourceResponse = response.body();
                    String resource = bigMLSourceResponse.getResource();
                    String body1 = "{\"source\": \"" + resource + "\"}";
                    RequestBody requestBody1 = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), body1);
                    RetrofitInterface retrofitInterface1 = ServiceGenerator.createService(RetrofitInterface.class);
                    retrofitInterface1.sendDataset("application/json", "bhavesh2109", "b5265ab5defd322e797052263c4f04e1bcb53d42", requestBody1).enqueue(new Callback<GsonModels.BigMLDatasetResponse>() {
                        @Override
                        public void onResponse(Call<GsonModels.BigMLDatasetResponse> call, Response<GsonModels.BigMLDatasetResponse> response) {
                            if (response.isSuccessful()) {
                                GsonModels.BigMLDatasetResponse bigMLDatasetResponse = response.body();
                                String resource1 = bigMLDatasetResponse.getResource();
                                android.util.Log.d("TAG", "onResponse: " + resource1);
                                String body2 = "{\"dataset\":\"" + resource1 + "\",\"objective_field\":\"000002\"}";
                                RequestBody requestBody2 = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), body2);
                                RetrofitInterface retrofitInterface2 = ServiceGenerator.createService(RetrofitInterface.class);
                                retrofitInterface2.sendModel("application/json", "bhavesh2109", "b5265ab5defd322e797052263c4f04e1bcb53d42", requestBody2).enqueue(new Callback<GsonModels.BigMLModelResponse>() {
                                    @Override
                                    public void onResponse(Call<GsonModels.BigMLModelResponse> call, Response<GsonModels.BigMLModelResponse> response) {
                                        if (response.isSuccessful()) {
                                            GsonModels.BigMLModelResponse bigMLModelResponse = response.body();
                                            String resource2 = bigMLModelResponse.getResource();
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("resource2", resource2);
                                            editor.apply();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<GsonModels.BigMLModelResponse> call, Throwable t) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<GsonModels.BigMLDatasetResponse> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<GsonModels.BigMLSourceResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DialpadActivity.class);
                startActivity(intent);
            }
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, MY_PERMISSIONS_READ_CALL_LOG);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_READ_CONTACTS);
        }
        getLoaderManager().initLoader(URL_LOADER, null, MainActivity.this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PredictionsFragment(), "PREDICTIONS");
        adapter.addFragment(new LogsFragment(), "LOGS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case URL_LOADER:
                // Returns a new CursorLoader
                return new CursorLoader(
                        this,   // Parent activity context
                        CallLog.Calls.CONTENT_URI,        // Table to query
                        null,     // Projection to return
                        null,            // No selection clause
                        null,            // No selection arguments
                        null             // Default sort order
                );
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        int name = data.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = data.getColumnIndex(CallLog.Calls.NUMBER);
        int type = data.getColumnIndex(CallLog.Calls.TYPE);
        int date = data.getColumnIndex(CallLog.Calls.DATE);
        int duration = data.getColumnIndex(CallLog.Calls.DURATION);
        StringBuilder sb = new StringBuilder();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append("{\"name\":\"" + df.format(Calendar.getInstance().getTime()) + "\",\"data\":\"ID,Name,Number,Weekday,Hour Of the Day\\n");

        while (data.moveToNext()) {
            String contactName = data.getString(name);
            String phNumber = data.getString(number);
            String callType = data.getString(type);
            String callDate = data.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = data.getString(duration);
            String dir = null;

            SimpleDateFormat hourDateFormat = new SimpleDateFormat("HH");
            String time = hourDateFormat.format(callDayTime);
            String dayOfTheWeek = (String) DateFormat.format("EEEE", callDayTime);
            int day = 0;

            switch (dayOfTheWeek) {
                case "Sunday":
                    day = 1;
                    break;
                case "Monday":
                    day = 2;
                    break;
                case "Tuesday":
                    day = 3;
                    break;
                case "Wednesday":
                    day = 4;
                    break;
                case "Thursday":
                    day = 5;
                    break;
                case "Friday":
                    day = 6;
                    break;
                case "Saturday":
                    day = 7;
                    break;
            }


            int callTypeCode = Integer.parseInt(callType);
            switch (callTypeCode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "Outgoing";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "Incoming";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "Missed";
                    break;
            }
            sb.append(getContactIDFromNumber(phNumber, this) + "," + contactName + "," + phNumber + "," + day + "," + time + "\\n");
        }
        data.close();
        sb.append("\"}");

        SharedPreferences sharedPreferences = getSharedPreferences("DynamicDialer", Context.MODE_PRIVATE);
        sendLogs(sb.toString(), sharedPreferences);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }
}
