package com.example.administrator.bakingtime.model;

import android.os.Parcelable;

import org.parceler.Parcel;

import io.realm.IngredientRealmProxy;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@Parcel(implementations = { IngredientRealmProxy.class },
        value = Parcel.Serialization.FIELD,
        analyze = { Ingredient.class })
public class Ingredient extends RealmObject {
    public double quantity;
    public String measure;
    public String ingredient;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

}
