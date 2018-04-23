package com.rabbit.green.baking.app.recipes.steps.details;

import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.recipes.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class IngredientsViewModel extends BaseViewModel {

    private List<Ingredient> ingredients;

    @Inject
    public IngredientsViewModel() {
    }

    public void setup(List<Ingredient> list) {
        this.ingredients = list;
    }
}
