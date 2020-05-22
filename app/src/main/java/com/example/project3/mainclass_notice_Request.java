package com.example.project3;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class mainclass_notice_Request extends StringRequest {
    final static private String URL = "http:///project/noticelist.php";
    Map<String,String> para;
    public mainclass_notice_Request(Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        para = new HashMap<>();
    }
    @Override
    public Map<String,String> getParams() {return para;}
}
