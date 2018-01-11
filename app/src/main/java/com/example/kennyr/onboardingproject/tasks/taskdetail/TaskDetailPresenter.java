package com.example.kennyr.onboardingproject.tasks.taskdetail;

import com.example.kennyr.onboardingproject.R;
import com.example.kennyr.onboardingproject.Toolbar;
import com.example.kennyr.onboardingproject.tasks.Task;
import com.example.kennyr.onboardingproject.tasks.TaskDataSource;
import com.example.kennyr.onboardingproject.util.DateConverter;
import com.example.kennyr.onboardingproject.util.StringProvider;
import com.example.kennyr.onboardingproject.util.TextUtils;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class TaskDetailPresenter {

    private final TaskDetailView view;
    private final StringProvider stringProvider;
    private final TaskDataSource dataSource;
    private final Toolbar toolbar;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    TaskDetailPresenter(TaskDetailView view,
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
        registerSubscription(view.listenToChangeDateClicks()
                .subscribe(ignore -> onSelectDateRequested(), Throwable::printStackTrace));
        registerSubscription(view.listenToSaveTaskClicks()
                .subscribe(pair -> onSaveTaskRequested(pair.first, pair.second),
                        Throwable::printStackTrace));
    }

    void onEditExisitingTaskRequested(int index) {
        final Task task = dataSource.get(index);
        view.showExistingTaskDetails(task.title, task.description,
                DateConverter.dateToMonthDayYearLong(task.date));
    }

    private void setupToolbar() {
        toolbar.showTitle(stringProvider.getString(R.string.task_detail_title));
    }

    private void setupContent() {
        view.showDate(DateConverter.dateToMonthDayYearLong(new Date()));
    }

    private void registerSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private void onSelectDateRequested() {
        registerSubscription(view.getSelectedDate()
                .subscribe(date -> view.showDate(DateConverter.dateToMonthDayYearLong(date)),
                        Throwable::printStackTrace));
    }

    private void onSaveTaskRequested(Task task, int index) {
        if (TextUtils.isEmpty(task.title)) {
            view.showEmptyFieldErrorMessage(stringProvider.getString(R.string.missing_title_entry));
        } else if (TextUtils.isEmpty(task.description)) {
            view.showEmptyFieldErrorMessage(stringProvider.getString(R.string.missing_description_entry));
        } else if (index < 0 || index >= dataSource.getTasksList().size()) {
            dataSource.add(task);
            view.showTasksList();
        } else {
            dataSource.replace(task, index);
            view.showTasksList();
        }
    }

    void stop() {
        compositeDisposable.dispose();
    }
}
