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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.project3.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    RelativeLayout header;
    private ListView listView;
    private Adapter_comm adapter_comm;
    private List<Comment_list> comment_lists;
    Animation anim_up;
    Animation anim_down;
    LinearLayout page, page2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        final String title = intent.getStringExtra("title");
        final String contentlist = intent.getStringExtra("array");
        final String studentnum = intent.getStringExtra("studentnum");

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

        final EditText comment = (EditText)findViewById(R.id.comment);
        final Button comm_btn = findViewById(R.id.comm_ok);

        listView = (ListView) findViewById(R.id.comm_list);
        header = (RelativeLayout)getLayoutInflater().inflate(R.layout.header, null, false);
        listView.addHeaderView(header);

        comment_lists = new ArrayList<Comment_list>();
        adapter_comm = new Adapter_comm(getApplicationContext(),comment_lists);
        listView.setAdapter(adapter_comm);

        //댓글 등록할 때 시간도 같이
        comm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comm_txt = comment.getText().toString();
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean success = jsonObject.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(ViewActivity.this);
                                builder.setMessage("댓글을 등록했습니다.")
                                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                comment.setText(null);
                                            }
                                        })
                                        .create()
                                        .show();
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                View_Request view_request = new View_Request(title,comm_txt,respon);
                RequestQueue queue = Volley.newRequestQueue(ViewActivity.this);
                queue.add(view_request);
            }
        });

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.startAnimation(anim_down);
                page.setVisibility(View.INVISIBLE);
            }
        });

        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page2.startAnimation(anim_down);
                page2.setVisibility(View.INVISIBLE);
            }
        });

        final TextView title_text = header.findViewById(R.id.view_title);
        final TextView content_text = header.findViewById(R.id.view_content);
        final TextView count = header.findViewById(R.id.comm_cnt);
        // title , content 텍스트 변경
        Response.Listener<String> respon = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean success = jsonObject.getBoolean("success");
                    if(success){
                        String title = jsonObject.getString("title");
                        String content = jsonObject.getString("content");
                        title_text.setText(title);
                        content_text.setText(content);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewActivity.this);
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
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        FreeActivityView_Request freeActivityView_request = new FreeActivityView_Request(studentnum,title,respon);
        RequestQueue queue = Volley.newRequestQueue(ViewActivity.this);
        queue.add(freeActivityView_request);
        // 댓글 리스트 가지고 와서 적용
        try{
            JSONArray jsonArray = new JSONArray(contentlist);
            int count1 = 0;
            String content;
            String time;
            while(count1 + 3 < jsonArray.length() + 3){
                JSONObject jsonObject = jsonArray.getJSONObject(count1);
                content = jsonObject.getString("content");
                time = jsonObject.getString("time");
                Comment_list list_item1 = new Comment_list(content,time);
                comment_lists.add(list_item1);
                count1++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
