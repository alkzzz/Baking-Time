package com.example.administrator.bakingtime.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.adapter.StepAdapter;
import com.example.administrator.bakingtime.model.Step;

import java.util.List;

public class DetailFragment extends Fragment {
    private boolean isTwoPane;
    private List<Step> stepList;
    private int recipe_id;


    public DetailFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        isTwoPane = getActivity().findViewById(R.id.step_two_pane).getVisibility() != View.GONE;

        RecyclerView rvSteps = (RecyclerView) rootView.findViewById(R.id.rv_step);
        rvSteps.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvSteps.setNestedScrollingEnabled(false);
        StepAdapter stepAdapter = new StepAdapter(stepList, isTwoPane, recipe_id);
        rvSteps.setAdapter(stepAdapter);

        return rootView;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }

    public void setRecipeId(int recipe_id) {
        this.recipe_id = recipe_id;
    }
}
