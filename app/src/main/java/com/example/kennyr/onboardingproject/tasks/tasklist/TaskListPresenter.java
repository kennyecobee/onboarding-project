package com.example.kennyr.onboardingproject.tasks.tasklist;

import com.example.kennyr.onboardingproject.R;
import com.example.kennyr.onboardingproject.Toolbar;
import com.example.kennyr.onboardingproject.tasks.TaskDataSource;
import com.example.kennyr.onboardingproject.util.StringProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class TaskListPresenter {

    private final TaskListView view;
    private final StringProvider stringProvider;
    private final TaskDataSource dataSource;
    private final Toolbar toolbar;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    TaskListPresenter(TaskListView view,
                      StringProvider stringProvider,
                      TaskDataSource dataSource,
                      Toolbar toolbar) {
        this.view = view;
        this.stringProvider = stringProvider;
        this.dataSource = dataSource;
        this.toolbar = toolbar;
    }

    void start() {
        view.start();
        setupToolbar();
        setupContent();
        registerSubscription(view.listenToNewTaskClicks().subscribe(ignore -> view.showTaskDetail(),
                Throwable::printStackTrace));
        registerSubscription(view.listenToEditTaskClicks().subscribe(view::showTaskDetail,
                Throwable::printStackTrace));
    }

    private void setupToolbar() {
        toolbar.showTitle(stringProvider.getString(R.string.task_list_title));
    }

    private void setupContent() {
        if (dataSource.getTasksList() == null || dataSource.getTasksList().isEmpty()) {
            view.hideTasksList();
            view.showNoTasksMessage();
        } else {
            view.hideNoTasksMessage();
            view.showTasksList(dataSource.getTasksList());
        }
    }

    private void registerSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    void stop() {
        compositeDisposable.dispose();
    }
}
