package com.prac.newsapp;

import com.prac.newsapp.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class RandomNewsGenerator {

    // Define the interface NewsDataCallback here
    public interface NewsDataCallback {
        void onDataFetched(List<NewsItem> newsItems);
    }

    public static void generateRandomNews(int count, NewsDataCallback callback) {
        List<NewsItem> newsItems = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String title = "Random News Title " + (i + 1);  // You can generate dynamic titles here
            String description = "This is a random news description for news article " + (i + 1);  // Generate dynamic descriptions
            String imageUrl = "https://picsum.photos/200/300";  // Random image URL from Lorem Picsum
            newsItems.add(new NewsItem(title, description, imageUrl));
        }

        // Pass the generated news items to the callback
        callback.onDataFetched(newsItems);
    }
}
