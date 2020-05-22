package com.example.project3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Noticewiter_Request extends StringRequest {
    final static private String URL = "http:///project/noticeenroll.php";
    private Map<String,String> para;
    public Noticewiter_Request(String subtitle, String title, String content, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        para = new HashMap<>();
        para.put("subtitle",subtitle);
        para.put("title",title);
        para.put("content",content);
    }
    @Override
    public Map<String,String> getParams() {return para;}

}
