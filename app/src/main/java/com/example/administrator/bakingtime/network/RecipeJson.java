package com.example.administrator.bakingtime.network;

import com.example.administrator.bakingtime.model.Recipe;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeJson {
    private String url;
    private RecipeCallback mRecipeCallback;
    private List<Recipe> mRecipeList;

    public RecipeJson(String url, RecipeCallback recipeCallback) {
        this.url = url;
        mRecipeCallback = recipeCallback;
    }

    public void fetchRecipeData() {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                String jsonResponse = response.body().string();
                Moshi moshi = new Moshi.Builder().build();
                Type type = Types.newParameterizedType(List.class, Recipe.class);
                JsonAdapter<List<Recipe>> adapter = moshi.adapter(type);
                try {
                    List<Recipe> recipes = adapter.fromJson(jsonResponse);
                    if (recipes != null) {
                        mRecipeList = new ArrayList<>();
                        mRecipeList.addAll(recipes);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mRecipeCallback.onSuccess(mRecipeList);
            }
        });
    }

    public interface RecipeCallback {
        void onSuccess(List<Recipe> recipes);
    }
}

