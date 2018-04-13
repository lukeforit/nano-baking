package com.rabbit.green.baking.app.recipes.selection.adapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Recipe;
import com.rabbit.green.baking.app.recipes.BaseActivity;
import com.rabbit.green.baking.app.recipes.steps.ItemListActivity;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> implements OnViewHolderClickListener {

    private List<Recipe> data;

    @Inject
    BaseActivity activity;

    @Inject
    public RecipeAdapter() {
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(binding, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public void setData(List<Recipe> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void onViewHolderClick(int position) {
        //TODO handle navigation by preparing activity scoped module and component
        Intent intent = new Intent(activity, ItemListActivity.class);
        intent.putExtra(ItemListActivity.BUNDLE_KEY_RECIPE, Parcels.wrap(data.get(position)));
        activity.startActivity(intent);
    }
}
