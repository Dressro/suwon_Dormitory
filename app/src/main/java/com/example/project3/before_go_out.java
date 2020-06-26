package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

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

public class before_go_out extends AppCompatActivity {
    private ListView listView;
    private gooutlistAdapter gooutlistadapter;
    private List<gooutlist> gooutlists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_go_out);
        //변수 설정
        Intent intent = getIntent();
        final String studentnum = intent.getStringExtra("studentnum");
        String go_out_list = intent.getStringExtra("gooutlist");
        final String name = intent.getStringExtra("name");
        final String roomnum = intent.getStringExtra("roomnum");
        listView = (ListView)findViewById(R.id.go_out_list);
        gooutlists = new ArrayList<gooutlist>();
        gooutlistadapter = new gooutlistAdapter(getApplicationContext(),gooutlists);
        listView.setAdapter(gooutlistadapter);
        Button go_out_button = (Button)findViewById(R.id.write_go_out);
        //메인 소스
        try{
            JSONArray jsonArray = new JSONArray(go_out_list);
            int count=0;
            String student;
            String content;
            while(count<jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);
                student = object.getString("studentnum");
                content = object.getString("gooutcomment");
                if(student.equals(studentnum)){
                    gooutlist gooutlist = new gooutlist(student,content);
                    gooutlists.add(gooutlist);
                }
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        go_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(before_go_out.this,go_out.class);
                intent.putExtra("studentnum",studentnum);
                intent.putExtra("name",name);
                intent.putExtra("roomnum",roomnum);
                startActivity(intent);
            }
        });
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(before_go_out.this, NoticeView.class);
                intent.putExtra("content",gooutlists.get(position).content);
                startActivity(intent);

            }
        });*/

    }
}
