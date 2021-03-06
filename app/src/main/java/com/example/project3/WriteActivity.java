package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Queue;

public class WriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Intent intent = getIntent();
        final FreeActivity freeActivity = (FreeActivity)FreeActivity.FreeActiv;
        final String studentnum = intent.getStringExtra("studentnum");

        final EditText freeWriteTitle = findViewById(R.id.Freetitle);
        final EditText freeWriteContent = findViewById(R.id.Freecontent);

        //글 등록하면 뒤로가기
        Button button = findViewById(R.id.Enroll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String free_title = freeWriteTitle.getText().toString();
                String free_content = freeWriteContent.getText().toString();
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(WriteActivity.this);
                                builder.setMessage("글쓰기 등록 했습니다.")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Response.Listener<String> respon = new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        try{
                                                            JSONObject jsonObject = new JSONObject(response);
                                                            JSONArray array = jsonObject.getJSONArray("response");
                                                            Intent intent = new Intent(WriteActivity.this,FreeActivity.class);
                                                            intent.putExtra("freelist",array.toString());
                                                            intent.putExtra("studentnum",studentnum);
                                                            freeActivity.finish();
                                                            startActivity(intent);
                                                            finish();
                                                        }catch (Exception e){
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                };
                                                mainclass_FreeWrite_Request mainclass_freeWrite_request = new mainclass_FreeWrite_Request(respon);
                                                RequestQueue queue = Volley.newRequestQueue(WriteActivity.this);
                                                queue.add(mainclass_freeWrite_request);
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(WriteActivity.this);
                                builder.setMessage("글쓰기 등록을 못했습니다.")
                                        .setNegativeButton("확인",null)
                                        .create()
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                Writer_Request writer_request = new Writer_Request(studentnum,free_title,free_content,respon);
                RequestQueue queue = Volley.newRequestQueue(WriteActivity.this);
                queue.add(writer_request);
            }
        });

    }
}