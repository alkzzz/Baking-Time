package com.example.administrator.bakingtime.ui;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Step;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class StepFragment extends Fragment {
    private List<Step> mStepList;
    private int stepIndex;
    private SimpleExoPlayerView videoView;
    private SimpleExoPlayer player;

    public StepFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        setupVideoPlayer(getActivity().getApplicationContext());
    }

    private void setupVideoPlayer(Context context) {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        player.setPlayWhenReady(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

        videoView = (SimpleExoPlayerView) rootView.findViewById(R.id.vv_video);
        videoView.setPlayer(player);

        if (mStepList != null) {
            String videoURL = mStepList.get(stepIndex).getVideoURL();
            String description = mStepList.get(stepIndex).getDescription();

            if (TextUtils.isEmpty(videoURL)) {
                videoView.setVisibility(View.GONE);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_no_video);
                imageView.setVisibility(View.VISIBLE);
            } else {
                videoView.setPlayer(player);
                videoView.setUseController(true);
                prepareVideo(videoURL);
            }

            if (rootView.findViewById(R.id.layout_fullscreen) == null) {
                TextView tvDesc = (TextView) rootView.findViewById(R.id.tv_step_desc);
                tvDesc.setText(description);
            }
        }

        return rootView;
    }

    private void prepareVideo(String videoURL) {
        Context context = getActivity().getApplicationContext();
        Uri uri = Uri.parse(videoURL);
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "BakingApplication"), bandwidthMeter);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource videoSource = new ExtractorMediaSource(uri,
                dataSourceFactory, extractorsFactory, null, null);
        player.prepare(videoSource);
    }

    public void setStepList(List<Step> stepList) {
        mStepList = stepList;
    }

    public void setIndex(int index) {
        stepIndex = index;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
