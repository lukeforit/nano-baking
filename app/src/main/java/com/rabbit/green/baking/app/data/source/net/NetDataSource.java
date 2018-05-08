package com.rabbit.green.baking.app.data.source.net;

import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.data.source.IDataSource;

import java.io.IOException;
import java.util.List;

public class NetDataSource implements IDataSource {

    private final IDataRestService restService;

    public NetDataSource(IDataRestService restService) {
        this.restService = restService;
    }

    @Override
    public List<Recipe> getRecipes() {
        try {
            return restService.recipes().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
