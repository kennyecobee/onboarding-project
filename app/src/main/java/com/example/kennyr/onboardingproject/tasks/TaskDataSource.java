package com.example.kennyr.onboardingproject.tasks;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class TaskDataSource {

    private ArrayList<Task> tasks = new ArrayList<>();

    @Inject
    TaskDataSource() {
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int position) {
        return tasks.get(position);
    }

    public List<Task> getTasksList() {
        return tasks;
    }

    public void replace(Task editedTask, int position) {
        tasks.set(position, editedTask);
    }

    public void clear() {
        tasks.clear();
    }
}
