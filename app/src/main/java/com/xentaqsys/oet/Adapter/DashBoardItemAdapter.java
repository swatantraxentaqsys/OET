package com.xentaqsys.oet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.xentaqsys.oet.Activities.TestListActivity;
import com.xentaqsys.oet.Model.DashBoardItem;
import com.xentaqsys.oet.R;

import java.util.ArrayList;

/**
 * Created by Swatantra.Singh on 9/6/17.
 */
public class DashBoardItemAdapter extends RecyclerView.Adapter<DashBoardItemAdapter.ViewHolder> {
    private ArrayList<DashBoardItem> values;
    Context c;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
//        public Button txtHeader;
        public TextView courseName;
        public TextView testAttempt;
        public  TextView thirdItem;
        public View layout;
        public CardView card_view;

        public ViewHolder(View v) {
            super(v);
            layout = v;
//            txtHeader = (Button) v.findViewById(R.id.superiio);
            courseName = (TextView) v.findViewById(R.id.textViewCourseName);
            testAttempt = (TextView) v.findViewById(R.id.textViewNoOfTest);
            thirdItem = (TextView) v.findViewById(R.id.textView);
            card_view = (CardView) v.findViewById(R.id.card_view);

//            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }


    }

//    public void add(int position, String item) {
//        values.add(position, item);
//        notifyItemInserted(position);
//    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DashBoardItemAdapter(Context context, ArrayList<DashBoardItem> myDataset) {
        values = myDataset;
        c = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DashBoardItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.dashboard_item_grid, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        final String name = values.get(position).getProdFlag();
//
//
//        final String productFlag = String.valueOf(values.get(position).getProdSeq());
//        final String productSeq = String.valueOf(values.get(position).getProdSeq());
//        final String productColorStreamId = values.get(position).getProdColoStreamId();
//        final String PanelItemsID = values.get(position).getPanelItemId();
//        final String coatTypes = values.get(position).getCoatType();
//        final String oem_name1 = values.get(position).getOem();
//        final String Intt_colour_code1 = values.get(position).getC_Code();
//        final String bar_code = values.get(position).getBarCode();

//        holder.txtHeader.setText(values.get(position).getValue1());
        holder.courseName.setText(values.get(position).getValue1());
        holder.testAttempt.setText(values.get(position).getValue2());
        holder.thirdItem.setText(values.get(position).getValue3());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(c, "Test" , Toast.LENGTH_SHORT).show();
                Intent intt = new Intent(c, TestListActivity.class);
                intt.putExtra("CourseName", values.get(position).getValue1());
//                intt.putExtra("CourseName", values.get(position).getValue2());

                c.startActivity(intt);

            }
        });

//        holder.txtFooter.setText("Footer: " + name);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}