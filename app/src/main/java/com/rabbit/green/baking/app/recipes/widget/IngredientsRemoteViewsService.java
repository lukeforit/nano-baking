package com.rabbit.green.baking.app.recipes.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class IngredientsRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListIngredientsRemoteViewsFactory(getApplicationContext(), intent);
    }
}
