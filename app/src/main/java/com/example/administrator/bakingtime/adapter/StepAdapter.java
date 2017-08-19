package com.example.administrator.bakingtime.adapter;

import android.support.v7.widget.CardView;
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
    private OnItemClickListener onItemClickListener;

    public StepAdapter(List<Step> stepList, OnItemClickListener onItemClickListener) {
        this.stepList = stepList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_card, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.bind(position, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {
        CardView cvStepCard;
        TextView tvStepShortDesc;
        public StepViewHolder(View itemView) {
            super(itemView);
            cvStepCard = (CardView) itemView.findViewById(R.id.cv_step);
            tvStepShortDesc = (TextView) itemView.findViewById(R.id.step_short_desc);
        }

        public void bind(final int position, final OnItemClickListener onItemClickListener) {
            tvStepShortDesc.setText(stepList.get(position).getShortDescription());
            if (stepList.get(position).getVideoURL().equals("")) {
                tvStepShortDesc.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_videocam_off_black_24dp, 0, 0, 0);
            } else {
                tvStepShortDesc.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_videocam_black_24dp, 0, 0, 0);
            }
            cvStepCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
