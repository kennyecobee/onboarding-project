package com.example.kennyr.onboardingproject.tasks.taskdetail;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskDetailModule {

    private final TaskDetailFragment fragment;

    TaskDetailModule(TaskDetailFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideView() {
        return fragment;
    }
}
