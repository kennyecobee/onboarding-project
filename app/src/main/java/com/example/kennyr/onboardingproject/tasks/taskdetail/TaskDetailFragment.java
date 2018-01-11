package com.example.kennyr.onboardingproject.tasks.taskdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kennyr.onboardingproject.R;
import com.example.kennyr.onboardingproject.tasks.TaskContract;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class TaskDetailFragment extends Fragment {

    public static final String TASK_INDEX_EXTRA = "_task_index_extra";
    public static final int INVALID_INDEX = -1;

    @Inject
    TaskDetailPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setRetainInstance(true);

        TaskDetailComponent component = ((TaskContract.ComponentProvider) getActivity()).getTaskComponent()
                .with(new TaskDetailModule(this));
        component.inject(this);
        presenter.start();

        if (getArguments() != null) {
            presenter.onEditExisitingTaskRequested(getArguments().getInt(TASK_INDEX_EXTRA));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.stop();
    }
}
