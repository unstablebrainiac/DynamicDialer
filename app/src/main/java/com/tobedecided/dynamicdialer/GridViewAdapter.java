package com.tobedecided.dynamicdialer;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final int[] digits;
    private final String[] alphanum;


    // 1
    public GridViewAdapter(Context context, int[] digits, String[] alphanum) {
        this.mContext = context;
        this.digits = digits;
        this.alphanum = alphanum;
    }

    // 2

    @Override
    public int getCount() {
        return digits.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final String alphanumf = alphanum[(position + 1) % 12];
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.digit_layout, null);
        }

        final TextView nameTextView = (TextView) convertView.findViewById(R.id.number);
        final TextView alphanumText = (TextView) convertView.findViewById(R.id.alpha_num);


        if (position < 9) {
            nameTextView.setText("" + (position + 1));
            alphanumText.setText(alphanumf);
        } else if (position == 9) {
            nameTextView.setText("*");
        } else if (position == 10) {
            nameTextView.setText("0");
            alphanumText.setText(" +");
        } else if (position == 11) {
            nameTextView.setText("#");
        }

        return convertView;
    }
}