package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FreeActivity extends AppCompatActivity {
    private ListView listView;
    private Adapter_list adapter_list;
    private List<List_Item> list_items;
    String freelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free);
        //변수 설정
        Intent intent = getIntent();
        freelist = intent.getStringExtra("freelist");
        final String studentnum = intent.getStringExtra("studentnum");
        listView = (ListView)findViewById(R.id.write_list);
        list_items = new ArrayList<List_Item>();
        adapter_list = new Adapter_list(getApplicationContext(),list_items);
        listView.setAdapter(adapter_list);
        Button button = findViewById(R.id.write_free);
        Button button1 = findViewById(R.id.search_free);
        Button free_refresh = (Button)findViewById(R.id.free_refresh);
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
                intent.putExtra("studentnum",studentnum);
                startActivity(intent);
            }
        });
        free_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("response");
                            freelist = array.toString();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                mainclass_FreeWrite_Request mainclass_freeWrite_request = new mainclass_FreeWrite_Request(respon);
                RequestQueue queue = Volley.newRequestQueue(FreeActivity.this);
                queue.add(mainclass_freeWrite_request);
                list_items.clear();
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
                adapter_list.notifyDataSetChanged();
            }
        });
        //검색
        /*button1.setOnClickListener(new View.OnClickListener() {
>>>>>>> teajun
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeActivity.this, FreeSearchActivity.class);
                startActivity(intent);
            }
        });*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String title1 = list_items.get(position).title;
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("response");
                            Intent intent = new Intent(FreeActivity.this, ViewActivity.class);
                            intent.putExtra("title",title1);
                            intent.putExtra("studentnum",studentnum);
                            intent.putExtra("array",array.toString());
                            startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                FreeActivitycontent_Request freeActivitycontent_request = new FreeActivitycontent_Request(list_items.get(position).title,respon);
                RequestQueue queue = Volley.newRequestQueue(FreeActivity.this);
                queue.add(freeActivitycontent_request);

            }
        });



    }
}
