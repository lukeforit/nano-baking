package com.rabbit.green.baking.app.recipes.steps;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.common.adapter.BaseAdapter;
import com.rabbit.green.baking.app.data.model.Step;

import javax.inject.Inject;

public class StepsAdapter extends BaseAdapter<Step> {

    private OnStepClickListener listener;

    @Inject
    public StepsAdapter() {
    }

    @Override
    public void onViewHolderClick(int position) {
        if (listener != null) {
            listener.onStepClick(position);
        }
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_step;
    }

    public void setListener(OnStepClickListener listener) {
        this.listener = listener;
    }
}
