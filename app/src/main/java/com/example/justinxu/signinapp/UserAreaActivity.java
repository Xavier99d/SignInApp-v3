package com.example.justinxu.signinapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class UserAreaActivity extends AppCompatActivity {

    ListView lvPeople;
    EditText etSearchBar;
    List<String[]> peopleList;
    ArrayAdapterHandler adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        this.setTitle("Check-In Page");

        lvPeople = (ListView) findViewById(R.id.lvPeople);
        etSearchBar = (EditText) findViewById(R.id.etSearchBar);
        peopleList = new ArrayList<String[]>();

        String[] item = {"Name", "Table", "Age", "ParentName"};
        for(int i = 0; i < 10; i++) {
            String[] itemCopy = new String[item.length];
            for (int j = 0; j < item.length; j++)
                itemCopy[j] = item[j] + (char)(i + 'A');
            peopleList.add(itemCopy);
        }
        Collections.sort(peopleList, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });

        adapter = new ArrayAdapterHandler(this, android.R.layout.simple_list_item_2, android.R.id.text1, peopleList);
        lvPeople.setAdapter(adapter);
        lvPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), KidInfoActivity.class);
                intent.putExtra("kidinfo", peopleList.get(position));
                startActivity(intent);
            }
        });

        etSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(""))
                    initList();
                else
                    lvPeople.setAdapter(new ArrayAdapterHandler(UserAreaActivity.this, android.R.layout.simple_list_item_2, android.R.id.text1, filter(s)));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            public List<String[]> filter(CharSequence cs) {
                List<String[]> filteredList = new ArrayList<String[]>();
                int searchFor = 0, indexSearch = 0;
                if(cs.toString().startsWith(":")) {
                    searchFor = 1;
                    indexSearch = 1;
                }
                for(int i = 0; i < peopleList.size(); i++)
                    if(peopleList.get(i)[searchFor].toLowerCase().contains(cs.toString().substring(indexSearch).toLowerCase()))
                        filteredList.add(peopleList.get(i));
                return filteredList;
            }

            public void initList() {
                adapter = new ArrayAdapterHandler(UserAreaActivity.this, android.R.layout.simple_list_item_2, android.R.id.text1, peopleList);
                lvPeople.setAdapter(adapter);
            }
        });
    }
}