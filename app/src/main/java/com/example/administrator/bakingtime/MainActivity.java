package com.example.administrator.bakingtime;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.bakingtime.model.Ingredient;
import com.example.administrator.bakingtime.model.Recipe;
import com.example.administrator.bakingtime.model.Step;
import com.example.administrator.bakingtime.network.JsonRecipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRecipeJson();
    }

    private void getRecipeJson() {
        final List<Recipe> recipes = new ArrayList<>();
        final List<Ingredient> ingredients = new ArrayList<>();
        final List<Step> steps = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject recipeObj = response.getJSONObject(i);
                                Recipe recipe = Recipe.builder()
                                        .id(recipeObj.getInt("id"))
                                        .name(recipeObj.getString("name"))
                                        .servings(recipeObj.getInt("servings"))
                                        .image(recipeObj.getString("image"))
                                        .build();
                                recipes.add(recipe);
                                JSONArray ingredientsArray = recipeObj.getJSONArray("ingredients");
                                for (int j = 0; j < ingredientsArray.length(); j++) {
                                    JSONObject ingredientObj = ingredientsArray.getJSONObject(i);
                                    Ingredient ingredient = Ingredient.builder()
                                            .quantity(ingredientObj.getInt("quantity"))
                                            .measure(ingredientObj.getString("measure"))
                                            .ingredient(ingredientObj.getString("ingredient"))
                                            .build();
                                    ingredients.add(ingredient);
                                }
                                JSONArray stepsArray = recipeObj.getJSONArray("steps");
                                for (int k = 0; k < stepsArray.length(); k++) {
                                    JSONObject stepObj = stepsArray.getJSONObject(i);
                                    Step step = Step.builder()
                                            .id(stepObj.getInt("id"))
                                            .shortDescription(stepObj.getString("shortDesription"))
                                            .description(stepObj.getString("description"))
                                            .videoURL(stepObj.getString("videoURL"))
                                            .thumbnailURL(stepObj.getString("thumbnailURL"))
                                            .build();
                                    steps.add(step);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonArrayRequest);
    }
}
