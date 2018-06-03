package com.rabbit.green.baking.app.data.source.local;

import android.net.Uri;
import android.provider.BaseColumns;

public class RecipesContract {
    private static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.rabbit.green.baking";
    static final String PATH_RECIPE = "recipe";
    private static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + AUTHORITY);

    static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + RecipeEntry.TABLE_NAME + "(" +
            BaseColumns._ID + " INTEGER PRIMARY KEY, " +
            RecipeEntry.COLUMN_NAME + " TEXT NOT NULL " +
            ");";

    static final String SQL_CREATE_INGREDIENT_TABLE = "CREATE TABLE " + IngredientEntry.TABLE_NAME + "(" +
            BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            IngredientEntry.COLUMN_NAME + " TEXT NOT NULL, " +
            IngredientEntry.COLUMN_MEASURE + " TEXT NOT NULL, " +
            IngredientEntry.COLUMN_QTY + " REAL NOT NULL, " +
            IngredientEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL " +
            ");";
    static final String SQL_QUERY_INGREDIENTS_BY_RECIPE_ID =
            "SELECT i." + IngredientEntry.COLUMN_NAME +
                    ", i." +IngredientEntry.COLUMN_MEASURE +
                    ", i." + IngredientEntry.COLUMN_QTY +
                    " FROM " + RecipeEntry.TABLE_NAME +
                    " r INNER JOIN " + IngredientEntry.TABLE_NAME +
                    " i ON r." + BaseColumns._ID + " = i." + IngredientEntry.COLUMN_RECIPE_ID +
                    " WHERE r." + BaseColumns._ID + "=?";

    static final Uri CONTENT_URI = BASE_CONTENT_URI
            .buildUpon()
            .appendPath(PATH_RECIPE)
            .build();

    static class RecipeEntry implements BaseColumns {

        static final String TABLE_NAME = "recipe";
        static final String COLUMN_NAME = "name";
    }

    static class IngredientEntry implements BaseColumns {

        static final String TABLE_NAME = "ingredient";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_MEASURE = "measure";
        static final String COLUMN_QTY = "qty";
        static final String COLUMN_RECIPE_ID = "recipeId";
    }
}
