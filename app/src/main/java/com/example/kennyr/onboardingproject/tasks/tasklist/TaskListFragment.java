package com.example.kennyr.onboardingproject.tasks.tasklist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kennyr.onboardingproject.R;
import com.example.kennyr.onboardingproject.tasks.TaskContract;

import javax.inject.Inject;

public class TaskListFragment extends Fragment {

    // TODO: Update Task Detail to new format
    // TODO: Write unit tests

    @Inject
    TaskListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        ((TaskContract.ComponentProvider) getActivity()).getTaskComponent().with(new TaskListModule(this)).inject(this);
        presenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.stop();
    }
}
