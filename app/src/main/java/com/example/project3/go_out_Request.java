package com.example.project3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class go_out_Request extends StringRequest {
    final static private String URL ="http://14.37.200.80/project/goout.php";
    private Map<String,String> para;
    public go_out_Request(String studentnum,String studentmajor,String studentname, String roomnum,String gooutcomment,Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        para = new HashMap<>();
        para.put("studentnum",studentnum);
        para.put("studentmajor",studentmajor);
        para.put("studentname",studentname);
        para.put("roomnum",roomnum);
        para.put("gooutcomment",gooutcomment);
    }
    @Override
    public Map<String,String> getParams() {return para;}
}
