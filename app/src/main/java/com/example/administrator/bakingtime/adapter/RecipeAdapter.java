package com.example.administrator.bakingtime.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context context;
    private OnItemClickListener mItemClickListener;
    private List<Recipe> recipes;

    public RecipeAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        mItemClickListener = onItemClickListener;
    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeViewHolder holder, int position) {
        holder.bind(recipes.get(position), mItemClickListener);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvRecipeName;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_recipe);
            tvRecipeName = (TextView) itemView.findViewById(R.id.tv_recipe_name);
        }

        public void bind(final Recipe recipe, final OnItemClickListener onItemClickListener) {
            tvRecipeName.setText(recipe.getName());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(recipe);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(Recipe recipe);
    }
}
