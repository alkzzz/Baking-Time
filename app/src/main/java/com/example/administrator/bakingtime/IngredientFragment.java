package com.example.administrator.bakingtime;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.bakingtime.adapter.IngredientAdapter;
import com.example.administrator.bakingtime.adapter.StepAdapter;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.model.Step;

import java.util.ArrayList;
import java.util.List;

public class IngredientFragment extends Fragment {
    private List<Ingredient> mIngredientList;
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

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_ingredient_list);
        for (Ingredient ing: mIngredientList) {
            String measure = ing.getMeasure();
            double quantity = ing.getQuantity();
            String ingredient = ing.getIngredient();
            CheckBox cbIngredients = new CheckBox(getActivity().getApplicationContext());
            cbIngredients.setText(quantity+" "+measure+" "+ingredient);
            cbIngredients.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(cbIngredients);
        }
        RecyclerView rvSteps = (RecyclerView) rootView.findViewById(R.id.rv_step);
        rvSteps.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvSteps.setNestedScrollingEnabled(false);
        StepAdapter stepAdapter = new StepAdapter(mStepList);
        rvSteps.setAdapter(stepAdapter);

        return rootView;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        mIngredientList = ingredientList;
    }

    public void setStepList(List<Step> stepList) {
        mStepList = stepList;
    }

}
