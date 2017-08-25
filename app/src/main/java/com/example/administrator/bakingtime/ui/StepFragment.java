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
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class StepFragment extends Fragment {
    private List<Step> mStepList;
    private int stepIndex;
    private ImageView thumbnailImage;
    private SimpleExoPlayerView videoView;
    private SimpleExoPlayer player;

    public StepFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private void initializePlayer(String videoURL) {
        if (player == null) {
            Uri uri = Uri.parse(videoURL);
            Context context = getActivity().getApplicationContext();
            TrackSelector trackSelector = new DefaultTrackSelector();
            player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
            videoView.setPlayer(player);

            String userAgent = Util.getUserAgent(getActivity().getApplicationContext(), "ClassicalMusicQuiz");
            MediaSource videoSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
                    context, userAgent), new DefaultExtractorsFactory(), null, null);
            player.prepare(videoSource);
            player.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        player.stop();
        player.release();
        player = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

        thumbnailImage = (ImageView) rootView.findViewById(R.id.iv_thumbnail);
        videoView = (SimpleExoPlayerView) rootView.findViewById(R.id.vv_video);

        videoView.setPlayer(player);

        if (mStepList != null) {
            String thumbnailURL = mStepList.get(stepIndex).getThumbnailURL();
            String videoURL = mStepList.get(stepIndex).getVideoURL();
            String description = mStepList.get(stepIndex).getDescription();

            if (!TextUtils.isEmpty(thumbnailURL)) {
                thumbnailImage.setVisibility(View.VISIBLE);
                Picasso.with(getActivity().getApplicationContext())
                        .load(thumbnailURL)
                        .into(thumbnailImage);
            }

            if (TextUtils.isEmpty(videoURL)) {
                videoView.setVisibility(View.GONE);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_no_video);
                imageView.setVisibility(View.VISIBLE);
            } else {
                initializePlayer(videoURL);
            }

            if (rootView.findViewById(R.id.layout_fullscreen) == null) {
                TextView tvDesc = (TextView) rootView.findViewById(R.id.tv_step_desc);
                tvDesc.setText(description);
            }
        }

        return rootView;
    }

    public void setStepList(List<Step> stepList) {
        mStepList = stepList;
    }

    public void setIndex(int index) {
        stepIndex = index;
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        String videoURL = mStepList.get(stepIndex).getVideoURL();
        initializePlayer(videoURL);
    }
}
