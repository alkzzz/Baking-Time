package com.example.administrator.bakingtime;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.model.Step;

import java.util.ArrayList;
import java.util.List;

public class IngredientFragment extends Fragment {
    private List<Step> mStepList;

    public IngredientFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);
        Button button = (Button) rootView.findViewById(R.id.btn_click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), StepActivity.class);
                intent.putParcelableArrayListExtra("steps", (ArrayList<? extends Parcelable>) mStepList);
                getActivity().startActivity(intent);
            }
        });

        return rootView;
    }

    public void setStepList(List<Step> stepList) {
        mStepList = stepList;
    }

}
