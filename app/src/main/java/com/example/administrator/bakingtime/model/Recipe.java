package com.example.administrator.bakingtime.model;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RecipeRealmProxy;

@Parcel(implementations = { RecipeRealmProxy.class },
        value = Parcel.Serialization.FIELD,
        analyze = { Recipe.class })
public class Recipe extends RealmObject {
    public int id;
    public String name;
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Ingredient> ingredients;
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Step> steps;
    public int servings;
    public String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(RealmList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public RealmList<Step> getSteps() {
        return steps;
    }

    public void setSteps(RealmList<Step> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
