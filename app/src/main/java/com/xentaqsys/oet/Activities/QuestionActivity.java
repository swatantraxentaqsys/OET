package com.xentaqsys.oet.Activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.xentaqsys.oet.Model.DividerItemDecoration;
import com.xentaqsys.oet.Model.OptionItems;
import com.xentaqsys.oet.Model.OptionListAdapter;
import com.xentaqsys.oet.R;

import java.util.ArrayList;

import static com.xentaqsys.oet.Model.DividerItemDecoration.VERTICAL_LIST;


public class QuestionActivity extends AppCompatActivity {

    RecyclerView recyclerViewOptions;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<OptionItems> dashBoardItems =new ArrayList<>();
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        recyclerViewOptions = (RecyclerView) findViewById(R.id.recyclerview_options);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(mLayoutManager);
//        recyclerViewDashboard.setHasFixedSize(true);
        recyclerViewOptions.addItemDecoration(new DividerItemDecoration(this,VERTICAL_LIST));

        /*Dummy Data for Testing*/
        /*TODO*/

        for (int i=0;i<4;i++){

            String s= Character.toString((char)(65+i));
            OptionItems item = new OptionItems();
            item.setValue1("CourseName:"+i);
            item.setValue2("TestName:"+i);
            item.setValue3(s);
            dashBoardItems.add(item);
//            courseName.add(String.valueOf(i));

        }

        mAdapter = new OptionListAdapter(QuestionActivity.this, dashBoardItems);
        recyclerViewOptions.setAdapter(mAdapter);
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
