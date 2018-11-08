package com.example.markwang.scrollview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListViewAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<DataHolder> dataHolderArrayList;
    private View.OnClickListener clickListener;
    private CustomOnScrollListener customOnScrollListener;

    public CustomListViewAdapter(Context context, ArrayList<DataHolder> dataHolderArrayList) {

        layoutInflater = LayoutInflater.from(context);
        this.dataHolderArrayList = dataHolderArrayList;
    }

    @Override
    public int getCount() {
        return dataHolderArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataHolderArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = layoutInflater.inflate(R.layout.custom_view, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.linearLayout=convertView.findViewById(R.id.customViewRoot);
            viewHolder.leftTextView = convertView.findViewById(R.id.textView);
            viewHolder.rightTextView = convertView.findViewById(R.id.textView2);
            viewHolder.rightTextView.setOnClickListener(clickListener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        dataHolderArrayList.get(position).itemRootLinearLayout = convertView.findViewById(R.id.customViewRoot);
        dataHolderArrayList.get(position).itemRootLinearLayout.scrollTo(0, 0);
        viewHolder.leftTextView.setText(dataHolderArrayList.get(position).leftText);
        viewHolder.rightTextView.setText(dataHolderArrayList.get(position).rightText);
        viewHolder.linearLayout.setCustomOnScrollListener(customOnScrollListener);
        return convertView;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    public void setCustomOnScrollListener(CustomOnScrollListener customOnScrollListener){
        this.customOnScrollListener=customOnScrollListener;
    }

    public void deleteDataHolder(int position) {
        dataHolderArrayList.remove(position);
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public CustomLinearLayout linearLayout;
        public TextView leftTextView;
        public TextView rightTextView;
    }
}
