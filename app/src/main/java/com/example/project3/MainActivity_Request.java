package com.example.project3;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity_Request extends StringRequest {
    final static private String URL = "http://14.37.200.80/project/projectlogin.php";
    private Map<String,String> para;
    public MainActivity_Request(String studentnum, String Password, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        para = new HashMap<>();
        para.put("studentnum",studentnum);
        para.put("Password",Password);
    }
    @Override
    public Map<String,String> getParams() {return para;}
}
