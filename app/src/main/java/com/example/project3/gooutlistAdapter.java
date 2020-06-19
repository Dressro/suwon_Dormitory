package com.example.project3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class gooutlistAdapter extends BaseAdapter {
    private Context context;
    private List<gooutlist> gooutlists;

    public gooutlistAdapter(Context context, List<gooutlist> gooutlists) {
        this.context = context;
        this.gooutlists = gooutlists;
    }

    @Override
    public int getCount() {
        return gooutlists.size();
    }

    @Override
    public Object getItem(int position) {
        return gooutlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.go_out_list,null);
        TextView studentname = v.findViewById(R.id.textView);
        TextView content = v.findViewById(R.id.textView2);

        studentname.setText(gooutlists.get(position).getName());
        content.setText(gooutlists.get(position).getContent());

        v.setTag(gooutlists.get(position).getName());
        return v;
    }
}
