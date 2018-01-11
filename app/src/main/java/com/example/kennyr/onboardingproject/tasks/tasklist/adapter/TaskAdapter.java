package com.example.kennyr.onboardingproject.tasks.tasklist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.kennyr.onboardingproject.tasks.Task;
import com.example.kennyr.onboardingproject.tasks.tasklist.OnTaskSelectedListener;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private OnTaskSelectedListener listener;
    private List<Task> tasks;

    public TaskAdapter(OnTaskSelectedListener listener) {
        this.listener = listener;
    }


    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.bind(tasks.get(position), position);
    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }
}
