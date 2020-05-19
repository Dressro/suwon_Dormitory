package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Gradeclass extends AppCompatActivity {
    private ListView listView;
    private gradelistAdapter adapter;
    private List<gradelist> gradeLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradeclass);

        //변수
        Intent intent = getIntent();
        listView = (ListView)findViewById(R.id.gradelists);
        gradeLists = new ArrayList<gradelist>();
        adapter = new gradelistAdapter(getApplicationContext(),gradeLists);
        listView.setAdapter(adapter);
        Button plusbutton = (Button)findViewById(R.id.gradeplusbutton);
        Button minusbutton = (Button)findViewById(R.id.grademinusbutton);
        final String gradelists1 = intent.getStringExtra("gradelist");
        final String get_studentnum = intent.getStringExtra("studentnum");

        //메인 코드


        try{
            JSONArray array = new JSONArray(gradelists1);
            int count = 0 ;
            String gradename;
            String gradestudentnum;
            String gradecontent;
            String gradeplus;
            String grademinus;
            int gradesum = 0;
            while(count < array.length()){
                JSONObject object = array.getJSONObject(count);
                gradestudentnum = object.getString("studentnum");
                gradecontent = object.getString("gradecontent");
                gradeplus = String.valueOf(object.getInt("gradeplus"));
                grademinus = String.valueOf(object.getInt("grademinus"));
                if(Integer.parseInt(gradeplus) > 0){
                    gradename = "상점";
                    if(gradestudentnum.equals(get_studentnum)) {
                        gradesum = gradesum + Integer.parseInt(gradeplus);
                        gradelist gradelist1 = new gradelist(gradename, gradeplus, gradecontent, String.valueOf(gradesum));
                        gradeLists.add(gradelist1);
                    }
                }
                if(Integer.parseInt(grademinus) > 0){
                    gradename = "벌점";
                    if(gradestudentnum.equals(get_studentnum)) {
                        gradesum = gradesum - Integer.parseInt(grademinus);
                        gradelist gradelist1 = new gradelist(gradename, grademinus, gradecontent, String.valueOf(gradesum));
                        gradeLists.add(gradelist1);
                    }
                }
                count++;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        plusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            gradeLists.clear();
            try{
                JSONArray array = new JSONArray(gradelists1);
                int count = 0;
                String gradename;
                String gradestudentnum;
                String gradecontent;
                String gradeplus;
                int gradesum = 0;
                while(count < array.length()){
                    JSONObject object = array.getJSONObject(count);
                    gradestudentnum = object.getString("studentnum");
                    gradecontent = object.getString("gradecontent");
                    gradeplus = String.valueOf(object.getInt("gradeplus"));
                    if(Integer.parseInt(gradeplus) > 0){
                        gradename = "상점";
                        if(gradestudentnum.equals(get_studentnum)) {
                            gradesum = gradesum + Integer.parseInt(gradeplus);
                            gradelist gradelist1 = new gradelist(gradename, gradeplus, gradecontent, String.valueOf(gradesum));
                            gradeLists.add(gradelist1);
                        }
                    }
                    count++;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
            }
        });
        minusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gradeLists.clear();
                try {
                    JSONArray array = new JSONArray(gradelists1);
                    int count = 0;
                    String gradename;
                    String gradestudentnum;
                    String gradecontent;
                    String grademinus;
                    int gradesum = 0;
                    while (count < array.length()) {
                        JSONObject object = array.getJSONObject(count);
                        gradestudentnum = object.getString("studentnum");
                        gradecontent = object.getString("gradecontent");
                        grademinus = String.valueOf(object.getInt("grademinus"));
                        if (Integer.parseInt(grademinus) > 0) {
                            gradename = "벌점";
                            if (gradestudentnum.equals(get_studentnum)) {
                                gradesum = gradesum + Integer.parseInt(grademinus);
                                gradelist gradelist1 = new gradelist(gradename, grademinus, gradecontent, String.valueOf(gradesum));
                                gradeLists.add(gradelist1);
                            }
                        }
                        count++;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


}

