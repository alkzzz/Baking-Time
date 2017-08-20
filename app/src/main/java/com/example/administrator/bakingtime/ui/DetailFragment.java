package com.example.administrator.bakingtime.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment implements StepAdapter.OnItemClickListener {
    private List<Step> stepList;

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


        RecyclerView rvSteps = (RecyclerView) rootView.findViewById(R.id.rv_step);
        rvSteps.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvSteps.setNestedScrollingEnabled(false);
        StepAdapter stepAdapter = new StepAdapter(stepList, DetailFragment.this);
        rvSteps.setAdapter(stepAdapter);

        return rootView;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }

    @Override
    public void onItemClick(int index) {
        Intent intent = new Intent(getActivity().getApplicationContext(), StepActivity.class);
        intent.putParcelableArrayListExtra("steplist", (ArrayList<? extends Parcelable>) stepList);
        intent.putExtra("index", index);
        startActivity(intent);
    }
}
