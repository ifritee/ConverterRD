package com.example.ifritee.rdconverter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class TwoActivityAdapter extends BaseAdapter {
    final Context _Context_o;
    final ArrayList<File> _Files_lst;

    public TwoActivityAdapter(Context Context_o, ArrayList<File> Files_lst) {
        this._Context_o = Context_o;
        this._Files_lst = Files_lst;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView _TextViewFileArray_o;

        public ViewHolder(View view) {
            _TextViewFileArray_o = view.findViewById(R.id.text_view_fd);
        }
    }

    @Override
    public int getCount() {
        return _Files_lst.size();
    }

    @Override
    public Object getItem(int position) {
        return _Files_lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return _Files_lst.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder ViewHolder_o;

        if (convertView == null) {
            convertView = LayoutInflater.from(_Context_o).inflate(R.layout.row_layout, parent, false);
            ViewHolder_o = new ViewHolder(convertView);
            convertView.setTag(ViewHolder_o);
        } else {
            ViewHolder_o = (ViewHolder) convertView.getTag();
        }
        LinearLayout LinearLayout_o = (LinearLayout)convertView.findViewById(R.id.rlLay);
        TextView textViewItemName = (TextView)convertView.findViewById(R.id.text_view_fd);
        ImageView ImageView_o = (ImageView) convertView.findViewById(R.id.ivIcon);
        File currentItem = (File)getItem(position);
        textViewItemName.setText(currentItem.getName());
        if(currentItem.isDirectory()) {
            LinearLayout_o.setBackgroundColor(Color.rgb(180,180,180));
            ImageView_o.setImageResource(R.drawable.dir_action_name);
        }
        else if (currentItem.isFile()) {
            LinearLayout_o.setBackgroundColor(Color.rgb(220,220,220));
            ImageView_o.setImageResource(R.drawable.file_action_name);
        }
        return convertView;
    }
}
