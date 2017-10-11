package com.xentaqsys.oet.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xentaqsys.oet.Activities.QuestionActivity;
import com.xentaqsys.oet.R;

/**
 * Created by Swatantra.Singh on 9/19/17.
 */

public class InstructionDialog extends DialogFragment {
    static InstructionDialog newInstance() {
        return new InstructionDialog();
    }
    Button btnStart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogfragment, container,
                false);
        getDialog().setTitle("Instructions");
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        // Do something else
        btnStart = (Button)rootView.findViewById(R.id.buttonStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),QuestionActivity.class);
                startActivity(intent);
            }
        });
        return rootView;

}
}
