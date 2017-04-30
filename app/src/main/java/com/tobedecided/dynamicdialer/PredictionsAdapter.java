package com.tobedecided.dynamicdialer;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static Bitmap getContactBitmapFromURI(Context context, Uri uri) {
        InputStream input = null;
        try {
            input = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            return null;
        }
        if (input == null) {
            return null;
        }
        return BitmapFactory.decodeStream(input);
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
        Bitmap bitmap = openPhoto(selectedPrediction.getContact_id());
        List<String> colorList = new ArrayList<>();
        colorList.add("#DB4437");
        colorList.add("#009688");
        colorList.add("#9C27B0");
        colorList.add("#689F38");
        colorList.add("#0F9D58");
        colorList.add("#673AB7");
        colorList.add("#3F51B5");
        colorList.add("#EF6C00");
        Random r = new Random();
        int i = r.nextInt(colorList.size());
        holder.photoRl.setBackgroundColor(Color.parseColor(colorList.get(i)));
        if (bitmap != null) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
            holder.photoRl.setBackgroundDrawable(bitmapDrawable);
        } else {
            char c = selectedPrediction.getName().charAt(0);
            holder.letterTv.setText("" + c);
        }
        holder.contactDetailsIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(selectedPrediction.getContact_id()));
                Intent intent = new Intent(Intent.ACTION_VIEW, contactUri);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return predictionList.size();
    }

    public Bitmap openPhoto(String contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contactId));
        Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

        return getContactBitmapFromURI(context, photoUri);
    }

    public class PredictionsHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView letterTv;
        private ImageView contactDetailsIv;
        private RelativeLayout photoRl;

        public PredictionsHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name);
            letterTv = (TextView) itemView.findViewById(R.id.letter);
            contactDetailsIv = (ImageView) itemView.findViewById(R.id.contact_details);
            photoRl = (RelativeLayout) itemView.findViewById(R.id.profile_photo);
        }
    }
}
