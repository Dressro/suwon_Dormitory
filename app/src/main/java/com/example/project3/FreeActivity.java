package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FreeActivity extends AppCompatActivity {
    private ListView listView;
    private Adapter_list adapter_list;
    private List<List_Item> list_items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free);
        //변수 설정
        Intent intent = getIntent();
        String freelist = intent.getStringExtra("freelist");
        listView = (ListView)findViewById(R.id.write_list);
        list_items = new ArrayList<List_Item>();
        adapter_list = new Adapter_list(getApplicationContext(),list_items);
        listView.setAdapter(adapter_list);
        Button button = findViewById(R.id.write_free);
        Button button1 = findViewById(R.id.search_free);
        //메인 소스
        try{
            JSONArray jsonArray = new JSONArray(freelist);
            int count = 0;
            String title;
            String time;
            String cnt;
            while(count < jsonArray.length()){
                JSONObject jsonObject = jsonArray.getJSONObject(count);
                title = jsonObject.getString("title");
                time = jsonObject.getString("time");
                cnt = Integer.toString(jsonObject.getInt("hits"));
                List_Item list_item1 = new List_Item(title,time,cnt);
                list_items.add(list_item1);
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });

        //검색

        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeActivity.this, FreeSearchActivity.class);
                startActivity(intent);
            }
        });*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FreeActivity.this, ViewActivity.class);
                intent.putExtra("title",list_items.get(position).title);
                startActivity(intent);

            }
        });

    }
}
