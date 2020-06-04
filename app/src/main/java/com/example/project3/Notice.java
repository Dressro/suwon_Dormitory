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

import java.util.ArrayList;
import java.util.List;

public class Notice extends AppCompatActivity {
    private ListView listView;
    private Adapter_list adapter_list;
    private List<List_Item> list_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        //변수 설정
        listView = findViewById(R.id.notice_list);
        list_item = new ArrayList<List_Item>();
        adapter_list = new Adapter_list(getApplicationContext(),list_item);
        listView.setAdapter(adapter_list);
        //메인
        //글쓰기
        Button button = findViewById(R.id.write_notice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notice.this, NoticeWrite.class);
                startActivity(intent);
            }
        });

        //검색
        Button button1 = findViewById(R.id.search_notice);
        button1.setOnClickListener(new View.OnClickListener() {
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
                //게시글로 이동
                startActivity(intent);

            }
        });

    }
}