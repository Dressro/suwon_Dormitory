package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {
    private ListView listview;
    private NoticelistAdapter noticelistAdapter;
    private List<Noticelist> noticelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        //변수
        Intent intent = getIntent();
        Button write_button = findViewById(R.id.writebutton);
        Button search_button = findViewById(R.id.search);
        String studentnum = intent.getStringExtra("studentnum");
        String notice_list = intent.getStringExtra("list");
        listview = (ListView)findViewById(R.id.NoticeList);
        noticelist = new ArrayList<Noticelist>();
        noticelistAdapter = new NoticelistAdapter(getApplicationContext(),noticelist);
        listview.setAdapter(noticelistAdapter);

        //설정
        if(studentnum.equals("root")){
            write_button.setVisibility(View.VISIBLE);
        }
        else{
            write_button.setVisibility(View.INVISIBLE);
        }


        //메인 소스
        try{
            JSONArray jsonArray = new JSONArray(notice_list);
            int count = 0;
            String subtitle;
            String title;
            while(count < jsonArray.length()){
                JSONObject object= jsonArray.getJSONObject(count);
                subtitle = object.getString("subtitle");
                title = object.getString("title");
                Noticelist noticelist1 = new Noticelist(subtitle,title);
                noticelist.add(noticelist1);
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        write_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, NoticewriterActivity.class);
                startActivity(intent);
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


    }
}
