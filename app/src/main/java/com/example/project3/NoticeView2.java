package com.example.project3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
//관
public class NoticeView2 extends AppCompatActivity {
    TextView title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_view2);

        title = findViewById(R.id.std_title);
        content = findViewById(R.id.std_content);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}