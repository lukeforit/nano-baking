package com.rabbit.green.baking.app.recipes.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.Pair;
import android.view.ViewGroup;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.recipes.BaseActivity;

import java.util.List;

import javax.inject.Inject;
//TODO clean widget provider and prefs
public class RecipeWidgetConfigureActivity extends BaseActivity {

    private static final String PREFS_NAME = "com.rabbit.green.baking.app.recipes.widget.RecipeWidget";
    private static final String PREF_PREFIX_RECIPE_ID_KEY = "appwidget_id_";
    private static final String PREF_PREFIX_RECIPE_NAME_KEY = "appwidget_name_";

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Inject
    RecipeConfigureViewModel viewModel;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        ViewDataBinding binding =
                DataBindingUtil.setContentView(this, R.layout.recipe_widget_configure);
        binding.setVariable(BR.vm, viewModel);
        binding.executePendingBindings();

        viewModel.setup();

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
    }

    void updateWidget(int id, String name, List<Ingredient> ingredients) {
        saveRecipeIdPref(mAppWidgetId, id, name);

        // It is the responsibility of the configuration activity to update the app widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RecipeWidget.updateAppWidget(this, appWidgetManager, mAppWidgetId, name, ingredients);

        // Make sure we pass back the original appWidgetId
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

    void saveRecipeIdPref(int appWidgetId, int id, String name) {
        SharedPreferences.Editor prefs = this.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putInt(PREF_PREFIX_RECIPE_ID_KEY + appWidgetId, id);
        prefs.putString(PREF_PREFIX_RECIPE_NAME_KEY + appWidgetId, name);
        prefs.apply();
    }

    static Pair<Integer, String> loadPrefPair(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return new Pair<>(prefs.getInt(PREF_PREFIX_RECIPE_ID_KEY + appWidgetId, 0),
                prefs.getString(PREF_PREFIX_RECIPE_NAME_KEY + appWidgetId, null));
    }

    static void deletePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_RECIPE_ID_KEY + appWidgetId);
        prefs.remove(PREF_PREFIX_RECIPE_NAME_KEY + appWidgetId);
        prefs.apply();
    }
}

