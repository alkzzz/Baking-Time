package com.example.administrator.bakingtime.ui;

import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.model.Step;

import java.util.List;

import io.realm.Realm;

public class StepActivity extends AppCompatActivity {
    private static final String TAG_STEP_FRAGMENT = "StepFragment";
    private StepFragment mStepFragment;
    private List<Step> stepList;
    private int recipe_id;
    private int stepIndex;
    private Realm realm;
    private Button prevButton;
    private Button nextButton;
    private ActionBar actionbar;

    public StepActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Bundle data = getIntent().getExtras();
        recipe_id = data.getInt("recipe_id");
        stepIndex = data.getInt("index");

        realm = Realm.getDefaultInstance();

        stepList = realm.where(Recipe.class).equalTo("id", recipe_id).findFirst().getSteps();

        actionbar = getSupportActionBar();

        if (getString(R.string.layout_type).equals("default")) {
            prevButton = (Button) findViewById(R.id.btn_previous);
            prevButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prevStep();
                }
            });
            nextButton = (Button) findViewById(R.id.btn_next);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextStep();
                }
            });
        }

        if (savedInstanceState == null) {
            actionbar.setTitle("Step "+(stepIndex + 1));

            FragmentManager fragmentManager = getSupportFragmentManager();
            mStepFragment = (StepFragment) fragmentManager.findFragmentByTag(TAG_STEP_FRAGMENT);

            if (mStepFragment == null) {
                mStepFragment = new StepFragment();
                mStepFragment.setStepList(stepList);
                mStepFragment.setIndex(stepIndex);

                fragmentManager.beginTransaction()
                        .add(R.id.step_container, mStepFragment, TAG_STEP_FRAGMENT)
                        .commit();
            }

            if (getString(R.string.layout_type).equals("default")) {
                if (stepIndex > 0) {
                    prevButton.setVisibility(View.VISIBLE);
                } else {
                    prevButton.setVisibility(View.INVISIBLE);
                }
                if (stepIndex < stepList.size() - 1) {
                    nextButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("stepindex", stepIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        stepIndex = savedInstanceState.getInt("stepindex");
        if (getString(R.string.layout_type).equals("default")) {
            if (stepIndex > 0) {
                prevButton.setVisibility(View.VISIBLE);
            } else {
                prevButton.setVisibility(View.INVISIBLE);
            }
            if (stepIndex < stepList.size() - 1) {
                nextButton.setVisibility(View.VISIBLE);
            } else {
                nextButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void prevStep() {
        if (stepIndex > 0 ) {
            nextButton.setVisibility(View.VISIBLE);
            stepIndex--;
            if (stepIndex == 0) {
                prevButton.setVisibility(View.INVISIBLE);
            }
        }
        mStepFragment = new StepFragment();
        mStepFragment.setStepList(stepList);
        mStepFragment.setIndex(stepIndex);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_container, mStepFragment, TAG_STEP_FRAGMENT)
                .commit();
        actionbar.setTitle("Step "+(stepIndex + 1));
    }

    public void nextStep() {
        if (stepIndex < stepList.size() -1) {
            prevButton.setVisibility(View.VISIBLE);
            stepIndex++;
            if (stepIndex == (stepList.size() - 1)) {
                nextButton.setVisibility(View.INVISIBLE);
            }
        }
        mStepFragment = new StepFragment();
        mStepFragment.setStepList(stepList);
        mStepFragment.setIndex(stepIndex);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_container, mStepFragment, TAG_STEP_FRAGMENT)
                .commit();
        actionbar.setTitle("Step "+(stepIndex + 1));
    }
}
