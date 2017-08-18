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
    private Step step;

    public StepFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);
        TextView tvDesc = (TextView) rootView.findViewById(R.id.tv_step_desc);
        tvDesc.setText(step.getDescription());

        return rootView;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
