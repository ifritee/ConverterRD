package com.example.ifritee.rdconverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;

public class FileConvertAdapter extends BaseAdapter {
    final Context _Context_o;
    final ArrayList<File> _Files_lst;

    public FileConvertAdapter(Context Context_o, ArrayList<File> Files_lst) {
        this._Context_o = Context_o;
        this._Files_lst = Files_lst;
    }

    private class ViewHolder {
        TextView _TextViewFileArray_o;

        public ViewHolder(View view) {
            _TextViewFileArray_o = view.findViewById(R.id.fdaFileName);
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
            convertView = LayoutInflater.from(_Context_o).inflate(R.layout.file_convert_adapter, parent, false);
            ViewHolder_o = new ViewHolder(convertView);
            convertView.setTag(ViewHolder_o);
        } else {
            ViewHolder_o = (ViewHolder) convertView.getTag();
        }
        TextView textViewItemName = (TextView)convertView.findViewById(R.id.fdaFileName);
        File currentItem = (File)getItem(position);
        textViewItemName.setText(currentItem.getName());
        return convertView;
    }
}
