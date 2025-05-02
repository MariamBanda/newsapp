package com.prac.newsapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.prac.newsapp.NewsAdapter;
import com.prac.newsapp.NewsItem;
import com.prac.newsapp.RandomNewsGenerator;

import java.util.List;

public class DetailFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DESC = "desc";
    private static final String ARG_IMAGE = "image";

    public static DetailFragment newInstance(NewsItem item) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, item.getTitle());
        args.putString(ARG_DESC, item.getDescription());
        args.putString(ARG_IMAGE, item.getImageUrl()); // Holds the image URL
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView description = view.findViewById(R.id.newsDescription);
        ImageView image = view.findViewById(R.id.newsImage);
        RecyclerView relatedNewsRecyclerView = view.findViewById(R.id.relatedNewsRecyclerView);

        // Fetch data from arguments
        String title = getArguments().getString(ARG_TITLE);
        String desc = getArguments().getString(ARG_DESC);
        String imageUrl = getArguments().getString(ARG_IMAGE);

        // Set the description
        description.setText(desc);


        if (imageUrl != null) {

            Glide.with(this)
                .load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("GlideError", "Error loading image", e);
                        return false;  // Allow Glide to handle the error
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        return false;
                    }
                })
                .into(image);
        }


        RandomNewsGenerator.generateRandomNews(5, new RandomNewsGenerator.NewsDataCallback() {
            @Override
            public void onDataFetched(List<NewsItem> relatedNewsItems) {

                relatedNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                relatedNewsRecyclerView.setAdapter(new NewsAdapter(relatedNewsItems, item -> {

                }));
            }
        });

        return view;
    }
}
