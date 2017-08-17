package com.example.administrator.bakingtime;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;

import java.util.List;

public class IngredientFragment extends Fragment {
    private static List<Ingredient> sIngredient;

    public IngredientFragment() {}

    // newInstance constructor for creating fragment with arguments
    public static IngredientFragment newInstance(List<Ingredient> ingredient) {
        IngredientFragment ingredientFragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putParcelable("ingredient", (Parcelable) sIngredient);
        ingredientFragment.setArguments(args);
        return ingredientFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredient, container, false);
    }

}
