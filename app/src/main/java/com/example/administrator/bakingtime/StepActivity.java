package com.example.administrator.bakingtime;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.model.Step;

import java.util.List;

public class StepActivity extends AppCompatActivity {
    private List<Step> mStepList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Bundle data = getIntent().getExtras();
        Step step = data.getParcelable("step");

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Step "+(step.getId()+1));

        StepFragment stepFragment = new StepFragment();
        stepFragment.setStep(step);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_container, stepFragment, null)
                .commit();
    }
}
