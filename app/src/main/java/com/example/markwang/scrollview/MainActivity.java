package com.example.markwang.scrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,CustomOnScrollListener {
    CustomListView customListView;
    ArrayList<DataHolder> dataArrayList;
    CustomListViewAdapter customListViewAdapter;
    CustomLinearLayout previousCustomLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customListView=(CustomListView)findViewById(R.id.customListView);


        dataArrayList=new ArrayList();
        for(int i=0;i<20;i++){
            String str="第"+i+"項";
            dataArrayList.add(new DataHolder(str,"delete"));
        }

        customListViewAdapter=new CustomListViewAdapter(MainActivity.this,dataArrayList);
        customListViewAdapter.setOnClickListener(this);
        customListViewAdapter.setCustomOnScrollListener(this);
        customListView.setAdapter(customListViewAdapter);
    }

    @Override
    public void onClick(View v) {
        Log.e("Main","onClick:"+v.getId()+"");
        if(v.getId()==R.id.textView2){
            int position=customListView.getPositionForView(v);
            customListViewAdapter.deleteDataHolder(position);
        }
    }

    @Override
    public void onScroll(CustomLinearLayout customLinearLayout) {
        if(previousCustomLinearLayout!=null){
            previousCustomLinearLayout.revertScroll();
        }
        previousCustomLinearLayout=customLinearLayout;
    }
}

