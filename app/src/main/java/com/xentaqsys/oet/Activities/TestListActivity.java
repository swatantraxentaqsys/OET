package com.xentaqsys.oet.Activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;


import com.xentaqsys.oet.Model.TestListItem;
import com.xentaqsys.oet.Model.TestListItemAdapter;
import com.xentaqsys.oet.R;

import java.util.ArrayList;

public class TestListActivity extends AppCompatActivity {
    RecyclerView recyclerViewDashboard;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    ArrayList<TestListItem> dashBoardItems =new ArrayList<>();
    ArrayList<String> courseName= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        recyclerViewDashboard = (RecyclerView) findViewById(R.id.testListRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewDashboard.setLayoutManager(mLayoutManager);
//        recyclerViewDashboard.setHasFixedSize(true);
//        recyclerViewDashboard.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        /*Dummy Data for Testing*/
        /*TODO*/

        for (int i=0;i<=10;i++){

            TestListItem item = new TestListItem();
            item.setValue1("CourseName:"+i);
            item.setValue2("TestName:"+i);
            item.setValue3("Value:"+i);
            dashBoardItems.add(item);
//            courseName.add(String.valueOf(i));

        }

        mAdapter = new TestListItemAdapter(TestListActivity.this, dashBoardItems);
        recyclerViewDashboard.setAdapter(mAdapter);
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
