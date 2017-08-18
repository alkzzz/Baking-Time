package com.example.administrator.bakingtime;

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
        mStepList = data.getParcelableArrayList("steps");

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Step "+(mStepList.get(0).getId() + 1));
    }
}
