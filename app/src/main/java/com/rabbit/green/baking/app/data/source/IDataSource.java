package com.rabbit.green.baking.app.data.source;

import com.rabbit.green.baking.app.data.model.Recipe;

import java.util.List;

public interface IDataSource {
    List<Recipe> getRecipes();
}
