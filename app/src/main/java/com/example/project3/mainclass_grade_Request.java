package com.example.project3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class mainclass_grade_Request extends StringRequest {
    final static private String URL ="http://14.37.200.80/project/gradelist.php";
    private Map<String,String> para;
    public mainclass_grade_Request(Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        para = new HashMap<>();
    }
    @Override
    public Map<String,String> getParams() {return para;}
}
