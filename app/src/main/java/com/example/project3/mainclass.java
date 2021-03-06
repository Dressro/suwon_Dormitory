package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class mainclass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainclass);
        Intent intent = getIntent();
        // 변수
        final String studentnum = intent.getStringExtra("studentnum");
        final String name = intent.getStringExtra("name");
        final String roomnum = intent.getStringExtra("roomnum");
        int plus = intent.getIntExtra("plus",0);
        int minus = intent.getIntExtra("minus",0);
        int total = intent.getIntExtra("total",0);
        Button noticeButton = (Button)findViewById(R.id.button);
        Button go_outButton = (Button)findViewById(R.id.button2);
        Button numButton = (Button)findViewById(R.id.button3);
        Button free_tableButton = (Button)findViewById(R.id.button4);
        Button gradeaddButton = (Button)findViewById(R.id.button5);
        TextView nameText = (TextView)findViewById(R.id.nameText);
        TextView totalText = (TextView)findViewById(R.id.totalText);
        TextView studentText = (TextView)findViewById(R.id.studentnumText);
        TextView roomText = (TextView)findViewById(R.id.roomnumText);
        TextView rootText = (TextView)findViewById(R.id.rootText);
        TextView studentnumText = (TextView)findViewById(R.id.studentText);
        //화면 View 설정
        roomText.setText("방 번호: " + roomnum);
        studentText.setText(studentnum);
        nameText.setText(name);
        totalText.setText("상점 벌점 합계 : "+ String.valueOf(total));

        //TextView 색깔 설정
        roomText.setTextColor(Color.BLACK);
        studentText.setTextColor(Color.BLACK);
        nameText.setTextColor(Color.BLACK);
        if(total > 0) {
            totalText.setTextColor(Color.GREEN);
        }
        else if(total < 0){
            totalText.setTextColor(Color.RED);
        }
        else{
            totalText.setTextColor(Color.BLACK);
        }
        // 버튼 설정
        if(studentnum.equals("root")){
            numButton.setVisibility(View.INVISIBLE);
            gradeaddButton.setVisibility(View.VISIBLE);
            studentnumText.setVisibility(View.INVISIBLE);
            rootText.setVisibility(View.VISIBLE);
        }
        else{
            numButton.setVisibility(View.VISIBLE);
            gradeaddButton.setVisibility(View.INVISIBLE);
            studentnumText.setVisibility(View.VISIBLE);
            rootText.setVisibility(View.INVISIBLE);
        }
        // 메인 소스
        noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray noticelist = jsonObject.getJSONArray("response");
                            Intent intent = new Intent(mainclass.this,Notice.class);
                            intent.putExtra("studentnum",studentnum);
                            intent.putExtra("name",name);
                            intent.putExtra("list",noticelist.toString());
                            startActivity(intent);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                mainclass_notice_Request mainclass_notice_request = new mainclass_notice_Request(respon);
                RequestQueue queue = Volley.newRequestQueue(mainclass.this);
                queue.add(mainclass_notice_request);
            }
        });
        numButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> respon =  new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("response");
                            Intent intent =new Intent(mainclass.this,Gradeclass.class);
                            intent.putExtra("gradelist",array.toString());
                            intent.putExtra("studentnum",studentnum);
                            startActivity(intent);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                mainclass_grade_Request mainclass_grade_request = new mainclass_grade_Request(respon);
                RequestQueue queue = Volley.newRequestQueue(mainclass.this);
                queue.add(mainclass_grade_request);
            }

        });
        gradeaddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mainclass.this,gradeaddclass.class);
                startActivity(intent);
            }
        });
        go_outButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("response");
                            Intent intent = new Intent(mainclass.this,before_go_out.class);
                            intent.putExtra("gooutlist",array.toString());
                            intent.putExtra("studentnum",studentnum);
                            intent.putExtra("roomnum",roomnum);
                            intent.putExtra("name",name);
                            startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                mainclass_go_out_Request mainclass_go_out_request = new mainclass_go_out_Request(respon);
                RequestQueue requestQueue = Volley.newRequestQueue(mainclass.this);
                requestQueue.add(mainclass_go_out_request);
            }
        });
        free_tableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("response");
                            Intent intent = new Intent(mainclass.this,FreeActivity.class);
                            intent.putExtra("freelist",array.toString());
                            intent.putExtra("studentnum",studentnum);
                            startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                mainclass_FreeWrite_Request mainclass_freeWrite_request = new mainclass_FreeWrite_Request(respon);
                RequestQueue queue = Volley.newRequestQueue(mainclass.this);
                queue.add(mainclass_freeWrite_request);
            }
        });
    }
}
