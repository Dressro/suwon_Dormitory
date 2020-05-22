package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoticeView extends AppCompatActivity {
    Animation anim_up;
    Animation anim_down;
    LinearLayout page;
    TextView title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_view);

        page = findViewById(R.id.page); //글/댓글 작성자가 자신의 글/댓글에서 ...버튼을 눌렀을 때 나오는 수정/삭제 버튼

        anim_up = AnimationUtils.loadAnimation(this,R.anim.translate_up);
        anim_down = AnimationUtils.loadAnimation(this,R.anim.translate_down);

        //관리자용
        Button button = findViewById(R.id.notice_post_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.setVisibility(View.VISIBLE);
                page.startAnimation(anim_up);
            }
        });
        //작성자용 버튼
        Button button1 = findViewById(R.id.notice_close);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.startAnimation(anim_down);
                page.setVisibility(View.INVISIBLE);
            }
        });
        Button button2 = findViewById(R.id.notice_post_mod);
        Button button3 = findViewById(R.id.notice_post_del);

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.startAnimation(anim_down);
                page.setVisibility(View.INVISIBLE);
            }
        });

        title = findViewById(R.id.notice_title);
        content = findViewById(R.id.notice_content);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}
