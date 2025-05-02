package com.prac.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class NewsFragment extends Fragment {

    private RecyclerView topStoriesRecyclerView, newsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        topStoriesRecyclerView = view.findViewById(R.id.topStoriesRecyclerView);
        newsRecyclerView = view.findViewById(R.id.newsRecyclerView);

        // Use the callback-based method to fetch the top stories
        RandomNewsGenerator.generateRandomNews(5, new RandomNewsGenerator.NewsDataCallback() {
            @Override
            public void onDataFetched(List<NewsItem> newsItems) {
                // Once data is fetched, update the "Top Stories" RecyclerView
                NewsAdapter.OnItemClickListener listener = item -> ((com.prac.newsapp.MainActivity) getActivity()).openDetailFragment(item);

                topStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                topStoriesRecyclerView.setAdapter(new NewsAdapter(newsItems, listener));
            }
        });

        // Use the callback-based method to fetch the regular news
        RandomNewsGenerator.generateRandomNews(10, new RandomNewsGenerator.NewsDataCallback() {
            @Override
            public void onDataFetched(List<NewsItem> newsItems) {
                // Once data is fetched, update the "News" RecyclerView
                NewsAdapter.OnItemClickListener listener = item -> ((MainActivity) getActivity()).openDetailFragment(item);

                newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                newsRecyclerView.setAdapter(new NewsAdapter(newsItems, listener));
            }
        });

        return view;
    }
}
