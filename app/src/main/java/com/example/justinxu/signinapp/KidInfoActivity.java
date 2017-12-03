package com.example.justinxu.signinapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KidInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_info);
        this.setTitle("Kid Info");

        final ListView lvKidInfo = (ListView) findViewById(R.id.lvKidInfo);
        final List<String> kidInfoList = new ArrayList<String>(Arrays.asList(getIntent().getExtras().getStringArray("kidinfo")));
        final Button bCheckIn = (Button) findViewById(R.id.bCheckIn);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, kidInfoList);
        lvKidInfo.setAdapter(adapter);

        bCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KidInfoActivity.this, getIntent().getExtras().getStringArray("kidinfo")[0] + " has been checked in.",
                        Toast.LENGTH_SHORT).show();
                //Will add database updating here to mark kid as here

                KidInfoActivity.super.onBackPressed();
            }
        });
    }
}
