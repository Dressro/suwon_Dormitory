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

public class Adapter_comm extends BaseAdapter {
    private Context context;
    private ArrayList<String> arrayList;
    private ViewHolder viewHolder;

    public Adapter_comm(Context context, ArrayList arrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // View에 Data 세팅
        viewHolder.txt_comment.setText(arrayList.get(position));
        viewHolder.txt_time.setText(arrayList.get(position));
        viewHolder.txt_btn.setText(arrayList.get(position));

        return convertView;
    }
    public class ViewHolder {
        private TextView txt_comment;
        private TextView txt_time;
        private Button txt_btn;

        public ViewHolder(View convertView) {
            txt_comment = (TextView) convertView.findViewById(R.id.comm_comment);
            txt_time = (TextView) convertView.findViewById(R.id.comm_time);
            txt_btn = (Button) convertView.findViewById(R.id.comm_button);
        }

    }
}
