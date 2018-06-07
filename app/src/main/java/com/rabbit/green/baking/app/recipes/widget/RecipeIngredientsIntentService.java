package com.rabbit.green.baking.app.recipes.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;

import com.rabbit.green.baking.app.data.source.local.RecipesLocalDataStore;
import com.rabbit.green.baking.app.recipes.widget.config.RecipeWidgetConfigureActivity;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class RecipeIngredientsIntentService extends IntentService {

    private static final String ACTION_FETCH_INGREDIENTS =
            RecipeIngredientsIntentService.class.getPackage() + ".action.ACTION_FETCH_INGREDIENTS";

    private static final String EXTRA_APP_WIDGET_IDS =
            RecipeIngredientsIntentService.class.getPackage() + ".extra.RECIPE_ID";

    public RecipeIngredientsIntentService() {
        super(RecipeIngredientsIntentService.class.getSimpleName());
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionFetchIngredients(Context context, int[] appWidgetIds) {
        Intent intent = new Intent(context, RecipeIngredientsIntentService.class);
        intent.setAction(ACTION_FETCH_INGREDIENTS);
        intent.putExtra(EXTRA_APP_WIDGET_IDS, appWidgetIds);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_INGREDIENTS.equals(action)) {
                final int[] appWidgetIds = intent.getIntArrayExtra(EXTRA_APP_WIDGET_IDS);
                handleActionFetchIngredients(appWidgetIds);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFetchIngredients(int[] appWidgetIds) {
        RecipesLocalDataStore dataStore = new RecipesLocalDataStore(getContentResolver());

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        for (int appWidgetId: appWidgetIds) {
            Pair<Integer, String> pair =  RecipeWidgetConfigureActivity.loadPrefPair(this, appWidgetId);
            RecipeWidget.updateAppWidget(this, appWidgetManager, appWidgetId,
                    pair.second, dataStore.getIngredients(pair.first));
        }
    }

}
