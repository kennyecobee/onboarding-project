package com.example.kennyr.onboardingproject.tasks.tasklist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.kennyr.onboardingproject.R;
import com.example.kennyr.onboardingproject.tasks.Task;
import com.example.kennyr.onboardingproject.tasks.TaskContract;
import com.example.kennyr.onboardingproject.tasks.taskdetail.TaskDetailFragment;
import com.example.kennyr.onboardingproject.tasks.tasklist.adapter.TaskAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class TaskListView implements OnTaskSelectedListener {

    @BindView(R.id.new_task_button)
    FloatingActionButton newTaskButton;

    @BindView(R.id.task_list)
    RecyclerView taskList;

    @BindView(R.id.no_tasks_text)
    TextView noTasksText;

    private final Fragment fragment;
    private final Toolbar toolbar;
    private PublishSubject<Boolean> newTaskClicks = PublishSubject.create();
    private PublishSubject<Integer> editTaskClicks = PublishSubject.create();

    @Inject
    TaskListView(Fragment fragment, Toolbar toolbar) {
        this.fragment = fragment;
        this.toolbar = toolbar;
    }

    void start() {
        ButterKnife.bind(this, fragment.getView());
        taskList.setLayoutManager(new LinearLayoutManager(fragment.getContext()));

        registerClickListeners();
    }

    Observable<Boolean> listenToNewTaskClicks() {
        return newTaskClicks;
    }

    Observable<Integer> listenToEditTaskClicks() {
        return editTaskClicks;
    }

    void showNoTasksMessage() {
        noTasksText.setVisibility(View.VISIBLE);
    }

    void hideNoTasksMessage() {
        noTasksText.setVisibility(View.GONE);
    }

    void showTasksList(List<Task> tasks) {
        TaskAdapter adapter = new TaskAdapter(this);
        taskList.setAdapter(adapter);
        adapter.setTasks(tasks);
        taskList.setVisibility(View.VISIBLE);
    }

    void showTaskDetail() {
        ((TaskContract.TaskActivityContract) fragment.getActivity())
                .replaceFragment(new TaskDetailFragment(),
                        TaskContract.FragmentTag.DETAIL,
                        null);
    }

    void showTaskDetail(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(TaskDetailFragment.TASK_INDEX_EXTRA, index);
        ((TaskContract.TaskActivityContract) fragment.getActivity())
                .replaceFragment(new TaskDetailFragment(),
                        TaskContract.FragmentTag.DETAIL,
                        bundle);
    }

    void hideTasksList() {
        taskList.setVisibility(View.GONE);
    }

    @Override
    public void onClick(int position) {
        editTaskClicks.onNext(position);
    }

    private void registerClickListeners() {
        newTaskButton.setOnClickListener(ignore -> newTaskClicks.onNext(true));
    }

    public void setTitle(String title) {
        toolbar.setTitle(title);
    }
}
