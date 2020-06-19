package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        final EditText Title = findViewById(R.id.title);
        final EditText Content = findViewById(R.id.content);

        //글 등록하면 뒤로가기
        Button button = findViewById(R.id.Enroll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String free_title = Title.getText().toString();
                String free_content = Content.getText().toString();
                //버튼 누르면 등록 시간도 같이 넘어가게
                onBackPressed();
            }
        });

    }
}