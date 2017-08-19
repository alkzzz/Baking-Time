package com.example.administrator.bakingtime.ui;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoControls;
import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepFragment extends Fragment implements OnPreparedListener {
    private List<Step> mStepList;
    private int stepIndex;
    private com.devbrackets.android.exomedia.ui.widget.VideoView videoView;
    private Button prevButton;
    private Button nextButton;

    public StepFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

        videoView = (com.devbrackets.android.exomedia.ui.widget.VideoView) rootView.findViewById(R.id.vv_video);


        if (mStepList != null) {
            String videoURL = mStepList.get(stepIndex).getVideoURL();
            String description = mStepList.get(stepIndex).getDescription();

            ((StepActivity) getActivity()).setActionBarTitle("Step "+(stepIndex + 1));

            if (videoURL.equals("")) {
                videoView.setVisibility(View.GONE);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_no_video);
                imageView.setVisibility(View.VISIBLE);
            } else {
                videoView.setOnPreparedListener(this);
                videoView.setVideoURI(Uri.parse(videoURL));
            }

            if (rootView.findViewById(R.id.layout_fullscreen) == null) {
                TextView tvDesc = (TextView) rootView.findViewById(R.id.tv_step_desc);
                tvDesc.setText(description);
                prevButton = (Button) rootView.findViewById(R.id.btn_previous);
                if (stepIndex == 0) {
                    prevButton.setVisibility(View.INVISIBLE);
                } else {
                    prevButton.setVisibility(View.VISIBLE);
                    prevButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prevStep();
                        }
                    });
                }
                nextButton = (Button) rootView.findViewById(R.id.btn_next);
                if (stepIndex == mStepList.size() -1) {
                    nextButton.setVisibility(View.INVISIBLE);
                } else {
                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nextStep();
                        }
                    });
                }
            }
        }

        return rootView;
    }

    @Override
    public void onPrepared() {
        videoView.start();
    }

    public void setStepList(List<Step> stepList) {
        mStepList = stepList;
    }

    public void setIndex(int index) {
        stepIndex = index;
    }

    public void prevStep() {
        if (stepIndex > 0 ) {
            stepIndex--;
        } else {
            stepIndex = mStepList.size() - 1;
        }
    }

    public void nextStep() {
        if (stepIndex < mStepList.size() -1) {
            stepIndex++;
        } else {
            stepIndex = 0;
        }
    }


}
