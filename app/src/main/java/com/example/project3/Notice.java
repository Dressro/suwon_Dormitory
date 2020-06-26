package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.project3.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Notice extends AppCompatActivity {
    private ListView listView;
    private Adapter_notice adapter_list;
    private List<Notice_list> list_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        //변수 설정
        Button writebutton = findViewById(R.id.write_notice);
        Button searchbutton = findViewById(R.id.search_notice);
        Intent intent = getIntent();
        String studentnum = intent.getStringExtra("studentnum");
        String notice_list = intent.getStringExtra("list");
        listView = (ListView) findViewById(R.id.notice_list);
        list_item = new ArrayList<Notice_list>();
        adapter_list = new Adapter_notice(getApplicationContext(),list_item);
        listView.setAdapter(adapter_list);
        //설정
        if(studentnum.equals("root")){
            writebutton.setVisibility(View.VISIBLE);
        }
        else{
            writebutton.setVisibility(View.INVISIBLE);
        }

        //메인
        try{
            JSONArray jsonArray = new JSONArray(notice_list);
            int count = 0;
            String title;
            String time;
           while(count < jsonArray.length()){
                JSONObject jsonObject = jsonArray.getJSONObject(count);
                title = jsonObject.getString("title");
                time = jsonObject.getString("time");
                Notice_list list_item1 = new Notice_list(title,time);
                list_item.add(list_item1);
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //글쓰기

        writebutton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notice.this, NoticewriterActivity.class);
                startActivity(intent);
            }
        });

        //검색

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notice.this, NoticeSearch.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Notice.this, NoticeView.class);
                intent.putExtra("title",list_item.get(position).title);
                startActivity(intent);

            }
        });

    }
}