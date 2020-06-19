package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.project3.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_comm extends BaseAdapter {
    private Context context;
    private List<Comment_list> list_items;

    public Adapter_comm(Context context, List<Comment_list> list_items) {
        this.context = context;
        this.list_items = list_items;
    }

    @Override
    public int getCount() {
        return list_items.size();
    }

    @Override
    public Object getItem(int position) {
        return list_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.comment_list,null);
        TextView comm_comment_text = v.findViewById(R.id.comm_comment);
        TextView comm_time_text = v.findViewById(R.id.comm_time);
        Button comm_btn = v.findViewById(R.id.comm_button);

        comm_comment_text.setText(list_items.get(position).getComment());
        comm_time_text.setText(list_items.get(position).getTime());
        comm_btn.setText((CharSequence) list_items.get(position).getButton());

        v.setTag(list_items.get(position).getComment());

        return v;
    }
}
