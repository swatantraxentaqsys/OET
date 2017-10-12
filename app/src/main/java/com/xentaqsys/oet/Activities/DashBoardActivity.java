package com.xentaqsys.oet.Activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.xentaqsys.oet.Adapter.DashBoardItemAdapter;
import com.xentaqsys.oet.Model.DashBoardItem;
import com.xentaqsys.oet.Model.GridSpacingItemDecoration;
import com.xentaqsys.oet.R;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    RecyclerView recyclerViewDashboard;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    ArrayList<DashBoardItem> dashBoardItems =new ArrayList<>();
    ArrayList<String> courseName= new ArrayList<>();

    private TableRow row;
    private TableLayout inflate;
    private TextView txtcol1,txtcol2, txtcol3,txtcol4,txtcol5,txtcol6,txtcol7,txtcol8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        recyclerViewDashboard = (RecyclerView) findViewById(R.id.recycler_view_dashboard);
        mLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewDashboard.setLayoutManager(mLayoutManager);
//        recyclerViewDashboard.setHasFixedSize(true);
        recyclerViewDashboard.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        /*Dummy Data for Testing*/
        /*TODO*/

        for (int i=0;i<=5;i++){

            DashBoardItem item = new DashBoardItem();
            item.setValue1("CourseName:"+i);
            item.setValue2("TestName:"+i);
            item.setValue3("Value:"+i);
            dashBoardItems.add(item);
//            courseName.add(String.valueOf(i));

        }

        mAdapter = new DashBoardItemAdapter(DashBoardActivity.this, dashBoardItems);
        recyclerViewDashboard.setAdapter(mAdapter);
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
