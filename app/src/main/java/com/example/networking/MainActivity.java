package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";


    private RecyclerViewAdapter adapter;
    private List<RecyclerViewItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                // Handle click events if needed
                if (item instanceof Mountain) {
                    Mountain mountain = (Mountain) item;
                    Toast.makeText(MainActivity.this, mountain.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(adapter);

        new JsonTask(this).execute(JSON_URL);
    }


    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", "JSON response: " + json);

        try {
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Mountain>>() {}.getType();
            // Parse JSON array into list of Mountain objects
            List<Mountain> mountains = gson.fromJson(json, listType);

            if (mountains != null) {
                items.clear();
                items.addAll(mountains);
                adapter.notifyDataSetChanged();
                Log.e("MainActivity", "Data sent through");
            } else {
                Log.e("MainActivity", "Couldn't read json");
                Toast.makeText(MainActivity.this, "Failed to parse JSON", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Couldn't read json: " + e.getMessage());
            Toast.makeText(MainActivity.this, "Couldn't read json", Toast.LENGTH_SHORT).show();
        }
    }
}