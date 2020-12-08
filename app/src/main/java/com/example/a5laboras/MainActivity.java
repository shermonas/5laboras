package com.example.a5laboras;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.os.Handler;
import android.view.View;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {
    ListView cList;
    ArrayList<String> listItems = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DataLoader().execute();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                displayList();
            }
        }, 1000);
    }
    public void displayList(){
        listItems=Parser.getList();
        cList = (ListView) findViewById(R.id.lvCurrency);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        cList.setAdapter(arrayAdapter);
    }
    public void onBtnClickLoadData(View view) {
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        new DataLoader().execute();
        final boolean b = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                displayList();
            }
        }, 1000);
    }
}