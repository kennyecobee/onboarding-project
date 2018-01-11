package com.example.kennyr.onboardingproject.tasks.tasklist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kennyr.onboardingproject.R;
import com.example.kennyr.onboardingproject.tasks.Task;
import com.example.kennyr.onboardingproject.tasks.tasklist.OnTaskSelectedListener;
import com.example.kennyr.onboardingproject.util.DateConverter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.task_title)
    TextView title;

    @BindView(R.id.task_description)
    TextView description;

    @BindView(R.id.date)
    TextView date;

    private OnTaskSelectedListener listener;
    private int position;

    private TaskViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setClickable(true);
        itemView.setOnClickListener(this);
    }

    public TaskViewHolder(ViewGroup parent, OnTaskSelectedListener listener) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false));
        this.listener = listener;
    }

    public void bind(Task task, int position) {
        this.position = position;
        title.setText(task.title);
        description.setText(task.description);
        date.setText(DateConverter.dateToMonthDayYearShort(task.date));
    }

    @Override
    public void onClick(View view) {
        listener.onClick(position);
    }
}
