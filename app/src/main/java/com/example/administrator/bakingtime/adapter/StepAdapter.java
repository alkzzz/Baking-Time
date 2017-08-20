package com.example.administrator.bakingtime.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Step;
import com.example.administrator.bakingtime.ui.DetailActivity;
import com.example.administrator.bakingtime.ui.StepActivity;
import com.example.administrator.bakingtime.ui.StepFragment;

import java.util.ArrayList;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    private List<Step> stepList;
    private boolean isTwoPane;

    private static final String TAG_STEP_FRAGMENT = "StepFragment";

    public StepAdapter(List<Step> stepList, boolean isTwoPane) {
        this.stepList = stepList;
        this.isTwoPane = isTwoPane;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_card, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.bind(position, isTwoPane);
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

        public void bind(final int position, final boolean isTwoPane) {
            tvStepShortDesc.setText(stepList.get(position).getShortDescription());
            if (stepList.get(position).getVideoURL().equals("")) {
                tvStepShortDesc.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_videocam_off_black_24dp, 0, 0, 0);
            } else {
                tvStepShortDesc.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_videocam_black_24dp, 0, 0, 0);
            }
            cvStepCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    if (!isTwoPane) {
                        context = v.getContext();
                        Intent intent = new Intent(context, StepActivity.class);
                        intent.putParcelableArrayListExtra("steplist", (ArrayList<? extends Parcelable>) stepList);
                        intent.putExtra("index", position);
                        context.startActivity(intent);
                    } else {
                        StepFragment stepFragment = new StepFragment();
                        stepFragment.setStepList(stepList);
                        stepFragment.setIndex(position);
                        ((DetailActivity)context).getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.step_two_pane, stepFragment, TAG_STEP_FRAGMENT)
                                .commit();
                    }
                }
            });
        }
    }
}
