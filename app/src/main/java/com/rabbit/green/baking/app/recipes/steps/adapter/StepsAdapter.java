package com.rabbit.green.baking.app.recipes.steps.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.common.adapter.OnViewHolderClickListener;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.data.model.StepTitle;

import java.util.List;

import javax.inject.Inject;

public class StepsAdapter extends RecyclerView.Adapter<StepItemViewHolder>
        implements OnViewHolderClickListener {

    private static final int DATA_SHIFT = 1;

    private List<Step> data;

    private OnStepClickListener listener;

    private StepTitle ingredientStep = new StepTitle() {
        @Override
        public String title() {
            return "Ingredients";
        }
    };

    @Inject
    public StepsAdapter() {
    }

    @NonNull
    @Override
    public StepItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_step, parent, false);
        return new StepItemViewHolder(binding, this);
    }

    @Override
    public void onBindViewHolder(@NonNull StepItemViewHolder holder, int position) {
        if (position < DATA_SHIFT) {
            holder.bind(ingredientStep);
        } else {
            holder.bind(data.get(position - DATA_SHIFT));
        }
    }

    public void setData(List<Step> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null ? DATA_SHIFT : data.size() + DATA_SHIFT;
    }

    @Override
    public void onViewHolderClick(int position) {
        if (listener != null) {
            listener.onStepClick(position - DATA_SHIFT);
        }
    }

    public void setListener(OnStepClickListener listener) {
        this.listener = listener;
    }
}
