package com.example.kennyr.onboardingproject.tasks.taskdetail;

import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kennyr.onboardingproject.DatePickerDialog;
import com.example.kennyr.onboardingproject.R;
import com.example.kennyr.onboardingproject.tasks.Task;
import com.example.kennyr.onboardingproject.tasks.TaskContract;
import com.example.kennyr.onboardingproject.tasks.tasklist.TaskListFragment;
import com.example.kennyr.onboardingproject.util.DateConverter;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class TaskDetailView {

    @BindView(R.id.title_entry)
    EditText titleEntry;

    @BindView(R.id.description_entry)
    EditText descriptionEntry;

    @BindView(R.id.date_entry)
    EditText dateEntry;

    @BindView(R.id.save_task_button)
    Button saveButton;

    private Fragment fragment;
    private PublishSubject<Boolean> changeDateClicks = PublishSubject.create();
    private PublishSubject<Pair<Task, Integer>> saveTaskClicks = PublishSubject.create();

    @Inject
    TaskDetailView(Fragment fragment) {
        this.fragment = fragment;
    }

    void start() {
        ButterKnife.bind(this, fragment.getView());
        setupOnClickListeners();
    }

    Observable<Boolean> listenToChangeDateClicks() {
        return changeDateClicks;
    }

    Observable<Pair<Task, Integer>> listenToSaveTaskClicks() {
        return saveTaskClicks;
    }

    void showDate(String date) {
        dateEntry.setText(date);
    }

    Observable<Date> getSelectedDate() {
        Calendar calendar = Calendar.getInstance();
        if (dateEntry.getText() != null && !TextUtils.isEmpty(dateEntry.getText().toString())) {
            calendar.setTime(DateConverter.dateFromMonthDayYearLong(dateEntry.getText().toString()));
        }

        final DatePickerDialog dialog = new DatePickerDialog(fragment.getContext(),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        return dialog.create();
    }

    void showEmptyFieldErrorMessage(String message) {
        Toast.makeText(fragment.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    void showTasksList() {
        ((TaskContract.TaskActivityContract) fragment.getActivity()).replaceFragment(new TaskListFragment(),
                TaskContract.FragmentTag.LIST, null);
    }

    void showExistingTaskDetails(String title, String description, String date) {
        titleEntry.setText(title);
        descriptionEntry.setText(description);
        dateEntry.setText(date);
    }

    private void setupOnClickListeners() {
        dateEntry.setOnClickListener(ignore -> changeDateClicks.onNext(true));

        final int index = fragment.getArguments() != null ?
                fragment.getArguments().getInt(TaskDetailFragment.TASK_INDEX_EXTRA) :
                TaskDetailFragment.INVALID_INDEX;

        saveButton.setOnClickListener(ignore -> {
            Task task = new Task(titleEntry.getText().toString(),
                    descriptionEntry.getText().toString(),
                    DateConverter.dateFromMonthDayYearLong(dateEntry.getText().toString()));
            saveTaskClicks.onNext(Pair.create(task, index));
        });
    }

}
