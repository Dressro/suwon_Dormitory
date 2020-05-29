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

import org.json.JSONObject;

public class go_out extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_out);
        Intent intent = getIntent();
        //변수 설정
        final String studentnum = intent.getStringExtra("studentnum");
        final String studentname = intent.getStringExtra("name");
        final String roomnum = intent.getStringExtra("roomnum");
        TextView studentnumText = (TextView)findViewById(R.id.Text);
        TextView studentnameText = (TextView)findViewById(R.id.editText3);
        TextView roomnumText = (TextView)findViewById(R.id.editText4);
        final EditText studentmajorText = (EditText)findViewById(R.id.editText2);
        final EditText gooutcommentText = (EditText)findViewById(R.id.editText5);
        Button enrollbutton = (Button)findViewById(R.id.gooutenroll);

        //메인
        studentnameText.setText(studentname);
        studentnumText.setText(studentnum);
        roomnumText.setText(roomnum);
        enrollbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String studentmajor = studentmajorText.getText().toString();
                final String gooutcomment = gooutcommentText.getText().toString();
                Response.Listener<String> respon = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(go_out.this);
                                builder.setMessage("외출 등록을 성공했습니다")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(go_out.this,mainclass.class);
                                                intent.putExtra("studentnum",studentnum);
                                                intent.putExtra("name",studentname);
                                                intent.putExtra("roomnum",roomnum);
                                                startActivity(intent);
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
                go_out_Request go_out_request = new go_out_Request(studentnum,studentmajor,studentname,roomnum,gooutcomment,respon);
                RequestQueue queue = Volley.newRequestQueue(go_out.this);
                queue.add(go_out_request);
            }
        });
    }
}
