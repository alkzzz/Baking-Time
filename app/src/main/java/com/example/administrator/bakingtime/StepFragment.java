package com.example.administrator.bakingtime;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepFragment extends Fragment {


    public StepFragment() {}

    // newInstance constructor for creating fragment with arguments
    public static StepFragment newInstance(List<Step> steps) {
        StepFragment stepFragment = new StepFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("ingredient", (ArrayList<? extends Parcelable>) steps);
        stepFragment.setArguments(args);
        return stepFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

        TextView tvShortDesc = (TextView) rootView.findViewById(R.id.tv_short_desc);
        tvShortDesc.setText("Steps");

        return rootView;
    }

}
