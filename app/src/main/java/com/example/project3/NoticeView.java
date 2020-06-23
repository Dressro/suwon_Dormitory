package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class NoticeView extends AppCompatActivity {
    Animation anim_up;
    Animation anim_down;
    LinearLayout page;
    boolean pageopen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_view);
        //변수 설정
        page = (LinearLayout) findViewById(R.id.page); //글/댓글 작성자가 자신의 글/댓글에서 ...버튼을 눌렀을 때 나오는 수정/삭제 버튼
        final TextView titleText = (TextView)findViewById(R.id.notice_title);
        final TextView contentText = (TextView)findViewById(R.id.notice_content);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        Button button = findViewById(R.id.notice_post_button);
        Button button1 = findViewById(R.id.notice_close);
        Button button2 = findViewById(R.id.notice_post_mod);
        Button button3 = findViewById(R.id.notice_post_del);

        anim_up = AnimationUtils.loadAnimation(this,R.anim.translate_up);
        anim_down = AnimationUtils.loadAnimation(this,R.anim.translate_down);



        //
        Response.Listener<String> respon = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        String title1 = jsonObject.getString("title");
                        String content = jsonObject.getString("content");
                        titleText.setText(title1);
                        contentText.setText(content);
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(NoticeView.this);
                        builder.setMessage("정보가 없습니다.")
                                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .create()
                                .show();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        NoticeView_Request noticeView_request = new NoticeView_Request(title,respon);
        RequestQueue queue = Volley.newRequestQueue(NoticeView.this);
        queue.add(noticeView_request);
        //관리자용
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.startAnimation(anim_up);
                page.setVisibility(View.VISIBLE);
            }
        });
        //작성자용 버튼

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.startAnimation(anim_down);
                page.setVisibility(View.GONE);
            }
        });


        /*page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.startAnimation(anim_down);
                page.setVisibility(View.INVISIBLE);
            }
        });*/



    }

}
