package com.example.project3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class gradelistAdapter extends BaseAdapter {
    private Context context;
    private List<gradelist> gradelists;

    public gradelistAdapter(Context context, List<gradelist> gradelists) {
        this.context = context;
        this.gradelists = gradelists;
    }

    @Override
    public int getCount() {
        return gradelists.size();
    }

    @Override
    public Object getItem(int position) {
        return gradelists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.gradelistview,null);
        TextView gradename = (TextView)v.findViewById(R.id.gradename);
        TextView gradenumber = (TextView)v.findViewById(R.id.gradenumber);
        TextView gradesum = (TextView)v.findViewById(R.id.gradesum);
        TextView gradecontent = (TextView)v.findViewById(R.id.gradContent);

        gradecontent.setText(gradelists.get(position).getGradecontent());
        gradename.setText(gradelists.get(position).getGradename());
        gradenumber.setText(gradelists.get(position).getGradenumber());
        gradesum.setText(gradelists.get(position).getGradesum());

        v.setTag(gradelists.get(position).getGradename());

        return v;
    }
}
