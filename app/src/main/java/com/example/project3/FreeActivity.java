package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FreeActivity extends AppCompatActivity {
    private ListView listView;
    private Adapter_list adapter_list;
    private List<List_Item> list_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free);

        listView = (ListView) findViewById(R.id.write_list);
        list_item = new ArrayList<List_Item>();
        adapter_list = new Adapter_list(getApplicationContext(),list_item);
        listView.setAdapter(adapter_list);

        Button write = findViewById(R.id.write_free);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });

        //검색
        Button search = findViewById(R.id.search_free);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeActivity.this, FreeSearchActivity.class);
                startActivity(intent);
            }
        });



    }
}
