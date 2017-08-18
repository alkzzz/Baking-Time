package com.example.administrator.bakingtime.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private List<Ingredient> mIngredientList;

    public IngredientAdapter(List<Ingredient> ingredientList) {
        mIngredientList = ingredientList;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        String measure = mIngredientList.get(position).getMeasure();
        double quantity = mIngredientList.get(position).getQuantity();
        String ingredient = mIngredientList.get(position).getIngredient();
        holder.cbIngredient.setText(quantity+" "+measure+" "+ingredient);
    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbIngredient;
        public IngredientViewHolder(View itemView) {
            super(itemView);
            cbIngredient = (CheckBox) itemView.findViewById(R.id.cb_ingredient);
        }
    }
}
