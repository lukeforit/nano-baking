package com.rabbit.green.baking.app.data.source.net;

import com.rabbit.green.baking.app.data.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IDataRestService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> recipes();
}
