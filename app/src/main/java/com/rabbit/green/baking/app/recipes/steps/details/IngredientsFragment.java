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

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

public class IngredientsFragment extends BaseFragment {

    private static final String ARG_INGREDIENTS = "ingredients";

    @Inject
    IngredientsViewModel viewModel;

    public IngredientsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            List<Ingredient> list = Parcels.unwrap(bundle.getParcelable(ARG_INGREDIENTS));
            viewModel.setup(list);
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
        args.putParcelable(ARG_INGREDIENTS, Parcels.wrap(ingredientList));
        fragment.setArguments(args);
        return fragment;
    }
}
