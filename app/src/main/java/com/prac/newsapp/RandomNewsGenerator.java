package com.prac.newsapp;

import com.prac.newsapp.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class RandomNewsGenerator {


    public interface NewsDataCallback {
        void onDataFetched(List<NewsItem> newsItems);
    }

    public static void generateRandomNews(int count, NewsDataCallback callback) {
        List<NewsItem> newsItems = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String title = "Random News Title " + (i + 1);
            String description = "This is a random news description for news article " + (i + 1);
            String imageUrl = "https://picsum.photos/200/300";
            newsItems.add(new NewsItem(title, description, imageUrl));
        }


        callback.onDataFetched(newsItems);
    }
}
