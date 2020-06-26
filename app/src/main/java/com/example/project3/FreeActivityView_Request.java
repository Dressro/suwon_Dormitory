package com.example.project3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FreeActivityView_Request extends StringRequest {
    final static private String URL = "http://14.37.200.80/project/freeview.php";
    private Map<String,String> para;
    public FreeActivityView_Request(String studentnum , String title , Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        para = new HashMap<>();
        para.put("studentnum",studentnum);
        para.put("title",title);
    }
    @Override
    public Map<String,String> getParams() {return para;}
}
