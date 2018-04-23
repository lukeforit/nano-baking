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
import com.rabbit.green.baking.app.data.model.Step;
import com.rabbit.green.baking.app.recipes.BaseFragment;

import org.parceler.Parcels;

import javax.inject.Inject;

public class StepDetailFragment extends BaseFragment {

    public static final String ARG_STEP = "step";
    public static final String BUNDLE_KEY_STEP = "step";

    @Inject
    StepDetailsViewModel viewModel;

    public StepDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getParcelable(BUNDLE_KEY_STEP) != null) {
            setData(Parcels.<Step>unwrap(savedInstanceState.getParcelable(BUNDLE_KEY_STEP)));
        } else {
            Bundle bundle = getArguments();
            if (bundle != null && bundle.containsKey(ARG_STEP)) {
                setData(Parcels.<Step>unwrap(bundle.getParcelable(ARG_STEP)));
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_KEY_STEP, Parcels.wrap(viewModel.getStep()));
    }

    public void setData(Step step) {
        viewModel.setData(step);
    }

    public static StepDetailFragment newInstance(Step step) {
        StepDetailFragment fragment = new StepDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(
                StepDetailFragment.ARG_STEP, Parcels.wrap(step));
        fragment.setArguments(bundle);
        return fragment;
    }
}
