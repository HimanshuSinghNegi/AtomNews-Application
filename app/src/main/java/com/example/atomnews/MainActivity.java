package com.example.atomnews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.atomnews.adapter.NewsAdapter;
import com.example.atomnews.adapter.NewsItemClicked;
import com.example.atomnews.databinding.ActivityMainBinding;
import com.example.atomnews.model.NewsModel;
import com.example.atomnews.utils.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsItemClicked {
    ActivityMainBinding binding;
    ArrayList<NewsModel> list1;
    NewsAdapter adapter;
    String url = "https://newsdata.io/api/1/news?apikey=pub_10503c68d9ed791ac566642ae3eb17b09aff1&language=en";
//    String url1 ="https://content.guardianapis.com/search?page=3&q=debate&api-key=d8163485-04ed-487d-8247-ba6844253db9";

    @SuppressLint("NotifyDataSetChanged")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setVisibility(View.GONE);
        list1 = new ArrayList<>();
        loadData(list1);

        //creating object for NewsAdapter
        adapter = new NewsAdapter(this, list1, this);

        //now setting LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
    }

    //here method to load method
    private ArrayList<NewsModel> loadData(ArrayList<NewsModel> list1) {
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
            Log.d("msg1", response.toString());
            try {
                JSONArray jsonArray = response.getJSONArray("results");
                Log.d("array", jsonArray.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject currentData = jsonArray.getJSONObject(i);
                    // extracting title
                    String title = currentData.getString("title");
                    Log.d("title", title);
                    //extracting description
                    String des = currentData.getString("description");

                    Log.d("title", des);
                    //extracting pubDate
                    String pubDate = currentData.getString("pubDate");
                    Log.d("title", pubDate);
                    String imgUrl = currentData.getString("image_url");
                    Log.d("title", imgUrl);
                    //here extracting category which is inside category array
                    String category = currentData.getJSONArray("category").getString(0);
                    Log.d("title", category);
                    String link = currentData.getString("link");
                    Log.d("link", link);
                    list1.add(new NewsModel(des, title, pubDate, category, imgUrl, link));
                    adapter.notifyDataSetChanged();


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this, "No response", Toast.LENGTH_LONG).show());
        //add request to queue
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
         return list1;

    }
    //overriding onItem method

    @Override
    public void onLongItemClick(NewsModel item) {
        Toast.makeText(MainActivity.this, item.getClass().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void viewFull(NewsModel item) {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("link", item.getLink());
        startActivity(intent);
//         Toast.makeText(MainActivity.this,item.getDes(),Toast.LENGTH_LONG).show();
    }


}