package com.example.administrator.bakingtime.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.bakingtime.R;
import com.example.administrator.bakingtime.model.Recipe;
import io.realm.Realm;


public class MainActivity extends AppCompatActivity {
    private static final String TAG_RECIPE_FRAGMENT = "RecipeFragment";
    private RecipeFragment mRecipeFragment;
    private Realm realm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        if(realm.where(Recipe.class).count() == 0) {
            importRecipeFromJson();
        }

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            mRecipeFragment = (RecipeFragment) fragmentManager.findFragmentByTag(TAG_RECIPE_FRAGMENT);

            if (mRecipeFragment == null) {
                mRecipeFragment = new RecipeFragment();
                fragmentManager.beginTransaction().add(mRecipeFragment, TAG_RECIPE_FRAGMENT).commit();
            }
        }
    }

    public void importRecipeFromJson() {
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.createAllFromJson(Recipe.class, response);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }

        });

        queue.add(stringRequest);
    }
}
