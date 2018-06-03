package com.rabbit.green.baking.app.recipes.selection;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.data.source.IDataSource;
import com.rabbit.green.baking.app.data.source.local.RecipesLocalDataStore;
import com.rabbit.green.baking.app.recipes.selection.adapter.RecipeAdapter;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SelectRecipeViewModel {

    @SuppressWarnings("WeakerAccess")
    @Inject
    SelectRecipeActivity activity;

    @SuppressWarnings("WeakerAccess")
    @Inject
    IDataSource dataSource;

    @SuppressWarnings("WeakerAccess")
    @Inject
    RecipeAdapter adapter;

    @Inject
    RecipesLocalDataStore localDataStore;

    @Inject
    public SelectRecipeViewModel() {
    }

    public void setup() {
        Single.fromCallable(new Callable<List<Recipe>>() {
            @Override
            public List<Recipe> call() {
                return dataSource.getRecipes();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Recipe>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Recipe> recipes) {
                        adapter.setData(recipes);
                        cacheRecipes(recipes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    private void cacheRecipes(List<Recipe> recipes) {
        // dummy all or nothing
        if (localDataStore.getRecipes().isEmpty()) {
            for (Recipe r : recipes) {
                localDataStore.saveRecipeIngredients(r);
            }
        }
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(activity, activity.getResources().getInteger(R.integer.grid_span_count));
    }

    public RecipeAdapter getAdapter() {
        return adapter;
    }
}
