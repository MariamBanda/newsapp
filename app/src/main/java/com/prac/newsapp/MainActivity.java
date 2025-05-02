package com.prac.newsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.prac.newsapp.NewsFragment;
import com.prac.newsapp.NewsItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, new NewsFragment())
            .commit();
    }

    public void openDetailFragment(NewsItem item) {
        DetailFragment detailFragment = DetailFragment.newInstance(item);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit();
    }
}
