package com.example.administrator.bakingtime.ui;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.adapter.StepAdapter;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Step;

import java.util.List;

public class IngredientFragment extends Fragment implements StepAdapter.OnItemClickListener {
    private List<Ingredient> ingredientList;
    private List<Step> stepList;

    public IngredientFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_ingredient_list);
        for (Ingredient ing: ingredientList) {
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
        StepAdapter stepAdapter = new StepAdapter(stepList, IngredientFragment.this);
        rvSteps.setAdapter(stepAdapter);

        return rootView;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }

    @Override
    public void onItemClick(Step step) {
        Intent intent = new Intent(getActivity().getApplicationContext(), StepActivity.class);
        intent.putExtra("step", step);
        startActivity(intent);
    }
}
