package com.example.administrator.bakingtime.ui;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Step;

import java.util.List;

public class StepActivity extends AppCompatActivity {
    private static final String TAG_STEP_FRAGMENT = "StepFragment";
    private StepFragment mStepFragment;
    private List<Step> stepList;
    private int stepIndex;
    private Button prevButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Bundle data = getIntent().getExtras();
        stepList = data.getParcelableArrayList("steplist");
        stepIndex = data.getInt("index");

        prevButton = (Button) findViewById(R.id.btn_previous);
        if (stepIndex > 0) {
            prevButton.setVisibility(View.VISIBLE);
            prevButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prevStep();
                }
            });
        } else {
            prevButton.setVisibility(View.INVISIBLE);
        }
        nextButton = (Button) findViewById(R.id.btn_next);
        if (stepIndex < stepList.size() -1) {
            nextButton.setVisibility(View.VISIBLE);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextStep();
                }
            });
        } else {
            nextButton.setVisibility(View.INVISIBLE);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        mStepFragment = (StepFragment) fragmentManager.findFragmentByTag(TAG_STEP_FRAGMENT);

        if (mStepFragment == null) {
            StepFragment stepFragment = new StepFragment();
            stepFragment.setStepList(stepList);
            stepFragment.setIndex(stepIndex);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_container, stepFragment, TAG_STEP_FRAGMENT)
                    .commit();
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void prevStep() {
        if (stepIndex == 1) {
            prevButton.setVisibility(View.INVISIBLE);
        }
        if (stepIndex > 0 ) {
            nextButton.setVisibility(View.VISIBLE);
            stepIndex--;
        }
        StepFragment stepFragment = new StepFragment();
        stepFragment.setStepList(stepList);
        stepFragment.setIndex(stepIndex);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_container, stepFragment, TAG_STEP_FRAGMENT)
                .commit();
    }

    public void nextStep() {
        if (stepIndex == stepList.size() -2) {
            nextButton.setVisibility(View.INVISIBLE);
        }
        if (stepIndex < stepList.size() -1) {
            prevButton.setVisibility(View.VISIBLE);
            stepIndex++;
        }
        StepFragment stepFragment = new StepFragment();
        stepFragment.setStepList(stepList);
        stepFragment.setIndex(stepIndex);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_container, stepFragment, TAG_STEP_FRAGMENT)
                .commit();
    }
}
