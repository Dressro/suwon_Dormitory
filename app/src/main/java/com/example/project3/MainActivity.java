package com.example.project3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //변수
        Button loginbutton = (Button)findViewById(R.id.loginbutton);
        final EditText idText = (EditText) findViewById(R.id.userID);
        final EditText passwordText = (EditText)findViewById(R.id.userPassword);

        //메인 소스
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String studentnum = idText.getText().toString();
                final String Password = passwordText.getText().toString();
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            final String name = jsonObject.getString("Name");
                            final int roomnum = jsonObject.getInt("roomnum");
                            final int plus = jsonObject.getInt("plus");
                            final int minus = jsonObject.getInt("minus");
                            final int total = plus + minus;
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("로그인에 성공했습니다")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(MainActivity.this,mainclass.class);
                                                intent.putExtra("studentnum",studentnum);
                                                intent.putExtra("name",name);
                                                intent.putExtra("plus",plus);
                                                intent.putExtra("minus",minus);
                                                intent.putExtra("total",total);
                                                intent.putExtra("roomnum",String.valueOf(roomnum));
                                                startActivity(intent);
                                                finish();
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("아이디나 비밀번호를 확인 해 주세요")
                                        .setNegativeButton("확인",null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                MainActivity_Request mainActivity_request = new MainActivity_Request(studentnum,Password,respon);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(mainActivity_request);
            }
        });
    }
}
