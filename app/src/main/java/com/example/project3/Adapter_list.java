package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project3.R;

import java.util.ArrayList;

public class Adapter_list extends BaseAdapter {
    private Context context;
    private ArrayList<String> arrayList;
    private ViewHolder viewHolder;

    public Adapter_list(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // View에 Data 세팅
        viewHolder.txt_title.setText(arrayList.get(position));
        viewHolder.txt_time.setText(arrayList.get(position));
        viewHolder.txt_cnt.setText(arrayList.get(position));

        return convertView;
    }
    public class ViewHolder {
        private TextView txt_title;
        private TextView txt_time;
        private TextView txt_cnt;

        public ViewHolder(View convertView) {
            txt_title = (TextView) convertView.findViewById(R.id.list_title);
            txt_time = (TextView) convertView.findViewById(R.id.list_time);
            txt_cnt = (TextView) convertView.findViewById(R.id.list_cnt);
        }
    }
}
