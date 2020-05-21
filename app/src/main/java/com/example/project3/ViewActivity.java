package com.example.bulletin_board;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    RelativeLayout header;
    private ListView listView;
    Animation anim_up;
    Animation anim_down;
    LinearLayout page, page2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        page = findViewById(R.id.page); //글/댓글 작성자가 자신의 글/댓글에서 ...버튼을 눌렀을 때 나오는 수정/삭제 버튼
        page2 = findViewById(R.id.page2); //다른사람의 글/댓글 ...버튼을 눌렀을 때 나오는 내용복사/신고 버튼

        anim_up = AnimationUtils.loadAnimation(this,R.anim.translate_up);
        anim_down = AnimationUtils.loadAnimation(this,R.anim.translate_down);

        //작성자용
        Button button = findViewById(R.id.post_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.setVisibility(View.VISIBLE);
                page.startAnimation(anim_up);
            }
        });
        //작성자용 버튼
        Button button1 = findViewById(R.id._close);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.startAnimation(anim_down);
                page.setVisibility(View.INVISIBLE);
            }
        });
        Button button2 = findViewById(R.id.post_mod);
        Button button3 = findViewById(R.id.post_del);

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.startAnimation(anim_down);
                page.setVisibility(View.INVISIBLE);
            }
        });
        //타게시물/댓글 버튼
        Button button11 = findViewById(R.id.post_copy);
        Button button22 = findViewById(R.id.post_rep);
        Button button33 = findViewById(R.id._close2);
        button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page2.startAnimation(anim_down);
                page2.setVisibility(View.INVISIBLE);
            }
        });

        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page2.startAnimation(anim_down);
                page2.setVisibility(View.INVISIBLE);
            }
        });

        listView = findViewById(R.id.comm_list);

        header = (RelativeLayout)getLayoutInflater().inflate(R.layout.header, null, false);
        listView.addHeaderView(header);

        TextView title = header.findViewById(R.id.view_title);
        TextView content = header.findViewById(R.id.view_content);
        TextView count = header.findViewById(R.id.comm_cnt);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
