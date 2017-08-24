package com.example.administrator.bakingtime.sync;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.bakingtime.model.Recipe;

import io.realm.Realm;

class RecipeSyncTask {

    synchronized public static void syncRecipe(final Context context) {
        getRecipeStringFromJson(context, new StringCallback() {
            @Override
            public void onSuccess(String jsonArray) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.createAllFromJson(Recipe.class, jsonArray);
                realm.commitTransaction();
                realm.close();
            }
        });

    }

    public static void getRecipeStringFromJson(final Context context, final StringCallback stringCallback) {
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        stringCallback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Couldn't connect to network, "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    interface StringCallback{
        void onSuccess(String result);
    }
}
