package com.rabbit.green.baking.app.recipes.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Ingredient;

import org.parceler.Parcels;

import java.util.List;

public class ListIngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    public static final String EXTRAS_WIDGET_INGREDIENTS = "EXTRAS_WIDGET_INGREDIENTS";

    private Context context;
    private List<Ingredient> ingredients;

    public ListIngredientsRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        Bundle bundle = intent.getBundleExtra(EXTRAS_WIDGET_INGREDIENTS);
        ingredients = Parcels.unwrap(bundle.getParcelable(EXTRAS_WIDGET_INGREDIENTS));
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients == null ? 0 : ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(context.getPackageName(),
                R.layout.item_ingredient_widget);

        row.setTextViewText(R.id.name_tv, ingredients.get(position).getIngredient());
        row.setTextViewText(R.id.qty_tv, ingredients.get(position).getQuantity() + "");
        row.setTextViewText(R.id.unit_tv, ingredients.get(position).getMeasure());

//        Intent i = new Intent();
//        Bundle extras = new Bundle();
//
//        extras.putString(WidgetProvider.EXTRA_WORD, items[position]);
//        i.putExtras(extras);
//        row.setOnClickFillInIntent(android.R.id.text1, i);

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
