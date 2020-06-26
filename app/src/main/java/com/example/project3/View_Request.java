package com.example.project3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class View_Request extends StringRequest {
    final static private String URL = "http://14.37.200.80/project/content.php";
    private Map<String,String> para;
    public View_Request(String title, String content , Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        para = new HashMap<>();
        para.put("title",title);
        para.put("content",content);
    }
    @Override
    public Map<String,String> getParams() {return para;}
}
