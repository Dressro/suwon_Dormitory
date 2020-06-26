package com.example.project3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FreeActivitycontent_Request extends StringRequest {
    final static private String URL = "http://14.37.200.80/project/contentlist.php";
    private Map<String,String> para;
    public FreeActivitycontent_Request(String title , Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        para = new HashMap<>();
        para.put("title",title);
    }
    @Override
    public Map<String,String> getParams() {return para;}
}
