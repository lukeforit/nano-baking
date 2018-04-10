package com.rabbit.green.baking.app.recipes.selection;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.recipes.BaseActivity;

import javax.inject.Inject;

public class SelectRecipeActivity extends BaseActivity {

    @Inject
    SelectRecipeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_select_recipe);
        binding.setVariable(BR.vm, viewModel);
        binding.executePendingBindings();
        viewModel.setup();
    }
}
