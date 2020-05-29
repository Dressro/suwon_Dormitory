package com.example.project3;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
//
public class NoticeStd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_std);

        //검색
        Button button1 = findViewById(R.id.search_std);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoticeSearch.class);
                startActivity(intent);
            }
        });

        ListView listView = findViewById(R.id.std_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoticeView2.class);
                //게시글로 이동
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
