package com.tobedecided.dynamicdialer;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogsFragment extends Fragment {

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
        logsRecyclerView = (RecyclerView) getActivity().findViewById(R.id.logs_list);
        logsAdapter = new LogsAdapter(logList, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //TODO: Handle log clicks
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        logsRecyclerView.setLayoutManager(layoutManager);
        logsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        logsRecyclerView.setAdapter(logsAdapter);

        prepareLogData();
    }

    private void prepareLogData() {
        Log log = new Log("Sajal Narang", "+918087088708", "12:56", "Pune", "Incoming");
        logList.add(log);
        log = new Log("Bhavesh Thakkar", "+91000-IDK-00", "01:02", "Gujrat?", "Outgoing");
        logList.add(log);
        log = new Log("Dhanvi Sreenivasan", "+91-IDK-000000", "00:00", "Pune again", "Missed");
        logList.add(log);

        logsAdapter.notifyDataSetChanged();
    }
}
