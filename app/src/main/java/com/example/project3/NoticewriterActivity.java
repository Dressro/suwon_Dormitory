package com.example.project3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class NoticewriterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticewriter);
        //변수 선언
        final EditText title_Text = (EditText) findViewById(R.id.title);
        final EditText main_content = (EditText)findViewById(R.id.content);
        Button Enroll_button = (Button)findViewById(R.id.Enroll);
        final Spinner subtitle_spinner = (Spinner)findViewById(R.id.subtitlespinner);

        //메인소스
        Enroll_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_Text.getText().toString();
                String content = main_content.getText().toString();
                String subtitle = subtitle_spinner.getSelectedItem().toString();
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            boolean success = object.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(NoticewriterActivity.this);
                                builder.setMessage("공지 등록 했습니다.")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(NoticewriterActivity.this);
                                builder.setMessage("공지 등록을 못했습니다.")
                                        .setNegativeButton("확인",null)
                                        .create()
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                Noticewiter_Request noticewiter_request = new Noticewiter_Request(subtitle,title,content,respon);
                RequestQueue queue = Volley.newRequestQueue(NoticewriterActivity.this);
                queue.add(noticewiter_request);
            }
        });
    }
}
