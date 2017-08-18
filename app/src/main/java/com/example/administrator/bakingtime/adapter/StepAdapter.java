package com.example.administrator.bakingtime.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Step;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    private List<Step> stepList;

    public StepAdapter(List<Step> stepList) {
        this.stepList = stepList;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.tvStepShortDesc.setText(stepList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {
        TextView tvStepShortDesc;
        public StepViewHolder(View itemView) {
            super(itemView);
            tvStepShortDesc = (TextView) itemView.findViewById(R.id.step_short_desc);
        }
    }
}
