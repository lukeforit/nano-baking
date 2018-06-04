package com.rabbit.green.baking.app.recipes.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;

import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.data.source.local.RecipesLocalDataStore;

import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class RecipeIngredientsIntentService extends IntentService {

    private static final String ACTION_FETCH_INGREDIENTS =
            RecipeIngredientsIntentService.class.getPackage() + ".action.ACTION_FETCH_INGREDIENTS";

    private static final String EXTRA_RECIPE_ID =
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
    public static void startActionFetchIngredients(Context context, int recipeId) {
        Intent intent = new Intent(context, RecipeIngredientsIntentService.class);
        intent.setAction(ACTION_FETCH_INGREDIENTS);
        intent.putExtra(EXTRA_RECIPE_ID, recipeId);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_INGREDIENTS.equals(action)) {
                final int id = intent.getIntExtra(EXTRA_RECIPE_ID, 0);
                handleActionFetchIngredients(id);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFetchIngredients(int id) {
        RecipesLocalDataStore dataStore = new RecipesLocalDataStore(getContentResolver());
        List<Ingredient> list = dataStore.getIngredients(id);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidget.class));
        RecipeWidget.updateAllWidgets(this, appWidgetManager, appWidgetIds);
    }

}
