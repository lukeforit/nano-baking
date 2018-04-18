package com.rabbit.green.baking.app.recipes.steps.details;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.green.baking.app.BR;
import com.rabbit.green.baking.app.R;
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.recipes.BaseFragment;
import com.rabbit.green.baking.app.recipes.steps.StepsActivity;

import org.parceler.Parcels;

import javax.inject.Inject;

public class StepDetailFragment extends BaseFragment {

    public static final String ARG_STEP = "step";
    public static final String TAG = "TAG_StepDetailFragment";

    @Inject
    StepDetailsViewModel viewModel;

    public StepDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(ARG_STEP)) {
            setData(Parcels.<Step>unwrap(bundle.getParcelable(ARG_STEP)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_detail, container, false);
        binding.setVariable(BR.vm, viewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.preparePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.releasePlayer();
    }

    public void setData(Step step) {
        viewModel.setData(step);
    }
}
