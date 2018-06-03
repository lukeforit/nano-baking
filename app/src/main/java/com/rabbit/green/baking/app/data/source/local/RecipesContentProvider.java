package com.rabbit.green.baking.app.data.source.local;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class RecipesContentProvider extends ContentProvider {

    private RecipesDBHelper dbHelper;

    private static final UriMatcher URI_MATCHER = buildUriMatcher();

    private static final int MATCH_CODE_RECIPES = 100;
    private static final int MATCH_CODE_RECIPE_WITH_ID = 101;

    private static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir";
    private static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item";

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(RecipesContract.AUTHORITY, RecipesContract.PATH_RECIPE, MATCH_CODE_RECIPES);
        uriMatcher.addURI(RecipesContract.AUTHORITY, RecipesContract.PATH_RECIPE + "/#", MATCH_CODE_RECIPE_WITH_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new RecipesDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        switch (URI_MATCHER.match(uri)) {
            case MATCH_CODE_RECIPES:
                cursor = db.query(RecipesContract.RecipeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case MATCH_CODE_RECIPE_WITH_ID:
                cursor = db.rawQuery(RecipesContract.SQL_QUERY_INGREDIENTS_BY_RECIPE_ID,
                        new String[]{uri.getPathSegments().get(1)});
                break;
            default:
                throw new UnsupportedOperationException("Unsupported URI " + uri);
        }
        if (getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case MATCH_CODE_RECIPES:
                return CONTENT_TYPE_DIR + "/" + RecipesContract.AUTHORITY + "/" + RecipesContract.PATH_RECIPE;
            case MATCH_CODE_RECIPE_WITH_ID:
                return CONTENT_TYPE_ITEM + "/" + RecipesContract.AUTHORITY + "/" + RecipesContract.PATH_RECIPE;
            default:
                throw new UnsupportedOperationException("Unsupported URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (values == null) {
            throw new UnsupportedOperationException("Content values cannot be null");
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri returnUri;
        long id;
        switch (URI_MATCHER.match(uri)) {
            case MATCH_CODE_RECIPE_WITH_ID:
                values.put(RecipesContract.IngredientEntry.COLUMN_RECIPE_ID, uri.getPathSegments().get(1));
                id = db.insertOrThrow(RecipesContract.IngredientEntry.TABLE_NAME, null, values);
                break;
            case MATCH_CODE_RECIPES:
                id = db.insertOrThrow(RecipesContract.RecipeEntry.TABLE_NAME, null, values);
                break;
            default:
                throw new UnsupportedOperationException("Unsupported URI " + uri);
        }
        if (id > 0) {
            returnUri = ContentUris.withAppendedId(RecipesContract.CONTENT_URI, id);
        } else {
            throw new SQLException("Failed insert a row for uri: " + uri);
        }
        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
