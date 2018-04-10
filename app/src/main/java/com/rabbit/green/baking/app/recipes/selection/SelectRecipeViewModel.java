package com.rabbit.green.baking.app.recipes.selection;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.data.source.IDataSource;
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

    @Inject
    SelectRecipeActivity activity;

    @Inject
    IDataSource dataSource;

    @Inject
    RecipeAdapter adapter;

    @Inject
    public SelectRecipeViewModel() {
    }

    public void setup() {
        Single.fromCallable(new Callable<List<Recipe>>() {
            @Override
            public List<Recipe> call() throws Exception {
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
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(activity, 1);
    }

    public RecipeAdapter getAdapter() {
        return adapter;
    }
}
