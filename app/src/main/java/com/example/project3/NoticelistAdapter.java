package com.example.project3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoticelistAdapter extends BaseAdapter {
    private Context context;
    private List<Noticelist> noticelists;

    public NoticelistAdapter(Context context, List<Noticelist> noticelists) {
        this.context = context;
        this.noticelists = noticelists;
    }

    @Override
    public int getCount() {
        return noticelists.size();
    }

    @Override
    public Object getItem(int position) {
        return noticelists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.noticelistview,null);
        TextView subtitle_text = v.findViewById(R.id.subtitle_Text);
        TextView title_text = v.findViewById(R.id.title_Text);

        subtitle_text.setText(noticelists.get(position).getSubtitle());
        title_text.setText(noticelists.get(position).getTitle());

        v.setTag(noticelists.get(position).getTitle());

        return v;
    }
}
