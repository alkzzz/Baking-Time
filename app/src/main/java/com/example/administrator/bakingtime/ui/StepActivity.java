package com.example.administrator.bakingtime.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Step;

import java.util.List;

public class StepActivity extends AppCompatActivity {
    private static final String TAG_STEP_FRAGMENT = "StepFragment";
    private StepFragment mStepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Bundle data = getIntent().getExtras();
        List<Step> stepList = data.getParcelableArrayList("steplist");
        int index = data.getInt("index");

        FragmentManager fragmentManager = getSupportFragmentManager();
        mStepFragment = (StepFragment) fragmentManager.findFragmentByTag(TAG_STEP_FRAGMENT);

        if (mStepFragment == null) {
            StepFragment stepFragment = new StepFragment();
            stepFragment.setStepList(stepList);
            stepFragment.setIndex(index);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_container, stepFragment, TAG_STEP_FRAGMENT)
                    .commit();
        }

    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
