package com.example.project3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
//공지리스트
public class Adapter_notice extends BaseAdapter {
    private Context context;
    private List<Notice_list> notice_list;

    public Adapter_notice(Context context, List<Notice_list> notice_list) {
        this.context = context;
        this.notice_list = notice_list;
    }

    @Override
    public int getCount() {
        return notice_list.size();
    }

    @Override
    public Object getItem(int position) {
        return notice_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.noticelistview,null);
        TextView list_title_text = v.findViewById(R.id.no_list_title);
        TextView list_time_text = v.findViewById(R.id.no_list_time);

        list_title_text.setText(notice_list.get(position).getTitle());
        list_time_text.setText(notice_list.get(position).getTime());

        v.setTag(notice_list.get(position).getTitle());

        return v;
    }
}
