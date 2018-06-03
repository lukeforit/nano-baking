package com.rabbit.green.baking.app.data.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipesLocalDataStore {
    private ContentResolver contentResolver;

    public RecipesLocalDataStore(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public List<Recipe> getRecipes() {
        Cursor cursor = contentResolver.query(RecipesContract.CONTENT_URI,
                new String[]{BaseColumns._ID,
                        RecipesContract.RecipeEntry.COLUMN_NAME},
                null, null, null);
        List<Recipe> result = new ArrayList<>();
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    result.add(mapCursorToRecipe(cursor));
                }
            } finally {
                cursor.close();
            }
        }
        return result;
    }

    public List<Ingredient> getIngredients(int id) {
        Cursor cursor = contentResolver.query(ContentUris.withAppendedId(RecipesContract.CONTENT_URI, id),
                null, null, null, null);
        List<Ingredient> result = new ArrayList<>();
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    result.add(mapCursorToIngredient(cursor));
                }
            } finally {
                cursor.close();
            }
        }
        return result;
    }

    public void saveRecipeIngredients(Recipe recipe) {
        insertRecipeOnly(recipe);
        insertIngredients(recipe);
    }

    private void insertRecipeOnly(Recipe recipe) {
        contentResolver.insert(RecipesContract.CONTENT_URI, mapRecipeToContentValues(recipe));
    }

    private void insertIngredients(Recipe recipe) {
        for (Ingredient i : recipe.getIngredients()) {
            contentResolver.insert(ContentUris.withAppendedId(RecipesContract.CONTENT_URI, recipe.getId()),
                    mapIngredientToContentValues(i));
        }
    }

    private ContentValues mapIngredientToContentValues(Ingredient ingredient) {
        ContentValues values = new ContentValues();
        values.put(RecipesContract.IngredientEntry.COLUMN_NAME, ingredient.getIngredient());
        values.put(RecipesContract.IngredientEntry.COLUMN_MEASURE, ingredient.getMeasure());
        values.put(RecipesContract.IngredientEntry.COLUMN_QTY, ingredient.getQuantity());
        return values;
    }

    private ContentValues mapRecipeToContentValues(Recipe recipe) {
        ContentValues values = new ContentValues();
        values.put(BaseColumns._ID, recipe.getId());
        values.put(RecipesContract.RecipeEntry.COLUMN_NAME, recipe.getName());
        return values;
    }

    private Ingredient mapCursorToIngredient(Cursor cursor) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient(cursor.getString(cursor.getColumnIndex(RecipesContract.IngredientEntry.COLUMN_NAME)));
        ingredient.setMeasure(cursor.getString(cursor.getColumnIndex(RecipesContract.IngredientEntry.COLUMN_MEASURE)));
        ingredient.setQuantity(cursor.getDouble(cursor.getColumnIndex(RecipesContract.IngredientEntry.COLUMN_QTY)));
        return ingredient;
    }

    private Recipe mapCursorToRecipe(@NonNull Cursor cursor) {
        Recipe recipe = new Recipe();
        recipe.setId(cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)));
        recipe.setName(cursor.getString(cursor.getColumnIndex(RecipesContract.RecipeEntry.COLUMN_NAME)));
        return recipe;
    }

}
