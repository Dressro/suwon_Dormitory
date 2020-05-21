package com.example.bulletin_board;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WriteActivity extends AppCompatActivity {
    private EditText Title;
    private EditText Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Title = findViewById(R.id.title);
        Content = findViewById(R.id.content);

        //글 등록하면 뒤로가기
        Button button = findViewById(R.id.Enroll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //제목/내용저장
                onBackPressed();
            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}