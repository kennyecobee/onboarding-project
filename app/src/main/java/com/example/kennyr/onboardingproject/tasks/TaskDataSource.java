package com.example.kennyr.onboardingproject.tasks;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

public class TaskDataSource {

    private static TaskDataSource dataSource;

    private ArrayList<Task> tasks = new ArrayList<>();

    private TaskDataSource() {
    }

    public void add(Task task) {
        dataSource.tasks.add(task);
    }

    public Task get(int position) {
        return dataSource.tasks.get(position);
    }

    public List<Task> getTasksList() {
        return dataSource.tasks;
    }

    public void replace(Task editedTask, int position) {
        dataSource.tasks.set(position, editedTask);
    }

    public void clear() {
        dataSource.tasks.clear();
    }

    public static TaskDataSource getInstance() {
        if (dataSource == null) {
            dataSource = new TaskDataSource();
        }
        return dataSource;
    }
}
