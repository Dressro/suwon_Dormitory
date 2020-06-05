package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project3.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Adapter_list extends BaseAdapter {
    private Context context;
    private List<List_Item> list_items;

    public Adapter_list(Context context, List<List_Item> list_items) {
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
        View v = View.inflate(context,R.layout.list_item,null);
        TextView list_title_text = v.findViewById(R.id.list_title);
        TextView list_time_text = v.findViewById(R.id.list_time);
        TextView list_cnt_text = v.findViewById(R.id.list_cnt);

        list_title_text.setText(list_items.get(position).getTitle());
        list_time_text.setText(list_items.get(position).getTime());
        list_cnt_text.setText(list_items.get(position).getNum());

        v.setTag(list_items.get(position).getTitle());

        return v;
    }
}
