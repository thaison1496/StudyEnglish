package com.example.ngocsang.studyenglish.screen.fragment.study.listen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.CollectionAdapter;
import com.example.ngocsang.studyenglish.constant.Constant;
import com.example.ngocsang.studyenglish.model.ItemCollection;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/22/2016.
 */

public class CollectionFragment extends BaseFragment{
    private GridView lvColection;
    private CollectionAdapter collectionAdapter;
    private ArrayList<ItemCollection> arrCollection;
    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.fragment_collection,container,false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        lvColection=(GridView)contentView.findViewById(R.id.grid_collection_video);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        lvColection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 DetailCollectionFragment collectionFragment=new DetailCollectionFragment();
                collectionFragment.setTitleScreen(arrCollection.get(position).getTitle());
                collectionFragment.setmYouTubeDataApi(mYoutubeDataApi);
                collectionFragment.setmPlaylistVideos(new PlaylistVideos(arrCollection.get(position).getIdLisVideo()));
                mActivity.replaceFullScreen(collectionFragment,true,"Detail_collection");
            }
        });

    }

    @Override
    protected void init() {
        super.init();
        mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                .setApplicationName(getResources().getString(R.string.app_name))
                .build();
        arrCollection=new ArrayList<>();
        arrCollection.add(new ItemCollection("Learning English News",R.color.amber_A200, Constant.LEARNING_ENGLISH_NEW));
        arrCollection.add(new ItemCollection("Education Report",R.color.blue_900, Constant.EDUCATION_REPORT));
        arrCollection.add(new ItemCollection("English In Minute",R.color.brown_500, Constant.ENGLISH_IN_MINUTE));
        arrCollection.add(new ItemCollection("American Store",R.color.color_gray, Constant.AMERICAN_STORE));
        arrCollection.add(new ItemCollection("Economic Report",R.color.color_pink, Constant.ECONOMIC_REPORT));
        arrCollection.add(new ItemCollection("Healthy Report",R.color.cyan_700, Constant.HEALTH_REPORT));
        arrCollection.add(new ItemCollection("Technology Report",R.color.indigo_900, Constant.TECHNOLOGY_REPORT));
        arrCollection.add(new ItemCollection("News Words",R.color.purple_A100, Constant.NEWS_WORDS));
        collectionAdapter=new CollectionAdapter(arrCollection,mActivity);
        lvColection.setAdapter(collectionAdapter);

    }
}
