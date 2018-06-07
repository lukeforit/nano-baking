package com.rabbit.green.baking.app.recipes.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Ingredient;

import org.parceler.Parcels;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String name, List<Ingredient> ingredients) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        views.setTextViewText(R.id.appwidget_recipe_name_tv, name);
        Intent intent = new Intent(context, IngredientsRemoteViewsService.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ListIngredientsRemoteViewsFactory.EXTRAS_WIDGET_INGREDIENTS,
                Parcels.wrap(ingredients));
        intent.putExtra(ListIngredientsRemoteViewsFactory.EXTRAS_WIDGET_INGREDIENTS, bundle);
        views.setRemoteAdapter(R.id.ingredients_lv, intent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //TODO retrieve IDs and pass to service
        RecipeIngredientsIntentService.startActionFetchIngredients(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

