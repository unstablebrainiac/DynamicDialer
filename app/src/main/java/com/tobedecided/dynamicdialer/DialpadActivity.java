package com.tobedecided.dynamicdialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.tobedecided.dynamicdialer.LogsAdapter.MY_PERMISSIONS_CALL_PHONE;
import static com.tobedecided.dynamicdialer.R.id.gridview;


public class DialpadActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[] digits = new int[12];
        String[] alphanum = new String[12];
        for (int i = 0; i < 10; i++) {
            digits[i] = i;
            switch (i) {
                case 0:
                    alphanum[0] = " + ";
                    break;
                case 1:
                    alphanum[1] = "N/A";
                    break;
                case 2:
                    alphanum[2] = "ABC";
                    break;
                case 3:
                    alphanum[3] = "DEF";
                    break;
                case 4:
                    alphanum[4] = "GHI";
                    break;
                case 5:
                    alphanum[5] = "JKL";
                    break;
                case 6:
                    alphanum[6] = "MNO";
                    break;
                case 7:
                    alphanum[7] = "PQRS";
                    break;
                case 8:
                    alphanum[8] = "TUV";
                    break;
                case 9:
                    alphanum[9] = "WXYZ";
                    break;
                case 10:
                    alphanum[10] = "*";
                    break;
                case 11:
                    alphanum[11] = "#";
                    break;
            }
        }
        super.onCreate(savedInstanceState);
        getIntent();
        setContentView(R.layout.activity_dialpad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.textView);
                String number = textView.getText().toString();
                if (number != null) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    if (ActivityCompat.checkSelfPermission(DialpadActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(DialpadActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_CALL_PHONE);
                    }
                    DialpadActivity.this.startActivity(intent);
                }
            }
        });
        GridView gridView = (GridView) findViewById(gridview);
        final ImageButton back = (ImageButton) findViewById(R.id.imageButton);
        final EditText dialed = (EditText) findViewById(R.id.textView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < 9) {
                    dialed.append("" + (position + 1));
                }
                if (position == 10) {
                    dialed.append("" + 0);
                }
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = dialed.getText().toString();
                if (s.length() != 0) {
                    s = s.substring(0, s.length() - 1);
                    dialed.setText(s);
                }
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            }
        });
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialed.setText("");
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });

        GridViewAdapter gva = new GridViewAdapter(this, digits, alphanum);
        gridView.setAdapter(gva);
    }


}
