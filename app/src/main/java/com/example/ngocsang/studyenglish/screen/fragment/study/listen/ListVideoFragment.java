package com.example.ngocsang.studyenglish.screen.fragment.study.listen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.BuildConfig;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ngoc Sang on 11/22/2016.
 */

public class ListVideoFragment extends BaseFragment{
    private RecyclerView mRecyclerView;
    private PlaylistVideos mPlaylistVideos;
    private RecyclerView.LayoutManager mLayoutManager;
    private PlaylistCardAdapter mPlaylistCardAdapter;
    private YouTube mYouTubeDataApi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Picasso.with(getActivity()).setIndicatorsEnabled(BuildConfig.DEBUG);
        contentView=inflater.inflate(R.layout.fragment_list_video,container,false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        mRecyclerView=(RecyclerView)contentView.findViewById(R.id.lv_list_video);
    }

    @Override
    protected void init() {
        super.init();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        reloadUi(mPlaylistVideos, true);


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void reloadUi(final PlaylistVideos playlistVideos, boolean fetchPlaylist) {
        initCardAdapter(playlistVideos);

        if (fetchPlaylist) {
            new GetPlaylistAsyncTask(mYouTubeDataApi) {
                @Override
                public void onPostExecute(Pair<String, List<Video>> result) {
                    handleGetPlaylistResult(playlistVideos, result);
                }
            }.execute(playlistVideos.playlistId, playlistVideos.getNextPageToken());
        }
    }

    private void initCardAdapter(final PlaylistVideos playlistVideos) {
        mPlaylistCardAdapter = new PlaylistCardAdapter(playlistVideos, new YouTubeRecyclerViewFragment.LastItemReachedListener() {
            @Override
            public void onLastItem(int position, String nextPageToken) {
                new GetPlaylistAsyncTask(mYouTubeDataApi) {
                    @Override
                    public void onPostExecute(Pair<String, List<Video>> result) {
                        handleGetPlaylistResult(playlistVideos, result);
                    }
                }.execute(playlistVideos.playlistId, playlistVideos.getNextPageToken());
            }
        });
        mRecyclerView.setAdapter(mPlaylistCardAdapter);
    }

    private void handleGetPlaylistResult(PlaylistVideos playlistVideos, Pair<String, List<Video>> result) {
        if (result == null) return;
        final int positionStart = playlistVideos.size();
        playlistVideos.setNextPageToken(result.first);
        playlistVideos.addAll(result.second);
        mPlaylistCardAdapter.notifyItemRangeInserted(positionStart, result.second.size());
    }

    public interface LastItemReachedListener {
        void onLastItem(int position, String nextPageToken);
    }

    public PlaylistVideos getmPlaylistVideos() {
        return mPlaylistVideos;
    }

    public void setmPlaylistVideos(PlaylistVideos mPlaylistVideos) {
        this.mPlaylistVideos = mPlaylistVideos;
    }

    public YouTube getmYouTubeDataApi() {
        return mYouTubeDataApi;
    }

    public void setmYouTubeDataApi(YouTube mYouTubeDataApi) {
        this.mYouTubeDataApi = mYouTubeDataApi;
    }

}
