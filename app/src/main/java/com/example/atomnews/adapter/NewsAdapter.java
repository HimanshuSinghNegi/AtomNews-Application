package com.example.atomnews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.atomnews.R;
import com.example.atomnews.model.NewsModel;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    final Context context;
    final ArrayList<NewsModel> list;
    final NewsItemClicked listener;

    public NewsAdapter(Context context, ArrayList<NewsModel> list, NewsItemClicked listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
//        view.setOnClickListener(view1 -> listener.onItemClicked(list.get(viewHolder.getAdapterPosition())));
        view.setOnLongClickListener(view1 -> {
            listener.onLongItemClick(list.get(viewHolder.getAdapterPosition()));
            return false;
        });
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NewsModel model = list.get(position);
        Glide.with(context.getApplicationContext()).load(model.getNewsImg()).listener(new RequestListener<Drawable>() {
            @SuppressLint("CheckResult")
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                return false;
            }
        }).placeholder(R.drawable.placeholder).into(holder.newsImg);
        holder.newHeading.setText(model.getNewHeading());

        //slicing the description text
        if (model.getDes().equals("null")) {
            holder.des.setVisibility(View.GONE);
        }
        if (model.getDes().length() > 216) {
            String desText = model.getDes().substring(0, (model.getDes().length() / 2 + (model.getDes().length() / 2) / 2)) + ".......";
            holder.des.setText(desText);
        } else {
            holder.des.setText(model.getDes());
        }

        holder.date.setText(model.getDate());
        holder.category.setText(model.getCategory());

        //listener to clickHere textView
        holder.clickHere.setOnClickListener(view -> listener.viewFull(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView newsImg;
        final TextView share;
        final TextView clickHere;
        final TextView newHeading;
        final TextView des;
        final TextView date;
        final TextView category;

        public ViewHolder(View itemView) {
            super(itemView);
            newsImg = itemView.findViewById(R.id.newsImg);
            newHeading = itemView.findViewById(R.id.newsHeading);
            des = itemView.findViewById(R.id.des);
            date = itemView.findViewById(R.id.date);
            category = itemView.findViewById(R.id.cat);
            share = itemView.findViewById(R.id.shareBtn);
            clickHere = itemView.findViewById(R.id.clickHere);
        }
    }
}
