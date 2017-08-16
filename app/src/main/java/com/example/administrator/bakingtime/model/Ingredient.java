package com.example.administrator.bakingtime.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Ingredient {
    public abstract int quantity();
    public abstract String measure();
    public abstract String ingredient();

    public static Builder builder() {
        return new AutoValue_Ingredient.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder quantity(int quantity);

        public abstract Builder measure(String measure);

        public abstract Builder ingredient(String ingredient);

        public abstract Ingredient build();
    }
}
