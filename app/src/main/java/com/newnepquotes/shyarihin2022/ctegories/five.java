package com.newnepquotes.shyarihin2022.ctegories;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.newnepquotes.shyarihin2022.Model.Model;
import com.newnepquotes.shyarihin2022.R;

import java.util.ArrayList;
import java.util.List;

public class five extends AppCompatActivity implements View.OnClickListener{

    TextView count_txt, quotes_txt;
    CardView back_btn, copy_btn, share_btn, next_btn;
    List<String> quotes_list;
    DatabaseReference databaseReference;
    Model myShayari;
    int position = 0;
    private AdView mAdView;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Romantic Quotes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        count_txt = findViewById(R.id.countTEXT);
        quotes_txt = findViewById(R.id.quotesTEXT);
        back_btn = findViewById(R.id.backBtn);
        copy_btn = findViewById(R.id.copyBtn);
        share_btn= findViewById(R.id.shareBtn);
        next_btn = findViewById(R.id.nextBtn);

        back_btn.setOnClickListener(this);
        copy_btn.setOnClickListener(this);
        share_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("romanticquotes");
        myShayari = new Model();
        quotes_list = new ArrayList<>();

        //Event Value methods
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    myShayari = dataSnapshot1.getValue(Model.class);
                    if (myShayari != null){
                        quotes_list.add(myShayari.getTitle());
                    }
                }
                quotes_txt.setText(quotes_list.get(position));
                count_txt.setText(position + "/" + quotes_list.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(),"ERROR OCCURED",Toast.LENGTH_SHORT).show();
            }
        });
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backBtn:
                back();
                break;

            case R.id.copyBtn:
                copy();
                break;

            case R.id.shareBtn:
                share();
                break;

            case R.id.nextBtn:
                next();
                break;


        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //button function starts here
    private void back(){
        if(position>0){
            position = (position -1)% quotes_list.size();
            quotes_txt.setText(quotes_list.get(position));
            count_txt.setText(position + "/" + quotes_list.size());

        }
    }

    private void next(){
        position = (position +1)% quotes_list.size();
        quotes_txt.setText(quotes_list.get(position));
        count_txt.setText(position + "/" + quotes_list.size());
    }
    private void copy(){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text",quotes_txt.getText());
        if(clipboardManager!=null){
            clipboardManager.setPrimaryClip(clipData);
        }
        Toast.makeText(getApplicationContext(), "Copied", Toast.LENGTH_SHORT).show();
    }

    private void share(){
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, quotes_txt.getText());
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "Copied", Toast.LENGTH_SHORT).show();
        }
    }
}
