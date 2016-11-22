package com.example.ngocsang.studyenglish.screen.fragment.study.listen;

import android.support.v4.BuildConfig;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFullScreenFragment;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ngoc Sang on 11/22/2016.
 */

public class DetailCollectionFragment extends BaseFullScreenFragment{
    private RecyclerView mRecyclerView;
    private PlaylistVideos mPlaylistVideos;
    private RecyclerView.LayoutManager mLayoutManager;
    private PlaylistCardAdapter mPlaylistCardAdapter;
    private YouTube mYouTubeDataApi;
    private String titleScreen;
    @Override
    protected void addView(LayoutInflater inflater, ViewGroup container) {
        Picasso.with(getActivity()).setIndicatorsEnabled(BuildConfig.DEBUG);
           contentView=inflater.inflate(R.layout.fragment_detail_collection,container,false);
        containerView.addView(contentView);
    }

    @Override
    protected void findViews() {
        super.findViews();
        mRecyclerView=(RecyclerView)contentView.findViewById(R.id.lv_detail_collection);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
    }

    public void setTitleScreen(String titleScreen) {
        this.titleScreen = titleScreen;
    }

    @Override
    protected void setUpScreen() {
        super.setUpScreen();
        setTitle(titleScreen);
    }

    @Override
    protected void init() {
        super.init();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        reloadUi(mPlaylistVideos, true);

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
