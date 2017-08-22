package com.example.administrator.bakingtime.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Recipe;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RecipeRealmAdapter extends RealmRecyclerViewAdapter<Recipe, RecipeRealmAdapter.RecipeViewHolder> {
    private OnItemClickListener mItemClickListener;

    public RecipeRealmAdapter(@Nullable OrderedRealmCollection<Recipe> data, boolean autoUpdate, OnItemClickListener mItemClickListener) {
        super(data, autoUpdate);
        setHasStableIds(true);
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, final int position) {
        final Recipe obj = getItem(position);
        holder.data = obj;
        holder.tvRecipeName.setText(obj.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.OnItemClick(position);
            }
        });
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvRecipeName;
        public Recipe data;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_recipe);
            tvRecipeName = (TextView) itemView.findViewById(R.id.tv_recipe_name);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }
}
