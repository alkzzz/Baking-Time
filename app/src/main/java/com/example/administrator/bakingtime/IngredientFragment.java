package com.example.administrator.bakingtime;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class IngredientFragment extends Fragment {

    public IngredientFragment() {}

    // newInstance constructor for creating fragment with arguments
    public static IngredientFragment newInstance(List<Ingredient> ingredients) {
        IngredientFragment ingredientFragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("ingredient", (ArrayList<? extends Parcelable>) ingredients);
        ingredientFragment.setArguments(args);
        return ingredientFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);

        TextView tvIngredients = (TextView) rootView.findViewById(R.id.tv_ingredients);
        tvIngredients.setText("Ingredients");

        return rootView;
    }

}
