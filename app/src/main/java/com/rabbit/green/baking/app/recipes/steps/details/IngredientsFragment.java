package com.rabbit.green.baking.app.recipes.steps.details;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Ingredient;
import com.rabbit.green.baking.app.recipes.BaseFragment;

import java.util.List;

import javax.inject.Inject;

public class IngredientsFragment extends BaseFragment {

    public static final String TAG = "TAG_IngredientsFragment";

    private static final String ARG_INGREDIENTS = "ingredients";

    @Inject
    IngredientsViewModel viewModel;

    public IngredientsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //TODO retrieve args
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ingredients,
                container, false);
        binding.setVariable(BR.vm, viewModel);
        return binding.getRoot();
    }

    public static IngredientsFragment newInstance(List<Ingredient> ingredientList) {
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
        //TODO add list to arguments
        fragment.setArguments(args);
        return fragment;
    }
}
