package com.example.atomnews.adapter;

import com.example.atomnews.model.NewsModel;

//interface
public interface NewsItemClicked {
    //    void onItemClicked(NewsModel item);
    void onLongItemClick(NewsModel item);

    void viewFull(NewsModel item);

}
