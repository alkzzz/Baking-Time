package com.example.administrator.bakingtime.model;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Recipe {
    public abstract int id();
    public abstract String name();
    public abstract List<Ingredient> ingredients();
    public abstract List<Step> steps();
    public abstract int servings();
    public abstract String image();

    public static Builder builder() {
        return new AutoValue_Recipe.Builder();
    }

    @AutoValue
    public abstract static class Ingredient {
        public abstract int quantity();
        public abstract String measure();
        public abstract String ingredient();

        public static Builder builder() {
            return new AutoValue_Recipe_Ingredient.Builder();
        }

        @AutoValue.Builder
        public abstract static class Builder {
            public abstract Builder quantity(int quantity);

            public abstract Builder measure(String measure);

            public abstract Builder ingredient(String ingredient);

            public abstract Ingredient build();
        }
    }

    @AutoValue
    public abstract static class Step {
        public abstract int id();
        public abstract String shortDescription();
        public abstract String description();
        public abstract String videoURL();
        public abstract String thumbnailURL();

        public static Builder builder() {
            return new AutoValue_Recipe_Step.Builder();
        }

        @AutoValue.Builder
        public abstract static class Builder {
            public abstract Builder id(int id);

            public abstract Builder shortDescription(String shortDescription);

            public abstract Builder description(String description);

            public abstract Builder videoURL(String videoURL);

            public abstract Builder thumbnailURL(String thumbnailURL);

            public abstract Step build();
        }
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(int id);

        public abstract Builder name(String name);

        public abstract Builder ingredients(List<Ingredient> ingredients);

        public abstract Builder steps(List<Step> steps);

        public abstract Builder servings(int servings);

        public abstract Builder image(String image);

        public abstract Recipe build();
    }
}
