package com.tobedecided.dynamicdialer;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogsFragment extends Fragment {

    private static final int MY_PERMISSIONS_READ_CALL_LOG = 2;
    private List<Log> logList;
    private RecyclerView logsRecyclerView;
    private LogsAdapter logsAdapter;

    public LogsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logs, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        logList = new ArrayList<>();
        logList = getCallDetails(getContext());
        logsRecyclerView = (RecyclerView) getActivity().findViewById(R.id.logs_list);
        logsAdapter = new LogsAdapter(logList, getActivity(), new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //TODO: Handle log clicks
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        logsRecyclerView.setLayoutManager(layoutManager);
        logsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        logsRecyclerView.setAdapter(logsAdapter);
    }

    private static List<Log> getCallDetails(Context context) {
        List<Log> logs = new ArrayList<>();
        while (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.READ_CALL_LOG}, MY_PERMISSIONS_READ_CALL_LOG);
        }
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        while (cursor.moveToNext()) {
            String contactName = cursor.getString(name);
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = cursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
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
            if (contactName == null)
                contactName = phNumber;
            logs.add(new Log(contactName, phNumber, callDuration, callDayTime, dir));

        }
        cursor.close();
        return logs;
    }
}
